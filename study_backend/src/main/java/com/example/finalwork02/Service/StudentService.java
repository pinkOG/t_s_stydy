package com.example.finalwork02.Service;




import com.example.finalwork02.DTO.AssignmentsTasksAndUri;
import com.example.finalwork02.DTO.ReviewRecordAndUri;
import com.example.finalwork02.Dao.*;


import com.example.finalwork02.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StudentService {

    @Autowired
    private SignInTaskRepository signInTaskRepository;

    @Autowired
    private SignInRecordRepository signInRecordRepository;
    @Autowired
    private TeacherCoursesRepository teacherCoursesRepository;
    @Autowired
    private AssignmentsTasksRepository assignmentsTasksRepository;
    @Autowired
    private StudentDetailsRepository studentDetailsRepository;
    @Autowired
    private  FileService fileService;
    @Autowired
    private LearningMaterialRepository learningMaterialRepository;
    @Autowired
    private ReviewRecordRepository reviewRecordRepository;
    @Autowired
    private AssignmentsRepository assignmentsRepository;

    /**
     * 学生签到
     * @param  signNumber 签到码
     * @param studentId 学生ID
     * @return 创建的签到记录
     * 没写重复签到的问题
     */
    public SignInRecord signIn(int signNumber, int studentId) {
        // 找到签到任务
        List<SignInTask> tasks = signInTaskRepository.findBySignCode(signNumber);
        if (tasks.size() == 0) {
            return null;
        }

        SignInTask task = tasks.get(0);
        List<SignInRecord> signInRecordList = signInRecordRepository.findBySignInTask_TaskId(task.getTaskId());
        for (SignInRecord signInRecord : signInRecordList) {
            if(signInRecord.getStudentId()==studentId){
                System.out.println("重复签到");
                return null;
            }
        }
        LocalDateTime now = LocalDateTime.now();

        SignInRecord.Status status;

        if (now.isAfter(task.getEndTime().plusHours(1).plusMinutes(30))) {
            status = SignInRecord.Status.ABSENT;
        } else if (now.isAfter(task.getEndTime())) {
            status = SignInRecord.Status.LATE;
        } else {
            status = SignInRecord.Status.ON_TIME;
        }

        SignInRecord record = new SignInRecord();
        record.setSignInTask(task);
        record.setStudentId(studentId);
        record.setSignInTime(now);
        record.setStatus(status);

        return signInRecordRepository.save(record);
    }
    /**
     * 查看签到状态
     * @param studentId 学生ID
     * @return 签到记录列表
     */
    @Autowired
    UserRepository userRepository;
    public List<SignInRecordWithUsername> viewSignInStatus(int studentId) {
        List<SignInRecord> signInRecordList = signInRecordRepository.findByStudentId(studentId);
        Optional<StudentDetails> studentDetail = studentDetailsRepository.findById(studentId);
        StudentDetails details = studentDetail.get();
        Integer userId = details.getUserId();
        Optional<User> userTemp = userRepository.findById(userId);
        String name = userTemp.get().getName();


        List<SignInRecordWithUsername>  mylist=new ArrayList<SignInRecordWithUsername>();

        for (SignInRecord signInRecord : signInRecordList) {
            SignInRecordWithUsername signInRecordWithUsername = new SignInRecordWithUsername(signInRecord,name);
            mylist.add(signInRecordWithUsername);
        }


        return mylist;
    }

    /**
     * 加课
     * @param studentId
     * @param entryCode
     * @return
     */
    @Autowired
    StudentCourseRepository studentCourseRepository;
    @Transactional
    public TeacherCourseAndCount joinCourse(int studentId, String entryCode) {
        Optional<TeacherCourse> course = teacherCoursesRepository.findByEntryNumber(entryCode);
//        已加课就不让加了
        if( !course.isPresent()){
            return null;
        }
        //存在则获取教师课程
        TeacherCourse course_exist = course.get();
        int courseId = course_exist.getCourseId();
        //保存学生与课程对应关系
        StudentCourse studentCourse = new StudentCourse(studentId,courseId);
        studentCourseRepository.save(studentCourse);
        //找到选课总人数
        int sum = studentCourseRepository.findCourseSumByCourseId(courseId);
        TeacherCourseAndCount teacherCourseAndCount = new TeacherCourseAndCount(course_exist, sum);
        System.out.println(sum);
        System.out.println("--------------------");
        return teacherCourseAndCount;

    }


    public List<TeacherCourseAndCount> getCourseList(int studentId) {
        //所有的课程主键
        List<Integer> courseIdList = studentCourseRepository.findByStudentId(studentId);

        List<TeacherCourse> courseList = teacherCoursesRepository.findByCourseIdIn(courseIdList);

        List<TeacherCourseAndCount> returnList=new LinkedList<>();

        for (TeacherCourse course : courseList) {
            int courseId = course.getCourseId();
            int count = studentCourseRepository.findCourseSumByCourseId(courseId);
            TeacherCourseAndCount teacherCourseAndCount = new TeacherCourseAndCount(course,count);
            returnList.add(teacherCourseAndCount);
        }
        return  returnList;
    }
    public List<TeacherCourseAndCountDTO> getCourseListAndImage(int studentId) throws IOException {

     List<TeacherCourseAndCountDTO>  dtoList =new LinkedList<>();

     List<Integer> courseIdList = studentCourseRepository.findByStudentId(studentId);
        for (Integer i : courseIdList) {
            TeacherCourseAndCountDTO dto= new TeacherCourseAndCountDTO();

            String courseImage = fileService.getCourseImage(i);
            dto.setUriLink(courseImage);

            int count = studentCourseRepository.findCourseSumByCourseId(i);
            dto.setCount(count);
            TeacherCourse course = teacherCoursesRepository.findByCourseId(i);
            dto.setTeacherCourse(course);

            dtoList.add(dto);

        }
        return  dtoList;
    }



    public List<AssignmentsTasksAndUri> getHomeworkList(int courseId) {
//        获取课程列表
        List<AssignmentsTasks> courseList = assignmentsTasksRepository.findByCourseId(courseId);
        List<AssignmentsTasksAndUri> assignmentsAndUriList= new ArrayList<>();
        for (AssignmentsTasks assignmentsTasks : courseList) {
            AssignmentsTasksAndUri assignmentsTasksAndUri = new AssignmentsTasksAndUri();
            String pathFile = assignmentsTasks.getPathFile();
            String uriLink = fileService.getfileDownloadUri(pathFile);
            assignmentsTasksAndUri.setAssignmentsTasks(assignmentsTasks);
            assignmentsTasksAndUri.setUriLink(uriLink);
            assignmentsAndUriList.add(assignmentsTasksAndUri);
        }
        return assignmentsAndUriList;
    }



    public StudentDetails findByUserId(int userId) {

       return studentDetailsRepository.findByUserId(userId);

   }
    public StudentDetails saveStudentDetails(StudentDetails studentDetails) {

         return studentDetailsRepository.save(studentDetails);

    }



    public List<ReviewRecordAndUri> getReviewToList(int taskId, int studentId) throws MalformedURLException {
        // 找到需要批改的作业记录
        List<ReviewRecord> list = reviewRecordRepository.findByTaskIdAndAssignedTo(taskId, studentId);

        //List<Map<String, ReviewRecord>> mapList = new ArrayList<>();
        List<ReviewRecordAndUri> reviewRecordAndUris = new ArrayList<>();

        for (ReviewRecord reviewRecord : list) {

            // 需要批改的学生id
            Integer assignedToId = reviewRecord.getAssignedTo();
            // 找到需要批改的作业
            Assignments assignments = assignmentsRepository.findByTaskIdAndStudentId(taskId, assignedToId);
            // 获得实际的资源文件路径
            String filePath = assignments.getFilePath();
            String fileDownloadUri = fileService.getfileDownloadUri(filePath);
            ReviewRecordAndUri reviewRecordAndUri = new ReviewRecordAndUri();

            reviewRecordAndUri.setReviewRecord(reviewRecord);
            reviewRecordAndUri.setUriLink(fileDownloadUri);
            if (reviewRecordAndUri.getReviewRecord().getScore().compareTo(BigDecimal.ZERO) == 0) {
                reviewRecordAndUris.add(reviewRecordAndUri);
            }

        }
        // 返回要批改的作业下载链接以及被批改人id
        return reviewRecordAndUris;
    }
    //给别人打分；如果超时，那么删除这个字段
    public ReviewRecord scoreToOthers(ReviewRecord reviewRecord) {

        return reviewRecordRepository.save(reviewRecord);
    }

    //显示已经提交的作业
    public List<List<AssignmentsTasksAndUri>> getAllHomeworkList(int studentId) {
        List<Integer> courseIdlist = studentCourseRepository.findByStudentId(studentId);
        List<List<AssignmentsTasksAndUri>> listList=new ArrayList<>();
        for (Integer i : courseIdlist) {
            List<AssignmentsTasks> assignmentsTasks = assignmentsTasksRepository.findByCourseId(i);
            List<AssignmentsTasksAndUri> list =new ArrayList<>();
            for (AssignmentsTasks assignmentsTask : assignmentsTasks) {
                int taskId = assignmentsTask.getTaskId();
                Assignments assignments = assignmentsRepository.findByTaskIdAndStudentId(taskId, studentId);
//              如果已经提交，就不管；
                if(assignments!=null){
                    continue;
                }
                AssignmentsTasksAndUri assignmentsTasksAndUri = new AssignmentsTasksAndUri();
                String pathFile = assignmentsTask.getPathFile();
                String uriLink = fileService.getfileDownloadUri(pathFile);
                assignmentsTasksAndUri.setUriLink(uriLink);
                assignmentsTasksAndUri.setAssignmentsTasks(assignmentsTask);
                list.add(assignmentsTasksAndUri);
            }
            if(!list.isEmpty()){
                listList.add(list);
            };

        }
        return  listList ;

    }

    public boolean isSumitedHomework(int studentId, int taskId) {
        Assignments byTaskIdAndStudentId = assignmentsRepository.findByTaskIdAndStudentId(taskId, studentId);
        return  byTaskIdAndStudentId!=null;
    }
    @Autowired
    ScoreAnalysisInitialRepository scoreAnalysisInitialRepository;
    public List<ScoreAnalysisInitial> getHomeworkScore(int courseId, int studentId) {
        List<ScoreAnalysisInitial> scoreAnalysisInitials = scoreAnalysisInitialRepository.findByStudentId(studentId);

        return  scoreAnalysisInitials;

    }
}
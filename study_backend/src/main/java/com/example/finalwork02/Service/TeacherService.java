package com.example.finalwork02.Service;




import com.example.finalwork02.Dao.*;

import com.example.finalwork02.pojo.*;
import com.example.finalwork02.utils.EntryNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private SignInTaskRepository signInTaskRepository;

    @Autowired
    private SignInRecordRepository signInRecordRepository;

    @Autowired
    private TeacherCoursesRepository teacherCoursesRepository;

    @Autowired
    private AssignmentsTasksRepository assignmentsTasksRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private  StudentCourseRepository studentCourseRepository;
    @Autowired
    private AssignmentsRepository assignmentsRepository;
    @Autowired
    private ReviewRecordRepository reviewRecordRepository;
    @Autowired
    private ScoreAnalysisInitialRepository scoreAnalysisInitialRepository;
    @Autowired
    private TeacherDetailsRepository teacherDetailsRepository;

    /**
     * 创建签到任务
     * @param teacherId 教师ID
     * @param startTime 签到开始时间
     * @param endTime 签到结束时间
     * @return 创建的签到任务
     */
    public SignInTask createSignInTask(int teacherId, LocalDateTime startTime, LocalDateTime endTime,int signCode) {
        SignInTask task = new SignInTask();
        task.setTeacherId(teacherId);
        task.setStartTime(startTime);
        task.setEndTime(endTime);

        task.setSignCode(signCode);
        System.out.println(task);

        return signInTaskRepository.save(task);
    }

    /**
     * 查看签到结果
     *
     * @param taskId 签到任务ID
     * @return 签到记录列表
     */
    public List<SignInRecordWithUsername> viewSignInResults(int taskId) {

        return signInRecordRepository.findSignInRecordsWithUsernamesByTaskId(taskId);
    }

    /**
     * 教师手动签到
     * @param taskId 签到任务ID
     * @param studentId 学生ID
     * @param status 签到状态
     * @return 创建的签到记录
     */
    public SignInRecord manualSignIn(int taskId, int studentId, SignInRecord.Status status) {
        SignInRecord record = new SignInRecord();
        record.setSignInTask(signInTaskRepository.findById(taskId).orElseThrow());
        record.setStudentId(studentId);
        record.setStatus(status);
        record.setSignInTime(LocalDateTime.now());
        return signInRecordRepository.save(record);
    }

    /**
     * 添加课程
     * @param teacherId
     * @param courseName
     * @param className
     * @return
     */
    @Transactional
    public TeacherCourse addCourse(int teacherId, String courseName,String className) {
        String entryCode;
        if(teacherDetailsRepository.findById(teacherId)== null)
            return null;
        // 保证生成的entryCode是唯一的
        do {
            entryCode = EntryNumberGenerator.generateUniqueCode();
        } while (teacherCoursesRepository.findByEntryNumber(entryCode).isPresent());

        // 设置唯一的entryCode
        TeacherCourse course = new TeacherCourse();
        Integer i = teacherCoursesRepository.findMaxId().get();
        course.setCourseId(i+1);
        course.setCourseName(courseName);
        course.setClassName(className);
        course.setTeacherId(teacherId);
        course.setEntryNumber(entryCode);
        course.setCreateAt(LocalDateTime.now());

        // 保存课程
        TeacherCourse savedCourse = teacherCoursesRepository.save(course);

        // 更新教师详情

        return savedCourse;
    }
    //添加课程图片
    public boolean addCourseImage(int courseId, MultipartFile file){
        LearningMaterial learningMaterial = fileService.storeImage(file, courseId);
        if(learningMaterial == null)
            return false;
        else
            return  true;
    }

    public List<TeacherCourse> getCourseList(int teacherId) {
        return teacherCoursesRepository.findByTeacherId(teacherId);
    }


    public AssignmentsTasks publishHomework(int courseId, LocalDateTime parseStartTime, LocalDateTime parseEndTime,String description) {
        AssignmentsTasks assignmentsTasks = new AssignmentsTasks();
        assignmentsTasks.setCourseId(courseId);
        assignmentsTasks.setStartTime(parseStartTime);
        assignmentsTasks.setEndTime(parseEndTime);
        assignmentsTasks.setDescription(description);

        return  assignmentsTasksRepository.save(assignmentsTasks);
    }

    public TeacherCourse getCourseDetail(int courseId) {

        return teacherCoursesRepository.findByCourseId(courseId);

    }

    public List<TeacherCourseAndCountDTO> getCourseAndImage(int teacherId) throws MalformedURLException {


        List<TeacherCourseAndCountDTO>  dtoList =new LinkedList<>();

        List<TeacherCourse> courseList = teacherCoursesRepository.findByTeacherId(teacherId);

        for (TeacherCourse course : courseList) {
            TeacherCourseAndCountDTO dto= new TeacherCourseAndCountDTO();
            //课程详情
            dto.setTeacherCourse(course);
            int courseId = course.getCourseId();

            //图片资源
            String courseImage = fileService.getCourseImage(courseId);
            dto.setUriLink(courseImage);

            //选课人数
            int count = studentCourseRepository.findCourseSumByCourseId(courseId);
            dto.setCount(count);


            dtoList.add(dto);

        }

        return  dtoList;


    }



    public TeacherDetails findByUserId(int userId) {
        return teacherDetailsRepository.findByUserId(userId);
    }

    public Resource publishHomeworkAndSave(MultipartFile file, AssignmentsTasks assignmentsTasks) {

        Resource resource = fileService.storeHomeWorkFile(file, assignmentsTasks);
        return resource;
    }
    //发布互改任务
    public boolean setReviewAssignment(int taskId, LocalDateTime startTime, LocalDateTime endTime) {
        //已经发布过互改任务了
        if(!reviewRecordRepository.findByTaskId(taskId).isEmpty()){
            return false;
        }

        // 获取提交该任务的所有作业
        List<Assignments> workList = assignmentsRepository.findByTaskId(taskId);

        if (workList == null || workList.isEmpty()) {
            // 如果没有找到任何作业，返回false
            return false;
        }

        // 获取所有提交该任务的学生ID
        List<Integer> studentIds = workList.stream()
                .map(Assignments::getStudentId)
                .collect(Collectors.toList());

        // 如果学生数量少于3，返回false，因为无法有效分配任务
        if (studentIds.size() < 3) {
            return false;
        }

        // 记录每个学生的作业被批改的次数
        Map<Integer, Integer> studentReviewCount = new HashMap<>();
        // 记录每个批改者的批改次数
        Map<Integer, Integer> graderReviewCount = new HashMap<>();

        // 初始化计数
        for (Integer studentId : studentIds) {
            studentReviewCount.put(studentId, 0);
            graderReviewCount.put(studentId, 0);
        }

        // 按照学生ID进行循环分配
        for (Assignments assignments : workList) {
            int studentId = assignments.getStudentId();

            // 获取除当前学生之外的所有学生ID列表
            List<Integer> reviewers = new ArrayList<>(studentIds);
            reviewers.remove(Integer.valueOf(studentId));

            // 随机打乱列表顺序
            Collections.shuffle(reviewers);

            // 选取最多三个学生作为批改者
            int assignedCount = 0;
            for (Integer reviewerId : reviewers) {
                if (assignedCount >= 3) break;

                // 检查批改者和被批改者的计数
                if (graderReviewCount.get(reviewerId) < 3 && studentReviewCount.get(studentId) < 3) {
                    ReviewRecord reviewRecord = new ReviewRecord();
                    // 被批改人的ID
                    reviewRecord.setStudentId(studentId);
                    // 批改人ID
                    reviewRecord.setAssignedTo(reviewerId);
                    // 设置其他必要字段，例如任务ID，开始时间，结束时间
                    reviewRecord.setTaskId(taskId);
                    reviewRecord.setStartTime(startTime);
                    reviewRecord.setEndTime(endTime);

                    try {
                        reviewRecordRepository.save(reviewRecord);

                        // 更新计数
                        graderReviewCount.put(reviewerId, graderReviewCount.get(reviewerId) + 1);
                        studentReviewCount.put(studentId, studentReviewCount.get(studentId) + 1);
                        assignedCount++;
                    } catch (Exception e) {
                        // 处理保存时可能出现的异常
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return true;
    }
    @Transactional
    public boolean startScoreAnalysisInitial(int courseId) {

        //找到这门课的所有作业任务
        List<AssignmentsTasks> tasksList = assignmentsTasksRepository.findByCourseId(courseId);

//        ArrayList<AssignmentsTasks> assignmentsTasks = new ArrayList<>();
        //只分析没分析过的
        for (AssignmentsTasks tasks : tasksList) {
            int taskId = tasks.getTaskId();
        //已经分析过的将分析过的数据删除
            if(scoreAnalysisInitialRepository.findByTaskId(taskId).size()!=0){
                scoreAnalysisInitialRepository.deleteByTaskId(taskId);
            }
        }
        ;

        return saveDetailBytaskList(tasksList)  ;

    }
    public  List<ScoreAnalysisInitial> getDetailAfterOperation( int courseId){
        //找到这门课的所有作业任务
        List<AssignmentsTasks> tasksList = assignmentsTasksRepository.findByCourseId(courseId);
        ArrayList<Integer> integers = new ArrayList<>();
        for (AssignmentsTasks tasks : tasksList) {
            integers.add(tasks.getTaskId());
        }
        return scoreAnalysisInitialRepository.findAll();
    }
    @Transactional
    public boolean saveDetailBytaskList(List<AssignmentsTasks> tasksList){
        //每个作业任务
        for (AssignmentsTasks assignmentsTask : tasksList) {

            //统计及格率
            int passRateCount = 0;

            int taskId = assignmentsTask.getTaskId();
            //每次任务的所有作业
            List<Assignments> homeworkList = assignmentsRepository.findByTaskId(taskId);

            //保存最终成绩
            saveFinalGrades(homeworkList,taskId);
            //找到单次的最终成绩
//                BigDecimal bigDecimal = scoreAnalysisInitialRepository.queryByTaskIdAndStudentId(assignments.getStudentId(), taskId);
            BigDecimal totalValue = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            //找到每次任务中最大的值
            BigDecimal tempMaxValue = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
            BigDecimal tempMinValue = new BigDecimal(1000).setScale(2, RoundingMode.HALF_UP);
            //找到每次任务中的最小值
            BigDecimal passScore = new BigDecimal(60).setScale(2, RoundingMode.HALF_UP);

            //找到该任务的成绩
//                List<BigDecimal> scoreDecimalList = scoreAnalysisInitialRepository.findScoreListByTaskId(taskId);
            List<ScoreAnalysisInitial> analysisInitials = scoreAnalysisInitialRepository.findByTaskId(taskId);

            for (ScoreAnalysisInitial analysisInitial : analysisInitials) {
                BigDecimal finalScore = analysisInitial.getFinalScore();
                tempMaxValue=tempMaxValue.max(finalScore);
                tempMinValue=tempMinValue.min(finalScore);
                if (finalScore.compareTo(passScore)>=0){
                    passRateCount++;
                }
                totalValue=totalValue.add(finalScore);
            }

            //每次循环结束时候才能统计及格率
            BigDecimal passRate = new BigDecimal(passRateCount).divide(new BigDecimal(analysisInitials.size()), 2, RoundingMode.HALF_UP);
            //平均分
            BigDecimal averageScore = totalValue.divide(new BigDecimal(analysisInitials.size()), 2, RoundingMode.UP);
            scoreAnalysisInitialRepository.saveDetails(tempMaxValue,tempMinValue,averageScore,passRate,taskId);



            int tempTaskId = assignmentsTask.getTaskId();
            scoreAnalysisInitialRepository.updatePassRateByTaskId(passRate, tempTaskId);

        }



        //每个学生每次作业的分数-平均分
        return true;
    }
    @Transactional
    public void saveFinalGrades(List<Assignments> homeworkList,int taskId){
        for (Assignments assignments : homeworkList) {
            ScoreAnalysisInitial analysistBiaoGe = new ScoreAnalysisInitial();

            int studentId = assignments.getStudentId();
            //单次作业 被其它同学批改的记录
            List<ReviewRecord> singleReviewRecord = reviewRecordRepository.findByTaskIdAndStudentId(taskId, studentId);
            int count = 0;
            BigDecimal totalScore = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            for (ReviewRecord reviewRecord : singleReviewRecord) {
                System.out.println(reviewRecord.getScore());
                totalScore = totalScore.add(reviewRecord.getScore().setScale(2, RoundingMode.HALF_UP));
                count++;
            }
            //每次作业的分数
            //每次作业的分数，如果count为0，finalScore设为0

            BigDecimal finalScore = count > 0 ? totalScore.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

            //最小值
//                tempMinValue = tempMinValue.min(finalScore);
            //最大值
//                tempMaxValue = tempMaxValue.max(finalScore);

            analysistBiaoGe.setFinalScore(finalScore);
            analysistBiaoGe.setStudentId(assignments.getStudentId());
            analysistBiaoGe.setTaskId(assignments.getTaskId());
//                analysistBiaoGe.setHighestScore(tempMaxValue);
//                analysistBiaoGe.setLowestScore(tempMinValue);

//                if (finalScore.compareTo(passScore) >= 0) {
//                    passRateCount++;
//                }
            //将最终成绩保存到数据库
            scoreAnalysisInitialRepository.save(analysistBiaoGe);
        }
    }
    public boolean startScoreAnalysisInitialAI(int courseId) {
        //找到这门课的所有作业
        List<AssignmentsTasks> tasksList = assignmentsTasksRepository.findByCourseId(courseId);

        for (AssignmentsTasks assignmentsTasks : tasksList) {
            //统计及格率
            int passRateCount = 0;

            int taskId = assignmentsTasks.getTaskId();
            //每次任务的所有作业
            List<Assignments> homeworkList = assignmentsRepository.findByTaskId(taskId);

            BigDecimal sumScores = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            BigDecimal tempMaxValue = new BigDecimal(-1).setScale(2, RoundingMode.HALF_UP);
            BigDecimal tempMinValue = new BigDecimal(101).setScale(2, RoundingMode.HALF_UP);
            BigDecimal passScore = new BigDecimal(60).setScale(2, RoundingMode.HALF_UP);

            for (Assignments assignments : homeworkList) {
                int studentId = assignments.getStudentId();
                //单次作业被其它同学批改的记录
                List<ReviewRecord> singleReviewRecord = reviewRecordRepository.findByTaskIdAndStudentId(taskId, studentId);
                BigDecimal totalScore = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

                for (ReviewRecord reviewRecord : singleReviewRecord) {
                    totalScore = totalScore.add(reviewRecord.getScore().setScale(2, RoundingMode.HALF_UP));
                }

                BigDecimal finalScore = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
                if (!singleReviewRecord.isEmpty()) {
                    finalScore = totalScore.divide(new BigDecimal(singleReviewRecord.size()), 2, RoundingMode.HALF_UP);
                }

                //最小值和最大值
                tempMinValue = tempMinValue.min(finalScore);
                tempMaxValue = tempMaxValue.max(finalScore);

                if (finalScore.compareTo(passScore) >= 0) {
                    passRateCount++;
                }

                ScoreAnalysisInitial analysistBiaoGe = new ScoreAnalysisInitial();
                analysistBiaoGe.setFinalScore(finalScore);
                analysistBiaoGe.setStudentId(assignments.getStudentId());
                analysistBiaoGe.setTaskId(assignments.getTaskId());
                analysistBiaoGe.setHighestScore(tempMaxValue);
                analysistBiaoGe.setLowerestScore(tempMinValue);

                scoreAnalysisInitialRepository.save(analysistBiaoGe);
            }

            //每次循环结束时候统计及格率
            BigDecimal passRate = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            if (!homeworkList.isEmpty()) {
                passRate = new BigDecimal(passRateCount).divide(new BigDecimal(homeworkList.size()), 2, RoundingMode.HALF_UP);
            }

            //存储到数据库中，或者进行其他需要的操作
        }

        //每个学生每次作业的分数-平均分
        return true;
    }

    public boolean saveTeacherDetails(TeacherDetails details) {
        return  teacherDetailsRepository.save(details) != null;
    }


    public List<CourseListAndTaskId> getCourseListAndTaskId(int teacherId) {
        List<CourseListAndTaskId> list=new LinkedList<>();
        List<TeacherCourse> courseList = teacherCoursesRepository.findByTeacherId(teacherId);

        for (TeacherCourse course : courseList) {
            CourseListAndTaskId dto = new CourseListAndTaskId();
            dto.setTeacherCourse(course);

            List<AssignmentsTasks> tasksList = assignmentsTasksRepository.findByCourseId(course.getCourseId());
            List<Integer> taskIdList =new ArrayList<>();
            for (AssignmentsTasks assignmentsTasks : tasksList) {
                taskIdList.add(assignmentsTasks.getTaskId());
            }
            dto.setListId(taskIdList);
            list.add(dto);
        }
            return  list;
    }
    @Autowired
    OverCourseRepository overCourseRepository;
    public OverCourse endUpCourse(int courseId) {
        TeacherCourse course = teacherCoursesRepository.findByCourseId(courseId);
        if(course!=null) {
            OverCourse overCourse = new OverCourse();
            overCourse.setCourseId(course.getCourseId());
            overCourse.setCourseName(course.getCourseName());
            overCourse.setClassName(course.getClassName());
            overCourse.setEntryCode(course.getEntryNumber());
            overCourse.setTeacherId(course.getTeacherId());
            teacherCoursesRepository.delete(course);
            return overCourseRepository.save(overCourse);
        }else {
            return null;
        }


    }

    public TeacherCourse recoveryCourse(int courseId) {
        Optional<OverCourse> overCourseOptional = overCourseRepository.findById(courseId);
        if(overCourseOptional.isPresent()) {
            OverCourse overCourse = overCourseOptional.get();
            TeacherCourse teacherCourse = new TeacherCourse();
            teacherCourse.setCourseId(overCourse.getCourseId());
            teacherCourse.setCourseName(overCourse.getCourseName());
            teacherCourse.setClassName(overCourse.getClassName());
            teacherCourse.setEntryNumber(overCourse.getEntryCode());
            teacherCourse.setTeacherId(overCourse.getTeacherId());

            teacherCoursesRepository.save(teacherCourse);

            overCourseRepository.delete(overCourse);
            return teacherCourse;
        }else {
            return null;
        }


    }

    public List<ScoreAnalysisInitial> getScoreAnalysisInitialDetail(int courseId) {
        return scoreAnalysisInitialRepository.findAll();
    }
}



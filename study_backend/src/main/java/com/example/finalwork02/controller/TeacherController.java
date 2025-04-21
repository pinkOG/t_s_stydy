package com.example.finalwork02.controller;



import com.example.finalwork02.DTO.AssignmentsTasksAndUri;
import com.example.finalwork02.Dao.SignInRecordRepository;
import com.example.finalwork02.Dao.SignInTaskRepository;

import com.example.finalwork02.Service.FileService;
import com.example.finalwork02.Service.StudentService;
import com.example.finalwork02.Service.TeacherService;
import com.example.finalwork02.Service.UserService;
import com.example.finalwork02.pojo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
        * 教师控制器类
        */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private SignInRecordRepository signInRecordRepository;
    @Autowired
    private SignInTaskRepository signInTaskRepository;
    @Autowired
    private FileService fileService;


    /**
     * 创建签到任务
     *
     * @param teacherId 教师ID
     * @param startTime 签到开始时间，格式为"yyyy-MM-ddTHH:mm:ss"
     * @param endTime   签到结束时间，格式为"yyyy-MM-ddTHH:mm:ss"
     * @return 创建的签到任务
     */
    @CrossOrigin(origins = " http://localhost:5173/")
    @PostMapping("/createSignInTask")
//    public int createSignInTask(@RequestParam int i) {
    public int createSignInTask(@RequestBody SignInTaskConverter sign) {

        LocalDateTime start = LocalDateTime.parse(sign.getStartTime().substring(0, 19));
        LocalDateTime end = LocalDateTime.parse(sign.getEndTime().substring(0, 19));
        System.out.println("--------------------------------------------------------");
        while (true) {
            Random random = new Random();
            int min = 100000; // 最小值为100000
            int max = 999999; // 最大值为999999
            int signNumber = random.nextInt(max - min + 1) + min;

            System.out.println("生成的随机六位数为: " + signNumber);
            List<SignInTask> isNullList = signInTaskRepository.findBySignCode(signNumber);
            if (isNullList.size() == 0) {
                teacherService.createSignInTask(sign.getTeacherId(), start, end, signNumber);
                return signNumber;
            }

        }
//        return 0;

    }

    /**
     * 查看签到结果
     *
     * @param taskId 签到任务ID
     * @return 签到记录列表
     */
    @GetMapping("/viewSignInResults")
    public List<SignInRecordWithUsername> viewSignInResults(@RequestParam int taskId) {
        System.out.println();
        return signInRecordRepository.findSignInRecordsWithUsernamesByTaskId(taskId);


    }


    /**
     * 教师手动签到
     *
     * @param taskId    签到任务ID
     * @param studentId 学生ID
     * @param status    签到状态，值为"ON_TIME"、"LATE"或"ABSENT"
     * @return 创建的签到记录sign_in_tasks
     */
    @PostMapping("/manualSignIn")
    public SignInRecord manualSignIn(@RequestParam int taskId, @RequestParam int studentId, @RequestParam String status) {
        SignInRecord.Status signInStatus = SignInRecord.Status.valueOf(status);
        return teacherService.manualSignIn(taskId, studentId, signInStatus);
    }

    /**
     * 添加课程并添加图片
     * @param teacherId
     * @param courseName
     * @param className
     * @return 返回课程实体
     */
    @PostMapping("/addCourses")
    public TeacherCourse addCourses(@RequestParam MultipartFile file,@RequestParam int teacherId,@RequestParam String courseName,@RequestParam String className) {

        TeacherCourse course = teacherService.addCourse(teacherId, courseName, className);
        fileService.storeImage(file,course.getCourseId());
        return  course;
    }


    /**
     * 查询教师所教的和图片
     * @param teacherId
     * @return
     * @throws IOException
     */
    @PostMapping("/getCourseAndImage")
    public ResponseEntity<List<TeacherCourseAndCountDTO>> getCourseAndImage(int teacherId) throws IOException {

        List<TeacherCourseAndCountDTO> courseAndImage = teacherService.getCourseAndImage(teacherId);
        return ResponseEntity.ok(courseAndImage);

    }

    /**
     * 查询详细教师所教课程
     * @param teacherId
     * @return教师所教课程列表
     */
    @PostMapping("/getCourseDetailList")
    public List<TeacherCourse> getCourseDetailList(@RequestParam int teacherId){
        return  teacherService.getCourseList(teacherId);
    }

    @Autowired
    StudentService studentService;
    @PostMapping("/getHomeworkList")
    public List<AssignmentsTasksAndUri> getHomeworkList(@RequestParam int courseId){

        return studentService.getHomeworkList(courseId);
    }
    @PostMapping("/getCourseListAndTaskId")
    public List<CourseListAndTaskId> getCourseListAndTaskId(int teacherId){
        return  teacherService.getCourseListAndTaskId(teacherId);
    }

    /**
     * 查询课程详情
     * @param courseId
     * @return 课程详情
     */
    @PostMapping("/getCourseDetail")
    public  TeacherCourse getCourseDetail(@RequestParam int courseId){
        return  teacherService.getCourseDetail(courseId);
    }

    /**
     * 发布作业和上传作业
     * @param file
     * @param teacherId
     * @param courseId
     * @param description
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/publishHomeworkAndSave")
    public ResponseEntity<Resource> publishHomeworkAndSave(@RequestParam("file1") MultipartFile file,
                                           @RequestParam("teacherId") int teacherId,
                                           @RequestParam("courseId") Integer courseId,
                                           @RequestParam("description") String description,
                                           @RequestParam("startTime") String startTime,
                                           @RequestParam("endTime") String endTime) {
        AssignmentsTasks assignmentsTasks = new AssignmentsTasks();
        assignmentsTasks.setTeacherId(teacherId);
        assignmentsTasks.setCourseId(courseId);
        assignmentsTasks.setDescription(description);
        assignmentsTasks.setStartTime(LocalDateTime.parse(startTime));
        assignmentsTasks.setEndTime(LocalDateTime.parse(endTime));
        Resource resource = teacherService.publishHomeworkAndSave(file, assignmentsTasks);


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
    /**
     * 发布作业
     * @param courseId
     * @param startTime
     * @param endTime
     * @return
     */

    @PostMapping("/publishHomework")
    public  AssignmentsTasks publishHomework(@RequestParam int courseId,@RequestParam String startTime,@RequestParam String endTime,@RequestParam String description){
        LocalDateTime parseStartTime = LocalDateTime.parse(startTime);
        LocalDateTime parseEndTime = LocalDateTime.parse(endTime);
        return  teacherService.publishHomework(courseId,parseStartTime,parseEndTime,description);
    }

    /**
     * 设置互改任务
     * @param taskId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/setReviewAssignment")
    public boolean setReviewAssignment(@RequestParam int taskId){

        LocalDateTime startTime1 = LocalDateTime.now();
        LocalDateTime endTime1 = startTime1.plusDays(5);

        return teacherService.setReviewAssignment(taskId,startTime1,endTime1);
    }

    /**
     * 开始成绩分析
     */
    @PostMapping("/startScoreAnalysisInitial")
    public boolean startScoreAnalysisInitial(@RequestBody Map<String, Object> params) {
        int courseId = (int) params.get("courseId");
        return teacherService.startScoreAnalysisInitial(courseId);
    }

    @PostMapping("/getScoreAnalysisInitialDetail")
    public List<ScoreAnalysisInitial> getScoreAnalysisInitialDetail(@RequestBody Map<String, Object> params) {
        int courseId = (int) params.get("courseId");
        return teacherService.getScoreAnalysisInitialDetail(courseId);
    }

    /**
     * 获取课程列表
     * @param courseId
     * @return
     */
    @PostMapping("/getMaterialsList")
    public List<LearningMaterialAndUri> getMaterialsList(@RequestParam int courseId){

        return   fileService.getMaterialsList(courseId);
    }
    @PostMapping("/endUpCourse")
    public  OverCourse endUpCourse(int courseId){

        return teacherService.endUpCourse(courseId);
    }
    @PostMapping("/recoveryCourse")
    public TeacherCourse recoveryCourse(int courseId){
        return  teacherService.recoveryCourse(courseId);
    }
    //根据课程进行成绩分析


}
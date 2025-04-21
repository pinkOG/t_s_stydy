package com.example.finalwork02.controller;




import com.example.finalwork02.DTO.AssignmentsAndUri;
import com.example.finalwork02.DTO.AssignmentsTasksAndUri;

import com.example.finalwork02.DTO.ReviewRecordAndUri;
import com.example.finalwork02.Service.FileService;
import com.example.finalwork02.Service.StudentService;
import com.example.finalwork02.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生控制器类
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private FileService fileService;


    //获取学生详细信息 废弃
    @GetMapping("/getStudentDetails")
    public StudentDetails getStudentDetails(@RequestParam int userId){

        return studentService.findByUserId(userId);
    }


    //保存学生信息
    @PostMapping("/saveStudentDetails")
    public StudentDetails saveStudentDetails(@RequestBody StudentDetails studentDetails){
        return  studentService.saveStudentDetails(studentDetails);
    }

    /**
     * 学生签到  一定会过：893206
     *
     * @return 如果签到记录存在则返回签到记录，不存在则返回空
     */

    @PostMapping("/signIn")
    public SignInRecord signIn(@RequestBody Map<String,Object> stringObjectMap) {
        System.out.println(stringObjectMap);
        int signNumber = (int)stringObjectMap.get("signNumber");
        int studentId = (int)stringObjectMap.get("studentId");
        SignInRecord signInRecord = studentService.signIn(signNumber,studentId);
        if(signInRecord !=null){
           return signInRecord;
       }
       return null;
    }

    /**
     * 查看签到状态
     * @param studentId 学生ID
     * @return 签到记录列表
     */
    @GetMapping("/viewSignInStatus")
    public List<SignInRecordWithUsername> viewSignInStatus(@RequestParam int studentId) {

        return  studentService.viewSignInStatus(studentId);
    }


    /**
     * 加课
     * @param studentId
     * @param entryCode
     * @return 返回课程详情以及选课人数
     */
    @PostMapping  ("/joinCourse")
    public TeacherCourseAndCount joinCourse(@RequestParam int studentId, @RequestParam String entryCode){
            return studentService.joinCourse(studentId,entryCode);
    }

    /**
     * 返回已加课程与图片
     * @param studentId
     * @return 课程与人数列表
     */
    @PostMapping ("/getCourseListAndImage")
    public ResponseEntity<List<TeacherCourseAndCountDTO>> getCourseListAndImage(@RequestParam int studentId) throws IOException {

        List<TeacherCourseAndCountDTO> courseListAndImage = studentService.getCourseListAndImage(studentId);

        return ResponseEntity.ok(courseListAndImage);
    }

    /**
     * 查看需要交的作业以及
     *
     * @param courseId
     * @return task_id
     */
    @PostMapping("/getHomeworkList")
    public List<AssignmentsTasksAndUri> getHomeworkList(@RequestParam int courseId){

        return studentService.getHomeworkList(courseId);
    }

    /**
     * 查看所有要交的作业
     * @param studentId
     * @return
     */
    @PostMapping("/getAllHomeworkList")
    public List<List<AssignmentsTasksAndUri>> getAllHomeworkList(@RequestParam int studentId){
        return studentService.getAllHomeworkList(studentId);
    }

    /**
     * 提交作业
     * @param file
     * @param studentId
     * @param taskId
     * @return 作业附件
     */
    @PostMapping("/submitHomework")
    public ResponseEntity<AssignmentsAndUri> submitHomework(@RequestParam MultipartFile file, @RequestParam int studentId, @RequestParam int taskId){
        Assignments assignments = new Assignments();
        assignments.setStudentId(studentId);
        assignments.setTaskId(taskId);


        AssignmentsAndUri assignmentsAndUri = fileService.saveSubHomework(file, assignments);

        return ResponseEntity.ok(assignmentsAndUri);
    }

    /**
     * 互改
     *
     * @param taskId
     * @param studentId
     * @return 需要被批改的下载链接以及批改记录对象
     * @throws MalformedURLException
     */
    @PostMapping("/mutualReview")
    public List<ReviewRecordAndUri> mutualReview(@RequestParam int taskId, @RequestParam int studentId) throws MalformedURLException {

            List<ReviewRecordAndUri> reviewToList = studentService.getReviewToList(taskId, studentId);

            return reviewToList;
    }

    /**
     *
     * @param reviewRecordList
     * @return
     */
    @PostMapping("/scoreToOthers")
    public List<ReviewRecord> scoreToOthers(@RequestBody List<ReviewRecord> reviewRecordList){

        List<ReviewRecord> myLists=new ArrayList<>();

        for (ReviewRecord recordRcord : reviewRecordList) {
            studentService.scoreToOthers(recordRcord);
            myLists.add(recordRcord);
        }
        return myLists;
    }
    @PostMapping("/getHomeworkScore")
    public List<ScoreAnalysisInitial> getHomeworkScore(@RequestBody Map<String, Object> params) {
        int courseId = (int) params.get("courseId");
        int studentId = (int) params.get("studentId");
        return studentService.getHomeworkScore(courseId,studentId);
    }

    @PostMapping("/getMaterialsList")
    public List<LearningMaterialAndUri> getMaterialsList(@RequestParam int courseId){
        System.out.println(courseId);

          return   fileService.getMaterialsList(courseId);
    }




}
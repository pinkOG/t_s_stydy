package com.example.finalwork02.controller;




import com.example.finalwork02.Dao.LearningMaterialRepository;
import com.example.finalwork02.Service.FileService;
import com.example.finalwork02.pojo.LearningMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private LearningMaterialRepository repository;

    /**
     *
     * @param files 文件
     * @param courseId 课程id
     * @param title  题目
     * @param description 描述
//     * @param fileType 类型：VIEDO / DOCUMENT/ASSIGNMENT

     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<List<LearningMaterial>> uploadFiles(@RequestParam("files") MultipartFile[] files,
                                                              @RequestParam("courseId") int courseId,
                                                              @RequestParam("title") String title,
                                                              @RequestParam("description") String description
                                                             ) {
        List<LearningMaterial> materials = fileService.storeFiles(files, courseId, title, description);

        return ResponseEntity.ok(materials);
    }


    @GetMapping("/list")
    public ResponseEntity<List<LearningMaterial>> listFiles() {
        List<LearningMaterial> materials = repository.findAll();

        return ResponseEntity.ok(materials);
    }

    @GetMapping("/assignmentList")
    public List<LearningMaterial> getAssignmentList(@RequestParam  int courseId,@RequestParam String filetype) {

        return fileService.findByCourseIdAndFileType(courseId,LearningMaterial.FileTpye.ASSIGNMENT);
    }

    //以附件的方式返回
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws UnsupportedEncodingException {
        Resource resource = fileService.loadFileAsResource(filename);

        // 对文件名进行编码，确保兼容性
        String encodedFileName = URLEncoder.encode(resource.getFilename(), "UTF-8").replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                .body(resource);
    }


}

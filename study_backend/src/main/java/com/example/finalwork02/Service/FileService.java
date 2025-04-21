package com.example.finalwork02.Service;

import com.example.finalwork02.DTO.AssignmentsAndUri;
import com.example.finalwork02.Dao.AssignmentsRepository;
import com.example.finalwork02.Dao.AssignmentsTasksRepository;
import com.example.finalwork02.Dao.LearningMaterialRepository;
import com.example.finalwork02.controller.LearningMaterialAndUri;
import com.example.finalwork02.pojo.Assignments;
import com.example.finalwork02.pojo.AssignmentsTasks;
import com.example.finalwork02.pojo.LearningMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.finalwork02.utils.FileTypeJudgement.VIDEO_EXTENSIONS;

/**
 * 处理文件存储操作的服务类。
 */
@Service
public class FileService {

    // 上传文件的存储路径
    private final Path fileStorageLocation;

    @Autowired
    private LearningMaterialRepository repository;
    @Autowired
    private AssignmentsTasksRepository assignmentsTasksRepository;


    /**
     * 构造函数，用于初始化文件存储位置。
     *
     * @param uploadDir 从应用配置文件中获取的文件存储目录。
     */
    @Autowired
    public FileService(@Value("${file.upload-dir}") String uploadDir) {
        //上传文件夹的绝对路径
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            // 创建多级目录，如果不存在的话
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("无法创建用于存储上传文件的目录。", ex);
        }
    }

    /**
     * 存储多个文件并将其元数据保存到数据库中。
     *
     * @param files       要存储的文件数组。
     * @param courseId    文件所属的课程ID。
     * @param title       学习资料的标题。
     * @param description 学习资料的描述。
//     * @param fileType    上传文件的类型。
     * @return 表示存储文件的LearningMaterial对象的列表。
     */
    public List<LearningMaterial> storeFiles(MultipartFile[] files, int courseId, String title, String description) {
        List<LearningMaterial> learningMaterials = new ArrayList<>();

        for (MultipartFile file : files) {
            LearningMaterial learningMaterial = storeFile(file, courseId, title, description);

            learningMaterials.add(learningMaterial);
        }

        return learningMaterials;
    }

    /**
     * 存储单个文件并将其元数据保存到数据库中。
     *
     * @param file        要存储的文件。
     * @param courseId    文件所属的课程ID。
     * @param title       学习资料的标题。
     * @param description 学习资料的描述。
//     * @param fileType    上传文件的类型。
     * @return 表示存储文件的LearningMaterial对象。
     */
    public LearningMaterial storeFile(MultipartFile file, int courseId, String title, String description) {
        // 规范化文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        String[] split = fileName.split("\\.");

        String fileType="DOCUMENT";
        System.out.println(split);
        if (split.length > 1) {
            String fileExtension = split[split.length - 1].toLowerCase(); // 获取扩展名并转为小写

            // 检查扩展名是否在视频扩展名集合中
            if (VIDEO_EXTENSIONS.contains(fileExtension)) {
                fileType = "VIDEO";
            }
        }

        try {
            // 将文件复制到目标位置（替换具有相同名称的现有文件）
            //fileStorageLocation:给定的文件夹路径 .resolve 将文件名和路径拼在一起
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 在数据库中保存文件信息
            LearningMaterial material = new LearningMaterial();
            material.setCourseId(courseId);
            material.setTitle(title);
            material.setDescription(description);
            material.setFilePath(targetLocation.toString());
            // 将fileType转换为枚举类型
            material.setFileType(LearningMaterial.FileTpye.valueOf(fileType.toUpperCase()));
            material.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));


            return repository.save(material);

        } catch (IOException ex) {
            throw new RuntimeException("无法存储文件 " + fileName + "。请重试！", ex);
        }
    }

    /**老师
     * 保存作业并返回附件
     * @param file
     * @param assignmentsTasks
     * @return
     */
    public Resource storeHomeWorkFile(MultipartFile file,AssignmentsTasks assignmentsTasks){

        Resource resource = saveFile(file);
        String stringPath = getStringPath(file);
        assignmentsTasks.setPathFile(stringPath);
        assignmentsTasksRepository.save(assignmentsTasks);
        return resource;

    }

    /**
     * 以资源的形式加载文件。
     *
     * @param fileName 要加载的文件名。
     * @return 表示文件的资源对象。
     */
    public Resource loadFileAsResource(String fileName) throws UnsupportedEncodingException {
        String decodedFilename = java.net.URLDecoder.decode(fileName, "UTF-8");


        System.out.println(decodedFilename);
        try {
            Path filePath = this.fileStorageLocation.resolve(decodedFilename).normalize();
            //核心代码
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("文件未找到 " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("文件未找到 " + fileName, ex);
        }
    }

    public List<LearningMaterial> findByCourseIdAndFileType(int courseId, LearningMaterial.FileTpye filetype) {
        return repository.findByCourseIdAndFileType(courseId,filetype);
    }
    //存课程图片
    public LearningMaterial storeImage(MultipartFile file, int courseId) {
        // 规范化文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 将文件复制到目标位置（替换具有相同名称的现有文件）
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 在数据库中保存文件信息
            LearningMaterial material = new LearningMaterial();
            material.setCourseId(courseId);
            material.setFilePath(targetLocation.toString());
            // 将fileType转换为枚举类型
            material.setFileType(LearningMaterial.FileTpye.valueOf("RESOURCE"));
            material.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));


            return repository.save(material);

        } catch (IOException ex) {
            throw new RuntimeException("无法存储文件 " + fileName + "。请重试！", ex);
        }
    }
    //存头像
    public LearningMaterial storeHeadImage(MultipartFile file, int userId) {
        // 规范化文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 将文件复制到目标位置（替换具有相同名称的现有文件）
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 在数据库中保存文件信息
            LearningMaterial material = new LearningMaterial();
            material.setUserId(userId);
            material.setFilePath(targetLocation.toString());
            // 将fileType转换为枚举类型
            material.setFileType(LearningMaterial.FileTpye.valueOf("RESOURCE"));

            material.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));


            return repository.save(material);

        } catch (IOException ex) {
            throw new RuntimeException("无法存储文件 " + fileName + "。请重试！", ex);
        }
    }
    //返回课程图片Uri
    public String getCourseImage(int courseId) throws MalformedURLException {
        List<LearningMaterial> resourse = repository.findByCourseIdAndFileType(courseId, LearningMaterial.FileTpye.RESOURCE);
        LearningMaterial learningMaterial = resourse.get(0);
        String materialFilePath = learningMaterial.getFilePath();
        String uriLink = getfileDownloadUri(materialFilePath);
        return uriLink;
    }
    @Autowired
    AssignmentsRepository assignmentsRepository;
    public AssignmentsAndUri saveSubHomework(MultipartFile file, Assignments assignments) {
//        看下是否重复提交
        Assignments isExist= assignmentsRepository.findByTaskIdAndStudentId(assignments.getTaskId(), assignments.getStudentId());

        AssignmentsAndUri assignmentsAndUri = new AssignmentsAndUri();
        if(isExist == null) {
            saveFile(file);
            String stringPath = getStringPath(file);
            String uriLink = getfileDownloadUri(stringPath);
            assignments.setFilePath(stringPath);
            assignments.setSubmittedAt(LocalDateTime.now());
            assignmentsRepository.save(assignments);
            assignmentsAndUri.setAssignments(assignments);
            assignmentsAndUri.setUriLink(uriLink);

        }else {
            String temp = isExist.getFilePath();
            File file1 = new File(temp);
            file1.delete();
            saveFile(file);
            String stringPath = getStringPath(file);
            String uriLink = getfileDownloadUri(stringPath);
            isExist.setFilePath(stringPath);
            isExist.setSubmittedAt(LocalDateTime.now());
            assignmentsRepository.save(isExist);

            assignmentsAndUri.setAssignments(isExist);
            assignmentsAndUri.setUriLink(uriLink);
        }


        return assignmentsAndUri;
    }
    public Resource saveFile(MultipartFile file){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // 将文件复制到目标位置（替换具有相同名称的现有文件）
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            URI uri = targetLocation.toUri();
            Resource resource = new UrlResource(uri);
            return  resource;
        }catch (Exception e){
            throw new RuntimeException();
        }

    }
    public String getStringPath(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        return targetLocation.toString();
    }
    public  Resource getStringPathToResource(String pathString) throws MalformedURLException {
        Path path = Paths.get(pathString);

        return new UrlResource(path.toUri());
    }

    public String getfileDownloadUri(String filePahtString){
        // 生成文件下载链接
        Path path = Paths.get(filePahtString);
        Path fileName = path.getFileName();
        System.out.println(fileName);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/"+fileName).toUriString();
        return  fileDownloadUri;
    }
    @Autowired
    LearningMaterialRepository learningMaterialRepository;
    public List<LearningMaterialAndUri> getMaterialsList(int courseId) {
        LearningMaterial.FileTpye document = LearningMaterial.FileTpye.valueOf("DOCUMENT");
        LearningMaterial.FileTpye video = LearningMaterial.FileTpye.valueOf("VIDEO");
        List<LearningMaterial> materialList = learningMaterialRepository.findByCourseIdAndFileTypes(courseId, document, video);
        List<LearningMaterialAndUri> materialUriList=new LinkedList<>();

        for (LearningMaterial learningMaterial : materialList) {
            String filePath = learningMaterial.getFilePath();
            String uriLink = getfileDownloadUri(filePath);
            LearningMaterialAndUri learningMaterialAndUri = new LearningMaterialAndUri();
            learningMaterialAndUri.setLearningMaterial(learningMaterial);
            learningMaterialAndUri.setUriLink(uriLink);
            materialUriList.add(learningMaterialAndUri);
        }
        return  materialUriList;

    }
}

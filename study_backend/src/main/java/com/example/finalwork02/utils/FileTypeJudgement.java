package com.example.finalwork02.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.util.HashSet;
import java.util.Set;

public class FileTypeJudgement {

    // 支持的视频文件扩展名集合
    public static final Set<String> VIDEO_EXTENSIONS = new HashSet<>();

    static {
        VIDEO_EXTENSIONS.add("mp4");
        VIDEO_EXTENSIONS.add("avi");
        VIDEO_EXTENSIONS.add("mov");
        VIDEO_EXTENSIONS.add("wmv");
        VIDEO_EXTENSIONS.add("flv");
        // 可以根据需要添加更多的视频扩展名
    }

}

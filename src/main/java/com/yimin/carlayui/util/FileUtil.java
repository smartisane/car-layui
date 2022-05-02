package com.yimin.carlayui.util;

import com.yimin.carlayui.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class FileUtil {

    //文件上传路径
    // @Value("${file.root.path}")无效的注解
    private static final String fileRootPath = "D:/upload/";

    public static String upload(MultipartFile file) throws IOException {
        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        // 文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 存储路径
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String filePath = fileRootPath + format.format(new Date()) + suffix;

        // 保存文件
        file.transferTo(new File(filePath));
        return filePath.substring(2);
    }

}

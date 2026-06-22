package com.database.homework.pangdonglaishop.controller;

import com.database.homework.pangdonglaishop.VO.Result;
import com.database.homework.pangdonglaishop.util.AliOssUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@RequestMapping("/upload")
@Tag(name = "文件上传")
@Slf4j
public class UploadFileController {

    @PostMapping
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件存储到本地磁盘上
        String originalFilename=file.getOriginalFilename();
        //保证文件的名字是唯一的
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存到本地
        //file.transferTo(new File("C:\\code\\java\\study\\springboot\\pic\\"+filename));

        //保存到阿里云oss
        String url = AliOssUtil.uploadFile(filename, file.getInputStream());

        return Result.success(url);
    }
}

package com.mango.fileupload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) {
        //1.定义上传文件目录
        String format = sdf.format(new Date());
        String path = request.getServletContext().getRealPath("/img") + format;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        //2.获取文件信息
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(folder, newName));

            //3.返回上传文件的url
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img" + format + newName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping("/uploads")
    public String uploads(MultipartFile[] files, HttpServletRequest request) {
        //1.定义上传文件目录
        String format = sdf.format(new Date());
        String path = request.getServletContext().getRealPath("/img") + format;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (MultipartFile file : files) {
            //2.获取文件信息
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            try {
                file.transferTo(new File(folder, newName));

                //3.返回上传文件的url
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img" + format + newName;
                System.err.println(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}

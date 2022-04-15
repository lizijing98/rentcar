package com.rentcar.controller;

import com.rentcar.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @Author: lzj
 */
@Slf4j
@RestController
@RequestMapping("/oss/")
public class FileController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        String fileName = getFileName(file.getOriginalFilename());
        String filePath = getFilePath(fileName);
        log.info(filePath);
        try {
            file.transferTo(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("失败");
        }
        return Result.success(fileName);
    }

    /**
     * 文件hash
     *
     * @param fileName
     * @return
     */
    private String getFileName(String fileName) {
        LocalDateTime dateTime = LocalDateTime.now();
        fileName = dateTime.getYear() + "-" + dateTime.getMonthValue() + "-"
                + dateTime.getDayOfMonth() + "-" + System.currentTimeMillis() + fileName;
        return fileName;
    }

    public String getFilePath(String fileName) {
        String filePath = System.getProperty("user.dir") + "/static";
        new File(filePath).mkdirs();
        return filePath + "/" + fileName;
    }

    @GetMapping("/download")
    public void download(String file, HttpServletResponse response) throws IOException {
        File imageFile = new File(getFilePath(file));
        if (!imageFile.exists()) {
            imageFile = new File(getFilePath("background-head.png"));
        }
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(file.getBytes(), StandardCharsets.ISO_8859_1));
        try (OutputStream os = response.getOutputStream();
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(imageFile));
             BufferedOutputStream out = new BufferedOutputStream(os)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } catch (Exception e) {

        }

    }
}

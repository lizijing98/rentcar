package com.rentcar.controller;

import com.rentcar.constant.SystemConstant;
import com.rentcar.exception.BusinessException;
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
import java.nio.file.Files;
import java.time.LocalDateTime;

/**
 * @author : lzj
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
	 * @param fileName 文件名
	 * @return 文件哈希
	 */
	private String getFileName(String fileName) {
		LocalDateTime dateTime = LocalDateTime.now();
		fileName =
				dateTime.getYear()
						+ "-"
						+ dateTime.getMonthValue()
						+ "-"
						+ dateTime.getDayOfMonth()
						+ "-"
						+ System.currentTimeMillis()
						+ fileName;
		return fileName;
	}

	public String getFilePath(String fileName) {
		return System.getProperty("user.dir") + SystemConstant.FILE_PATH + fileName;
	}

	@GetMapping("/download")
	public void download(String file, HttpServletResponse response) throws IOException {
		File imageFile = new File(getFilePath(file));
		if (!imageFile.exists()) {
			imageFile = new File(getFilePath("background-head.png"));
		}
		log.info("filePath:{}", imageFile);
		response.setHeader(
				"Content-Disposition",
				"attachment;filename=" + new String(file.getBytes(), StandardCharsets.ISO_8859_1));
		try (OutputStream os = response.getOutputStream();
			 BufferedInputStream in = new BufferedInputStream(Files.newInputStream(imageFile.toPath()));
			 BufferedOutputStream out = new BufferedOutputStream(os)) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			out.flush();
		} catch (Exception e) {
			throw new BusinessException("获取静态文件异常");
		}
	}
}

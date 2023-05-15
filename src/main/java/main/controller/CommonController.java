package main.controller;

import main.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {

    /**
     * file will save in @Value path
     */
    @Value("${main.linux-path}")
    private String basePath;


    /**
     * upload file
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {  //parameter must be same as frontend request name
        log.info("File upload:{}", file.toString());

        // get the file name
        String originalFilename = file.getOriginalFilename();
        // obtain the file extension
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // Using UUID to create file name
        String fileName = UUID.randomUUID() + suffix;
        // Create a directory
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + fileName)); // Move file to specific directory
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return file name
        return R.success(fileName);
    }


    /**
     * download file
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        FileInputStream fileInputStream = null;     // Input
        ServletOutputStream outputStream = null;    // Output

        try {
            // input file, read data from file
            fileInputStream = new FileInputStream(new File(basePath + name));
            // output file, write back the data to browser
            outputStream = response.getOutputStream();
            // write data
            byte[] bytes = new byte[1024];  // 1KB
            int length = 0;                 // the length of each write
            while ((length = fileInputStream.read(bytes)) != -1) {  // read
                outputStream.write(bytes, 0, length);           // write
                outputStream.flush();                               // buffer
            }

            // set response
            response.setContentType("image/jpeg");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

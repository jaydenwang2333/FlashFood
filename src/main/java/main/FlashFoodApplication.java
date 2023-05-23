package main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class FlashFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlashFoodApplication.class, args);
        log.info("Open project...");
        log.info("Backend management system default link: http://localhost:8080/backend/index.html");
        log.info("Frontend management system default link: http://localhost:8080/front/index.html");
    }
}

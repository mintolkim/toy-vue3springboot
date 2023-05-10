package com.example.toyproject.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
@RequestMapping("/v3")
@RestController
@Controller
public class COController {
    @GetMapping("test")
    public void getTest(){
        System.out.println("test Controller");
        log.info("테스트 컨트롤러");
    }
}

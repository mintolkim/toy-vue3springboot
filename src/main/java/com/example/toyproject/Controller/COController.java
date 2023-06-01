package com.example.toyproject.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequestMapping("/")
@RestController
@Controller
public class COController implements ErrorController {
    private final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    public String redirectRoot(){
        return "index.html";
    }

    public String getErrorPath(){
        return null;
    }
}

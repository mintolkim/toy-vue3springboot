package com.example.toy.Controller;

import com.example.toy.RequestForm.UserForm;
import com.example.toy.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class COController{
    private final UserService userService;

    @PostMapping("join")
    public ResponseEntity<?> join(@RequestBody UserForm userForm){
        String message = userService.Join(userForm);
        return ResponseEntity.ok().body("success");
    }

}

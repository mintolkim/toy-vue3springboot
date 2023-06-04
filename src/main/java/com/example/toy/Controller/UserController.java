package com.example.toy.Controller;

import com.example.toy.RequestForm.UserForm;
import com.example.toy.Service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("join")
    public ResponseEntity<String> join(@RequestBody UserForm userForm){
        String result = userService.Join(userForm);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserForm userForm) {
        String result = userService.login(userForm);
        return ResponseEntity.ok().body(result);
    }

}

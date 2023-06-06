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

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("join")
    public ResponseEntity<Map<String, String>> join(@RequestBody UserForm userForm){
        Map<String, String> result = userService.Join(userForm);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserForm userForm) {
        String result = userService.login(userForm);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("idUnique")
    public ResponseEntity<String> idUnique(@RequestBody UserForm userForm) {
        Boolean idUnique = userService.idUnique(userForm);
        String result = idUnique ? "사용 가능한 아이디입니다." : "이미 사용중인 아이디입니다.";
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("deleteUser")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestBody UserForm userForm) {
        Map<String,String> result = userService.deleteUser(userForm);
        return ResponseEntity.ok().body(result);
    }
}

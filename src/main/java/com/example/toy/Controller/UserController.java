package com.example.toy.Controller;

import com.example.toy.RequestForm.UserForm;
import com.example.toy.Service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8083")
public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("join")
    public ResponseEntity<Map<String, Object>> join(@RequestBody UserForm userForm){
        Map<String, Object> result = userService.Join(userForm);
        return ResponseEntity.ok().body(result);
    }

    // 로그인
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserForm userForm) {
        Map<String, Object> result = userService.login(userForm);
        return ResponseEntity.ok().body(result);
    }

    // 아이디 중복 체크
    @PostMapping("idUnique")
    public ResponseEntity<String> idUnique(@RequestBody UserForm userForm) {
        String result = userService.idUnique(userForm);
        return ResponseEntity.ok().body(result);
    }

    // 회원 탈퇴
    @PostMapping("deleteUser")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody UserForm userForm) {
        Map<String, Object> result = userService.deleteUser(userForm);
        return ResponseEntity.ok().body(result);
    }

    // 유저 프로필 업로드
    @PostMapping("/{id}/image")
    public ResponseEntity<?> uploadUserImage(@PathVariable Long id, @RequestParam("profileImg") MultipartFile profileImg) throws IOException {
        Map<String, Object> result = userService.uploadProfileImg(id, profileImg);
        return ResponseEntity.ok().body(result);
    }
}

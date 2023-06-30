package com.example.toy.Controller;

import com.example.toy.RequestForm.MenuForm;
import com.example.toy.RequestForm.UserForm;
import com.example.toy.Service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8083")
public class BlogController {
    private final BlogService blogService;

    // 블로그 정보 가져오기
    @PostMapping("blogInfo/{id}")
    public ResponseEntity<Map<String,Object>> blogInfo(@PathVariable Long id){
        Map<String,Object> result = blogService.blogInfo(id);
        return ResponseEntity.ok().body(result);
    }

    // 메뉴 추가
    @PostMapping("createMenu")
    public ResponseEntity<Map<String,Object>> createMenu(@RequestBody MenuForm menuForm){
        Map<String,Object> result = blogService.createMenu(menuForm);
        return ResponseEntity.ok().body(result);
    }

    // 메뉴 삭제
    @PostMapping("deleteMenu")
    public ResponseEntity<Map<String, Object>> deleteMenu(@RequestBody MenuForm menuForm){
        Map<String, Object> result = blogService.deleteMenu(menuForm);
        return ResponseEntity.ok().body(result);
    }
    
    // 메뉴 수정
    @PostMapping("updateMenu")
    public ResponseEntity<Map<String, Object>> updateMenu(@RequestBody MenuForm menuForm){
        Map<String, Object> result = blogService.updateMenu(menuForm);
        return ResponseEntity.ok().body(result);
    }

    // 메뉴 조회
    @PostMapping("selectMenu")
    public ResponseEntity<Map<String, Object>> selectMenu(@RequestBody UserForm userForm){
        Map<String, Object> result = blogService.selectMenu(userForm);
        return ResponseEntity.ok().body(result);
    }

    // 메뉴 별 글 목록 보기
    @PostMapping("postMenu/{pageNo}")
    public ResponseEntity<Map<String, Object>> postMenu(@RequestBody MenuForm menuForm,@PathVariable int pageNo) {
        Map<String, Object> result = blogService.postMenu(menuForm, pageNo);
        return ResponseEntity.ok().body(result);
    }
}

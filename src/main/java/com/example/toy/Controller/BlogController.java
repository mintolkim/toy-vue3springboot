package com.example.toy.Controller;

import com.example.toy.RequestForm.MenuForm;
import com.example.toy.RequestForm.UserForm;
import com.example.toy.Service.blog.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    // 메뉴 추가
    @PostMapping("createMenu")
    public ResponseEntity<Map<String,Object>> createMenu(@RequestBody  MenuForm menuForm, UserForm userForm){
        Map<String,Object> result = blogService.createMenu(menuForm, userForm);
        return ResponseEntity.ok().body(result);
    }

    // 메뉴 삭제
    @PostMapping("deleteMenu")
    public ResponseEntity<Map<String, Object>> deleteMenu(@RequestBody MenuForm menuForm, UserForm userForm){
        Map<String, Object> result = blogService.deleteMenu(menuForm, userForm);
        return ResponseEntity.ok().body(result);
    }
    
    // 메뉴 수정
    @PostMapping("updateMenu")
    public ResponseEntity<Map<String, Object>> updateMenu(@RequestBody MenuForm menuForm){
        Map<String, Object> result = blogService.updateMenu(menuForm);
        return ResponseEntity.ok().body(result);
    }

    // TODO : 메뉴 조회

}

package com.example.toy.Controller;

import com.example.toy.RequestForm.PostForm;
import com.example.toy.RequestForm.UserForm;
import com.example.toy.Service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8083")
public class PostController {

    private final PostService postService;

    // 글 등록
    @PostMapping("write")
    public ResponseEntity<Map<String, Object>> writePost(@RequestBody PostForm postForm){
        Map<String, Object> result = postService.writePost(postForm);
        return ResponseEntity.ok().body(result);
    }

    // 글 수정
    @PostMapping("update")
    public ResponseEntity<Map<String, Object>> updatePost(@RequestBody PostForm postForm){
        Map<String, Object> result = postService.updatePost(postForm);
        return ResponseEntity.ok().body(result);
    }

    // 글 삭제
    @PostMapping("delete")
    public ResponseEntity<Map<String, Object>> deletePost(@RequestBody PostForm postForm){
        Map<String, Object> result = postService.deletePost(postForm);
        return ResponseEntity.ok().body(result);
    }

    // 글 조회
    @PostMapping("postList/{pageNo}")
    public ResponseEntity<Map<String, Object>> getPostList(@RequestBody PostForm postForm,@PathVariable int pageNo){
        Map<String, Object> result = postService.getPostList(postForm, pageNo);
        return ResponseEntity.ok().body(result);
    }

    // TODO : 글 상세보기 restapi 사용
    @PostMapping("{postId}")
    public ResponseEntity<Map<String, Object>> viewPost(@PathVariable Long postId){
        Map<String, Object> result = postService.viewPost(postId);
        return ResponseEntity.ok().body(result);
    }
    

}

package com.example.toy.Controller;

import com.example.toy.RequestForm.CommentForm;
import com.example.toy.Service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 쓰기
    @PostMapping("write")
    public ResponseEntity<Map<String, Object>> writeComment(@RequestBody CommentForm commentForm){
        Map<String, Object> result = commentService.writeComment(commentForm);
        return ResponseEntity.ok().body(result);
    }

    // 댓글 수정
    @PostMapping("update")
    public ResponseEntity<Map<String, Object>> updateComment(@RequestBody CommentForm commentForm){
        Map<String, Object> result = commentService.updateComment(commentForm);
        return ResponseEntity.ok().body(result);
    }

    // 댓글 삭제
    @PostMapping("delete")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody CommentForm commentForm){
        Map<String, Object> result = commentService.deleteComment(commentForm);
        return ResponseEntity.ok().body(result);
    }
    
    // 댓글 보기
    @PostMapping("{postId}")
    public ResponseEntity<Map<String, Object>> viewComment(@PathVariable long postId){
        Map<String, Object> result = commentService.viewComment(postId);
        return ResponseEntity.ok().body(result);
    }

}

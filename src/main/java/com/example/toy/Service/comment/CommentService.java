package com.example.toy.Service.comment;

import com.example.toy.Repository.CommentRepository;
import com.example.toy.RequestForm.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글 쓰기
    public Map<String, Object> writeComment(CommentForm commentForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            commentRepository.writeComment(commentForm);
            result.put("status", "200");
            result.put("message", "댓글이 등록되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "댓글 등록에 실패하였습니다.");
        }
        return result;
    }

    // 댓글 수정
    public Map<String, Object> updateComment(CommentForm commentForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            commentRepository.updateComment(commentForm);
            result.put("status", "200");
            result.put("message", "댓글이 수정되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "댓글 수정에 실패하였습니다.");
        }
        return result;

    }

    // 댓글 삭제
    public Map<String, Object> deleteComment(CommentForm commentForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            commentRepository.deleteComment(commentForm);
            result.put("status", "200");
            result.put("message", "댓글이 삭제되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "댓글 삭제에 실패하였습니다.");
        }
        return result;
    }
}

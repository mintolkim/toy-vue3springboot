package com.example.toy.Service.comment;

import com.example.toy.Repository.CommentRepository;
import com.example.toy.RequestForm.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.toy.util.ServiceUtil.serviceUtilMethod;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글 쓰기
    public Map<String, Object> writeComment(CommentForm commentForm) {
        return serviceUtilMethod(
            () -> commentRepository.writeComment(commentForm),
            "댓글이 등록되었습니다.",
            "댓글 등록에 실패하였습니다."
        );
    }

    // 댓글 수정
    public Map<String, Object> updateComment(CommentForm commentForm) {
        return serviceUtilMethod(
                () -> commentRepository.updateComment(commentForm),
                "댓글이 수정되었습니다.",
                "댓글 수정에 실패하였습니다."
        );
    }

    // 댓글 삭제
    public Map<String, Object> deleteComment(long commentId) {
        return serviceUtilMethod(
            () -> commentRepository.deleteComment(commentId),
            "댓글이 삭제되었습니다.",
            "댓글 삭제에 실패하였습니다."
        );
    }

    public Map<String, Object> viewComment(long postId) {
        return serviceUtilMethod(
            () -> commentRepository.viewComment(postId),
            "댓글이 삭제되었습니다.",
            "댓글 삭제에 실패하였습니다."
        );
    }
}

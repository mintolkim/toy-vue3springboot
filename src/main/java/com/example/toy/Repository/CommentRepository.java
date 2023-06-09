package com.example.toy.Repository;

import com.example.toy.RequestForm.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    // 댓글 작성
    public void writeComment(CommentForm commentForm) {
    }

    // 댓글 수정
    public void updateComment(CommentForm commentForm) {
    }

    // 댓글 삭제
    public void deleteComment(CommentForm commentForm) {
    }
}

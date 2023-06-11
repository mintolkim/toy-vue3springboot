package com.example.toy.Repository;

import com.example.toy.Entity.Comment;
import com.example.toy.Entity.Date;
import com.example.toy.Entity.Post;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepository {
    private final EntityManager em;

    // 댓글 작성
    public String writeComment(CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());

        User user = em.find(User.class, commentForm.getUser_id());
        Post post = em.find(Post.class, commentForm.getPost_id());
        Date date = new Date();
        date.WriteDate(LocalDateTime.now());

        comment.setUser(user);
        comment.setPost(post);
        comment.setDate(date);

        em.persist(comment);

        return commentForm.getContent();
    }

    // 댓글 수정
    public String updateComment(CommentForm commentForm) {
        Comment comment = em.find(Comment.class ,commentForm.getId());
        Date date = new Date();
        date.changeUpdateDate(LocalDateTime.now());
        date.WriteDate(comment.getDate().getWriteDate());
        comment.setDate(date);
        comment.setContent(commentForm.getContent());
        em.persist(comment);
        return commentForm.getContent();
    }

    // 댓글 삭제
    public String deleteComment(CommentForm commentForm) {
        Comment comment = em.find(Comment.class, commentForm.getId());
        String jpql = "delete from Comment c where id =: id";
        em.createQuery(jpql)
                .setParameter("id", comment.getId())
                .executeUpdate();
        em.clear();
        return commentForm.getContent();
    }

    // 댓글 보기
    public String viewComment(long postId) {
        return null;
    }
}

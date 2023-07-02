package com.example.toy.Repository;

import com.example.toy.Entity.Comment;
import com.example.toy.Entity.Date;
import com.example.toy.Entity.Post;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.CommentForm;
import com.example.toy.RequestForm.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepository {
    private final EntityManager em;

    // 댓글 작성
    public String writeComment(CommentForm commentForm) {
        Comment comment = new Comment();
        comment.setContent(commentForm.getComment());

        User user = em.find(User.class, commentForm.getUserId());
        Post post = em.find(Post.class, commentForm.getPostId());
        Date date = new Date();
        date.WriteDate(LocalDateTime.now());

        comment.setUser(user);
        comment.setPost(post);
        comment.setDate(date);

        em.persist(comment);

        return commentForm.getComment();
    }

    // 댓글 수정
    public String updateComment(CommentForm commentForm) {
        Comment comment = em.find(Comment.class ,commentForm.getId());
        Date date = new Date();
        date.changeUpdateDate(LocalDateTime.now());
        date.WriteDate(comment.getDate().getWriteDate());
        comment.setDate(date);
        comment.setContent(commentForm.getComment());
        em.persist(comment);
        return commentForm.getComment();
    }

    // 댓글 삭제
    public String deleteComment(CommentForm commentForm) {
        Comment comment = em.find(Comment.class, commentForm.getId());
        String jpql = "delete from Comment c where id =: id";
        em.createQuery(jpql)
                .setParameter("id", comment.getId())
                .executeUpdate();
        em.clear();
        return commentForm.getComment();
    }

    // 댓글 보기
    public List<CommentForm> viewComment(long postId) {
        List<Comment> commentList = em.createQuery("select c from Comment c where c.post.id =: post_id", Comment.class)
                .setParameter("post_id", postId)
                .getResultList();

        // PostForm 으로 변환
        List<CommentForm> resultList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentForm commentForm = new CommentForm();
            commentForm.setId(comment.getId());
            commentForm.setPostId(comment.getPost().getId());
            commentForm.setUserId(comment.getUser().getId());
            commentForm.setNickname(comment.getUser().getNickname());
            commentForm.setWriteDate(comment.getDate().getWriteDate());
            commentForm.setUpdateDate(comment.getDate().getUpdateDate());
            commentForm.setComment(comment.getContent());

            resultList.add(commentForm);
        }
        return resultList;
    }
}

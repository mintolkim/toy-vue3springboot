package com.example.toy.Repository;

import com.example.toy.Entity.Date;
import com.example.toy.Entity.Menu;
import com.example.toy.Entity.Post;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.PostForm;
import com.example.toy.RequestForm.UserForm;
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
public class PostRepository {
    private final EntityManager em;

    // 글 등록
    public void writePost(PostForm postForm, UserForm userForm) {
        User user = findUser(userForm);

        Post post = new Post();
        post.setId(postForm.getId());
        post.setSubject(postForm.getSubject());
        post.setContent(postForm.getContent());

        Date date = new Date();
        date.WriteDate(LocalDateTime.now());

        Menu menu = em.find(Menu.class, postForm.getMenu_id());
        post.setMenu(menu);

        post.setDate(date);
        post.setUser(user);
        em.persist(post);
    }

    // 글 수정
    public void updatePost(PostForm postForm) {
        Post post = findPost(postForm);
        post.setSubject(postForm.getSubject());
        post.setContent(postForm.getContent());

        Date date = new Date();
        date.changeUpdateDate(LocalDateTime.now());
        date.WriteDate(postForm.getWriteDate());
        post.setDate(date);

        em.persist(post);

    }

    // 글 삭제
    public void deletePost(PostForm postForm) {
        Post post = findPost(postForm);
        String jpql = "delete from Post p where p.id =: id";
        em.createQuery(jpql)
                .setParameter("id", post.getId())
                .executeUpdate();
        em.clear();
    }

    // 글 조회
    public List<PostForm> getPostList(UserForm userForm, int pageNo) {
        User user = findUser(userForm);
        int pageSize = 10; // 한 페이지당 데이터 수

        String jpql = "select p from Post p where p.user.id = :userId";
        List<Post> postList = em.createQuery(jpql, Post.class)
                .setParameter("userId", user.getId())
                .setFirstResult(pageSize * pageNo)
                .setMaxResults(pageSize)
                .getResultList();

        // PostForm 으로 변환
        List<PostForm> resultList = new ArrayList<>();
        for (Post post : postList) {
            PostForm postForm = new PostForm();
            postForm.setId(post.getId());
            postForm.setSubject(post.getSubject());
            postForm.setContent(post.getContent());

            if (post.getDate() != null || post.getMenu() != null) {
                postForm.setWriteDate(post.getDate().getWriteDate());
                postForm.setUpdateDate(post.getDate().getUpdateDate());
                postForm.setMenu_id(post.getMenu().getId());
            }

            // 필요한 필드들을 매핑
            resultList.add(postForm);
        }

        em.clear();
        return resultList;
    }

    // 사용자 찾는 메서드
    private User findUser(UserForm userForm) {
        if (userForm == null || userForm.getId() == null) {
            throw new IllegalArgumentException("userForm is null");
        }
        User user = em.find(User.class, userForm.getId());
        return user;
    }

    private Post findPost(PostForm postForm) {
        if (postForm == null || postForm.getId() == null) {
            throw new IllegalArgumentException("postForm is Null");
        }

        Post post = em.find(Post.class ,postForm.getId());
        return post;
    }

}

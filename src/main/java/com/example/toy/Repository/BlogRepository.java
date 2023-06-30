package com.example.toy.Repository;

import com.example.toy.Entity.User;
import com.example.toy.RequestForm.BlogRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class BlogRepository {
    private final EntityManager em;
    public BlogRequestForm blogInfo(Long id) {
        BlogRequestForm blogRequestForm = new BlogRequestForm();
        User user = em.find(User.class, id);
        blogRequestForm.setNickname(user.getNickname());
        blogRequestForm.setImage(user.getProfileImg());
        blogRequestForm.setVisitCount(user.getVisitCount());

        return blogRequestForm;
    }
}

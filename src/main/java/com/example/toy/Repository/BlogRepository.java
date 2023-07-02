package com.example.toy.Repository;

import com.example.toy.Entity.User;
import com.example.toy.RequestForm.BlogRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BlogRepository {
    private final EntityManager em;
    public BlogRequestForm blogInfo(String id) {
        BlogRequestForm blogRequestForm = new BlogRequestForm();
        List<User> user =
                em.createQuery("select u from User u where username =: username", User.class)
                        .setParameter("username", id)
                        .getResultList();
        blogRequestForm.setNickname(user.get(0).getNickname());
        blogRequestForm.setImage(user.get(0).getProfileImg());
        blogRequestForm.setVisitCount(user.get(0).getVisitCount());

        return blogRequestForm;
    }
}

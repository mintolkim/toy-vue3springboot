package com.example.toy.Repository;

import com.example.toy.Entity.Address;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    // 회원가입
    @Transactional
    public String join(UserForm userForm) {
        try {
            if (isIdUnique(userForm)){
                User user = createUserFromForm(userForm);
                em.persist(user);
            } else {
                throw new RuntimeException("user join fail");
            }
        } catch (Exception e) {
            throw new RuntimeException("user join fail");
        }
        return userForm.getUsername();
    }

    // 로그인
    public UserForm login(UserForm userForm) {
        User user = checkPassword(userForm);
        if (user != null){
            userForm.setId(user.getId());
            userForm.setUsername(user.getUsername());
            userForm.setCity(user.getAddress().getCity());
            userForm.setStreet(user.getAddress().getStreet());
            userForm.setZipcode(user.getAddress().getZipcode());
            return userForm;
        } else {
            throw new RuntimeException("user login fail");
        }
    }

    // 회원 탈퇴
    @Transactional
    public String deleteUser(UserForm userForm) {
        try {
            User user = em.find(User.class, userForm.getId());
            if (user != null) {
                em.remove(user);
            } else {
                throw new RuntimeException("user delete fail");
            }
        } catch (Exception e){
            throw new RuntimeException("user delete fail");
        }
        return userForm.getUsername();
    }

    // 아이디 중복체크
    public boolean isIdUnique (UserForm userForm){
        String jpql = "select u from User u where username = :username";
        List<User> username = em.createQuery(jpql, User.class)
                .setParameter("username", userForm.getUsername())
                .getResultList();
        return username.isEmpty();
    }

    // 비밀번호 체크
    private User checkPassword(UserForm userForm) {
        String jpql = "select u from User u where username = :username and password = :password";
        List<User> users = em.createQuery(jpql, User.class)
                .setParameter("username", userForm.getUsername())
                .setParameter("password", userForm.getPassword())
                .getResultList();

        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }


    // Entity 세팅
    private User createUserFromForm(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setAddress(new Address(userForm.getCity(), userForm.getStreet(), userForm.getZipcode()));
        user.setType(userForm.getType());
        return user;
    }

}
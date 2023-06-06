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
    public void join(UserForm userForm) {
        try {
            User user = createUserFromForm(userForm);
            em.persist(user);
        } catch (Exception e) {
            throw new RuntimeException("user join fail");
        }
    }

    // 로그인
    public Map<String, Object> login(UserForm userForm) {
        Map<String, Object> result = new HashMap<>();
        User user = checkPassword(userForm);

        if (user != null){
            result.put("status", "200");
            result.put("message", "환영합니다. " + user.getUsername());
            return result;
        } else {
            throw new RuntimeException("login error");
        }
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(UserForm userForm) {
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
    }

    // 아이디 중복체크
    public boolean isIdUnique (UserForm userForm){
        List<User> username = em.createQuery("select u from User u where username = :username", User.class)
                .setParameter("username", userForm.getUsername())
                .getResultList();
        return username.isEmpty();
    }

    // 비밀번호 체크
    private User checkPassword(UserForm userForm) {
        List<User> users = em.createQuery("select u from User u where username = :username and password = :password")
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
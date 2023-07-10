package com.example.toy.Repository;

import com.example.toy.Entity.Address;
import com.example.toy.Entity.Menu;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.PostForm;
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
                // 기본 메뉴 설정하기
                createBaseMenu(user);
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
            userForm.setNickname(user.getNickname());
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
        user.setNickname(userForm.getNickname());
        user.setPassword(userForm.getPassword());
        user.setAddress(new Address(userForm.getCity(), userForm.getStreet(), userForm.getZipcode()));
        user.setType(userForm.getType());
        return user;
    }

    // 기본 메뉴 insert 하기
    private void createBaseMenu(User user){
        Menu menu = new Menu();
        menu.setUser(user);
        menu.setMenuName("자유 게시판");
        em.persist(menu);
        Menu menu2 = new Menu();
        menu2.setUser(user);
        menu2.setMenuName("메모장");
        em.persist(menu2);

    }


    public String uploadProfileImg(Long id, String userImg) {
        UserForm userForm = new UserForm();
        User user = em.find(User.class, id);
        user.setProfileImg(userImg);
        userForm.setProfileImg(user.getProfileImg());
        return userImg;
    }

    // 사용자 찾는 jpql
    public Long findUserJpql(String username) {
        if (username == null) {
            throw new IllegalArgumentException("userForm is null");
        }

        String jpql = "select u from User u where username =: username";
        List<User> user = em.createQuery(jpql, User.class)
                .setParameter("username", username)
                .getResultList();

        return user.get(0).getId();
    }

    // 닉네임 업데이트
    @Transactional
    public String updateNick(UserForm userForm) {
        User user = em.find(User.class, userForm.getId());
        user.setNickname(userForm.getNickname());
        em.persist(user);

        return user.getNickname();
    }
}
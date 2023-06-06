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
    public Map<String, String> join(UserForm userForm) {
        Map<String,String> result = new HashMap<>();
        try {
            User user = createUserFromForm(userForm);
            em.persist(user);
            result.put("status", "200");
            result.put("message", "회원가입이 완료되었습니다.");
            return result;
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "회원가입에 실패하였습니다.");
            return result;
        }
    }

    // 로그인
    public String login(UserForm userForm) {
        if (checkPassword(userForm)){
            return "로그인에 성공하였습니다.";
        } else {
            return "존재하지 않는 아이디거나, 비밀번호가 잘못 되었습니다.";
        }
    }

    // 회원 탈퇴
    @Transactional
    public Map<String, String> deleteUser(UserForm userForm) {
        Map<String, String> result = new HashMap<>();
        try {
            User user = em.find(User.class, userForm.getId());
            if (user != null) {
                em.remove(user);
                em.flush();
                em.clear();
            }
            result.put("status", "200");
            result.put("message", "회원탈퇴가 정상적으로 되었습니다.");
            return result;
        } catch (Exception e){
            result.put("status", "500");
            result.put("message", "회원탈퇴에 실패했습니다.");
            return result;
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
    private boolean checkPassword (UserForm userForm){
        List<User> user = em.createQuery("select u from User u where username = :username and password = :password")
                            .setParameter("username", userForm.getUsername())
                            .setParameter("password", userForm.getPassword())
                            .getResultList();
        return !user.isEmpty();
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

package com.example.toy.Repository;

import com.example.toy.Entity.Address;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    // 회원가입
    public String join(UserForm userForm) {
        if (isIdUnique(userForm)) {
            try {
                User user = createUserFromForm(userForm);
                em.persist(user);
                return "가입이 완료되었습니다.";
            } catch (Exception e) {
                return "가입에 실패하였습니다.";
            }
        } else {
            return "아이디가 이미 사용 중입니다.";
        }
    }

    public String login(UserForm userForm) {
        if (checkPassword(userForm)){
            return "로그인에 성공하였습니다.";
        } else {
            return "존재하지 않는 아이디거나, 비밀번호가 잘못 되었습니다.";
        }
    }

    // 아이디 중복체크
    private boolean isIdUnique (UserForm userForm){
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

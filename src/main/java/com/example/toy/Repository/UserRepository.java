package com.example.toy.Repository;

import com.example.toy.Entity.Address;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public String join(UserForm userForm) {
        try {
            User user = createUserFromForm(userForm);
            em.persist(user);
            return "가입이 완료되었습니다.";
        } catch (Exception e) {
            return "가입에 실패하였습니다.";
        }
    }

    private User createUserFromForm(UserForm userForm) {
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setAddress(new Address(userForm.getCity(), userForm.getStreet(), userForm.getZipcode()));
        user.setType(userForm.getType());
        return user;
    }
}

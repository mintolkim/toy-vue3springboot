package com.example.toy.Repository;

import com.example.toy.Entity.Menu;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.MenuForm;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class MenuRepository {
    private final EntityManager em;

    // 메뉴 생성
    public void createMenu(MenuForm menuForm, UserForm userForm) {
        Map<String, Object> result = new HashMap<>();

        try {
            User user = findUser(userForm);
            Menu menu = new Menu();
            menu.setMenuName(menuForm.getMenuName());
            menu.setUser(user);
            em.persist(menu);

        } catch (Exception e) {
            throw new RuntimeException("Menu create fail");
        }
    }

    // 메뉴 삭제
    public void deleteMenu(MenuForm menuForm) {
        try {
            Menu menu = em.find(Menu.class, menuForm.getId());
            if (menu != null) {
                em.createQuery("delete from Menu m where id = :id ")
                                .setParameter("id", menu.getId())
                                .executeUpdate();
                em.clear();
            } else {
                throw new RuntimeException("Menu not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Menu delete fail");
        }
    }

    // 메뉴 수정
    public void updateMenu(MenuForm menuForm) {
        Menu menu = em.find(Menu.class, menuForm.getId());
        menu.setMenuName(menuForm.getMenuName());
        em.persist(menu);
    }

    // 사용자 찾는 메서드
    public User findUser(UserForm userForm) {
        User user = em.find(User.class, userForm.getId());
        return user;
    }
}

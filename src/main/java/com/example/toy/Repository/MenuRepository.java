package com.example.toy.Repository;

import com.example.toy.Entity.Menu;
import com.example.toy.Entity.User;
import com.example.toy.RequestForm.MenuForm;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class MenuRepository {
    private final EntityManager em;

    // 메뉴 생성
    public void createMenu(MenuForm menuForm, UserForm userForm) throws Exception{
        Map<String, Object> result = new HashMap<>();
        User user = findUser(userForm);
        Menu menu = new Menu();
        menu.setMenuName(menuForm.getMenuName());
        menu.setUser(user);
        em.persist(menu);
    }

    // 메뉴 삭제
    public void deleteMenu(MenuForm menuForm) {
        Menu menu = em.find(Menu.class, menuForm.getId());
        if (menu != null) {
            String jpql = "delete from Menu m where id = :id ";
            em.createQuery(jpql)
                            .setParameter("id", menu.getId())
                            .executeUpdate();
            em.clear();
        } else {
            throw new RuntimeException("Menu not found");
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
        if (userForm == null || userForm.getId() == null) {
            throw new IllegalArgumentException("UserForm or UserForm ID must not be null");
        }
        User user = em.find(User.class, userForm.getId());
        return user;
    }


    // 메뉴 조회
    public List<MenuForm> selectMenu(UserForm userForm) {
        User user = findUser(userForm);
        List<Menu> menuList = user.getMenuList();

        // MenuForm으로 변환
        List<MenuForm> menuFormList = new ArrayList<>();
        for (Menu menu : menuList) {
            MenuForm menuForm = new MenuForm();
            menuForm.setId(menu.getId());
            menuForm.setMenuName(menu.getMenuName());
            menuFormList.add(menuForm);
        }

        return menuFormList;
    }


}

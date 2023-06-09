package com.example.toy.Service.blog;

import com.example.toy.Entity.Menu;
import com.example.toy.Entity.User;
import com.example.toy.Repository.MenuRepository;
import com.example.toy.RequestForm.MenuForm;
import com.example.toy.RequestForm.PostForm;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final MenuRepository menuRepository;
    public Map<String, Object> createMenu(MenuForm menuForm, UserForm userForm) {
        Map<String,Object> result = new HashMap<>();

        // 유저 찾기
        if (menuRepository.findUser(userForm) == null) {
            result.put("status", "500");
            result.put("message", "사용자를 찾을 수 없습니다.");
        } else {
            try {
                menuRepository.createMenu(menuForm, userForm);
                result.put("status", "200");
                result.put("message", menuForm.getMenuName());
            } catch (Exception e) {
                result.put("status", "500");
                result.put("message", "메뉴 생성에 실패했습니다.");
            }

        }

        return result;
    }

    public Map<String, Object> deleteMenu(MenuForm menuForm, UserForm userForm) {
        Map<String, Object> result = new HashMap<>();

        // 유저 찾기
        if (menuRepository.findUser(userForm) == null) {
            result.put("status", "500");
            result.put("message", "사용자를 찾을 수 없습니다.");
        } else {
            try{
                menuRepository.deleteMenu(menuForm);
                result.put("status", "200");
                result.put("message", "메뉴 삭제가 완료되었습니다.");
            } catch (Exception e){
                result.put("status", "500");
                result.put("message", "메뉴 삭제에 실패했습니다.");
            }
        }

        return result;
    }

    // 메뉴 수정
    public Map<String, Object> updateMenu(MenuForm menuForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            menuRepository.updateMenu(menuForm);
            result.put("status", "200");
            result.put("message", "메뉴 수정에 완료했습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "메뉴 수정에 실패했습니다.");
        }
        return result;
    }

    // 메뉴 조회
    public Map<String, Object> selectMenu(UserForm userForm) {
        Map<String, Object> result = new HashMap<>();
        try{
            List<MenuForm> MenuList = menuRepository.selectMenu(userForm);
            result.put("status", "200");
            result.put("result", MenuList);
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "메뉴 조회에 실패하였습니다.");
        }
        return result;
    }

    public Map<String, Object> postMenu(MenuForm menuForm, int pageNo) {
        Map<String, Object> result = new HashMap<>();
        try{
            List<PostForm> PostList = menuRepository.menuPost(menuForm, pageNo);
            result.put("status", "200");
            result.put("result", PostList);
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "메뉴 조회에 실패하였습니다.");
        }
        return result;
    }
}

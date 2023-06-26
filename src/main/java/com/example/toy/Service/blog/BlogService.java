package com.example.toy.Service.blog;

import com.example.toy.Repository.BlogRepository;
import com.example.toy.Repository.MenuRepository;
import com.example.toy.RequestForm.MenuForm;
import com.example.toy.RequestForm.PostForm;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.toy.util.ServiceUtil.serviceUtilMethod;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final MenuRepository menuRepository;
    private final BlogRepository blogRepository;

    // 블로그 정보
    public Map<String, Object> blogInfo(Long id) {
        return serviceUtilMethod(
            () -> blogRepository.blogInfo(id),
            "블로그 조회에 성공하였습니다.",
            "블로그 조회에 실패했습니다."
        );
    }

    // 메뉴 생성
    public Map<String, Object> createMenu(MenuForm menuForm) {
        return serviceUtilMethod(
            () -> menuRepository.createMenu(menuForm),
            "메뉴 생성에 성공하였습니다.",
            "메뉴 생성에 실패했습니다."
        );
    }

    // 메뉴 삭제
    public Map<String, Object> deleteMenu(MenuForm menuForm) {
        return serviceUtilMethod(
            () -> menuRepository.deleteMenu(menuForm),
            "메뉴 삭제가 완료되었습니다.",
            "메뉴 삭제에 실패했습니다."
        );
    }

    // 메뉴 수정
    public Map<String, Object> updateMenu(MenuForm menuForm) {
        return serviceUtilMethod(
            () -> menuRepository.updateMenu(menuForm),
            "메뉴 수정에 완료했습니다.",
            "메뉴 수정에 실패했습니다."
        );
    }

    // 메뉴 조회
    public Map<String, Object> selectMenu(UserForm userForm) {
        return serviceUtilMethod(
            () -> menuRepository.selectMenu(userForm),
            "메뉴 조회에 완료했습니다.",
            "메뉴 조회에 실패하였습니다."
        );
    }

    // 메뉴별 글 조회
    public Map<String, Object> postMenu(MenuForm menuForm, int pageNo) {
        return serviceUtilMethod(
            () -> menuRepository.menuPost(menuForm, pageNo),
            "메뉴 조회에 완료했습니다.",
            "메뉴 조회에 실패하였습니다."
        );
    }
}

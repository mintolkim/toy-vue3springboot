package com.example.toy.Service.user;

import com.example.toy.Entity.User;
import com.example.toy.Repository.UserRepository;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.toy.util.ServiceUtil.serviceUtilMethod;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    public Map<String, Object> Join(UserForm userForm){
        return serviceUtilMethod(
            () -> userRepository.join(userForm),
            "회원가입이 완료되었습니다.",
            "회원가입에 실패했습니다."
        );
    }

    // 로그인
    public Map<String, Object> login(UserForm userForm){
        return serviceUtilMethod(
            () -> {
                User user = userRepository.login(userForm);
                if (user == null) {
                    throw new RuntimeException("Invalid username or password");
                }
                return "환영합니다. " + user.getUsername();
            },
            "로그인에 성공하였습니다. ",
            "로그인에 실패하였습니다."
        );
    }


    // 아이디 중복체크
    public String idUnique(UserForm userForm) {
        Boolean idUnique = userRepository.isIdUnique(userForm);
        String result = idUnique ? "사용 가능한 아이디입니다." : "이미 사용중인 아이디입니다.";
        return result;
    }

    // 회원 탈퇴
    public Map<String, Object> deleteUser(UserForm userForm) {
        return serviceUtilMethod(
            () -> userRepository.deleteUser(userForm),
            "회원탈퇴가 정상적으로 되었습니다.",
            "회원탈퇴에 실패했습니다."
        );
    }
}

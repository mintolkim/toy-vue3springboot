package com.example.toy.Service.user;

import com.example.toy.Repository.UserRepository;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    public Map<String, String> Join(UserForm userForm){
        Map<String, String> result = new HashMap<>();
        if(userRepository.isIdUnique(userForm)){
            try {
                userRepository.join(userForm);
                result.put("status", "200");
                result.put("message", "회원가입이 완료되었습니다.");
            } catch (Exception e) {
                result.put("status","500");
                result.put("message","회원가입에 실패했습니다.");
            }
        } else {
            result.put("status","500");
            result.put("message","중복된 아이디 입니다.");
        }
        return result;
    }

    // 로그인
    public Map<String, Object> login(UserForm userForm){
        Map<String, Object > result = new HashMap<>();
        try{
            result = userRepository.login(userForm);
        } catch (Exception e){
            result.put("status","500");
            result.put("message","로그인에 실패하였습니다.");
        }

        return result;
    }

    // 아이디 중복체크
    public String idUnique(UserForm userForm) {
        Boolean idUnique = userRepository.isIdUnique(userForm);
        String result = idUnique ? "사용 가능한 아이디입니다." : "이미 사용중인 아이디입니다.";
        return result;
    }

    // 회원 탈퇴
    public Map<String, String> deleteUser(UserForm userForm) {
        Map<String, String > result = new HashMap<>();
        try {
            userRepository.deleteUser(userForm);
            result.put("status", "200");
            result.put("message", "회원탈퇴가 정상적으로 되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "회원탈퇴에 실패했습니다.");
        }
        return result;
    }
}

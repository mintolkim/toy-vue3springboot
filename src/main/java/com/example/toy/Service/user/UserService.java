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
            result = userRepository.join(userForm);
        } else {
            result.put("status","500");
            result.put("message","회원가입에 실패했습니다.");
        }
        return result;
    }

    // 로그인
    public String login(UserForm userForm){
        String result = userRepository.login(userForm);
        return result;
    }

    // 아이디 중복체크
    public Boolean idUnique(UserForm userForm) {
        Boolean result = userRepository.isIdUnique(userForm);
        return result;
    }

    public Map<String, String> deleteUser(UserForm userForm) {
        Map<String, String > result = userRepository.deleteUser(userForm);
        return result;
    }
}

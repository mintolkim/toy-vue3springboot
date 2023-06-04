package com.example.toy.Service.user;

import com.example.toy.Repository.UserRepository;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String Join(UserForm userForm){
        String result = userRepository.join(userForm);
        return result;
    }

    public String login(UserForm userForm){
        String result = userRepository.login(userForm);
        return result;
    }

}

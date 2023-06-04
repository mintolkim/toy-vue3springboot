package com.example.toy.Service;

import com.example.toy.Repository.UserRepository;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String Join(UserForm userForm){
        String message = userRepository.join(userForm);
        return null;
    }

}

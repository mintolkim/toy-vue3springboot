package com.example.toy.RequestForm;

import com.example.toy.Entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    private Long id;

    private String username;
    private String password;


    private Type type;

    private String city;
    private String street;
    private String zipcode;

    public UserForm() {
    }

}

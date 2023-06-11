package com.example.toy.RequestForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuForm {
    private Long id;
    private String menuName;
    private Long userId;

    public MenuForm() {
    }
}

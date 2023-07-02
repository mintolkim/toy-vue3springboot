package com.example.toy.RequestForm;

import com.example.toy.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostForm {
    private Long id;

    private String subject;

    private String content;

    private LocalDateTime updateDate;

    private LocalDateTime writeDate;

    private Long menuId;

    private Long userId;

    private String username;

    public PostForm() {
    }
}

package com.example.toy.RequestForm;

import com.example.toy.Entity.Date;
import com.example.toy.Entity.Post;
import com.example.toy.Entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentForm {

    private Long id;

    private LocalDateTime updateDate;

    private LocalDateTime writeDate;

    private Long postId;

    private Long userId;

    private String nickname;

    private String comment;
}

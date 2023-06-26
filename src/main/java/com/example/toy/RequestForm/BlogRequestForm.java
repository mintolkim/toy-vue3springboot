package com.example.toy.RequestForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestForm {
    private Long userid;
    private String username;
    private String nickname;
    private Long visitCount;
    private String image;

}

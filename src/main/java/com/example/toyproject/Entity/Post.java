package com.example.toyproject.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String subject;

    @Lob
    private String content;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    private User user;

    @Embedded
    private Date date;

}


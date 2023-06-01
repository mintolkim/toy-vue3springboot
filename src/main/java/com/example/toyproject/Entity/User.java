package com.example.toyproject.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private Type type;

    @Embedded
    private Adress adress;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();
}

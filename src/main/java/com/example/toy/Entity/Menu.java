package com.example.toy.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue
    private Long id;

    private String menuName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    List<Post> postList = new ArrayList<>();

    public void setUser(User user){
        this.user = user;
        user.getMenuList().add(this);
    }
}

package com.example.toy.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Embedded
    private Date date;
}

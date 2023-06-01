package com.example.toyproject.Entity;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Menu {
    @Id
    @GeneratedValue
    private Long id;

    private String menuName;

    @ManyToOne
    private User user;

    @Embedded
    private Date date;
}

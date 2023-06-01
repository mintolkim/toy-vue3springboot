package com.example.toyproject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class File {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private File file;
}

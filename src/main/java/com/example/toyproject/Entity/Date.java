package com.example.toyproject.Entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Date {

    private LocalDateTime updateDate;

    private LocalDateTime writeDate;
}

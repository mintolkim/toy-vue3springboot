package com.example.toy.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class Date {

    private LocalDateTime updateDate;

    private LocalDateTime writeDate;

    public Date() {
    }

    public Date(LocalDateTime updateDate, LocalDateTime writeDate) {
        this.updateDate = updateDate;
        this.writeDate = writeDate;
    }
}
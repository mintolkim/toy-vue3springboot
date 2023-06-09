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

    public void WriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }

    public void changeUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}

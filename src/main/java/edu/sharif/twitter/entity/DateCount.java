package edu.sharif.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DateCount {

    Date date;
    Long count;

    @Override
    public String toString() {
        return "Date: " + getDate() + ", Count: " + getCount();
    }
}

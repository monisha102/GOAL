package com.example.administrator.goalee;

import java.util.Date;

public class DeadlineInfo {
    public String email,exam;
    public Date date;

    DeadlineInfo(){}
    DeadlineInfo(String email, String exam, Date date){
        this.date=date;
        this.email=email;
        this.exam=exam;
    }
}

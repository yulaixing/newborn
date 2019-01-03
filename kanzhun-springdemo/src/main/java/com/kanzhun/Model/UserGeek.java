package com.kanzhun.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author:xing.yl
 * @date: 18/12/25
 */
@Component
public class UserGeek implements Serializable {


    @Value("${a}")
    private int userId;


    private String geekAddr;


    public UserGeek(){

        this.geekAddr="北京市冠捷大厦";
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGeekAddr() {
        return geekAddr;
    }

    public void setGeekAddr(String geekAddr) {
        this.geekAddr = geekAddr;
    }
}

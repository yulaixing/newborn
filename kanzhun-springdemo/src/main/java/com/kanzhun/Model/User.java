package com.kanzhun.Model;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author:xing.yl
 * @date: 18/12/25
 */

@Component
public class User implements Serializable {

    @Resource
    private UserGeek userGeek;

    private int id;

    private String name;

    private int age;


    public User(){
        this.id=1;
        this.name="xingyulai";
        this.age=30;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserGeek getUserGeek() {
        return userGeek;
    }
}

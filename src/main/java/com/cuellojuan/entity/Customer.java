package com.cuellojuan.entity;


import com.cuellojuan.dao.GenericDAO;

import java.util.List;

public class Customer {
    public int id;
    public String name;

    public int age;



    public Customer(){

    }

    public Customer(int id, String name, int age) {
        this.id = id;
        this.age = age;
        this.name = name;

    }

    public int getCustId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }




}

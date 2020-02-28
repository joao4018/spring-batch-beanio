package com.example.demo.core.model.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autobot")
public class Autobot {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String car;

    public Autobot() {
    }

    public Autobot(String name, String car) {
        this.name = name;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
    
    @Override
    public String toString() {
        return "Autobot{" +
                "name='" + name + '\'' +
                ", car='" + car + '\'' +
                '}';
    }

}

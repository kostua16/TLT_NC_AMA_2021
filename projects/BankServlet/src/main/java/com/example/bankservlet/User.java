package com.example.bankservlet;

public class User {
    private String name;
    private String pass;
    private int money;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return getName() + " " + getPass() + " " + getMoney();
    }

}
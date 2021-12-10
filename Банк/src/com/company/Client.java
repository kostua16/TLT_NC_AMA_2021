package com.company;

public class Client {
    private Integer balance;
    private String id;
    private String password;
    private String name;

    public Client(String name,Integer balance, String id, String password) {
        this.balance = balance;
        this.id = id;
        this.password = password;
        this.name = name;
    }



    Integer getBalance (){
        return balance;
    }

    void checkBalance (){
        System.out.println("На счету " + balance + "$");
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}

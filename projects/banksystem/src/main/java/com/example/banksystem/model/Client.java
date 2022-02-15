package com.example.banksystem.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    //поля клиента
    private int money ;
    private int id ;
    private int password;

}

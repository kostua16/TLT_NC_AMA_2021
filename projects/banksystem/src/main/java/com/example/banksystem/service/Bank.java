package com.example.banksystem.service;

import com.example.banksystem.model.Client;
import org.springframework.stereotype.Service;


@Service
public class Bank extends Bankomat{


    //кластьденьги
    public Integer addMoney(Client client, int money) {


        if (login(client)) {
            Client currentClient= userList.stream().filter(i -> i.getId() == client.getId() && i.getPassword() == client.getPassword()).findFirst().get();
            currentClient.setMoney(currentClient.getMoney()+money);
            return currentClient.getMoney();

        }
        else {
            return 0;
        }
    }

}

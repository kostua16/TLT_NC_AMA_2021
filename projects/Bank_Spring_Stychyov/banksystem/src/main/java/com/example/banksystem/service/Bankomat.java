package com.example.banksystem.service;


import com.example.banksystem.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Bankomat {
    protected static List<Client> userList = new ArrayList<>(

            Arrays.asList(
                    new Client(15000,123,123456)
            )
    );



    public Boolean login(Client client) {
        if ( userList.stream().filter(i -> i.getId() == client.getId() && i.getPassword() == client.getPassword()).count() > 0)
            return true;
        else
            return false;
    }


    public Integer checkMoney(Client client) {

        if (login(client))
            return userList.stream().filter(i -> i.getId() == client.getId() && i.getPassword() == client.getPassword()).findFirst().get().getMoney();
        else
            return 0;

    }
    public Integer withdrawMoney(Client client, int money) {


        if (login(client)) {
            Client currentClient= userList.stream().filter(i -> i.getId() == client.getId() && i.getPassword() == client.getPassword()).findFirst().get();
            if (currentClient.getMoney()-money>=0)
            currentClient.setMoney(currentClient.getMoney()-money);

            return currentClient.getMoney();

        }
        else {
            return 0;
        }
    }

}

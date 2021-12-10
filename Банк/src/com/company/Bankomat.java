package com.company;

public class Bankomat {
    Boolean flag;
    private Client currentClient;

    Boolean testClient (Client[] clients, String id, String password) {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getId().equals(id) && clients[i].getPassword().equals(password)){
                currentClient = clients[i];
                flag = true;
                break;
            }else flag = false;
        }return flag;
    }
    public Client getCurrentClient() {
        return currentClient;
    }

    Integer getBalance(){
     return currentClient.getBalance();
    }

    Boolean subBalance(Integer value){
        if(value < getBalance()) {
            return true;
        }
        else return false;
    }
}

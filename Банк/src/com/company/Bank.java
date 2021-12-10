package com.company;

import static com.company.Main.clients;

public class Bank {

    void initializationClients(){
        clients [0] = new Client("Василий",10000, "pupkin20", "283129");
        clients [1] = new Client("Дмитрий",1000, "petrov", "udfsdss");
        clients [2] = new Client("Виталий",300000, "Kartochka", "300.000$");
        clients [3] = new Client("Admin",10000000, "admin", "admin");
    }

    void robberyBank(){
        int min = 0;
        int max = 100;
        int random_number = min + (int) (Math.random() * max);
        if (random_number < 50){
            System.out.println("Вы подскользнулись. Охранник вызвал полицию, ваш шанс: " + random_number + "");
        }
        else System.out.println("Вы успешно ограбили банк, но полиция настигла вас(хорошей концовки не будет), шанс: "
                + random_number);
    }

    Boolean addBalance(Integer value){
        System.out.println("Купюра принята, баланс пополнен на" + value + "$");
        return true;
    }
}

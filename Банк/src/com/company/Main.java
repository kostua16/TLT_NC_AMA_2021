package com.company;

import java.util.Scanner;

public class Main {
    static Client[] clients = new Client[4];

    public static void main(String[] args) {

        Bankomat bankomat = new Bankomat();
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        bank.initializationClients();

        System.out.println("----------------------- \n" +
                "1) Зайти в банкомат \n" +
                "2) Зайти в банк \n" +
                "3) Выход");

        Integer choice = scanner.nextInt();

        switch (choice) {
            case (1):
                System.out.println("-----------------------\n" +
                        "Вы вошли в банкомат:\n" +
                        "Авторизуйтесь!\n" +
                        "1) Введите id и пароль\n" +
                        "2) Выход\n" +
                        "-----------------------");
                        Integer choiceTwo = scanner.nextInt();
                        if (choiceTwo == 1){
                            System.out.println("Введите id: ");
                            String id = scanner.next();
                            System.out.println("Введите пароль: ");
                            String pass = scanner.next();
                            Boolean login = bankomat.testClient(clients,id,pass);
                            if (login == true){
                                System.out.println("-----------------------");
                                System.out.println("Здравствуйте, " + bankomat.getCurrentClient().getName() + ", " +
                                        "авторизация прошла успешна!\n" +
                                        "1) Проверить баланс\n" +
                                        "2) Снять деньги\n" +
                                        "3) Забрать карту\n" +
                                        "-----------------------");
                                int checkThree = scanner.nextInt();
                                if (checkThree == 1){
                                    System.out.println(bankomat.getCurrentClient().getName() + ", баланс составляет: " +
                                            bankomat.getBalance() + "$");
                                }
                                else if (checkThree == 2){
                                    System.out.println("Введите сумму, которую хотите снять: ");
                                    Integer value = scanner.nextInt();
                                    boolean sub = bankomat.subBalance(value);
                                    if (sub == true){
                                        System.out.println(bankomat.getCurrentClient().getName() +
                                                ", деньги успешно обналичины!");
                                    }
                                    else System.out.println("Не хватает денег на карте!");
                                }else {
                                    System.out.println(bankomat.getCurrentClient().getName() + ", операция завершена!");
                                }

                            }else {
                                System.out.println("Пользователя не существует, обратитесь в банк!");
                                System.exit(0);
                            }
                        }else {
                            System.out.println("Операция завершена!");
                            System.exit(0);}
                break;
            case (2):
                System.out.println("-----------------------\n" +
                        "1) Воспользоваться банкоматом\n" +
                        "2) Ограбить банк");
                Integer choiceFour = scanner.nextInt();

                if(choiceFour == 1){
                    System.out.println("Введите id: ");
                    String id = scanner.next();
                    System.out.println("Введите пароль: ");
                    String pass = scanner.next();
                    Boolean login = bankomat.testClient(clients,id,pass);
                    if (login == true){
                        System.out.println("-----------------------");
                        System.out.println("Здравствуйте, " + bankomat.getCurrentClient().getName() + ", " +
                                "авторизация прошла успешна!\n" +
                                "1) Проверить баланс\n" +
                                "2) Снять деньги\n" +
                                "3) Пополнить баланс\n" +
                                "4) Забрать карту\n" +
                                "-----------------------");
                        int checkThree = scanner.nextInt();
                        if (checkThree == 1){
                            System.out.println(bankomat.getCurrentClient().getName() + ", баланс составляет: " +
                                    bankomat.getBalance() + "$");
                        }
                        else if (checkThree == 2){
                            System.out.println("Введите сумму, которую хотите снять: ");
                            Integer value = scanner.nextInt();
                            boolean sub = bankomat.subBalance(value);
                            if (sub == true){
                                System.out.println(bankomat.getCurrentClient().getName() +
                                        ", деньги успешно обналичины!");
                            }
                            else System.out.println("Не хватает денег на карте!");
                        }
                        else if (checkThree == 3){
                            System.out.println("Введите сумму пополнения: ");
                            int value = scanner.nextInt();
                            bank.addBalance(value);
                            System.out.println(bankomat.getCurrentClient().getName() + ", баланс составляет: " +
                                    bankomat.getCurrentClient().getBalance() + value + "$");
                        }
                        else {
                            System.out.println(bankomat.getCurrentClient().getName() + ", операция завершена!");
                        }

                    }else {
                        System.out.println("Пользователя не существует, обратитесь в банк!");
                        System.exit(0);
                    }

                }
                else if (choiceFour == 2){
                    bank.robberyBank();
                }
                else System.exit(0);
                break;
            case (3):
                System.exit(0);
                break;
        }
    }
}

package Bank_system;

import java.util.Scanner;

public class enterToBank {
    public static void main(String[] args) {
        Client[] clients = {new Client(788, "f342", "456"),new Client(100, "lk34","123"), new Client(1000, "fr78", "789")};
        Scanner sc = new Scanner(System.in);
        Boolean checker = true;
        while(checker){
            System.out.println("Добро пожаловать в банк!");
            System.out.println("Вы хотите: " +
                    "\n 1)Зайти в банк" +
                    "\n 2)Зайти в банкомат" +
                    "\n 3)Выйти\n");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case  (1):
                    System.out.println("Вы решили положить деньги на счёт!\n");
                    System.out.println("Введите логин: ");
                    String login = sc.nextLine();
                    System.out.println("\nВведите пароль: ");
                    String password = sc.nextLine();
                    Client client = Bankomat.testClient(clients, login, password);
                    if(client!=null){
                        System.out.println("\nВы авторизованы!\n"+
                                "Введите сумму: ");
                        client.addBalance(sc.nextInt());
                    }else{
                        System.out.println("Неверный логин или пароль");
                    }
                    break;
                case  (2):
                    System.out.println("Введите логин: ");
                    login = sc.nextLine();
                    System.out.println("\nВведите пароль: ");
                    password = sc.nextLine();
                    client = Bankomat.testClient(clients, login, password);
                    if(client!=null){
                        System.out.println("Вы хотите:\n" +
                                "1)снять деньги \n" +
                                "2)проверить баланс\n");
                        choice = sc.nextInt();
                        switch (choice){
                            case (1):
                                Bankomat newOperation = new Bankomat(client);
                                System.out.println("Сколько вы хотите снять?\n");
                                newOperation.subBalance(sc.nextInt());
                                break;
                            case (2):
                                newOperation = new Bankomat(client);
                                newOperation.getBalance();
                                break;
                        }
                    }else{
                        System.out.println("Неверный логин или пароль");
                    }
                    break;
                case  (3):
                    System.out.println("До свидания!");
                    checker = false;
                    break;
            }
        }
    }
}

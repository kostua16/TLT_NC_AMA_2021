package Bank_system;

public class Client {
    Integer balance;
    String id;
    String password;
    public Client(Integer balance, String id, String password){
        this.balance = balance;
        this.id = id;
        this.password = password;
    }
    Integer getBalance(){
        return balance;
    }
    Boolean addBalance(Integer value){
        balance+=value;
        System.out.println("Вы положили "+ value+".\n" +
                "Теперь на счету "+balance);
        return true;
    }
    Boolean subBalance(Integer value){
        if (value<=balance){
            balance-=value;
            return true;
        }else{
            return false;
        }
    }
}

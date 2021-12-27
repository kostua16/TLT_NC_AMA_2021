package bank_system;

public class Client {
    /* default */ Integer balance;
    /* default */ String clientId;
    /* default */ String password;

    public Client(Integer balance, String clientId, String password) {
        this.balance = balance;
        this.clientId = clientId;
        this.password = password;
    }

    /* default */ Integer getBalance() {
        return balance;
    }

    /* default */ Boolean addBalance(Integer value) {
        balance += value;
        return true;
    }

    /* default */ Boolean subBalance(Integer value) {
        Boolean flag = false;
        if (value <= balance) {
            balance -= value;
            flag = true;
        }
        return flag;
    }
}

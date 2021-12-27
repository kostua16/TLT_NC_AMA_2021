package bank_system;

public class BankAddOperation extends Bankomat {
    public BankAddOperation(Client curClient) {
        super(curClient);
    }

    public String addBalance(Integer value) {
        currentClient.addBalance(value);
        return
                "<h1>Транзакция прошла успешно</h1>" + "<br>" + "<h2>Вы положили " + value + "."
                        + "Теперь на счету " + currentClient.balance + "</h2>";

    }
}

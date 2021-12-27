package bank_system;


import java.util.List;

public class Bankomat {
    /* default */ Client currentClient;

    public Bankomat(Client curClient) {
        currentClient = curClient;
    }

    public static Client testClient(List<Client> clients, String clientId, String password) {
        Client tempClient = null;
        for (Client client : clients) {
            if (client.clientId.equals(clientId) && client.password.equals(password)) {
                tempClient = client;
            }
        }
        return tempClient;
    }

    public String subBalance(int value) {
        String answer = null;
        if (currentClient.subBalance(value)) {
             answer = "<h1>Транзакция прошла успешно</h1>" +
                     "<br>" +
                     "<h2>Вы сняли " + value + " У вас осталось " + currentClient.balance + "</h2>";
        }
        return answer;
    }

    public String getBalanceBank() {
        return "У вас на счету " + currentClient.getBalance();
    }
}
/*public String subBalance(int value) {
        if (currentClient.subBalance(value)) {
            return
                    "<h1>Транзакция прошла успешно</h1>" +
                            "<br>" +
                            "<h2>Вы сняли " + value + " У вас осталось " + currentClient.balance + "</h2>";
        } else {
            return
                    "<h1>Транзакция не прошла</h1>" +
                            "<br>" +
                            "<h2>На вашем счету недостаточно средств:" + currentClient.balance + "</h2>";
        }
    }*/
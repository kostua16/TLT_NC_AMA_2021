package Bank_system;

public class Bankomat {
    Client currentClient;
    public Bankomat(Client curClient){
        currentClient = curClient;
    }
    public static Client testClient(Client[] clients, String id, String password){
        for(Client client : clients) {
            if((client.id.equals(id))&&(client.password.equals(password))){
            return client;
        }
        }
        return  null;
    }
    Boolean subBalance(int value){
        if (currentClient.subBalance(value)){
            System.out.println("Вы сняли "+value+"\n" +
                    "У вас осталось "+currentClient.balance);
        }else {
            System.out.println("На вашем счету недостаточно средств:" + currentClient.balance);
        }
        return true;
    }
    Boolean getBalance(){
        System.out.println("У вас на счету " + currentClient.getBalance());
        return true;
    }
}

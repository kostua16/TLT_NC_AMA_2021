package Bank_system;

public class Bank extends Bankomat{
    public Bank(Client curClient){
        super(curClient);
    }
    Boolean addBalance(Integer value){
        currentClient.addBalance(value);
        return true;
    }
}

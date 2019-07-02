package pereira.anderson.testeandroidv2.dao.ExecuteDao;

import pereira.anderson.testeandroidv2.dao.ClienteCurrency;

/**
 * Created by Anderson on 15/06/2019.
 */

public class ExecuteClienteCurrency {

    public void Execute(String ID){
        ClienteCurrency clienteCurrency = new ClienteCurrency();
        clienteCurrency.ID = ID;
        ClienteCurrency.JsonTask myRequest = new ClienteCurrency.JsonTask();
        myRequest.execute();
    }
}

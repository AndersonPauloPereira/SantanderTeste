package pereira.anderson.testeandroidv2.dao.ExecuteDao;

import android.content.Context;

import pereira.anderson.testeandroidv2.dao.ClienteLogin;
import pereira.anderson.testeandroidv2.model.ModeloLogin;

import static pereira.anderson.testeandroidv2.model.StringsUrlWebService.UrlLoginWebservice;

/**
 * Created by Anderson on 15/06/2019.
 */

public class ExecuteClienteLogin {

    public void Execute(ModeloLogin Login, Context context){
        ClienteLogin clienteLogin = new ClienteLogin();
        clienteLogin.EMAIL = Login.Email;
        clienteLogin.PASS = Login.Password;
        clienteLogin.context = context;
        try{
            ClienteLogin.JsonTask myRequest = new ClienteLogin.JsonTask();
            myRequest.execute(UrlLoginWebservice);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

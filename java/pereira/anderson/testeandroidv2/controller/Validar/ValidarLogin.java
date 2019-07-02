package pereira.anderson.testeandroidv2.controller.Validar;

import android.util.Patterns;

import java.util.regex.Pattern;

import pereira.anderson.testeandroidv2.model.ModeloLogin;

/**
 * Created by Anderson on 13/06/2019.
 */

public class ValidarLogin {
    private ModeloLogin login;
    private String valido;
    public static String CPF = null;
    private final static String EXPRESSAO_REGULAR_SENHA = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}$";

    public ValidarLogin(ModeloLogin login) {
        this.login = login;
        Login(login);
    }

    public void Login(ModeloLogin login){
        if(validacampos(login).equals("true")){

        }else{

        }
    }

    private String validacampos(ModeloLogin login) {

        try{
            if(ValidaCPF.isCPF(login.getEmail())){//.substring(11).matches("[0-9]*")){
               CPF = ValidaCPF.imprimeCPF(login.getEmail());
           }else{
               valido = "false";

                Integer.valueOf(login.getEmail());
           }
        }catch (Exception e) {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if (login.getEmail().length() == 0) {
                return valido = "Favor digitar o seu email!";
            } else if (!pattern.matcher(login.getEmail()).matches()) {
                return valido = "Favor digite um email valido!";
            } else {
                valido = "true";
            }
        }
       if (!login.getPassword().matches(EXPRESSAO_REGULAR_SENHA)){
            return valido = "A senha precisa de pelo menos\n" +
                    "Uma letra maiuscula\nUm numero e\nUm caracter especial\nExemplo: @#$%^&+=";
        }else{
           valido = "true";
       }
        return valido;
    }

    public String RetornoString(){
        return valido;
    }

}

package pereira.anderson.testeandroidv2.controller;

import android.content.Context;
import android.content.SharedPreferences;

import pereira.anderson.testeandroidv2.model.ModeloDadosUsuario;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Anderson on 14/06/2019.
 */

public class SharedPreferenceUsuario {
    ModeloDadosUsuario usuario;

    public SharedPreferenceUsuario(ModeloDadosUsuario usuario) {
        this.usuario = usuario;
    }

    public void SalvardadosLocalmente(ModeloDadosUsuario usuario, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("Login_Salvo", MODE_PRIVATE).edit();
        editor.putInt("id", usuario.getUserId());
        editor.putString("name", usuario.getName());
        editor.putString("bankAccont", usuario.getBankAccount());
        editor.putString("agency", usuario.getAgency());
        editor.putString("balance", usuario.getBalance());
        editor.apply();
    }
}

package pereira.anderson.testeandroidv2.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pereira.anderson.testeandroidv2.R;
import pereira.anderson.testeandroidv2.controller.Validar.ValidaCPF;
import pereira.anderson.testeandroidv2.controller.Validar.ValidarLogin;
import pereira.anderson.testeandroidv2.dao.ExecuteDao.ExecuteClienteLogin;
import pereira.anderson.testeandroidv2.model.ModeloLogin;

public class LoginActivity extends AppCompatActivity {

    EditText Email, Password;
    TextView ErroSenha;
    Button Login;
    ModeloLogin ObjLogin;
    ValidarLogin controlerLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        ErroSenha = (TextView) findViewById(R.id.txErroSenha);
        Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() || actionId == EditorInfo.IME_ACTION_DONE) {
                    ActionButonEntrar();
                }
                return false;
            }
        });
        Login = (Button) findViewById(R.id.btLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ActionButonEntrar();
            }
        });
    }

    public void ActionButonEntrar(){
        ObjLogin = new ModeloLogin(
                Email.getText().toString().trim(),Password.getText().toString().trim());
        controlerLogin = new ValidarLogin(ObjLogin);
        if(controlerLogin.RetornoString().equals("Favor digitar o seu email ou CPF!")){
            ErroSenha.setVisibility(View.VISIBLE);
            ErroSenha.setText(controlerLogin.RetornoString());
            Email.setError("Digite um Email ou CPF valido!");
        }else if(!controlerLogin.RetornoString().equals("true")){
            ErroSenha.setVisibility(View.VISIBLE);
            ErroSenha.setText(controlerLogin.RetornoString());
            Password.setError("Digite uma senha valida!");
        }else{
            if(ValidarLogin.CPF != null){
                Email.setText(ValidarLogin.CPF);
            }
            ErroSenha.setVisibility(View.INVISIBLE);
            Password.setError(null);

            try {
                ExecuteClienteLogin executeClienteLogin= new ExecuteClienteLogin();
                executeClienteLogin.Execute(ObjLogin, LoginActivity.this);
                finish();
                startActivity(new Intent(LoginActivity.this, CurrencyActivity.class));
            }catch (Exception e){

            }
        }
    }
}

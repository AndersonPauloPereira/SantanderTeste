package pereira.anderson.testeandroidv2.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import pereira.anderson.testeandroidv2.R;
import pereira.anderson.testeandroidv2.controller.Adapters.CurrencyAdapter;
import pereira.anderson.testeandroidv2.controller.Validar.Monetary;
import pereira.anderson.testeandroidv2.dao.ClienteCurrency;
import pereira.anderson.testeandroidv2.dao.ExecuteDao.ExecuteClienteCurrency;

public class CurrencyActivity extends AppCompatActivity {
    TextView NomeCliente, ContaCliente, SaldoCliente;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView ListaCurrency;
    ImageButton BtSair;
    SharedPreferences.Editor editor;
    ExecuteClienteCurrency executeClienteCurrency;
    int UserId;
    String Name, BankAccount, Agency, Balance, Saldo;
    CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_lista);
        ListaCurrency = (ListView) findViewById(R.id.Lista);

        SharedPreferences prefs = getSharedPreferences("Login_Salvo", MODE_PRIVATE);
        editor = prefs.edit();
        UserId = prefs.getInt("id", 0);
        Name = prefs.getString("name", "No name defined");
        BankAccount = prefs.getString("bankAccont", "No bankAccont defined");
        Agency = prefs.getString("agency", "No agency defined");
        Balance = prefs.getString("balance", "No balance defined");
        int restoredText = prefs.getInt("id", 0);

        NomeCliente = (TextView) findViewById(R.id.txNomeCliente);
        ContaCliente = (TextView) findViewById(R.id.txContaCliente);
        SaldoCliente = (TextView) findViewById(R.id.txSaldoCliente);

        try {
            Saldo = Monetary.convertToBigDecimal(Balance).toString();
        } catch (Exception e) {
            Saldo = "erro ao carregar";
        }
        if (restoredText != 0) {
            UserId = restoredText;
            NomeCliente.setText(Name);
            ContaCliente.setText(BankAccount + "/" + Agency);
            SaldoCliente.setText(Saldo);

            ClienteCurrency clienteCurrency = new ClienteCurrency();
            if (clienteCurrency.arrayOfCurrency != null) {
                executeClienteCurrency = new ExecuteClienteCurrency();
                executeClienteCurrency.Execute(String.valueOf(restoredText));
                adapter = new CurrencyAdapter(CurrencyActivity.this, clienteCurrency.arrayOfCurrency);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ListaCurrency.setAdapter(adapter);
            }
        }

        BtSair = (ImageButton) findViewById(R.id.BtSair);
        BtSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CurrencyActivity.this, LoginActivity.class));
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                ListaCurrency.setAdapter(null);
                ListaCurrency.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}

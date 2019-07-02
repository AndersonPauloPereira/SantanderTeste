package pereira.anderson.testeandroidv2.dao;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import pereira.anderson.testeandroidv2.model.ModeloCurrency;

import static pereira.anderson.testeandroidv2.model.StringsUrlWebService.UrlListaLancamentosClienteWebservice;

/**
 * Created by Anderson on 16/06/2019.
 */

public class ClienteCurrency {

    public static String ID, RETORNO;
    public static JSONArray JsonDados;
    public static ArrayList<ModeloCurrency> arrayOfCurrency = new ArrayList<>();

    public static class JsonTask extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(UrlListaLancamentosClienteWebservice
                        +ID);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                InputStream inputStream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuffer buffer = new StringBuffer();

               while ((line = reader.readLine()) != null) {
                   buffer.append(line+"\n");
                   JSONObject jsonObject = new JSONObject(buffer.toString());
                   JsonDados =  jsonObject.getJSONArray("statementList");
                }

                return RETORNO = buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                RETORNO = null;
            } catch (IOException e) {
                e.printStackTrace();
                RETORNO = null;
            } catch (JSONException e) {
                e.printStackTrace();
                RETORNO = null;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    RETORNO = null;
                }
            }
            return RETORNO;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
                for (int i = 0; i < JsonDados.length(); i++) {
                    String jsonArrayString = null;
                    try {
                        jsonArrayString = JsonDados.getString(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONObject dados = null;
                    try {
                        dados = new JSONObject(jsonArrayString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ModeloCurrency modelo = null;
                    Locale ptBr = new Locale("pt", "BR");
                    try {
                        modelo = new ModeloCurrency(
                                dados.getString("title"),
                                dados.getString("desc"),
                                dados.getString("date"),
                                NumberFormat.getCurrencyInstance(ptBr).
                                        format(Double.valueOf(dados.getString("value")))
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arrayOfCurrency.add(modelo);
                }
        }
    }


}

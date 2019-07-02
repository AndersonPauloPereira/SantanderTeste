package pereira.anderson.testeandroidv2.dao;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import pereira.anderson.testeandroidv2.controller.SharedPreferenceUsuario;
import pereira.anderson.testeandroidv2.model.ModeloDadosUsuario;

/**
 * Created by Anderson on 14/06/2019.
 */

public class ClienteLogin {

    public static  Context context;
    public static  String EMAIL, PASS;
    public static JSONObject JsonDados;

    public static class JsonTask extends  AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                String StringDeEnvio =  "user=" + String.valueOf(EMAIL);
                StringDeEnvio += "&password=" + String.valueOf(PASS);

                conn.setFixedLengthStreamingMode(StringDeEnvio.getBytes().length);
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(StringDeEnvio);
                out.close();
                InputStream stream = conn.getInputStream();
                conn.connect();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");

                    JSONObject jsonObject = new JSONObject(buffer.toString());
                    JsonDados = new JSONObject(jsonObject.getString("userAccount"));

                    }

            return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
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
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                int id = Integer.valueOf(JsonDados.getString("userId"));
                String nome = JsonDados.getString("name");
                String bank = JsonDados.getString("bankAccount");
                String agency = JsonDados.getString("agency");
                String balance = JsonDados.getString("balance");

                ModeloDadosUsuario modeloDadosUsuario =
                        new ModeloDadosUsuario(id, nome, bank, agency, balance);
                SharedPreferenceUsuario UpDateUser = new SharedPreferenceUsuario(modeloDadosUsuario);
                UpDateUser.SalvardadosLocalmente(modeloDadosUsuario, ClienteLogin.context);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}

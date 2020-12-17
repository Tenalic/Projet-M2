package com.example.monopolight;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.emailuser);
        etPassword = (EditText) findViewById(R.id.passuser);
        btnLogin = (Button) findViewById(R.id.submituser);
        tvRegister = (TextView) findViewById(R.id.registerUser);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new LoginUser().execute(Email, Password);
            }
        });
    }




    public class LoginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Password = strings[1];
            String authorization = Email+ ":" + Password;

            byte[] encodeValue = Base64.encode(authorization.getBytes(), Base64.DEFAULT);
            String encodedString = new String(encodeValue);
            Log.d("ENCODE_DECODE", "value = " + encodedString);


            Log.d("ENCODE_DECODE", "defaultValue = " + Password);
            Log.d("ENCODE_DECODE", "encodeValue = " + new String(encodeValue));


            try {
                URL url = new URL("https://monopolym2tnsi.hopto.org:8443/account/connection");
                ConfigSSL.trustAllHosts();
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setHostnameVerifier(ConfigSSL.DO_NOT_VERIFY);
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Basic " + encodedString);
                JSONObject data = new JSONObject();
                data.put("Username", Email);
                data.put("Password",encodeValue );

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data.toString());
                wr.flush();
                wr.close();


                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject user = new JSONObject(response.toString());

                Intent i = new Intent(LoginActivity.this,
                        MainActivity.class);
                i.putExtra("id", user.getString("id"));
                startActivity(i);
                System.out.println(response.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }



    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onResume()
    {
        super.onResume();
        etEmail.setText("");
        etPassword.setText("");
    }
}

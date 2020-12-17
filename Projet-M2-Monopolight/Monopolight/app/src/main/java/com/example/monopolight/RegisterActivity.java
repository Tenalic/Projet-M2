package com.example.monopolight;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etfirstname,etlastname,etnickname;
    Button btnRegister;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etfirstname = (EditText) findViewById(R.id.firstname);
        etlastname = (EditText) findViewById(R.id.lastname);
        etnickname = (EditText) findViewById(R.id.nickname);
        etEmail = (EditText) findViewById(R.id.emailuser);
        etPassword = (EditText) findViewById(R.id.passworduser);
        btnRegister = (Button) findViewById(R.id.registerUser);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstname = etfirstname.getText().toString();
                String Lastname = etlastname.getText().toString();
                String Password = etPassword.getText().toString();
                String Nickname = etnickname.getText().toString();
                String Email = etEmail.getText().toString();

                if(Firstname.isEmpty() || Lastname.isEmpty() || Password.isEmpty() || Nickname.isEmpty() || Email.isEmpty())
                {
                    showToast("Vérifier les informations renseignées.");
                }
                else
                {
                    new RegisterUser().execute(Firstname, Lastname, Nickname,Email,Password);
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }




    public class RegisterUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Firstname = strings[0];
            String Lastname = strings[1];
            String Nickname = strings[2];
            String Email = strings[3];
            String Password = strings[4];

         //   byte[] encodeValue = Base64.encode(Password.getBytes(), Base64.DEFAULT);


            try {
                URL url = new URL("https://monopolym2tnsi.hopto.org:8443/account/create");
                ConfigSSL.trustAllHosts();
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setHostnameVerifier(ConfigSSL.DO_NOT_VERIFY);
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject data = new JSONObject();
                data.put("lastname", Lastname);
                data.put("firstname", Firstname);
                data.put("nickname", Nickname);
                data.put("email", Email);
                data.put("password", Password);

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

                JSONObject user = new JSONObject(response.toString());

                Intent i = new Intent(RegisterActivity.this,
                        MainActivity.class);
                i.putExtra("id", user.getString("id"));
                startActivity(i);
                finish();
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                showToast("Verifiez vos informations.");
            }
            return null;
        }
    }


    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}

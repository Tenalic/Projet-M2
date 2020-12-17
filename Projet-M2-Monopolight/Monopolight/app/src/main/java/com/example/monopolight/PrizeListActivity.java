package com.example.monopolight;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class PrizeListActivity extends AppCompatActivity {

    private GridView gridView;
    public String rep = "";
    private String idAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_list);

        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("id")) { idAccount = intent.getStringExtra("id"); }
        }

        List<Prize> image_details = new ArrayList<Prize>();
        try {

            image_details = getPrizeList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gridView = (GridView) findViewById(R.id.gridView);
        //List<Prize> image_details = getPrizeList();
        gridView.setAdapter(new GridAdapter(this, image_details)); // utilise GridAdapter pour adapter chaque cadeau dans la liste

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Prize prize = (Prize) o;
                Toast.makeText(PrizeListActivity.this, "Selected :"
                        + " " + prize, Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private List<Prize> getPrizeList() throws Exception {
        List<Prize> prizeList = new ArrayList<Prize>();
        thread th = new thread();

        th.start();
        th.join();
        rep = th.resp;

        JSONObject board = new JSONObject(rep);
        JSONArray jsonArray = new JSONArray(board.getString("prize"));
        System.out.println(jsonArray.toString());
        int i;
        for (i = 0; i <= jsonArray.length() - 1; i++) {
            prizeList.add(new Prize(jsonArray.get(i).toString(), 5, "bigmac"));

        }
        return prizeList;
    }

    public class thread extends Thread {
        public String resp = "";

        @Override
        public void run() {

            String urlGet = "https://monopolym2tnsi.hopto.org:8443/account/game-info";
            URL obj = null;
            try {
                obj = new URL(urlGet);
                ConfigSSL.trustAllHosts();
                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                con.setHostnameVerifier(ConfigSSL.DO_NOT_VERIFY);
                con.setRequestMethod("GET");

                con.setRequestProperty("IdAccount", idAccount);
                con.setRequestProperty("Content-Type", "application/json");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;

                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                resp = response.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
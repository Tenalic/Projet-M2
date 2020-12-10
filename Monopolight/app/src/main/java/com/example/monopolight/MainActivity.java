package com.example.monopolight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    public int idAccount = 307;

    public String repGet = "";

    public int position;
    public int nbdice = 0;
    public int money = 0;
    private List<RelativeLayout> Cases;
    private List<RelativeLayout> pions;

    private RelativeLayout pion0;
    private RelativeLayout pion1;
    private RelativeLayout pion2;
    private RelativeLayout pion3;
    private RelativeLayout pion4;
    private RelativeLayout pion5;
    private RelativeLayout pion6;
    private RelativeLayout pion7;
    private RelativeLayout pion8;
    private RelativeLayout pion9;
    private RelativeLayout pion10;
    private RelativeLayout pion11;
    private RelativeLayout pion12;
    private RelativeLayout pion13;
    private RelativeLayout pion14;
    private RelativeLayout pion15;
    private RelativeLayout pion16;
    private RelativeLayout pion17;
    private RelativeLayout pion18;
    private RelativeLayout pion19;

    public RelativeLayout root;

    private Button add;
    private Button listPrizes;
    private Button dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        root = findViewById(R.id.root);
// region Pions
        pions = new ArrayList<RelativeLayout>();

        pion0 = findViewById(R.id.pion0);
        pion1 = findViewById(R.id.pion1);
        pion2 = findViewById(R.id.pion2);
        pion3 = findViewById(R.id.pion3);
        pion4 = findViewById(R.id.pion4);
        pion5 = findViewById(R.id.pion5);
        pion6 = findViewById(R.id.pion6);
        pion7 = findViewById(R.id.pion7);
        pion8 = findViewById(R.id.pion8);
        pion9 = findViewById(R.id.pion9);
        pion10 = findViewById(R.id.pion10);
        pion11 = findViewById(R.id.pion11);
        pion12 = findViewById(R.id.pion12);
        pion13 = findViewById(R.id.pion13);
        pion14 = findViewById(R.id.pion14);
        pion15 = findViewById(R.id.pion15);
        pion16 = findViewById(R.id.pion16);
        pion17 = findViewById(R.id.pion17);
        pion18 = findViewById(R.id.pion18);
        pion19 = findViewById(R.id.pion19);

        pions.add(pion0);
        pions.add(pion1);
        pions.add(pion2);
        pions.add(pion3);
        pions.add(pion4);
        pions.add(pion5);
        pions.add(pion6);
        pions.add(pion7);
        pions.add(pion8);
        pions.add(pion9);
        pions.add(pion10);
        pions.add(pion11);
        pions.add(pion12);
        pions.add(pion13);
        pions.add(pion14);
        pions.add(pion15);
        pions.add(pion16);
        pions.add(pion17);
        pions.add(pion18);
        pions.add(pion19);
// endregion
// region Buttons
        add = findViewById(R.id.add);
        listPrizes = findViewById(R.id.listprizes);
        dice = findViewById(R.id.btndice);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listPrizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dice.setClickable(false);

                PopupWindow popUp = new PopupWindow(MainActivity.this);
                Random r = new Random();
                int frandom = r.nextInt((6 - 1) + 1) + 1;
                int srandom = r.nextInt((6 - 1) + 1) + 1;
                LinearLayout layout = new LinearLayout(MainActivity.this);

                GifImageView fdice = new GifImageView(MainActivity.this);
                switch(frandom)
                {
                    case 1 :
                        fdice.setImageResource(R.drawable.de1);
                        break;
                    case 2 :
                        fdice.setImageResource(R.drawable.de2);
                        break;
                    case 3 :
                        fdice.setImageResource(R.drawable.de3);
                        break;
                    case 4 :
                        fdice.setImageResource(R.drawable.de4);
                        break;
                    case 5 :
                        fdice.setImageResource(R.drawable.de5);
                        break;
                    case 6 :
                        fdice.setImageResource(R.drawable.de6);
                        break;
                }

                GifImageView sdice = new GifImageView(MainActivity.this);
                switch(srandom)
                {
                    case 1 :
                        sdice.setImageResource(R.drawable.de1);
                        break;
                    case 2 :
                        sdice.setImageResource(R.drawable.de2);
                        break;
                    case 3 :
                        sdice.setImageResource(R.drawable.de3);
                        break;
                    case 4 :
                        sdice.setImageResource(R.drawable.de4);
                        break;
                    case 5 :
                        sdice.setImageResource(R.drawable.de5);
                        break;
                    case 6 :
                        sdice.setImageResource(R.drawable.de6);
                        break;
                }

                ViewGroup.LayoutParams par = new ViewGroup.LayoutParams(150,150);

                LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(fdice, par);
                layout.addView(sdice, par);
                popUp.setContentView(layout);

                popUp.showAtLocation(layout, Gravity.CENTER, 0, 75);

                final Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        popUp.dismiss();
                        forward(position,frandom, srandom);
                        dice.setClickable(true);
                    }
                };
                final Handler h = new Handler();
                h.postDelayed(run, 2360);


            }
        });
// endregion
// region HTTP request
        try {
            sendGet();
        } catch (Exception e) {
            System.out.println(e);
        }
// endregion
        //récupération position dans le plateau
        position = 0;
        pion0.setVisibility(View.VISIBLE);
        //

    }

    private void forward(int position, int fdice, int sdice)
    {
        int fd = fdice + sdice;
        pions.get(position).setVisibility(View.INVISIBLE);
        if(position + fd > 19)
        {
            this.position = (position + fd) - 19;
        }
        else
        {
            this.position = position + fd;
        }
        pions.get(this.position).setVisibility(View.VISIBLE);
    }

    private void sendGet() throws Exception
    {
        thread th = new thread();

        th.start();
        th.join();
        repGet = th.resp;
        System.out.println(repGet);

    }

    public class thread extends Thread
    {
        public String resp = "";
        @Override
        public void run() {

            String urlGet = "http://monopolym2tnsi.hopto.org:8080/account/game-info";
            URL obj = null;
            try {
                obj = new URL(urlGet);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");

                con.setRequestProperty("IdAccount", "307");
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

        public String getResp()
        {
            return resp;
        }
    }
}
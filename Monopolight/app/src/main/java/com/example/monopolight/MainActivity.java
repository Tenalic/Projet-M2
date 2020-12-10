package com.example.monopolight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.JsonReader;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.*;

public class MainActivity extends AppCompatActivity {
    public int idAccount = 307;

    public String repGet = "";
    public String repGetBoard = "";

    int frandom = 1;
    int srandom = 1;

    public int position;
    public String nbdice;
    public String money;
    public String casesPurchased;
    private List<square> cases;
    private List<RelativeLayout> pions;
    private List<ImageView> homes;
// region Layout pion
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
//endregion
// region Layout case
    private RelativeLayout case0;
    private RelativeLayout case1;
    private RelativeLayout case2;
    private RelativeLayout case3;
    private RelativeLayout case4;
    private RelativeLayout case5;
    private RelativeLayout case6;
    private RelativeLayout case7;
    private RelativeLayout case8;
    private RelativeLayout case9;
    private RelativeLayout case10;
    private RelativeLayout case11;
    private RelativeLayout case12;
    private RelativeLayout case13;
    private RelativeLayout case14;
    private RelativeLayout case15;
    private RelativeLayout case16;
    private RelativeLayout case17;
    private RelativeLayout case18;
    private RelativeLayout case19;
//endregion
// region Image Home
    private ImageView home0;
    private ImageView home1;
    private ImageView home2;
    private ImageView home3;
    private ImageView home4;
    private ImageView home5;
    private ImageView home6;
    private ImageView home7;
    private ImageView home8;
    private ImageView home9;
    private ImageView home10;
    private ImageView home11;
    private ImageView home12;
    private ImageView home13;
    private ImageView home14;
    private ImageView home15;
    private ImageView home16;
    private ImageView home17;
    private ImageView home18;
    private ImageView home19;
//endregion

    public RelativeLayout root;

    private Button add;
    private Button listPrizes;
    private Button dice;

    private TextView txtcredit;
    private TextView txtnbdice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcredit = findViewById(R.id.credit);
        txtnbdice = findViewById(R.id.nbdice);

        cases = new ArrayList<square>();

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
// region Homes
        homes = new ArrayList<ImageView>();

        home0 = findViewById(R.id.home0);
        home1 = findViewById(R.id.home1);
        home2 = findViewById(R.id.home2);
        home3 = findViewById(R.id.home3);
        home4 = findViewById(R.id.home4);
        home5 = findViewById(R.id.home5);
        home6 = findViewById(R.id.home6);
        home7 = findViewById(R.id.home7);
        home8 = findViewById(R.id.home8);
        home9 = findViewById(R.id.home9);
        home10 = findViewById(R.id.home10);
        home11 = findViewById(R.id.home11);
        home12 = findViewById(R.id.home12);
        home13 = findViewById(R.id.home13);
        home14 = findViewById(R.id.home14);
        home15 = findViewById(R.id.home15);
        home16 = findViewById(R.id.home16);
        home17 = findViewById(R.id.home17);
        home18 = findViewById(R.id.home18);
        home19 = findViewById(R.id.home19);

        homes.add(home0);
        homes.add(home1);
        homes.add(home2);
        homes.add(home3);
        homes.add(home4);
        homes.add(home5);
        homes.add(home6);
        homes.add(home7);
        homes.add(home8);
        homes.add(home9);
        homes.add(home10);
        homes.add(home11);
        homes.add(home12);
        homes.add(home13);
        homes.add(home14);
        homes.add(home15);
        homes.add(home16);
        homes.add(home17);
        homes.add(home18);
        homes.add(home19);
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
                if(Integer.parseInt(nbdice) >= 2)
                {
                    dice.setClickable(false);

                    PopupWindow popUp = new PopupWindow(MainActivity.this);
                    /*Random r = new Random(); */


                    threatPostDice th = new threatPostDice();

                    try {
                        th.start();
                        th.join();

                        JSONObject roll = new JSONObject(th.resp);
                        JSONArray rolldice = new JSONArray(roll.getString("diceResult"));

                        frandom = (int) rolldice.get(0);
                        srandom = (int) rolldice.get(1);

                    } catch (InterruptedException | JSONException e) {
                        e.printStackTrace();
                    }
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
                            buy();
                            dice.setClickable(true);
                            int temp = Integer.parseInt(nbdice) - 2;
                            nbdice = String.valueOf(temp);
                            txtnbdice.setText(nbdice);
                        }
                    };
                    final Handler h = new Handler();
                    h.postDelayed(run, 2360);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Impossible");
                    builder.setMessage("Vous n'avez pas assez de dé pour jouer !");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            }
        });
// endregion
// region HTTP request GET
        try {
            sendGet();
            sendGetBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
// endregion
// region Cases

        cases.get(0).c = findViewById(R.id.coinbasdroite2);
        cases.get(1).c = findViewById(R.id.quatriemebas);
        cases.get(2).c = findViewById(R.id.troisiemebas);
        cases.get(3).c = findViewById(R.id.deuxiemebas);
        cases.get(4).c = findViewById(R.id.premierbas);
        cases.get(5).c = findViewById(R.id.coinbasgauche2);
        cases.get(6).c = findViewById(R.id.quatriemegauche);
        cases.get(7).c = findViewById(R.id.troisiemegauche);
        cases.get(8).c = findViewById(R.id.deuxiemegauche);
        cases.get(9).c = findViewById(R.id.premiergauche);
        cases.get(10).c = findViewById(R.id.coinhautgauche2);
        cases.get(11).c = findViewById(R.id.premierhaut);
        cases.get(12).c = findViewById(R.id.deuxiemehaut);
        cases.get(13).c = findViewById(R.id.troisiemehaut);
        cases.get(14).c = findViewById(R.id.quatriemehaut);
        cases.get(15).c = findViewById(R.id.coinhautdroite2);
        cases.get(16).c = findViewById(R.id.premierdroite);
        cases.get(17).c = findViewById(R.id.deuxiemedroite);
        cases.get(18).c = findViewById(R.id.troisiemedroite);
        cases.get(19).c = findViewById(R.id.quatriemedroite);

        int j;
        for(j = 0; j <= cases.size()-1; j++)
        {
            TextView txtname = (TextView) cases.get(j).c.getChildAt(2);
            TextView txtcost = (TextView) cases.get(j).c.getChildAt(3);
            txtname.setTextColor(Color.parseColor("#363535"));
            txtname.setText(cases.get(j).name);
            txtcost.setTextColor(Color.parseColor("#363535"));
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(cases.get(j).cost) + "$");
            txtcost.setText(sb.toString());
            cases.get(j).c.setBackgroundColor(Color.parseColor(cases.get(j).color));
        }
// endregion
// region traitement de la réponse GET
        try {
            JSONObject user = new JSONObject(repGet);
            nbdice = user.getString("nbDice");
            txtnbdice.setText(nbdice);
            money = user.getString("credit");
            txtcredit.setText(money);
            //récupération position dans le plateau
            position = Integer.parseInt(user.getString("indexSquare"));
            pions.get(position).setVisibility(View.VISIBLE);
            //
            //récupération des cases achetés par le joueur
            casesPurchased = (user.getString("indexSquarePurchased"));
            StringBuilder sb = new StringBuilder(casesPurchased);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            String[] indexSquarePurchased = sb.toString().split(",");
            int i;
            for(i=0; i <= indexSquarePurchased.length-1; i++)
            {
                homes.get(Integer.parseInt(indexSquarePurchased[i])).setVisibility(View.VISIBLE);
            }
            //
        } catch (JSONException e) {
        System.out.println("oui");
    }
// endregion
    }

    private void forward(int position, int fdice, int sdice)
    {
        int fd = fdice + sdice;
        pions.get(position).setVisibility(View.INVISIBLE);
        if(position + fd > 19)
        {
            this.position = (position + fd) - 20;
        }
        else
        {
            this.position = position + fd;
        }
        pions.get(this.position).setVisibility(View.VISIBLE);
    }

    private void sendGet() throws Exception
    {
        threadGet th = new threadGet();

        th.start();
        th.join();
        repGet = th.resp;
    }

    private void sendGetBoard() throws Exception
    {
        threadGetBoard th = new threadGetBoard();

        th.start();
        th.join();
        repGetBoard = th.resp;



        JSONObject board = new JSONObject(repGetBoard);
        JSONArray jsonArray = new JSONArray(board.getString("board"));
        int i;
        for(i = 0; i <= jsonArray.length()-1; i++)
        {
            JSONObject JTemp = (JSONObject) jsonArray.get(i);
            square s = new square();
            s.name = JTemp.getString("streetName");
            s.cost = Integer.parseInt(JTemp.getString("cost"));
            s.color = JTemp.getString("color");
            cases.add(s);
        }
    }

    public class threadGet extends Thread
    {
        public String resp;
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

    public class threadGetBoard extends Thread
    {
        public String resp = "";
        @Override
        public void run()
        {
            String urlGet = "http://monopolym2tnsi.hopto.org:8080/board";
            URL obj = null;
            try {
                obj = new URL(urlGet);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");



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

    public class threatPostBuy extends Thread
    {
        String resp = "";
        @Override
        public void run()
        {
            String urlGet = "http://monopolym2tnsi.hopto.org:8080/account/buy";
            URL obj = null;
            try {
                obj = new URL(urlGet);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("POST");

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
    }

    public class threatPostDice extends Thread
    {
        String resp = "";
        @Override
        public void run()
        {
            String urlGet = "http://monopolym2tnsi.hopto.org:8080/account/dice";
            URL obj = null;
            try {
                obj = new URL(urlGet);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("POST");

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
    }

    public class square
    {
        String name;
        int cost;
        String color;
        RelativeLayout c;
    }

    private void buy()
    {
        if(homes.get(position).getVisibility() == View.INVISIBLE && position != 0 && position != 5 && position != 10 && position != 15)
        {
            if(Integer.parseInt(money) > cases.get(position).cost)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Acheter");
                builder.setMessage("Voulez-vous acheter cette case ?");
                builder.setCancelable(false);


                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // verif front
                        homes.get(position).setVisibility(View.VISIBLE);
                        int temp = Integer.parseInt(money) - cases.get(position).cost;
                        money = String.valueOf(temp);
                        txtcredit.setText(money);
                        dialog.dismiss();

                        // verif back
                        threatPostBuy th = new threatPostBuy();

                        try {
                            th.start();
                            th.join();

                            JSONObject rep = new JSONObject(th.resp);
                            if(rep.length() == 4)
                            {
                                updateSquarePurchased(new JSONArray(rep.getString("indexSquarePurchased")));

                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                                builder.setTitle("Bravo");
                                builder.setMessage("Vous avez gagné une récompense :\n" + rep.getString("prizeWin"));

                                builder.setPositiveButton("Merci", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                builder.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    private void updateSquarePurchased(JSONArray jarray) throws Exception
    {
        int i = 0;
        for(i = 0; i <= jarray.length()-1; i++)
        {
            homes.get((int) jarray.get(i)).setVisibility(View.GONE);
        }
        int j = 0;
        for(j = 0; j <= homes.size()-1; j++)
        {
            if(homes.get(j).getVisibility() == View.GONE)
            {
                homes.get(j).setVisibility(View.VISIBLE);
            }
            else { homes.get(j).setVisibility(View.INVISIBLE); }
        }
    }
}
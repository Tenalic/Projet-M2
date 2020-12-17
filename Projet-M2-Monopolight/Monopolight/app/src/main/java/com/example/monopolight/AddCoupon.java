package com.example.monopolight;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class AddCoupon extends AppCompatActivity implements View.OnClickListener {

    String idAccount;
    Button scanBtn;
    Button submit;
    EditText eTqrcode;
    String sqrcode;
    Long qrcode;
    RequestQueue mQueue;
    Integer nbDice = 0;
    Integer credit = 0;
    TextView mTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);

        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("id")) { idAccount = intent.getStringExtra("id"); }
        }

        scanBtn= findViewById(R.id.scanner);
        scanBtn.setOnClickListener(this);
        HurlStack hurl = new HurlStack() {
            @Override
            protected HttpURLConnection createConnection(URL url) throws IOException {
                ConfigSSL.trustAllHosts();
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.createConnection(url);
                httpsURLConnection.setHostnameVerifier(ConfigSSL.DO_NOT_VERIFY);
                return httpsURLConnection;
            }
        };

        mQueue = Volley.newRequestQueue(this, hurl);
        mTxtView = findViewById(R.id.text_view_test);

        eTqrcode = findViewById(R.id.qrcode);

        LinearLayout linearForImage = (LinearLayout)findViewById(R.id.image_layout);

        submit= findViewById(R.id.submit);

        scanBtn.setBackgroundColor(Color.parseColor("#d14232"));
        submit.setBackgroundColor(Color.parseColor("#d14232"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(eTqrcode)) {
                    Log.e("champ vide", "c'est vide");
                } else {
                    sqrcode = eTqrcode.getText().toString();
                    if(sqrcode.matches("\\d*")) {
                        qrcode = Long.parseLong(sqrcode);
                        Log.e("val numerique: ", "valeur " + qrcode);

                        jsonCall(qrcode, idAccount, linearForImage);

                    } else {
                        Toast.makeText(AddCoupon.this, "Code invalide, le code est uniquement numérique", Toast.LENGTH_LONG).show();
                    }
                    //Log.e("qrcode vaut :", sqrcode);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        scanQRCode();
    }

    public void scanQRCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan du code");
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LinearLayout linearForImage = (LinearLayout)findViewById(R.id.image_layout);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // just if Boolean checking = checkCouponValidity(result.getContents(), databaseList());
                sqrcode = result.getContents();
                if(sqrcode.matches("\\d*")) {
                    qrcode = Long.parseLong(sqrcode);
                    Log.e("qrcode numerique vaut :", " " + qrcode);
                    jsonCall(qrcode, idAccount, linearForImage);
                } else {
                    Toast.makeText(this, "Code invalide", Toast.LENGTH_SHORT).show();
                }
                //
                Log.e("valeur du scan", sqrcode);
                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents() );
                builder.setTitle("Resultat du scan");
                builder.setPositiveButton("Scan encore", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanQRCode();
                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();*/
            } else {
                Toast.makeText(this, "Pas de résultat, veuillez réessayer", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void jsonCall(Long qrcodeR, String idaccount, LinearLayout linearForImage) {
        String urltest = "https://httpbin.org/response-headers?freeform=100";
        String url = "https://monopolym2tnsi.hopto.org:8443/code/use/" + qrcodeR;
        //String url3 = "http://monopolym2tnsi.hopto.org:8080/code/use/5";
        //String url4 = "http://monopolym2tnsi.hopto.org:8080/code/use/258364";

        Log.e("url: ", " " + url);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ImageView image = new ImageView(AddCoupon.this);

                            if(response.has("message")) {
                                String msg = response.getString("message");
                                Log.e("msg = ", " " + msg);

                                mTxtView.append(dateFormat.format(date) + ' ' + msg + "\n\n");
                            } else {
                                if (response.has("prize")){
                                    nbDice = response.getInt("nbDice");
                                    String prizeWin = response.getString("prizeWin");
                                    Log.e("nb dé :", " " + nbDice);
                                    Log.e("prize win :", " " + prizeWin);

                                    mTxtView.append(dateFormat.format(date) + " nombre dé:" + nbDice + ", prix gagné : " + prizeWin + "\n\n");
                                } else {
                                    if (response.has("credit")) {
                                        nbDice = response.getInt("nbDice");
                                        credit = response.getInt("credit");
                                        Log.e("nb dé :", " " + nbDice);
                                        Log.e("nb credit :", " " + credit);

                                        mTxtView.append(dateFormat.format(date) + " nombre dé:" + nbDice + ", crédit actuel:" + credit + "\n\n");
                                    } else {
                                        if (response.has("nbDice")) {
                                            nbDice = response.getInt("nbDice");
                                            Log.e("nb dé :", " " + nbDice);

                                            mTxtView.append(dateFormat.format(date) + " nombre dé:" + nbDice + "\n\n");
                                        }
                                    }
                                }

                            }
                            Log.e("linear image :", " " + linearForImage);
                            if(linearForImage.getChildCount() == 0) {

                                //ImageView image = new ImageView(AddCoupon.this);
                                image.setImageResource(R.drawable.ic_check);
                                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(410,390));
                                image.setMaxHeight(40);
                                image.setMaxWidth(40);


                                // Adds the view to the layout
                                linearForImage.addView(image);
                            }



                        } catch (JSONException e) {
                            Log.e("erreur dans catch", " ");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("erreur dans on error response ", " first");
                // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {

                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        //JSONObject obj = new JSONObject(res);
                        Log.e("erreur dans on error response ", " first1" + res);
                    } catch (UnsupportedEncodingException e1) {
                        Log.e("erreur dans on error response ", " first2");
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } /*catch (JSONException e2) {
                        Log.e("erreur dans on error response ", " first3");
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }*/
                }

                Log.e("erreur dans on error response ", " last");
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idaccount", idaccount);
                //params.put("Accept-Language", "fr");

                return params;
            }
        };

        mQueue.add(request);

    }
}
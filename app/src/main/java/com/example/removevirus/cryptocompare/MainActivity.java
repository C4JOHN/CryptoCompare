package com.example.removevirus.cryptocompare;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue rq;
    String[] apiCalls;
    String[] currencyPics;
    String[] currencyNames;

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    CustomAdapter adapter;
    List<Currencies> currenciesList;

    String strBtc;
    String strEth;
    String curName;
    String imgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        currenciesList=new ArrayList<>();
        //currenciesList.add(new Currencies("NAIRA","23","334","http://c8.alamy.com/comp/J72W9M/sign-of-money-naira-icon-cartoon-style-J72W9M.jpg"));
        currenciesList.add(new Currencies("NAIRA","23","334","http://c8.alamy.com/comp/J72W9M/sign-of-money-naira-icon-cartoon-style-J72W9M.jpg"));
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter=new CustomAdapter(this,currenciesList);
        recyclerView.setAdapter(adapter);

        rq= Volley.newRequestQueue(this);
        apiCalls=getResources().getStringArray(R.array.api_calls);
        currencyPics=getResources().getStringArray(R.array.currency_pics);
        currencyNames=getResources().getStringArray(R.array.currency_names);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Currencies movie = currenciesList.get(position);
                Toast.makeText(getApplicationContext(), movie.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplication(),ConverstionActivity.class);
                intent.putExtra("name",movie.getName());
                intent.putExtra("img",movie.getImg());
                intent.putExtra("btc",movie.getBtc());
                intent.putExtra("eth",movie.getEth());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        String strBtc=null;
        String strEth=null;
        String  curName=null;
        String  imgUrl=null;


        for (int i=0; i<apiCalls.length;i++){
            final int j=i;
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                    apiCalls[i],null, new Response.Listener<JSONObject>(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        String strBtc=response.getString("BTC");
                        String strEth=response.getString("ETH");
                       String  curName=currencyNames[j];
                       String  imgUrl=currencyPics[j];
                        Currencies curr=new Currencies(curName,strBtc,strEth,imgUrl);
                        currenciesList.add(j,curr);
                       // currenciesList.add(new Currencies("NAIRA","23","334","http://c8.alamy.com/comp/J72W9M/sign-of-money-naira-icon-cartoon-style-J72W9M.jpg"));
                               // .add(curr);
                       // Toast.makeText(getApplicationContext(),curr.getName() +curr.getImg()+curr.getBtc()+curr.getEth(),Toast.LENGTH_SHORT).show();
                    }catch(JSONException e){

                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Error loading prices",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

            rq.add(jsonObjectRequest);
        }

        currenciesList.remove(currenciesList.size()-1);

        Toast.makeText(getApplicationContext(),String.valueOf(currenciesList.size()),
                Toast.LENGTH_LONG).show();

       /*final TextView tv=(TextView)findViewById(R.id.BTC);
        final TextView tv1=(TextView)findViewById(R.id.ETH);
        final TextView tv2=(TextView)findViewById(R.id.currency_name);
        ImageView img=(ImageView)findViewById(R.id.image_view);*/

       /* Glide.with(getApplicationContext()).load(currencyPics[0]).into(img);

        tv2.setText(currencyNames[8]);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                apiCalls[0],null,new Response.Listener<JSONObject>(){

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String str=response.getString("BTC");
                    double value=Math.pow(Double.parseDouble(str),-1.0);
                    DecimalFormat formatter=new DecimalFormat("#,###.00");
                    tv.append(String.valueOf(formatter.format(value)));
                    String str1=response.getString("ETH");
                    value=Math.pow(Double.parseDouble(str1),-1.0);
                    tv1.append(String.valueOf(formatter.format(value)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jsonObjectRequest);*/
    }
}

package com.example.removevirus.cryptocompare;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;

public class ConverstionActivity extends AppCompatActivity {

  // double btcConvert;
   //double ethConvert;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converstion);

      final  DecimalFormat formatter=new DecimalFormat("#,###.00000000");

        TextView currentText=(TextView)findViewById(R.id.currency_name);
       final EditText btcEntry=(EditText)findViewById(R.id.btc_entry);
        final EditText ethEntry=(EditText)findViewById(R.id.eth_entry);

        final EditText baseEntry=(EditText)findViewById(R.id.base_entry);
        CircleImageView currencyImageView=(CircleImageView)findViewById(R.id.conversion_image_view);

        Intent intent=getIntent();
        String name =intent.getStringExtra("name");
        String imgName=intent.getStringExtra("img");
        String stringBtc=intent.getStringExtra("btc");
        String stringEth=intent.getStringExtra("eth");

        currentText.setText(name);
        btcEntry.setText(formatter.format(Double.parseDouble(stringBtc)).toString());
        ethEntry.setText(formatter.format(Double.parseDouble(stringEth)).toString());
        Glide.with(getApplicationContext()).load(imgName).into(currencyImageView);




     final double btcConvert=Double.parseDouble(stringBtc);
     final double ethConvert=Double.parseDouble(stringEth);
        //Toast.makeText(getApplicationContext(),String.valueOf(btcConvert),Toast.LENGTH_SHORT).show();
        baseEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getApplicationContext(),baseEntry.getText().toString(),Toast.LENGTH_SHORT).show();
                double val = 0;
                if (baseEntry.getText().toString().isEmpty() || baseEntry.getText() == null || baseEntry.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_SHORT).show();
                } else {
                    val=Double.parseDouble(baseEntry.getText().toString());
                    ethEntry.setText(formatter.format(ethConvert * val));
                    btcEntry.setText(formatter.format(btcConvert * val));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btcEntry.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getApplicationContext(),btcEntry.getText().toString(),Toast.LENGTH_SHORT).show();
            double val = 0;
                if (btcEntry.getText().toString().isEmpty() ||btcEntry.getText().toString().equals(".") || btcEntry.getText() == null || btcEntry.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_SHORT).show();
                }else{
                    val=Double.parseDouble(btcEntry.getText().toString());
                   // Toast.makeText(getApplicationContext(),String.valueOf(val), Toast.LENGTH_SHORT).show();
                   // baseEntry.setText(formatter.format((1/btcConvert)*val));
                    //ethEntry.setText(formatter.format((ethConvert/btcConvert)*val).toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ethEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             //   Toast.makeText(getApplicationContext(),ethEntry.getText().toString(),Toast.LENGTH_SHORT).show();
                double val = 0;
                if (ethEntry.getText().toString().isEmpty() ||ethEntry.getText().toString().equals(".") || ethEntry.getText() == null || btcEntry.getText().toString() == null) {
                    Toast.makeText(getApplicationContext(), "field is empty", Toast.LENGTH_SHORT).show();
                }else{
                    val=Double.parseDouble(ethEntry.getText().toString());
                    Toast.makeText(getApplicationContext(),String.valueOf(val), Toast.LENGTH_SHORT).show();
                    //baseEntry.setText(formatter.format((1/ethConvert)*val));
                    //btcEntry.setText(formatter.format((btcConvert/ethConvert)*val).toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void convert(){

    }
}

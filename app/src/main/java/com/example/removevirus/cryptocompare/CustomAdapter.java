package com.example.removevirus.cryptocompare;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by removevirus on 11/4/2017.
 */

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    List<Currencies> currenciesList;

    public CustomAdapter(Context context, List<Currencies> currenciesList) {
        this.context = context;
        this.currenciesList = currenciesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DecimalFormat formatter=new DecimalFormat("#,###.00");
        //tv.setText(String.valueOf(formatter.format(
          // Math.pow(Double.parseDouble(currenciesList.get(position).getBtc()),-1.0))));

        Glide.with(context).load(currenciesList.get(position).getImg()).into(holder.img);
         holder.currencyName.setText(currenciesList.get(position).getName());
        holder.btcToCurrency.setText("1 BTC= "+String.valueOf(formatter.format(
                Math.pow(Double.parseDouble(currenciesList.get(position).getBtc()),-1.0))));
        holder.ethToCurrency.setText("1 ETH= "+String.valueOf(formatter.format(
                Math.pow(Double.parseDouble(currenciesList.get(position).getEth()),-1.0))));
    }

    @Override
    public int getItemCount() {
        return currenciesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView currencyName;
        TextView btcToCurrency;
        TextView ethToCurrency;
        public ViewHolder(View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.image_view);
            currencyName=(TextView)itemView.findViewById(R.id.currency_name);
            btcToCurrency=(TextView)itemView.findViewById(R.id.BTC);
            ethToCurrency=(TextView)itemView.findViewById(R.id.ETH);
        }
    }
}

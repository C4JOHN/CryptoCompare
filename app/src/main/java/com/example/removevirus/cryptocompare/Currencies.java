package com.example.removevirus.cryptocompare;

/**
 * Created by removevirus on 11/4/2017.
 */

public class Currencies {
    String name,btc, eth,img;

    public Currencies(String name,String btc, String eth, String img) {
        this.name=name;
        this.btc = btc;
        this.eth = eth;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBtc() {

        return btc;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }

    public String getEth() {
        return eth;
    }

    public void setEth(String eth) {
        this.eth = eth;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

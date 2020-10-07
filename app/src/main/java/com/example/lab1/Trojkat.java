package com.example.lab1;

import android.util.Log;

public class Trojkat extends Figura {
    double wysokosc;

    public Trojkat(double wymiar) {
        super(wymiar);
        wysokosc = wymiar * Math.sqrt(3) / 2;
        super.pole = wymiar * wymiar * Math.sqrt(3) / 4;
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "TROJKAT o polu " + super.df.format(super.pole) + " i wysokosci " + super.df.format(wysokosc));
    }
}

package com.example.lab1;

import android.util.Log;

public class Kolo extends Figura {
    double srednica;

    public Kolo(double wymiar) {
        super(wymiar);
        this.srednica = wymiar * 2;
        super.pole = wymiar * wymiar * Math.PI;
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KOLO o polu " + super.df.format(super.pole) + " i przekątnej " + super.df.format(srednica));
    }
}

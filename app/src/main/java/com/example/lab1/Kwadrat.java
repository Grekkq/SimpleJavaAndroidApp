package com.example.lab1;

import android.util.Log;

public class Kwadrat extends Figura {
    double przekatna;
    public Kwadrat(double wymiar) {
        super(wymiar);
        this.przekatna = wymiar * Math.sqrt(2);
        super.pole = wymiar * wymiar;
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KWADRAT o polu " + super.df.format(super.pole) + " i przekątnej " + super.df.format(przekatna));
    }
}

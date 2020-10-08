package com.example.lab1;

import android.util.Log;

// Klasa wywodząca się z Figury, musi implementować wszystkie metody klasy bazowej, dostęp do metod z klasy bazowej przez słowo kluczowe super
public class Kolo extends Figura {

    public Kolo(double wymiar) {
        super(wymiar);
        super.setCecha(wymiar * 2);
        super.setPole(wymiar * wymiar * Math.PI);
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KOLO o polu " + super.df.format(super.getPole()) + " i przekątnej " + super.df.format(super.getCecha()));
    }
}

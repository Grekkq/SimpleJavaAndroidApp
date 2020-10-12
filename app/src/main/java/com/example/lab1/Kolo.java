package com.example.lab1;

import android.util.Log;

// Klasa wywodząca się z Figury, musi implementować wszystkie metody klasy bazowej, dostęp do metod z klasy bazowej przez słowo kluczowe super
public class Kolo extends Figura {

    public Kolo(double wymiar) {
        super(wymiar);
        double cecha = wymiar * 2;
        double pole = wymiar * wymiar * Math.PI;
        super.setCecha(cecha);
        super.setPole(pole);
        super.liczbaKol++;
        super.sumaCechKol += cecha;
        super.sumaPolKol += pole;
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KOLO o polu " + super.df.format(super.getPole()) + " i przekątnej " + super.df.format(super.getCecha()));
    }
}

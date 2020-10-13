package com.example.lab1;

import android.util.Log;

// Klasa wywodząca się z Figury, musi implementować wszystkie metody klasy bazowej, dostęp do metod z klasy bazowej przez słowo kluczowe super
public class Kolo extends Figura {

    public Kolo(double wymiar) {
        super(wymiar);
        double cecha = wymiar * 2;
        double pole = wymiar * wymiar * Math.PI;
        setCecha(cecha);
        setPole(pole);
        liczbaKol++;
        sumaCechKol += cecha;
        sumaPolKol += pole;
    }

    @Override
    public Figura skopiuj() {
        return new Kolo(this.getWymiar());
    }

    @Override
    public void zamknij() {
        liczbaKol--;
        sumaCechKol -= getCecha();
        sumaPolKol -= getPole();
    }

    @Override
    public void zmienWymiar(double wymiar) {
        sumaCechKol -= getCecha();
        sumaPolKol -= getPole();
        setWymiar(wymiar);
        setCecha(wymiar * 2);
        setPole(wymiar * wymiar * Math.PI);
        sumaCechKol += getCecha();
        sumaPolKol += getPole();
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KOLO o polu " + super.df.format(super.getPole()) + " i przekątnej " + super.df.format(super.getCecha()));
    }
}

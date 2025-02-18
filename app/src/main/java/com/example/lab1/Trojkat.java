package com.example.lab1;

import android.util.Log;

// Klasa wywodząca się z Figury, musi implementować wszystkie metody klasy bazowej, dostęp do metod z klasy bazowej przez słowo kluczowe super
public class Trojkat extends Figura {

    public Trojkat(double wymiar) {
        super(wymiar);
        double cecha = wymiar * Math.sqrt(3) / 2;
        double pole = wymiar * wymiar * Math.sqrt(3) / 4;
        setCecha(cecha);
        setPole(pole);
        liczbaTrojkatow++;
        sumaCechTrojkatow += cecha;
        sumaPolTrojkatow += pole;
    }

    @Override
    public Figura skopiuj() {
        return new Trojkat(this.getWymiar());
    }

    @Override
    public void zamknij() {
        liczbaTrojkatow--;
        sumaCechTrojkatow -= getCecha();
        sumaPolTrojkatow -= getPole();
    }

    @Override
    public void zmienWymiar(double wymiar) {
        sumaCechTrojkatow -= getCecha();
        sumaPolTrojkatow -= getPole();
        setWymiar(wymiar);
        setCecha(wymiar * Math.sqrt(3) / 2);
        setPole(wymiar * wymiar * Math.sqrt(3) / 4);
        sumaCechTrojkatow += getCecha();
        sumaPolTrojkatow += getPole();
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "TROJKAT o polu " + super.df.format(super.getPole()) + " i wysokosci " + super.df.format(super.getCecha()));
    }
}

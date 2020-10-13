package com.example.lab1;

import android.util.Log;

// Klasa wywodząca się z Figury, musi implementować wszystkie metody klasy bazowej, dostęp do metod z klasy bazowej przez słowo kluczowe super
public class Kwadrat extends Figura {
    public Kwadrat(double wymiar) {
        super(wymiar);
        double cecha = wymiar * Math.sqrt(2);
        double pole = wymiar * wymiar;
        setCecha(cecha);
        setPole(pole);
        liczbaKwadratow++;
        sumaCechKwadratow += cecha;
        sumaPolKwadratow += pole;
    }

    @Override
    public Figura skopiuj() {
        return new Kwadrat(this.getWymiar());
    }

    @Override
    public void zamknij() {
        liczbaKwadratow--;
        sumaCechKwadratow -= getCecha();
        sumaPolKwadratow -= getPole();
    }

    @Override
    public void zmienWymiar(double wymiar) {
        sumaCechKwadratow -= getCecha();
        sumaPolKwadratow -= getPole();
        setWymiar(wymiar);
        setCecha(wymiar * Math.sqrt(2));
        setPole(wymiar * wymiar);
        sumaCechKwadratow += getCecha();
        sumaPolKwadratow += getPole();
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KWADRAT o polu " + super.df.format(super.getPole()) + " i przekątnej " + super.df.format(super.getCecha()));
    }
}

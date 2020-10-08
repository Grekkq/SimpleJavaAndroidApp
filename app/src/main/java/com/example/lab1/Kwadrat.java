package com.example.lab1;

import android.util.Log;

// Klasa wywodząca się z Figury, musi implementować wszystkie metody klasy bazowej, dostęp do metod z klasy bazowej przez słowo kluczowe super
public class Kwadrat extends Figura {
    public Kwadrat(double wymiar) {
        super(wymiar);
        super.setCecha(wymiar * Math.sqrt(2));
        super.setPole(wymiar * wymiar);
    }

    @Override
    public void wypisz() {
        Log.i("FIGURA", "KWADRAT o polu " + super.df.format(super.getPole()) + " i przekątnej " + super.df.format(super.getCecha()));
    }
}

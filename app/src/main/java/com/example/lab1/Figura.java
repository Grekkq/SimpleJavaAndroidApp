package com.example.lab1;

import java.text.DecimalFormat;

public abstract class Figura {
    double wymiar;
    double pole;
    DecimalFormat df = new DecimalFormat("0.000");
    public Figura(double wymiar) {
        this.wymiar = wymiar;
    }
    public abstract void wypisz();
}

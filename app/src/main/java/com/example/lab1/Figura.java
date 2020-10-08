package com.example.lab1;

import java.text.DecimalFormat;

// Klasa bazowa z której korzystają pozostałe figury, abstrakcyjna więc nie można stworzyć jej instancji
public abstract class Figura {
    private final double wymiar;
    private double pole;
    private double cecha;
    DecimalFormat df = new DecimalFormat("0.000");
    public Figura(double wymiar) {
        this.wymiar = wymiar;
    }
    public abstract void wypisz();

    public double getWymiar() {
        return wymiar;
    }

    public double getPole() {
        return pole;
    }

    public void setPole(double pole) {
        this.pole = pole;
    }

    public double getCecha() {
        return cecha;
    }

    public void setCecha(double cecha) {
        this.cecha = cecha;
    }
}

package com.example.lab1;

import java.text.DecimalFormat;

// Klasa bazowa z której korzystają pozostałe figury, abstrakcyjna więc nie można stworzyć jej instancji
public abstract class Figura {
    private final double wymiar;
    private double pole;
    private double cecha;
    public static int liczbaKwadratow = 0;
    public static int liczbaKol = 0;
    public static int liczbaTrojkatow = 0;
    public static double sumaPolKwwadratow = 0;
    public static double sumaPolKol = 0;
    public static double sumaPolTrojkatow = 0;
    public static double sumaCechKwadratow = 0;
    public static double sumaCechKol = 0;
    public static double sumaCechTrojkatow = 0;
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

package com.example.lab1;

import java.text.DecimalFormat;

// Klasa bazowa z której korzystają pozostałe figury, abstrakcyjna więc nie można stworzyć jej instancji
public abstract class Figura {
    private double wymiar;
    private double pole;
    private double cecha;
    protected static int liczbaKwadratow = 0;
    protected static int liczbaKol = 0;
    protected static int liczbaTrojkatow = 0;
    protected static double sumaPolKwadratow = 0;
    protected static double sumaPolKol = 0;
    protected static double sumaPolTrojkatow = 0;
    protected static double sumaCechKwadratow = 0;
    protected static double sumaCechKol = 0;
    protected static double sumaCechTrojkatow = 0;
    final static DecimalFormat df = new DecimalFormat("0.000");
    final static DecimalFormat bigDf = new DecimalFormat("0.0E0");
    final static int duzaLiczba = 999999;

    public Figura(double wymiar) {
        this.wymiar = wymiar;
    }

    public abstract Figura skopiuj();

    public abstract void zamknij();

    public abstract void zmienWymiar(double wymiar);

    public static void wyczyscStatystyki() {
        liczbaKol = 0;
        liczbaKwadratow = 0;
        liczbaTrojkatow = 0;
        sumaCechKol = 0;
        sumaCechKwadratow = 0;
        sumaCechTrojkatow = 0;
        sumaPolKol = 0;
        sumaPolKwadratow = 0;
        sumaPolTrojkatow = 0;
    }

    public static int getLiczbaKwadratow() {
        return liczbaKwadratow;
    }

    public static int getLiczbaKol() {
        return liczbaKol;
    }

    public static int getLiczbaTrojkatow() {
        return liczbaTrojkatow;
    }

    public static double getSumaPolKwadratow() {
        return sumaPolKwadratow;
    }

    public static double getSumaPolKol() {
        return sumaPolKol;
    }

    public static double getSumaPolTrojkatow() {
        return sumaPolTrojkatow;
    }

    public static double getSumaCechKwadratow() {
        return sumaCechKwadratow;
    }

    public static double getSumaCechKol() {
        return sumaCechKol;
    }

    public static double getSumaCechTrojkatow() {
        return sumaCechTrojkatow;
    }

    public static String wypiszLiczbaKwadratow() {
        if (liczbaKwadratow > duzaLiczba)
            return bigDf.format(liczbaKwadratow);
        return String.valueOf(liczbaKwadratow);
    }

    public static String wypiszLiczbaKol() {
        if (liczbaKol > duzaLiczba)
            return bigDf.format(liczbaKol);
        return String.valueOf(liczbaKol);
    }

    public static String wypiszLiczbaTrojkatow() {
        if (liczbaTrojkatow > duzaLiczba)
            return bigDf.format(liczbaTrojkatow);
        return String.valueOf(liczbaTrojkatow);
    }

    public static String wypiszSumaPolKwadratow() {
        if (sumaPolKwadratow > duzaLiczba)
            return bigDf.format(sumaPolKwadratow);
        return df.format(sumaPolKwadratow);
    }

    public static String wypiszSumaPolKol() {
        if (sumaPolKol > duzaLiczba)
            return bigDf.format(sumaPolKol);
        return df.format(sumaPolKol);
    }

    public static String wypiszSumaPolTrojkatow() {
        if (sumaPolTrojkatow > duzaLiczba)
            return bigDf.format(sumaPolTrojkatow);
        return df.format(sumaPolTrojkatow);
    }

    public static String wypiszSumaCechKwadratow() {
        if (sumaCechKwadratow > duzaLiczba)
            return bigDf.format(sumaCechKwadratow);
        return df.format(sumaCechKwadratow);
    }

    public static String wypiszSumaCechKol() {
        if (sumaCechKol > duzaLiczba)
            return bigDf.format(sumaCechKol);
        return df.format(sumaCechKol);
    }

    public static String wypiszSumaCechTrojkatow() {
        if (sumaCechTrojkatow > duzaLiczba)
            return bigDf.format(sumaCechTrojkatow);
        return df.format(sumaCechTrojkatow);
    }

    public abstract void wypisz();

    public double getWymiar() {
        return wymiar;
    }

    protected void setWymiar(double wymiar) {
        this.wymiar = wymiar;
    }

    public double getPole() {
        return pole;
    }

    protected void setPole(double pole) {
        this.pole = pole;
    }

    public double getCecha() {
        return cecha;
    }

    protected void setCecha(double cecha) {
        this.cecha = cecha;
    }
}

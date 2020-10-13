package com.example.lab1;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SharedViewModel extends ViewModel {
    final private Random generator = new Random();
    private List<Figura> listaFigur = new ArrayList<>();
    // Ustawienia generacji
    private int iloscGenerowanychFigur = 10;
    private double dolnaGranicaWymiaru = 0.1;
    private double gornaGranicaPrzedzialu = 1;
    public ActiveFragment aktywnyWidok = ActiveFragment.Main;

    public void duplikuj(int doZduplikowania) {
        listaFigur.add(listaFigur.get(doZduplikowania).skopiuj());
    }

    public void usun(int doUsuniecia) {
        listaFigur.get(doUsuniecia).zamknij();
        listaFigur.remove(doUsuniecia);
    }

    public void edytuj(int doEdycji, double nowyWymiar) {
        Figura figura = listaFigur.get(doEdycji);
        figura.zmienWymiar(nowyWymiar);
    }

    public void dodajFigury() {
        _wygenerujNoweFigury(this.iloscGenerowanychFigur, this.dolnaGranicaWymiaru, this.gornaGranicaPrzedzialu);
    }

    public void dodajFigure() {
        _wygenerujNoweFigury(1, this.dolnaGranicaWymiaru, this.gornaGranicaPrzedzialu);
    }

    public void wygenerujPonownieFigury() {
        int poprzedniaIlosc = listaFigur.size();
        wyczyscFigury();
        _wygenerujNoweFigury(poprzedniaIlosc, this.dolnaGranicaWymiaru, this.gornaGranicaPrzedzialu);
    }

    public void wyczyscFigury() {
        listaFigur.clear();
        Figura.wyczyscStatystyki();
    }

    public List<Figura> pobierzFigury() {
        return this.listaFigur;
    }

    private void _wygenerujNoweFigury(int iloscGenerowanychFigur, double dolnaGranicaWymiaru, double gornaGranicaPrzedzialu) {
        for (int i = 0; i < iloscGenerowanychFigur; i++)
            switch (generator.nextInt(3)) {
                case 0:
                    listaFigur.add(new Kwadrat(ThreadLocalRandom.current().nextDouble(dolnaGranicaWymiaru, gornaGranicaPrzedzialu)));
                    break;
                case 1:
                    listaFigur.add(new Kolo(ThreadLocalRandom.current().nextDouble(dolnaGranicaWymiaru, gornaGranicaPrzedzialu)));
                    break;
                case 2:
                    listaFigur.add(new Trojkat(ThreadLocalRandom.current().nextDouble(dolnaGranicaWymiaru, gornaGranicaPrzedzialu)));
                    break;
            }
    }

    public int getIloscGenerowanychFigur() {
        return iloscGenerowanychFigur;
    }

    public void setIloscGenerowanychFigur(int iloscGenerowanychFigur) {
        this.iloscGenerowanychFigur = iloscGenerowanychFigur;
    }

    public double getDolnaGranicaWymiaru() {
        return dolnaGranicaWymiaru;
    }

    public void setDolnaGranicaWymiaru(double dolnaGranicaWymiaru) {
        if (dolnaGranicaWymiaru <= 0)
            this.dolnaGranicaWymiaru = 0.1;
        else
            this.dolnaGranicaWymiaru = dolnaGranicaWymiaru;
    }

    public double getGornaGranicaPrzedzialu() {
        return gornaGranicaPrzedzialu;
    }

    public void setGornaGranicaPrzedzialu(double gornaGranicaPrzedzialu) {
        this.gornaGranicaPrzedzialu = gornaGranicaPrzedzialu;
    }
}

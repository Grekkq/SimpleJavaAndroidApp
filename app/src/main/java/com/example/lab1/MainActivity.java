package com.example.lab1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Boczny panel
    private DrawerLayout drawer;
    // Lista do której będziemy dodawać nowe figury
    private List<Figura> listaFigur = new LinkedList<>();
    // Inicjalizacja generatora liczb losowych
    private Random generator = new Random();
    // Któy ekran jest aktualnie pokazywany
    private ActiveFragment activeFragment = ActiveFragment.Main;
    // Ustawienia
    int howManyFiguresGenerate = 10;
    double dimensionBottomLimit = 0.1;
    double dimensionTopLimit = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ustawienia paska
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Figures");
        // W teorii te dwie linie powinny mieć taki sam efekt jak ta na dole ale z jakeigoś powodu one powodują crasha po kliknięciu na przycisk
//        drawer = (DrawerLayout) new DrawerLayout(this);
//        drawer.findViewById(R.id.drawer_layout);
        // Znajdź element w widoku
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Dodanie ikonki do otwierania drawera na pasku
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        // Automatyczny obrót żeby dostosować się przy obracaniu ekranu
        toggle.syncState();
        // Generacja figur w sposób losowy
        generateNewFigures(howManyFiguresGenerate, dimensionBottomLimit, dimensionTopLimit);

        // Wypisanie figur przy budowaniu widoku
        for (Figura figura : listaFigur)
            figura.wypisz();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MainFragment(listaFigur)).commit();
    }

    // Akcje wywoływane po naciśnięciu przycisków w panelu bocznym
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment(listaFigur)).commit();
                activeFragment = ActiveFragment.Main;
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment(howManyFiguresGenerate, dimensionBottomLimit, dimensionTopLimit)).commit();
                activeFragment = ActiveFragment.Settings;
                break;
            case R.id.nav_statistics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatisticsFragment(listaFigur.get(0))).commit();
                activeFragment = ActiveFragment.Statistics;
                break;
            case R.id.nav_add:
                generateNewFigures(howManyFiguresGenerate, dimensionBottomLimit, dimensionTopLimit);
                if (activeFragment == ActiveFragment.Main)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new MainFragment(listaFigur)).commit();
                if (activeFragment == ActiveFragment.Statistics)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new StatisticsFragment(listaFigur.get(0 ))).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Zamknij drawer zamiast zamykać aplikacje
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    void generateNewFigures(int howMany, double bottomLimit, double topLimit) {
        for (int i = 0; i < howMany; i++)
            switch (generator.nextInt(3)) {
                case 0:
                    listaFigur.add(new Kwadrat(ThreadLocalRandom.current().nextDouble(bottomLimit, topLimit)));
                    break;
                case 1:
                    listaFigur.add(new Kolo(ThreadLocalRandom.current().nextDouble(bottomLimit, topLimit)));
                    break;
                case 2:
                    listaFigur.add(new Trojkat(ThreadLocalRandom.current().nextDouble(bottomLimit, topLimit)));
                    break;
            }
    }

    public void dispatchSettings(int count, double bottom, double top) {
        this.howManyFiguresGenerate = count;
        if (bottom <= 0)
            this.dimensionBottomLimit = 0.1;
        else
            this.dimensionBottomLimit = bottom;
        if (top < bottom)
            this.dimensionTopLimit = bottom + 1;
        else
            this.dimensionTopLimit = top;
    }

}
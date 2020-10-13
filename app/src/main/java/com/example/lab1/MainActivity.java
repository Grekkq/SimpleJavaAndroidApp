package com.example.lab1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Boczny panel
    private DrawerLayout drawer;
    // Któy ekran jest aktualnie pokazywany
//    private ActiveFragment activeFragment = ActiveFragment.Main;
    // Klasa z danymi dostępnymi pomiędzy fragmentami
    private SharedViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ustawienia appBar'a
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Inicjazlzuje klasę która przetrwa obrót ekranu :D
        model = new ViewModelProvider(this).get(SharedViewModel.class);
        // W teorii te dwie linie powinny mieć taki sam efekt jak ta na dole ale z jakeigoś powodu one powodują crasha po kliknięciu na przycisk
//        drawer = (DrawerLayout) new DrawerLayout(this);
//        drawer.findViewById(R.id.drawer_layout);
        // Znajdź element w widoku
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        // Dodanie ikonki do otwierania drawera na pasku
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        // Automatyczny obrót żeby dostosować się przy obracaniu ekranu
        toggle.syncState();

        switch (model.aktywnyWidok) {
            case Main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Lista figur");
                break;
            case Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Ustawienia");
                break;
            case Statistics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatisticsFragment()).commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Statystyki");
                break;
            case Info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InfoFragment()).commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Informacje o programie");
                break;
        }
    }

    // Akcje wywoływane po naciśnięciu przycisków w panelu bocznym
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int selectedItem = menuItem.getItemId();
        switch (selectedItem) {
            case R.id.nav_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).commit();
                model.aktywnyWidok = ActiveFragment.Main;
                Objects.requireNonNull(getSupportActionBar()).setTitle("Lista figur");
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                model.aktywnyWidok = ActiveFragment.Settings;
                Objects.requireNonNull(getSupportActionBar()).setTitle("Ustawienia");
                break;
            case R.id.nav_statistics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatisticsFragment()).commit();
                model.aktywnyWidok = ActiveFragment.Statistics;
                Objects.requireNonNull(getSupportActionBar()).setTitle("Statystyki");
                break;
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InfoFragment()).commit();
                model.aktywnyWidok = ActiveFragment.Info;
                Objects.requireNonNull(getSupportActionBar()).setTitle("Informacje o programie");
                break;
            case R.id.nav_add:
                model.dodajFigury();
                break;
            case R.id.nav_add_single:
                model.dodajFigure();
                break;
            case R.id.nav_delete_all:
                model.wyczyscFigury();
                break;
            case R.id.nav_delete_and_generate:
                model.wygenerujPonownieFigury();
                break;
        }
        // Jeśli był update listy zaktualizuj widok
        ArrayList<Integer> arr = new ArrayList<Integer>(4);
        arr.add(R.id.nav_add);
        arr.add(R.id.nav_add_single);
        arr.add(R.id.nav_delete_all);
        arr.add(R.id.nav_delete_and_generate);
        if (arr.contains(selectedItem)) {
            if (model.aktywnyWidok == ActiveFragment.Main)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).commit();
            if (model.aktywnyWidok == ActiveFragment.Statistics)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatisticsFragment()).commit();
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

}
package com.example.lab1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Figura> listaFigur = new LinkedList<>();
    Random generator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        for (int i = 0; i < 10; i++)
            switch(generator.nextInt(2)) {
                case 0:
                    listaFigur.add(new Kwadrat(generator.nextDouble()));
                    break;
                case 1:
                    listaFigur.add(new Kolo(generator.nextDouble()));
                    break;
                case 2:
                    listaFigur.add(new Trojkat(generator.nextDouble()));
                    break;
            }


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                for (Figura figura : listaFigur) {
                    figura.wypisz();
                }
            }
        });
    }
}
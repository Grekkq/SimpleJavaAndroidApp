package com.example.lab1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Lista do której będziemy dodawać nowe figury
    List<Figura> listaFigur = new LinkedList<>();
    // Inicjalizacja generatora liczb losowych
    Random generator = new Random();

    // Generowanie nowego wiersza dla głównej listy
    private LinearLayout createNewItemInMainList(Figura figura) {
        // Inicjalizacja elementów interfejsu
        DecimalFormat df = new DecimalFormat("0.000");
        LinearLayout ll = new LinearLayout(this);
        ImageView imageWidget = new ImageView(this);
        TextView areaWidget = new TextView(this);
        TextView featureWidget = new TextView(this);

        // textAlign = center
        areaWidget.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        featureWidget.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        // Jakby coś poszło nie tak to wyświetl domyślne wartości a nie crash aplikacji
        String featureText = "placeholdere";
        int image = R.drawable.square;

        // Dopasowanie elementów dynamicznie do typu figury
        if (figura instanceof Kwadrat) {
            featureText = "Przekątna\n" + df.format(figura.getCecha());
            image = R.drawable.square;
        } else if (figura instanceof Kolo) {
            featureText = "Średnica\n" + df.format(figura.getCecha());
            image = R.drawable.circle;
        } else if (figura instanceof Trojkat) {
            featureText = "Wysokość\n" + df.format(figura.getCecha());
            image = R.drawable.triangle;
        }

        // Ustawienie zawartości elementów
        imageWidget.setImageResource(image);
        featureWidget.setText(featureText);
        areaWidget.setText(df.format(figura.getPole()));

        // Połączenie elementó w jeden wiersz
        ll.addView(imageWidget, (new LinearLayout.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT,0.8f)));
        ll.addView(areaWidget, (new LinearLayout.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,0.8f)));
        ll.addView(featureWidget, (new LinearLayout.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,0.8f)));

        return ll;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Figures");

        // Generacja figur w sposób losowy
        for (int i = 0; i < 10; i++)
            switch(generator.nextInt(3)) {
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

        // Wypisanie figur przy budowaniu widoku
        for (Figura figura : listaFigur)
            figura.wypisz();

        FloatingActionButton fab = findViewById(R.id.fab);
        final LinearLayout mainList = findViewById(R.id.LinearLayoutMainList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Figury wypisane", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, 0, 10);


                // Wypisanie wcześniej wygenerowanych figur po naciśnięciu na przycisk
                for (Figura figura : listaFigur) {
                    figura.wypisz();
                    // Stwórz nowy wiersz do widoku z obecnej figury i dodaj go do głównej (wyświetlanej) listy
                    mainList.addView(createNewItemInMainList(figura), layoutParams);
                }
            }
        });
    }
}
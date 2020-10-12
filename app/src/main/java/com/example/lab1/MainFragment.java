package com.example.lab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.List;

public class MainFragment extends Fragment {
    final List<Figura> listaFigur;

    public MainFragment(List<Figura> listaFigur) {
        this.listaFigur = listaFigur;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout mainList = view.findViewById(R.id.LinearLayoutMainList);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 10);
        for (Figura figura : listaFigur)
            mainList.addView(createNewItemInMainList(figura), layoutParams);

    }

    // Generowanie nowego wiersza dla głównej listy
    private LinearLayout createNewItemInMainList(Figura figura) {
        // Inicjalizacja elementów interfejsu
        DecimalFormat df = new DecimalFormat("0.000");
        LinearLayout ll = new LinearLayout(getContext());
        ImageView imageWidget = new ImageView(getContext());
        TextView areaWidget = new TextView(getContext());
        TextView featureWidget = new TextView(getContext());

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
        ll.addView(imageWidget, (new LinearLayout.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.8f)));
        ll.addView(areaWidget, (new LinearLayout.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)));
        ll.addView(featureWidget, (new LinearLayout.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)));

        return ll;
    }
}

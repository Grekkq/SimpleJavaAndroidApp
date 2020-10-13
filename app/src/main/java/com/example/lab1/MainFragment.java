package com.example.lab1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment {

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
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        int i = 0;
        for (Figura figura : model.pobierzFigury()) {
            mainList.addView(createNewItemInMainList(figura, i), layoutParams);
            i++;
        }

    }

    // Generowanie nowego wiersza dla głównej listy
    private LinearLayout createNewItemInMainList(final Figura figura, int id) {
        final int listId = id;
        final SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // Inicjalizacja elementów interfejsu
        DecimalFormat df = new DecimalFormat("0.000");
        LinearLayout ll = new LinearLayout(getContext());
        ImageView imageWidget = new ImageView(getContext());
        TextView areaWidget = new TextView(getContext());
        TextView featureWidget = new TextView(getContext());
        // Maluje na pomarańczowo kliknięty wiersz
        ll.setClickable(true);
        ll.setBackground(getResources().getDrawable(android.R.drawable.list_selector_background));
        // Akcja po kliknięciu
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    FragmentTransaction reloadFragment = getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new MainFragment());
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_duplicate:
                                model.duplikuj(listId);
                                reloadFragment.commit();
                                break;
                            case R.id.action_delete:
                                model.usun(listId);
                                reloadFragment.commit();
                                break;
                            case R.id.action_edit:
                                _showEditAlert(figura, model, listId, reloadFragment);
                                break;
                        }
                        return true;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.item_popup, popup.getMenu());
                popup.show();

                Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
            }
        });


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

    private void _showEditAlert(Figura figura, final SharedViewModel model, final int listId, final FragmentTransaction reloadFragment) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Zmień wymiar");
        alert.setMessage("Podaj nowy wymiar");
        // Set an EditText view to get user input
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setText(String.valueOf(figura.getWymiar()));
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                model.edytuj(listId, Double.parseDouble(input.getText().toString()));
                reloadFragment.commit();
            }
        });
        alert.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }
}

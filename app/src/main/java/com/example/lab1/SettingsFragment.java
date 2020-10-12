package com.example.lab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class SettingsFragment extends Fragment {
    final int countOfFigures;
    final double bottomLimit;
    final double topLimit;
    EditText countInput;
    EditText bottomLimitInput;
    EditText topLimitInput;

    public SettingsFragment(int countOfFigures, double bottomLimit, double topLimit) {
        this.countOfFigures = countOfFigures;
        this.bottomLimit = bottomLimit;
        this.topLimit = topLimit;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countInput = (EditText) view.findViewById(R.id.figuresCountInput);
        bottomLimitInput = (EditText) view.findViewById(R.id.bottomLimitInput);
        topLimitInput = (EditText) view.findViewById(R.id.upperLimitInput);
        countInput.setText(String.valueOf(countOfFigures));
        bottomLimitInput.setText(String.valueOf(bottomLimit));
        topLimitInput.setText(String.valueOf(topLimit));
        Button clickButton = (Button) view.findViewById(R.id.saveSettingsButton);
        // Zapisz opcje po kliknięciu przycisku
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) Objects.requireNonNull(getActivity(), "Nie udało się znaleźć klasy MainActivity, nie można zapisać ustawień 😕"))
                        .dispatchSettings(Integer.parseInt(countInput.getText().toString()), Double.parseDouble(bottomLimitInput.getText().toString()), Double.parseDouble(topLimitInput.getText().toString()));
                Snackbar.make(view, "Zmiany zapisane", Snackbar.LENGTH_LONG).show();
            }
        });


    }

}

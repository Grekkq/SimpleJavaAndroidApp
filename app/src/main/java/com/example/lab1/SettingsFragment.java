package com.example.lab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment {
    EditText countInput;
    EditText bottomLimitInput;
    EditText topLimitInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        countInput = (EditText) view.findViewById(R.id.figuresCountInput);
        bottomLimitInput = (EditText) view.findViewById(R.id.bottomLimitInput);
        topLimitInput = (EditText) view.findViewById(R.id.upperLimitInput);
        Button clickButton = (Button) view.findViewById(R.id.saveSettingsButton);

        // Ustaw obecne wartości na start
        countInput.setText(String.valueOf(model.getIloscGenerowanychFigur()));
        bottomLimitInput.setText(String.valueOf(model.getDolnaGranicaWymiaru()));
        topLimitInput.setText(String.valueOf(model.getGornaGranicaPrzedzialu()));

        // Zapisz opcje po kliknięciu przycisku
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                try {
                    model.setIloscGenerowanychFigur(Integer.parseInt(countInput.getText().toString()));
                    model.setDolnaGranicaWymiaru(Double.parseDouble(bottomLimitInput.getText().toString()));
                    model.setGornaGranicaPrzedzialu(Double.parseDouble(topLimitInput.getText().toString()));
                    message = "Zmiany zapisane";
                } catch (final NumberFormatException e) {
                    message = "Upewnij się że wypełniłeś wszyzstkie pola";
                }
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });


    }

}

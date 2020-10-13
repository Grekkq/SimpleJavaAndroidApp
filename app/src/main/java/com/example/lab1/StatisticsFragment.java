package com.example.lab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class StatisticsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat bigDf = new DecimalFormat("0.0E0");
        // Ilość
        TextView squareCountText = (TextView) view.findViewById(R.id.squareCountText);
        squareCountText.setText(Figura.wypiszLiczbaKwadratow());
        TextView circleCountText = (TextView) view.findViewById(R.id.circleCountText);
        circleCountText.setText(Figura.wypiszLiczbaKol());
        TextView triangleCountText = (TextView) view.findViewById(R.id.triangleCountText);
        triangleCountText.setText(Figura.wypiszLiczbaTrojkatow());
        // Pola
        TextView squareAreaText = (TextView) view.findViewById(R.id.squareAreaText);
        squareAreaText.setText(Figura.wypiszSumaPolKwadratow());
        TextView circleAreaText = (TextView) view.findViewById(R.id.circleAreaText);
        circleAreaText.setText(Figura.wypiszSumaPolKol());
        TextView triangleAreaText = (TextView) view.findViewById(R.id.triangleAreaText);
        triangleAreaText.setText(Figura.wypiszSumaPolTrojkatow());
        // Cechy
        TextView squareFeatureText = (TextView) view.findViewById(R.id.squareFeatureText);
        squareFeatureText.setText(Figura.wypiszSumaCechKwadratow());
        TextView circleFeatureText = (TextView) view.findViewById(R.id.circleFeatureText);
        circleFeatureText.setText(Figura.wypiszSumaCechKol());
        TextView triangleFeatureText = (TextView) view.findViewById(R.id.triangleFeatureText);
        triangleFeatureText.setText(Figura.wypiszSumaCechTrojkatow());
    }
}

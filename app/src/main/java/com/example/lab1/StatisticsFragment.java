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
        // Ilość
        TextView squareCountText = (TextView) view.findViewById(R.id.squareCountText);
        squareCountText.setText(String.valueOf(Figura.liczbaKwadratow));
        TextView circleCountText = (TextView) view.findViewById(R.id.circleCountText);
        circleCountText.setText(String.valueOf(Figura.liczbaKol));
        TextView triangleCountText = (TextView) view.findViewById(R.id.triangleCountText);
        triangleCountText.setText(String.valueOf(Figura.liczbaTrojkatow));
        // Pola
        TextView squareAreaText = (TextView) view.findViewById(R.id.squareAreaText);
        squareAreaText.setText(df.format(Figura.sumaPolKwadratow));
        TextView circleAreaText = (TextView) view.findViewById(R.id.circleAreaText);
        circleAreaText.setText(df.format(Figura.sumaPolKol));
        TextView triangleAreaText = (TextView) view.findViewById(R.id.triangleAreaText);
        triangleAreaText.setText(df.format(Figura.sumaPolTrojkatow));
        // Cechy
        TextView squareFeatureText = (TextView) view.findViewById(R.id.squareFeatureText);
        squareFeatureText.setText(df.format(Figura.sumaCechKwadratow));
        TextView circleFeatureText = (TextView) view.findViewById(R.id.circleFeatureText);
        circleFeatureText.setText(df.format(Figura.sumaCechKol));
        TextView triangleFeatureText = (TextView) view.findViewById(R.id.triangleFeatureText);
        triangleFeatureText.setText(df.format(Figura.sumaCechTrojkatow));
    }
}

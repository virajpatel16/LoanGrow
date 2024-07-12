package com.example.loangrow.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class Margin_fragment extends Fragment {

    ImageView backmargin;
    private EditText costMarginEditText, revenueMarginEditText;
    private TextView resultMarginTextView, resultProfitTextView;


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_margin_fragment, container, false);
        backmargin = view.findViewById(R.id.backmargin);
        costMarginEditText = view.findViewById(R.id.CostmarginEditText);
        revenueMarginEditText = view.findViewById(R.id.RevenuemarginEdittext);
        resultMarginTextView = view.findViewById(R.id.answermargin);
        resultProfitTextView = view.findViewById(R.id.answerprofit);
        ExtendedFloatingActionButton calculateMarginButton = view.findViewById(R.id.calculatebuttonmargin);
        calculateMarginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMarginAndProfit();
                keybord(v);
            }
        });

        backmargin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requireActivity().finish();

            }
        });

        ExtendedFloatingActionButton resetMarginButton = view.findViewById(R.id.resetbuttonmargin);
        resetMarginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMarginFields();
            }
        });
        return view;
    }

    private void calculateMarginAndProfit() {
        String costMarginStr = costMarginEditText.getText().toString().trim();
        String revenueMarginStr = revenueMarginEditText.getText().toString().trim();

        if (costMarginStr.isEmpty() || revenueMarginStr.isEmpty()) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double costMargin = Double.parseDouble(costMarginStr);
        double revenueMargin = Double.parseDouble(revenueMarginStr);

        // Calculate margin percentage
        double margin = ((revenueMargin - costMargin) / revenueMargin) * 100;

        // Calculate profit
        double profit = revenueMargin - costMargin;

        // Display the result
        resultMarginTextView.setText(String.format("%.2f", margin));
        resultProfitTextView.setText(String.format("%.2f", profit));
    }

    private void resetMarginFields() {
        costMarginEditText.setText("");
        revenueMarginEditText.setText("");
        resultMarginTextView.setText("");
        resultProfitTextView.setText("");
        Toast.makeText(getContext(), "Value is 0", Toast.LENGTH_SHORT).show();

    }
    private void keybord(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
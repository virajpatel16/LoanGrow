package com.example.loangrow.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class Grossprofit_fragment extends Fragment {

    ImageView backGrossprofit;
    TextView answergross, answergrossmarkup;
    private EditText sellingPriceEditText, costPriceEditText;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grossprofit_fragment, container, false);
        backGrossprofit = view.findViewById(R.id.backGrossprofit);
        sellingPriceEditText = view.findViewById(R.id.SellingpriceEditText);
        costPriceEditText = view.findViewById(R.id.CostpriceEdittext);
        ExtendedFloatingActionButton calculateButton = view.findViewById(R.id.calculateButtonGross_profit);
        answergross = view.findViewById(R.id.answergross);
        answergrossmarkup = view.findViewById(R.id.answergrossmarkup);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGrossProfit();
                hideKeyboard(v);
            }

        });
        ExtendedFloatingActionButton resetButton = view.findViewById(R.id.redsetButtonGross_profit);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        backGrossprofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
        return view;
    }

    private void calculateGrossProfit() {
        String sellingPriceStr = sellingPriceEditText.getText().toString().trim();
        String costPriceStr = costPriceEditText.getText().toString().trim();


        if (TextUtils.isEmpty(sellingPriceStr)) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(costPriceStr)) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double sellingPrice = Double.parseDouble(sellingPriceStr);
            double costPrice = Double.parseDouble(costPriceStr);

            // Calculate gross profit
            double grossProfit = sellingPrice - costPrice;
            answergross.setText(String.valueOf(grossProfit));

            // Calculate gross profit markup
            if (costPrice != 0) {
                double markup = (grossProfit / costPrice) * 100;
                answergrossmarkup.setText(String.format("%.2f%%", markup));
            } else {
                // Handle division by zero (costPrice is zero)
                answergrossmarkup.setText("N/A");
            }

            // Display a toast message with the calculated gross profit
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric values)
            Toast.makeText(requireContext(), "Invalid input. Please enter numeric values.", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        sellingPriceEditText.setText("");
        costPriceEditText.setText("");
        answergrossmarkup.setText("");
        answergross.setText("");
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
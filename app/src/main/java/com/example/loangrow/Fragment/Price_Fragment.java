package com.example.loangrow.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class Price_Fragment extends Fragment {


    ImageView backprice;
    private EditText costEditText, grossMarginEditText;
    private TextView resultPriceTextView, resultMarkupTextView;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_price_, container, false);

        backprice=view.findViewById(R.id.backprice);
        costEditText = view.findViewById(R.id.CostEditText);
        grossMarginEditText = view.findViewById(R.id.GrossmarginEdittext);
        resultPriceTextView = view.findViewById(R.id.answerprice);
        resultMarkupTextView = view.findViewById(R.id.answerpricemarkup);
        ExtendedFloatingActionButton calculateButton = view.findViewById(R.id.calculatorbuttonprice);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePrice();
                keybord(v);
            }
        });

        ExtendedFloatingActionButton resetButton = view.findViewById(R.id.resetbuttonprice);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                     
            }
        });

        backprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                requireActivity().finish();

            }
        });

        return view;

    }

    private void calculatePrice() {
        String costStr = costEditText.getText().toString().trim();
        String grossMarginStr = grossMarginEditText.getText().toString().trim();

        if (costStr.isEmpty()||grossMarginStr.isEmpty()) {
            Toast.makeText(requireActivity(), "price is empty", Toast.LENGTH_SHORT).show();

            return;
        }

        if (TextUtils.isEmpty(grossMarginStr)) {
            grossMarginEditText.setError("Enter Rate of Return");
            grossMarginEditText.requestFocus();
            return;
        }

        double cost = Double.parseDouble(costStr);
        double grossMargin = Double.parseDouble(grossMarginStr);

        // Calculate price
        double price = cost / (1 - grossMargin / 100);
        resultPriceTextView.setText(String.format("%.2f", price));

        // Calculate markup
        double markup = (price - cost) / cost * 100;
        resultMarkupTextView.setText(String.format("%.2f%%", markup));
    }

    private void resetFields() {
        costEditText.setText("");
        grossMarginEditText.setText("");
        resultPriceTextView.setText("");
        resultMarkupTextView.setText("");

        Toast.makeText(getContext(), "Value is 0", Toast.LENGTH_SHORT).show();
    }
    private void keybord(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
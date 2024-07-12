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


public class Markup_Fragment extends Fragment {


    ImageView backmarkup;
    private EditText costMarkupEditText, revenueMarkupEditText;
    private TextView resultMarkupTextView;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_markup_, container, false);

        backmarkup=view.findViewById(R.id.backmarkup);
        costMarkupEditText =view. findViewById(R.id.CostmarkupEditText);
        revenueMarkupEditText = view.findViewById(R.id.RevenuemarkupEdittext);
        resultMarkupTextView = view.findViewById(R.id.answermarkup);

        ExtendedFloatingActionButton calculateMarkupButton = view.findViewById(R.id.calculatemarkup);
        calculateMarkupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMarkup();
                keybord(v);

            }
        });
        ExtendedFloatingActionButton resetMarkupButton = view.findViewById(R.id.resetbuttonmarkup);
        resetMarkupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMarkupFields();

            }
        });
        backmarkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
    return view;
    }
    private void calculateMarkup() {
        String costMarkupStr = costMarkupEditText.getText().toString().trim();
        String revenueMarkupStr = revenueMarkupEditText.getText().toString().trim();

        if (TextUtils.isEmpty(costMarkupStr)) {

            Toast.makeText(requireActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(revenueMarkupStr)) {
            Toast.makeText(requireActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double costMarkup = Double.parseDouble(costMarkupStr);
        double revenueMarkup = Double.parseDouble(revenueMarkupStr);

// Calculate markup percentage
        double markup = Math.abs((revenueMarkup - costMarkup) / costMarkup) * 100;

// Display the result
        resultMarkupTextView.setText(String.format("%.2f", markup));

    }

    private void resetMarkupFields() {
        costMarkupEditText.setText("");
        revenueMarkupEditText.setText("");
        resultMarkupTextView.setText("");
    }
    private void keybord(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
package com.example.loangrow.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class DiscountFragment extends Fragment {

    EditText editTextOriginalPrice, editTextDiscountPercentage;
    TextView edt_monthly_emi, edt_t_interest;
    ExtendedFloatingActionButton btn_emi_calculate, btn_emi_reset;

    ImageView backdiscountimgbtn;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_discount, container, false);

        editTextOriginalPrice = view.findViewById(R.id.intialacccountEditText);
        editTextDiscountPercentage = view.findViewById(R.id.discountcalculateedittext);
        edt_monthly_emi = view.findViewById(R.id.edt_monthly_emi);
        edt_t_interest = view.findViewById(R.id.edt_t_interest);
        btn_emi_calculate = view.findViewById(R.id.btn_emi_calculate);
        btn_emi_reset = view.findViewById(R.id.btn_emi_reset);
        backdiscountimgbtn=view.findViewById(R.id.backdiscountimgbtn);
        backdiscountimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
        btn_emi_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDiscount();
                hideKeyboard(v);

            }
        });

        btn_emi_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset values
                editTextOriginalPrice.setText("0");
                editTextDiscountPercentage.setText("0");
                edt_monthly_emi.setText("");
                edt_t_interest.setText("");

            }
        });

   return view;


    }

    private void calculateDiscount() {
        try {
            double originalPrice = Double.parseDouble(editTextOriginalPrice.getText().toString());
            double discountPercentage = Double.parseDouble(editTextDiscountPercentage.getText().toString());

            // Calculate discount
            double discountValue = originalPrice * (discountPercentage / 100);
            double discountedPrice = originalPrice - discountValue;

            // Set calculated values to views
            edt_monthly_emi.setText(String.valueOf(discountedPrice));
            edt_t_interest.setText(String.valueOf(discountValue));
        } catch (NumberFormatException e) {
            // Handle invalid input
            edt_monthly_emi.setText("Invalid input");
            edt_t_interest.setText("Invalid input");
        }
    }
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
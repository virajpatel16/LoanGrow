package com.example.loangrow.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DecimalFormat;


public class Recurring_Deposite extends Fragment {

    private EditText edtPrincipalAmount, edtInterestRate, edtTimePeriod;
    private TextView txtPrincipalAmount, txtEstReturns, txtTotalInterest;
ImageView back_rd;
    private ExtendedFloatingActionButton btnCalculate, btnReset;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_recurring__deposite, container, false);

        // Initialize views
        edtPrincipalAmount = view.findViewById(R.id.edt_deposite_amount);
        edtInterestRate = view.findViewById(R.id.edt_interest_rate);
        edtTimePeriod = view.findViewById(R.id.edt_time_period);

        txtPrincipalAmount = view.findViewById(R.id.txt_principal_amount_rd);
        txtEstReturns = view.findViewById(R.id.txt_est_returns_rd);
        txtTotalInterest = view.findViewById(R.id.txt_total_interest_rd);

        btnCalculate = view.findViewById(R.id.btn_calculate);
        btnReset = view.findViewById(R.id.btn_reset);
        back_rd=view.findViewById(R.id.back_rd);


        back_rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });

        // Calculate button click listener
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRD();
                hideKeyboard(v);
            }
        });

        // Reset button click listener
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    return view;
    }
    private void calculateRD() {
        // Validate input
        String principalStr = edtPrincipalAmount.getText().toString().trim();
        String interestRateStr = edtInterestRate.getText().toString().trim();
        String timePeriodStr = edtTimePeriod.getText().toString().trim();

        if (TextUtils.isEmpty(principalStr) || TextUtils.isEmpty(interestRateStr) || TextUtils.isEmpty(timePeriodStr)) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse input values
        double principal = Double.parseDouble(principalStr);
        double interestRate = Double.parseDouble(interestRateStr);
        double timePeriod = Double.parseDouble(timePeriodStr);

        // Calculate RD details
        double totalAmount = calculateTotalAmount(principal, interestRate, timePeriod);
        double totalInterest = totalAmount - principal;

        // Format values
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedPrincipal = decimalFormat.format(principal);
        String formattedEstReturns = decimalFormat.format(totalAmount);
        String formattedTotalInterest = decimalFormat.format(totalInterest);

        // Display calculated values
        txtPrincipalAmount.setText(formattedPrincipal);
        txtEstReturns.setText(formattedEstReturns);
        txtTotalInterest.setText(formattedTotalInterest);
    }

    private double calculateTotalAmount(double principal, double interestRate, double timePeriod) {
        // Calculate total amount using the RD formula
        double monthlyInterestRate = interestRate / (12 * 100); // Monthly interest rate
        double totalMonths = timePeriod * 12; // Total number of months
        double totalAmount = 0;

        for (int i = 1; i <= totalMonths; i++) {
            totalAmount += principal + (totalAmount * monthlyInterestRate);
        }

        return totalAmount;
    }

    private void resetFields() {
        // Reset all input fields and text views
        edtPrincipalAmount.setText("");
        edtInterestRate.setText("");
        edtTimePeriod.setText("");

        txtPrincipalAmount.setText("");
        txtEstReturns.setText("");
        txtTotalInterest.setText("");
    }
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
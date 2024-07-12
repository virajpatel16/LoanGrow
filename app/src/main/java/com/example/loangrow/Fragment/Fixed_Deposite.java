package com.example.loangrow.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class Fixed_Deposite extends Fragment {
    private EditText edtDepositAmount, edtInterestRate, edtTimePeriod;
    private TextView txtInvestmentAmount, txtEstReturns, txtTotalInterestFd;
    private ExtendedFloatingActionButton btnCalculate, btnReset;
    private RadioGroup radioGroup, radioGroup2, radioGroup1;
    ImageView back_fd;
    private RadioButton radioHalfYearly, radioQuarterly, radioYearly;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fixed__deposite, container, false);
        edtDepositAmount = view.findViewById(R.id.edt_deposite_amount);
        edtInterestRate = view.findViewById(R.id.edt_interest_rate);
        edtTimePeriod = view.findViewById(R.id.edt_time_period);
        txtInvestmentAmount = view.findViewById(R.id.txt_investment_amount);
        txtEstReturns = view.findViewById(R.id.txt_est_returns);
        txtTotalInterestFd = view.findViewById(R.id.txt_total_interest_fd);
        btnCalculate = view.findViewById(R.id.btn_calculate);
        btnReset = view.findViewById(R.id.btn_reset);
        radioGroup = view.findViewById(R.id.radio_group);
        radioGroup1 = view.findViewById(R.id.radio_group2);
        radioGroup2 = view.findViewById(R.id.radio_group3);
        radioHalfYearly = view.findViewById(R.id.radio_half_yearly);
        radioQuarterly = view.findViewById(R.id.radio_quarterly);
        radioYearly = view.findViewById(R.id.radio_yearly);
        back_fd = view.findViewById(R.id.back_fd);

        back_fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });

        btnCalculate.setOnClickListener(v -> {
            hideKeyboard(v);
            calculateFixedDeposit();
        });

        btnReset.setOnClickListener(v -> {
            hideKeyboard(v);
            resetFields();
        });


        return view;
    }

    private void calculateFixedDeposit() {
        String depositStr = edtDepositAmount.getText().toString();
        String rateStr = edtInterestRate.getText().toString();
        String timeStr = edtTimePeriod.getText().toString();

        if (depositStr.isEmpty() || rateStr.isEmpty() || timeStr.isEmpty()) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double depositAmount = Double.parseDouble(depositStr);
        double interestRate = Double.parseDouble(rateStr);
        int timePeriod = Integer.parseInt(timeStr);

        double compoundingFrequency = 0;

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.radio_half_yearly) {
            compoundingFrequency = 2;
        } else if (selectedRadioButtonId == R.id.radio_quarterly) {
            compoundingFrequency = 4;
        } else if (selectedRadioButtonId == R.id.radio_yearly) {
            compoundingFrequency = 1;
        } else {
            Toast.makeText(getContext(), "Please select compounding frequency", Toast.LENGTH_SHORT).show();
            return;
        }
        double rate = interestRate / (100 * compoundingFrequency);
        double base = 1 + rate;
        double exponent = compoundingFrequency * timePeriod;
        double maturityAmount = depositAmount * Math.pow(base, exponent);
        double interestEarned = maturityAmount - depositAmount;

        txtInvestmentAmount.setText(String.format("%.2f", depositAmount));
        txtEstReturns.setText(String.format("%.2f", maturityAmount));
        txtTotalInterestFd.setText(String.format("%.2f", interestEarned));
    }

    private void resetFields() {
        edtDepositAmount.setText("");
        edtInterestRate.setText("");
        edtTimePeriod.setText("");
        txtInvestmentAmount.setText("");
        txtEstReturns.setText("");
        txtTotalInterestFd.setText("");
        radioGroup.clearCheck();
        radioGroup1.clearCheck();
        radioGroup2.clearCheck();
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
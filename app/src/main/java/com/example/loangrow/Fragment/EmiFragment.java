package com.example.loangrow.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class EmiFragment extends Fragment {

ImageView back_emi;
    private EditText edtInitialAmount, edtInterest, edtTenure;
    private TextView txtMonthlyEmi, txtTotalAmount, txtTotalInterest;
    private ExtendedFloatingActionButton btnCalculate, btnReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_emi, container, false);
        edtInitialAmount = view.findViewById(R.id.edt_intial_amount);
        edtInterest = view.findViewById(R.id.edt_interest);
        back_emi=view.findViewById(R.id.back_emi);
        edtTenure = view.findViewById(R.id.edt_tenure);
        txtMonthlyEmi = view.findViewById(R.id.txt_monthly_emi);
        txtTotalAmount = view.findViewById(R.id.txt_total_amount);
        txtTotalInterest = view.findViewById(R.id.txt_total_interest);
        btnCalculate = view.findViewById(R.id.btn_calculate);
        btnReset = view.findViewById(R.id.btn_reset);

        back_emi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
        btnCalculate.setOnClickListener(v -> {
            hideKeyboard(v);
            calculateEmi();
        });
        btnReset.setOnClickListener(v -> resetFields());
    return view;
    }
    private void calculateEmi() {
        String principalStr = edtInitialAmount.getText().toString();
        String rateStr = edtInterest.getText().toString();
        String tenureStr = edtTenure.getText().toString();

        if (principalStr.isEmpty() || rateStr.isEmpty() || tenureStr.isEmpty()) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double principal = Double.parseDouble(principalStr);
        double rate = Double.parseDouble(rateStr) / 12 / 100;
        int tenure = Integer.parseInt(tenureStr) * 12;

        double emi = (principal * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
        double totalAmount = emi * tenure;
        double totalInterest = totalAmount - principal;

        txtMonthlyEmi.setText(String.format("%.2f", emi));
        txtTotalAmount.setText(String.format("%.2f", totalAmount));
        txtTotalInterest.setText(String.format("%.2f", totalInterest));
    }

    private void resetFields() {
        edtInitialAmount.setText("");
        edtInterest.setText("");
        edtTenure.setText("");
        txtMonthlyEmi.setText("");
        txtTotalAmount.setText("");
        txtTotalInterest.setText("");
        Toast.makeText(requireActivity(), "values is 0", Toast.LENGTH_SHORT).show();
    }
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
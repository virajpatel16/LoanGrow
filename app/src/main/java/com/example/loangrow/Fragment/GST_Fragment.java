package com.example.loangrow.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GST_Fragment extends Fragment {
    private Spinner vatSpinner;
    ImageView back_gst;
    private final ArrayList<String> name = new ArrayList<>();
    private EditText edtInitialAmount, edtInterest;

    private TextView txtNetAmount, txtGetAmount, txtCgstSgst, txtTotalAmount;
    private ExtendedFloatingActionButton btnCalculate, btnReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_g_s_t_, container, false);

        vatSpinner = view.findViewById(R.id.mode_gst_spinner);
        name.add("Add GST");
        name.add("sub GST");
        vatSpinner.setAdapter(new ArrayAdapter<>(requireContext(), R.layout.simple_spinner_item, R.id.textlist, name));
        edtInitialAmount = view.findViewById(R.id.edt_intial_amount_gst);
        edtInterest = view.findViewById(R.id.edt_interest_gst);

        txtNetAmount = view.findViewById(R.id.txt_net_amount);
        txtGetAmount = view.findViewById(R.id.txt_get_amount);
        txtCgstSgst = view.findViewById(R.id.txt_cgst_sgst);
        txtTotalAmount = view.findViewById(R.id.txt_total_amount_gst);
        btnCalculate = view.findViewById(R.id.btn_calculate);
        btnReset = view.findViewById(R.id.btn_reset);
        back_gst = view.findViewById(R.id.back_gst);


        back_gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGST();
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

    private void calculateGST() {
        // Validate input
        String initialAmountStr = edtInitialAmount.getText().toString().trim();
        String interestStr = edtInterest.getText().toString().trim();

        if (initialAmountStr.isEmpty() || interestStr.isEmpty()) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double initialAmount = Double.parseDouble(initialAmountStr);
        double interest = Double.parseDouble(interestStr);

        // Calculate GST based on selected mode
        String selectedMode = vatSpinner.getSelectedItem().toString();
        double gstAmount = 0.0;

        if (selectedMode.equals("Add GST")) {
            gstAmount = initialAmount * (1 + (interest / 100));
        } else if (selectedMode.equals("Subtract GST")) {
            gstAmount = initialAmount / (1 + (interest / 100));
        }

        // Calculate total amount including GST
        double totalAmount = gstAmount;

        // Format values
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedNetAmount = decimalFormat.format(initialAmount);
        String formattedGetAmount = decimalFormat.format(gstAmount - initialAmount);
        String formattedTotalAmount = decimalFormat.format(totalAmount);

        // Display calculated values
        txtNetAmount.setText(" " + formattedNetAmount);
        txtGetAmount.setText("" + formattedGetAmount);
        txtTotalAmount.setText("" + formattedTotalAmount);

        // Display CGST and SGST (assuming GST is 18%)
        double cgstSgst = gstAmount - initialAmount;
        double cgstPercent = 9.0; // Assuming 9% CGST
        double sgstPercent = 9.0; // Assuming 9% SGST
        String formattedCgstSgst = String.format("CGST : %.2f%% = ₹ %.2f\nSGST : %.2f%% = ₹ %.2f",
                cgstPercent, (cgstSgst * cgstPercent / 100), sgstPercent, (cgstSgst * sgstPercent / 100));
        txtCgstSgst.setText(formattedCgstSgst);
    }

    private void resetFields() {
        // Reset all input fields and text views
        edtInitialAmount.setText("");
        edtInterest.setText("");
        txtNetAmount.setText("Net Amount: ₹ 0.00");
        txtGetAmount.setText("GST Amount: ₹ 0.00");
        txtCgstSgst.setText("CGST : 0.00% = ₹ 0\nSGST : 0.00% = ₹ 0");
        txtTotalAmount.setText("Total Amount: ₹ 0.00");
        vatSpinner.setSelection(0); // Reset spinner to the first item
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
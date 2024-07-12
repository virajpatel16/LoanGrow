package com.example.loangrow.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loangrow.Activity.DetailsActivity;
import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;

public class Advance_EMI_Fragment extends Fragment {

    private EditText etPrincipal, etInterest, etTenure, etFees;
    private TextView tvInterestAmt, tvPrincipalAmt, tvTotalPayAmt, monthlyEMI, tvYears, tvMonths;
    private RadioButton rbArrears, rbAdvance;
    private RadioGroup radiogroup;

    private ExtendedFloatingActionButton btnCalculate, btnreset;

    Button btnDetails;
    private ImageView img_back13;
    private PieChart chartAdvanceEmi;
    private int monthOrYear = 1;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_advance__e_m_i_, container, false);
        etPrincipal = view.findViewById(R.id.et_principal);
        etInterest = view.findViewById(R.id.et_interest);
        etTenure = view.findViewById(R.id.et_tenure);
        etFees = view.findViewById(R.id.et_fees);
        tvInterestAmt = view.findViewById(R.id.tv_interest_amt);
        tvPrincipalAmt = view.findViewById(R.id.tv_principal_amt);
        tvTotalPayAmt = view.findViewById(R.id.tv_total_pay_amt);
        monthlyEMI = view.findViewById(R.id.Monthly_EMI);
        radiogroup = view.findViewById(R.id.radiogroup);
        tvYears = view.findViewById(R.id.tv_years);
        tvMonths = view.findViewById(R.id.tv_months);
        btnreset = view.findViewById(R.id.btn_reset);
        img_back13 = view.findViewById(R.id.img_back13);
        rbArrears = view.findViewById(R.id.rb_arrears);
        rbAdvance = view.findViewById(R.id.rb_advance);
        btnCalculate = view.findViewById(R.id.btn_Calculate);
        btnDetails = view.findViewById(R.id.btn_details);
        chartAdvanceEmi = view.findViewById(R.id.chart_advance_emi);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keybord(v);

                // Perform the calculation
                calculateEMI();
            }
        });
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String principal = etPrincipal.getText().toString().trim();
                String interest = etInterest.getText().toString().trim();
                String fees = etFees.getText().toString().trim();
                String tenure = etTenure.getText().toString().trim();
                String interestAmt = tvInterestAmt.getText().toString();
                String principalAmt = tvPrincipalAmt.getText().toString();
                String totalPayAmt = tvTotalPayAmt.getText().toString();
                String emi = monthlyEMI.getText().toString();

                Intent intent = new Intent(requireActivity(), DetailsActivity.class);
                intent.putExtra("principal", principal);
                intent.putExtra("interest", interest);
                intent.putExtra("fees", fees);
                intent.putExtra("total_interest", interestAmt);
                intent.putExtra("tvPrincipalAmt", principalAmt);
                intent.putExtra("tvTotalPayAmt", totalPayAmt);
                intent.putExtra("tenure", tenure);
                intent.putExtra("emi", emi);
                startActivity(intent);
            }
        });

        tvMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColorsOfSelectedTextView(tvYears, tvMonths);
                monthOrYear = 2;
                calculateEMI();
            }
        });
        img_back13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        tvYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColorsOfSelectedTextView(tvMonths, tvYears);
                monthOrYear = 1;
                calculateEMI();
            }
        });
    return view;
    }
    private void calculateEMI() {
        String principalStr = etPrincipal.getText().toString().trim();
        String interestStr = etInterest.getText().toString().trim();
        String tenureStr = etTenure.getText().toString().trim();
        String feesStr = etFees.getText().toString().trim();

        if (TextUtils.isEmpty(principalStr) || TextUtils.isEmpty(interestStr) ||
                TextUtils.isEmpty(tenureStr) || TextUtils.isEmpty(feesStr)) {
            // Handle empty input
            Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double principal = Double.parseDouble(principalStr);
            double interestRate = Double.parseDouble(interestStr) / 12 / 100;
            int tenure = Integer.parseInt(tenureStr);
            double fees = Double.parseDouble(feesStr);

            int tenureInMonths;
            if (monthOrYear == 1) {
                tenureInMonths = tenure * 12;
            } else {
                tenureInMonths = tenure;
            }

            double emi;
            if (rbArrears.isChecked()) {
                emi = (principal * interestRate * Math.pow(1 + interestRate, tenureInMonths)) /
                        (Math.pow(1 + interestRate, tenureInMonths) - 1);
            } else {
                emi = principal / tenureInMonths + (principal * interestRate);
            }

            double totalInterest = emi * tenureInMonths - principal;
            double totalPayment = principal + totalInterest + fees;

            DecimalFormat df = new DecimalFormat("#.##");

            tvInterestAmt.setText(df.format(totalInterest));
            tvPrincipalAmt.setText(df.format(principal));
            tvTotalPayAmt.setText(df.format(totalPayment));
            monthlyEMI.setText(df.format(emi));

            int years = tenureInMonths / 12;
            int months = tenureInMonths % 12;

            if (monthOrYear == 1) {
                tvYears.setText(String.format("%d years", tenure));
                tvMonths.setText("");
            } else {
                tvYears.setText(String.format("%d years", years));
                tvMonths.setText(String.format("%d months", months));
            }

            chartAdvanceEmi.clearChart();
            chartAdvanceEmi.addPieSlice(new PieModel("Principal", (float) principal, getResources().getColor(R.color.black)));
            chartAdvanceEmi.addPieSlice(new PieModel("Interest", (float) totalInterest, getResources().getColor(R.color.white)));
            chartAdvanceEmi.addPieSlice(new PieModel("Fees", (float) fees, getResources().getColor(R.color.black)));
            chartAdvanceEmi.startAnimation();

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Error calculating EMI", Toast.LENGTH_SHORT).show();
        }
    }

    private void setColorsOfSelectedTextView(TextView selectedTextView, TextView deselectedTextView) {
        selectedTextView.setTextColor(Color.WHITE);
        selectedTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.appcolor));

        deselectedTextView.setTextColor(Color.WHITE);
        deselectedTextView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black));
    }

    private void resetFields() {
        etPrincipal.setText("");
        etInterest.setText("");
        etTenure.setText("");
        etFees.setText("");
        radiogroup.check(R.id.rb_arrears);
        tvInterestAmt.setText("0");
        tvPrincipalAmt.setText("0");
        tvTotalPayAmt.setText("0");
        monthlyEMI.setText("0");
        tvYears.setText("");
        tvMonths.setText("");
        Toast.makeText(getContext(), "Value is 0", Toast.LENGTH_SHORT).show();
    }
    private  void keybord(View v){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
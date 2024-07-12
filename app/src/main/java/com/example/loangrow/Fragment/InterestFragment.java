package com.example.loangrow.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loangrow.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DecimalFormat;

public class InterestFragment extends Fragment {



    ImageView backInterest;


    private EditText investmentAmountEditText, rateOfInterestEditText, tenureEditText, typeOfInterestEditText;
    private TextView answerInvestmentAmountTextView, answerTotalInterestValueTextView, answerMaturityValueTextView;
    private ExtendedFloatingActionButton calculateButton, resetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_interest, container, false);
        backInterest = view.findViewById(R.id.backInterest);




        typeOfInterestEditText = view.findViewById(R.id.TypeInterestEditext);

        typeOfInterestEditText.setText("GST is Added");
        investmentAmountEditText = view.findViewById(R.id.InvestmentAmountedInterestittext);
        rateOfInterestEditText = view.findViewById(R.id.ExpectrateofinterestInterestEditText);
        tenureEditText = view.findViewById(R.id.TenureInterestEdittext);


        answerInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamountinterest);
        answerTotalInterestValueTextView = view.findViewById(R.id.answertotalinterestvalueinterest);
        answerMaturityValueTextView = view.findViewById(R.id.answermatururityvalueinterest);

        calculateButton = view.findViewById(R.id.calculatebuttoninterest);
        resetButton = view.findViewById(R.id.resebuttoninterest);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInterestAndMaturity();
                hideKeyboard(v);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();


            }
        });
        backInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requireActivity().finish();
            }
        });

    return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void calculateInterestAndMaturity() {
        // Retrieve input values
        String investmentAmountStr = investmentAmountEditText.getText().toString().trim();
        String rateOfInterestStr = rateOfInterestEditText.getText().toString().trim();
        String tenureStr = tenureEditText.getText().toString().trim();

        // Validate input
        if (investmentAmountStr.isEmpty() || rateOfInterestStr.isEmpty() || tenureStr.isEmpty()) {
            Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert input strings to appropriate data types
        double investmentAmount = Double.parseDouble(investmentAmountStr);
        double rateOfInterest = Double.parseDouble(rateOfInterestStr) / 100; // Convert percentage to decimal
        int tenure = Integer.parseInt(tenureStr);

        // Perform interest and maturity calculation
        double totalInterest = investmentAmount * rateOfInterest * tenure;
        double maturityValue = investmentAmount + totalInterest;

        // Display the calculated values
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        answerInvestmentAmountTextView.setText(decimalFormat.format(investmentAmount));
        answerTotalInterestValueTextView.setText(decimalFormat.format(totalInterest));
        answerMaturityValueTextView.setText(decimalFormat.format(maturityValue));
    }


    private void resetFields() {
        // Clear all input fields
        investmentAmountEditText.setText("");
        rateOfInterestEditText.setText("");
        tenureEditText.setText("");
        typeOfInterestEditText.setText("");

        // Clear all result TextViews
        answerInvestmentAmountTextView.setText("");
        answerTotalInterestValueTextView.setText("");
        answerMaturityValueTextView.setText("");
    }
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
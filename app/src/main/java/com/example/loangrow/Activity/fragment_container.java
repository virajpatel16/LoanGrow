package com.example.loangrow.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loangrow.Fragment.Advance_EMI_Fragment;
import com.example.loangrow.Fragment.Advance_SIP_Fragment;
import com.example.loangrow.Fragment.Age_Fragment;
import com.example.loangrow.Fragment.BMI_Fragment;
import com.example.loangrow.Fragment.Calculate_FRagment;
import com.example.loangrow.Fragment.CompareFragment;
import com.example.loangrow.Fragment.Digital_Storage;
import com.example.loangrow.Fragment.DiscountFragment;
import com.example.loangrow.Fragment.EmiFragment;
import com.example.loangrow.Fragment.Fixed_Deposite;
import com.example.loangrow.Fragment.GST_Fragment;
import com.example.loangrow.Fragment.Grossprofit_fragment;
import com.example.loangrow.Fragment.InterestFragment;
import com.example.loangrow.Fragment.LengthFragment;
import com.example.loangrow.Fragment.Loan_Amount;
import com.example.loangrow.Fragment.Margin_fragment;
import com.example.loangrow.Fragment.Markup_Fragment;
import com.example.loangrow.Fragment.Price_Fragment;
import com.example.loangrow.Fragment.Recurring_Deposite;
import com.example.loangrow.Fragment.SimpleFragment;
import com.example.loangrow.Fragment.Sip_Fragment;
import com.example.loangrow.Fragment.TimeFragment;
import com.example.loangrow.Fragment.Vat_Fragment;
import com.example.loangrow.Fragment.Weigth_Fragment;
import com.example.loangrow.R;

public class fragment_container extends AppCompatActivity {

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        if (getIntent().getExtras() != null) {
            check = getIntent().getIntExtra("dailycheck", 1);


            switch (check) {
                case 101:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new EmiFragment()).commit();
                    break;
                case 102:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Fixed_Deposite()).commit();
                    break;
                case 103:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Recurring_Deposite()).commit();
                    break;
                case 104:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new GST_Fragment()).commit();
                    break;
                case 105:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CompareFragment()).commit();
                    break;
                case 106:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new InterestFragment()).commit();
                    break;
                case 107:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SimpleFragment()).commit();
                    break;
                case 108:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DiscountFragment()).commit();
                    break;
                case 109:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TimeFragment()).commit();
                    break;
                case 110:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new LengthFragment()).commit();
                    break;
                case 111:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Weigth_Fragment()).commit();
                    break;
                case 112:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new BMI_Fragment()).commit();
                    break;
                case 113:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Age_Fragment()).commit();
                    break;
                case 114:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Digital_Storage()).commit();
                    break;
                case 115:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Vat_Fragment()).commit();
                    break;
                case 116:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Price_Fragment()).commit();
                    break;
                case 117:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Markup_Fragment()).commit();
                    break;
                case 118:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Loan_Amount()).commit();
                    break;
                case 119:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Grossprofit_fragment()).commit();
                    break;
                case 120:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Sip_Fragment()).commit();
                    break;
                case 121:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Margin_fragment()).commit();
                    break;
                case 122:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Advance_SIP_Fragment()).commit();
                    break;
                case 123:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Advance_EMI_Fragment()).commit();
                    break;
                    case 124:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Calculate_FRagment()).commit();
                    break;
            }
        }

    }
}
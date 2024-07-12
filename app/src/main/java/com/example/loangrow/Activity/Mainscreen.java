package com.example.loangrow.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loangrow.R;

public class Mainscreen extends AppCompatActivity {

    LinearLayout emi, fd, rd, gst, compare, interest, simple, time, length, discount,
            weigth, bmi, age, digital, vat, price, gross, markup, loan, sip, margin,
    advanceemi,advancesip,calculate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        emi = findViewById(R.id.emi);
        sip = findViewById(R.id.sip);
        calculate=findViewById(R.id.calculate);
        margin = findViewById(R.id.margin);
        advanceemi=findViewById(R.id.advanceemi);
        advancesip=findViewById(R.id.advancesip);
        fd = findViewById(R.id.fd);
        rd = findViewById(R.id.rd);
        gst = findViewById(R.id.gst);
        compare = findViewById(R.id.compare);
        weigth = findViewById(R.id.weigth);
        bmi = findViewById(R.id.bmi);
        age = findViewById(R.id.age);
        digital = findViewById(R.id.digital);
        vat = findViewById(R.id.vat);
        price = findViewById(R.id.price);
        interest = findViewById(R.id.interest);
        simple = findViewById(R.id.simple);
        time = findViewById(R.id.time);
        length = findViewById(R.id.length);
        discount = findViewById(R.id.discount);
        gross = findViewById(R.id.gross);
        markup = findViewById(R.id.markup);
        loan = findViewById(R.id.loan);




        emi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 101);
                startActivity(intent);
            }
        });
        fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 102);
                startActivity(intent);
            }
        });
        rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 103);
                startActivity(intent);
            }
        });
        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 104);
                startActivity(intent);
            }
        });
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 105);
                startActivity(intent);
            }
        });
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 106);
                startActivity(intent);
            }
        });
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 107);
                startActivity(intent);
            }
        });
        discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 108);
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 109);
                startActivity(intent);
            }
        });
        length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 110);
                startActivity(intent);
            }
        });
        weigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 111);
                startActivity(intent);
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 112);
                startActivity(intent);
            }
        });
        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 113);
                startActivity(intent);
            }
        });
        digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 114);
                startActivity(intent);
            }
        });
        vat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 115);
                startActivity(intent);
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 116);
                startActivity(intent);
            }
        });
        markup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 117);
                startActivity(intent);
            }
        });
        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 118);
                startActivity(intent);
            }
        });
        gross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 119);
                startActivity(intent);
            }
        });
        sip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 120);
                startActivity(intent);
            }
        });
        margin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 121);
                startActivity(intent);
            }
        });
        advancesip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 122);
                startActivity(intent);
            }
        });
        advanceemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 123);
                startActivity(intent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainscreen.this, fragment_container.class);
                intent.putExtra("dailycheck", 124);
                startActivity(intent);
            }
        });
    }
}
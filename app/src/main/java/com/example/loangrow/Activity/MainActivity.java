package com.example.loangrow.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.loangrow.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private DotsIndicator dotsIndicator;
    private TextView nextText,skipbtn;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.slidepage);
        dotsIndicator = findViewById(R.id.dots_indicator);
        nextText = findViewById(R.id.nextbtn);
        skipbtn=findViewById(R.id.skipbtn);

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.first);
        imageList.add(R.drawable.secound);
        imageList.add(R.drawable.third);
        imageList.add(R.drawable.fddot);
        imageList.add(R.drawable.gstdot);
        imageList.add(R.drawable.rddot);
        imageList.add(R.drawable.emidot);

        ViewPagerAdapter adapter = new ViewPagerAdapter(imageList);
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager2(viewPager);
        nextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next page
                int nextPosition = viewPager.getCurrentItem() + 1;
                if (nextPosition < adapter.getItemCount()) {
                    viewPager.setCurrentItem(nextPosition);
                } else  {
                    Intent intent = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        });
        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mainscreen.class);
                startActivity(intent);
            }
        });
    }
}
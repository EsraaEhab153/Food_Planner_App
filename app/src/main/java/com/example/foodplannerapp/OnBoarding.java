package com.example.foodplannerapp;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodplannerapp.HelperClasses.SliderAdapter;

public class OnBoarding extends AppCompatActivity {
  ViewPager2 viewPager2;
  LinearLayout dots;
  SliderAdapter sliderAdapter;
  TextView [] dotsIndicator;
  Button letsGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_boarding);
        viewPager2 = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.btn_get_started);

        sliderAdapter = new SliderAdapter(this);
        viewPager2.setAdapter(sliderAdapter);
        addDots(0);

         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                addDots(position);

                if(position == 0){
                    letsGetStarted.setVisibility(View.INVISIBLE);
                }else if(position == 1){
                    letsGetStarted.setVisibility(View.INVISIBLE);
                }else{
                    letsGetStarted.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void addDots(int position){
        dotsIndicator = new TextView[3];
        dots.removeAllViews();
        for(int i = 0 ;i<dotsIndicator.length;i++){
            dotsIndicator[i] = new TextView(this);
            dotsIndicator[i].setText(Html.fromHtml("&#8226;"));
            dotsIndicator[i].setTextSize(35);
            dotsIndicator[i].setTextColor(getResources().getColor(R.color.gray_color));

            dots.addView(dotsIndicator[i]);
        }
        if(dotsIndicator.length>0){
            dotsIndicator[position].setTextColor(getResources().getColor(R.color.primary_color));
        }
    }

}
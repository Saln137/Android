package com.example.personalcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.personalcenter.View.MainView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        getSupportActionBar().hide();
        MainView mainView=new MainView(this);
        FrameLayout frameLayout=findViewById(R.id.main_body);
        frameLayout.addView(mainView.getView());
        mainView.showView();

    }


}

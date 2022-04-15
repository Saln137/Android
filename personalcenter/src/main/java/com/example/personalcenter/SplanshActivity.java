package com.example.personalcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplanshActivity extends AppCompatActivity {

    private TextView tv_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splansh);
//        getSupportActionBar().hide();
        init();
    }
    public void init(){
        tv_bg = findViewById(R.id.tv_bg);
        try {
            PackageInfo info=getPackageManager().getPackageInfo(
                    getPackageName(),0);
            tv_bg.setText("V"+info.versionName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            tv_bg.setText("V");
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplanshActivity.this, MainActivity.class);
                startActivity(intent);
                SplanshActivity.this.finish();
            }
        };
        timer.schedule(task,1000);
    }
}

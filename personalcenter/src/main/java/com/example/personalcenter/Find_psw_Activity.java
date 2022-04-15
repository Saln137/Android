package com.example.personalcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Find_psw_Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        getSupportActionBar().hide();

        init();
    }

    private void init() {
        TextView tv_back = findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                Find_psw_Activity.this.finish();
                break;
        }

    }
}

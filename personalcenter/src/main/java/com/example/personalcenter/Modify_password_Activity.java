package com.example.personalcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalcenter.utils.AnalysisUtils;
import com.example.personalcenter.utils.MD5Utils;

public class Modify_password_Activity extends AppCompatActivity {

    private TextView tv_main_title;
    private TextView tv_back;
    private EditText et_original_psw,et_new_psw,et_new_psw_again;
    private Button btn_save;
    private String originalPsw,newPsw,newPswAgain;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password_);
        getSupportActionBar().hide();
        init();
        userName= AnalysisUtils.readLoginUserName(this);

    }

    private void init() {
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        tv_back=(TextView) findViewById(R.id.tv_back);
        et_original_psw=(EditText) findViewById(R.id.et_original_psw);
        et_new_psw=(EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again=(EditText) findViewById(R.id.et_new_psw_again);
        btn_save=(Button) findViewById(R.id.btn_save);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modify_password_Activity.this.finish();
            }
        });
//保存按钮的点击事件
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)) {
                    Toast.makeText(Modify_password_Activity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!MD5Utils.md5(originalPsw).equals(readPsw())) {
                    Toast.makeText(Modify_password_Activity.this, "输入的密码与原始密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if(MD5Utils.md5(newPsw).equals(readPsw())){
                    Toast.makeText(Modify_password_Activity.this, "输入的新密码与原始密码不能一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPsw)) {
                    Toast.makeText(Modify_password_Activity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(newPswAgain)) {
                    Toast.makeText(Modify_password_Activity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!newPsw.equals(newPswAgain)) {
                    Toast.makeText(Modify_password_Activity.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(Modify_password_Activity.this, "新密码设置成功", Toast.LENGTH_SHORT).show();
                    //修改登录成功时保存在SharedPreferences中的密码
                    modifyPsw(newPsw);
                    Intent intent = new Intent(Modify_password_Activity.this, LoginActivity.class);
                    startActivity(intent);
//                    SettingActivity.instance.finish();
                    Modify_password_Activity.this.finish();
                }
            }
        });

    }

    /**
     * 获取控件上的字符串
     */
    private void getEditString(){
        originalPsw=et_original_psw.getText().toString().trim();
        newPsw=et_new_psw.getText().toString().trim();
        newPswAgain=et_new_psw_again.getText().toString().trim();
    }
    /**
     * 修改登录成功时保存在SharedPreferences中的密码
     */
    private void modifyPsw(String newPsw){
        String md5Psw= MD5Utils.md5(newPsw);//把密码用MD5加密
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putString(userName, md5Psw);//保存新密码
        editor.commit();//提交修改
    }
    /**
     *从SharedPreferences中读取原始密码
     */
    private String readPsw(){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");
        return spPsw;
    }




}

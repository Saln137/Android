package com.example.personalcenter;

import androidx.annotation.Nullable;
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

import com.example.personalcenter.utils.MD5Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_main_title;
    private TextView tv_back,tv_register,tv_find_psw;
    private Button btn_login;
    private String userName,psw,spPsw;
    private EditText et_user_name,et_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        TextView tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        TextView tv_find_psw = findViewById(R.id.tv_find_psw);
        tv_find_psw .setOnClickListener(this);
        TextView tv_back=findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        et_user_name=(EditText) findViewById(R.id.et_user_name);
        et_psw=(EditText) findViewById(R.id.et_psw);
        tv_main_title=(TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("登录");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
//                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                init();
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_find_psw:
                Intent intent1 = new Intent(LoginActivity.this, Find_psw_Activity.class);
                startActivity(intent1);
                break;
            case R.id.tv_back:
                LoginActivity.this.finish();
                break;
        }

    }

    private void init() {

        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        String md5Psw= MD5Utils.md5(psw);
        spPsw=readPsw(userName);
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(psw)){
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }else if(md5Psw.equals(spPsw)){
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            //保存登录状态
            saveLoginStatus(true, userName);
            Intent data=new Intent();
            data.putExtra("isLogin",true);
            setResult(RESULT_OK,data);
            LoginActivity.this.finish();
            return;
        }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
            Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLoginStatus(boolean status, String userName) {
        //loginInfo表示文件名
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putBoolean("isLogin", status);//存入boolean类型的登录状态
        editor.putString("loginUserName", userName);//存入登录状态时的用户名
        editor.commit();//提交修改
    }

    private String readPsw(String userName) {
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //从注册界面传递过来的用户名
            String userName =data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                //设置光标的位置
                et_user_name.setSelection(userName.length());
            }
        }
    }
}

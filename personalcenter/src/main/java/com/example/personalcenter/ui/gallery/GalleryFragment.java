package com.example.personalcenter.ui.gallery;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.personalcenter.ChangeUserInfoActivity;
import com.example.personalcenter.R;
import com.example.personalcenter.bean.UserBean;
import com.example.personalcenter.utils.AnalysisUtils;
import com.example.personalcenter.utils.DBUtils;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_nickName, tv_signature, tv_user_name, tv_sex;
    private RelativeLayout rl_nickName, rl_sex, rl_signature,
            rl_title_bar;
    private static final int CHANGE_NICKNAME = 1;//修改昵称的自定义常量
    private static final int CHANGE_SIGNATURE = 2;//修改个性签名的自定义常量
    private String spUserName;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //从SharedPreferences中获取登录时的用户名
        spUserName = AnalysisUtils.readLoginUserName(getContext());
        init();
        initData();
        setListener();
    }
    //初始化控件
    private void init() {
        rl_nickName = (RelativeLayout) getView().findViewById(R.id.rl_nickName);
        rl_sex = (RelativeLayout)getView(). findViewById(R.id.rl_sex);
        rl_signature = (RelativeLayout) getView().findViewById(R.id.rl_signature);
        tv_nickName = (TextView) getView().findViewById(R.id.tv_nickName);
        tv_user_name = (TextView) getView().findViewById(R.id.tv_user_name);
        tv_sex = (TextView) getView().findViewById(R.id.tv_sex);
        tv_signature = (TextView) getView().findViewById(R.id.tv_signature);
    }
    //获取数据
    private void initData() {
        UserBean bean = null;
        bean = DBUtils.getInstance(getContext()).getUserInfo(spUserName);
        // 首先判断一下数据库是否有数据
        if (bean == null) {
            bean = new UserBean();
            bean.userName=spUserName;
            bean.nickName="问答精灵";
            bean.sex="男";
            bean.signature="问答精灵";
            //保存用户信息到数据库
            DBUtils.getInstance(getContext()).saveUserInfo(bean);
        }
        setValue(bean);
    }

    private void setValue(UserBean bean) {
        tv_nickName.setText(bean.nickName);
        tv_user_name.setText(bean.userName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
    }

    //设置控件的点击监听事件
    private void setListener() {
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_nickName://昵称的点击事件
                String name = tv_nickName.getText().toString();//获取昵称控件上的数据
                Bundle bdName = new Bundle();
                bdName.putString("content", name);//传递界面上的昵称数据
                bdName.putString("title", "昵称");
                bdName.putInt("flag", 1);//flag传递1时表示是修改昵称
                enterActivityForResult(ChangeUserInfoActivity.class,
                        CHANGE_NICKNAME, bdName);//跳转到个人资料修改界面
                break;
            case R.id.rl_sex://性别的点击事件
                String sex = tv_sex.getText().toString();//获取性别控件上的数据
                sexDialog(sex);
                break;
            case R.id.rl_signature://签名的点击事件
                String signature = tv_signature.getText().toString();//获取签名控件上的数据
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content", signature);//传递界面上的签名数据
                bdSignature.putString("title", "签名");
                bdSignature.putInt("flag", 2);//flag传递2时表示是修改签名
                enterActivityForResult(ChangeUserInfoActivity.class,
                        CHANGE_SIGNATURE, bdSignature);//跳转到个人资料修改界面
                break;
            default:
                break;
        }
    }

    /**
     * 设置性别的弹出框
     */
    private void sexDialog(String sex){
        int sexFlag=0;
        if("男".equals(sex)){
            sexFlag=0;
        }else if("女".equals(sex)){
            sexFlag=1;
        }
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());  //先得到构造器
        builder.setTitle("性别"); //设置标题
        builder.setSingleChoiceItems(items,sexFlag,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//第二个参数是默认选中的哪个项
                dialog.dismiss();
                Toast.makeText(getActivity(),items[which],Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create().show();
    }

    /**
     * 更新界面上的性别数据
     */
    private void setSex(String sex){
        tv_sex.setText(sex);
        // 更新数据库中的性别字段
        DBUtils.getInstance(getActivity()).updateUserInfo("sex",
                sex, spUserName);
    }

    /**
     * 获取回传数据时需使用的跳转方法，
     * 第一个参数to表示需要跳转到的界面，第二个参数requestCode表示一个请求码，第三个参数b表示跳转时传递的数据
     */
    public void enterActivityForResult(Class<?> to, int requestCode, Bundle b) {
        Intent i = new Intent(getActivity(), to);
        i.putExtras(b);
        startActivityForResult(i, requestCode);
    }
    /**
     * 回传数据
     */
    private String new_info;//最新数据

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANGE_NICKNAME://个人资料修改界面回传过来的昵称数据
                if (data != null) {
                    new_info = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info) || new_info == null) {
                        return;
                    }
                    tv_nickName.setText(new_info);
                    // 更新数据库中的昵称字段
                    DBUtils.getInstance(getActivity()).updateUserInfo(
                            "nickName", new_info, spUserName);
                }
                break;
            case CHANGE_SIGNATURE://个人资料修改界面回传过来的签名数据
                if (data != null) {
                    new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info) || new_info == null) {
                        return;
                    }
                    tv_signature.setText(new_info);
                    // 更新数据库中的签名字段
                    DBUtils.getInstance(getActivity()).updateUserInfo(
                            "signature", new_info, spUserName);
                }
                break;
        }
    }
}
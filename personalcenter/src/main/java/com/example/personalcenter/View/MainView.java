package com.example.personalcenter.View;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;


import com.example.personalcenter.R;
import com.example.personalcenter.adapter.MainAdapter;
import com.example.personalcenter.bean.ExerciseBean;

import java.util.ArrayList;
import java.util.List;

public class MainView {
    private ListView lv_list;
    private MainAdapter adapter;
    private List<ExerciseBean> ebl;
    private Activity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    public MainView(Activity context) {
        mContext = context;
        // 为之后将Layout转化为view时用
        mInflater = LayoutInflater.from(mContext);
    }
    private void createView() {
        initView();
    }
    /**
     * 初始化控件
     */
    private void initView() {
        mCurrentView = mInflater
                .inflate(R.layout.activity_exercise, null);
        lv_list =  mCurrentView.findViewById(R.id.lv_list2);
        adapter = new MainAdapter(mContext);
        initData();
        adapter.setData(ebl);
        lv_list.setAdapter(adapter);
    }
    /**
     * 设置数据
     */
    private void initData() {
        ebl = new ArrayList<ExerciseBean>();
        for (int i = 0; i < 10; i++) {
            ExerciseBean bean = new ExerciseBean();
            bean.id=(i + 1);
            switch (i) {
                case 0:
                    bean.title="第1章 Java基础入门";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title="第2章 异常处理";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title="第3章 I/O";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title="第4章 泛型";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title="第5章 SQLite数据库";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title="第6章 反射机制";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title="第7章 服务框架";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title="第8章 多线程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title="第9章 网络编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title="第10章 高级编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            ebl.add(bean);
        }
    }
    /**
     * 获取当前在导航栏上方显示对应的View
     */
    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }
    /**
     * 显示当前导航栏上方所对应的view界面
     */
    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}

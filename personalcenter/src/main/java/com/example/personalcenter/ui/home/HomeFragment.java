package com.example.personalcenter.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.personalcenter.GlideImageLoader;
import com.example.personalcenter.ParticularsActivity;
import com.example.personalcenter.R;
import com.example.personalcenter.View.CourseView;
import com.example.personalcenter.adapter.AdBannerAdapter;
import com.example.personalcenter.bean.CourseBean;
//import com.example.personalcenter.ui.adbanner.AdBannerFragment;
import com.example.personalcenter.ui.adbanner.AdBannerViewModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    //    private AdBannerViewModel mViewModel;
    private String ab;// 广告
    private ImageView iv;// 图片
    private ListView lv_list;
    private CourseView mCourseView;
    private FrameLayout  mBodyLayout;
    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private List<Integer> imagePath;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//         创建广告图片控件
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        iv.setLayoutParams(lp);// 设置图片宽高参数
        iv.setScaleType(ImageView.ScaleType.FIT_XY);// 把图片塞满整个控件
//        return iv;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ImageView iv_1 = getView().findViewById(R.id.iv_1);
//        iv_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
//                startActivity(intent);
//            }
//        });
        // 创建广告图片控件
//        iv = new ImageView(getActivity());
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                ViewGroup.LayoutParams.FILL_PARENT);
//        iv.setLayoutParams(lp);// 设置图片宽高参数
//        iv.setScaleType(ImageView.ScaleType.FIT_XY);// 把图片塞满整个控件

//        Bundle arg = getArguments();
        // 获取广告图片名称
//        ab = arg.getString("ad");

//        CourseView mCourseView = new CourseView(getActivity());
//        mCourseView.initView();
//        mCourseView.getView();
//        mCourseView.showView();
//        AdBannerAdapter adBannerAdapter = new AdBannerAdapter(getFragmentManager());
//            setInitStatus();
//        mBodyLayout = getView().findViewById(R.id.home_body);
//        mCourseView = new CourseView(getActivity());
//        mBodyLayout.addView(mCourseView.getView());
//        mCourseView.showView();


        FrameLayout mBodyLayout  = getView().findViewById(R.id.home_body);
        mCourseView = new CourseView(getActivity());
        mBodyLayout.addView(mCourseView.getView());
        mCourseView.showView();
//        initDate();
//        initView();

    }






}
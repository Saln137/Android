package com.example.personalcenter.View;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.personalcenter.GlideImageLoader;
import com.example.personalcenter.R;
import com.example.personalcenter.adapter.AdBannerAdapter;
import com.example.personalcenter.adapter.CourseAdapter;
import com.example.personalcenter.bean.CourseBean;
import com.example.personalcenter.utils.AnalysisUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CourseView {
    private ListView lv_list;
    private CourseAdapter adapter;
    private List<List<CourseBean>> cbl;
    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;// 广告
    private View adBannerLay;// 广告条容器
    private AdBannerAdapter ada;// 适配器
    public static final int MSG_AD_SLID = 002;// 广告自动滑动
    private ViewPagerIndicator vpi;// 小圆点
    private MHandler mHandler;// 事件捕获
    private List<CourseBean> cadl;
    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private List<Integer> imagePath;
    private List<String> imageTitle;


    public CourseView(FragmentActivity context) {
        mContext = context;
        // 为之后将Layout转化为view时用
        mInflater = LayoutInflater.from(mContext);
    }
    public void createView() {
        mHandler = new MHandler();
//        initAdData();
        getCourseData();
        initView();
//        new AdAutoSlidThread().start();

    }
    /**
     * 事件捕获
     */
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_AD_SLID:
                    if (ada.getCount() > 0) {
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }
    /**
     * 广告自动滑动
     */
    class AdAutoSlidThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null)
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
            }
        }
    }
    /**
     * 初始化控件
     */
    public void initView() {
        mCurrentView = mInflater.inflate(R.layout.main_view_course, null);
        lv_list = (ListView) mCurrentView.findViewById(R.id.lv_list2);
        adapter = new CourseAdapter(mContext);
        adapter.setData(cbl);
        lv_list.setAdapter(adapter);
        initDate();
        initView2();

//        mCurrentView= mInflater.inflate(R.layout.main_adbanner,null);
//        adPager = (ViewPager) mCurrentView.findViewById(R.id.vp_advertBanner);
//        adPager.setLongClickable(false);
//        ada = new AdBannerAdapter(mContext.getSupportFragmentManager(),
//                mHandler);
//        adPager.setAdapter(ada);// 给ViewPager设置适配器
//        adPager.setOnTouchListener(ada);
//
//
//        vpi =  mCurrentView
//                .findViewById(R.id.vpi_advert_indicator);// 获取广告条上的小圆点
//        vpi.setCount(ada.getSize());// 设置小圆点的个数
//
//        adBannerLay = mCurrentView.findViewById(R.id.rl_adBanner);
//        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//            @Override
//            public void onPageSelected(int position) {
//                if (ada.getSize() > 0) {
//                    //由于index数据在滑动时是累加的，因此用index % ada.getSize()来标记滑动到的当前位置
//                    vpi.setCurrentPosition(position % ada.getSize());
//                }
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        resetSize();
//        if (cadl != null) {
//            if (cadl.size() > 0) {
//                vpi.setCount(cadl.size());
//                vpi.setCurrentPosition(0);
//            }
//            ada.setDatas(cadl);
//        }
    }
    /**
//     * 计算控件大小
//     */
//    private void resetSize() {
//        int sw = getScreenWidth(mContext);
//        int adLheight = sw / 2;// 广告条高度
//        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
//        adlp.width = sw;
//        adlp.height = adLheight;
//        adBannerLay.setLayoutParams(adlp);
//    }
//    /**
//     * 读取屏幕宽
//     */
//    public static int getScreenWidth(Activity context) {
//        DisplayMetrics metrics = new DisplayMetrics();
//        Display display = context.getWindowManager().getDefaultDisplay();
//        display.getMetrics(metrics);
//        return metrics.widthPixels;
//    }
//    /**
//     * 初始化广告中的数据
//     */
//    private void initAdData() {
//        cadl = new ArrayList<CourseBean>();
//        for (int i = 0; i < 3; i++) {
//            CourseBean bean = new CourseBean();
//            bean.id=(i + 1);
//            switch (i) {
//                case 0:
//                    bean.icon="banner_1";
//                    break;
//                case 1:
//                    bean.icon="banner_2";
//                    break;
//                case 2:
//                    bean.icon="banner_3";
//                    break;
//                default:
//                    break;
//            }
//            cadl.add(bean);
//        }
//    }
    private void initDate(){
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.banner_4);
        imagePath.add(R.drawable.banner_5);
        imagePath.add(R.drawable.banner_6);
        imageTitle.add("信息工程学院");
        imageTitle.add("2019级物联网班");
        imageTitle.add("2019年移动开发实训");
    }
    private void initView2(){
        glideImageLoader = new GlideImageLoader();
        banner = getView().findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//三个滑点
        banner.setBannerTitles(imageTitle);
        banner.setImageLoader(glideImageLoader);
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imagePath);
        banner.start();
    }
    /**
     * 获取课程信息
     */
    private void getCourseData() {
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            cbl = AnalysisUtils.getCourseInfos(is);//getCourseInfos(is)方法在下面会有说明
        } catch (Exception e) {
            e.printStackTrace();
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

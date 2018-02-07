package com.android.phonedemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class DemoActivity extends AppCompatActivity {
    private static final int SCROLL_AD = 0;
    private ViewPager mAdPager;
    private boolean mInterrupt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mAdPager = (ViewPager) findViewById(R.id.ad_pager);
        mAdPager.setCurrentItem(40000);
        initAD();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mInterrupt = false;
        scrollAd();
    }

    @Override
    public void onPause() {
        super.onPause();
        interruptAd();
        finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                interruptAd();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mInterrupt = false;
                scrollAd();
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCROLL_AD:
                    if (!mInterrupt) {
                        mAdPager.setCurrentItem(mAdPager.getCurrentItem() + 1, true);
                        scrollAd();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void initAD() {
        int[] showList = {R.drawable.wallpaper_y000001,
                R.drawable.wallpaper_y000002,
                R.drawable.wallpaper_y000003,
                R.drawable.wallpaper_y000004,
                R.drawable.wallpaper_y000005,
                R.drawable.wallpaper_y000006,
                R.drawable.wallpaper_y000007,
                R.drawable.wallpaper_y000008,
                R.drawable.wallpaper_y000009,
                R.drawable.wallpaper_y000010,
                R.drawable.wallpaper_y000011,
                R.drawable.wallpaper_y000012,
                R.drawable.wallpaper_y000013,
                R.drawable.wallpaper_y000014,
                R.drawable.wallpaper_y000015,
                R.drawable.wallpaper_y000016,
                R.drawable.wallpaper_y000017,
                R.drawable.wallpaper_y000018,
                R.drawable.wallpaper_y000019,
                R.drawable.wallpaper_y000020,
                R.drawable.wallpaper_y000021,
                R.drawable.wallpaper_y000022,
                R.drawable.wallpaper_y000023,
                R.drawable.wallpaper_y000024,
                R.drawable.wallpaper_y000025,
                R.drawable.wallpaper_y000026,
                R.drawable.wallpaper_y000027,
        };
        mAdPager.setAdapter(new AdPagerAdapter(this, showList));
    }

    private void interruptAd() {
        mInterrupt = true;
        handler.removeMessages(SCROLL_AD);
    }

    private void scrollAd() {
        handler.sendEmptyMessageDelayed(SCROLL_AD, 2000);
    }

    public class AdPagerAdapter extends PagerAdapter {
        private Context context;
        private int[] images;

        public AdPagerAdapter(Context context, int[] list) {
            this.context = context;
            this.images = list;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (images.length == 0) return null;
            ImageView img = new ImageView(context);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageResource(images[position % images.length]);
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

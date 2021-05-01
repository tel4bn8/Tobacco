package com.nss.tobacco.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.nss.tobacco.activity.MainActivity;
import com.nss.tobacco.fragment.Fragment_first;
import com.nss.tobacco.fragment.Fragment_forth;
import com.nss.tobacco.fragment.Fragment_second;
import com.nss.tobacco.fragment.Fragment_third;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private Fragment_first myFragment1 = null;
    private Fragment_second myFragment2 = null;
    private Fragment_third myFragment3 = null;
    private Fragment_forth myFragment4 = null;


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new Fragment_first();
        myFragment2 = new Fragment_second();
        myFragment3 = new Fragment_third();
        myFragment4 = new Fragment_forth();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

}
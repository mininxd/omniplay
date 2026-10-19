package com.dolby.daxappui.tutorial;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ViewPagerAdapter extends PagerAdapter {
    private List<View> viewdata;

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    ViewPagerAdapter(List<View> list) {
        this.viewdata = list;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.viewdata.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.viewdata.get(i), 0);
        return this.viewdata.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.viewdata.get(i));
    }
}

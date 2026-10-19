package com.dolby.daxappui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public class FragProfilePresets extends Fragment implements AdapterView.OnItemClickListener {
    private FragProfilePanelPageAdapter mProfileAdapter;
    private boolean mTabletLayout;
    private TabletProfilesAdapter mTabletProfilesAdapter;
    private CustomViewPager mViewPager;
    private IDsFragObserver mFObserver = null;
    private ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() { // from class: com.dolby.daxappui.FragProfilePresets.1
        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            FragProfilePresets.this.mFObserver.chooseProfile(i);
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mFObserver = (IDsFragObserver) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement IDsFragObserver");
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    @SuppressLint({"ClickableViewAccessibility"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mTabletLayout = getResources().getBoolean(R.bool.tabletLayout);
        View viewInflate = layoutInflater.inflate(R.layout.fragprofilepresets, viewGroup, false);
        String[] profileNames = DAXApplication.getInstance().getProfileNames();
        if (this.mTabletLayout) {
            AdapterView adapterView = (AdapterView) viewInflate.findViewById(R.id.presetsListView);
            this.mTabletProfilesAdapter = new TabletProfilesAdapter(getActivity());
            if (adapterView != null) {
                adapterView.setAdapter(this.mTabletProfilesAdapter);
                adapterView.setOnItemClickListener(this);
            }
        } else {
            TabLayout tabLayout = (TabLayout) viewInflate.findViewById(R.id.profiletable);
            for (String str : profileNames) {
                TabLayout.Tab tabNewTab = tabLayout.newTab();
                tabNewTab.setText(str);
                tabLayout.addTab(tabNewTab);
            }
            if (tabLayout != null) {
                this.mViewPager = (CustomViewPager) viewInflate.findViewById(R.id.profileViewpager);
                this.mViewPager.setPagingEnabled(false);
                this.mProfileAdapter = new FragProfilePanelPageAdapter(getChildFragmentManager());
                this.mViewPager.setAdapter(this.mProfileAdapter);
                this.mViewPager.addOnPageChangeListener(this.pagerListener);
                this.mViewPager.setOffscreenPageLimit(3);
                tabLayout.setupWithViewPager(this.mViewPager);
            }
        }
        return viewInflate;
    }

    public void updateProfileSettings(int i) {
        TabLayout.Tab tabAt;
        IDsFragObserver iDsFragObserver = this.mFObserver;
        if ((iDsFragObserver == null ? null : iDsFragObserver.getDolbyAudioEffect()) != null) {
            if (this.mTabletLayout) {
                TabletProfilesAdapter tabletProfilesAdapter = this.mTabletProfilesAdapter;
                if (tabletProfilesAdapter != null) {
                    tabletProfilesAdapter.setProfileSelected(i);
                    return;
                }
                return;
            }
            if (this.mViewPager != null) {
                View view = getView();
                if (view != null && (tabAt = ((TabLayout) view.findViewById(R.id.profiletable)).getTabAt(i)) != null) {
                    tabAt.select();
                }
                this.mViewPager.setCurrentItem(i);
                FragProfilePanelPageAdapter fragProfilePanelPageAdapter = this.mProfileAdapter;
                if (fragProfilePanelPageAdapter != null) {
                    ((FragProfilePanel) fragProfilePanelPageAdapter.instantiateItem(this.mViewPager, i)).updateProfilePanel(i);
                    return;
                }
                return;
            }
            return;
        }
        Log.e("FragProfilePresets", "updateProfileSettings(): Dolby audio effect is null!");
    }

    public void setGeqViewEnabled(int i) {
        FragProfilePanelPageAdapter fragProfilePanelPageAdapter = this.mProfileAdapter;
        if (fragProfilePanelPageAdapter != null) {
            ((FragProfilePanel) fragProfilePanelPageAdapter.instantiateItem(this.mViewPager, i)).setGeqViewEnabled();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (getView() == null || this.mFObserver == null || adapterView != getView().findViewById(R.id.presetsListView)) {
            return;
        }
        this.mFObserver.chooseProfile(i);
    }
}

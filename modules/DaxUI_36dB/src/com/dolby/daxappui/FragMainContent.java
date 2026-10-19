package com.dolby.daxappui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class FragMainContent extends Fragment {
    private View mView;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2;
        View view = this.mView;
        if (view != null && (viewGroup2 = (ViewGroup) view.getParent()) != null) {
            viewGroup2.removeView(this.mView);
        }
        this.mView = layoutInflater.inflate(R.layout.content_main, viewGroup, false);
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        if (getResources().getBoolean(R.bool.tabletLayout) && childFragmentManager.findFragmentById(R.id.fragProfilePanelTablet) == null) {
            fragmentTransactionBeginTransaction.replace(R.id.fragProfilePanelTablet, new FragProfilePanel());
        }
        if (childFragmentManager.findFragmentById(R.id.fragProfilePanel) == null) {
            fragmentTransactionBeginTransaction.replace(R.id.fragProfilePanel, new FragProfilePresets());
        }
        fragmentTransactionBeginTransaction.commit();
        return this.mView;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        ViewGroup viewGroup;
        super.onDestroyView();
        View view = this.mView;
        if (view == null || (viewGroup = (ViewGroup) view.getParent()) == null) {
            return;
        }
        viewGroup.removeView(this.mView);
    }
}

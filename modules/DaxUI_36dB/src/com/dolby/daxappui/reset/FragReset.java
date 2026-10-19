package com.dolby.daxappui.reset;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.dolby.daxappui.DsClientSettings;
import com.dolby.daxappui.IDsFragObserver;
import com.dolby.daxappui.R;

/* JADX INFO: loaded from: classes.dex */
public class FragReset extends Fragment {
    private IDsFragObserver mFObserver;

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
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreate(bundle);
        View viewInflate = layoutInflater.inflate(R.layout.reset, viewGroup, false);
        ((RelativeLayout) viewInflate.findViewById(R.id.resetButtonView)).setOnClickListener(new View.OnClickListener() { // from class: com.dolby.daxappui.reset.-$$Lambda$FragReset$kGyPNc8iWrVBFBFKhmrGwmWzAMU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateView$0$FragReset(view);
            }
        });
        return viewInflate;
    }

    public /* synthetic */ void lambda$onCreateView$0$FragReset(View view) {
        DsClientSettings.INSTANCE.resetAllProfileSpecificSettings(this.mFObserver);
    }
}

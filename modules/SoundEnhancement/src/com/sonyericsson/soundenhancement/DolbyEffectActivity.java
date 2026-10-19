package com.sonyericsson.soundenhancement;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public class DolbyEffectActivity extends BaseActivity {
    private static final String LOG_TAG = "SoundEnhancement";
    private DolbyEffectFragment mFragment;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFragment = (DolbyEffectFragment) getFragmentManager().findFragmentById(android.R.id.content);
        if (this.mFragment == null) {
            this.mFragment = new DolbyEffectFragment();
            getFragmentManager().beginTransaction().replace(android.R.id.content, this.mFragment).commit();
        }
        setResult(-1);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}

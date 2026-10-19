package com.sonyericsson.soundenhancement;

import android.content.Intent;
import android.os.Bundle;
import com.dolby.dax.DsParams;

/* JADX INFO: loaded from: classes2.dex */
public class NoiseCancelingActivity extends BaseActivity {
    private static final String LOG_TAG = "SoundEnhancement";
    private NoiseCancelingFragment mNoiseCancelingFragment;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(getIntent());
        intent.addFlags(DsParams.EFFECT_PARAM_PROFILE_PORT_PARAMETER);
        super.onCreate(savedInstanceState);
        setIntent(intent);
        NoiseCancelingFragment fragment = (NoiseCancelingFragment) getFragmentManager().findFragmentById(android.R.id.content);
        if (fragment == null) {
            this.mNoiseCancelingFragment = new NoiseCancelingFragment();
            getFragmentManager().beginTransaction().replace(android.R.id.content, this.mNoiseCancelingFragment).commit();
        } else {
            this.mNoiseCancelingFragment = fragment;
        }
        setVolumeControlStream(3);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }
}

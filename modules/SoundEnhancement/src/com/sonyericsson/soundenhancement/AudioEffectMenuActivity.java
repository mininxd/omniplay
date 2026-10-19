package com.sonyericsson.soundenhancement;

import android.content.Intent;
import android.os.Bundle;
import com.dolby.dax.DsParams;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectMenuActivity extends BaseActivity {
    public static final String EXTRA_START_FROM_SEARCH_RESULT = "com.sonyericsson.soundenhancement.EXTRA_START_FROM_SEARCH_RESULT";
    private static final String LOG_TAG = "SoundEnhancement";
    protected AudioEffectMenuFragment mAudioEffectMenuFragment;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(getIntent());
        intent.addFlags(DsParams.EFFECT_PARAM_PROFILE_PORT_PARAMETER);
        super.onCreate(savedInstanceState);
        setIntent(intent);
        AudioEffectMenuFragment audioEffectMenuFragment = (AudioEffectMenuFragment) getFragmentManager().findFragmentById(android.R.id.content);
        if (audioEffectMenuFragment == null) {
            this.mAudioEffectMenuFragment = new AudioEffectMenuFragment();
            getFragmentManager().beginTransaction().replace(android.R.id.content, this.mAudioEffectMenuFragment).commit();
        } else {
            this.mAudioEffectMenuFragment = audioEffectMenuFragment;
        }
        setVolumeControlStream(3);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

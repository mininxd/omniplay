package com.sonyericsson.soundenhancement;

import android.content.Intent;
import android.os.Bundle;
import com.dolby.dax.DsParams;

/* JADX INFO: loaded from: classes2.dex */
public class SurroundSelectorActivity extends BaseActivity {
    private static final String LOG_TAG = "SoundEnhancement";

    @Override // android.app.Activity
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Intent intent = new Intent(getIntent());
        intent.addFlags(DsParams.EFFECT_PARAM_PROFILE_PORT_PARAMETER);
        setIntent(intent);
        SurroundSelectorFragment surroundSelectorFragment = (SurroundSelectorFragment) getFragmentManager().findFragmentById(android.R.id.content);
        if (surroundSelectorFragment == null) {
            SurroundSelectorFragment surroundSelectorFragment2 = new SurroundSelectorFragment();
            getFragmentManager().beginTransaction().replace(android.R.id.content, surroundSelectorFragment2).commit();
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
    public void onDestroy() {
        super.onDestroy();
    }
}

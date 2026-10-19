package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import com.dolby.dax.DsParams;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementRedirector extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(getIntent());
        i.addFlags(DsParams.EFFECT_PARAM_PROFILE_PORT_PARAMETER);
        i.setComponent(new ComponentName(getApplicationContext(), (Class<?>) AudioEffectMenuActivity.class));
        String packageName = getIntent().getStringExtra("android.media.extra.PACKAGE_NAME");
        if (packageName == null) {
            String packageName2 = getCallingPackage();
            i.putExtra("android.media.extra.PACKAGE_NAME", packageName2);
        }
        startActivity(i);
        finish();
    }
}

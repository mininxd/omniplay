package com.sonyericsson.soundenhancement;

import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public class SoundEnhancementForSearchActivity extends SoundEnhancementActivity {
    private static final String LOG_TAG = "SoundEnhancement";

    @Override // com.sonyericsson.soundenhancement.SoundEnhancementActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(getIntent());
        intent.putExtra("android.media.extra.PACKAGE_NAME", "com.android.settings");
        setIntent(intent);
        super.onCreate(savedInstanceState);
    }
}

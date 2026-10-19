package com.sonyericsson.soundenhancement;

import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public class AudioEffectMenuForAudioActivity extends AudioEffectMenuActivity {
    private static final String LOG_TAG = "SoundEnhancement";

    @Override // com.sonyericsson.soundenhancement.AudioEffectMenuActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        boolean isEnabled = SoundEnhancementUtility.isSoundEnhancementEnabled(getApplicationContext());
        if (!isEnabled) {
            Intent i = new Intent(getApplicationContext(), (Class<?>) AlertDialogActivity.class);
            i.putExtra(AlertDialogActivity.DIALOG_TYPE, 0);
            i.setFlags(335544320);
            getApplicationContext().startActivity(i);
            super.onCreate(savedInstanceState);
            finish();
            return;
        }
        Intent intent = new Intent(getIntent());
        intent.putExtra(AudioEffectMenuActivity.EXTRA_START_FROM_SEARCH_RESULT, true);
        setIntent(intent);
        super.onCreate(savedInstanceState);
    }
}

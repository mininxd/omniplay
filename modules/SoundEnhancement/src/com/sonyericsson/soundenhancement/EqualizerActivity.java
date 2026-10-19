package com.sonyericsson.soundenhancement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes2.dex */
public class EqualizerActivity extends BaseActivity {
    private static final String LOG_TAG = "SoundEnhancement";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            createFragment();
        }
        setVolumeControlStream(3);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 20) {
            EqualizerFragment equalizer = (EqualizerFragment) getFragmentManager().findFragmentById(android.R.id.content);
            boolean actionbarFocused = equalizer.isFocusActionBar();
            equalizer.onKeyDown(keyCode, event);
            int rotation = getWindow().getWindowManager().getDefaultDisplay().getRotation();
            boolean isPortrait = rotation == 0 || rotation == 2;
            if (actionbarFocused && !isPortrait) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void createFragment() {
        Intent intent = new Intent(getIntent());
        Fragment fragment = new EqualizerFragment();
        intent.setClass(this, EqualizerFragment.class);
        fragment.setArguments(intent.getExtras());
        String name = getResources().getString(R.string.sound_enhance_strings_tab_equalizer_txt);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.content, fragment, name);
        ft.commit();
    }
}

package com.dolby.daxappui;

import android.content.Intent;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;
import com.dolby.dax.DolbyAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public class DaxTileService extends TileService {
    private static int mPriority = -1;
    private DolbyAudioEffect mDolbyAudio = null;

    private void updateDolbyTileUI() {
        Tile qsTile = getQsTile();
        if (qsTile == null) {
            Log.w("DaxTileService", "Failed to get tile. Skip to update tile.");
            return;
        }
        int dolbyState = getDolbyState();
        Log.d("DaxTileService", "updateDolbyTileUI, daxState = " + dolbyState);
        String strSubstring = getDolbyVersion().substring(0, 3);
        if (dolbyState == 0) {
            qsTile.setState(1);
            if (strSubstring.equals("DS1")) {
                qsTile.setLabel(getString(R.string.app_name_ds1));
            } else {
                qsTile.setLabel(getString(R.string.app_name));
            }
        } else if (dolbyState == 1) {
            qsTile.setState(2);
            qsTile.setLabel(getProfileNames()[getDolbyProfile()]);
        } else if (dolbyState == 2) {
            qsTile.setState(0);
            if (strSubstring.equals("DS1")) {
                qsTile.setLabel(getString(R.string.app_name_ds1));
            } else {
                qsTile.setLabel(getString(R.string.app_name));
            }
        }
        qsTile.updateTile();
    }

    private String[] getProfileNames() {
        return new String[]{getString(R.string.dynamic), getString(R.string.movie), getString(R.string.music), getString(R.string.custom)};
    }

    private int getDolbyProfile() {
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            try {
                return dolbyAudioEffect.getProfile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private int getDolbyState() {
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            try {
                return dolbyAudioEffect.getDsOn() ? 1 : 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 2;
    }

    private void setDolbyState() {
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            try {
                boolean z = !dolbyAudioEffect.getDsOn();
                if (!this.mDolbyAudio.hasControl()) {
                    this.mDolbyAudio.release();
                    mPriority = 0;
                    this.mDolbyAudio = new DolbyAudioEffect(mPriority, 0);
                }
                this.mDolbyAudio.setDsOn(z);
                updateDolbyTileUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getDolbyVersion() {
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            try {
                return dolbyAudioEffect.getDsVersion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "DAX3";
    }

    @Override // android.service.quicksettings.TileService
    public void onStartListening() {
        super.onStartListening();
        updateDolbyTileUI();
        Log.d("DaxTileService", "onStartListening");
    }

    @Override // android.service.quicksettings.TileService
    public void onStopListening() {
        super.onStopListening();
        Log.d("DaxTileService", "onStopListening");
    }

    @Override // android.service.quicksettings.TileService
    public void onClick() {
        super.onClick();
        setDolbyState();
        Log.d("DaxTileService", "onClick");
    }

    @Override // android.service.quicksettings.TileService, android.app.Service
    public IBinder onBind(Intent intent) {
        try {
            return super.onBind(intent);
        } catch (RuntimeException unused) {
            Log.w("DaxTileService", "onBind failed");
            return null;
        }
    }

    @Override // android.service.quicksettings.TileService
    public void onTileAdded() {
        Log.d("DaxTileService", "onTileAdded");
    }

    @Override // android.service.quicksettings.TileService
    public void onTileRemoved() {
        Log.d("DaxTileService", "onTileRemoved");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            this.mDolbyAudio = new DolbyAudioEffect(mPriority, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("DaxTileService", "onCreate() executed");
    }

    @Override // android.service.quicksettings.TileService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        DolbyAudioEffect dolbyAudioEffect = this.mDolbyAudio;
        if (dolbyAudioEffect != null) {
            dolbyAudioEffect.release();
            this.mDolbyAudio = null;
        }
        Log.d("DaxTileService", "onDestory() executed");
    }
}

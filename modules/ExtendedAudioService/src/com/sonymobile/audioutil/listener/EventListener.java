package com.sonymobile.audioutil.listener;

import android.content.Context;
import com.sonymobile.audioutil.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public abstract class EventListener {
    private static final String TAG = EventListener.class.getSimpleName();
    private Callback mCallback;
    private boolean mIsRegistered;

    public interface Callback {
        void onEvent(String str);
    }

    public EventListener(Context context, Callback callback) {
        this.mCallback = callback;
    }

    public boolean register() {
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".register mIsRegistered=" + this.mIsRegistered);
        if (this.mIsRegistered) {
            return false;
        }
        this.mIsRegistered = true;
        return true;
    }

    public boolean unregister() {
        LogUtil.d(LogUtil.LOG_TAG, TAG + ".unregister mIsRegistered=" + this.mIsRegistered);
        if (!this.mIsRegistered) {
            return false;
        }
        this.mIsRegistered = false;
        return true;
    }

    public boolean isRegistered() {
        return this.mIsRegistered;
    }

    protected void sendEvent(String event) {
        if (this.mIsRegistered) {
            this.mCallback.onEvent(event);
        }
    }
}

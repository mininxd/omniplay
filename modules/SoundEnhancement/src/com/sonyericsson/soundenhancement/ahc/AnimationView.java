package com.sonyericsson.soundenhancement.ahc;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import com.sonyericsson.soundenhancement.R;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class AnimationView {
    private static final long ANIMATION_START_DELAY = 500;
    private static final long FRAME_DURATION_MS = 50;
    private Context mContext;
    private Drawable mCurrentDrawable;
    private int[] mCurrentFrameList;
    private int mCurrentFrameListSize;
    private AnimHandler mHandler;
    private Timer mTimer;
    private int mCurrentFrameIndex = 0;
    private boolean mIsTransitionRunning = false;
    private OnAnimationStateChange mOnAnimationChange = null;
    private final int[] mInactiveFrameList = {R.drawable.ahc_inactive_anim_01, R.drawable.ahc_inactive_anim_02, R.drawable.ahc_inactive_anim_03, R.drawable.ahc_inactive_anim_04, R.drawable.ahc_inactive_anim_05, R.drawable.ahc_inactive_anim_06, R.drawable.ahc_inactive_anim_07, R.drawable.ahc_inactive_anim_08, R.drawable.ahc_inactive_anim_09, R.drawable.ahc_inactive_anim_10, R.drawable.ahc_inactive_anim_11, R.drawable.ahc_inactive_anim_12, R.drawable.ahc_inactive_anim_13, R.drawable.ahc_inactive_anim_14, R.drawable.ahc_inactive_anim_15, R.drawable.ahc_inactive_anim_16, R.drawable.ahc_inactive_anim_17, R.drawable.ahc_inactive_anim_18, R.drawable.ahc_inactive_anim_19, R.drawable.ahc_inactive_anim_20};
    private final int[] mTransitionFrameList = {R.drawable.ahc_transition_anim_01, R.drawable.ahc_transition_anim_02, R.drawable.ahc_transition_anim_03, R.drawable.ahc_transition_anim_04, R.drawable.ahc_transition_anim_05, R.drawable.ahc_transition_anim_06, R.drawable.ahc_transition_anim_07, R.drawable.ahc_transition_anim_08, R.drawable.ahc_transition_anim_09, R.drawable.ahc_transition_anim_10};
    private final int[] mActiveFrameList = {R.drawable.ahc_active_anim_01, R.drawable.ahc_active_anim_02, R.drawable.ahc_active_anim_03, R.drawable.ahc_active_anim_04, R.drawable.ahc_active_anim_05, R.drawable.ahc_active_anim_06, R.drawable.ahc_active_anim_07, R.drawable.ahc_active_anim_08, R.drawable.ahc_active_anim_09, R.drawable.ahc_active_anim_10, R.drawable.ahc_active_anim_11, R.drawable.ahc_active_anim_12, R.drawable.ahc_active_anim_13, R.drawable.ahc_active_anim_14, R.drawable.ahc_active_anim_15, R.drawable.ahc_active_anim_16, R.drawable.ahc_active_anim_17, R.drawable.ahc_active_anim_18, R.drawable.ahc_active_anim_19, R.drawable.ahc_active_anim_20, R.drawable.ahc_active_anim_21, R.drawable.ahc_active_anim_22, R.drawable.ahc_active_anim_23, R.drawable.ahc_active_anim_24, R.drawable.ahc_active_anim_25, R.drawable.ahc_active_anim_26, R.drawable.ahc_active_anim_27, R.drawable.ahc_active_anim_28, R.drawable.ahc_active_anim_29, R.drawable.ahc_active_anim_30, R.drawable.ahc_active_anim_31, R.drawable.ahc_active_anim_32, R.drawable.ahc_active_anim_33, R.drawable.ahc_active_anim_34, R.drawable.ahc_active_anim_35, R.drawable.ahc_active_anim_36, R.drawable.ahc_active_anim_37, R.drawable.ahc_active_anim_38, R.drawable.ahc_active_anim_39, R.drawable.ahc_active_anim_40, R.drawable.ahc_active_anim_41, R.drawable.ahc_active_anim_42, R.drawable.ahc_active_anim_43, R.drawable.ahc_active_anim_44, R.drawable.ahc_active_anim_45, R.drawable.ahc_active_anim_46, R.drawable.ahc_active_anim_47, R.drawable.ahc_active_anim_48, R.drawable.ahc_active_anim_49, R.drawable.ahc_active_anim_50, R.drawable.ahc_active_anim_51, R.drawable.ahc_active_anim_52, R.drawable.ahc_active_anim_53, R.drawable.ahc_active_anim_54, R.drawable.ahc_active_anim_55, R.drawable.ahc_active_anim_56, R.drawable.ahc_active_anim_57, R.drawable.ahc_active_anim_58, R.drawable.ahc_active_anim_59, R.drawable.ahc_active_anim_60};

    public interface OnAnimationStateChange {
        void onAnimationActive();

        void onAnimationChanged(Drawable drawable);

        void onAnimationInactive();
    }

    static /* synthetic */ int access$204(AnimationView x0) {
        int i = x0.mCurrentFrameIndex + 1;
        x0.mCurrentFrameIndex = i;
        return i;
    }

    public AnimationView(Context context) {
        this.mContext = null;
        this.mContext = context;
    }

    public void setOnAnimationStateChangeListener(OnAnimationStateChange listener) {
        this.mOnAnimationChange = listener;
    }

    public void runOffAnimation() {
        this.mCurrentFrameIndex = 0;
        int[] iArr = this.mInactiveFrameList;
        this.mCurrentFrameList = iArr;
        this.mCurrentFrameListSize = iArr.length - 1;
        this.mIsTransitionRunning = false;
        OnAnimationStateChange onAnimationStateChange = this.mOnAnimationChange;
        if (onAnimationStateChange != null) {
            onAnimationStateChange.onAnimationInactive();
        }
    }

    public void runOnAnimation() {
        this.mCurrentFrameIndex = 0;
        int[] iArr = this.mTransitionFrameList;
        this.mCurrentFrameList = iArr;
        this.mCurrentFrameListSize = iArr.length - 1;
        this.mIsTransitionRunning = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runActiveAnimation() {
        this.mCurrentFrameIndex = 0;
        int[] iArr = this.mActiveFrameList;
        this.mCurrentFrameList = iArr;
        this.mCurrentFrameListSize = iArr.length - 1;
        this.mIsTransitionRunning = false;
        OnAnimationStateChange onAnimationStateChange = this.mOnAnimationChange;
        if (onAnimationStateChange != null) {
            onAnimationStateChange.onAnimationActive();
        }
    }

    private class AnimHandler extends Handler {
        private AnimHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AnimationView.this.mOnAnimationChange.onAnimationChanged(AnimationView.this.mCurrentDrawable);
        }
    }

    private class AnimTask extends TimerTask {
        private AnimTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (AnimationView.access$204(AnimationView.this) >= AnimationView.this.mCurrentFrameListSize) {
                if (AnimationView.this.mIsTransitionRunning) {
                    AnimationView.this.runActiveAnimation();
                }
                AnimationView.this.mCurrentFrameIndex = 0;
            }
            AnimationView animationView = AnimationView.this;
            animationView.mCurrentDrawable = animationView.mContext.getResources().getDrawable(AnimationView.this.mCurrentFrameList[AnimationView.this.mCurrentFrameIndex]);
            AnimationView.this.mHandler.sendEmptyMessage(0);
        }
    }

    public void startAnimation() {
        this.mHandler = new AnimHandler();
        this.mCurrentFrameIndex = 0;
        this.mTimer = new Timer();
        this.mTimer.schedule(new AnimTask(), ANIMATION_START_DELAY, FRAME_DURATION_MS);
    }

    public void stopAnimation() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        AnimHandler animHandler = this.mHandler;
        if (animHandler != null) {
            animHandler.removeCallbacks(null);
        }
    }
}

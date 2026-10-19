package com.sonyericsson.soundenhancement.seekbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.sonyericsson.soundenhancement.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsVerticalSeekBar extends VerticalProgressBar {
    private static final int NO_ALPHA = 255;
    private static final int PROGRESS_ANIMATION_DURATION = 250;
    private float mDisabledAlpha;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mIsDragging;
    boolean mIsUserSeekable;
    private int mKeyProgressIncrement;
    private ObjectAnimator mPositionAnimator;
    private int mScaledTouchSlop;
    private boolean mSplitTrack;
    private final Rect mTempRect;
    private Drawable mThumb;
    private int mThumbOffset;
    private ColorStateList mThumbTintList;
    private PorterDuff.Mode mThumbTintMode;
    private float mTouchDownX;
    float mTouchProgressOffset;

    public AbsVerticalSeekBar(Context context) {
        super(context);
        this.mTempRect = new Rect();
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mIsUserSeekable = true;
        this.mKeyProgressIncrement = 1;
    }

    public AbsVerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTempRect = new Rect();
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mIsUserSeekable = true;
        this.mKeyProgressIncrement = 1;
    }

    public AbsVerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AbsVerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mTempRect = new Rect();
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mIsUserSeekable = true;
        this.mKeyProgressIncrement = 1;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SeekBar, defStyleAttr, defStyleRes);
        Drawable thumb = a.getDrawable(0);
        setThumb(thumb);
        int thumbOffset = a.getDimensionPixelOffset(1, getThumbOffset());
        setThumbOffset(thumbOffset);
        this.mSplitTrack = a.getBoolean(0, false);
        a.recycle();
        TypedArray a2 = context.obtainStyledAttributes(attrs, R.styleable.Theme, 0, 0);
        this.mDisabledAlpha = a2.getFloat(0, 0.5f);
        a2.recycle();
        applyThumbTint();
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setThumb(Drawable thumb) {
        boolean needUpdate;
        Drawable drawable = this.mThumb;
        if (drawable != null && thumb != drawable) {
            drawable.setCallback(null);
            needUpdate = true;
        } else {
            needUpdate = false;
        }
        if (thumb != null) {
            thumb.setCallback(this);
            this.mThumbOffset = thumb.getIntrinsicHeight() / 2;
            if (needUpdate && (thumb.getIntrinsicWidth() != this.mThumb.getIntrinsicWidth() || thumb.getIntrinsicHeight() != this.mThumb.getIntrinsicHeight())) {
                requestLayout();
            }
        }
        this.mThumb = thumb;
        applyThumbTint();
        invalidate();
        if (needUpdate) {
            updateThumbAndTrackPos(getWidth(), getHeight());
            if (thumb != null && thumb.isStateful()) {
                int[] state = getDrawableState();
                thumb.setState(state);
            }
        }
    }

    public Drawable getThumb() {
        return this.mThumb;
    }

    public void setThumbTintList(ColorStateList tint) {
        this.mThumbTintList = tint;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public void setThumbTintMode(PorterDuff.Mode tintMode) {
        this.mThumbTintMode = tintMode;
        this.mHasThumbTintMode = true;
        applyThumbTint();
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    private void applyThumbTint() {
        if (this.mThumb != null) {
            if (this.mHasThumbTint || this.mHasThumbTintMode) {
                this.mThumb = this.mThumb.mutate();
                if (this.mHasThumbTint) {
                    this.mThumb.setTintList(this.mThumbTintList);
                }
                if (this.mHasThumbTintMode) {
                    this.mThumb.setTintMode(this.mThumbTintMode);
                }
            }
        }
    }

    public int getThumbOffset() {
        return this.mThumbOffset;
    }

    public void setThumbOffset(int thumbOffset) {
        this.mThumbOffset = thumbOffset;
        invalidate();
    }

    public void setSplitTrack(boolean splitTrack) {
        this.mSplitTrack = splitTrack;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public void setKeyProgressIncrement(int increment) {
        this.mKeyProgressIncrement = increment < 0 ? -increment : increment;
    }

    public int getKeyProgressIncrement() {
        return this.mKeyProgressIncrement;
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar
    public synchronized void setMax(int max) {
        super.setMax(max);
        if (this.mKeyProgressIncrement == 0 || getMax() / this.mKeyProgressIncrement > 20) {
            setKeyProgressIncrement(Math.max(1, Math.round(getMax() / 20.0f)));
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return who == this.mThumb || super.verifyDrawable(who);
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setAlpha(isEnabled() ? 255 : (int) (this.mDisabledAlpha * 255.0f));
        }
        Drawable thumb = this.mThumb;
        if (thumb != null && thumb.isStateful()) {
            thumb.setState(getDrawableState());
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            drawable.setHotspot(x, y);
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar
    void onProgressRefresh(float scale, boolean fromUser) {
        super.onProgressRefresh(scale, fromUser);
        Drawable thumb = this.mThumb;
        if (!isAnimationRunning() && thumb != null) {
            setThumbPos(getHeight(), thumb, scale, Integer.MIN_VALUE);
            invalidate();
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar
    void onAnimatePosition(float scale, boolean fromUser) {
        setThumbPos(scale);
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    protected synchronized void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateThumbAndTrackPos(w, h);
    }

    private void updateThumbAndTrackPos(int w, int h) {
        int thumbOffset;
        Drawable track = getCurrentDrawable();
        Drawable thumb = this.mThumb;
        int trackWidth = Math.min(this.mMaxWidth, (w - this.mPaddingRight) - this.mPaddingLeft);
        int thumbWidth = thumb == null ? 0 : thumb.getIntrinsicWidth();
        if (thumbWidth > trackWidth) {
            int trackOffset = (thumbWidth - trackWidth) / 2;
            thumbOffset = trackOffset * (-1);
        } else {
            thumbOffset = (trackWidth - thumbWidth) / 2;
        }
        if (track != null) {
            track.setBounds(0, 0, (w - this.mPaddingRight) - this.mPaddingLeft, (h - this.mPaddingBottom) - this.mPaddingTop);
        }
        if (thumb != null) {
            setThumbPos(h, thumb, getScale(), thumbOffset);
        }
    }

    private float getScale() {
        int max = getMax();
        if (max > 0) {
            return getProgress() / max;
        }
        return 0.0f;
    }

    private void setThumbPos(float scale) {
        Drawable thumb = this.mThumb;
        if (thumb != null) {
            setThumbPos(getHeight(), thumb, scale, Integer.MIN_VALUE);
            invalidate();
        }
    }

    private void setThumbPos(int h, Drawable thumb, float scale, int offset) {
        int left;
        int right;
        int available = (h - this.mPaddingTop) - this.mPaddingBottom;
        int thumbWidth = thumb.getIntrinsicWidth();
        int thumbHeight = thumb.getIntrinsicHeight();
        int available2 = (available - thumbHeight) + (this.mThumbOffset * 2);
        int thumbPos = (int) (available2 - (((scale * 1000.0f) * available2) / 1000.0f));
        if (offset == Integer.MIN_VALUE) {
            Rect oldBounds = thumb.getBounds();
            left = oldBounds.left;
            right = oldBounds.right;
        } else {
            left = offset;
            right = offset + thumbWidth;
        }
        int bottom = thumbPos + thumbHeight;
        Drawable background = getBackground();
        if (background != null) {
            thumb.getBounds();
            int offsetY = this.mPaddingTop - this.mThumbOffset;
            int offsetX = this.mPaddingLeft;
            background.setHotspotBounds(left + offsetX, thumbPos + offsetY, right + offsetX, bottom + offsetY);
        }
        thumb.setBounds(left, thumbPos, right, bottom);
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThumb(canvas);
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar
    void drawTrack(Canvas canvas) {
        Drawable thumbDrawable = this.mThumb;
        if (thumbDrawable != null && this.mSplitTrack) {
            Rect tempRect = this.mTempRect;
            thumbDrawable.copyBounds(tempRect);
            tempRect.offset(this.mPaddingLeft - this.mThumbOffset, this.mPaddingTop);
            int saveCount = canvas.save();
            canvas.clipRect(tempRect, Region.Op.DIFFERENCE);
            super.drawTrack(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.drawTrack(canvas);
    }

    void drawThumb(Canvas canvas) {
        if (this.mThumb != null) {
            canvas.save();
            canvas.translate(this.mPaddingLeft, this.mPaddingTop - this.mThumbOffset);
            this.mThumb.draw(canvas);
            canvas.restore();
        }
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getCurrentDrawable();
        int thumbWidth = this.mThumb == null ? 0 : this.mThumb.getIntrinsicWidth();
        int dw = 0;
        int dh = 0;
        if (d != null) {
            int dw2 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, d.getIntrinsicWidth()));
            dw = Math.max(thumbWidth, dw2);
            dh = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, d.getIntrinsicHeight()));
        }
        setMeasuredDimension(resolveSizeAndState(dw + this.mPaddingLeft + this.mPaddingRight, widthMeasureSpec, 0), resolveSizeAndState(dh + this.mPaddingTop + this.mPaddingBottom, heightMeasureSpec, 0));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!this.mIsUserSeekable || !isEnabled()) {
            return false;
        }
        int action = event.getAction();
        if (action == 0) {
            setPressed(true);
            Drawable drawable = this.mThumb;
            if (drawable != null) {
                invalidate(drawable.getBounds());
            }
            onStartTrackingTouch();
            trackTouchEvent(event);
            attemptClaimDrag();
        } else if (action == 1) {
            if (this.mIsDragging) {
                trackTouchEvent(event);
                onStopTrackingTouch();
                setPressed(false);
            } else {
                onStartTrackingTouch();
                trackTouchEvent(event);
                onStopTrackingTouch();
            }
            invalidate();
        } else if (action != 2) {
            if (action == 3) {
                if (this.mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
                invalidate();
            }
        } else if (this.mIsDragging) {
            trackTouchEvent(event);
        } else {
            float x = event.getX();
            if (Math.abs(x - this.mTouchDownX) > this.mScaledTouchSlop) {
                setPressed(true);
                Drawable drawable2 = this.mThumb;
                if (drawable2 != null) {
                    invalidate(drawable2.getBounds());
                }
                onStartTrackingTouch();
                trackTouchEvent(event);
                attemptClaimDrag();
            }
        }
        return true;
    }

    private void setHotspot(float x, float y) {
        Drawable bg = getBackground();
        if (bg != null) {
            bg.setHotspot(x, y);
        }
    }

    private void trackTouchEvent(MotionEvent event) {
        float scale;
        int width = getWidth();
        int i = (width - this.mPaddingLeft) - this.mPaddingRight;
        int x = (int) event.getX();
        float progress = 0.0f;
        int height = getHeight();
        int available_height = (height - this.mPaddingTop) - this.mPaddingBottom;
        int y = height - ((int) event.getY());
        if (y < this.mPaddingBottom) {
            scale = 0.0f;
        } else if (y > height - this.mPaddingTop) {
            scale = 1.0f;
        } else {
            scale = (y - this.mPaddingBottom) / available_height;
            progress = this.mTouchProgressOffset;
        }
        int max = getMax();
        setHotspot(x, (int) event.getY());
        setProgress((int) (progress + (max * scale)), true);
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    void onKeyChange() {
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isEnabled() && isSelected()) {
            int progress = getProgress();
            boolean decrease = keyCode == 20;
            boolean increase = keyCode == 19;
            if (decrease && progress > 0) {
                animateSetProgress(progress - this.mKeyProgressIncrement);
                onKeyChange();
                return true;
            }
            if (increase && progress < getMax()) {
                animateSetProgress(this.mKeyProgressIncrement + progress);
                onKeyChange();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    boolean isAnimationRunning() {
        ObjectAnimator objectAnimator = this.mPositionAnimator;
        return objectAnimator != null && objectAnimator.isRunning();
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar
    public void setProgress(int progress, boolean fromUser) {
        if (isAnimationRunning()) {
            this.mPositionAnimator.cancel();
        }
        super.setProgress(progress, fromUser);
    }

    void animateSetProgress(int progress) {
        float curProgress = isAnimationRunning() ? getAnimationPosition() : getProgress();
        if (progress < 0) {
            progress = 0;
        } else if (progress > getMax()) {
            progress = getMax();
        }
        setProgressValueOnly(progress);
        this.mPositionAnimator = ObjectAnimator.ofFloat(this, "animationPosition", curProgress, progress);
        this.mPositionAnimator.setDuration(250L);
        this.mPositionAnimator.setAutoCancel(true);
        this.mPositionAnimator.start();
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(AbsVerticalSeekBar.class.getName());
    }

    @Override // com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(AbsVerticalSeekBar.class.getName());
        if (isEnabled()) {
            int progress = getProgress();
            if (progress > 0) {
                info.addAction(8192);
            }
            if (progress < getMax()) {
                info.addAction(4096);
            }
        }
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (super.performAccessibilityAction(action, arguments)) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        int progress = getProgress();
        int increment = Math.max(1, Math.round(getMax() / 5.0f));
        if (action != 4096) {
            if (action != 8192 || progress <= 0) {
                return false;
            }
            setProgress(progress - increment, true);
            onKeyChange();
            return true;
        }
        if (progress >= getMax()) {
            return false;
        }
        setProgress(progress + increment, true);
        onKeyChange();
        return true;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
        Drawable thumb = this.mThumb;
        if (thumb != null) {
            setThumbPos(getWidth(), thumb, getScale(), Integer.MIN_VALUE);
            invalidate();
        }
    }
}

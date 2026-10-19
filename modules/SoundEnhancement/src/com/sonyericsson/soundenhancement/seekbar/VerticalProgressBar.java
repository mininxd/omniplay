package com.sonyericsson.soundenhancement.seekbar;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes2.dex */
@RemoteViews.RemoteView
public class VerticalProgressBar extends View {
    private static final int MAX_LEVEL = 10000;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    private AccessibilityEventSender mAccessibilityEventSender;
    private AlphaAnimation mAnimation;
    private float mAnimationPosition;
    private boolean mAttached;
    private int mBehavior;
    private Drawable mCurrentDrawable;
    private int mDuration;
    private boolean mHasAnimation;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private Interpolator mInterpolator;
    private int mMax;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    boolean mMirrorForRtl;
    private Context mMyContext;
    private String mName;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    protected int mPaddingBottom;
    protected int mPaddingLeft;
    protected int mPaddingRight;
    protected int mPaddingTop;
    private int mProgress;
    private Drawable mProgressDrawable;
    private ProgressTintInfo mProgressTintInfo;
    private boolean mRefreshIsPosted;
    Bitmap mSampleTile;
    protected int mScrollX;
    protected int mScrollY;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;

    public VerticalProgressBar(Context context) {
        this(context, null);
        this.mMyContext = context;
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.progressBarStyle);
        this.mMyContext = context;
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        this.mMyContext = context;
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mMirrorForRtl = false;
        this.mMyContext = context;
        this.mUiThreadId = Thread.currentThread().getId();
        initProgressBar();
        TypedArray a = context.obtainStyledAttributes(attrs, com.sonyericsson.soundenhancement.R.styleable.ProgressBar, defStyleAttr, defStyleRes);
        this.mNoInvalidate = true;
        Drawable progressDrawable = a.getDrawable(8);
        if (progressDrawable != null) {
            setProgressDrawableTiled(progressDrawable);
        }
        this.mDuration = a.getInt(9, this.mDuration);
        this.mMinWidth = a.getDimensionPixelSize(11, this.mMinWidth);
        this.mMaxWidth = a.getDimensionPixelSize(0, this.mMaxWidth);
        this.mMinHeight = a.getDimensionPixelSize(12, this.mMinHeight);
        this.mMaxHeight = a.getDimensionPixelSize(1, this.mMaxHeight);
        this.mBehavior = a.getInt(10, this.mBehavior);
        this.mScrollX = getScrollX();
        this.mScrollY = getScrollY();
        this.mPaddingLeft = getPaddingLeft();
        this.mPaddingRight = getPaddingRight();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        int resID = a.getResourceId(13, R.anim.linear_interpolator);
        if (resID > 0) {
            setInterpolator(context, resID);
        }
        setMax(a.getInt(2, this.mMax));
        setProgress(a.getInt(3, this.mProgress));
        setSecondaryProgress(a.getInt(4, this.mSecondaryProgress));
        Drawable indeterminateDrawable = a.getDrawable(7);
        if (indeterminateDrawable != null) {
            setIndeterminateDrawableTiled(indeterminateDrawable);
        }
        this.mOnlyIndeterminate = a.getBoolean(6, this.mOnlyIndeterminate);
        this.mNoInvalidate = false;
        setIndeterminate(this.mOnlyIndeterminate || a.getBoolean(5, this.mIndeterminate));
        this.mMirrorForRtl = a.getBoolean(5, this.mMirrorForRtl);
        a.recycle();
        applyProgressTints();
        applyIndeterminateTint();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private Drawable tileify(Drawable drawable, boolean clip) {
        if (drawable instanceof LayerDrawable) {
            LayerDrawable background = (LayerDrawable) drawable;
            int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];
            for (int i = 0; i < N; i++) {
                int id = background.getId(i);
                outDrawables[i] = tileify(background.getDrawable(i), id == 16908301 || id == 16908303);
            }
            LayerDrawable newBg = new LayerDrawable(outDrawables);
            for (int i2 = 0; i2 < N; i2++) {
                newBg.setId(i2, background.getId(i2));
            }
            return newBg;
        }
        if (drawable instanceof StateListDrawable) {
            StateListDrawable out = new StateListDrawable();
            return out;
        }
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmap = (BitmapDrawable) drawable;
            Bitmap tileBitmap = bitmap.getBitmap();
            if (this.mSampleTile == null) {
                this.mSampleTile = tileBitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            BitmapShader bitmapShader = new BitmapShader(tileBitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
            shapeDrawable.getPaint().setShader(bitmapShader);
            return clip ? new ClipDrawable(shapeDrawable, 80, 2) : shapeDrawable;
        }
        return drawable;
    }

    Shape getDrawableShape() {
        float[] roundedCorners = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
        return new RoundRectShape(roundedCorners, null, null);
    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable background = (AnimationDrawable) drawable;
            int N = background.getNumberOfFrames();
            AnimationDrawable newBg = new AnimationDrawable();
            newBg.setOneShot(background.isOneShot());
            for (int i = 0; i < N; i++) {
                Drawable frame = tileify(background.getFrame(i), true);
                frame.setLevel(MAX_LEVEL);
                newBg.addFrame(frame, background.getDuration(i));
            }
            newBg.setLevel(MAX_LEVEL);
            return newBg;
        }
        return drawable;
    }

    private synchronized void initProgressBar() {
        this.mMax = 100;
        this.mProgress = 0;
        this.mSecondaryProgress = 0;
        this.mIndeterminate = false;
        this.mOnlyIndeterminate = false;
        this.mDuration = 4000;
        this.mBehavior = 1;
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
    }

    @ViewDebug.ExportedProperty(category = NotificationCompat.CATEGORY_PROGRESS)
    public synchronized boolean isIndeterminate() {
        return this.mIndeterminate;
    }

    public synchronized void setIndeterminate(boolean indeterminate) {
        if ((!this.mOnlyIndeterminate || !this.mIndeterminate) && indeterminate != this.mIndeterminate) {
            this.mIndeterminate = indeterminate;
            if (indeterminate) {
                this.mCurrentDrawable = this.mIndeterminateDrawable;
                startAnimation();
            } else {
                this.mCurrentDrawable = this.mProgressDrawable;
                stopAnimation();
            }
        }
    }

    public Drawable getIndeterminateDrawable() {
        return this.mIndeterminateDrawable;
    }

    public void setIndeterminateDrawable(Drawable d) {
        Drawable drawable = this.mIndeterminateDrawable;
        if (drawable != d) {
            if (drawable != null) {
                drawable.setCallback(null);
                unscheduleDrawable(this.mIndeterminateDrawable);
            }
            this.mIndeterminateDrawable = d;
            if (d != null) {
                d.setCallback(this);
                if (d.isStateful()) {
                    d.setState(getDrawableState());
                }
                applyIndeterminateTint();
            }
            synchronized (this) {
                if (this.mIndeterminate) {
                    this.mCurrentDrawable = d;
                    postInvalidate();
                }
            }
        }
    }

    public void setIndeterminateTintList(ColorStateList tint) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mIndeterminateTintList = tint;
        progressTintInfo.mHasIndeterminateTint = true;
        applyIndeterminateTint();
    }

    public ColorStateList getIndeterminateTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mIndeterminateTintList;
        }
        return null;
    }

    public void setIndeterminateTintMode(PorterDuff.Mode tintMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mIndeterminateTintMode = tintMode;
        progressTintInfo.mHasIndeterminateTintMode = true;
        applyIndeterminateTint();
    }

    public PorterDuff.Mode getIndeterminateTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mIndeterminateTintMode;
        }
        return null;
    }

    private void applyIndeterminateTint() {
        if (this.mIndeterminateDrawable != null && this.mProgressTintInfo != null) {
            ProgressTintInfo tintInfo = this.mProgressTintInfo;
            if (tintInfo.mHasIndeterminateTint || tintInfo.mHasIndeterminateTintMode) {
                this.mIndeterminateDrawable = this.mIndeterminateDrawable.mutate();
                if (tintInfo.mHasIndeterminateTint) {
                    this.mIndeterminateDrawable.setTintList(tintInfo.mIndeterminateTintList);
                }
                if (tintInfo.mHasIndeterminateTintMode) {
                    this.mIndeterminateDrawable.setTintMode(tintInfo.mIndeterminateTintMode);
                }
            }
        }
    }

    public void setIndeterminateDrawableTiled(Drawable d) {
        if (d != null) {
            d = tileifyIndeterminate(d);
        }
        setIndeterminateDrawable(d);
    }

    public Drawable getProgressDrawable() {
        return this.mProgressDrawable;
    }

    public void setProgressDrawable(Drawable d) {
        Drawable drawable = this.mProgressDrawable;
        if (drawable != d) {
            if (drawable != null) {
                drawable.setCallback(null);
                unscheduleDrawable(this.mProgressDrawable);
            }
            this.mProgressDrawable = d;
            if (d != null) {
                d.setCallback(this);
                if (d.isStateful()) {
                    d.setState(getDrawableState());
                }
                int drawableHeight = d.getMinimumHeight();
                synchronized (this) {
                    if (this.mMaxHeight < drawableHeight) {
                        this.mMaxHeight = drawableHeight;
                        requestLayout();
                    }
                }
                applyProgressTints();
            }
            synchronized (this) {
                if (!this.mIndeterminate) {
                    this.mCurrentDrawable = d;
                    postInvalidate();
                }
            }
            updateDrawableBounds(getWidth(), getHeight());
            updateDrawableState();
            doRefreshProgress(R.id.progress, this.mProgress, false, false);
            doRefreshProgress(R.id.secondaryProgress, this.mSecondaryProgress, false, false);
        }
    }

    private void applyProgressTints() {
        if (this.mProgressDrawable != null && this.mProgressTintInfo != null) {
            applyPrimaryProgressTint();
            applyProgressBackgroundTint();
            applySecondaryProgressTint();
        }
    }

    private void applyPrimaryProgressTint() {
        Drawable target;
        if ((this.mProgressTintInfo.mHasProgressTint || this.mProgressTintInfo.mHasProgressTintMode) && (target = getTintTarget(R.id.progress, true)) != null) {
            if (this.mProgressTintInfo.mHasProgressTint) {
                target.setTintList(this.mProgressTintInfo.mProgressTintList);
            }
            if (this.mProgressTintInfo.mHasProgressTintMode) {
                target.setTintMode(this.mProgressTintInfo.mProgressTintMode);
            }
        }
    }

    private void applyProgressBackgroundTint() {
        Drawable target;
        if ((this.mProgressTintInfo.mHasProgressBackgroundTint || this.mProgressTintInfo.mHasProgressBackgroundTintMode) && (target = getTintTarget(R.id.background, false)) != null) {
            if (this.mProgressTintInfo.mHasProgressBackgroundTint) {
                target.setTintList(this.mProgressTintInfo.mProgressBackgroundTintList);
            }
            if (this.mProgressTintInfo.mHasProgressBackgroundTintMode) {
                target.setTintMode(this.mProgressTintInfo.mProgressBackgroundTintMode);
            }
        }
    }

    private void applySecondaryProgressTint() {
        Drawable target;
        if ((this.mProgressTintInfo.mHasSecondaryProgressTint || this.mProgressTintInfo.mHasSecondaryProgressTintMode) && (target = getTintTarget(R.id.secondaryProgress, false)) != null) {
            if (this.mProgressTintInfo.mHasSecondaryProgressTint) {
                target.setTintList(this.mProgressTintInfo.mSecondaryProgressTintList);
            }
            if (this.mProgressTintInfo.mHasSecondaryProgressTintMode) {
                target.setTintMode(this.mProgressTintInfo.mSecondaryProgressTintMode);
            }
        }
    }

    public void setProgressTintList(ColorStateList tint) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressTintList = tint;
        progressTintInfo.mHasProgressTint = true;
        if (this.mProgressDrawable != null) {
            applyPrimaryProgressTint();
        }
    }

    public ColorStateList getProgressTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressTintList;
        }
        return null;
    }

    public void setProgressTintMode(PorterDuff.Mode tintMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressTintMode = tintMode;
        progressTintInfo.mHasProgressTintMode = true;
        if (this.mProgressDrawable != null) {
            applyPrimaryProgressTint();
        }
    }

    public PorterDuff.Mode getProgressTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressTintMode;
        }
        return null;
    }

    public void setProgressBackgroundTintList(ColorStateList tint) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressBackgroundTintList = tint;
        progressTintInfo.mHasProgressBackgroundTint = true;
        if (this.mProgressDrawable != null) {
            applyProgressBackgroundTint();
        }
    }

    public ColorStateList getProgressBackgroundTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBackgroundTintList;
        }
        return null;
    }

    public void setProgressBackgroundTintMode(PorterDuff.Mode tintMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mProgressBackgroundTintMode = tintMode;
        progressTintInfo.mHasProgressBackgroundTintMode = true;
        if (this.mProgressDrawable != null) {
            applyProgressBackgroundTint();
        }
    }

    public PorterDuff.Mode getProgressBackgroundTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mProgressBackgroundTintMode;
        }
        return null;
    }

    public void setSecondaryProgressTintList(ColorStateList tint) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mSecondaryProgressTintList = tint;
        progressTintInfo.mHasSecondaryProgressTint = true;
        if (this.mProgressDrawable != null) {
            applySecondaryProgressTint();
        }
    }

    public ColorStateList getSecondaryProgressTintList() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mSecondaryProgressTintList;
        }
        return null;
    }

    public void setSecondaryProgressTintMode(PorterDuff.Mode tintMode) {
        if (this.mProgressTintInfo == null) {
            this.mProgressTintInfo = new ProgressTintInfo();
        }
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        progressTintInfo.mSecondaryProgressTintMode = tintMode;
        progressTintInfo.mHasSecondaryProgressTintMode = true;
        if (this.mProgressDrawable != null) {
            applySecondaryProgressTint();
        }
    }

    public PorterDuff.Mode getSecondaryProgressTintMode() {
        ProgressTintInfo progressTintInfo = this.mProgressTintInfo;
        if (progressTintInfo != null) {
            return progressTintInfo.mSecondaryProgressTintMode;
        }
        return null;
    }

    private Drawable getTintTarget(int layerId, boolean shouldFallback) {
        Drawable layer = null;
        Drawable d = this.mProgressDrawable;
        if (d == null) {
            return null;
        }
        this.mProgressDrawable = d.mutate();
        if (d instanceof LayerDrawable) {
            layer = ((LayerDrawable) d).findDrawableByLayerId(layerId);
        }
        if (shouldFallback && layer == null) {
            return d;
        }
        return layer;
    }

    public void setProgressDrawableTiled(Drawable d) {
        if (d != null) {
            d = tileify(d, false);
        }
        setProgressDrawable(d);
    }

    synchronized Drawable getCurrentDrawable() {
        return this.mCurrentDrawable;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return who == this.mProgressDrawable || who == this.mIndeterminateDrawable || super.verifyDrawable(who);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public void postInvalidate() {
        if (!this.mNoInvalidate) {
            super.postInvalidate();
        }
    }

    private void setDrawableTint(int id, ColorStateList tint, PorterDuff.Mode tintMode, boolean fallback) {
        Drawable layer = null;
        Drawable d = this.mCurrentDrawable;
        if (d instanceof LayerDrawable) {
            layer = ((LayerDrawable) d).findDrawableByLayerId(id);
        }
        if (fallback && layer == null) {
            layer = d;
        }
        layer.mutate();
        layer.setTintList(tint);
        layer.setTintMode(tintMode);
    }

    private float getScale(float progress) {
        int i = this.mMax;
        if (i > 0) {
            return progress / i;
        }
        return 0.0f;
    }

    private synchronized void doRefreshProgress(int id, float progress, boolean fromUser, boolean callBackToApp) {
        doRefreshProgress(id, progress, fromUser, callBackToApp, false);
    }

    private synchronized void doRefreshProgress(int id, float progress, boolean fromUser, boolean callBackToApp, boolean animate) {
        float scale = getScale(progress);
        Drawable d = this.mCurrentDrawable;
        if (d != null) {
            Drawable progressDrawable = null;
            if (d instanceof LayerDrawable) {
                progressDrawable = ((LayerDrawable) d).findDrawableByLayerId(id);
            }
            int level = (int) (10000.0f * scale);
            (progressDrawable != null ? progressDrawable : d).setLevel(level);
        } else {
            invalidate();
        }
        if (id == 16908301) {
            if (animate) {
                onAnimatePosition(scale, fromUser);
            } else if (callBackToApp) {
                onProgressRefresh(scale, fromUser);
            }
        }
    }

    void onAnimatePosition(float scale, boolean fromUser) {
    }

    void setProgressValueOnly(int progress) {
        this.mProgress = progress;
        onProgressRefresh(getScale(progress), true);
    }

    void setAnimationPosition(float position) {
        this.mAnimationPosition = position;
        refreshProgress(R.id.progress, position, true, true);
    }

    float getAnimationPosition() {
        return this.mAnimationPosition;
    }

    void onProgressRefresh(float scale, boolean fromUser) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.mMyContext.getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            scheduleAccessibilityEventSender();
        }
    }

    private synchronized void refreshProgress(int id, float progress, boolean fromUser) {
        refreshProgress(id, progress, fromUser, false);
    }

    private synchronized void refreshProgress(int id, float progress, boolean fromUser, boolean animate) {
        doRefreshProgress(id, progress, fromUser, true, animate);
    }

    public synchronized void setProgress(int progress) {
        setProgress(progress, false);
    }

    synchronized void setProgress(int progress, boolean fromUser) {
        if (this.mIndeterminate) {
            return;
        }
        if (progress < 0) {
            progress = 0;
        }
        if (progress > this.mMax) {
            progress = this.mMax;
        }
        if (progress != this.mProgress) {
            this.mProgress = progress;
            refreshProgress(R.id.progress, this.mProgress, fromUser);
        }
    }

    public synchronized void setSecondaryProgress(int secondaryProgress) {
        if (this.mIndeterminate) {
            return;
        }
        if (secondaryProgress < 0) {
            secondaryProgress = 0;
        }
        if (secondaryProgress > this.mMax) {
            secondaryProgress = this.mMax;
        }
        if (secondaryProgress != this.mSecondaryProgress) {
            this.mSecondaryProgress = secondaryProgress;
            refreshProgress(R.id.secondaryProgress, this.mSecondaryProgress, false);
        }
    }

    @ViewDebug.ExportedProperty(category = NotificationCompat.CATEGORY_PROGRESS)
    public synchronized int getProgress() {
        return this.mIndeterminate ? 0 : this.mProgress;
    }

    @ViewDebug.ExportedProperty(category = NotificationCompat.CATEGORY_PROGRESS)
    public synchronized int getSecondaryProgress() {
        return this.mIndeterminate ? 0 : this.mSecondaryProgress;
    }

    @ViewDebug.ExportedProperty(category = NotificationCompat.CATEGORY_PROGRESS)
    public synchronized int getMax() {
        return this.mMax;
    }

    public synchronized void setMax(int max) {
        if (max < 0) {
            max = 0;
        }
        if (max != this.mMax) {
            this.mMax = max;
            postInvalidate();
            if (this.mProgress > max) {
                this.mProgress = max;
            }
            refreshProgress(R.id.progress, this.mProgress, false);
        }
    }

    public final synchronized void incrementProgressBy(int diff) {
        setProgress(this.mProgress + diff);
    }

    public final synchronized void incrementSecondaryProgressBy(int diff) {
        setSecondaryProgress(this.mSecondaryProgress + diff);
    }

    synchronized void startAnimation() {
        if (getVisibility() != 0) {
            return;
        }
        if (this.mIndeterminateDrawable instanceof Animatable) {
            this.mShouldStartAnimationDrawable = true;
            this.mHasAnimation = false;
        } else {
            this.mHasAnimation = true;
            if (this.mInterpolator == null) {
                this.mInterpolator = new LinearInterpolator();
            }
            if (this.mTransformation == null) {
                this.mTransformation = new Transformation();
            } else {
                this.mTransformation.clear();
            }
            if (this.mAnimation == null) {
                this.mAnimation = new AlphaAnimation(0.0f, 1.0f);
            } else {
                this.mAnimation.reset();
            }
            this.mAnimation.setRepeatMode(this.mBehavior);
            this.mAnimation.setRepeatCount(-1);
            this.mAnimation.setDuration(this.mDuration);
            this.mAnimation.setInterpolator(this.mInterpolator);
            this.mAnimation.setStartTime(-1L);
        }
        postInvalidate();
    }

    synchronized void stopAnimation() {
        this.mHasAnimation = false;
        if (this.mIndeterminateDrawable instanceof Animatable) {
            ((Animatable) this.mIndeterminateDrawable).stop();
            this.mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    public void setInterpolator(Context context, int resID) {
        setInterpolator(AnimationUtils.loadInterpolator(context, resID));
    }

    public synchronized void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public synchronized Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    @Override // android.view.View
    public void setVisibility(int v) {
        if (getVisibility() != v) {
            super.setVisibility(v);
            if (this.mIndeterminate) {
                if (v == 8 || v == 4) {
                    stopAnimation();
                } else {
                    startAnimation();
                }
            }
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (this.mIndeterminate) {
            if (visibility == 8 || visibility == 4) {
                stopAnimation();
            } else {
                startAnimation();
            }
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public synchronized void invalidateDrawable(Drawable dr) {
        if (!this.mInDrawing) {
            if (verifyDrawable(dr)) {
                Rect dirty = dr.getBounds();
                int scrollX = this.mScrollX + this.mPaddingLeft;
                int scrollY = this.mScrollY + this.mPaddingTop;
                invalidate(dirty.left + scrollX, dirty.top + scrollY, dirty.right + scrollX, dirty.bottom + scrollY);
            } else {
                super.invalidateDrawable(dr);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        updateDrawableBounds(w, h);
    }

    private void updateDrawableBounds(int w, int h) {
        int w2 = w - (this.mPaddingRight + this.mPaddingLeft);
        int h2 = h - (this.mPaddingTop + this.mPaddingBottom);
        int right = w2;
        int bottom = h2;
        int top = 0;
        int left = 0;
        Drawable drawable = this.mIndeterminateDrawable;
        if (drawable != null) {
            if (this.mOnlyIndeterminate && !(drawable instanceof AnimationDrawable)) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = this.mIndeterminateDrawable.getIntrinsicHeight();
                float intrinsicAspect = intrinsicWidth / intrinsicHeight;
                float boundAspect = w2 / h2;
                if (intrinsicAspect != boundAspect) {
                    if (boundAspect > intrinsicAspect) {
                        int width = (int) (h2 * intrinsicAspect);
                        int left2 = (w2 - width) / 2;
                        right = width + left2;
                        left = left2;
                    } else {
                        int height = (int) (w2 * (1.0f / intrinsicAspect));
                        int top2 = (h2 - height) / 2;
                        bottom = top2 + height;
                        top = top2;
                    }
                }
            }
            this.mIndeterminateDrawable.setBounds(left, top, right, bottom);
        }
        Drawable drawable2 = this.mProgressDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, right, bottom);
        }
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void drawTrack(Canvas canvas) {
        Drawable drawable = this.mCurrentDrawable;
        if (drawable != 0) {
            int saveCount = canvas.save();
            canvas.translate(this.mPaddingLeft, this.mPaddingTop);
            long time = getDrawingTime();
            if (this.mHasAnimation) {
                this.mAnimation.getTransformation(time, this.mTransformation);
                float scale = this.mTransformation.getAlpha();
                try {
                    this.mInDrawing = true;
                    drawable.setLevel((int) (10000.0f * scale));
                    this.mInDrawing = false;
                    postInvalidateOnAnimation();
                } catch (Throwable th) {
                    this.mInDrawing = false;
                    throw th;
                }
            }
            drawable.draw(canvas);
            canvas.restoreToCount(saveCount);
            if (this.mShouldStartAnimationDrawable && (drawable instanceof Animatable)) {
                ((Animatable) drawable).start();
                this.mShouldStartAnimationDrawable = false;
            }
        }
    }

    @Override // android.view.View
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = this.mCurrentDrawable;
        int dw = 0;
        int dh = 0;
        if (d != null) {
            dw = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, d.getIntrinsicWidth()));
            dh = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, d.getIntrinsicHeight()));
        }
        updateDrawableState();
        setMeasuredDimension(resolveSizeAndState(dw + this.mPaddingLeft + this.mPaddingRight, widthMeasureSpec, 0), resolveSizeAndState(dh + this.mPaddingTop + this.mPaddingBottom, heightMeasureSpec, 0));
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    private void updateDrawableState() {
        int[] state = getDrawableState();
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null && drawable.isStateful()) {
            this.mProgressDrawable.setState(state);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null && drawable2.isStateful()) {
            this.mIndeterminateDrawable.setState(state);
        }
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            drawable.setHotspot(x, y);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            drawable2.setHotspot(x, y);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sonyericsson.soundenhancement.seekbar.VerticalProgressBar.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int progress;
        int secondaryProgress;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.progress = in.readInt();
            this.secondaryProgress = in.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.progress);
            out.writeInt(this.secondaryProgress);
        }
    }

    @Override // android.view.View
    public synchronized Parcelable onSaveInstanceState() {
        SavedState ss;
        Parcelable superState = super.onSaveInstanceState();
        ss = new SavedState(superState);
        ss.progress = this.mProgress;
        ss.secondaryProgress = this.mSecondaryProgress;
        return ss;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setProgress(ss.progress);
        setSecondaryProgress(ss.secondaryProgress);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIndeterminate) {
            startAnimation();
        }
        this.mAttached = true;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (this.mIndeterminate) {
            stopAnimation();
        }
        AccessibilityEventSender accessibilityEventSender = this.mAccessibilityEventSender;
        if (accessibilityEventSender != null) {
            removeCallbacks(accessibilityEventSender);
        }
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(VerticalProgressBar.class.getName());
        event.setItemCount(this.mMax);
        event.setCurrentItemIndex(this.mProgress);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(VerticalProgressBar.class.getName());
    }

    private void scheduleAccessibilityEventSender() {
        AccessibilityEventSender accessibilityEventSender = this.mAccessibilityEventSender;
        if (accessibilityEventSender == null) {
            this.mAccessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(accessibilityEventSender);
        }
        postDelayed(this.mAccessibilityEventSender, 200L);
    }

    private class AccessibilityEventSender implements Runnable {
        private AccessibilityEventSender() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VerticalProgressBar.this.sendAccessibilityEvent(4);
        }
    }

    private static class ProgressTintInfo {
        boolean mHasIndeterminateTint;
        boolean mHasIndeterminateTintMode;
        boolean mHasProgressBackgroundTint;
        boolean mHasProgressBackgroundTintMode;
        boolean mHasProgressTint;
        boolean mHasProgressTintMode;
        boolean mHasSecondaryProgressTint;
        boolean mHasSecondaryProgressTintMode;
        ColorStateList mIndeterminateTintList;
        PorterDuff.Mode mIndeterminateTintMode;
        ColorStateList mProgressBackgroundTintList;
        PorterDuff.Mode mProgressBackgroundTintMode;
        ColorStateList mProgressTintList;
        PorterDuff.Mode mProgressTintMode;
        ColorStateList mSecondaryProgressTintList;
        PorterDuff.Mode mSecondaryProgressTintMode;

        private ProgressTintInfo() {
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 32768) {
            event.getText().add(this.mName);
        }
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    public void setText(String str) {
        this.mName = str;
    }
}

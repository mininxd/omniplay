package com.dolby.daxappui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.math.BigDecimal;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class GeqView extends View {
    private DAXConfiguration mConfiguration;
    private Context mContext;
    private float mCurrentYPosition;
    private IDsFragObserver mFObserver;
    private float[] mGeqData;
    private Paint mGeqDataTitlePaint;
    private Paint mHLinePaint;
    private Paint mRecPaint;
    private Rect mRect;
    public int mSelectedBar;
    private Path mStokeLinePath;
    private Paint mStrokeLinePaint;
    private Paint mTitlePaint;
    private int[] mUserGain;
    private int[] mXLeftPosition;
    private int[] mXRightPosition;
    private final String[] yTitlesStrings;

    private void init() {
        this.mTitlePaint = new Paint(1);
        this.mTitlePaint.setTextAlign(Paint.Align.RIGHT);
        this.mRecPaint = new Paint();
        this.mHLinePaint = new Paint();
        this.mStrokeLinePaint = new Paint(1);
        this.mStrokeLinePaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
        this.mStokeLinePath = new Path();
        this.mGeqDataTitlePaint = new Paint(1);
        this.mRect = new Rect();
        updateThisData(this.mGeqData);
    }

    private void updateUserGain() {
        DAXConfiguration dAXConfiguration = this.mConfiguration;
        if (dAXConfiguration == null) {
            return;
        }
        if (this.mGeqData[this.mSelectedBar] >= dAXConfiguration.getMinEditGain() && this.mGeqData[this.mSelectedBar] <= this.mConfiguration.getMaxEditGain()) {
            float fFloor = (float) Math.floor(this.mGeqData[this.mSelectedBar]);
            float[] fArr = this.mGeqData;
            int i = this.mSelectedBar;
            if (fArr[i] - fFloor < 0.25f) {
                fArr[i] = fFloor;
            } else if (fArr[i] - fFloor >= 0.25f && fArr[i] - fFloor <= 0.75f) {
                fArr[i] = fFloor + 0.5f;
            } else {
                float[] fArr2 = this.mGeqData;
                int i2 = this.mSelectedBar;
                if (fArr2[i2] - fFloor > 0.75f) {
                    fArr2[i2] = fFloor + 1.0f;
                }
            }
        }
        if (this.mGeqData[this.mSelectedBar] < this.mConfiguration.getMinEditGain()) {
            this.mGeqData[this.mSelectedBar] = this.mConfiguration.getMinEditGain();
        } else if (this.mGeqData[this.mSelectedBar] > this.mConfiguration.getMaxEditGain()) {
            this.mGeqData[this.mSelectedBar] = this.mConfiguration.getMaxEditGain();
        }
        int i3 = this.mSelectedBar;
        if (i3 == 0) {
            int[] iArr = this.mUserGain;
            float[] fArr3 = this.mGeqData;
            iArr[i3] = (int) (fArr3[i3] * 16.0f);
            iArr[i3 + 1] = (int) (((fArr3[i3] + fArr3[i3 + 1]) / 2.0f) * 16.0f);
        } else if (i3 == 9) {
            int[] iArr2 = this.mUserGain;
            float[] fArr4 = this.mGeqData;
            iArr2[(i3 * 2) - 1] = (int) (((fArr4[i3 - 1] + fArr4[i3]) / 2.0f) * 16.0f);
            iArr2[i3 * 2] = (int) (fArr4[i3] * 16.0f);
            iArr2[(i3 * 2) + 1] = (int) (fArr4[i3] * 16.0f);
        } else {
            int[] iArr3 = this.mUserGain;
            float[] fArr5 = this.mGeqData;
            iArr3[(i3 * 2) - 1] = (int) (((fArr5[i3 - 1] + fArr5[i3]) / 2.0f) * 16.0f);
            iArr3[i3 * 2] = (int) (fArr5[i3] * 16.0f);
            iArr3[(i3 * 2) + 1] = (int) (((fArr5[i3] + fArr5[i3 + 1]) / 2.0f) * 16.0f);
        }
        DsClientSettings.INSTANCE.setGraphicEqualizerBandGains(this.mFObserver, this.mUserGain);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GeqView(Context context) {
        super(context);
        this.yTitlesStrings = new String[]{"+12dB", "0dB", "-12dB"};
        this.mGeqData = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.mUserGain = new int[20];
        this.mXLeftPosition = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mXRightPosition = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mSelectedBar = -1;
        init();
        try {
            this.mFObserver = (IDsFragObserver) context;
            this.mContext = context;
            this.mConfiguration = DAXConfiguration.getInstance(this.mContext.getApplicationContext());
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement IDsFragObserver");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GeqView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.yTitlesStrings = new String[]{"+12dB", "0dB", "-12dB"};
        this.mGeqData = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.mUserGain = new int[20];
        this.mXLeftPosition = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mXRightPosition = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.mSelectedBar = -1;
        init();
        try {
            this.mFObserver = (IDsFragObserver) context;
            this.mContext = context;
            this.mConfiguration = DAXConfiguration.getInstance(this.mContext.getApplicationContext());
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement IDsFragObserver");
        }
    }

    public void updateThisData(float[] fArr) {
        this.mGeqData = fArr;
        postInvalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int height = getHeight() - (getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin) * 2);
        if (this.mConfiguration == null) {
            return true;
        }
        int i = 0;
        if (action == 0) {
            while (i < this.mGeqData.length) {
                if (x > this.mXLeftPosition[i] && x < this.mXRightPosition[i]) {
                    this.mSelectedBar = i;
                    break;
                }
                i++;
            }
            this.mCurrentYPosition = y;
            postInvalidate();
        } else if (action != 1) {
            if (action == 2) {
                float f = this.mCurrentYPosition - y;
                while (true) {
                    float[] fArr = this.mGeqData;
                    if (i < fArr.length) {
                        if (x > this.mXLeftPosition[i] && x < this.mXRightPosition[i]) {
                            fArr[i] = ((f / height) * 10.0f) + fArr[i];
                            if (fArr[i] < this.mConfiguration.getMinEditGain()) {
                                this.mGeqData[i] = this.mConfiguration.getMinEditGain();
                            } else if (this.mGeqData[i] > this.mConfiguration.getMaxEditGain()) {
                                this.mGeqData[i] = this.mConfiguration.getMaxEditGain();
                            }
                            int i2 = this.mSelectedBar;
                            if (i2 != i && i2 != -1) {
                                updateUserGain();
                                postInvalidate();
                            }
                            if (i == 0) {
                                int[] iArr = this.mUserGain;
                                float[] fArr2 = this.mGeqData;
                                iArr[i] = (int) (fArr2[i] * 16.0f);
                                int i3 = i + 1;
                                iArr[i3] = (int) (((fArr2[i] + fArr2[i3]) / 2.0f) * 16.0f);
                            } else if (i == 9) {
                                int[] iArr2 = this.mUserGain;
                                int i4 = i * 2;
                                float[] fArr3 = this.mGeqData;
                                iArr2[i4 - 1] = (int) (((fArr3[i - 1] + fArr3[i]) / 2.0f) * 16.0f);
                                iArr2[i4] = (int) (fArr3[i] * 16.0f);
                                iArr2[i4 + 1] = (int) (fArr3[i] * 16.0f);
                            } else if (i > 0 && i < 9) {
                                int[] iArr3 = this.mUserGain;
                                int i5 = i * 2;
                                float[] fArr4 = this.mGeqData;
                                iArr3[i5 - 1] = (int) (((fArr4[i - 1] + fArr4[i]) / 2.0f) * 16.0f);
                                iArr3[i5] = (int) (fArr4[i] * 16.0f);
                                iArr3[i5 + 1] = (int) (((fArr4[i] + fArr4[i + 1]) / 2.0f) * 16.0f);
                            }
                            DsClientSettings.INSTANCE.setGraphicEqualizerBandGains(this.mFObserver, this.mUserGain);
                            this.mSelectedBar = i;
                            updateThisData(this.mGeqData);
                            this.mCurrentYPosition = y;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
        } else if (this.mSelectedBar != -1) {
            updateUserGain();
            this.mSelectedBar = -1;
            postInvalidate();
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        float f;
        float f2;
        float f3;
        int i;
        float dimensionPixelSize;
        int dimensionPixelSize2;
        float f4;
        super.onDraw(canvas);
        boolean z = getResources().getBoolean(R.bool.tabletLayout);
        DsClientSettings dsClientSettings = DsClientSettings.INSTANCE;
        IDsFragObserver iDsFragObserver = this.mFObserver;
        boolean graphicEqualizerOn = dsClientSettings.getGraphicEqualizerOn(iDsFragObserver, iDsFragObserver.getActivePort());
        float f5 = getContext().getResources().getDisplayMetrics().density;
        int height = getHeight();
        Resources resources = getResources();
        int i2 = R.dimen.geq_border_vertical_margin;
        int dimensionPixelSize3 = height - (resources.getDimensionPixelSize(R.dimen.geq_border_vertical_margin) * 2);
        if (z) {
            width = getWidth() - getResources().getDimensionPixelSize(R.dimen.geq_bar_end_padding);
        } else {
            width = getWidth();
        }
        float f6 = dimensionPixelSize3 / 2.0f;
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        int i3 = 1;
        if (layoutDirectionFromLocale == 1) {
            this.mTitlePaint.setTextAlign(Paint.Align.LEFT);
        } else {
            this.mTitlePaint.setTextAlign(Paint.Align.RIGHT);
        }
        this.mTitlePaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.geq_text_size));
        if (graphicEqualizerOn) {
            this.mTitlePaint.setColor(getResources().getColor(R.color.colorGeqGainText, this.mContext.getTheme()));
        } else {
            this.mTitlePaint.setColor(getResources().getColor(R.color.colorGeqDisableText, this.mContext.getTheme()));
        }
        Paint.FontMetrics fontMetrics = this.mTitlePaint.getFontMetrics();
        int i4 = (int) fontMetrics.descent;
        float f7 = fontMetrics.bottom - fontMetrics.top;
        float fMeasureText = this.mTitlePaint.measureText(this.yTitlesStrings[0]);
        if (layoutDirectionFromLocale == 1) {
            fMeasureText = getWidth() - fMeasureText;
        }
        int i5 = 0;
        while (i5 < this.yTitlesStrings.length) {
            if (i5 == 0) {
                f4 = f7 - i4;
                dimensionPixelSize = getResources().getDimensionPixelSize(i2);
            } else {
                if (i5 == i3) {
                    dimensionPixelSize = ((f7 / 2.0f) + f6) - i4;
                    dimensionPixelSize2 = getResources().getDimensionPixelSize(i2);
                } else {
                    dimensionPixelSize = (f6 * 2.0f) - i4;
                    dimensionPixelSize2 = getResources().getDimensionPixelSize(i2);
                }
                f4 = dimensionPixelSize2;
            }
            canvas.drawText(this.yTitlesStrings[i5], fMeasureText, f4 + dimensionPixelSize, this.mTitlePaint);
            i5++;
            i2 = R.dimen.geq_border_vertical_margin;
            i3 = 1;
        }
        int dimensionPixelSize4 = (int) ((width - getResources().getDimensionPixelSize(R.dimen.geq_text_and_bar_margin)) / this.mGeqData.length);
        if (z) {
            f = dimensionPixelSize4;
            f2 = 0.85f * f;
            f3 = 0.15f;
        } else {
            f = dimensionPixelSize4;
            f2 = 0.92f * f;
            f3 = 0.08f;
        }
        float f8 = f * f3;
        float f9 = f2;
        int left = getLeft() + getResources().getDimensionPixelSize(R.dimen.geq_text_and_bar_margin);
        if (layoutDirectionFromLocale == 1) {
            left = getRight() - getResources().getDimensionPixelSize(R.dimen.geq_text_and_bar_margin);
        }
        int i6 = left;
        for (int i7 = 0; i7 < this.mGeqData.length; i7++) {
            this.mRecPaint.setColor(getResources().getColor(R.color.colorGeqBackgroundBar, this.mContext.getTheme()));
            canvas.getClipBounds(this.mRect);
            if (layoutDirectionFromLocale == 1) {
                Rect rect = this.mRect;
                int i8 = (i7 + 1) * dimensionPixelSize4;
                rect.left = i6 - i8;
                if (i7 == 0) {
                    rect.right = (int) (i6 - f8);
                } else {
                    rect.right = i6 - ((int) (i8 - f9));
                }
            } else {
                if (i7 == 0) {
                    this.mRect.left = (int) (i6 + f8);
                } else {
                    this.mRect.left = ((int) (((i7 + 1) * dimensionPixelSize4) - f9)) + i6;
                }
                this.mRect.right = ((i7 + 1) * dimensionPixelSize4) + i6;
            }
            this.mRect.top = getTop() + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
            this.mRect.bottom = getTop() + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin) + dimensionPixelSize3;
            if (this.mSelectedBar != i7) {
                canvas.drawRect(this.mRect, this.mRecPaint);
            }
        }
        if (this.mSelectedBar != -1) {
            this.mRecPaint.setColor(getResources().getColor(R.color.colorSelectedGeqBackgroundBar, this.mContext.getTheme()));
            canvas.getClipBounds(this.mRect);
            int i9 = dimensionPixelSize3 / 2;
            int top = ((getTop() + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin)) + i9) - ((int) (i9 * (this.mGeqData[this.mSelectedBar] / 12.0f)));
            this.mRect.top = getTop() + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
            Rect rect2 = this.mRect;
            rect2.bottom = top;
            if (layoutDirectionFromLocale == 1) {
                int i10 = this.mSelectedBar;
                rect2.right = i6 - (dimensionPixelSize4 * i10);
                rect2.left = i6 - ((int) (((i10 + 1) * dimensionPixelSize4) + f8));
            } else {
                int i11 = this.mSelectedBar;
                rect2.left = (dimensionPixelSize4 * i11) + i6;
                rect2.right = ((int) (((i11 + 1) * dimensionPixelSize4) + f8)) + i6;
            }
            canvas.drawRect(this.mRect, this.mRecPaint);
        }
        int i12 = 0;
        while (true) {
            float[] fArr = this.mGeqData;
            if (i12 >= fArr.length) {
                return;
            }
            float f10 = fArr[i12];
            canvas.getClipBounds(this.mRect);
            int i13 = this.mSelectedBar;
            if (i13 == i12) {
                if (layoutDirectionFromLocale == 1) {
                    Rect rect3 = this.mRect;
                    rect3.right = i6 - (dimensionPixelSize4 * i13);
                    rect3.left = i6 - ((int) (((i13 + 1) * dimensionPixelSize4) + f8));
                } else {
                    Rect rect4 = this.mRect;
                    rect4.left = (dimensionPixelSize4 * i13) + i6;
                    rect4.right = ((int) (((i13 + 1) * dimensionPixelSize4) + f8)) + i6;
                }
                this.mRecPaint.setColor(getResources().getColor(R.color.colorSelectedGeqBar, this.mContext.getTheme()));
            } else {
                if (layoutDirectionFromLocale == 1) {
                    if (i12 == 0) {
                        this.mRect.right = (int) (i6 - f8);
                    } else {
                        this.mRect.right = i6 - ((int) (((i12 + 1) * dimensionPixelSize4) - f9));
                    }
                    this.mRect.left = i6 - ((i12 + 1) * dimensionPixelSize4);
                } else {
                    if (i12 == 0) {
                        this.mRect.left = (int) (i6 + f8);
                    } else {
                        this.mRect.left = ((int) (((i12 + 1) * dimensionPixelSize4) - f9)) + i6;
                    }
                    this.mRect.right = ((i12 + 1) * dimensionPixelSize4) + i6;
                }
                this.mRecPaint.setColor(getResources().getColor(R.color.colorGeqBar, this.mContext.getTheme()));
            }
            int[] iArr = this.mXLeftPosition;
            Rect rect5 = this.mRect;
            iArr[i12] = rect5.left;
            this.mXRightPosition[i12] = rect5.right;
            int top2 = getTop() + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
            int i14 = dimensionPixelSize3 / 2;
            Rect rect6 = this.mRect;
            rect6.top = (top2 + i14) - ((int) (i14 * (f10 / 12.0f)));
            rect6.bottom = getTop() + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin) + dimensionPixelSize3;
            if (graphicEqualizerOn) {
                canvas.drawRect(this.mRect, this.mRecPaint);
            }
            if (this.mSelectedBar == i12) {
                if (graphicEqualizerOn) {
                    this.mHLinePaint.setColor(getResources().getColor(R.color.colorSelectedGeqBarTopLine, this.mContext.getTheme()));
                } else {
                    this.mHLinePaint.setColor(getResources().getColor(R.color.colorGeqDisableText, this.mContext.getTheme()));
                }
                this.mHLinePaint.setStrokeWidth(f5 * 2.0f);
                float dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin) + this.mRect.left;
                Rect rect7 = this.mRect;
                i = i12;
                canvas.drawLine(dimensionPixelOffset, rect7.top, rect7.right - getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin), this.mRect.top, this.mHLinePaint);
                this.mStrokeLinePaint.setAntiAlias(true);
                this.mStrokeLinePaint.setStyle(Paint.Style.STROKE);
                this.mStrokeLinePaint.setColor(getResources().getColor(R.color.colorImageOnGeqBarStorke, this.mContext.getTheme()));
                this.mStrokeLinePaint.setStrokeWidth(5.0f);
                this.mStokeLinePath.reset();
                if (layoutDirectionFromLocale == 1) {
                    this.mStokeLinePath.moveTo((i6 - dimensionPixelSize4) + f9, this.mRect.top);
                    Path path = this.mStokeLinePath;
                    Rect rect8 = this.mRect;
                    path.lineTo(rect8.right, rect8.top);
                } else {
                    this.mStokeLinePath.moveTo((i6 + dimensionPixelSize4) - f9, this.mRect.top);
                    Path path2 = this.mStokeLinePath;
                    Rect rect9 = this.mRect;
                    path2.lineTo(rect9.left, rect9.top);
                }
                if (this.mSelectedBar != 0) {
                    canvas.drawPath(this.mStokeLinePath, this.mStrokeLinePaint);
                }
                Drawable drawable = getResources().getDrawable(R.drawable.image_on_top_geqbar, this.mContext.getTheme());
                if (drawable != null) {
                    int i15 = (int) f8;
                    drawable.setBounds(this.mRect.left + (((dimensionPixelSize4 - getResources().getDimensionPixelSize(R.dimen.geq_top_image_size)) + i15) / 2), this.mRect.top - (getResources().getDimensionPixelSize(R.dimen.geq_top_image_size) / 2), this.mRect.left + (((getResources().getDimensionPixelSize(R.dimen.geq_top_image_size) + dimensionPixelSize4) + i15) / 2), this.mRect.top + (getResources().getDimensionPixelSize(R.dimen.geq_top_image_size) / 2));
                    drawable.draw(canvas);
                }
                float fFloatValue = new BigDecimal(this.mGeqData[i]).setScale(2, 4).floatValue();
                this.mGeqDataTitlePaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.geq_text_size));
                this.mGeqDataTitlePaint.setColor(getResources().getColor(R.color.colorSelectedGeqBar, this.mContext.getTheme()));
                String strValueOf = String.valueOf(fFloatValue);
                Rect rect10 = this.mRect;
                int i16 = rect10.left;
                canvas.drawText(strValueOf, i16 - ((i16 - ((i6 + dimensionPixelSize4) - f9)) / 2.0f), rect10.top - getResources().getDimensionPixelSize(R.dimen.geq_gain_text_and_stroke_line_margin), this.mGeqDataTitlePaint);
            } else {
                i = i12;
                this.mHLinePaint.setColor(getResources().getColor(R.color.colorGeqBarTopLine, this.mContext.getTheme()));
                this.mHLinePaint.setStrokeWidth(f5 * 2.0f);
                float dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin) + this.mRect.left;
                Rect rect11 = this.mRect;
                canvas.drawLine(dimensionPixelOffset2, rect11.top, rect11.right - getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin), this.mRect.top, this.mHLinePaint);
            }
            i12 = i + 1;
            graphicEqualizerOn = graphicEqualizerOn;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x005b  */
    /* JADX WARN: Code duplicated, block: B:20:0x005f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0076  */
    /* JADX WARN: Code duplicated, block: B:23:0x007a  */
    /* JADX WARN: Code duplicated, block: B:24:0x009b  */
    /* JADX WARN: Code duplicated, block: B:32:0x00c1 A[SYNTHETIC] */
    public void onUpdateGeqData() {
        this.mUserGain = DsClientSettings.INSTANCE.getGraphicEqualizerBandGains(this.mFObserver);
        if (this.mConfiguration == null || this.mUserGain == null) {
            return;
        }
        int i = 0;
        boolean z = false;
        while (true) {
            float[] fArr = this.mGeqData;
            if (i >= fArr.length) {
                break;
            }
            int i2 = i * 2;
            fArr[i] = this.mUserGain[i2] / 16.0f;
            if (fArr[i] > this.mConfiguration.getMaxEditGain()) {
                this.mGeqData[i] = this.mConfiguration.getMaxEditGain();
            } else {
                if (this.mGeqData[i] < this.mConfiguration.getMinEditGain()) {
                    this.mGeqData[i] = this.mConfiguration.getMinEditGain();
                }
                if (!z) {
                    if (i == 0) {
                        int[] iArr = this.mUserGain;
                        float[] fArr2 = this.mGeqData;
                        iArr[i] = (int) (fArr2[i] * 16.0f);
                        int i3 = i + 1;
                        iArr[i3] = (int) (((fArr2[i] + fArr2[i3]) / 2.0f) * 16.0f);
                    } else if (i == 9) {
                        int[] iArr2 = this.mUserGain;
                        float[] fArr3 = this.mGeqData;
                        iArr2[i2 - 1] = (int) (((fArr3[i - 1] + fArr3[i]) / 2.0f) * 16.0f);
                        iArr2[i2] = (int) (fArr3[i] * 16.0f);
                        iArr2[i2 + 1] = (int) (fArr3[i] * 16.0f);
                    } else {
                        int[] iArr3 = this.mUserGain;
                        float[] fArr4 = this.mGeqData;
                        iArr3[i2 - 1] = (int) (((fArr4[i - 1] + fArr4[i]) / 2.0f) * 16.0f);
                        iArr3[i2] = (int) (fArr4[i] * 16.0f);
                        iArr3[i2 + 1] = (int) (((fArr4[i] + fArr4[i + 1]) / 2.0f) * 16.0f);
                    }
                }
                i++;
            }
            z = true;
            if (!z) {
                if (i == 0) {
                    int[] iArr4 = this.mUserGain;
                    float[] fArr5 = this.mGeqData;
                    iArr4[i] = (int) (fArr5[i] * 16.0f);
                    int i4 = i + 1;
                    iArr4[i4] = (int) (((fArr5[i] + fArr5[i4]) / 2.0f) * 16.0f);
                } else if (i == 9) {
                    int[] iArr5 = this.mUserGain;
                    float[] fArr6 = this.mGeqData;
                    iArr5[i2 - 1] = (int) (((fArr6[i - 1] + fArr6[i]) / 2.0f) * 16.0f);
                    iArr5[i2] = (int) (fArr6[i] * 16.0f);
                    iArr5[i2 + 1] = (int) (fArr6[i] * 16.0f);
                } else {
                    int[] iArr6 = this.mUserGain;
                    float[] fArr7 = this.mGeqData;
                    iArr6[i2 - 1] = (int) (((fArr7[i - 1] + fArr7[i]) / 2.0f) * 16.0f);
                    iArr6[i2] = (int) (fArr7[i] * 16.0f);
                    iArr6[i2 + 1] = (int) (((fArr7[i] + fArr7[i + 1]) / 2.0f) * 16.0f);
                }
            }
            i++;
        }
        if (z) {
            DsClientSettings.INSTANCE.setGraphicEqualizerBandGains(this.mFObserver, this.mUserGain);
        }
        updateThisData(this.mGeqData);
    }
}

package com.dolby.daxappui.tutorial;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.dolby.dax.DolbyAudioEffect;
import com.dolby.daxappui.Assets;
import com.dolby.daxappui.CustomViewPager;
import com.dolby.daxappui.DAXApplication;
import com.dolby.daxappui.GeqView;
import com.dolby.daxappui.IDsFragObserver;
import com.dolby.daxappui.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TutorialActivity extends AppCompatActivity implements IDsFragObserver {
    private List<ImageView> dots;
    private View item_view01;
    private View item_view02;
    ViewPager mTutorialViewPager;
    private int oldPosition = 0;
    private List<View> Mview = new ArrayList();
    private int mCurrentSelectedPage = 0;

    @Override // com.dolby.daxappui.IDsFragObserver
    public void chooseProfile(int i) {
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public int getActivePort() {
        return 0;
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public DolbyAudioEffect getDolbyAudioEffect() {
        return null;
    }

    @Override // com.dolby.daxappui.IDsFragObserver
    public void resetProfile(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLayout(int i) {
        boolean z;
        boolean z2;
        int i2;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int[] iArr = new int[2];
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tutorial_text_and_tooltip_spacing);
        getResources().getDimensionPixelSize(R.dimen.tutorial_tooltip_and_tooltip_spacing);
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        boolean z3 = getResources().getBoolean(R.bool.tabletLayout);
        if (i != 1) {
            if (i != 2) {
                return;
            }
            TextView textView = (TextView) findViewById(R.id.deText);
            textView.getLocationOnScreen(iArr);
            TextView textView2 = (TextView) findViewById(R.id.deText_tutorial);
            textView2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView2.getLayoutParams();
            if (layoutDirectionFromLocale == 1) {
                layoutParams.addRule(21);
                layoutParams.setMarginEnd(iArr[0]);
            } else {
                layoutParams.setMarginStart(iArr[0]);
            }
            layoutParams.topMargin = iArr[1];
            textView2.setLayoutParams(layoutParams);
            if (z3) {
                SeekBar seekBar = (SeekBar) findViewById(R.id.deButton);
                seekBar.getLocationOnScreen(iArr);
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.deButtonView_tutorial);
                relativeLayout.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
                if (layoutDirectionFromLocale == 1) {
                    layoutParams2.addRule(21);
                    layoutParams2.setMarginEnd(iArr[0]);
                } else {
                    layoutParams2.setMarginStart(iArr[0]);
                }
                layoutParams2.topMargin = iArr[1];
                relativeLayout.setLayoutParams(layoutParams2);
                SeekBar seekBar2 = (SeekBar) findViewById(R.id.deButton_tutorial);
                seekBar2.setEnabled(false);
                seekBar2.setProgress(seekBar.getProgress());
            } else {
                SeekBar seekBar3 = (SeekBar) findViewById(R.id.deButton);
                seekBar3.getLocationOnScreen(iArr);
                SeekBar seekBar4 = (SeekBar) findViewById(R.id.deButton_tutorial);
                seekBar4.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) seekBar4.getLayoutParams();
                layoutParams3.width = (((int) DAXApplication.getInstance().getIeqViewWidth()) * 2) + (getResources().getDimensionPixelOffset(R.dimen.ieq_horizontal_spacing_normal) * 2) + getResources().getDimensionPixelOffset(R.dimen.profile_switch_track_width);
                if (layoutDirectionFromLocale == 1) {
                    layoutParams3.addRule(21);
                    z2 = false;
                    layoutParams3.setMarginEnd(iArr[0]);
                } else {
                    z2 = false;
                    layoutParams3.setMarginStart(iArr[0]);
                }
                layoutParams3.topMargin = iArr[1];
                seekBar4.setLayoutParams(layoutParams3);
                seekBar4.setEnabled(z2);
                seekBar4.setProgress(seekBar3.getProgress());
            }
            TextView textView3 = (TextView) findViewById(R.id.de_tooltip);
            textView3.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) textView3.getLayoutParams();
            layoutParams4.addRule(12, -1);
            int[] iArr2 = new int[2];
            textView.getLocationOnScreen(iArr2);
            if (iArr2[1] < iArr[1]) {
                i2 = iArr2[1];
            } else {
                i2 = iArr[1];
            }
            layoutParams4.bottomMargin = (i4 - i2) + dimensionPixelSize;
            textView3.setLayoutParams(layoutParams4);
            return;
        }
        ((TextView) findViewById(R.id.ieqText)).getLocationOnScreen(iArr);
        TextView textView4 = (TextView) findViewById(R.id.ieqText_tutorial);
        textView4.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) textView4.getLayoutParams();
        if (layoutDirectionFromLocale == 1) {
            layoutParams5.addRule(21);
            layoutParams5.setMarginEnd(iArr[0]);
        } else {
            layoutParams5.setMarginStart(iArr[0]);
        }
        layoutParams5.topMargin = iArr[1];
        textView4.setLayoutParams(layoutParams5);
        ((TextView) findViewById(R.id.ieqName)).getLocationOnScreen(iArr);
        TextView textView5 = (TextView) findViewById(R.id.ieqName_tutorial);
        textView5.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) textView5.getLayoutParams();
        if (layoutDirectionFromLocale == 1) {
            layoutParams6.addRule(21);
            layoutParams6.setMarginEnd(iArr[0]);
        } else {
            layoutParams6.setMarginStart(iArr[0]);
        }
        layoutParams6.topMargin = iArr[1];
        textView5.setLayoutParams(layoutParams6);
        GridView gridView = (GridView) findViewById(R.id.equalizerListView);
        gridView.getLocationOnScreen(iArr);
        GridView gridView2 = (GridView) findViewById(R.id.equalizerListView_tutorial);
        gridView2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) gridView2.getLayoutParams();
        layoutParams7.width = gridView.getWidth();
        if (layoutDirectionFromLocale == 1) {
            z = false;
            layoutParams7.setMarginStart((i3 - iArr[0]) - gridView.getWidth());
        } else {
            z = false;
            layoutParams7.setMarginStart(iArr[0]);
        }
        layoutParams7.topMargin = iArr[1];
        gridView2.setLayoutParams(layoutParams7);
        gridView2.setAdapter((ListAdapter) new EqualizerAdapterT(this));
        gridView2.setEnabled(z);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.equalizerListOff);
        linearLayout.getLocationOnScreen(iArr);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.equalizerListOff_tutorial);
        linearLayout2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) linearLayout2.getLayoutParams();
        layoutParams8.width = linearLayout.getWidth();
        if (layoutDirectionFromLocale == 1) {
            layoutParams8.addRule(21);
            layoutParams8.setMarginEnd(iArr[0]);
        } else {
            layoutParams8.setMarginStart(iArr[0]);
        }
        layoutParams8.topMargin = iArr[1];
        linearLayout2.setLayoutParams(layoutParams8);
        TextView textView6 = (TextView) findViewById(R.id.ieq_tooltip);
        textView6.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) textView6.getLayoutParams();
        layoutParams9.addRule(3, gridView2.getId());
        layoutParams9.topMargin = dimensionPixelSize;
        textView6.setLayoutParams(layoutParams9);
        ((TextView) findViewById(R.id.geqText)).getLocationOnScreen(iArr);
        TextView textView7 = (TextView) findViewById(R.id.geqText_tutorial);
        textView7.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) textView7.getLayoutParams();
        if (layoutDirectionFromLocale == 1) {
            layoutParams10.addRule(21);
            layoutParams10.setMarginEnd(iArr[0]);
        } else {
            layoutParams10.setMarginStart(iArr[0]);
        }
        layoutParams10.topMargin = iArr[1];
        textView7.setLayoutParams(layoutParams10);
        TextView textView8 = (TextView) findViewById(R.id.geq_tooltip);
        textView8.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) textView8.getLayoutParams();
        layoutParams11.addRule(12, -1);
        layoutParams11.bottomMargin = (i4 - iArr[1]) + dimensionPixelSize;
        textView8.setLayoutParams(layoutParams11);
        GeqView geqView = (GeqView) findViewById(R.id.geqView);
        geqView.getLocationOnScreen(iArr);
        GeqViewTutorial geqViewTutorial = (GeqViewTutorial) findViewById(R.id.geqView_tutorial);
        geqViewTutorial.measure(View.MeasureSpec.makeMeasureSpec(0, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 1073741824));
        RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(geqView.getWidth(), geqView.getHeight());
        if (layoutDirectionFromLocale == 1) {
            layoutParams12.setMarginStart((i3 - iArr[0]) - geqView.getWidth());
        } else {
            layoutParams12.setMarginStart(iArr[0]);
        }
        layoutParams12.topMargin = iArr[1];
        geqViewTutorial.setLayoutParams(layoutParams12);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(DAXApplication.ConfigurationWrapper.wrapLocale(context));
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean z = getResources().getBoolean(R.bool.tabletLayout);
        if (z) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
        final String productVersion = ((DAXApplication) getApplication()).getProductVersion();
        Assets.init(this);
        setContentView(R.layout.tutorial_activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_sidemenu_titlebar);
        }
        if (z) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#161819")));
            }
            ListView listView = (ListView) findViewById(R.id.presetsListView);
            if (listView != null) {
                listView.setItemChecked(3, true);
            }
            TextView textView = (TextView) findViewById(R.id.profileName);
            if (textView != null) {
                textView.setText(getResources().getString(R.string.custom));
            }
            TextView textView2 = (TextView) findViewById(R.id.ieqName);
            if (textView2 != null) {
                textView2.setText(getResources().getString(R.string.off));
            }
            TextView textView3 = (TextView) findViewById(R.id.profileDescription);
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        } else {
            CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.profileViewpager);
            if (customViewPager != null) {
                customViewPager.setCurrentItem(3, true);
            }
        }
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.tutorialMainLayout);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dolby.daxappui.tutorial.TutorialActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                TextView textView4 = (TextView) TutorialActivity.this.findViewById(R.id.dsLogoText);
                if (productVersion.substring(0, 3).equals("DS1")) {
                    textView4.setText(TutorialActivity.this.getResources().getString(R.string.app_name_ds1));
                } else {
                    textView4.setText(TutorialActivity.this.getResources().getString(R.string.app_name));
                }
            }
        });
        this.mTutorialViewPager = (ViewPager) findViewById(R.id.tutorialViewPager);
        this.dots = new ArrayList();
        this.dots.add((ImageView) findViewById(R.id.dot_1));
        this.item_view01 = getLayoutInflater().inflate(R.layout.tutorial_page_1, (ViewGroup) null);
        this.Mview.add(this.item_view01);
        this.item_view01.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dolby.daxappui.tutorial.TutorialActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                TutorialActivity.this.item_view01.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                TutorialActivity.this.updateLayout(1);
            }
        });
        if (!productVersion.substring(0, 3).equals("DS1")) {
            this.dots.add((ImageView) findViewById(R.id.dot_2));
            this.item_view02 = getLayoutInflater().inflate(R.layout.tutorial_page_2, (ViewGroup) null);
            this.Mview.add(this.item_view02);
            this.item_view02.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dolby.daxappui.tutorial.TutorialActivity.3
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    TutorialActivity.this.item_view02.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    TutorialActivity.this.updateLayout(2);
                }
            });
        } else {
            ImageView imageView = (ImageView) findViewById(R.id.dot_1);
            ImageView imageView2 = (ImageView) findViewById(R.id.dot_2);
            imageView.setVisibility(8);
            imageView2.setVisibility(8);
        }
        this.mTutorialViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.dolby.daxappui.tutorial.TutorialActivity.4
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ((ImageView) TutorialActivity.this.dots.get(TutorialActivity.this.oldPosition)).setImageDrawable(TutorialActivity.this.getDrawable(R.drawable.tutorial_dot_unselected));
                ((ImageView) TutorialActivity.this.dots.get(i)).setImageDrawable(TutorialActivity.this.getDrawable(R.drawable.tutorial_dot_selected));
                TutorialActivity.this.oldPosition = i;
                TutorialActivity.this.mCurrentSelectedPage = i;
            }
        });
        this.mTutorialViewPager.setAdapter(new ViewPagerAdapter(this.Mview));
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("currentSelectedPage", this.mCurrentSelectedPage);
    }

    public static class GeqViewTutorial extends View {
        private Context mContext;
        private float[] mGeqData;
        private Paint mHLinePaint;
        private Paint mRecPaint;
        private Rect mRect;
        private Path mStokeLinePath;
        private Paint mStrokeLinePaint;
        private Paint mTitlePaint;
        private final String[] yTitlesStrings;

        private void init() {
            this.mTitlePaint = new Paint(1);
            this.mTitlePaint.setTextAlign(Paint.Align.RIGHT);
            this.mRecPaint = new Paint();
            this.mHLinePaint = new Paint();
            this.mRect = new Rect();
            this.mStrokeLinePaint = new Paint(1);
            this.mStrokeLinePaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
            this.mStokeLinePath = new Path();
            postInvalidate();
        }

        public GeqViewTutorial(Context context) {
            super(context);
            this.yTitlesStrings = new String[]{"+5dB", "0dB", "-5dB"};
            this.mGeqData = new float[]{0.0f, 0.0f, 0.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            this.mContext = context;
            init();
        }

        public GeqViewTutorial(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.yTitlesStrings = new String[]{"+5dB", "0dB", "-5dB"};
            this.mGeqData = new float[]{0.0f, 0.0f, 0.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            this.mContext = context;
            init();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            int width;
            float f;
            float f2;
            float f3;
            float f4;
            int dimensionPixelSize;
            float dimensionPixelSize2;
            super.onDraw(canvas);
            boolean z = getResources().getBoolean(R.bool.tabletLayout);
            float f5 = getContext().getResources().getDisplayMetrics().density;
            if (z) {
                width = getWidth() - getResources().getDimensionPixelSize(R.dimen.geq_bar_end_padding);
            } else {
                width = getWidth();
            }
            int height = getHeight();
            Resources resources = getResources();
            int i = R.dimen.geq_border_vertical_margin;
            int dimensionPixelSize3 = height - (resources.getDimensionPixelSize(R.dimen.geq_border_vertical_margin) * 2);
            float f6 = dimensionPixelSize3 / 2.0f;
            int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
            int i2 = 1;
            if (layoutDirectionFromLocale == 1) {
                this.mTitlePaint.setTextAlign(Paint.Align.LEFT);
            } else {
                this.mTitlePaint.setTextAlign(Paint.Align.RIGHT);
            }
            this.mTitlePaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.geq_text_size));
            this.mTitlePaint.setColor(getResources().getColor(R.color.colorGeqGainText, this.mContext.getTheme()));
            Paint.FontMetrics fontMetrics = this.mTitlePaint.getFontMetrics();
            int i3 = (int) fontMetrics.descent;
            float f7 = fontMetrics.bottom - fontMetrics.top;
            float fMeasureText = this.mTitlePaint.measureText(this.yTitlesStrings[0]);
            if (layoutDirectionFromLocale == 1) {
                fMeasureText = getWidth() - fMeasureText;
            }
            int i4 = 0;
            while (i4 < this.yTitlesStrings.length) {
                if (i4 == 0) {
                    f4 = f7 - i3;
                    dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
                } else {
                    if (i4 == i2) {
                        dimensionPixelSize2 = (((f7 / 2.0f) + f6) - i3) + getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
                    } else {
                        f4 = (f6 * 2.0f) - i3;
                        dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
                    }
                    canvas.drawText(this.yTitlesStrings[i4], fMeasureText, dimensionPixelSize2, this.mTitlePaint);
                    i4++;
                    i2 = 1;
                }
                dimensionPixelSize2 = f4 + dimensionPixelSize;
                canvas.drawText(this.yTitlesStrings[i4], fMeasureText, dimensionPixelSize2, this.mTitlePaint);
                i4++;
                i2 = 1;
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
            int dimensionPixelSize5 = getResources().getDimensionPixelSize(R.dimen.geq_text_and_bar_margin);
            if (layoutDirectionFromLocale == 1) {
                dimensionPixelSize5 = getWidth() - getResources().getDimensionPixelSize(R.dimen.geq_text_and_bar_margin);
            }
            int i5 = dimensionPixelSize5;
            for (int i6 = 0; i6 < this.mGeqData.length; i6++) {
                this.mRecPaint.setColor(getResources().getColor(R.color.colorGeqBackgroundBar, this.mContext.getTheme()));
                canvas.getClipBounds(this.mRect);
                if (layoutDirectionFromLocale == 1) {
                    Rect rect = this.mRect;
                    int i7 = (i6 + 1) * dimensionPixelSize4;
                    rect.left = i5 - i7;
                    if (i6 == 0) {
                        rect.right = (int) (i5 - f8);
                    } else {
                        rect.right = i5 - ((int) (i7 - f9));
                    }
                } else {
                    if (i6 == 0) {
                        this.mRect.left = (int) (i5 + f8);
                    } else {
                        this.mRect.left = ((int) (((i6 + 1) * dimensionPixelSize4) - f9)) + i5;
                    }
                    this.mRect.right = ((i6 + 1) * dimensionPixelSize4) + i5;
                }
                this.mRect.top = getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
                this.mRect.bottom = getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin) + dimensionPixelSize3;
                if (3 != i6) {
                    canvas.drawRect(this.mRect, this.mRecPaint);
                } else {
                    this.mRecPaint.setColor(getResources().getColor(R.color.colorSelectedGeqBackgroundBar, this.mContext.getTheme()));
                    canvas.getClipBounds(this.mRect);
                    if (layoutDirectionFromLocale == 1) {
                        Rect rect2 = this.mRect;
                        rect2.right = i5 - (dimensionPixelSize4 * 3);
                        rect2.left = i5 - ((int) ((dimensionPixelSize4 * 4) + f8));
                    } else {
                        Rect rect3 = this.mRect;
                        rect3.left = (dimensionPixelSize4 * 3) + i5;
                        rect3.right = ((int) ((dimensionPixelSize4 * 4) + f8)) + i5;
                    }
                    int i8 = dimensionPixelSize3 / 2;
                    int dimensionPixelSize6 = (getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin) + i8) - ((int) (i8 * 1.0f));
                    this.mRect.top = getResources().getDimensionPixelSize(R.dimen.geq_border_vertical_margin);
                    Rect rect4 = this.mRect;
                    rect4.bottom = dimensionPixelSize6;
                    canvas.drawRect(rect4, this.mRecPaint);
                }
            }
            int i9 = 0;
            while (true) {
                float[] fArr = this.mGeqData;
                if (i9 >= fArr.length) {
                    return;
                }
                float f10 = fArr[i9];
                canvas.getClipBounds(this.mRect);
                if (3 == i9) {
                    if (layoutDirectionFromLocale == 1) {
                        Rect rect5 = this.mRect;
                        rect5.right = i5 - (dimensionPixelSize4 * 3);
                        rect5.left = i5 - ((int) ((dimensionPixelSize4 * 4) + f8));
                    } else {
                        Rect rect6 = this.mRect;
                        rect6.left = (dimensionPixelSize4 * 3) + i5;
                        rect6.right = ((int) ((dimensionPixelSize4 * 4) + f8)) + i5;
                    }
                    this.mRecPaint.setColor(getResources().getColor(R.color.colorSelectedGeqBar, this.mContext.getTheme()));
                } else {
                    if (layoutDirectionFromLocale == 1) {
                        if (i9 == 0) {
                            this.mRect.right = (int) (i5 - f8);
                        } else {
                            this.mRect.right = i5 - ((int) (((i9 + 1) * dimensionPixelSize4) - f9));
                        }
                        this.mRect.left = i5 - ((i9 + 1) * dimensionPixelSize4);
                    } else {
                        if (i9 == 0) {
                            this.mRect.left = (int) (i5 + f8);
                        } else {
                            this.mRect.left = ((int) (((i9 + 1) * dimensionPixelSize4) - f9)) + i5;
                        }
                        this.mRect.right = ((i9 + 1) * dimensionPixelSize4) + i5;
                    }
                    this.mRecPaint.setColor(getResources().getColor(R.color.colorGeqBar, this.mContext.getTheme()));
                }
                int i10 = dimensionPixelSize3 / 2;
                int dimensionPixelSize7 = getResources().getDimensionPixelSize(i) + i10;
                Rect rect7 = this.mRect;
                rect7.top = dimensionPixelSize7 - ((int) (i10 * (f10 / 5.0f)));
                rect7.bottom = getResources().getDimensionPixelSize(i) + dimensionPixelSize3;
                canvas.drawRect(this.mRect, this.mRecPaint);
                if (3 == i9) {
                    this.mHLinePaint.setColor(getResources().getColor(R.color.colorSelectedGeqBarTopLine, this.mContext.getTheme()));
                    this.mHLinePaint.setStrokeWidth(f5 * 2.0f);
                    float dimensionPixelOffset = this.mRect.left + getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin);
                    Rect rect8 = this.mRect;
                    canvas.drawLine(dimensionPixelOffset, rect8.top, rect8.right - getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin), this.mRect.top, this.mHLinePaint);
                    this.mStrokeLinePaint.setAntiAlias(true);
                    this.mStrokeLinePaint.setStyle(Paint.Style.STROKE);
                    this.mStrokeLinePaint.setColor(getResources().getColor(R.color.colorGeqBarTopLine, this.mContext.getTheme()));
                    this.mStrokeLinePaint.setStrokeWidth(5.0f);
                    this.mStokeLinePath.reset();
                    if (layoutDirectionFromLocale == 1) {
                        this.mStokeLinePath.moveTo((i5 - dimensionPixelSize4) + f9, this.mRect.top);
                        Path path = this.mStokeLinePath;
                        Rect rect9 = this.mRect;
                        path.lineTo(rect9.right, rect9.top);
                    } else {
                        this.mStokeLinePath.moveTo((i5 + dimensionPixelSize4) - f9, this.mRect.top);
                        Path path2 = this.mStokeLinePath;
                        Rect rect10 = this.mRect;
                        path2.lineTo(rect10.left, rect10.top);
                    }
                    canvas.drawPath(this.mStokeLinePath, this.mStrokeLinePaint);
                    Drawable drawable = getResources().getDrawable(R.drawable.image_on_top_geqbar, this.mContext.getTheme());
                    if (drawable != null) {
                        int i11 = (int) f8;
                        drawable.setBounds(this.mRect.left + (((dimensionPixelSize4 - getResources().getDimensionPixelSize(R.dimen.geq_top_image_size)) + i11) / 2), this.mRect.top - (getResources().getDimensionPixelSize(R.dimen.geq_top_image_size) / 2), this.mRect.left + (((getResources().getDimensionPixelSize(R.dimen.geq_top_image_size) + dimensionPixelSize4) + i11) / 2), this.mRect.top + (getResources().getDimensionPixelSize(R.dimen.geq_top_image_size) / 2));
                        drawable.draw(canvas);
                    }
                } else {
                    this.mHLinePaint.setColor(getResources().getColor(R.color.colorGeqBarTopLine, this.mContext.getTheme()));
                    this.mHLinePaint.setStrokeWidth(f5 * 2.0f);
                    float dimensionPixelOffset2 = this.mRect.left + getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin);
                    Rect rect11 = this.mRect;
                    canvas.drawLine(dimensionPixelOffset2, rect11.top, rect11.right - getResources().getDimensionPixelOffset(R.dimen.geq_top_line_margin), this.mRect.top, this.mHLinePaint);
                }
                i9++;
                i = R.dimen.geq_border_vertical_margin;
            }
        }

        @Override // android.view.View
        protected void onMeasure(int i, int i2) {
            setMeasuredDimension(i, i2);
        }
    }

    private class EqualizerAdapterT extends BaseAdapter {
        private Context mContext;
        private int[] imgIdForIeqOn = {R.drawable.ic_open_on_ieq, R.drawable.ic_rich_on_ieq, R.drawable.ic_focus_on_ieq};
        private int[] imgIdForIeqOff = {R.drawable.ic_open_off_ieq, R.drawable.ic_rich_off_ieq, R.drawable.ic_focus_off_ieq};

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        EqualizerAdapterT(Context context) {
            this.mContext = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.imgIdForIeqOn.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.equalizer_list_item, (ViewGroup) null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.equalizerListViewLayout);
            if (i == 3) {
                linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_blue_shadow));
                imageView.setImageResource(this.imgIdForIeqOn[i]);
            } else {
                linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_shadow));
                imageView.setImageResource(this.imgIdForIeqOff[i]);
            }
            return view;
        }
    }
}

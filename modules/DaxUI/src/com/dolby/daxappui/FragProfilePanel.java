package com.dolby.daxappui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.dolby.dax.DolbyAudioEffect;

/* JADX INFO: loaded from: classes.dex */
public class FragProfilePanel extends Fragment implements View.OnClickListener, EqualizerAdapter.IPresetListener {
    private Context mContext;
    private EqualizerAdapter mEqualizerAdapter;
    private String[] mIeqName;
    private FrameLayout mMask;
    private String mProductVersion;
    private String[] mProfileNames;
    public int mNum = -1;
    private IDsFragObserver mFObserver = null;
    private int[] profileImgId_dax2 = {R.drawable.ic_dynamic_profile_panel, R.drawable.ic_movie_profile_panel, R.drawable.ic_music_profile_panel, R.drawable.ic_custom_profile_panel};

    public static FragProfilePanel newInstance(int i) {
        FragProfilePanel fragProfilePanel = new FragProfilePanel();
        Bundle bundle = new Bundle();
        bundle.putInt("num", i);
        fragProfilePanel.setArguments(bundle);
        return fragProfilePanel;
    }

    @Override // com.dolby.daxappui.EqualizerAdapter.IPresetListener
    public void onPresetChanged(int i, boolean z) {
        TextView textView;
        if (getView() == null || (textView = (TextView) getView().findViewById(R.id.ieqName)) == null) {
            return;
        }
        textView.setText(this.mIeqName[i]);
        ImageView imageView = (ImageView) getView().findViewById(R.id.icon_off);
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.equalizerListOffLayout);
        if (i == 3) {
            linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_blue_shadow));
            imageView.setImageResource(R.drawable.ic_none_on_ieq);
        } else {
            linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_shadow));
            imageView.setImageResource(R.drawable.ic_none_off_ieq);
        }
        if (z) {
            DsClientSettings.INSTANCE.setIeqPreset(this.mFObserver, i);
        }
    }

    public void setGeqViewEnabled() {
        IDsFragObserver iDsFragObserver = this.mFObserver;
        if (iDsFragObserver != null) {
            boolean graphicEqualizerOn = DsClientSettings.INSTANCE.getGraphicEqualizerOn(iDsFragObserver, iDsFragObserver.getActivePort());
            View view = getView();
            if (graphicEqualizerOn) {
                if (view != null) {
                    ViewGroup viewGroup = (ViewGroup) this.mMask.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.mMask);
                    }
                    TextView textView = (TextView) view.findViewById(R.id.geqText);
                    if (textView != null) {
                        textView.setTextColor(getResources().getColor(R.color.colorText, getContext().getTheme()));
                    }
                }
            } else if (view != null) {
                GeqViewLayout geqViewLayout = (GeqViewLayout) view.findViewById(R.id.GeqViewLayout);
                TextView textView2 = (TextView) view.findViewById(R.id.geqText);
                if (((ViewGroup) this.mMask.getParent()) == null) {
                    geqViewLayout.addView(this.mMask);
                }
                if (textView2 != null) {
                    textView2.setTextColor(getResources().getColor(R.color.colorGeqDisableText, getContext().getTheme()));
                }
            }
            if (view != null) {
                ((GeqView) view.findViewById(R.id.geqView)).invalidate();
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mFObserver = (IDsFragObserver) context;
            this.mProfileNames = DAXApplication.getInstance().getProfileNames();
            this.mProductVersion = DAXApplication.getInstance().getProductVersion();
            this.mIeqName = new String[4];
            this.mIeqName[0] = context.getString(R.string.balanced);
            this.mIeqName[1] = context.getString(R.string.warm);
            this.mIeqName[2] = context.getString(R.string.detailed);
            this.mIeqName[3] = context.getString(R.string.off);
            this.mContext = context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context.toString() + " must implement IDsFragObserver");
        }
    }

    /* JADX WARN: Code duplicated, block: B:61:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:62:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:70:0x0301  */
    /* JADX WARN: Code duplicated, block: B:72:0x030b  */
    /* JADX WARN: Code duplicated, block: B:74:0x0312  */
    /* JADX WARN: Code duplicated, block: B:75:0x0329  */
    /* JADX WARN: Code duplicated, block: B:77:0x033c  */
    /* JADX WARN: Code duplicated, block: B:79:0x0343  */
    /* JADX WARN: Code duplicated, block: B:82:0x0364  */
    /* JADX WARN: Code duplicated, block: B:84:0x036e  */
    /* JADX WARN: Code duplicated, block: B:85:0x0373  */
    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        TextView textView;
        GeqView geqView;
        boolean graphicEqualizerOn;
        TextView textView2;
        RelativeLayout relativeLayout;
        ImageView imageView;
        ViewGroup viewGroup2;
        View viewInflate = layoutInflater.inflate(R.layout.profile, (ViewGroup) null);
        String strSubstring = this.mProductVersion.substring(0, 3);
        this.mMask = new FrameLayout(getContext());
        this.mMask.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mMask.setBackgroundColor(getResources().getColor(R.color.colorMaskBackground, getContext().getTheme()));
        this.mMask.setClickable(true);
        boolean z = getResources().getBoolean(R.bool.tabletLayout);
        int i3 = this.mNum;
        if (z) {
            GridView gridView = (GridView) viewInflate.findViewById(R.id.equalizerListView);
            if (gridView != null) {
                LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.equalizerListOff);
                ImageView imageView2 = (ImageView) viewInflate.findViewById(R.id.icon_off);
                LinearLayout linearLayout2 = (LinearLayout) viewInflate.findViewById(R.id.equalizerListOffLayout);
                int ieqPreset = DsClientSettings.INSTANCE.getIeqPreset(this.mFObserver);
                ((TextView) viewInflate.findViewById(R.id.ieqName)).setText(this.mIeqName[ieqPreset]);
                if (ieqPreset == 3) {
                    linearLayout2.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_blue_shadow));
                    imageView2.setImageResource(R.drawable.ic_none_on_ieq);
                } else {
                    linearLayout2.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_shadow));
                    imageView2.setImageResource(R.drawable.ic_none_off_ieq);
                }
                linearLayout.setOnClickListener(this);
                this.mEqualizerAdapter = new EqualizerAdapter(this.mContext, this);
                gridView.setAdapter((ListAdapter) this.mEqualizerAdapter);
            }
            RelativeLayout relativeLayout2 = (RelativeLayout) viewInflate.findViewById(R.id.deView);
            if (strSubstring.equals("DS1")) {
                relativeLayout2.setVisibility(8);
            } else if (i3 == 1 || i3 == 3) {
                SeekBar seekBar = (SeekBar) viewInflate.findViewById(R.id.deButton);
                seekBar.setProgress(DsClientSettings.INSTANCE.getDialogEnhancerAmount(this.mFObserver));
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.dolby.daxappui.FragProfilePanel.1
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStartTrackingTouch(SeekBar seekBar2) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStopTrackingTouch(SeekBar seekBar2) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onProgressChanged(SeekBar seekBar2, int i4, boolean z2) {
                        if (z2) {
                            DsClientSettings.INSTANCE.setDialogEnhancerAmount(FragProfilePanel.this.mFObserver, i4);
                        }
                    }
                });
                relativeLayout2.setVisibility(0);
            } else {
                relativeLayout2.setVisibility(8);
            }
            ((TextView) viewInflate.findViewById(R.id.profileDescription)).setTypeface(Assets.getFont(Assets.FontType.LIGHT));
            GeqView geqView2 = (GeqView) viewInflate.findViewById(R.id.geqView);
            if (geqView2 != null && geqView2.mSelectedBar == -1) {
                geqView2.onUpdateGeqData();
            }
            ImageView imageView3 = (ImageView) viewInflate.findViewById(R.id.profileResetButton);
            if (imageView3 != null) {
                if (DsClientSettings.INSTANCE.isProfileSpecificSettingsModified(this.mFObserver, i3)) {
                    imageView3.setVisibility(0);
                } else {
                    imageView3.setVisibility(4);
                }
                imageView3.setOnClickListener(this);
            }
        } else {
            ((ImageView) viewInflate.findViewById(R.id.profileIcon)).setImageResource(this.profileImgId_dax2[this.mNum]);
            ((TextView) viewInflate.findViewById(R.id.profileName)).setText(this.mProfileNames[i3]);
            RelativeLayout relativeLayout3 = (RelativeLayout) viewInflate.findViewById(R.id.ieqLayout);
            if (i3 == 2 || i3 == 3) {
                relativeLayout3.setVisibility(0);
                int ieqPreset2 = DsClientSettings.INSTANCE.getIeqPreset(this.mFObserver);
                ((TextView) viewInflate.findViewById(R.id.ieqName)).setText(this.mIeqName[ieqPreset2]);
                GridView gridView2 = (GridView) viewInflate.findViewById(R.id.equalizerListView);
                LinearLayout linearLayout3 = (LinearLayout) viewInflate.findViewById(R.id.equalizerListOff);
                ViewGroup.LayoutParams layoutParams = gridView2.getLayoutParams();
                int ieqViewWidth = (((int) DAXApplication.getInstance().getIeqViewWidth()) * 3) + (getResources().getDimensionPixelOffset(R.dimen.ieq_horizontal_spacing_normal) * 2);
                if (layoutParams == null) {
                    layoutParams = new ViewGroup.LayoutParams(ieqViewWidth, -2);
                } else {
                    layoutParams.width = ieqViewWidth;
                }
                gridView2.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = linearLayout3.getLayoutParams();
                int ieqViewWidth2 = (int) DAXApplication.getInstance().getIeqViewWidth();
                if (layoutParams2 == null) {
                    layoutParams2 = new ViewGroup.LayoutParams(ieqViewWidth2, -2);
                } else {
                    layoutParams2.width = ieqViewWidth2;
                }
                linearLayout3.setLayoutParams(layoutParams2);
                ImageView imageView4 = (ImageView) viewInflate.findViewById(R.id.icon_off);
                LinearLayout linearLayout4 = (LinearLayout) viewInflate.findViewById(R.id.equalizerListOffLayout);
                if (ieqPreset2 == 3) {
                    linearLayout4.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_blue_shadow));
                    imageView4.setImageResource(R.drawable.ic_none_on_ieq);
                } else {
                    linearLayout4.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_shadow));
                    imageView4.setImageResource(R.drawable.ic_none_off_ieq);
                }
                linearLayout3.setOnClickListener(this);
                this.mEqualizerAdapter = new EqualizerAdapter(this.mContext, this);
                gridView2.setAdapter((ListAdapter) this.mEqualizerAdapter);
                this.mEqualizerAdapter.setIeqSelection(ieqPreset2, false);
                i = R.id.deView;
            } else {
                relativeLayout3.setVisibility(8);
                i = R.id.deView;
            }
            RelativeLayout relativeLayout4 = (RelativeLayout) viewInflate.findViewById(i);
            if (strSubstring.equals("DS1")) {
                relativeLayout4.setVisibility(8);
            } else {
                if (i3 == 1 || i3 == 3) {
                    relativeLayout4.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                    RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) relativeLayout4.getLayoutParams();
                    layoutParams3.width = (((int) DAXApplication.getInstance().getIeqViewWidth()) * 2) + (getResources().getDimensionPixelOffset(R.dimen.ieq_horizontal_spacing_normal) * 2) + getResources().getDimensionPixelOffset(R.dimen.profile_switch_track_width);
                    relativeLayout4.setLayoutParams(layoutParams3);
                    SeekBar seekBar2 = (SeekBar) viewInflate.findViewById(R.id.deButton);
                    seekBar2.setLayoutParams(layoutParams3);
                    seekBar2.setProgress(DsClientSettings.INSTANCE.getDialogEnhancerAmount(this.mFObserver));
                    seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.dolby.daxappui.FragProfilePanel.2
                        @Override // android.widget.SeekBar.OnSeekBarChangeListener
                        public void onStartTrackingTouch(SeekBar seekBar3) {
                        }

                        @Override // android.widget.SeekBar.OnSeekBarChangeListener
                        public void onStopTrackingTouch(SeekBar seekBar3) {
                        }

                        @Override // android.widget.SeekBar.OnSeekBarChangeListener
                        public void onProgressChanged(SeekBar seekBar3, int i4, boolean z2) {
                            if (z2) {
                                DsClientSettings.INSTANCE.setDialogEnhancerAmount(FragProfilePanel.this.mFObserver, i4);
                            }
                        }
                    });
                    i2 = 0;
                    relativeLayout4.setVisibility(0);
                } else {
                    relativeLayout4.setVisibility(8);
                }
                textView = (TextView) viewInflate.findViewById(R.id.profileDescription);
                if (i3 == 0) {
                    textView.setTypeface(Assets.getFont(Assets.FontType.LIGHT));
                    textView.setVisibility(i2);
                } else {
                    textView.setVisibility(8);
                }
                geqView = (GeqView) viewInflate.findViewById(R.id.geqView);
                if (geqView != null && geqView.mSelectedBar == -1) {
                    geqView.onUpdateGeqData();
                }
                DsClientSettings dsClientSettings = DsClientSettings.INSTANCE;
                IDsFragObserver iDsFragObserver = this.mFObserver;
                graphicEqualizerOn = dsClientSettings.getGraphicEqualizerOn(iDsFragObserver, iDsFragObserver.getActivePort());
                textView2 = (TextView) viewInflate.findViewById(R.id.geqText);
                if (graphicEqualizerOn) {
                    viewGroup2 = (ViewGroup) this.mMask.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(this.mMask);
                    }
                    if (textView2 != null) {
                        textView2.setTextColor(getResources().getColor(R.color.colorText, getContext().getTheme()));
                    }
                } else {
                    relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.geqLayout);
                    if (((ViewGroup) this.mMask.getParent()) == null) {
                        relativeLayout.addView(this.mMask);
                    }
                    if (textView2 != null) {
                        textView2.setTextColor(getResources().getColor(R.color.colorSwitchDisableText, getContext().getTheme()));
                    }
                }
                imageView = (ImageView) viewInflate.findViewById(R.id.profileResetButton);
                if (imageView != null) {
                    if (DsClientSettings.INSTANCE.isProfileSpecificSettingsModified(this.mFObserver, i3)) {
                        imageView.setVisibility(0);
                    } else {
                        imageView.setVisibility(4);
                    }
                    imageView.setOnClickListener(this);
                }
                viewInflate.setTag(Integer.valueOf(i3));
            }
            i2 = 0;
            textView = (TextView) viewInflate.findViewById(R.id.profileDescription);
            if (i3 == 0) {
                textView.setTypeface(Assets.getFont(Assets.FontType.LIGHT));
                textView.setVisibility(i2);
            } else {
                textView.setVisibility(8);
            }
            geqView = (GeqView) viewInflate.findViewById(R.id.geqView);
            if (geqView != null) {
                geqView.onUpdateGeqData();
            }
            DsClientSettings dsClientSettings2 = DsClientSettings.INSTANCE;
            IDsFragObserver iDsFragObserver2 = this.mFObserver;
            graphicEqualizerOn = dsClientSettings2.getGraphicEqualizerOn(iDsFragObserver2, iDsFragObserver2.getActivePort());
            textView2 = (TextView) viewInflate.findViewById(R.id.geqText);
            if (graphicEqualizerOn) {
                viewGroup2 = (ViewGroup) this.mMask.getParent();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(this.mMask);
                }
                if (textView2 != null) {
                    textView2.setTextColor(getResources().getColor(R.color.colorText, getContext().getTheme()));
                }
            } else {
                relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.geqLayout);
                if (((ViewGroup) this.mMask.getParent()) == null) {
                    relativeLayout.addView(this.mMask);
                }
                if (textView2 != null) {
                    textView2.setTextColor(getResources().getColor(R.color.colorSwitchDisableText, getContext().getTheme()));
                }
            }
            imageView = (ImageView) viewInflate.findViewById(R.id.profileResetButton);
            if (imageView != null) {
                if (DsClientSettings.INSTANCE.isProfileSpecificSettingsModified(this.mFObserver, i3)) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(4);
                }
                imageView.setOnClickListener(this);
            }
            viewInflate.setTag(Integer.valueOf(i3));
        }
        return viewInflate;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IDsFragObserver iDsFragObserver = this.mFObserver;
        DolbyAudioEffect dolbyAudioEffect = iDsFragObserver == null ? null : iDsFragObserver.getDolbyAudioEffect();
        if (dolbyAudioEffect != null) {
            int id = view.getId();
            if (R.id.profileResetButton == id) {
                int profile = dolbyAudioEffect.getProfile();
                DsClientSettings.INSTANCE.resetProfileSpecificSettings(this.mFObserver, profile);
                this.mFObserver.resetProfile(profile);
                return;
            } else {
                if (R.id.equalizerListOff == id) {
                    ImageView imageView = (ImageView) getView().findViewById(R.id.icon_off);
                    ((LinearLayout) getView().findViewById(R.id.equalizerListOffLayout)).setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_blue_shadow));
                    imageView.setImageResource(R.drawable.ic_none_on_ieq);
                    this.mEqualizerAdapter.setIeqSelection(3, true);
                    return;
                }
                return;
            }
        }
        Log.e("FragProfilePanel", "onClick(): Dolby audio effect is null!");
    }

    public void updateProfilePanel(int i) {
        View view = getView();
        if (view != null) {
            if (!getResources().getBoolean(R.bool.tabletLayout)) {
                ((ImageView) view.findViewById(R.id.profileIcon)).setImageResource(this.profileImgId_dax2[i]);
            }
            ((TextView) view.findViewById(R.id.profileName)).setText(this.mProfileNames[i]);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.ieqLayout);
            if (i == 2 || i == 3) {
                relativeLayout.setVisibility(0);
                int ieqPreset = DsClientSettings.INSTANCE.getIeqPreset(this.mFObserver);
                ((TextView) view.findViewById(R.id.ieqName)).setText(this.mIeqName[ieqPreset]);
                ImageView imageView = (ImageView) view.findViewById(R.id.icon_off);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.equalizerListOffLayout);
                if (ieqPreset == 3) {
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_blue_shadow));
                    imageView.setImageResource(R.drawable.ic_none_on_ieq);
                } else {
                    linearLayout.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.background_with_shadow));
                    imageView.setImageResource(R.drawable.ic_none_off_ieq);
                }
                EqualizerAdapter equalizerAdapter = this.mEqualizerAdapter;
                if (equalizerAdapter != null) {
                    equalizerAdapter.setIeqSelection(ieqPreset, false);
                }
            } else {
                relativeLayout.setVisibility(8);
            }
            RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.deView);
            if (!this.mProductVersion.substring(0, 3).equals("DS1")) {
                if (i == 0 || i == 2) {
                    relativeLayout2.setVisibility(8);
                } else {
                    relativeLayout2.setVisibility(0);
                    ((SeekBar) view.findViewById(R.id.deButton)).setProgress(DsClientSettings.INSTANCE.getDialogEnhancerAmount(this.mFObserver));
                }
            }
            TextView textView = (TextView) view.findViewById(R.id.profileDescription);
            if (textView != null) {
                if (i == 0) {
                    textView.setTypeface(Assets.getFont(Assets.FontType.LIGHT));
                    textView.setVisibility(0);
                } else {
                    textView.setVisibility(8);
                }
            }
            GeqView geqView = (GeqView) view.findViewById(R.id.geqView);
            if (geqView != null && geqView.mSelectedBar == -1) {
                geqView.onUpdateGeqData();
            }
            ImageView imageView2 = (ImageView) view.findViewById(R.id.profileResetButton);
            if (imageView2 != null) {
                if (DsClientSettings.INSTANCE.isProfileSpecificSettingsModified(this.mFObserver, i)) {
                    imageView2.setVisibility(0);
                } else {
                    imageView2.setVisibility(4);
                }
            }
            setGeqViewEnabled();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        ViewGroup viewGroup;
        super.onDestroyView();
        if (getView() == null || (viewGroup = (ViewGroup) getView().getParent()) == null) {
            return;
        }
        viewGroup.removeView(getView());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}

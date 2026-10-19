package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class EqualizerFragment extends Fragment implements ServiceConnection, ListDialogFragment.Callbacks {
    private static final int ANIMATION_DELAY = 200;
    private static final int ANIMATION_INTERVAL = 50;
    private static final int ANIMATION_RESOLUTION = 5;
    private static final String BUNDLE_KEY_AVAILABLE = "key_available";
    private static final String BUNDLE_KEY_CURRENT_PRESET = "key_current_preset";
    private static final String BUNDLE_KEY_FROMLEVEL = "key_fromlevel";
    private static final String BUNDLE_KEY_LASTPRESET = "key_lastpreset";
    private static final String BUNDLE_KEY_NUMOFBANDS = "key_numofbands";
    private static final String BUNDLE_KEY_PREVIOUS_PRESET = "key_previous_preset";
    private static final String BUNDLE_KEY_SHOW_PRESET = "key_show_preset";
    private static final String EQUALIZER_PRESET_DIALOG_TAG = "EqualizerPresetDialog";
    private static final String LOG_TAG = "SoundEnhancement";
    private static final int SCALE_BAR_NUM = 11;
    private PorterDuffColorFilter mAccentColorFilter;
    private Handler mAnimHandler;
    private int mAnimIndex;
    private Runnable mAnimRunnable;
    private SeekBar mCbHorizontalSeekBar;
    private View.OnFocusChangeListener mCbOnFocusChangeListener;
    private View.OnKeyListener mCbOnKeyListener;
    private SeekBar.OnSeekBarChangeListener mCbOnSeekBarChangeListener;
    private int mCbPaddingLR;
    private RelativeLayout mCbPopupLayout;
    private TextView mCbPopupText;
    private View mCbRootLayout;
    private View.OnFocusChangeListener mCbVerticalOnFocusChangeListener;
    private View.OnKeyListener mCbVerticalOnKeyListener;
    private VerticalSeekBar.OnSeekBarChangeListener mCbVerticalOnSeekBarChangeListener;
    private VerticalSeekBar mCbVerticalSeekBar;
    private int mContentType;
    private ViewGroup mContentView;
    private AudioEffectManager.OnControlStatusChangeListener mControlStatusChangeListener;
    private int mCurrentPreset;
    private RelativeLayout[] mEqBandLayout;
    private TextView[] mEqFreqText;
    private LinearLayout mEqLayout;
    private RelativeLayout[] mEqPopupLayout;
    private TextView[] mEqPopupText;
    private VerticalSeekBar[] mEqSeekBar;
    private View.OnFocusChangeListener mEqVerticalOnFocusChangeListener;
    private View.OnKeyListener mEqVerticalOnKeyListener;
    private VerticalSeekBar.OnSeekBarChangeListener mEqVerticalOnSeekBarChangeListener;
    private int[] mFromLevels;
    private boolean mIsChangingFocusFromActionBar;
    private boolean mIsPortrait;
    private boolean mIsRtl;
    private boolean mIsSaveStatePossible;
    private boolean mIsShownPresetDialog;
    private boolean mIsShownPrevBandLevel;
    private boolean mIsUpdatedViewItems;
    private int mLastFocusId;
    private String mLaunchPackageName;
    private SoundEnhancementLocalise mLocalise;
    private PopUpRemoveHandler mPopUpRemoveHandler;

    @VisibleForTesting
    Button mPresetBtn;
    private View.OnClickListener mPresetBtnOnClickListener;
    private View.OnFocusChangeListener mPresetBtnOnFocusChangeListener;
    private View.OnTouchListener mPresetBtnOnTouchListener;

    @VisibleForTesting
    ListDialogFragment mPresetDialogFragment;
    private String[] mPresetList;
    private int mPreviousPreset;
    private PorterDuffColorFilter mPrimaryColorFilter;
    private int mSavedPreset;
    private int mScaleH;
    private int mScaleOffset;
    private AudioEffectService mService;
    private boolean mServiceConnected;
    private int[] mToLevels;
    private PorterDuffColorFilter mWhiteColorFilter;
    private static final int[] EQ_BAND_ID = {R.id.band_component_0, R.id.band_component_1, R.id.band_component_2, R.id.band_component_3, R.id.band_component_4};
    private static final int[] EQ_POPUP_ID = {R.id.eq_band_popup_0, R.id.eq_band_popup_1, R.id.eq_band_popup_2, R.id.eq_band_popup_3, R.id.eq_band_popup_4};
    private static final int EQ_BAND_NUM = EQ_BAND_ID.length;
    private static final int LAST_POPUP_BAND_CLEAR_BASS_ID = EQ_BAND_NUM;
    private static final int BAND_NUM = AudioEffectUtilities.getNumberOfBands();
    private static final int[] CENTER_FREQ = AudioEffectUtilities.getCenterFreqs();
    private static final int[] BAND_LEVEL_RANGE = AudioEffectUtilities.getBandLevelRange();

    public EqualizerFragment() {
        int i = EQ_BAND_NUM;
        this.mEqBandLayout = new RelativeLayout[i];
        this.mEqSeekBar = new VerticalSeekBar[i];
        this.mEqPopupLayout = new RelativeLayout[i];
        this.mEqPopupText = new TextView[i];
        this.mEqFreqText = new TextView[i];
        this.mLastFocusId = -1;
        this.mIsChangingFocusFromActionBar = false;
        this.mAnimHandler = new Handler();
        this.mAnimIndex = 0;
        this.mCurrentPreset = -1;
        this.mPreviousPreset = -1;
        this.mPresetDialogFragment = null;
        this.mIsShownPresetDialog = false;
        this.mIsShownPrevBandLevel = false;
        this.mPopUpRemoveHandler = new PopUpRemoveHandler(this);
        this.mControlStatusChangeListener = new AudioEffectManager.OnControlStatusChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.1
            @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager.OnControlStatusChangeListener
            public void onControlStatusChange(boolean controlGranted) {
                if (!controlGranted) {
                    EqualizerFragment.this.showErrorNoControl();
                }
            }
        };
        this.mPresetBtnOnClickListener = new View.OnClickListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (EqualizerFragment.this.mService != null && EqualizerFragment.this.mIsUpdatedViewItems) {
                    EqualizerFragment equalizerFragment = EqualizerFragment.this;
                    equalizerFragment.mFromLevels = equalizerFragment.mService.getBandLevels();
                    EqualizerFragment equalizerFragment2 = EqualizerFragment.this;
                    equalizerFragment2.mCurrentPreset = equalizerFragment2.mService.getCurrentEqualizerPreset();
                    EqualizerFragment equalizerFragment3 = EqualizerFragment.this;
                    equalizerFragment3.mPreviousPreset = equalizerFragment3.mCurrentPreset;
                    EqualizerFragment.this.showPresetDialog();
                }
            }
        };
        this.mPresetBtnOnTouchListener = new View.OnTouchListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                Drawable background = v.getBackground();
                if (event.getAction() == 0) {
                    background.setColorFilter(EqualizerFragment.this.mAccentColorFilter);
                    return false;
                }
                if (event.getAction() == 1) {
                    background.setColorFilter(EqualizerFragment.this.mWhiteColorFilter);
                    return false;
                }
                return false;
            }
        };
        this.mPresetBtnOnFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.4
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (EqualizerFragment.this.mLastFocusId != -1) {
                    v.setNextFocusDownId(EqualizerFragment.this.mLastFocusId);
                }
                Drawable background = v.getBackground();
                if (hasFocus) {
                    background.setColorFilter(EqualizerFragment.this.mAccentColorFilter);
                } else {
                    background.setColorFilter(EqualizerFragment.this.mWhiteColorFilter);
                }
                EqualizerFragment.this.mIsChangingFocusFromActionBar = false;
            }
        };
        this.mEqVerticalOnSeekBarChangeListener = new VerticalSeekBar.OnSeekBarChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.5
            @Override // com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(VerticalSeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && EqualizerFragment.BAND_LEVEL_RANGE != null) {
                    int index = EqualizerFragment.this.getEqBandIndex(seekBar);
                    EqualizerFragment.this.updatedEqBandLevel(index, EqualizerFragment.BAND_LEVEL_RANGE[0] + progress);
                    EqualizerFragment.this.showEqPopupView(index);
                }
            }

            @Override // com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(VerticalSeekBar seekBar) {
                EqualizerFragment.this.updateProgressAtEdge(seekBar);
            }

            @Override // com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(VerticalSeekBar seekBar) {
                int index = EqualizerFragment.this.getEqBandIndex(seekBar);
                if (!seekBar.isSelected()) {
                    EqualizerFragment.this.hideEqPopupView(index, true);
                }
                if (EqualizerFragment.this.mService != null && EqualizerFragment.this.mIsUpdatedViewItems) {
                    int equalizerBand = (EqualizerFragment.BAND_NUM - EqualizerFragment.EQ_BAND_NUM) + index;
                    int newBandLevel = EqualizerFragment.this.mService.getBandLevel(equalizerBand);
                    seekBar.setProgress(newBandLevel - EqualizerFragment.BAND_LEVEL_RANGE[0]);
                    EqualizerFragment.this.mService.saveSettingsToPrefs();
                }
                EqualizerFragment.this.reportEqualizerEvent(ReportContents.EVENT_EQ_BAND_LEVELS);
            }
        };
        this.mEqVerticalOnKeyListener = new View.OnKeyListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.6
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                int index = EqualizerFragment.this.getEqBandIndex((VerticalSeekBar) v);
                if (event.getAction() == 1) {
                    if (!v.isSelected()) {
                        EqualizerFragment.this.hideEqPopupView(index, true);
                    }
                } else if (keyCode == 66 || keyCode == 23) {
                    if (v.isSelected()) {
                        v.setNextFocusUpId(EqualizerFragment.this.mPresetBtn.getId());
                        v.setNextFocusDownId(-1);
                        v.setSelected(false);
                    } else {
                        v.setNextFocusUpId(v.getId());
                        v.setNextFocusDownId(v.getId());
                        v.setSelected(true);
                        EqualizerFragment.this.showEqPopupView(index);
                    }
                } else if (keyCode == 21 && !EqualizerFragment.this.mIsRtl && index == 0) {
                    if (EqualizerFragment.this.mCbVerticalSeekBar.getVisibility() == 0) {
                        EqualizerFragment.this.mLastFocusId = -1;
                    }
                } else if (keyCode == 22 && EqualizerFragment.this.mIsRtl && index == 0) {
                    if (EqualizerFragment.this.mCbVerticalSeekBar.getVisibility() == 0) {
                        EqualizerFragment.this.mLastFocusId = -1;
                    }
                } else if (keyCode == 22 && !EqualizerFragment.this.mIsRtl && index == 3) {
                    EqualizerFragment.this.mLastFocusId = -1;
                } else if (keyCode == 21 && EqualizerFragment.this.mIsRtl && index == 3) {
                    EqualizerFragment.this.mLastFocusId = -1;
                }
                return false;
            }
        };
        this.mEqVerticalOnFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.7
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                int index = EqualizerFragment.this.getEqBandIndex((VerticalSeekBar) v);
                if (index == 4) {
                    if (!EqualizerFragment.this.mIsChangingFocusFromActionBar) {
                        if (EqualizerFragment.this.mLastFocusId != -1 && v.getId() != EqualizerFragment.this.mLastFocusId) {
                            View lastFocusedView = EqualizerFragment.this.mContentView.findViewById(EqualizerFragment.this.mLastFocusId);
                            lastFocusedView.requestFocus();
                            return;
                        }
                    } else {
                        EqualizerFragment.this.mPresetBtn.requestFocus();
                        EqualizerFragment.this.mIsChangingFocusFromActionBar = false;
                        return;
                    }
                }
                if (hasFocus) {
                    EqualizerFragment.this.mLastFocusId = v.getId();
                } else {
                    v.setNextFocusUpId(EqualizerFragment.this.mPresetBtn.getId());
                    v.setNextFocusDownId(-1);
                }
                v.setSelected(false);
                EqualizerFragment.this.hideEqPopupView(index, false);
            }
        };
        this.mCbOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.8
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!seekBar.isSelected()) {
                    EqualizerFragment.this.hideCbPopupView(true);
                }
                EqualizerFragment.this.updateCbProgress();
                EqualizerFragment.this.reportEqualizerEvent(ReportContents.EVENT_EQ_CLEAR_BASS);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                EqualizerFragment.this.updateProgressAtEdge(seekBar);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && EqualizerFragment.BAND_LEVEL_RANGE != null) {
                    if (!seekBar.isFocused() || seekBar.isSelected()) {
                        EqualizerFragment.this.updateCbBandLevel(EqualizerFragment.BAND_LEVEL_RANGE[0] + progress);
                        EqualizerFragment.this.showCbPopupView();
                    } else {
                        EqualizerFragment.this.updateCbProgress();
                    }
                }
            }
        };
        this.mCbOnKeyListener = new View.OnKeyListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.9
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 1) {
                    if (!v.isSelected()) {
                        EqualizerFragment.this.hideCbPopupView(true);
                    }
                } else if (keyCode == 66 || keyCode == 23) {
                    if (v.isSelected()) {
                        v.setSelected(false);
                    } else {
                        v.setSelected(true);
                        EqualizerFragment.this.showCbPopupView();
                    }
                }
                return false;
            }
        };
        this.mCbOnFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.10
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (EqualizerFragment.this.mLastFocusId != -1) {
                    v.setNextFocusUpId(EqualizerFragment.this.mLastFocusId);
                }
                v.setSelected(false);
                EqualizerFragment.this.hideCbPopupView(false);
            }
        };
        this.mCbVerticalOnSeekBarChangeListener = new VerticalSeekBar.OnSeekBarChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.11
            @Override // com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(VerticalSeekBar seekBar) {
                if (!seekBar.isSelected()) {
                    EqualizerFragment.this.hideCbPopupView(true);
                }
                EqualizerFragment.this.updateCbProgress();
                EqualizerFragment.this.reportEqualizerEvent(ReportContents.EVENT_EQ_CLEAR_BASS);
            }

            @Override // com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(VerticalSeekBar seekBar) {
                EqualizerFragment.this.updateProgressAtEdge(seekBar);
            }

            @Override // com.sonyericsson.soundenhancement.seekbar.VerticalSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(VerticalSeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && EqualizerFragment.BAND_LEVEL_RANGE != null) {
                    EqualizerFragment.this.updateCbBandLevel(EqualizerFragment.BAND_LEVEL_RANGE[0] + progress);
                    EqualizerFragment.this.showCbPopupView();
                }
            }
        };
        this.mCbVerticalOnKeyListener = new View.OnKeyListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.12
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 1) {
                    if (!v.isSelected()) {
                        EqualizerFragment.this.hideCbPopupView(true);
                    }
                } else if (keyCode == 66 || keyCode == 23) {
                    if (v.isSelected()) {
                        v.setNextFocusUpId(EqualizerFragment.this.mPresetBtn.getId());
                        v.setNextFocusDownId(-1);
                        v.setSelected(false);
                    } else {
                        v.setNextFocusUpId(v.getId());
                        v.setNextFocusDownId(v.getId());
                        v.setSelected(true);
                        EqualizerFragment.this.showCbPopupView();
                    }
                }
                return false;
            }
        };
        this.mCbVerticalOnFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.13
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (!EqualizerFragment.this.mIsChangingFocusFromActionBar) {
                    if (EqualizerFragment.this.mLastFocusId != -1 && v.getId() != EqualizerFragment.this.mLastFocusId) {
                        View lastFocusedView = EqualizerFragment.this.mContentView.findViewById(EqualizerFragment.this.mLastFocusId);
                        lastFocusedView.requestFocus();
                        return;
                    }
                    EqualizerFragment.this.mLastFocusId = v.getId();
                    if (!hasFocus) {
                        v.setNextFocusUpId(EqualizerFragment.this.mPresetBtn.getId());
                        v.setNextFocusDownId(-1);
                    }
                    v.setSelected(false);
                    EqualizerFragment.this.hideCbPopupView(false);
                    return;
                }
                EqualizerFragment.this.mPresetBtn.requestFocus();
                EqualizerFragment.this.mIsChangingFocusFromActionBar = false;
            }
        };
    }

    static /* synthetic */ int access$3408(EqualizerFragment x0) {
        int i = x0.mAnimIndex;
        x0.mAnimIndex = i + 1;
        return i;
    }

    private static class PopUpRemoveHandler extends Handler {
        private static final int DELAY_TIME = 250;
        private final EqualizerFragment mFragment;

        PopUpRemoveHandler(EqualizerFragment fragment) {
            this.mFragment = fragment;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.mFragment != null) {
                if (message.what == EqualizerFragment.LAST_POPUP_BAND_CLEAR_BASS_ID) {
                    this.mFragment.mCbPopupText.sendAccessibilityEvent(16384);
                    this.mFragment.mCbPopupText.setVisibility(8);
                } else {
                    this.mFragment.mEqPopupText[message.what].sendAccessibilityEvent(16384);
                    this.mFragment.mEqPopupText[message.what].setVisibility(8);
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.mService = ((SoundEnhancementService.SoundEnhancementServiceBinder) service).getAudioEffectService();
        String str = this.mLaunchPackageName;
        if (str != null && str.length() > 0) {
            this.mService.createSessionHolder(this.mLaunchPackageName, this.mContentType);
            this.mService.restoreAudioSessionFromPreference();
            this.mService.setControlStatusListener(this.mLaunchPackageName, this.mControlStatusChangeListener);
        }
        setupViewItems();
        finishActivityBySettings();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        String str;
        if (this.mService != null && (str = this.mLaunchPackageName) != null && str.length() > 0) {
            this.mService.cancelControlStatusListener(this.mLaunchPackageName);
        }
        this.mService = null;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        this.mLaunchPackageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        this.mContentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", 0);
        Context context = getActivity();
        this.mLocalise = new SoundEnhancementLocalise();
        String[] presetList = AudioEffectUtilities.getEqualizerPresets();
        this.mPresetList = new String[presetList.length];
        for (int i = 0; i < presetList.length; i++) {
            this.mPresetList[i] = this.mLocalise.getLocalisedEqualizerPresetName(context.getApplicationContext(), presetList[i]);
        }
        int primaryColor = SoundEnhancementUtility.fetchPrimaryColor(context);
        this.mPrimaryColorFilter = new PorterDuffColorFilter(primaryColor, PorterDuff.Mode.MULTIPLY);
        int accentColor = SoundEnhancementUtility.fetchAccentColor(context);
        this.mAccentColorFilter = new PorterDuffColorFilter(accentColor, PorterDuff.Mode.MULTIPLY);
        this.mWhiteColorFilter = new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY);
        this.mIsSaveStatePossible = false;
        this.mFromLevels = null;
        if (savedInstanceState != null) {
            this.mIsShownPresetDialog = savedInstanceState.getBoolean(BUNDLE_KEY_SHOW_PRESET);
            if (this.mIsShownPresetDialog) {
                this.mCurrentPreset = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_PRESET);
                this.mPreviousPreset = savedInstanceState.getInt(BUNDLE_KEY_PREVIOUS_PRESET);
                this.mIsShownPrevBandLevel = true;
            }
        }
        startAndBindToService();
    }

    private void startAndBindToService() {
        Intent intent = new Intent("android.intent.action.MAIN");
        ComponentName cn = new ComponentName(getActivity(), (Class<?>) SoundEnhancementService.class);
        intent.setComponent(cn);
        ComponentName cn2 = getActivity().startService(intent);
        if (cn2 == null) {
            Log.e(LOG_TAG, "EqualizerFragment.startAndBindToService() Failed to connect to SoundEnhancementService!");
        }
        this.mServiceConnected = getActivity().getApplicationContext().bindService(intent, this, 1);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        int i;
        if (savedInstanceState != null && savedInstanceState.getBoolean(BUNDLE_KEY_AVAILABLE)) {
            this.mSavedPreset = savedInstanceState.getInt(BUNDLE_KEY_LASTPRESET);
            int numOfBands = savedInstanceState.getInt(BUNDLE_KEY_NUMOFBANDS);
            if (numOfBands != 0) {
                this.mFromLevels = new int[numOfBands];
                for (int i2 = 0; i2 < numOfBands; i2++) {
                    this.mFromLevels[i2] = savedInstanceState.getInt(BUNDLE_KEY_FROMLEVEL + i2);
                }
            }
        }
        Context context = getActivity();
        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        this.mContentView = (ViewGroup) inflater.inflate(R.layout.equalizer, viewGroup, false);
        this.mIsRtl = config.getLayoutDirection() == 1;
        this.mIsPortrait = SoundEnhancementUtility.isPortraitLayout(context);
        this.mPresetBtn = (Button) this.mContentView.findViewById(R.id.cur_preset_name);
        this.mEqLayout = (LinearLayout) this.mContentView.findViewById(R.id.eq_band_component);
        for (int band = 0; band < EQ_BAND_NUM; band++) {
            RelativeLayout bandLayout = (RelativeLayout) this.mContentView.findViewById(EQ_BAND_ID[band]);
            this.mEqBandLayout[band] = bandLayout;
            this.mEqSeekBar[band] = (VerticalSeekBar) bandLayout.findViewById(R.id.vertical_slider);
            this.mEqSeekBar[band].setId(bandLayout.getId() + 10000);
            this.mEqFreqText[band] = (TextView) bandLayout.findViewById(R.id.band_freq);
            this.mEqPopupLayout[band] = (RelativeLayout) this.mContentView.findViewById(EQ_POPUP_ID[band]);
            this.mEqPopupText[band] = (TextView) this.mEqPopupLayout[band].findViewById(R.id.band_level_text);
            this.mEqPopupText[band].setVisibility(4);
            LayerDrawable defaultThumb = (LayerDrawable) res.getDrawable(R.drawable.vertical_thumb);
            if (defaultThumb != null) {
                defaultThumb.setColorFilter(this.mAccentColorFilter);
                this.mEqSeekBar[band].setThumb(defaultThumb);
            }
            ImageView edgeImage = (ImageView) bandLayout.findViewById(R.id.scale_bar_runnel_edge);
            edgeImage.getDrawable().setColorFilter(this.mAccentColorFilter);
            this.mEqSeekBar[band].getProgressDrawable().setColorFilter(this.mAccentColorFilter);
            this.mEqPopupText[band].setTextColor(-1);
            Drawable popupTextBackground = this.mEqPopupText[band].getBackground();
            popupTextBackground.setColorFilter(this.mAccentColorFilter);
            this.mEqPopupText[band].setBackground(popupTextBackground);
            int index = (BAND_NUM + band) - EQ_BAND_NUM;
            double freq = CENTER_FREQ[index];
            String freqText = this.mLocalise.getLocalisedCenterFreq(getActivity(), freq / 1000000.0d);
            this.mEqFreqText[band].setText(freqText);
            String freqText2 = this.mLocalise.getLocalisedCenterFreqForSpeech(getActivity(), freq / 1000000.0d);
            this.mEqSeekBar[band].setText(freqText2);
        }
        int[] iArr = BAND_LEVEL_RANGE;
        int minText = iArr[0] / 100;
        int maxText = iArr[1] / 100;
        RelativeLayout eqRangeLayout = (RelativeLayout) this.mContentView.findViewById(R.id.eq_scale_num);
        TextView[] eqScaleNumText = {(TextView) eqRangeLayout.findViewById(R.id.scale_num_lower_limit), (TextView) eqRangeLayout.findViewById(R.id.scale_num_0), (TextView) eqRangeLayout.findViewById(R.id.scale_num_upper_limit)};
        eqScaleNumText[0].setText(this.mLocalise.getLocalisedScaleNum(context, minText));
        eqScaleNumText[1].setText(this.mLocalise.getLocalisedScaleNum(context, 0));
        eqScaleNumText[2].setText(this.mLocalise.getLocalisedScaleNum(context, maxText));
        this.mCbRootLayout = this.mContentView.findViewById(R.id.eq_cb_root);
        this.mCbHorizontalSeekBar = (SeekBar) this.mContentView.findViewById(R.id.horizontal_slider);
        this.mCbVerticalSeekBar = (VerticalSeekBar) this.mContentView.findViewById(R.id.vertical_slider);
        this.mCbVerticalSeekBar.setText(this.mLocalise.getLocalisedClearBassForSpeech(context));
        LayerDrawable horizontalThumb = (LayerDrawable) res.getDrawable(R.drawable.horizontal_thumb);
        if (horizontalThumb != null) {
            horizontalThumb.setColorFilter(this.mAccentColorFilter);
            this.mCbHorizontalSeekBar.setThumb(horizontalThumb);
        }
        SeekBar seekBar = this.mCbHorizontalSeekBar;
        seekBar.setThumbOffset(seekBar.getThumbOffset());
        this.mCbHorizontalSeekBar.getProgressDrawable().setColorFilter(this.mAccentColorFilter);
        ImageView cbEdgeImage = (ImageView) this.mCbRootLayout.findViewById(R.id.cb_scale_bar_runnel_edge);
        if (cbEdgeImage != null) {
            cbEdgeImage.getDrawable().setColorFilter(this.mAccentColorFilter);
            if (this.mIsRtl) {
                cbEdgeImage.setScaleType(ImageView.ScaleType.FIT_END);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cbEdgeImage.getLayoutParams();
                params.removeRule(5);
                params.addRule(7, R.id.cb_scale_bar_runnel);
                cbEdgeImage.setLayoutParams(params);
            }
        }
        LayerDrawable verticalThumb = (LayerDrawable) res.getDrawable(R.drawable.vertical_thumb);
        if (verticalThumb != null) {
            verticalThumb.setColorFilter(this.mAccentColorFilter);
            this.mCbVerticalSeekBar.setThumb(verticalThumb);
        }
        this.mCbVerticalSeekBar.getProgressDrawable().setColorFilter(this.mAccentColorFilter);
        this.mCbPopupLayout = (RelativeLayout) this.mContentView.findViewById(R.id.cb_band_popup);
        this.mCbPopupText = (TextView) this.mCbPopupLayout.findViewById(R.id.band_level_text);
        this.mCbPopupText.setVisibility(4);
        this.mCbPopupText.setTextColor(-1);
        Drawable cbPopupTextBackground = this.mCbPopupText.getBackground();
        cbPopupTextBackground.setColorFilter(this.mAccentColorFilter);
        this.mCbPopupText.setBackground(cbPopupTextBackground);
        if (this.mIsPortrait) {
            RelativeLayout cbScaleNumLayout = (RelativeLayout) this.mContentView.findViewById(R.id.cb_scale_num);
            TextView[] cbScaleNumText = {(TextView) cbScaleNumLayout.findViewById(R.id.scale_num_lower_limit), (TextView) cbScaleNumLayout.findViewById(R.id.scale_num_0), (TextView) cbScaleNumLayout.findViewById(R.id.scale_num_upper_limit)};
            cbScaleNumText[0].setText(this.mLocalise.getLocalisedScaleNum(context, minText));
            i = 0;
            cbScaleNumText[1].setText(this.mLocalise.getLocalisedScaleNum(context, 0));
            cbScaleNumText[2].setText(this.mLocalise.getLocalisedScaleNum(context, maxText));
        } else {
            i = 0;
        }
        Bitmap hSliderThumb = BitmapFactory.decodeResource(res, R.drawable.sound_enhance_horizontal_slider_icn);
        this.mCbPaddingLR = hSliderThumb.getWidth() + (this.mCbHorizontalSeekBar.getPaddingLeft() - this.mCbHorizontalSeekBar.getThumbOffset()) + (this.mCbHorizontalSeekBar.getPaddingRight() - this.mCbHorizontalSeekBar.getThumbOffset());
        this.mPresetBtn.setOnClickListener(this.mPresetBtnOnClickListener);
        this.mPresetBtn.setOnTouchListener(this.mPresetBtnOnTouchListener);
        this.mPresetBtn.setOnFocusChangeListener(this.mPresetBtnOnFocusChangeListener);
        this.mCbHorizontalSeekBar.setOnSeekBarChangeListener(this.mCbOnSeekBarChangeListener);
        this.mCbHorizontalSeekBar.setOnKeyListener(this.mCbOnKeyListener);
        this.mCbHorizontalSeekBar.setOnFocusChangeListener(this.mCbOnFocusChangeListener);
        this.mCbVerticalSeekBar.setOnSeekBarChangeListener(this.mCbVerticalOnSeekBarChangeListener);
        this.mCbVerticalSeekBar.setOnKeyListener(this.mCbVerticalOnKeyListener);
        this.mCbVerticalSeekBar.setOnFocusChangeListener(this.mCbVerticalOnFocusChangeListener);
        int bandIndex = 0;
        VerticalSeekBar[] verticalSeekBarArr = this.mEqSeekBar;
        int length = verticalSeekBarArr.length;
        while (i < length) {
            int maxText2 = maxText;
            final VerticalSeekBar seekBar2 = verticalSeekBarArr[i];
            Context context2 = context;
            seekBar2.setOnSeekBarChangeListener(this.mEqVerticalOnSeekBarChangeListener);
            seekBar2.setOnKeyListener(this.mEqVerticalOnKeyListener);
            seekBar2.setOnFocusChangeListener(this.mEqVerticalOnFocusChangeListener);
            final int index2 = bandIndex;
            bandIndex++;
            seekBar2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.14
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (EqualizerFragment.this.getActivity() == null || EqualizerFragment.this.getActivity().isFinishing()) {
                        return true;
                    }
                    int h = seekBar2.getHeight();
                    EqualizerFragment.this.setEqSliderScale(h, index2);
                    seekBar2.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
            i++;
            maxText = maxText2;
            context = context2;
            res = res;
            eqRangeLayout = eqRangeLayout;
        }
        if (!this.mIsPortrait) {
            this.mCbVerticalSeekBar.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.15
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (EqualizerFragment.this.getActivity() == null || EqualizerFragment.this.getActivity().isFinishing()) {
                        return true;
                    }
                    int height = EqualizerFragment.this.mCbVerticalSeekBar.getHeight();
                    EqualizerFragment.this.setCbSliderScale(height);
                    EqualizerFragment.this.mCbVerticalSeekBar.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        } else {
            this.mCbHorizontalSeekBar.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.16
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (EqualizerFragment.this.getActivity() == null || EqualizerFragment.this.getActivity().isFinishing()) {
                        return true;
                    }
                    int height = EqualizerFragment.this.mCbHorizontalSeekBar.getHeight();
                    EqualizerFragment.this.setCbSliderScale(height);
                    EqualizerFragment.this.mCbHorizontalSeekBar.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
        return this.mContentView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getEqBandIndex(VerticalSeekBar seekBar) {
        VerticalSeekBar[] verticalSeekBarArr = this.mEqSeekBar;
        if (verticalSeekBarArr == null) {
            return 0;
        }
        int length = verticalSeekBarArr.length;
        for (int i = 0; i < length; i++) {
            if (this.mEqSeekBar[i].equals(seekBar)) {
                int index = i;
                return index;
            }
        }
        return 0;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        setupViewItems();
        finishActivityBySettings();
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mIsShownPresetDialog) {
            showPresetDialog();
            this.mIsShownPresetDialog = false;
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        this.mIsUpdatedViewItems = false;
        if (this.mPresetDialogFragment != null) {
            if ((getActivity().getChangingConfigurations() & 128) == 128 || (getActivity().getChangingConfigurations() & 512) == 512) {
                this.mIsShownPresetDialog = true;
            } else {
                cancelPresetDialog();
            }
            this.mPresetDialogFragment.dismiss();
            this.mPresetDialogFragment = null;
        }
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        if (this.mServiceConnected) {
            getActivity().getApplicationContext().unbindService(this);
            this.mServiceConnected = false;
        }
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mService != null && this.mIsSaveStatePossible) {
            this.mIsSaveStatePossible = false;
            outState.putBoolean(BUNDLE_KEY_AVAILABLE, true);
            outState.putInt(BUNDLE_KEY_LASTPRESET, this.mService.getCurrentEqualizerPreset());
            int[] iArr = this.mFromLevels;
            if (iArr != null) {
                outState.putInt(BUNDLE_KEY_NUMOFBANDS, iArr.length);
                for (int i = 0; i < this.mFromLevels.length; i++) {
                    outState.putInt(BUNDLE_KEY_FROMLEVEL + i, this.mFromLevels[i]);
                }
            }
        } else {
            outState.putBoolean(BUNDLE_KEY_AVAILABLE, false);
        }
        outState.putBoolean(BUNDLE_KEY_SHOW_PRESET, this.mIsShownPresetDialog);
        if (this.mIsShownPresetDialog) {
            outState.putInt(BUNDLE_KEY_CURRENT_PRESET, this.mCurrentPreset);
            outState.putInt(BUNDLE_KEY_PREVIOUS_PRESET, this.mPreviousPreset);
        }
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onPositiveButtonClicked(DialogInterface dialog, String tag) {
        this.mPreviousPreset = -1;
        this.mCurrentPreset = -1;
        this.mService.saveSettingsToPrefs();
        dialog.dismiss();
        setupBandLevel();
        reportEqualizerEvent(ReportContents.EVENT_EQUALIZER);
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onNegativeButtonClicked(DialogInterface dialog, String tag) {
        cancelPresetDialog();
        dialog.dismiss();
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onListItemClicked(DialogInterface dialog, String tag, int position) {
        this.mCurrentPreset = position;
        this.mService.useEqualizerPreset(this.mCurrentPreset);
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onDismiss(DialogInterface dialog, String tag) {
        if (this.mPresetDialogFragment != null) {
            this.mPresetDialogFragment = null;
        }
    }

    @Override // com.sonyericsson.soundenhancement.ListDialogFragment.Callbacks
    public void onCancel(DialogInterface dialog, String tag) {
        cancelPresetDialog();
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPresetDialog() {
        if (this.mPresetDialogFragment != null) {
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        Resources res = getActivity().getResources();
        ListDialogFragment.Builder builder = new ListDialogFragment.Builder();
        builder.setTitle(res.getString(R.string.sound_enhance_strings_setting_preset_txt));
        builder.setTargetFragment(this);
        builder.setPositiveButton(true);
        builder.setNegativeButton(true);
        int listIndex = this.mCurrentPreset;
        builder.setPresetNames(this.mPresetList);
        builder.setCurrentPosition(listIndex);
        this.mPresetDialogFragment = builder.create();
        this.mPresetDialogFragment.show(fragmentManager, EQUALIZER_PRESET_DIALOG_TAG);
    }

    private void cancelPresetDialog() {
        int i = this.mPreviousPreset;
        if (i != this.mCurrentPreset) {
            this.mService.useEqualizerPreset(i);
            this.mCurrentPreset = -1;
            this.mPreviousPreset = -1;
        }
    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 20 && isFocusActionBar() && !this.mIsPortrait) {
            this.mPresetBtn.requestFocusFromTouch();
        }
    }

    private void setupViewItems() {
        if (this.mService != null && !this.mIsUpdatedViewItems) {
            this.mIsUpdatedViewItems = true;
            this.mIsSaveStatePossible = true;
            setupSeekBarRange();
            setupBandLevel();
            setNextFocus();
        }
    }

    private void setupBandLevel() {
        int[] iArr;
        getActivity().setResult(-1);
        setupPreset();
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null && this.mIsUpdatedViewItems) {
            this.mToLevels = audioEffectService.getBandLevels();
        }
        boolean isAnimation = false;
        int[] iArr2 = this.mFromLevels;
        if (iArr2 != null && !Arrays.equals(iArr2, this.mToLevels)) {
            isAnimation = true;
        }
        if (isAnimation && !this.mIsShownPrevBandLevel) {
            setupBandLevel(this.mFromLevels);
            updateBandLevels();
        } else if (this.mIsShownPrevBandLevel && (iArr = this.mFromLevels) != null) {
            setupBandLevel(iArr);
            this.mIsShownPrevBandLevel = false;
        } else {
            setupBandLevel(this.mToLevels);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatedEqBandLevel(int band, int level) {
        int i = EQ_BAND_NUM;
        if (band >= i) {
            throw new IllegalArgumentException("Invalid band.");
        }
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null && this.mIsUpdatedViewItems) {
            int bandIndex = (BAND_NUM - i) + band;
            audioEffectService.setBandLevel(bandIndex, level);
            setupPreset(this.mService.getCurrentEqualizerPreset());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCbBandLevel(int level) {
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null && this.mIsUpdatedViewItems) {
            audioEffectService.setBandLevel(0, level);
            setupPreset(this.mService.getCurrentEqualizerPreset());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCbProgress() {
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null && this.mIsUpdatedViewItems) {
            int level = audioEffectService.getBandLevel(0);
            this.mCbHorizontalSeekBar.setProgress(level - BAND_LEVEL_RANGE[0]);
            this.mCbVerticalSeekBar.setProgress(level - BAND_LEVEL_RANGE[0]);
            this.mService.saveSettingsToPrefs();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgressAtEdge(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        if (progress == seekBar.getMax()) {
            seekBar.setProgress(progress - 1);
        } else if (progress == 0) {
            seekBar.setProgress(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgressAtEdge(VerticalSeekBar seekBar) {
        int progress = seekBar.getProgress();
        if (progress == seekBar.getMax()) {
            seekBar.setProgress(progress - 1);
        } else if (progress == 0) {
            seekBar.setProgress(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEqPopupView(int band) {
        int scaleH;
        int yOffset;
        if (band >= EQ_BAND_NUM) {
            throw new IllegalArgumentException("Invalid band.");
        }
        if (this.mService != null && this.mIsUpdatedViewItems) {
            hideEqPopupView(band, false);
            RelativeLayout popupLayout = this.mEqPopupLayout[band];
            TextView popupText = this.mEqPopupText[band];
            VerticalSeekBar seekBar = this.mEqSeekBar[band];
            RelativeLayout eqBandLayout = this.mEqBandLayout[band];
            int bandIndex = (BAND_NUM - EQ_BAND_NUM) + band;
            int bandLevel = this.mService.getBandLevel(bandIndex);
            String levelText = this.mLocalise.getLocalisedBandLevel(getActivity(), bandLevel / 100);
            popupText.setText(levelText);
            int eqBandW = eqBandLayout.getWidth();
            int eqBandLeft = eqBandLayout.getLeft();
            int eqBandRight = eqBandLayout.getRight();
            int popupW = popupText.getWidth();
            int popupH = popupText.getHeight();
            int max = seekBar.getMax();
            int progress = seekBar.getProgress();
            int scaleH2 = this.mScaleH;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
            Resources res = getActivity().getResources();
            if (!this.mIsPortrait) {
                int margin = res.getDimensionPixelSize(R.dimen.eq_band_component_margin_end);
                ViewGroup seekRoot = (ViewGroup) this.mContentView.findViewById(R.id.eq_band_component_root);
                ViewGroup eqRoot = (ViewGroup) this.mContentView.findViewById(R.id.eq_band_root);
                int seekRootTop = seekRoot.getTop();
                int seekRootW = seekRoot.getWidth();
                int eqRootLeft = eqRoot.getLeft();
                int rootH = this.mContentView.getHeight();
                int startX = !this.mIsRtl ? eqRootLeft + eqBandLeft : (seekRootW - eqBandRight) - margin;
                int margin2 = scaleH2 * 1000;
                int scroll = ((margin2 / max) * (max - progress)) / 1000;
                int xOffset = startX + ((eqBandW - popupW) / 2);
                int yOffset2 = (seekRootTop + scroll) - popupH;
                int yOffset3 = yOffset2 < 0 ? 0 : yOffset2;
                int scroll2 = rootH - popupH;
                scaleH = yOffset3 > scroll2 ? rootH - popupH : yOffset3;
                yOffset = xOffset;
            } else {
                int margin3 = res.getDimensionPixelSize(R.dimen.eq_band_component_margin_end);
                ViewGroup eqRoot2 = (ViewGroup) this.mContentView.findViewById(R.id.eq_band_root);
                int eqRootTop = eqRoot2.getTop();
                int eqRootW = eqRoot2.getWidth();
                int eqRootLeft2 = this.mEqLayout.getLeft();
                int rootH2 = this.mContentView.getHeight();
                int startX2 = !this.mIsRtl ? eqRootLeft2 + eqBandLeft : (eqRootW - eqBandRight) - margin3;
                int margin4 = scaleH2 * 1000;
                int scroll3 = ((margin4 / max) * (max - progress)) / 1000;
                int xOffset2 = startX2 + ((eqBandW - popupW) / 2);
                int yOffset4 = (eqRootTop + scroll3) - popupH;
                int yOffset5 = yOffset4 < 0 ? 0 : yOffset4;
                int scroll4 = rootH2 - popupH;
                scaleH = yOffset5 > scroll4 ? rootH2 - popupH : yOffset5;
                yOffset = xOffset2;
            }
            params.setMarginStart(yOffset);
            params.setMargins(0, scaleH, 0, 0);
            popupLayout.setLayoutParams(params);
            popupText.setVisibility(0);
            int index = (band + BAND_NUM) - EQ_BAND_NUM;
            double freq = CENTER_FREQ[index];
            popupText.setContentDescription(this.mLocalise.getLocalisedEqualizerSliderValue(getActivity(), bandLevel / 100) + "\n" + this.mLocalise.getLocalisedCenterFreqForSpeech(getActivity(), freq / 1000000.0d));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideEqPopupView(int band, boolean isDelayed) {
        if (band >= EQ_BAND_NUM) {
            throw new IllegalArgumentException("Invalid band.");
        }
        this.mPopUpRemoveHandler.removeMessages(band);
        if (!isDelayed) {
            this.mEqPopupText[band].setVisibility(8);
            return;
        }
        Message msg = Message.obtain();
        msg.what = band;
        this.mPopUpRemoveHandler.sendMessageDelayed(msg, 250L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCbPopupView() {
        int yOffset;
        int yOffset2;
        if (this.mService != null && this.mIsUpdatedViewItems) {
            hideCbPopupView(false);
            int bandLevel = this.mService.getBandLevel(0);
            String levelText = this.mLocalise.getLocalisedBandLevel(getActivity(), bandLevel / 100);
            this.mCbPopupText.setText(levelText);
            int popupW = this.mCbPopupText.getWidth();
            int popupH = this.mCbPopupText.getHeight();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
            Resources res = getActivity().getResources();
            if (this.mIsPortrait) {
                ViewGroup parent = (ViewGroup) this.mContentView.findViewById(R.id.cb_band_component);
                int padding = res.getDimensionPixelSize(R.dimen.cb_seek_bar_padding);
                int cbW = this.mCbRootLayout.getWidth();
                int cbH = this.mCbRootLayout.getHeight();
                int rootH = this.mContentView.getHeight();
                int startX = !this.mIsRtl ? parent.getLeft() : cbW - parent.getRight();
                int offsetX1000 = ((this.mCbHorizontalSeekBar.getWidth() - this.mCbPaddingLR) * 1000) / this.mCbHorizontalSeekBar.getMax();
                int scroll = (this.mCbHorizontalSeekBar.getProgress() * offsetX1000) / 1000;
                int xOffset = ((startX + scroll) + padding) - (popupW / 2);
                yOffset = (rootH - cbH) - popupH;
                yOffset2 = xOffset;
            } else {
                ViewGroup seekRoot = (ViewGroup) this.mContentView.findViewById(R.id.eq_band_component_root);
                int seekRootTop = seekRoot.getTop();
                int seekRootW = seekRoot.getWidth();
                int cbW2 = this.mCbRootLayout.getWidth();
                int cbLeft = this.mCbRootLayout.getLeft();
                int cbRight = this.mCbRootLayout.getRight();
                int rootH2 = this.mContentView.getHeight();
                int max = this.mCbVerticalSeekBar.getMax();
                int progress = this.mCbVerticalSeekBar.getProgress();
                int scaleH = this.mScaleH;
                int startX2 = !this.mIsRtl ? cbLeft : seekRootW - cbRight;
                int seekRootTop2 = scaleH * 1000;
                int scroll2 = ((seekRootTop2 / max) * (max - progress)) / 1000;
                int xOffset2 = startX2 + ((cbW2 - popupW) / 2);
                int yOffset3 = (seekRootTop + scroll2) - popupH;
                int yOffset4 = yOffset3 < 0 ? 0 : yOffset3;
                int progress2 = rootH2 - popupH;
                yOffset = yOffset4 > progress2 ? rootH2 - popupH : yOffset4;
                yOffset2 = xOffset2;
            }
            params.setMarginStart(yOffset2);
            params.setMargins(0, yOffset, 0, 0);
            this.mCbPopupLayout.setLayoutParams(params);
            this.mCbPopupText.setVisibility(0);
            this.mCbPopupText.setContentDescription(this.mLocalise.getLocalisedEqualizerSliderValue(getActivity(), bandLevel / 100) + "\n" + this.mLocalise.getLocalisedClearBassForSpeech(getActivity()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideCbPopupView(boolean isDelayed) {
        this.mPopUpRemoveHandler.removeMessages(LAST_POPUP_BAND_CLEAR_BASS_ID);
        if (!isDelayed) {
            this.mCbPopupText.setVisibility(8);
            this.mPopUpRemoveHandler.removeMessages(LAST_POPUP_BAND_CLEAR_BASS_ID);
        } else {
            Message msg = Message.obtain();
            msg.what = LAST_POPUP_BAND_CLEAR_BASS_ID;
            this.mPopUpRemoveHandler.sendMessageDelayed(msg, 250L);
        }
    }

    private void setupSeekBarRange() {
        int[] iArr = BAND_LEVEL_RANGE;
        int maxRange = iArr[1] - iArr[0];
        this.mCbHorizontalSeekBar.setMax(maxRange);
        this.mCbVerticalSeekBar.setMax(maxRange);
        for (int band = 0; band < EQ_BAND_NUM; band++) {
            this.mEqSeekBar[band].setMax(maxRange);
        }
    }

    private void setupPreset() {
        int index;
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService != null && this.mIsUpdatedViewItems) {
            this.mSavedPreset = audioEffectService.getCurrentEqualizerPreset();
            if (this.mPreviousPreset == -1) {
                index = this.mSavedPreset;
            } else {
                index = this.mPreviousPreset;
            }
            this.mPresetBtn.setText(this.mPresetList[index]);
            this.mPresetBtn.setAllCaps(false);
        }
    }

    private void setupPreset(int preset) {
        if (preset != this.mSavedPreset) {
            this.mPresetBtn.setText(this.mPresetList[preset]);
            this.mPresetBtn.setAllCaps(false);
            this.mSavedPreset = preset;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupBandLevel(int[] level) {
        if (BAND_NUM > EQ_BAND_NUM) {
            if (this.mCbHorizontalSeekBar.isLaidOut()) {
                this.mCbHorizontalSeekBar.setProgress(level[0] - BAND_LEVEL_RANGE[0]);
            }
            if (this.mCbVerticalSeekBar.isLaidOut()) {
                this.mCbVerticalSeekBar.setProgress(level[0] - BAND_LEVEL_RANGE[0]);
            }
        }
        int band = 0;
        while (true) {
            int i = EQ_BAND_NUM;
            if (band >= i) {
                break;
            }
            int index = (BAND_NUM + band) - i;
            if (this.mEqSeekBar[band].isLaidOut()) {
                this.mEqSeekBar[band].setProgress(level[index] - BAND_LEVEL_RANGE[0]);
            }
            band++;
        }
        for (RelativeLayout bandLayout : this.mEqBandLayout) {
            bandLayout.setVisibility(0);
        }
        this.mCbRootLayout.setVisibility(0);
    }

    private void updateBandLevels() {
        if (this.mAnimRunnable == null) {
            this.mAnimRunnable = new Runnable() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.17
                @Override // java.lang.Runnable
                public void run() {
                    EqualizerFragment.access$3408(EqualizerFragment.this);
                    if (EqualizerFragment.this.mFromLevels == null || EqualizerFragment.this.mToLevels == null) {
                        EqualizerFragment.this.mAnimIndex = 0;
                        EqualizerFragment.this.mFromLevels = null;
                        if (EqualizerFragment.this.mToLevels != null) {
                            EqualizerFragment equalizerFragment = EqualizerFragment.this;
                            equalizerFragment.setupBandLevel(equalizerFragment.mToLevels);
                            return;
                        }
                        return;
                    }
                    EqualizerFragment equalizerFragment2 = EqualizerFragment.this;
                    equalizerFragment2.updateBandLevelsWithRatio(equalizerFragment2.mFromLevels, EqualizerFragment.this.mToLevels, EqualizerFragment.getInterpolation(EqualizerFragment.this.mAnimIndex / 5.0f));
                    if (EqualizerFragment.this.mAnimIndex < 5) {
                        EqualizerFragment.this.mAnimHandler.postDelayed(this, 50L);
                    } else {
                        EqualizerFragment.this.mAnimIndex = 0;
                        EqualizerFragment.this.mFromLevels = null;
                    }
                }
            };
        }
        this.mAnimHandler.postDelayed(this.mAnimRunnable, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBandLevelsWithRatio(int[] from, int[] to, float ratio) {
        if (BAND_NUM > EQ_BAND_NUM) {
            int level = ((int) ((to[0] - from[0]) * ratio)) + from[0];
            int progress = level - BAND_LEVEL_RANGE[0];
            this.mCbHorizontalSeekBar.setProgress(progress);
            this.mCbVerticalSeekBar.setProgress(progress);
        }
        int band = 0;
        while (true) {
            int i = EQ_BAND_NUM;
            if (band < i) {
                int bandIndex = (BAND_NUM + band) - i;
                int level2 = ((int) ((to[bandIndex] - from[bandIndex]) * ratio)) + from[bandIndex];
                this.mEqSeekBar[band].setProgress(level2 - BAND_LEVEL_RANGE[0]);
                band++;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float getInterpolation(float input) {
        return ((float) (Math.cos(((double) (1.0f + input)) * 3.141592653589793d) / 2.0d)) + 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEqSliderScale(int height, int band) {
        LinearLayout layout = (LinearLayout) this.mEqBandLayout[band].findViewById(R.id.scale_bar);
        setScale(layout, this.mEqSeekBar[band], height);
        refreshEqualizerBandLevel(band);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCbSliderScale(int height) {
        View layout = this.mContentView.findViewById(R.id.cb_scale_bar);
        if (!this.mIsPortrait) {
            setScale((LinearLayout) layout, this.mCbVerticalSeekBar, height);
        } else {
            setCbHorizontalScale((LinearLayout) layout);
        }
        refreshClearBassBandLevel();
    }

    private void setNextFocus() {
        boolean isPortrait = this.mIsPortrait;
        boolean isRtl = this.mIsRtl;
        if (!isPortrait) {
            Button button = this.mPresetBtn;
            button.setNextFocusRightId(button.getId());
        }
        Button button2 = this.mPresetBtn;
        button2.setNextFocusLeftId(button2.getId());
        if (isPortrait) {
            this.mPresetBtn.setNextFocusDownId(this.mEqSeekBar[4].getId());
            this.mLastFocusId = this.mEqSeekBar[0].getId();
        } else {
            this.mPresetBtn.setNextFocusDownId(this.mCbVerticalSeekBar.getId());
        }
        if (isPortrait) {
            this.mCbHorizontalSeekBar.setNextFocusUpId(this.mEqSeekBar[4].getId());
        }
        SeekBar seekBar = this.mCbHorizontalSeekBar;
        seekBar.setNextFocusLeftId(seekBar.getId());
        SeekBar seekBar2 = this.mCbHorizontalSeekBar;
        seekBar2.setNextFocusRightId(seekBar2.getId());
        if (isRtl) {
            this.mCbVerticalSeekBar.setNextFocusLeftId(this.mEqSeekBar[0].getId());
            VerticalSeekBar verticalSeekBar = this.mCbVerticalSeekBar;
            verticalSeekBar.setNextFocusRightId(verticalSeekBar.getId());
        } else {
            this.mCbVerticalSeekBar.setNextFocusRightId(this.mEqSeekBar[0].getId());
            VerticalSeekBar verticalSeekBar2 = this.mCbVerticalSeekBar;
            verticalSeekBar2.setNextFocusLeftId(verticalSeekBar2.getId());
        }
        if (isPortrait) {
            if (isRtl) {
                VerticalSeekBar[] verticalSeekBarArr = this.mEqSeekBar;
                verticalSeekBarArr[0].setNextFocusRightId(verticalSeekBarArr[0].getId());
            } else {
                VerticalSeekBar[] verticalSeekBarArr2 = this.mEqSeekBar;
                verticalSeekBarArr2[0].setNextFocusLeftId(verticalSeekBarArr2[0].getId());
            }
        }
        if (isRtl) {
            VerticalSeekBar[] verticalSeekBarArr3 = this.mEqSeekBar;
            verticalSeekBarArr3[4].setNextFocusLeftId(verticalSeekBarArr3[4].getId());
        } else {
            VerticalSeekBar[] verticalSeekBarArr4 = this.mEqSeekBar;
            verticalSeekBarArr4[4].setNextFocusRightId(verticalSeekBarArr4[4].getId());
        }
        int presetId = this.mPresetBtn.getId();
        this.mEqSeekBar[0].setNextFocusUpId(presetId);
        this.mEqSeekBar[1].setNextFocusUpId(presetId);
        this.mEqSeekBar[2].setNextFocusUpId(presetId);
        this.mEqSeekBar[3].setNextFocusUpId(presetId);
        this.mEqSeekBar[4].setNextFocusUpId(presetId);
        if (!isPortrait) {
            this.mCbVerticalSeekBar.setNextFocusUpId(presetId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorNoControl() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setResult(-1);
        if (isResumed()) {
            new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setTitle(android.R.string.dialog_alert_title).setMessage(R.string.sound_enhance_strings_error_not_available_by_prio_txt).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.EqualizerFragment.18
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    activity.finish();
                }
            }).setCancelable(false).create().show();
        } else {
            activity.finish();
        }
    }

    public boolean isFocusActionBar() {
        View focusedView = getActivity().getCurrentFocus();
        if (focusedView != null && focusedView != this.mPresetBtn && focusedView != this.mCbHorizontalSeekBar && focusedView != this.mCbVerticalSeekBar) {
            VerticalSeekBar[] verticalSeekBarArr = this.mEqSeekBar;
            if (focusedView != verticalSeekBarArr[0] && focusedView != verticalSeekBarArr[1] && focusedView != verticalSeekBarArr[2] && focusedView != verticalSeekBarArr[3] && focusedView != verticalSeekBarArr[4]) {
                return true;
            }
        }
        return false;
    }

    private void setScale(LinearLayout layout, VerticalSeekBar seekBar, int height) {
        int scaleCenterLineHeight;
        Resources res = getActivity().getResources();
        int scaleLineHeight = res.getDimensionPixelSize(R.dimen.scalebar_line_height);
        int scaleCenterLineHeight2 = res.getDimensionPixelSize(R.dimen.scalebar_center_line_height);
        int scaleCenterLineOffset = scaleCenterLineHeight2 - scaleLineHeight;
        int scaleLineColor = res.getColor(R.color.scalebar_line_color);
        int scaleCenterLineColor = res.getColor(R.color.scalebar_center_line_color);
        this.mScaleH = (height - (seekBar.getPaddingTop() * 2)) + scaleLineHeight;
        int scaleBarTotalIntervalHeight = this.mScaleH - (scaleLineHeight * 11);
        int scaleBarAdjustTotalIntervalHeight = (scaleBarTotalIntervalHeight / 10) * 10;
        if ((scaleBarAdjustTotalIntervalHeight / 10) % 2 != 0) {
            scaleBarAdjustTotalIntervalHeight -= 10;
        }
        this.mScaleOffset = (seekBar.getPaddingTop() + ((scaleBarTotalIntervalHeight - scaleBarAdjustTotalIntervalHeight) / 2)) - (scaleCenterLineOffset / 2);
        int thumbOffset = seekBar.getThumbOffset() - ((scaleBarTotalIntervalHeight - scaleBarAdjustTotalIntervalHeight) / 2);
        int scaleInterval = scaleBarAdjustTotalIntervalHeight / 10;
        seekBar.setThumbOffset(thumbOffset);
        int scale = 0;
        while (scale < 11) {
            View scaleBar = new View(getActivity());
            Resources res2 = res;
            int scaleLineHeight2 = scaleLineHeight;
            if (scale == 5) {
                scaleLineHeight = scaleCenterLineHeight2;
            }
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(-1, scaleLineHeight);
            if (scale == 5) {
                scaleBar.setBackgroundColor(scaleCenterLineColor);
                scaleCenterLineHeight = scaleCenterLineHeight2;
                param.setMargins(0, scaleInterval - (scaleCenterLineOffset / 2), 0, scaleInterval - (scaleCenterLineOffset / 2));
            } else {
                scaleCenterLineHeight = scaleCenterLineHeight2;
                scaleBar.setBackgroundColor(scaleLineColor);
                if (scale < 5 - 1) {
                    if (scale == 0) {
                        param.setMargins(0, this.mScaleOffset, 0, scaleInterval);
                    } else {
                        param.setMargins(0, 0, 0, scaleInterval);
                    }
                } else if (scale > 5 + 1) {
                    param.setMargins(0, scaleInterval, 0, 0);
                } else {
                    param.setMargins(0, 0, 0, 0);
                }
            }
            layout.addView(scaleBar, param);
            scale++;
            res = res2;
            scaleLineHeight = scaleLineHeight2;
            scaleCenterLineHeight2 = scaleCenterLineHeight;
        }
    }

    private void setCbHorizontalScale(LinearLayout layout) {
        int centerLineColor;
        int centerLineOffset;
        Resources res = getActivity().getResources();
        int lineColor = res.getColor(R.color.scalebar_line_color);
        int centerLineColor2 = res.getColor(R.color.scalebar_center_line_color);
        int lineW = res.getDimensionPixelSize(R.dimen.scalebar_line_height);
        int centerLineW = res.getDimensionPixelSize(R.dimen.scalebar_center_line_height);
        int centerLineOffset2 = centerLineW - lineW;
        int parentH = layout.getLayoutParams().height;
        int seekBarW = this.mCbHorizontalSeekBar.getWidth() - (this.mCbHorizontalSeekBar.getPaddingStart() * 2);
        int totalIntervalW = seekBarW - ((lineW * 10) + centerLineW);
        int adjustIntervalW = (totalIntervalW / 10) * 10;
        if ((adjustIntervalW / 10) % 2 != 0) {
            adjustIntervalW -= 10;
        }
        int scaleOffset = (this.mCbHorizontalSeekBar.getPaddingStart() + ((totalIntervalW - adjustIntervalW) / 2)) - (centerLineOffset2 / 2);
        int thumbOffset = (this.mCbHorizontalSeekBar.getThumbOffset() - ((totalIntervalW - adjustIntervalW) / 2)) - (centerLineOffset2 / 2);
        this.mCbHorizontalSeekBar.setThumbOffset(thumbOffset);
        int scaleInterval = adjustIntervalW / 10;
        int centerIndex = 10 / 2;
        int scale = 0;
        while (true) {
            Resources res2 = res;
            if (scale < 11) {
                int lineW2 = lineW;
                View scaleBar = new View(getActivity());
                int centerLineW2 = centerLineW;
                if (scale != centerIndex) {
                    centerLineW = lineW2;
                }
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(centerLineW, parentH);
                if (scale == centerIndex) {
                    scaleBar.setBackgroundColor(centerLineColor2);
                    centerLineColor = centerLineColor2;
                    int centerLineColor3 = scaleInterval - (centerLineOffset2 / 2);
                    centerLineOffset = centerLineOffset2;
                    param.setMargins(scaleInterval - (centerLineOffset2 / 2), 0, centerLineColor3, 0);
                } else {
                    centerLineColor = centerLineColor2;
                    centerLineOffset = centerLineOffset2;
                    scaleBar.setBackgroundColor(lineColor);
                    param.setMargins(0, 0, 0, 0);
                    if (scale < centerIndex - 1) {
                        if (scale == 0) {
                            param.setMarginStart(scaleOffset);
                            param.setMarginEnd(scaleInterval);
                        } else {
                            param.setMarginStart(0);
                            param.setMarginEnd(scaleInterval);
                        }
                    } else if (scale > centerIndex + 1) {
                        param.setMarginStart(scaleInterval);
                        param.setMarginEnd(0);
                    } else {
                        param.setMarginStart(0);
                        param.setMarginEnd(0);
                    }
                }
                layout.addView(scaleBar, param);
                scale++;
                res = res2;
                lineW = lineW2;
                centerLineW = centerLineW2;
                centerLineOffset2 = centerLineOffset;
                centerLineColor2 = centerLineColor;
            } else {
                return;
            }
        }
    }

    private void refreshEqualizerBandLevel(int band) {
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService == null) {
            return;
        }
        int[] bandLevels = this.mFromLevels;
        if (bandLevels == null) {
            bandLevels = audioEffectService.getBandLevels();
        }
        int[] refreshLevels = bandLevels;
        int bandIndex = (BAND_NUM + band) - EQ_BAND_NUM;
        int refreshLevel = refreshLevels[bandIndex];
        this.mEqSeekBar[band].setProgress(refreshLevel - BAND_LEVEL_RANGE[0]);
    }

    private void refreshClearBassBandLevel() {
        AudioEffectService audioEffectService = this.mService;
        if (audioEffectService == null) {
            return;
        }
        int[] bandLevels = this.mFromLevels;
        if (bandLevels == null) {
            bandLevels = audioEffectService.getBandLevels();
        }
        int[] refreshLevels = bandLevels;
        int refreshLevel = refreshLevels[0];
        this.mCbVerticalSeekBar.setProgress(refreshLevel - BAND_LEVEL_RANGE[0]);
        this.mCbHorizontalSeekBar.setProgress(refreshLevel - BAND_LEVEL_RANGE[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportEqualizerEvent(String event) {
        int preset = this.mService.getCurrentEqualizerPreset();
        String[] presetList = AudioEffectUtilities.getEqualizerPresets();
        int[] bandLevels = this.mService.getBandLevels();
        String[] values = {presetList[preset], AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[0]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[1]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[2]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[3]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[4]), AudioEffectUtilities.getEqualizerBandLevelLabel(bandLevels[5])};
        Reporter.reportEvent(getActivity().getApplicationContext(), event, values);
    }

    private void finishActivityBySettings() {
        Activity activity;
        if (!SoundEnhancementUtility.canShowSoundEffectsScreen(this.mService) && (activity = getActivity()) != null && !activity.isFinishing()) {
            activity.finish();
        }
    }
}

package com.sonyericsson.soundenhancement;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectService;
import com.sonyericsson.soundenhancement.audioeffect.AudioEffectUtilities;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import com.sonyericsson.soundenhancement.surroundgallery.ViewPagerContainer;

/* JADX INFO: loaded from: classes2.dex */
public class SurroundSelectorFragment extends Fragment implements ServiceConnection {
    private static final String LOG_TAG = "SoundEnhancement";
    private SurroundSelectorAdapter mAdapter;
    private ImageView mCheckIcon;
    private ViewPagerContainer mContainer;
    private int mContentType;
    private boolean mIsCheckIconSmall;
    private boolean mIsUpdatedViewItems;
    private SoundEnhancementLocalise mLocalise;
    private OrientationEventListener mOrientationListener;
    private String mPackageName;
    private SoundEnhancementPresetList mPresetList;
    private AudioEffectService mService;
    private boolean mServiceConnected;
    private AudioEffectManager.OnControlStatusChangeListener mStatusChangeListener = new AudioEffectManager.OnControlStatusChangeListener() { // from class: com.sonyericsson.soundenhancement.SurroundSelectorFragment.1
        @Override // com.sonyericsson.soundenhancement.audioeffect.AudioEffectManager.OnControlStatusChangeListener
        public void onControlStatusChange(boolean controlGranted) {
            if (!controlGranted) {
                SurroundSelectorFragment.this.showErrorNoControl();
            }
        }
    };
    private TextView mSurroundDescription;
    private TextView mTextView;
    private ViewPager mViewPager;

    @Override // android.app.Fragment
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.mLocalise = new SoundEnhancementLocalise();
        this.mPresetList = new SoundEnhancementPresetList();
        Intent intent = getActivity().getIntent();
        if (intent == null) {
            getActivity().finish();
            return;
        }
        this.mPackageName = intent.getStringExtra("android.media.extra.PACKAGE_NAME");
        this.mContentType = intent.getIntExtra("android.media.extra.CONTENT_TYPE", 0);
        this.mPresetList.queryAvailableSurroundPresets(AudioEffectUtilities.getSurroundPresetNames());
        startAndBindToService();
        getActivity().setVolumeControlStream(3);
    }

    private void startAndBindToService() {
        Intent serviceStartIntent = new Intent("android.intent.action.MAIN");
        ComponentName componentName = new ComponentName(getActivity(), (Class<?>) SoundEnhancementService.class);
        serviceStartIntent.setComponent(componentName);
        getActivity().startService(serviceStartIntent);
        this.mServiceConnected = getActivity().getApplicationContext().bindService(serviceStartIntent, this, 1);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.surround_selector_view, viewGroup, false);
        this.mAdapter = new SurroundSelectorAdapter(getActivity());
        this.mContainer = (ViewPagerContainer) contentView.findViewById(R.id.pager_container);
        this.mCheckIcon = (ImageView) contentView.findViewById(R.id.surround_check_icon);
        this.mViewPager = this.mContainer.getViewPager();
        this.mViewPager.setClipChildren(false);
        this.mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.sonyericsson.soundenhancement.SurroundSelectorFragment.2
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                SurroundSelectorFragment.this.mAdapter.setSelected(position);
                if (SurroundSelectorFragment.this.mService != null && SurroundSelectorFragment.this.isResumed()) {
                    SurroundSelectorFragment.this.mService.setSurroundPreset(SurroundSelectorFragment.this.mPresetList.getSurroundPresetFromUiListIndex(position));
                    SurroundSelectorFragment.this.mService.saveSettingsToPrefs();
                    Reporter.reportEvent(SurroundSelectorFragment.this.getActivity().getApplicationContext(), ReportContents.EVENT_SURROUND, SurroundSelectorFragment.this.mPresetList.getSurroundPresetNames()[SurroundSelectorFragment.this.mViewPager.getCurrentItem()]);
                }
                if (SurroundSelectorFragment.this.mTextView != null && SurroundSelectorFragment.this.mSurroundDescription != null) {
                    Context context = SurroundSelectorFragment.this.getActivity().getApplicationContext();
                    String[] presetList = SurroundSelectorFragment.this.mPresetList.getSurroundPresetNames();
                    SurroundSelectorFragment.this.mTextView.setText(SurroundSelectorFragment.this.mLocalise.getLocalisedSurroundPresetName(context, presetList[position]));
                    SurroundSelectorFragment.this.mSurroundDescription.setText(SurroundSelectorFragment.this.mLocalise.getLocalisedSurroundDescription(context, presetList[position]));
                    SurroundSelectorFragment.this.mContainer.setContentDescription(context.getResources().getString(R.string.sound_enhance_strings_accessibility_vpt_list_item_txt, SurroundSelectorFragment.this.mLocalise.getLocalisedSurroundPresetName(context, presetList[position]) + SurroundSelectorFragment.this.mLocalise.getLocalisedSurroundDescription(context, presetList[position])));
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                SurroundSelectorFragment.this.mContainer.invalidate();
                float currentPositionAndOffset = position + positionOffset;
                if (currentPositionAndOffset != SurroundSelectorFragment.this.mAdapter.getPositionAndOffset() && SurroundSelectorFragment.this.mAdapter.getScrollState() == 1 && !SurroundSelectorFragment.this.mIsCheckIconSmall) {
                    SurroundSelectorFragment.this.animateCheckIcon(R.animator.check_icon_scale_shrink);
                    SurroundSelectorFragment.this.mIsCheckIconSmall = true;
                }
                SurroundSelectorFragment.this.mAdapter.setPositionAndOffset(currentPositionAndOffset);
            }

            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
                if (state != 1 && SurroundSelectorFragment.this.mIsCheckIconSmall) {
                    SurroundSelectorFragment.this.animateCheckIcon(R.animator.check_icon_scale_grow);
                    SurroundSelectorFragment.this.mIsCheckIconSmall = false;
                }
                SurroundSelectorFragment.this.mAdapter.setScrollState(state);
            }
        });
        this.mOrientationListener = new OrientationEventListener(getActivity(), 3) { // from class: com.sonyericsson.soundenhancement.SurroundSelectorFragment.3
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                if (SurroundSelectorFragment.this.mTextView != null) {
                    SurroundSelectorFragment.this.mTextView.invalidate();
                }
                if (SurroundSelectorFragment.this.mSurroundDescription != null) {
                    SurroundSelectorFragment.this.mSurroundDescription.invalidate();
                }
            }
        };
        this.mOrientationListener.enable();
        this.mTextView = (TextView) contentView.findViewById(R.id.surround_textview);
        this.mSurroundDescription = (TextView) contentView.findViewById(R.id.surround_description);
        boolean isMulti = SoundEnhancementUtility.isMultiWindowMode(getActivity());
        setupViewLayout(isMulti);
        return contentView;
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        setupViewItems();
        finishActivityBySettings();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.mIsUpdatedViewItems = false;
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        if (this.mServiceConnected) {
            getActivity().getApplicationContext().unbindService(this);
            this.mServiceConnected = false;
        }
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setAdapter(null);
        }
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        setupViewLayout(isInMultiWindowMode);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.mService = ((SoundEnhancementService.SoundEnhancementServiceBinder) service).getAudioEffectService();
        String str = this.mPackageName;
        if (str != null && str.length() > 0) {
            this.mService.createSessionHolder(this.mPackageName, this.mContentType);
            this.mService.restoreAudioSessionFromPreference();
            this.mService.setControlStatusListener(this.mPackageName, this.mStatusChangeListener);
        }
        setupViewItems();
        finishActivityBySettings();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        String str;
        if (this.mService != null && (str = this.mPackageName) != null && str.length() > 0) {
            this.mService.cancelControlStatusListener(this.mPackageName);
        }
        this.mService = null;
        this.mServiceConnected = false;
    }

    private void setupViewLayout(boolean isMulti) {
        int w;
        int h;
        Resources res = getActivity().getResources();
        if (!isMulti) {
            w = res.getDimensionPixelSize(R.dimen.surround_preset_bg_width);
            h = res.getDimensionPixelSize(R.dimen.surround_preset_bg_height);
        } else {
            w = res.getDimensionPixelSize(R.dimen.surround_preset_bg_small_width);
            h = res.getDimensionPixelSize(R.dimen.surround_preset_bg_small_height);
        }
        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(w, h, 1);
        this.mViewPager.setLayoutParams(param);
        int margin = res.getDimensionPixelSize(R.dimen.surround_preset_margin);
        this.mViewPager.setPageMargin(margin);
        this.mAdapter.setMultiWindowLayout(isMulti);
    }

    private void setupViewItems() {
        if (this.mService != null && !this.mIsUpdatedViewItems) {
            this.mIsUpdatedViewItems = true;
            Context context = getActivity().getApplicationContext();
            if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
                this.mPresetList.setIsRtlAlphabet(true);
            } else {
                this.mPresetList.setIsRtlAlphabet(false);
            }
            String[] names = this.mPresetList.getSurroundPresetNames();
            this.mAdapter.setImageIds(this.mLocalise.getImageIdsFromSurroundPresetNames(getActivity(), names));
            this.mAdapter.setDescriptions(this.mLocalise.getLocalisedSurroundDescriptions(getActivity(), names));
            this.mAdapter.setPresetNames(this.mLocalise.getLocalisedSurroundPresetNames(getActivity(), names));
            int index = this.mPresetList.getUiListIndexFromSurroundPreset(this.mService.getCurrentSurroundPreset());
            this.mAdapter.setSelected(index);
            this.mViewPager.setAdapter(this.mAdapter);
            this.mViewPager.setOffscreenPageLimit(this.mAdapter.getCount());
            this.mViewPager.setCurrentItem(index);
            this.mContainer.setContentDescription(context.getResources().getString(R.string.sound_enhance_strings_accessibility_vpt_list_item_txt, this.mLocalise.getLocalisedSurroundPresetName(context, names[index]) + this.mLocalise.getLocalisedSurroundDescription(context, names[index])));
            AccessibilityManager am = (AccessibilityManager) context.getSystemService("accessibility");
            if (am.isEnabled()) {
                AccessibilityEvent event = AccessibilityEvent.obtain(16384);
                this.mContainer.onInitializeAccessibilityEvent(event);
                event.getText().add(context.getResources().getString(R.string.sound_enhance_strings_accessibility_vpt_list_item_txt, this.mLocalise.getLocalisedSurroundPresetName(context, names[index]) + this.mLocalise.getLocalisedSurroundDescription(context, names[index])));
                am.sendAccessibilityEvent(event);
            }
            this.mTextView.setText(this.mLocalise.getLocalisedSurroundPresetName(getActivity(), names[index]));
            this.mSurroundDescription.setText(this.mLocalise.getLocalisedSurroundDescription(getActivity(), names[index]));
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
            new AlertDialog.Builder(activity).setIcon(android.R.drawable.ic_dialog_alert).setTitle(android.R.string.dialog_alert_title).setMessage(R.string.sound_enhance_strings_error_not_available_by_prio_txt).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.SurroundSelectorFragment.4
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

    private void finishActivityBySettings() {
        Activity activity;
        if (!SoundEnhancementUtility.canShowSoundEffectsScreen(this.mService) && (activity = getActivity()) != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateCheckIcon(int scale) {
        Animator animation = AnimatorInflater.loadAnimator(getActivity().getApplicationContext(), scale);
        animation.setTarget(this.mCheckIcon);
        animation.start();
    }
}

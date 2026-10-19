package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Switch;
import com.dolby.dax.DsParams;
import com.sonyericsson.soundenhancement.ahc.AnimationView;
import com.sonyericsson.soundenhancement.ahc.Logger;
import com.sonyericsson.soundenhancement.reporter.ReportContents;
import com.sonyericsson.soundenhancement.reporter.Reporter;
import com.sonyericsson.soundenhancement.switchbar.SwitchBar;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class AutomaticHeadphoneActivity extends BaseActivity {
    private AutomaticHeadphoneService mAhcService;
    private AnimationView mAnimationView;
    private SharedPreferences mSharedPreferences;
    private SwitchBar mSwitchBar;
    private ImageView mEarView = null;
    private ImageView mAnimView = null;
    private boolean mFeatureState = false;
    private Boolean mBound = false;
    private PermissionDialogFragment mPermissionDialog = null;
    private PermissionDialogFragment mContinueDialog = null;
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder binder) {
            AutomaticHeadphoneService.AutomaticHeadsetBinder ahcBinder = (AutomaticHeadphoneService.AutomaticHeadsetBinder) binder;
            AutomaticHeadphoneActivity.this.mAhcService = ahcBinder.getService();
            ahcBinder.setListener(new AutomaticHeadphoneService.BindServiceListener() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.4.1
                @Override // com.sonyericsson.soundenhancement.AutomaticHeadphoneService.BindServiceListener
                public void setFeatureStatus(boolean status) {
                    AutomaticHeadphoneActivity.this.setSwitchState(status);
                }
            });
            if (AutomaticHeadphoneActivity.this.mAhcService == null) {
                AutomaticHeadphoneActivity.this.setSwitchState(false);
            } else {
                AutomaticHeadphoneActivity automaticHeadphoneActivity = AutomaticHeadphoneActivity.this;
                automaticHeadphoneActivity.setSwitchState(!automaticHeadphoneActivity.mAhcService.isServiceBusy());
            }
            AutomaticHeadphoneActivity.this.mBound = true;
            Logger.LOGD("Connected to AHC service");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            AutomaticHeadphoneActivity.this.mAhcService = null;
            AutomaticHeadphoneActivity.this.mBound = false;
        }
    };
    private AnimationView.OnAnimationStateChange mAnimationChange = new AnimationView.OnAnimationStateChange() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.5
        @Override // com.sonyericsson.soundenhancement.ahc.AnimationView.OnAnimationStateChange
        public void onAnimationInactive() {
            AutomaticHeadphoneActivity.this.runOnUiThread(new Runnable() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.5.1
                @Override // java.lang.Runnable
                public void run() {
                    AutomaticHeadphoneActivity.this.mEarView.setImageDrawable(AutomaticHeadphoneActivity.this.getResources().getDrawable(R.drawable.ahc_inactive_background));
                }
            });
        }

        @Override // com.sonyericsson.soundenhancement.ahc.AnimationView.OnAnimationStateChange
        public void onAnimationActive() {
            AutomaticHeadphoneActivity.this.runOnUiThread(new Runnable() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.5.2
                @Override // java.lang.Runnable
                public void run() {
                    AutomaticHeadphoneActivity.this.mEarView.setImageDrawable(AutomaticHeadphoneActivity.this.getResources().getDrawable(R.drawable.ahc_active_background));
                }
            });
        }

        @Override // com.sonyericsson.soundenhancement.ahc.AnimationView.OnAnimationStateChange
        public void onAnimationChanged(final Drawable drawable) {
            AutomaticHeadphoneActivity.this.runOnUiThread(new Runnable() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.5.3
                @Override // java.lang.Runnable
                public void run() {
                    AutomaticHeadphoneActivity.this.mAnimView.setImageDrawable(drawable);
                }
            });
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(getIntent());
        intent.addFlags(DsParams.EFFECT_PARAM_PROFILE_PORT_PARAMETER);
        super.onCreate(savedInstanceState);
        if (!SoundEnhancementUtility.getSupportAutoHeadsetCompensation() && !isFinishing()) {
            finish();
            return;
        }
        setContentView(R.layout.ahc_main_default);
        this.mSharedPreferences = PreferenceUtil.getSharedPreferencesWithDeviceProtected(this, com.sonyericsson.soundenhancement.ahc.Constants.PREF_AHC, 4);
        this.mFeatureState = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.PREF_STATUS, false);
        this.mEarView = (ImageView) findViewById(R.id.earview);
        this.mEarView.setImageResource(R.drawable.ahc_inactive_background);
        this.mAnimView = (ImageView) findViewById(R.id.animview);
        this.mAnimationView = new AnimationView(getApplicationContext());
        this.mAnimationView.setOnAnimationStateChangeListener(this.mAnimationChange);
        this.mSwitchBar = (SwitchBar) findViewById(R.id.switch_bar);
        this.mSwitchBar.show();
        this.mSwitchBar.setEnabled(this.mFeatureState);
        this.mSwitchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.1
            @Override // com.sonyericsson.soundenhancement.switchbar.SwitchBar.OnSwitchChangeListener
            public void onSwitchChanged(Switch switchView, boolean isChecked) {
                boolean oldState = AutomaticHeadphoneActivity.this.mFeatureState;
                AutomaticHeadphoneActivity.this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.PREF_STATUS, isChecked).commit();
                AutomaticHeadphoneActivity.this.mFeatureState = isChecked;
                if (AutomaticHeadphoneActivity.this.mAhcService != null && oldState != isChecked) {
                    Reporter.reportEvent(AutomaticHeadphoneActivity.this.getApplicationContext(), ReportContents.EVENT_AUTO_OPTIMIZATION, isChecked);
                    AutomaticHeadphoneActivity.this.mAhcService.changeState(isChecked);
                }
                if (AutomaticHeadphoneActivity.this.mAhcService != null) {
                    if (AutomaticHeadphoneActivity.this.mAhcService.isImpedanceAnalyzing() && isChecked) {
                        AutomaticHeadphoneActivity.this.updateAnimationState(false);
                    } else {
                        AutomaticHeadphoneActivity automaticHeadphoneActivity = AutomaticHeadphoneActivity.this;
                        automaticHeadphoneActivity.updateAnimationState(automaticHeadphoneActivity.mFeatureState);
                    }
                }
            }
        });
        this.mSwitchBar.setOnBeforeCheckedChangeListener(new SwitchBar.OnBeforeCheckedChangeListener() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.2
            @Override // com.sonyericsson.soundenhancement.switchbar.SwitchBar.OnBeforeCheckedChangeListener
            public boolean onBeforeCheckedChanged(boolean isChecked) {
                return AutomaticHeadphoneActivity.this.checkPermission(isChecked);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkPermission(boolean isChecked) {
        String[] needPermissions = getAllDeniedPermissionsFromGrps();
        if (needPermissions != null && needPermissions.length > 0 && isChecked) {
            requestNeededPermissionsWithDialog(this, needPermissions);
            return true;
        }
        return false;
    }

    private void requestNeededPermissionsWithDialog(Activity activity, final String[] needPermissions) {
        final int requestCode;
        if (needPermissions != null && needPermissions.length > 0) {
            if (needPermissions.length == 1) {
                requestCode = 10;
            } else {
                requestCode = 20;
            }
            String message = PermissionUtil.getRationaleTextMessage(this, needPermissions);
            if (!TextUtils.isEmpty(message)) {
                showPermissionDialog(message, new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        AutomaticHeadphoneActivity.this.requestPermissions(needPermissions, requestCode);
                    }
                });
            } else {
                this.mSwitchBar.setCheckedInternal(false);
                requestPermissions(needPermissions, requestCode);
            }
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Logger.LOGD("onRequestPermissionsResult requestCode = " + Integer.toString(requestCode));
        if (requestCode == 10 || requestCode == 20) {
            Set<String> grantPermissionGrps = null;
            Set<String> deniedPermissionGrps = null;
            for (int i = 0; i < permissions.length; i++) {
                Logger.LOGD("onRequestPermissionsResult permissions:" + permissions[i] + " grantResults:" + Integer.toString(grantResults[i]));
                if (grantResults[i] == 0) {
                    Logger.LOGD(permissions[i] + " is Granted : " + grantResults[i]);
                    this.mSwitchBar.setCheckedInternal(true);
                    if (grantPermissionGrps == null) {
                        grantPermissionGrps = new HashSet<>();
                    }
                    grantPermissionGrps.add(PermissionUtil.getPermissionGroupName(this, permissions[i]));
                } else if (grantResults[i] == -1) {
                    Logger.LOGD(permissions[i] + " is Denied : " + grantResults[i]);
                    this.mSwitchBar.setCheckedInternal(false);
                    if (deniedPermissionGrps == null) {
                        deniedPermissionGrps = new HashSet<>();
                    }
                    deniedPermissionGrps.add(PermissionUtil.getPermissionGroupName(this, permissions[i]));
                }
            }
            if (deniedPermissionGrps != null && deniedPermissionGrps.size() > 0) {
                String rationaleMessage = PermissionUtil.createRationalemessage(this, deniedPermissionGrps);
                showContinueDialog(rationaleMessage);
                return;
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showPermissionDialog(String message, DialogInterface.OnClickListener okListener) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Bundle args = new Bundle();
        args.putString(PermissionDialogFragment.MESSAGE_KEY, message);
        args.putString(PermissionDialogFragment.MESSAGE_TITLE, getString(R.string.permission_dialog_title_permission_txt, new Object[]{getString(R.string.sound_enhance_strings_app_name_txt)}));
        args.putString(PermissionDialogFragment.MESSAGE_SUB_TITLE, getString(R.string.permission_dialog_message_1_txt, new Object[]{getString(R.string.sound_enhance_strings_app_name_txt)}));
        args.putInt(PermissionDialogFragment.POS_BUTTON_STRING_RES_KEY, android.R.string.ok);
        args.putInt(PermissionDialogFragment.NEG_BUTTON_STRING_RES_KEY, 0);
        args.putInt(PermissionDialogFragment.DIALOG_TYPE_KEY, 1);
        this.mPermissionDialog = new PermissionDialogFragment();
        this.mPermissionDialog.setArguments(args);
        this.mPermissionDialog.setPositiveListener(okListener);
        this.mPermissionDialog.show(getFragmentManager(), (String) null);
    }

    private void showContinueDialog(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Bundle args = new Bundle();
        args.putString(PermissionDialogFragment.MESSAGE_KEY, message);
        args.putString(PermissionDialogFragment.MESSAGE_TITLE, getString(R.string.permission_dialog_title_continue_txt));
        args.putString(PermissionDialogFragment.MESSAGE_SUB_TITLE, getString(R.string.permission_dialog_message_2_txt, new Object[]{getString(R.string.sound_enhance_strings_app_name_txt)}));
        args.putInt(PermissionDialogFragment.POS_BUTTON_STRING_RES_KEY, R.string.permission_dialog_button_go_to_app_info_txt);
        args.putInt(PermissionDialogFragment.NEG_BUTTON_STRING_RES_KEY, android.R.string.cancel);
        args.putInt(PermissionDialogFragment.DIALOG_TYPE_KEY, 2);
        this.mContinueDialog = new PermissionDialogFragment();
        this.mContinueDialog.setArguments(args);
        this.mContinueDialog.show(getFragmentManager(), (String) null);
    }

    private String[] getAllDeniedPermissionsFromGrps() {
        String[] permissionGrpLists = {"android.permission-group.MICROPHONE"};
        String[] needPermissions = PermissionUtil.getAllDeniedPermissionsFromGrps(this, permissionGrpLists);
        return needPermissions;
    }

    @Override // android.app.Activity
    protected void onResume() {
        boolean z;
        super.onResume();
        boolean isPermissionDenied = PermissionUtil.isPermissionDenied(this, "android.permission.RECORD_AUDIO");
        if (!isPermissionDenied) {
            z = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.AHC_STATE, false);
        } else {
            z = false;
        }
        this.mFeatureState = z;
        this.mSharedPreferences.edit().putBoolean(com.sonyericsson.soundenhancement.ahc.Constants.PREF_STATUS, this.mFeatureState).commit();
        this.mSwitchBar.setCheckedInternal(this.mFeatureState);
        this.mSwitchBar.setEnabled(this.mFeatureState);
        this.mAnimationView.startAnimation();
        AutomaticHeadphoneService automaticHeadphoneService = this.mAhcService;
        if (automaticHeadphoneService != null) {
            setSwitchState(!automaticHeadphoneService.isServiceBusy());
        } else {
            setSwitchState(false);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, (Class<?>) AutomaticHeadphoneService.class);
        bindService(intent, this.mConnection, 1);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mBound.booleanValue()) {
            unbindService(this.mConnection);
            this.mBound = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwitchState(boolean state) {
        if (this.mSwitchBar != null) {
            this.mFeatureState = this.mSharedPreferences.getBoolean(com.sonyericsson.soundenhancement.ahc.Constants.PREF_STATUS, false);
            if (state) {
                this.mSwitchBar.setEnabled(true);
                updateAnimationState(this.mFeatureState);
            } else {
                this.mSwitchBar.setEnabled(false);
                updateAnimationState(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAnimationState(boolean status) {
        AnimationView animationView = this.mAnimationView;
        if (animationView != null) {
            if (status) {
                animationView.runOnAnimation();
            } else {
                animationView.runOffAnimation();
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mAnimationView.stopAnimation();
        if (this.mBound.booleanValue()) {
            unbindService(this.mConnection);
            this.mBound = false;
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mAnimationView.stopAnimation();
    }
}

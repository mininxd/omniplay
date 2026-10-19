package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceDetectedDialogFragment extends DialogFragment {
    private static final String KEY_STATE_CHECKBOX = "key_state_checkbox";
    private static final String PREF_DEVICE_DETECTED_DO_NOT_SHOW_AGAIN = "device_detected_do_not_show_again";
    private static final String PREF_NAME = "SE-SETTINGS";
    private SharedPreferences mSp = null;

    public static DeviceDetectedDialogFragment newInstance() {
        DeviceDetectedDialogFragment fragment = new DeviceDetectedDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mSp = PreferenceUtil.getSharedPreferencesWithDeviceProtected(getActivity(), PREF_NAME, 0);
        if (this.mSp.getBoolean(PREF_DEVICE_DETECTED_DO_NOT_SHOW_AGAIN, false)) {
            getActivity().finish();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        CheckBox checkBox;
        super.onSaveInstanceState(outState);
        Dialog dialog = getDialog();
        if (dialog != null && (checkBox = (CheckBox) dialog.findViewById(R.id.chk_do_not_show_again)) != null) {
            outState.putBoolean(KEY_STATE_CHECKBOX, checkBox.isChecked());
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        boolean isChecked = false;
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_STATE_CHECKBOX)) {
            isChecked = savedInstanceState.getBoolean(KEY_STATE_CHECKBOX);
        }
        View layout = View.inflate(getActivity(), R.layout.device_detected_dialog, null);
        final CheckBox checkBox = (CheckBox) layout.findViewById(R.id.chk_do_not_show_again);
        checkBox.setChecked(isChecked);
        DialogInterface.OnClickListener lister = new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.DeviceDetectedDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean isChecked2 = checkBox.isChecked();
                SharedPreferences.Editor editor = DeviceDetectedDialogFragment.this.mSp.edit();
                editor.putBoolean(DeviceDetectedDialogFragment.PREF_DEVICE_DETECTED_DO_NOT_SHOW_AGAIN, isChecked2).apply();
            }
        };
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.sound_enhance_strings_audio_device_detect_title_txt).setMessage(R.string.sound_enhance_strings_audio_device_detect_desc_txt).setPositiveButton(R.string.sound_enhance_strings_button_got_it_txt, lister).setView(layout).create();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }
}

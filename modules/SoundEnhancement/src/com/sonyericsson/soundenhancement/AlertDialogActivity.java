package com.sonyericsson.soundenhancement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public class AlertDialogActivity extends Activity {
    private static final String DEVICE_DETECTED_TAG = "device_detected";
    public static final String DIALOG_TITLE = "Title";
    public static final String DIALOG_TYPE = "Type";
    private static final String LOG_TAG = "SoundEnhancement";
    public static final int TYPE_CALL_ERROR = 0;
    public static final int TYPE_DEVICE_DETECTED = 1;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        int type = i.getIntExtra(DIALOG_TYPE, -1);
        if (type == 0) {
            showCallErrorDialog(i);
        } else if (type == 1) {
            showDeviceDetectedDialog();
        } else {
            finish();
        }
    }

    private void showCallErrorDialog(Intent i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setMessage(R.string.sound_enhance_strings_error_not_available_by_prio_low_txt).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.AlertDialogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sonyericsson.soundenhancement.AlertDialogActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialog) {
                AlertDialogActivity.this.finish();
            }
        });
        int titleId = i.getIntExtra(DIALOG_TITLE, -1);
        if (titleId != -1) {
            builder.setTitle(titleId);
        }
        builder.create().show();
    }

    private void showDeviceDetectedDialog() {
        if (getFragmentManager().findFragmentByTag(DEVICE_DETECTED_TAG) == null) {
            DialogFragment fragment = DeviceDetectedDialogFragment.newInstance();
            fragment.show(getFragmentManager(), DEVICE_DETECTED_TAG);
        }
    }
}

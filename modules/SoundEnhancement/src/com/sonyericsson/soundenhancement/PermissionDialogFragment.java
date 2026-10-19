package com.sonyericsson.soundenhancement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionDialogFragment extends DialogFragment {
    public static final int CONTINUE_DIALOG = 2;
    public static final String DIALOG_TYPE_KEY = "type";
    public static final String MESSAGE_KEY = "message";
    public static final String MESSAGE_SUB_TITLE = "massage_sub_title";
    public static final String MESSAGE_TITLE = "message_title";
    public static final String NEG_BUTTON_STRING_RES_KEY = "neg_btn_str_res";
    public static final int PERMISSION_DIALOG = 1;
    public static final String POS_BUTTON_STRING_RES_KEY = "pos_btn_str_res";
    private DialogInterface.OnClickListener mPositiveListener = null;
    private DialogInterface.OnClickListener mNegativeListener = null;
    private DialogInterface.OnDismissListener mOnDismissListener = null;

    public DialogInterface.OnClickListener getPositiveListener() {
        return this.mPositiveListener;
    }

    public void setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.mPositiveListener = positiveListener;
    }

    public DialogInterface.OnClickListener getNegativeListener() {
        return this.mNegativeListener;
    }

    public void setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.mNegativeListener = negativeListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return this.mOnDismissListener;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
        this.mOnDismissListener = dismissListener;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!canCreateDialog()) {
            return null;
        }
        String message = getArguments().getString(MESSAGE_KEY);
        String message_title = getArguments().getString(MESSAGE_TITLE);
        String headerText = getArguments().getString(MESSAGE_SUB_TITLE);
        int positiveTextRes = getArguments().getInt(POS_BUTTON_STRING_RES_KEY);
        int negativeTextRes = getArguments().getInt(NEG_BUTTON_STRING_RES_KEY);
        int dialogType = getArguments().getInt(DIALOG_TYPE_KEY);
        ViewGroup view = null;
        if (dialogType != 0) {
            if (dialogType == 1) {
                view = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.permission_dialog_normal_body, (ViewGroup) null);
                if (view != null) {
                    TextView herderTextView = (TextView) view.findViewById(R.id.permission_dialog_header_txt);
                    TextView bodyTextView = (TextView) view.findViewById(R.id.permission_dialog_body_txt);
                    if (herderTextView != null && bodyTextView != null) {
                        herderTextView.setText(headerText);
                        bodyTextView.setText(message);
                    }
                }
            } else if (dialogType == 2 && (view = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.permission_dialog_body, (ViewGroup) null)) != null) {
                TextView herderTextView2 = (TextView) view.findViewById(R.id.permission_dialog_header_txt);
                TextView bodyTextView2 = (TextView) view.findViewById(R.id.permission_dialog_body_txt);
                TextView footerTextView = (TextView) view.findViewById(R.id.permission_dialog_footer_txt);
                String footerText = getActivity().getString(R.string.permission_dialog_message_go_to_settings_txt);
                if (herderTextView2 != null && bodyTextView2 != null && footerTextView != null) {
                    herderTextView2.setText(headerText);
                    bodyTextView2.setText(message);
                    footerTextView.setText(footerText);
                }
                if (this.mPositiveListener == null) {
                    this.mPositiveListener = new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.PermissionDialogFragment.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = PermissionUtil.createSettingAppInfoViewIntent(PermissionDialogFragment.this.getActivity().getPackageName());
                            PermissionDialogFragment.this.startActivity(intent);
                        }
                    };
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(message_title)) {
            builder.setTitle(message_title);
        }
        if (view != null) {
            builder.setView(view);
        }
        if (positiveTextRes != 0) {
            builder.setPositiveButton(positiveTextRes, this.mPositiveListener);
        }
        if (negativeTextRes != 0) {
            if (this.mNegativeListener == null) {
                this.mNegativeListener = new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.PermissionDialogFragment.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionDialogFragment.this.dismiss();
                    }
                };
            }
            builder.setNegativeButton(negativeTextRes, this.mNegativeListener);
        }
        return builder.create();
    }

    private boolean canCreateDialog() {
        if (getArguments() == null) {
            return false;
        }
        return true;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }
}

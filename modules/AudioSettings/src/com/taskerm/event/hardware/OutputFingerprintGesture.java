package com.joaomgcd.taskerm.event.hardware;

import android.annotation.TargetApi;
import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "fp")
@TargetApi(26)
public final class OutputFingerprintGesture {
    private final int code;
    private final String codeName;

    public OutputFingerprintGesture(Context context, int i) {
        int i2;
        k.b(context, "context");
        this.code = i;
        int i3 = this.code;
        if (i3 == 4) {
            i2 = R.string.word_up;
        } else if (i3 != 8) {
            switch (i3) {
                case 1:
                    i2 = R.string.pl_right;
                    break;
                case 2:
                    i2 = R.string.pl_left;
                    break;
                default:
                    i2 = R.string.word_unknown;
                    break;
            }
        } else {
            i2 = R.string.word_down;
        }
        this.codeName = aj.a(i2, context, new Object[0]);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.fingerprint_gesture_code_description, labelResId = R.string.pl_code, name = "code")
    public final int getCode() {
        return this.code;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.fingerprint_gesture_name_description, labelResId = R.string.pl_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getCodeName() {
        return this.codeName;
    }
}

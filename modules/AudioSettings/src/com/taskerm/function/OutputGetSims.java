package com.joaomgcd.taskerm.function;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "sim")
public final class OutputGetSims {
    private final String[] name;

    public OutputGetSims(String[] strArr) {
        k.b(strArr, ProfileManager.EXTRA_PROFILE_NAME);
        this.name = strArr;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_name, name = "names")
    public final String[] getName() {
        return this.name;
    }
}

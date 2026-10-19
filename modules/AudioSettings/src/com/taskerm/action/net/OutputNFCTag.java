package com.joaomgcd.taskerm.action.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nfc")
public final class OutputNFCTag {
    private final String id;
    private final String payload;
    private final Boolean present;
    private final String[] types;

    public OutputNFCTag() {
        this(null, null, null, null, 15, null);
    }

    public OutputNFCTag(String str, String str2, String[] strArr, Boolean bool) {
        this.id = str;
        this.payload = str2;
        this.types = strArr;
        this.present = bool;
    }

    public /* synthetic */ OutputNFCTag(String str, String str2, String[] strArr, Boolean bool, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String[]) null : strArr, (i & 8) != 0 ? (Boolean) null : bool);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.id_nfc_description, labelResId = R.string.word_id, name = net.dinglisch.android.taskerm.be.EXTRA_ID)
    public final String getId() {
        return this.id;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.payload_nfc_description, labelResId = R.string.payload, name = "payload")
    public final String getPayload() {
        return this.payload;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.types_description, labelResId = R.string.types, name = "types")
    public final String[] getTypes() {
        return this.types;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.present_nfc_description, labelResId = R.string.present_nfc, name = "present")
    public final Boolean getPresent() {
        return this.present;
    }
}

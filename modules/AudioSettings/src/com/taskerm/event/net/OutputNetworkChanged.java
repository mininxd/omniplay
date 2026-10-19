package com.joaomgcd.taskerm.event.net;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nc")
public final class OutputNetworkChanged {
    private final String[] capabilities;
    private final Boolean connected;
    private final String mobileType;
    private final String[] types;

    public OutputNetworkChanged(String[] strArr, String[] strArr2, String str, Boolean bool) {
        this.types = strArr;
        this.capabilities = strArr2;
        this.mobileType = str;
        this.connected = bool;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_changed_types_description, labelResId = R.string.network_changed_types, name = "types")
    public final String[] getTypes() {
        return this.types;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_changed_capabilities_description, labelResId = R.string.network_changed_capabilities, name = "capabilities")
    public final String[] getCapabilities() {
        return this.capabilities;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.network_changed_mobile_type_description, labelResId = R.string.network_changed_mobile_type, name = "mobile_type")
    public final String getMobileType() {
        return this.mobileType;
    }

    public final Boolean getConnected() {
        return this.connected;
    }
}

package com.joaomgcd.taskerm.function;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.alarmclock.ClockContract;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class OutputListStorageVolumes {
    private final Boolean canAccess;
    private final String description;
    private final Boolean external;
    private final String label;
    private final String path;
    private final String subsystem;
    private final String uuid;

    public OutputListStorageVolumes(String str, String str2, Boolean bool, String str3, String str4, String str5, Boolean bool2) {
        this.uuid = str;
        this.path = str2;
        this.canAccess = bool;
        this.label = str3;
        this.description = str4;
        this.subsystem = str5;
        this.external = bool2;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_id, name = "uuid")
    public final String getUuid() {
        return this.uuid;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_path, name = "path")
    public final String getPath() {
        return this.path;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.can_access_storage_volume_explained, labelResId = R.string.can_access, name = "can_access")
    public final Boolean getCanAccess() {
        return this.canAccess;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_label, name = ClockContract.AlarmSettingColumns.LABEL)
    public final String getLabel() {
        return this.label;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_description, name = "description")
    public final String getDescription() {
        return this.description;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.subsystem_storage_explained, labelResId = R.string.subsystem, name = "subsystem")
    public final String getSubsystem() {
        return this.subsystem;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.subsystem_storage_explained, labelResId = R.string.external_storage, name = "external")
    public final Boolean getExternal() {
        return this.external;
    }
}

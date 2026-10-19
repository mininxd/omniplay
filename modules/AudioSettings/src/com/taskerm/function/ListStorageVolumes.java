package com.joaomgcd.taskerm.function;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.storage.StorageVolume;
import c.f.b.k;
import c.q;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.dk;
import com.joaomgcd.taskerm.util.f;
import java.io.File;
import java.util.ArrayList;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.as;

/* JADX INFO: loaded from: classes.dex */
public final class ListStorageVolumes extends FunctionBase<InputListStorageVolumes, OutputListStorageVolumes[]> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.list_storage_volumes;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputListStorageVolumes> getInputClass() {
        return InputListStorageVolumes.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputListStorageVolumes[]> getOutputClass() {
        return OutputListStorageVolumes[].class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public String[] getPermissions(Context context, InputListStorageVolumes inputListStorageVolumes) {
        k.b(context, "context");
        k.b(inputListStorageVolumes, "input");
        return bt.f9101c.d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    @TargetApi(24)
    public OutputListStorageVolumes[] doIt(Context context, InputListStorageVolumes inputListStorageVolumes) {
        File fileB;
        k.b(context, "context");
        k.b(inputListStorageVolumes, "input");
        Boolean onlyExternal = inputListStorageVolumes.getOnlyExternal();
        boolean zBooleanValue = onlyExternal != null ? onlyExternal.booleanValue() : false;
        StorageVolume[] storageVolumeArrA = dk.a(context);
        ArrayList arrayList = new ArrayList();
        for (StorageVolume storageVolume : storageVolumeArrA) {
            boolean zIsRemovable = storageVolume.isRemovable();
            Object outputListStorageVolumes = null;
            if ((!zBooleanValue || zIsRemovable) && (fileB = dk.b(storageVolume)) != null) {
                String uuid = storageVolume.getUuid();
                String string = fileB.toString();
                k.a((Object) string, "path.toString()");
                outputListStorageVolumes = new OutputListStorageVolumes(uuid, string, Boolean.valueOf(as.a(context, fileB, false, true, true) ? as.a(context, fileB, true, true) : true), dk.a(storageVolume, context, string), f.f9323b.t() ? storageVolume.getDescription(context) : null, dk.a(storageVolume), Boolean.valueOf(zIsRemovable));
            }
            if (outputListStorageVolumes != null) {
                arrayList.add(outputListStorageVolumes);
            }
        }
        Object[] array = arrayList.toArray(new OutputListStorageVolumes[0]);
        if (array != null) {
            return (OutputListStorageVolumes[]) array;
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}

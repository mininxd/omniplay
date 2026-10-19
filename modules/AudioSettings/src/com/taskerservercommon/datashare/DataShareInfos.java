package com.joaomgcd.taskerservercommon.datashare;

import c.f.b.k;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareInfos extends ArrayList<DataShareInfoWithUrl> {
    public /* bridge */ boolean contains(DataShareInfoWithUrl dataShareInfoWithUrl) {
        return super.contains((Object) dataShareInfoWithUrl);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof DataShareInfoWithUrl) {
            return contains((DataShareInfoWithUrl) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(DataShareInfoWithUrl dataShareInfoWithUrl) {
        return super.indexOf((Object) dataShareInfoWithUrl);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof DataShareInfoWithUrl) {
            return indexOf((DataShareInfoWithUrl) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(DataShareInfoWithUrl dataShareInfoWithUrl) {
        return super.lastIndexOf((Object) dataShareInfoWithUrl);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof DataShareInfoWithUrl) {
            return lastIndexOf((DataShareInfoWithUrl) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final DataShareInfoWithUrl remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(DataShareInfoWithUrl dataShareInfoWithUrl) {
        return super.remove((Object) dataShareInfoWithUrl);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof DataShareInfoWithUrl) {
            return remove((DataShareInfoWithUrl) obj);
        }
        return false;
    }

    public DataShareInfoWithUrl removeAt(int i) {
        return (DataShareInfoWithUrl) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public DataShareInfos() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataShareInfos(List<DataShareInfoWithUrl> list) {
        super(list);
        k.b(list, "list");
    }
}

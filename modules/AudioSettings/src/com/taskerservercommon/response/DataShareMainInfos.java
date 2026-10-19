package com.joaomgcd.taskerservercommon.response;

import c.f.b.k;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DataShareMainInfos extends ArrayList<DataShareMainInfo> {
    public /* bridge */ boolean contains(DataShareMainInfo dataShareMainInfo) {
        return super.contains((Object) dataShareMainInfo);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof DataShareMainInfo) {
            return contains((DataShareMainInfo) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(DataShareMainInfo dataShareMainInfo) {
        return super.indexOf((Object) dataShareMainInfo);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof DataShareMainInfo) {
            return indexOf((DataShareMainInfo) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(DataShareMainInfo dataShareMainInfo) {
        return super.lastIndexOf((Object) dataShareMainInfo);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof DataShareMainInfo) {
            return lastIndexOf((DataShareMainInfo) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final DataShareMainInfo remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(DataShareMainInfo dataShareMainInfo) {
        return super.remove((Object) dataShareMainInfo);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof DataShareMainInfo) {
            return remove((DataShareMainInfo) obj);
        }
        return false;
    }

    public DataShareMainInfo removeAt(int i) {
        return (DataShareMainInfo) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public DataShareMainInfos() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataShareMainInfos(List<DataShareMainInfo> list) {
        super(list);
        k.b(list, "other");
    }
}

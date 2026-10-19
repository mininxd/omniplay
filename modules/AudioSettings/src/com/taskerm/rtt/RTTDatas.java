package com.joaomgcd.taskerm.rtt;

import android.net.wifi.rtt.RangingResult;
import androidx.annotation.Keep;
import c.a.j;
import c.f.b.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RTTDatas extends ArrayList<RTTData> {
    public /* bridge */ boolean contains(RTTData rTTData) {
        return super.contains((Object) rTTData);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof RTTData) {
            return contains((RTTData) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(RTTData rTTData) {
        return super.indexOf((Object) rTTData);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof RTTData) {
            return indexOf((RTTData) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(RTTData rTTData) {
        return super.lastIndexOf((Object) rTTData);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof RTTData) {
            return lastIndexOf((RTTData) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final RTTData remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(RTTData rTTData) {
        return super.remove((Object) rTTData);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof RTTData) {
            return remove((RTTData) obj);
        }
        return false;
    }

    public RTTData removeAt(int i) {
        return (RTTData) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public RTTDatas(List<RangingResult> list) {
        k.b(list, "rangingResults");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((RangingResult) obj).getStatus() == 0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(j.a((Iterable) arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(new RTTData((RangingResult) it.next()));
        }
        this((Collection<? extends RTTData>) j.f((Collection) arrayList3));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RTTDatas(Collection<? extends RTTData> collection) {
        super(collection);
        k.b(collection, "c");
    }

    public RTTDatas() {
    }

    public final List<String> getMacs() {
        ArrayList arrayList = new ArrayList(j.a((Iterable) this, 10));
        Iterator<RTTData> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getMac());
        }
        return arrayList;
    }
}

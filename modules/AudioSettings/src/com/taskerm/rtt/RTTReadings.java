package com.joaomgcd.taskerm.rtt;

import androidx.annotation.Keep;
import c.a.d;
import c.a.j;
import c.f.b.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RTTReadings extends ArrayList<RTTReading> {
    public /* bridge */ boolean contains(RTTReading rTTReading) {
        return super.contains((Object) rTTReading);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof RTTReading) {
            return contains((RTTReading) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(RTTReading rTTReading) {
        return super.indexOf((Object) rTTReading);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof RTTReading) {
            return indexOf((RTTReading) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(RTTReading rTTReading) {
        return super.lastIndexOf((Object) rTTReading);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof RTTReading) {
            return lastIndexOf((RTTReading) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final RTTReading remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(RTTReading rTTReading) {
        return super.remove((Object) rTTReading);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof RTTReading) {
            return remove((RTTReading) obj);
        }
        return false;
    }

    public RTTReading removeAt(int i) {
        return (RTTReading) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public RTTReadings(int i) {
        super(i);
    }

    public RTTReadings() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RTTReadings(Collection<? extends RTTReading> collection) {
        super(collection);
        k.b(collection, "c");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RTTReadings(RTTReading... rTTReadingArr) {
        this(d.l(rTTReadingArr));
        k.b(rTTReadingArr, "readings");
    }

    public final List<String> getMacs() {
        ArrayList arrayList = new ArrayList(j.a((Iterable) this, 10));
        Iterator<RTTReading> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getMacs());
        }
        return j.l(j.a((Iterable) arrayList));
    }
}

package com.joaomgcd.taskerm.contacts;

import androidx.annotation.Keep;
import c.f.b.k;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ContactDatas extends ArrayList<ContactData> {
    public /* bridge */ boolean contains(ContactData contactData) {
        return super.contains((Object) contactData);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ContactData) {
            return contains((ContactData) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(ContactData contactData) {
        return super.indexOf((Object) contactData);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ContactData) {
            return indexOf((ContactData) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ContactData contactData) {
        return super.lastIndexOf((Object) contactData);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ContactData) {
            return lastIndexOf((ContactData) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final ContactData remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(ContactData contactData) {
        return super.remove((Object) contactData);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof ContactData) {
            return remove((ContactData) obj);
        }
        return false;
    }

    public ContactData removeAt(int i) {
        return (ContactData) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public ContactDatas(int i) {
        super(i);
    }

    public ContactDatas() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContactDatas(List<ContactData> list) {
        super(list);
        k.b(list, "c");
    }
}

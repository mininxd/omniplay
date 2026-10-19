package com.joaomgcd.taskerm.action.input;

import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class StoredPowerMenuActions extends ArrayList<StoredPowerMenuAction> {
    public /* bridge */ boolean contains(StoredPowerMenuAction storedPowerMenuAction) {
        return super.contains((Object) storedPowerMenuAction);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof StoredPowerMenuAction) {
            return contains((StoredPowerMenuAction) obj);
        }
        return false;
    }

    public int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(StoredPowerMenuAction storedPowerMenuAction) {
        return super.indexOf((Object) storedPowerMenuAction);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof StoredPowerMenuAction) {
            return indexOf((StoredPowerMenuAction) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(StoredPowerMenuAction storedPowerMenuAction) {
        return super.lastIndexOf((Object) storedPowerMenuAction);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof StoredPowerMenuAction) {
            return lastIndexOf((StoredPowerMenuAction) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final StoredPowerMenuAction remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(StoredPowerMenuAction storedPowerMenuAction) {
        return super.remove((Object) storedPowerMenuAction);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof StoredPowerMenuAction) {
            return remove((StoredPowerMenuAction) obj);
        }
        return false;
    }

    public StoredPowerMenuAction removeAt(int i) {
        return (StoredPowerMenuAction) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return getSize();
    }

    public StoredPowerMenuActions(int i) {
        super(i);
    }

    public StoredPowerMenuActions() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StoredPowerMenuActions(Collection<StoredPowerMenuAction> collection) {
        super(collection);
        c.f.b.k.b(collection, "c");
    }

    public final StoredPowerMenuAction edit(StoredPowerMenuAction storedPowerMenuAction) {
        c.f.b.k.b(storedPowerMenuAction, "storedPowerMenuAction");
        StoredPowerMenuAction storedPowerMenuActionById = byId(storedPowerMenuAction.getId());
        if (storedPowerMenuActionById != null) {
            remove((Object) storedPowerMenuActionById);
            storedPowerMenuActionById.changeState(storedPowerMenuAction);
            storedPowerMenuAction = storedPowerMenuActionById;
        }
        add(storedPowerMenuAction);
        com.joaomgcd.taskerm.rx.b.c(storedPowerMenuAction);
        return storedPowerMenuAction;
    }

    public final StoredPowerMenuAction byId(String str) {
        StoredPowerMenuAction next;
        c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
        Iterator<StoredPowerMenuAction> it = iterator();
        while (it.hasNext()) {
            next = it.next();
            if (c.f.b.k.a((Object) next.getId(), (Object) str)) {
                return next;
            }
        }
        next = null;
        return next;
    }

    public final List<StoredPowerMenuAction> byIds(Collection<String> collection) {
        c.f.b.k.b(collection, "ids");
        ArrayList arrayList = new ArrayList();
        for (StoredPowerMenuAction storedPowerMenuAction : this) {
            if (collection.contains(storedPowerMenuAction.getId())) {
                arrayList.add(storedPowerMenuAction);
            }
        }
        return arrayList;
    }
}

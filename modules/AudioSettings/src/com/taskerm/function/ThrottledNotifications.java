package com.joaomgcd.taskerm.function;

import android.content.Intent;
import android.util.SparseArray;
import b.a.d.f;
import b.a.k.b;
import c.f.b.g;
import c.f.b.k;
import c.s;
import com.google.android.gms.common.annotation.KeepName;
import com.joaomgcd.taskerm.rx.i;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
@KeepName
public final class ThrottledNotifications extends HashMap<String, Integer> {
    public static final Companion Companion = new Companion(null);
    private static final SparseArray<b<Intent>> publishers = new SparseArray<>();

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    public /* bridge */ boolean containsKey(String str) {
        return super.containsKey((Object) str);
    }

    public /* bridge */ boolean containsValue(Integer num) {
        return super.containsValue((Object) num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof Integer) {
            return containsValue((Integer) obj);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<String, Integer>> entrySet() {
        return getEntries();
    }

    public /* bridge */ Integer get(String str) {
        return (Integer) super.get((Object) str);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public Set getEntries() {
        return super.entrySet();
    }

    public Set getKeys() {
        return super.keySet();
    }

    public /* bridge */ Integer getOrDefault(String str, Integer num) {
        return (Integer) super.getOrDefault((Object) str, num);
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return obj instanceof String ? getOrDefault((String) obj, (Integer) obj2) : obj2;
    }

    public int getSize() {
        return super.size();
    }

    public Collection getValues() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<String> keySet() {
        return getKeys();
    }

    public /* bridge */ Integer remove(String str) {
        return (Integer) super.remove((Object) str);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof Integer)) {
            return remove((String) obj, (Integer) obj2);
        }
        return false;
    }

    public /* bridge */ boolean remove(String str, Integer num) {
        return super.remove((Object) str, (Object) num);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final int size() {
        return getSize();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Collection<Integer> values() {
        return getValues();
    }

    public static final class Companion {
        private static /* synthetic */ void publishers$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        public final synchronized b<Intent> getPublisher(final int i, final c.f.a.b<? super Intent, s> bVar) {
            k.b(bVar, "handle");
            b<Intent> bVar2 = (b) ThrottledNotifications.publishers.get(i);
            if (bVar2 != null) {
                return bVar2;
            }
            b<Intent> bVarJ = b.j();
            k.a((Object) bVarJ, "it");
            i.b(bVarJ, i, TimeUnit.SECONDS, ThrottledNotifications$Companion$getPublisher$1$1.INSTANCE).d(new f<Intent>() { // from class: com.joaomgcd.taskerm.function.ThrottledNotifications$Companion$getPublisher$$inlined$also$lambda$1
                @Override // b.a.d.f
                public final void accept(Intent intent) {
                    k.b(intent, "intent");
                    bn.b("ThrottledNotifications", "Sending throttled notification for " + intent.getStringExtra("pkg"));
                    bVar.invoke(intent);
                }
            });
            ThrottledNotifications.publishers.put(i, bVarJ);
            k.a((Object) bVarJ, "PublishSubject.create<In…ishers.put(seconds, it) }");
            return bVarJ;
        }
    }
}

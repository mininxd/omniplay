package com.joaomgcd.taskerm.google.drive;

import com.joaomgcd.taskerm.google.drive.io.DriveMetadatasV3;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class y extends HashMap<String, DriveMetadatasV3> {
    public DriveMetadatasV3 a(String str, DriveMetadatasV3 driveMetadatasV3) {
        return (DriveMetadatasV3) super.getOrDefault(str, driveMetadatasV3);
    }

    public Set a() {
        return super.entrySet();
    }

    public boolean a(DriveMetadatasV3 driveMetadatasV3) {
        return super.containsValue(driveMetadatasV3);
    }

    public boolean a(String str) {
        return super.containsKey(str);
    }

    public DriveMetadatasV3 b(String str) {
        return (DriveMetadatasV3) super.get(str);
    }

    public Set b() {
        return super.keySet();
    }

    public boolean b(String str, DriveMetadatasV3 driveMetadatasV3) {
        return super.remove(str, driveMetadatasV3);
    }

    public DriveMetadatasV3 c(String str) {
        return (DriveMetadatasV3) super.remove(str);
    }

    public Collection c() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return a((String) obj);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        if (obj instanceof DriveMetadatasV3) {
            return a((DriveMetadatasV3) obj);
        }
        return false;
    }

    public int d() {
        return super.size();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<String, DriveMetadatasV3>> entrySet() {
        return a();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj instanceof String) {
            return b((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        return obj instanceof String ? a((String) obj, (DriveMetadatasV3) obj2) : obj2;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<String> keySet() {
        return b();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (obj instanceof String) {
            return c((String) obj);
        }
        return null;
    }

    @Override // java.util.HashMap, java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof DriveMetadatasV3)) {
            return b((String) obj, (DriveMetadatasV3) obj2);
        }
        return false;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final int size() {
        return d();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Collection<DriveMetadatasV3> values() {
        return c();
    }
}

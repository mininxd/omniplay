package com.joaomgcd.taskerm.securesettings;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class Setting {
    private final String name;
    private final String value;

    public static /* synthetic */ Setting copy$default(Setting setting, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = setting.name;
        }
        if ((i & 2) != 0) {
            str2 = setting.value;
        }
        return setting.copy(str, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final String component2() {
        return this.value;
    }

    public final Setting copy(String str, String str2) {
        return new Setting(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Setting)) {
            return false;
        }
        Setting setting = (Setting) obj;
        return c.f.b.k.a((Object) this.name, (Object) setting.name) && c.f.b.k.a((Object) this.value, (Object) setting.value);
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.value;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "Setting(name=" + this.name + ", value=" + this.value + ")";
    }

    public Setting(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }
}

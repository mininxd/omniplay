package com.joaomgcd.taskerm.a;

import c.f.b.k;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.m;

/* JADX INFO: loaded from: classes.dex */
public enum a {
    Back(1, 16, "back button", new m(245, R.string.an_back_button, 55, 4, null, new Object[0])),
    LongPower(6, 21, "long power", new m(246, R.string.an_long_power, 55, 4, null, new Object[0])),
    ShowRecents(3, 16, "show recents", new m(247, R.string.an_show_recents, 15, 4, null, new Object[0])),
    ToggleAppDock(7, 24, "toggle app dock", new m(244, R.string.an_toggle_app_dock, 15, 4, "toggle_app_dock", new Object[0])),
    QuickSettings(5, 17, "quick settings", new m(219, R.string.an_quick_settings, 30, 4, null, new Object[0])),
    Screenshot(9, 28, "screenshot", new m(249, R.string.an_accessibility_take_screenshot, 55, 4, null, new Object[0]));

    private final int h;
    private final int i;
    private final String j;
    private final m k;

    a(int i, int i2, String str, m mVar) {
        k.b(str, "description");
        k.b(mVar, "actionSpec");
        this.h = i;
        this.i = i2;
        this.j = str;
        this.k = mVar;
    }

    public final int a() {
        return this.h;
    }

    public final String b() {
        return this.j;
    }

    public final m c() {
        return this.k;
    }

    public final boolean a(int i) {
        return i >= this.i;
    }
}

package com.joaomgcd.a;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import c.f.b.g;
import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0088a f4886a = new C0088a(null);

    public static final void a(Menu menu, SubMenu subMenu, SubMenu subMenu2) {
        f4886a.a(menu, subMenu, subMenu2);
    }

    public static final boolean a(Context context, MenuItem menuItem) {
        return f4886a.a(context, menuItem);
    }

    /* JADX INFO: renamed from: com.joaomgcd.a.a$a, reason: collision with other inner class name */
    public static final class C0088a {
        public final void a(Menu menu, SubMenu subMenu, SubMenu subMenu2) {
            k.b(menu, "menu");
            k.b(subMenu, "moreMenu");
            k.b(subMenu2, "monitoringMenu");
        }

        public final boolean a(Context context, MenuItem menuItem) {
            k.b(context, "context");
            k.b(menuItem, "item");
            return false;
        }

        private C0088a() {
        }

        public /* synthetic */ C0088a(g gVar) {
            this();
        }
    }
}

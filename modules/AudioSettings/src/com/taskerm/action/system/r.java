package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import com.joaomgcd.taskerm.util.bt;
import com.joaomgcd.taskerm.util.bu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.bp;

/* JADX INFO: loaded from: classes.dex */
public final class r extends com.joaomgcd.taskerm.helper.actions.c<am> {
    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return i == 0;
    }

    private static final class a implements com.joaomgcd.taskerm.helper.x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f5829a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f5830b;

        public a(Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, be.EXTRA_ID);
            this.f5829a = context;
            this.f5830b = str;
        }

        @Override // com.joaomgcd.taskerm.helper.x
        public String a() {
            return this.f5830b;
        }

        public String toString() {
            return bu.a(a(), this.f5829a) + " (" + c.l.n.a(a(), (CharSequence) "android.permission.") + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(ActionEdit actionEdit, b bVar) {
        super(actionEdit, bVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(bVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (am) obj);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, am amVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(amVar, "input");
        String[] strArr = bp.f11897e;
        c.f.b.k.a((Object) strArr, "ManifestPermissions.KNOWN_PERMS");
        List listL = c.a.d.l(strArr);
        c.a.j.a(listL, bt.f9101c.b());
        List<String> list = listL;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        for (String str : list) {
            ActionEdit actionEditU = u();
            c.f.b.k.a((Object) str, "it");
            arrayList.add(new a(actionEditU, str));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (hashSet.add(((a) obj).toString())) {
                arrayList2.add(obj);
            }
        }
        com.joaomgcd.taskerm.helper.l.a(this, arrayList2, R.string.dt_permission, bVar.a(), iVar, amVar.getPermissionsString(), c.b(), false, null, null, 224, null);
    }
}

package com.joaomgcd.taskerm.tether;

import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.cn;
import java.util.ArrayList;
import java.util.Iterator;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
final class af extends ArrayList<ac> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.b<String, c.s> f8746a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public af(c.f.a.b<? super String, c.s> bVar, ac... acVarArr) {
        super(c.a.d.l(acVarArr));
        c.f.b.k.b(bVar, "debugger");
        c.f.b.k.b(acVarArr, "doers");
        this.f8746a = bVar;
    }

    public boolean a(ac acVar) {
        return super.contains(acVar);
    }

    public int b() {
        return super.size();
    }

    public int b(ac acVar) {
        return super.indexOf(acVar);
    }

    public int c(ac acVar) {
        return super.lastIndexOf(acVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof ac) {
            return a((ac) obj);
        }
        return false;
    }

    public boolean d(ac acVar) {
        return super.remove(acVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof ac) {
            return b((ac) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof ac) {
            return c((ac) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof ac) {
            return d((ac) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return b();
    }

    public final cl a() {
        Object next;
        for (ac acVar : this) {
            try {
                return acVar.b();
            } catch (Throwable th) {
                this.f8746a.invoke("Couldn't do with " + acVar.getClass() + ": " + th.getMessage());
                Iterator<T> it = com.joaomgcd.taskerm.util.ai.b(th).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!(((Throwable) next) instanceof SecurityException));
                Throwable th2 = (Throwable) next;
                if (th2 != null) {
                    if (c.l.n.b((CharSequence) th2.toString(), (CharSequence) "TETHER_PRIVILEGED", false, 2, (Object) null)) {
                        return cn.a("Unfortunately your device is blocking you from using this function.\n\nThe only way to use it is if you have a rooted device and by making Tasker a system app: \n\n" + aj.a(R.string.hint_make_tasker_system_app, acVar.a(), new Object[0]));
                    }
                    String string = th2.toString();
                    if (string == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase = string.toLowerCase();
                    c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                    if (c.l.n.b((CharSequence) lowerCase, (CharSequence) "service unavailable", false, 2, (Object) null)) {
                        return cn.a("Unfortunately your device is blocking you from using this function.\n\nIt seems that on this specific device it's impossible for third party apps to toggle the Wifi Hotspot. Sorry!");
                    }
                }
            }
        }
        return new cm("Device doesn't support Wifi Tether");
    }
}

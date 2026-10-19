package com.joaomgcd.taskerm.securesettings;

import android.content.ContentResolver;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8555a;

    public abstract b.a.l<String> a(String str);

    public abstract b.a.l<Boolean> b(String str);

    protected abstract b.a.l<Boolean> b(String str, String str2);

    public abstract p b();

    public t(Context context) {
        c.f.b.k.b(context, "context");
        this.f8555a = context;
    }

    public final Context d() {
        return this.f8555a;
    }

    protected final ContentResolver a() {
        ContentResolver contentResolver = this.f8555a.getContentResolver();
        c.f.b.k.a((Object) contentResolver, "context.contentResolver");
        return contentResolver;
    }

    public final b.a.l<Boolean> a(String str, String str2) {
        c.f.b.k.b(str, "key");
        c.f.b.k.b(str2, "value");
        if (c.f.b.k.a((Object) str2, (Object) aa.f8460b)) {
            b.a.l lVarA = a(str).a(new b(str));
            c.f.b.k.a((Object) lVarA, "get(key).flatMap { curre… realValue)\n            }");
            return lVarA;
        }
        return b(str, str2);
    }

    static final class b<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f8558b;

        b(String str) {
            this.f8558b = str;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<Boolean> apply(String str) {
            String str2;
            c.f.b.k.b(str, "current");
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        str2 = "1";
                        return t.this.b(this.f8558b, str2);
                    }
                    break;
                case 49:
                    if (str.equals("1")) {
                        str2 = "0";
                        return t.this.b(this.f8558b, str2);
                    }
                    break;
            }
            return b.a.l.b(new c("This setting can't be toggled"));
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<ai> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ai invoke() {
            String value;
            List<Setting> listA = t.this.b().a(t.this.d(), "list");
            ArrayList arrayList = new ArrayList();
            for (Setting setting : listA) {
                String name = setting.getName();
                r rVar = null;
                if (name != null && (value = setting.getValue()) != null) {
                    rVar = new r(t.this.b(), name, value, false, 0, 0, 48, null);
                }
                if (rVar != null) {
                    arrayList.add(rVar);
                }
            }
            ai aiVar = new ai();
            aiVar.addAll(arrayList);
            return aiVar;
        }
    }

    public final b.a.l<ai> c() {
        return com.joaomgcd.taskerm.rx.i.b(new a());
    }
}

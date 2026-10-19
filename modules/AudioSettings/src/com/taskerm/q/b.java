package com.joaomgcd.taskerm.q;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import c.f.a.m;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.s;
import com.joaomgcd.taskerm.util.aj;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.dinglisch.android.taskerm.bg;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final SharedPreferences a(Context context) {
        k.b(context, "receiver$0");
        return gr.h(context);
    }

    public static final SharedPreferences b(Context context) {
        k.b(context, "receiver$0");
        return context.getSharedPreferences(bg.j, 0);
    }

    public static final SharedPreferences c(Context context) {
        k.b(context, "receiver$0");
        return gr.g(context);
    }

    public static final SharedPreferences d(Context context) {
        k.b(context, "receiver$0");
        return context.getSharedPreferences(bg.g, 0);
    }

    public static final void a(SharedPreferences sharedPreferences, c.f.a.b<? super SharedPreferences.Editor, s> bVar) {
        k.b(sharedPreferences, "receiver$0");
        k.b(bVar, "block");
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        k.a((Object) editorEdit, "this");
        bVar.invoke(editorEdit);
        editorEdit.commit();
    }

    public static final SharedPreferences e(Context context) {
        k.b(context, "receiver$0");
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static final <T> void a(SharedPreferences sharedPreferences, String str, T t, m<? super SharedPreferences.Editor, ? super T, ? extends SharedPreferences.Editor> mVar) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (t == null) {
            editorEdit.remove(str);
        } else {
            k.a((Object) editorEdit, "editor");
            mVar.invoke(editorEdit, t);
        }
        editorEdit.commit();
    }

    public static final void a(Context context, String str, String str2, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        a(sharedPreferences, str, str2);
    }

    public static final void a(Context context, String str, Boolean bool, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        a(sharedPreferences, str, bool);
    }

    public static /* synthetic */ void a(Context context, String str, Boolean bool, SharedPreferences sharedPreferences, int i, Object obj) {
        if ((i & 4) != 0) {
            sharedPreferences = e(context);
            k.a((Object) sharedPreferences, "preferences");
        }
        a(context, str, bool, sharedPreferences);
    }

    public static final void a(Context context, String str, Integer num, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        a(sharedPreferences, str, num);
    }

    public static /* synthetic */ void a(Context context, String str, Integer num, SharedPreferences sharedPreferences, int i, Object obj) {
        if ((i & 4) != 0) {
            sharedPreferences = e(context);
            k.a((Object) sharedPreferences, "preferences");
        }
        a(context, str, num, sharedPreferences);
    }

    public static final void a(Context context, String str, Long l, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        a(sharedPreferences, str, l);
    }

    public static final void a(Context context, String str, Object obj, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        a(sharedPreferences, str, obj);
    }

    public static /* synthetic */ void a(Context context, String str, Object obj, SharedPreferences sharedPreferences, int i, Object obj2) {
        if ((i & 4) != 0) {
            sharedPreferences = e(context);
            k.a((Object) sharedPreferences, "preferences");
        }
        a(context, str, obj, sharedPreferences);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.q.b$b, reason: collision with other inner class name */
    static final class C0224b extends l implements m<SharedPreferences.Editor, String, SharedPreferences.Editor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8342a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0224b(String str) {
            super(2);
            this.f8342a = str;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final SharedPreferences.Editor invoke(SharedPreferences.Editor editor, String str) {
            k.b(editor, "receiver$0");
            k.b(str, "it");
            SharedPreferences.Editor editorPutString = editor.putString(this.f8342a, str);
            k.a((Object) editorPutString, "putString(key, it)");
            return editorPutString;
        }
    }

    static final class c extends l implements m<SharedPreferences.Editor, Boolean, SharedPreferences.Editor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8343a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str) {
            super(2);
            this.f8343a = str;
        }

        @Override // c.f.a.m
        public /* synthetic */ SharedPreferences.Editor invoke(SharedPreferences.Editor editor, Boolean bool) {
            return a(editor, bool.booleanValue());
        }

        public final SharedPreferences.Editor a(SharedPreferences.Editor editor, boolean z) {
            k.b(editor, "receiver$0");
            SharedPreferences.Editor editorPutBoolean = editor.putBoolean(this.f8343a, z);
            k.a((Object) editorPutBoolean, "putBoolean(key, it)");
            return editorPutBoolean;
        }
    }

    public static final void a(SharedPreferences sharedPreferences, String str, String str2) {
        k.b(sharedPreferences, "receiver$0");
        k.b(str, "key");
        a(sharedPreferences, str, str2, new C0224b(str));
    }

    public static final void a(SharedPreferences sharedPreferences, String str, Boolean bool) {
        k.b(sharedPreferences, "receiver$0");
        k.b(str, "key");
        a(sharedPreferences, str, bool, new c(str));
    }

    static final class d extends l implements m<SharedPreferences.Editor, Integer, SharedPreferences.Editor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8344a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str) {
            super(2);
            this.f8344a = str;
        }

        @Override // c.f.a.m
        public /* synthetic */ SharedPreferences.Editor invoke(SharedPreferences.Editor editor, Integer num) {
            return a(editor, num.intValue());
        }

        public final SharedPreferences.Editor a(SharedPreferences.Editor editor, int i) {
            k.b(editor, "receiver$0");
            SharedPreferences.Editor editorPutInt = editor.putInt(this.f8344a, i);
            k.a((Object) editorPutInt, "putInt(key, it)");
            return editorPutInt;
        }
    }

    static final class e extends l implements m<SharedPreferences.Editor, Long, SharedPreferences.Editor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8345a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str) {
            super(2);
            this.f8345a = str;
        }

        @Override // c.f.a.m
        public /* synthetic */ SharedPreferences.Editor invoke(SharedPreferences.Editor editor, Long l) {
            return a(editor, l.longValue());
        }

        public final SharedPreferences.Editor a(SharedPreferences.Editor editor, long j) {
            k.b(editor, "receiver$0");
            SharedPreferences.Editor editorPutLong = editor.putLong(this.f8345a, j);
            k.a((Object) editorPutLong, "putLong(key, it)");
            return editorPutLong;
        }
    }

    public static final void a(SharedPreferences sharedPreferences, String str, Integer num) {
        k.b(sharedPreferences, "receiver$0");
        k.b(str, "key");
        a(sharedPreferences, str, num, new d(str));
    }

    public static final void a(SharedPreferences sharedPreferences, String str, Long l) {
        k.b(sharedPreferences, "receiver$0");
        k.b(str, "key");
        a(sharedPreferences, str, l, new e(str));
    }

    static final class f extends l implements m<SharedPreferences.Editor, Object, SharedPreferences.Editor> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8346a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str) {
            super(2);
            this.f8346a = str;
        }

        @Override // c.f.a.m
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final SharedPreferences.Editor invoke(SharedPreferences.Editor editor, Object obj) {
            k.b(editor, "receiver$0");
            k.b(obj, "it");
            editor.remove(this.f8346a);
            SharedPreferences.Editor editorPutString = editor.putString(this.f8346a, aj.a(obj));
            k.a((Object) editorPutString, "putString(key, it.json)");
            return editorPutString;
        }
    }

    public static final void a(SharedPreferences sharedPreferences, String str, Object obj) {
        k.b(sharedPreferences, "receiver$0");
        k.b(str, "key");
        a(sharedPreferences, str, obj, new f(str));
    }

    public static final String b(Context context, String str, String str2, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        return sharedPreferences.getString(str, str2);
    }

    public static final boolean a(Context context, String str, boolean z, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        return sharedPreferences.getBoolean(str, z);
    }

    public static /* synthetic */ boolean a(Context context, String str, boolean z, SharedPreferences sharedPreferences, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            sharedPreferences = e(context);
            k.a((Object) sharedPreferences, "preferences");
        }
        return a(context, str, z, sharedPreferences);
    }

    public static final int a(Context context, String str, int i, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        return sharedPreferences.getInt(str, i);
    }

    public static /* synthetic */ int a(Context context, String str, int i, SharedPreferences sharedPreferences, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            sharedPreferences = e(context);
            k.a((Object) sharedPreferences, "preferences");
        }
        return a(context, str, i, sharedPreferences);
    }

    public static final long a(Context context, String str, long j, SharedPreferences sharedPreferences) {
        k.b(context, "receiver$0");
        k.b(str, "key");
        k.b(sharedPreferences, "state");
        return sharedPreferences.getLong(str, j);
    }

    public static final boolean a(Context context, com.joaomgcd.taskerm.util.h hVar) {
        k.b(context, "receiver$0");
        k.b(hVar, "key");
        String strA = hVar.a();
        SharedPreferences sharedPreferencesA = a(context);
        k.a((Object) sharedPreferencesA, "preferencesState");
        return a(context, strA, false, sharedPreferencesA);
    }

    public static final <T> b.a.l<T> a(Context context, com.joaomgcd.taskerm.util.h hVar, c.f.a.a<? extends b.a.l<T>> aVar, c.f.a.b<? super T, Boolean> bVar, c.f.a.a<? extends b.a.l<T>> aVar2) {
        k.b(context, "receiver$0");
        k.b(hVar, "tipKey");
        k.b(aVar, "getterIfDone");
        k.b(bVar, "isDone");
        k.b(aVar2, "block");
        String strA = hVar.a();
        if (a(context, hVar)) {
            return aVar.invoke();
        }
        b.a.l<T> lVarB = aVar2.invoke().b(new a(context, bVar, strA));
        k.a((Object) lVarB, "block().doOnSuccess {\n  …, preferencesState)\n    }");
        return lVarB;
    }

    static final class a<T> implements b.a.d.f<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f8339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f8340b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f8341c;

        a(Context context, c.f.a.b bVar, String str) {
            this.f8339a = context;
            this.f8340b = bVar;
            this.f8341c = str;
        }

        @Override // b.a.d.f
        public final void accept(T t) {
            if (((Boolean) this.f8340b.invoke(t)).booleanValue()) {
                Context context = this.f8339a;
                String str = this.f8341c;
                SharedPreferences sharedPreferencesA = b.a(this.f8339a);
                k.a((Object) sharedPreferencesA, "preferencesState");
                b.a(context, str, (Boolean) true, sharedPreferencesA);
            }
        }
    }

    public static final void b(Context context, com.joaomgcd.taskerm.util.h hVar) {
        k.b(context, "receiver$0");
        k.b(hVar, "tipKey");
        String strA = hVar.a();
        SharedPreferences sharedPreferencesA = a(context);
        k.a((Object) sharedPreferencesA, "preferencesState");
        a(context, strA, (Boolean) true, sharedPreferencesA);
    }

    public static final int a(Context context, String str) {
        k.b(context, "receiver$0");
        k.b(str, "prefix");
        SharedPreferences sharedPreferencesA = a(context);
        k.a((Object) sharedPreferencesA, "preferencesState");
        Map<String, ?> all = sharedPreferencesA.getAll();
        k.a((Object) all, "preferencesState.all");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            k.a((Object) key, "it.key");
            if (n.a(key, str, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        SharedPreferences.Editor editorEdit = a(context).edit();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            editorEdit.remove((String) ((Map.Entry) it.next()).getKey());
        }
        editorEdit.apply();
        return linkedHashMap.size();
    }
}

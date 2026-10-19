package com.joaomgcd.taskerm.util;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.fz;
import net.dinglisch.android.taskerm.gr;
import net.dinglisch.android.taskerm.gw;

/* JADX INFO: loaded from: classes.dex */
public final class ak {
    public static final Locale a(String str) {
        if (str == null) {
            return null;
        }
        String str2 = str;
        if (!c.l.n.b((CharSequence) str2, (CharSequence) "-", false, 2, (Object) null)) {
            return new Locale(str);
        }
        List<String> listB = c.l.n.b((CharSequence) str2, new String[]{"-"}, false, 2);
        return listB.size() == 1 ? new Locale(str) : new Locale(listB.get(0), listB.get(1));
    }

    public static final String b(String str) {
        if (str == null) {
            return null;
        }
        return c.l.n.a(str, (CharSequence) "file://");
    }

    public static final String c(String str) {
        String strB = b(str);
        if (strB != null) {
            return m(strB);
        }
        return null;
    }

    public static final boolean d(String str) {
        if (str == null) {
            return false;
        }
        return c.l.n.a(str, "file://", false, 2, (Object) null);
    }

    public static final String e(String str) {
        c.f.b.k.b(str, "receiver$0");
        Locale locale = Locale.getDefault();
        c.f.b.k.a((Object) locale, "Locale.getDefault()");
        return a(str, locale);
    }

    public static final String f(String str) {
        c.f.b.k.b(str, "receiver$0");
        Locale locale = Locale.ROOT;
        c.f.b.k.a((Object) locale, "Locale.ROOT");
        return a(str, locale);
    }

    public static final String g(String str) {
        c.f.b.k.b(str, "receiver$0");
        Locale locale = Locale.ROOT;
        c.f.b.k.a((Object) locale, "Locale.ROOT");
        String lowerCase = str.toLowerCase(locale);
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    public static final String a(String str, Locale locale) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(locale, "locale");
        if (str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(0, 1);
        c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new c.q("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = strSubstring.toUpperCase(locale);
        c.f.b.k.a((Object) upperCase, "(this as java.lang.String).toUpperCase(locale)");
        sb.append(upperCase);
        String strSubstring2 = str.substring(1);
        c.f.b.k.a((Object) strSubstring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(strSubstring2);
        return sb.toString();
    }

    public static final String[] h(String str) {
        c.f.b.k.b(str, "receiver$0");
        Object[] array = new c.l.k("(?<=[a-z])(?=[A-Z])").c(e(c.l.n.a(str, "_", " ", false, 4, (Object) null)), 0).toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final j i(String str) {
        c.f.b.k.b(str, "receiver$0");
        return new j(str);
    }

    public static final String j(String str) {
        c.f.b.k.b(str, "receiver$0");
        return c.a.d.a(h(str), " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8963a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f8964b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Context context) {
            super(0);
            this.f8963a = str;
            this.f8964b = context;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            gr.e(this.f8964b, this.f8963a, new Object[0]);
        }
    }

    public static final c.k<String, String> a(String str, int i) {
        c.f.b.k.b(str, "receiver$0");
        String strSubstring = str.substring(0, i);
        c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String strSubstring2 = str.substring(i);
        c.f.b.k.a((Object) strSubstring2, "(this as java.lang.String).substring(startIndex)");
        return new c.k<>(strSubstring, strSubstring2);
    }

    public static final void a(String str, Context context) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(context, "context");
        com.joaomgcd.taskerm.rx.i.e(new b(str, context));
    }

    public static final String a(String str, String str2) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "string");
        return c.l.n.a(str, str2, "", false, 4, (Object) null);
    }

    public static final String k(String str) {
        c.f.b.k.b(str, "receiver$0");
        return String.valueOf((char) Long.parseLong(c.l.n.b((CharSequence) str).toString(), 16));
    }

    public static final String a(CharSequence charSequence) {
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public static final String b(String str, String str2) {
        if (str != null) {
            return c(str, str2);
        }
        return null;
    }

    public static final String c(String str, String str2) {
        String strA;
        c.f.b.k.b(str, "receiver$0");
        return (str2 == null || (strA = a(str, str2)) == null) ? str : strA;
    }

    public static final String l(String str) throws UnsupportedEncodingException {
        c.f.b.k.b(str, "receiver$0");
        String strEncode = URLEncoder.encode(str, c.l.d.f2083a.name());
        c.f.b.k.a((Object) strEncode, "URLEncoder.encode(this, Charsets.UTF_8.name())");
        return c.l.n.a(strEncode, "+", "%20", false, 4, (Object) null);
    }

    public static final String m(String str) {
        c.f.b.k.b(str, "receiver$0");
        return URLDecoder.decode(str, c.l.d.f2083a.name());
    }

    public static final String n(String str) {
        c.f.b.k.b(str, "receiver$0");
        return net.dinglisch.android.taskerm.t.a(str);
    }

    public static final byte[] o(String str) {
        c.f.b.k.b(str, "receiver$0");
        return net.dinglisch.android.taskerm.t.b(str);
    }

    public static final byte[] p(String str) {
        c.f.b.k.b(str, "receiver$0");
        return Base64.decode(str, 0);
    }

    public static final <T> T a(String str, Class<T> cls) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(cls, "jsonClass");
        return (T) com.joaomgcd.taskerm.n.b.a().a(str, (Class) cls);
    }

    public static /* synthetic */ String a(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return a(str, str2, z);
    }

    public static final String a(String str, String str2, boolean z) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "replaceSpaceWith");
        String strA = c.l.n.a(str, "%", "", false, 4, (Object) null);
        if (strA == null) {
            throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String strA2 = new c.l.k("\\[[0-9]+\\]").a(c.l.n.a(c.l.n.a(c.l.n.a(c.l.n.a(c.l.n.a(c.l.n.b((CharSequence) strA).toString(), " ", str2, false, 4, (Object) null), " ", "_", false, 4, (Object) null), "[]", "", false, 4, (Object) null), "-", "", false, 4, (Object) null), "'", "_", false, 4, (Object) null), "");
        if (!z) {
            if (strA2 == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            strA2 = strA2.toLowerCase();
            c.f.b.k.a((Object) strA2, "(this as java.lang.String).toLowerCase()");
        }
        Iterator<c.l.i> itA = new c.l.k("[^A-Za-z0-9_]").b(strA2, 0).a();
        String strA3 = strA2;
        while (itA.hasNext()) {
            strA3 = c.l.n.a(strA3, itA.next().b(), "", false, 4, (Object) null);
        }
        return strA3.length() < 3 ? a(strA3, "a", 3, false) : strA3;
    }

    public static final String q(String str) {
        c.f.b.k.b(str, "receiver$0");
        return a(str, "_", false, 2, (Object) null);
    }

    public static final String r(String str) {
        c.f.b.k.b(str, "receiver$0");
        return a(str, "_", true);
    }

    public static final String s(String str) {
        c.f.b.k.b(str, "receiver$0");
        return e(c.a.j.a(c.l.n.b((CharSequence) j(c.l.n.a(str, (CharSequence) "%")), new String[]{"_"}, false, 0, 6, (Object) null), " ", null, null, 0, null, null, 62, null));
    }

    public static final boolean t(String str) {
        if (str == null) {
            return false;
        }
        return gw.c(str);
    }

    public static final boolean u(String str) {
        if (str == null) {
            return false;
        }
        return gw.e(str);
    }

    public static final boolean v(String str) {
        return t(str) || u(str);
    }

    public static final boolean w(String str) {
        if (str == null) {
            return false;
        }
        return gw.d(str);
    }

    public static final boolean x(String str) {
        return str != null && t(str) && gw.o(str);
    }

    public static final String a(String str, Context context, Bundle bundle) {
        c.f.b.k.b(context, "context");
        return gw.b(context, str, bundle);
    }

    public static final List<String> b(String str, Context context, Bundle bundle) {
        c.f.b.k.b(context, "context");
        List<String> listB = gw.b(context, str, true, false, bundle);
        c.f.b.k.a((Object) listB, "Variables.getArrayValues…s, true, false, taskVars)");
        return listB;
    }

    public static final String a(String str, String str2, int i, boolean z) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "ch");
        int length = i - str.length();
        c.f.b.z zVar = c.f.b.z.f2031a;
        Object[] objArr = {0};
        String str3 = String.format("%0" + length + "d", Arrays.copyOf(objArr, objArr.length));
        c.f.b.k.a((Object) str3, "java.lang.String.format(format, *args)");
        String strA = c.l.n.a(str3, "0", str2, false, 4, (Object) null);
        String str4 = z ? "" : strA;
        if (!z) {
            strA = "";
        }
        return str4 + str + strA;
    }

    public static final String y(String str) {
        c.f.b.k.b(str, "receiver$0");
        if (!c.l.n.a(str, "com.joaomgcd.", false, 2, (Object) null)) {
            return str;
        }
        String strA = a(str, "com.joaomgcd.");
        if (c.l.n.a(strA, "auto", false, 2, (Object) null)) {
            return "Auto" + e(a(strA, "auto"));
        }
        return e(strA);
    }

    public static final void a(Integer num, Context context) {
        c.f.b.k.b(context, "context");
        b(num != null ? aj.a(num.intValue(), context, new Object[0]) : null, context);
    }

    public static final void b(Integer num, Context context) {
        c.f.b.k.b(context, "context");
        c(num != null ? aj.a(num.intValue(), context, new Object[0]) : null, context);
    }

    public static final void b(String str, Context context) {
        c.f.b.k.b(context, "context");
        a(str, context, 0);
    }

    public static final void c(String str, Context context) {
        c.f.b.k.b(context, "context");
        a(str, context, 1);
    }

    static final class f extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8968a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f8969b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f8970c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str, Context context, int i) {
            super(0);
            this.f8968a = str;
            this.f8969b = context;
            this.f8970c = i;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            gr.b(this.f8969b, aj.a(R.string.app_name, this.f8969b, new Object[0]), this.f8968a, this.f8970c);
        }
    }

    private static final void a(String str, Context context, int i) {
        com.joaomgcd.taskerm.rx.i.e(new f(str, context, i));
    }

    public static final String d(String str, String str2) {
        c.f.b.k.b(str2, "default");
        if (str != null) {
            if (!(str.length() == 0)) {
                return str;
            }
        }
        return str2;
    }

    public static final String a(String str, String str2, String str3) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "before");
        c.f.b.k.b(str3, "after");
        String str4 = str;
        String strSubstring = str.substring(str2.length() + c.l.n.a((CharSequence) str4, str2, 0, false, 6, (Object) null), c.l.n.b((CharSequence) str4, str3, 0, false, 6, (Object) null));
        c.f.b.k.a((Object) strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static final boolean z(String str) {
        if (str != null) {
            if (str.length() > 0) {
                return true;
            }
        }
        return false;
    }

    public static final String A(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        return str;
    }

    public static final c.s a(String str, c.f.a.b<? super String, c.s> bVar) {
        c.f.b.k.b(bVar, "block");
        String strA = A(str);
        if (strA != null) {
            return bVar.invoke(strA);
        }
        return null;
    }

    public static final <T> void a(T[] tArr, c.f.a.b<? super T[], c.s> bVar) {
        c.f.b.k.b(bVar, "block");
        if (tArr == null || c.a.d.h(tArr).isEmpty()) {
            return;
        }
        bVar.invoke(tArr);
    }

    public static final boolean B(String str) {
        if (str != null) {
            return c.l.n.a(str, "text/", false, 2, (Object) null) || c.f.b.k.a((Object) str, (Object) "application/json");
        }
        return false;
    }

    public static final boolean e(String str, String str2) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "words");
        List listB = c.l.n.b((CharSequence) str2, new String[]{" "}, false, 0, 6, (Object) null);
        if ((listB instanceof Collection) && listB.isEmpty()) {
            return false;
        }
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            if (c.l.n.b((CharSequence) str, (CharSequence) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static final List<String> a(CharSequence charSequence, String... strArr) {
        c.f.b.k.b(charSequence, "receiver$0");
        c.f.b.k.b(strArr, "delimiters");
        List listB = c.l.n.b(charSequence, (String[]) Arrays.copyOf(strArr, strArr.length), false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listB) {
            if (((String) obj).length() > 0) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Uri C(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Uri.parse(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final j D(String str) {
        c.f.b.k.b(str, "receiver$0");
        return new j(str);
    }

    public static final ArrayList<Integer> a(String str, CharSequence[] charSequenceArr) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(charSequenceArr, "items");
        ArrayList<Integer> arrayList = new ArrayList<>();
        String lowerCase = str.toLowerCase();
        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        List listB = c.l.n.b((CharSequence) lowerCase, new String[]{" "}, false, 0, 6, (Object) null);
        int length = charSequenceArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            String string = charSequenceArr[i].toString();
            if (string == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase2 = string.toLowerCase();
            c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
            List list = listB;
            boolean z = true;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (!c.l.n.b((CharSequence) lowerCase2, (CharSequence) it.next(), false, 2, (Object) null)) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                arrayList.add(Integer.valueOf(i2));
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    public static final class d<T> extends c.f.b.l implements c.f.a.b<T, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f8966a = new d();

        public d() {
            super(1);
        }

        /* JADX WARN: Incorrect types in method signature: (TT;)Ljava/lang/String; */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(Enum r2) {
            c.f.b.k.b(r2, "it");
            return r2.name();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [T] */
    public static final class e<T> extends c.f.b.l implements c.f.a.b<T, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f8967a = new e();

        e() {
            super(1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(T t) {
            if (t != 0) {
                return ((Enum) t).name();
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Enum<*>");
        }
    }

    public static /* synthetic */ Object a(String str, Class cls, c.f.a.b bVar, int i, Object obj) {
        if ((i & 2) != 0) {
            bVar = e.f8967a;
        }
        return a(str, cls, bVar);
    }

    public static final <T> T a(String str, Class<T> cls, c.f.a.b<? super T, String> bVar) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(cls, "clzz");
        c.f.b.k.b(bVar, "stringGetter");
        if (!cls.isEnum()) {
            throw new ClassCastException(cls.getName() + " is not an enum");
        }
        T[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (T t : enumConstants) {
            if (c.f.b.k.a((Object) bVar.invoke(t), (Object) str)) {
                return t;
            }
        }
        return null;
    }

    public static final void a(String str, String str2, boolean z, Throwable th) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "tag");
        if (z) {
            str = com.joaomgcd.taskerm.rx.i.b() + ": " + str;
        }
        if (th == null) {
            net.dinglisch.android.taskerm.bn.b(str2, str);
        } else {
            net.dinglisch.android.taskerm.bn.a(str2, str, th);
        }
    }

    public static final String a(String str, Context context, String str2, int i, Bundle bundle, boolean z) {
        String strA;
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str2, "actionName");
        if (str == null || (strA = gw.a(context, str, true, false, true, false, (fz.a) null, bundle)) == null) {
            return null;
        }
        Double dA = net.dinglisch.android.taskerm.ar.a(strA, true, "dsv/" + str2 + '/' + str, i);
        if (dA != null) {
            String strA2 = gr.a(dA.doubleValue(), i);
            c.f.b.k.a((Object) strA2, "Utils.doubleToString(mathEval, roundingDigits)");
            return c.l.n.a(strA2, (char) 8722, '-', false, 4, (Object) null);
        }
        if (z) {
            return strA;
        }
        return null;
    }

    public static final Spanned E(String str) {
        c.f.b.k.b(str, "receiver$0");
        return com.joaomgcd.taskerm.util.f.f9323b.e() ? Html.fromHtml(str) : Html.fromHtml(str, 0);
    }

    public static final CharSequence F(String str) {
        c.f.b.k.b(str, "receiver$0");
        boolean zA = a(str, "</", "/>");
        CharSequence charSequenceE = str;
        if (zA) {
            charSequenceE = E(str);
        }
        return charSequenceE;
    }

    static final class c extends c.f.b.l implements c.f.a.b<c.l.i, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8965a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(c.l.i iVar) {
            c.f.b.k.b(iVar, "it");
            return iVar.b();
        }
    }

    public static final c.k.g<String> G(String str) {
        return str == null ? c.k.h.a(new String[0]) : c.k.h.b(c.l.k.b(new c.l.k("(?<=\\(\\?\\<)\\w+"), str, 0, 2, null), c.f8965a);
    }

    public static final c.l.i f(String str, String str2) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "regex");
        return c.l.k.a(new c.l.k(str2), str, 0, 2, null);
    }

    public static final List<c.l.i> g(String str, String str2) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "regex");
        return c.k.h.d(c.l.k.b(new c.l.k(str2), str, 0, 2, null));
    }

    public static final Integer h(String str, String str2) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(str2, "regex");
        List<c.l.i> listG = g(str, str2);
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) listG, 10));
        Iterator<T> it = listG.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((c.l.i) it.next()).b().length()));
        }
        return (Integer) c.a.j.o(arrayList);
    }

    public static final a.a.a.b.e H(String str) {
        c.f.b.k.b(str, "receiver$0");
        return a.a.a.b.e.a(str);
    }

    public static final StringBuilder b(String str, int i) {
        c.f.b.k.b(str, "receiver$0");
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = new c.i.c(0, i).iterator();
        while (it.hasNext()) {
            ((c.a.y) it).b();
            sb.append(str);
        }
        return sb;
    }

    static final class a extends c.f.b.l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8962a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f8962a = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            return Color.parseColor(this.f8962a);
        }
    }

    public static final Integer I(String str) {
        if (str != null) {
            return (Integer) aj.a((c.f.a.b) null, new a(str), 1, (Object) null);
        }
        return null;
    }

    public static final String J(String str) {
        c.f.b.k.b(str, "receiver$0");
        return org.joda.time.format.a.a(str).a(System.currentTimeMillis());
    }

    public static final String[] a(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(charSequenceArr.length);
        for (CharSequence charSequence : charSequenceArr) {
            arrayList.add(charSequence.toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public static final boolean a(String str, String... strArr) {
        c.f.b.k.b(str, "receiver$0");
        c.f.b.k.b(strArr, "strings");
        for (String str2 : strArr) {
            if (c.l.n.b((CharSequence) str, (CharSequence) str2, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}

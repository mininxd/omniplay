package com.joaomgcd.taskerm.action.input;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ay extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5454a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5456c;

    public ay() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void center$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void left$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void right$annotations() {
    }

    static final class a extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5457a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(invoke2(str));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(String str) {
            String string;
            if (str == null) {
                string = null;
            } else {
                if (str == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                string = c.l.n.b((CharSequence) str).toString();
            }
            String str2 = string;
            return str2 == null || str2.length() == 0;
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5458a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(invoke2(str));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(String str) {
            if (str == null) {
                return false;
            }
            String str2 = str;
            if (c.l.n.b((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null) && !c.l.n.b((CharSequence) str2, (CharSequence) "]", false, 2, (Object) null)) {
                return true;
            }
            if ((c.l.n.b((CharSequence) str2, (CharSequence) "]", false, 2, (Object) null) && !c.l.n.b((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null)) || c.l.n.b((CharSequence) str2, (CharSequence) "[]", false, 2, (Object) null)) {
                return true;
            }
            int i = 0;
            for (int i2 = 0; i2 < str2.length(); i2++) {
                if (str2.charAt(i2) == '[') {
                    i++;
                }
            }
            int i3 = 0;
            for (int i4 = 0; i4 < str2.length(); i4++) {
                if (str2.charAt(i4) == ']') {
                    i3++;
                }
            }
            return i != i3;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.b<String, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5459a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(String str) {
            return Boolean.valueOf(invoke2(str));
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2(String str) {
            c.f.b.k.b(str, "receiver$0");
            String str2 = str;
            if (!c.l.n.b((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null) && !c.l.n.b((CharSequence) str2, (CharSequence) "]", false, 2, (Object) null)) {
                return false;
            }
            if (!c.l.n.b((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null) || c.l.n.b((CharSequence) str2, (CharSequence) "]", false, 2, (Object) null)) {
                return (c.l.n.b((CharSequence) str2, (CharSequence) "]", false, 2, (Object) null) && !c.l.n.b((CharSequence) str2, (CharSequence) "[", false, 2, (Object) null)) || new c.l.k("key\\([^0-9]*?:").b(str2) || c.l.n.a(com.joaomgcd.taskerm.util.ak.a(str, "[", "]")) == null;
            }
            return true;
        }
    }

    public /* synthetic */ ay(String str, String str2, String str3, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3);
    }

    public final String getLeft() {
        return this.f5454a;
    }

    public final void setLeft(String str) {
        this.f5454a = str;
    }

    public final String getCenter() {
        return this.f5455b;
    }

    public final void setCenter(String str) {
        this.f5455b = str;
    }

    public final String getRight() {
        return this.f5456c;
    }

    public final void setRight(String str) {
        this.f5456c = str;
    }

    public ay(String str, String str2, String str3) {
        this.f5454a = str;
        this.f5455b = str2;
        this.f5456c = str3;
    }

    public final String getNavigationBarValue() {
        if (!isValid()) {
            return "";
        }
        String str = com.joaomgcd.taskerm.util.ak.d(this.f5454a, "") + ';' + com.joaomgcd.taskerm.util.ak.d(this.f5455b, "") + ';' + com.joaomgcd.taskerm.util.ak.d(this.f5456c, "");
        if (c.l.n.b((CharSequence) str, (CharSequence) "pin", false, 2, (Object) null)) {
            return str;
        }
        return "pin[0.0000001]," + str;
    }

    public final boolean isValid() {
        return (all(a.f5457a) || any(b.f5458a) || anyItem(c.f5459a)) ? false : true;
    }

    public final boolean all(c.f.a.b<? super String, Boolean> bVar) {
        c.f.b.k.b(bVar, "condition");
        return bVar.invoke(this.f5454a).booleanValue() && bVar.invoke(this.f5455b).booleanValue() && bVar.invoke(this.f5456c).booleanValue();
    }

    public final boolean any(c.f.a.b<? super String, Boolean> bVar) {
        c.f.b.k.b(bVar, "condition");
        return bVar.invoke(this.f5454a).booleanValue() || bVar.invoke(this.f5455b).booleanValue() || bVar.invoke(this.f5456c).booleanValue();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x004e  */
    /* JADX WARN: Code duplicated, block: B:34:0x0095  */
    /* JADX WARN: Code duplicated, block: B:36:0x0099  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:43:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:46:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:59:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:? A[LOOP:2: B:44:0x00c1->B:60:?, LOOP_END, SYNTHETIC] */
    public final boolean anyItem(c.f.a.b<? super String, Boolean> bVar) {
        String str;
        String str2;
        List listB;
        List list;
        Iterator it;
        boolean z;
        List listB2;
        boolean z2;
        List listB3;
        boolean z3;
        c.f.b.k.b(bVar, "condition");
        String str3 = this.f5454a;
        if (str3 != null && (listB3 = c.l.n.b((CharSequence) str3, new String[]{","}, false, 0, 6, (Object) null)) != null) {
            List list2 = listB3;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it2 = list2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z3 = false;
                        break;
                    }
                    if (bVar.invoke((String) it2.next()).booleanValue()) {
                        z3 = true;
                        break;
                    }
                }
            } else {
                z3 = false;
                break;
            }
            if (!z3) {
                str = this.f5455b;
                if (str == null) {
                    str2 = this.f5456c;
                    if (str2 != null) {
                        list = listB;
                        if (list instanceof Collection) {
                            it = list.iterator();
                            while (it.hasNext()) {
                                if (bVar.invoke((String) it.next()).booleanValue()) {
                                    z = true;
                                }
                            }
                            z = false;
                        } else {
                            it = list.iterator();
                            while (it.hasNext()) {
                                if (bVar.invoke((String) it.next()).booleanValue()) {
                                    z = true;
                                }
                            }
                            z = false;
                        }
                        if (z) {
                        }
                    }
                    return false;
                }
                str2 = this.f5456c;
                if (str2 != null) {
                    list = listB;
                    if (list instanceof Collection) {
                        it = list.iterator();
                        while (it.hasNext()) {
                            if (bVar.invoke((String) it.next()).booleanValue()) {
                                z = true;
                            }
                        }
                        z = false;
                    } else {
                        it = list.iterator();
                        while (it.hasNext()) {
                            if (bVar.invoke((String) it.next()).booleanValue()) {
                                z = true;
                            }
                        }
                        z = false;
                    }
                    if (z) {
                    }
                }
                return false;
            }
        } else {
            str = this.f5455b;
            if (str == null && (listB2 = c.l.n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null)) != null) {
                List list3 = listB2;
                if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                    Iterator it3 = list3.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z2 = false;
                            break;
                        }
                        if (bVar.invoke((String) it3.next()).booleanValue()) {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                    break;
                }
                if (!z2) {
                    str2 = this.f5456c;
                    if (str2 != null) {
                        list = listB;
                        if (list instanceof Collection) {
                            it = list.iterator();
                            while (it.hasNext()) {
                                if (bVar.invoke((String) it.next()).booleanValue()) {
                                    z = true;
                                }
                            }
                            z = false;
                        } else {
                            it = list.iterator();
                            while (it.hasNext()) {
                                if (bVar.invoke((String) it.next()).booleanValue()) {
                                    z = true;
                                }
                            }
                            z = false;
                        }
                        if (z) {
                        }
                    }
                    return false;
                }
            } else {
                str2 = this.f5456c;
                if (str2 != null && (listB = c.l.n.b((CharSequence) str2, new String[]{","}, false, 0, 6, (Object) null)) != null) {
                    list = listB;
                    if ((list instanceof Collection) || !list.isEmpty()) {
                        it = list.iterator();
                        while (it.hasNext()) {
                            if (bVar.invoke((String) it.next()).booleanValue()) {
                                z = true;
                            }
                        }
                        z = false;
                    } else {
                        z = false;
                    }
                    if (z) {
                    }
                }
                return false;
            }
        }
        return true;
    }
}

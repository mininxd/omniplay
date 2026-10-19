package com.joaomgcd.taskerm.action.f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f5195c;

    public ae() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.structure_type_help)
    public static /* synthetic */ void type$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, d = true)
    public static /* synthetic */ void variableName$annotations() {
    }

    public ae(String str, String str2, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5193a = str;
        this.f5194b = str2;
        this.f5195c = jVar;
    }

    public /* synthetic */ ae(String str, String str2, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getVariableName() {
        return this.f5193a;
    }

    public final void setVariableName(String str) {
        this.f5193a = str;
    }

    public final String getType() {
        return this.f5194b;
    }

    public final void setType(String str) {
        this.f5194b = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f5195c;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5195c = jVar;
    }

    public final List<String> getNamesArray() {
        List listB;
        String string;
        String str = this.f5193a;
        if (str == null || (listB = c.l.n.b((CharSequence) str, new String[]{g.b(this.f5193a, ",")}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            String strA = com.joaomgcd.taskerm.util.ak.A((String) it.next());
            if (strA == null) {
                string = null;
            } else {
                if (strA == null) {
                    throw new c.q("null cannot be cast to non-null type kotlin.CharSequence");
                }
                string = c.l.n.b((CharSequence) strA).toString();
            }
            if (string != null) {
                arrayList.add(string);
            }
        }
        return arrayList;
    }

    public final com.joaomgcd.taskerm.v.b getEnumType() {
        String str = this.f5194b;
        if (str != null) {
            return (com.joaomgcd.taskerm.v.b) ((Enum) com.joaomgcd.taskerm.util.ak.a(str, com.joaomgcd.taskerm.v.b.class, com.joaomgcd.taskerm.util.ak.d.f8966a));
        }
        return null;
    }
}

package com.joaomgcd.taskerm.action.f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class aa extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private k f5176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5177c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5178d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5179e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5180f;
    private com.joaomgcd.taskerm.inputoutput.j g;

    public aa() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, d = true, e = R.string.array_join_formatter_help)
    public static /* synthetic */ void format$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.array_joiner_help)
    public static /* synthetic */ void joiner$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void mergeType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, d = true, e = R.string.one_or_more_array_names)
    public static /* synthetic */ void names$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, e = R.string.array_join_output_joiner_help)
    public static /* synthetic */ void outputArrayJoiner$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, d = true, e = R.string.array_join_output_help)
    public static /* synthetic */ void outputArrayName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public /* synthetic */ aa(String str, k kVar, String str2, String str3, String str4, String str5, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (k) null : kVar, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getNames() {
        return this.f5175a;
    }

    public final void setNames(String str) {
        this.f5175a = str;
    }

    public final k getMergeType() {
        return this.f5176b;
    }

    public final void setMergeType(k kVar) {
        this.f5176b = kVar;
    }

    public final String getJoiner() {
        return this.f5177c;
    }

    public final void setJoiner(String str) {
        this.f5177c = str;
    }

    public final String getFormat() {
        return this.f5178d;
    }

    public final void setFormat(String str) {
        this.f5178d = str;
    }

    public final String getOutputArrayName() {
        return this.f5179e;
    }

    public final void setOutputArrayName(String str) {
        this.f5179e = str;
    }

    public final String getOutputArrayJoiner() {
        return this.f5180f;
    }

    public final void setOutputArrayJoiner(String str) {
        this.f5180f = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.g = jVar;
    }

    public aa(String str, k kVar, String str2, String str3, String str4, String str5, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5175a = str;
        this.f5176b = kVar;
        this.f5177c = str2;
        this.f5178d = str3;
        this.f5179e = str4;
        this.f5180f = str5;
        this.g = jVar;
    }

    public final List<String> getNamesArray() {
        List listB;
        String str = this.f5175a;
        if (str == null || (listB = c.l.n.b((CharSequence) str, new String[]{b.b(this.f5175a)}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = listB.iterator();
        while (it.hasNext()) {
            String strA = com.joaomgcd.taskerm.util.ak.A((String) it.next());
            if (strA != null) {
                arrayList.add(strA);
            }
        }
        return arrayList;
    }
}

package com.joaomgcd.taskerm.action.f;

import androidx.core.app.FrameMetricsAggregator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class af extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5197b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5199d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f5200e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Integer f5201f;
    private Boolean g;
    private Boolean h;
    private com.joaomgcd.taskerm.inputoutput.j i;

    public af() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, e = R.string.do_maths_multiple)
    public static /* synthetic */ void doMaths$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7, e = R.string.keep_existing_variables_help)
    public static /* synthetic */ void keepExisting$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void maxRoundingDigits$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, d = true, e = R.string.one_or_more_comma_separated_variable_names)
    public static /* synthetic */ void names$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.splitter_for_values)
    public static /* synthetic */ void splitter$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.splitter_for_variables)
    public static /* synthetic */ void splitterVariableNames$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void structuredOutput$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, d = true, e = R.string.one_or_more_values_corresponding_to_names)
    public static /* synthetic */ void values$annotations() {
    }

    public /* synthetic */ af(String str, String str2, String str3, String str4, Boolean bool, Integer num, Boolean bool2, Boolean bool3, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (Boolean) null : bool, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? (Boolean) null : bool2, (i & 128) != 0 ? (Boolean) null : bool3, (i & 256) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final String getNames() {
        return this.f5196a;
    }

    public final void setNames(String str) {
        this.f5196a = str;
    }

    public final String getSplitterVariableNames() {
        return this.f5197b;
    }

    public final void setSplitterVariableNames(String str) {
        this.f5197b = str;
    }

    public final String getValues() {
        return this.f5198c;
    }

    public final void setValues(String str) {
        this.f5198c = str;
    }

    public final String getSplitter() {
        return this.f5199d;
    }

    public final void setSplitter(String str) {
        this.f5199d = str;
    }

    public final Boolean getDoMaths() {
        return this.f5200e;
    }

    public final void setDoMaths(Boolean bool) {
        this.f5200e = bool;
    }

    public final Integer getMaxRoundingDigits() {
        return this.f5201f;
    }

    public final void setMaxRoundingDigits(Integer num) {
        this.f5201f = num;
    }

    public final Boolean getKeepExisting() {
        return this.g;
    }

    public final void setKeepExisting(Boolean bool) {
        this.g = bool;
    }

    public final Boolean getStructuredOutput() {
        return this.h;
    }

    public final void setStructuredOutput(Boolean bool) {
        this.h = bool;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.i;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.i = jVar;
    }

    public af(String str, String str2, String str3, String str4, Boolean bool, Integer num, Boolean bool2, Boolean bool3, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5196a = str;
        this.f5197b = str2;
        this.f5198c = str3;
        this.f5199d = str4;
        this.f5200e = bool;
        this.f5201f = num;
        this.g = bool2;
        this.h = bool3;
        this.i = jVar;
    }

    public final List<String> getNamesArray() {
        List listB;
        String str = this.f5196a;
        if (str == null || (listB = c.l.n.b((CharSequence) str, new String[]{i.b(this.f5196a, this.f5197b)}, false, 0, 6, (Object) null)) == null) {
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

    public final List<String> getValuesArray() {
        String str = this.f5198c;
        if (str != null) {
            return c.l.n.b((CharSequence) str, new String[]{getValuesSplitter()}, false, 0, 6, (Object) null);
        }
        return null;
    }

    public final String getValuesSplitter() {
        return i.b(this.f5198c, this.f5199d);
    }
}

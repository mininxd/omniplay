package com.joaomgcd.taskerm.action.f;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ab extends com.joaomgcd.taskerm.helper.actions.execute.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private l f5181a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5182b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5183c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5185e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5186f;
    private String g;
    private Boolean h;
    private Boolean i;
    private ak j;
    private String k;
    private com.joaomgcd.taskerm.inputoutput.j l;

    public ab() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 9, e = R.string.do_maths_parse_format_datetime)
    public static /* synthetic */ void doMaths$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 8)
    public static /* synthetic */ void getAllDetails$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, d = true, e = R.string.input_dates_format_help)
    public static /* synthetic */ void inputFormats$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4, e = R.string.separator_comma_or_newnline_by_default)
    public static /* synthetic */ void inputSeparator$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void inputType$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, d = true, e = R.string.input_dates_help)
    public static /* synthetic */ void inputs$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0, e = R.string.output_dates_help)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6, e = R.string.separator_comma_or_newnline_by_default)
    public static /* synthetic */ void outputFormatSeparator$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5, d = true, e = R.string.output_dates_format_help, g = "https://www.joda.org/joda-time/apidocs/org/joda/time/format/DateTimeFormat.html")
    public static /* synthetic */ void outputFormats$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 7, d = true, e = R.string.output_dates_format_variable_names_help)
    public static /* synthetic */ void outputFormattedVariableName$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 11, e = R.string.output_offset_explained)
    public static /* synthetic */ void outputOffset$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 10, e = R.string.output_offset_type_explained)
    public static /* synthetic */ void outputOffsetType$annotations() {
    }

    public /* synthetic */ ab(l lVar, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, ak akVar, String str7, com.joaomgcd.taskerm.inputoutput.j jVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (l) null : lVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (String) null : str4, (i & 32) != 0 ? (String) null : str5, (i & 64) != 0 ? (String) null : str6, (i & 128) != 0 ? (Boolean) null : bool, (i & 256) != 0 ? (Boolean) null : bool2, (i & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (ak) null : akVar, (i & 1024) != 0 ? (String) null : str7, (i & 2048) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar);
    }

    public final l getInputType() {
        return this.f5181a;
    }

    public final void setInputType(l lVar) {
        this.f5181a = lVar;
    }

    public final String getInputs() {
        return this.f5182b;
    }

    public final void setInputs(String str) {
        this.f5182b = str;
    }

    public final String getInputFormats() {
        return this.f5183c;
    }

    public final void setInputFormats(String str) {
        this.f5183c = str;
    }

    public final String getInputSeparator() {
        return this.f5184d;
    }

    public final void setInputSeparator(String str) {
        this.f5184d = str;
    }

    public final String getOutputFormats() {
        return this.f5185e;
    }

    public final void setOutputFormats(String str) {
        this.f5185e = str;
    }

    public final String getOutputFormatSeparator() {
        return this.f5186f;
    }

    public final void setOutputFormatSeparator(String str) {
        this.f5186f = str;
    }

    public final String getOutputFormattedVariableName() {
        return this.g;
    }

    public final void setOutputFormattedVariableName(String str) {
        this.g = str;
    }

    public final Boolean getGetAllDetails() {
        return this.h;
    }

    public final void setGetAllDetails(Boolean bool) {
        this.h = bool;
    }

    public final Boolean getDoMaths() {
        return this.i;
    }

    public final void setDoMaths(Boolean bool) {
        this.i = bool;
    }

    public final ak getOutputOffsetType() {
        return this.j;
    }

    public final void setOutputOffsetType(ak akVar) {
        this.j = akVar;
    }

    public final String getOutputOffset() {
        return this.k;
    }

    public final void setOutputOffset(String str) {
        this.k = str;
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.l;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.l = jVar;
    }

    public ab(l lVar, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, ak akVar, String str7, com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f5181a = lVar;
        this.f5182b = str;
        this.f5183c = str2;
        this.f5184d = str3;
        this.f5185e = str4;
        this.f5186f = str5;
        this.g = str6;
        this.h = bool;
        this.i = bool2;
        this.j = akVar;
        this.k = str7;
        this.l = jVar;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0055  */
    public final List<String> getFormattedVariableNames() {
        List listA;
        List listB;
        String strA = com.joaomgcd.taskerm.util.ak.A(this.g);
        if (strA == null || (listB = c.l.n.b((CharSequence) strA, new String[]{c.b(this.g)}, false, 0, 6, (Object) null)) == null) {
            listA = c.a.j.a("formatted");
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator it = listB.iterator();
            while (it.hasNext()) {
                String strA2 = com.joaomgcd.taskerm.util.ak.A((String) it.next());
                String strR = strA2 != null ? com.joaomgcd.taskerm.util.ak.r(strA2) : null;
                if (strR != null) {
                    arrayList.add(strR);
                }
            }
            listA = com.joaomgcd.taskerm.util.aj.a((List) arrayList);
            if (listA == null) {
                listA = c.a.j.a("formatted");
            }
        }
        List list = listA;
        ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add("%" + ((String) it2.next()));
        }
        return arrayList2;
    }

    public final ak getOutputOffsetTypeNotNull() {
        ak akVar = this.j;
        return akVar != null ? akVar : ak.None;
    }

    public final boolean getOutputHasOffset() {
        return getOutputOffsetTypeNotNull() != ak.None;
    }

    public final List<Integer> getOutputOffsetsSeconds() {
        List listB;
        int iIntValue;
        ak outputOffsetTypeNotNull = getOutputOffsetTypeNotNull();
        if (outputOffsetTypeNotNull == ak.None) {
            return c.a.j.a();
        }
        int iOrdinal = outputOffsetTypeNotNull.ordinal();
        String str = this.k;
        if (str == null || (listB = c.l.n.b((CharSequence) str, new String[]{c.b(this.k)}, false, 0, 6, (Object) null)) == null) {
            return c.a.j.a();
        }
        List list = listB;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Integer numC = c.l.n.c((String) it.next());
            if (numC != null) {
                iIntValue = numC.intValue();
                if (iOrdinal >= ak.Minutes.ordinal()) {
                    iIntValue *= 60;
                }
                if (iOrdinal >= ak.Hours.ordinal()) {
                    iIntValue *= 60;
                }
                if (iOrdinal >= ak.Days.ordinal()) {
                    iIntValue *= 24;
                }
            } else {
                iIntValue = 0;
            }
            arrayList.add(Integer.valueOf(iIntValue));
        }
        return arrayList;
    }

    public final boolean getDoMathsNotNull() {
        Boolean bool = this.i;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getHasCustomInput() {
        return this.f5181a == l.Custom;
    }
}

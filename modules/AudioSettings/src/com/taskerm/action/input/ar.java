package com.joaomgcd.taskerm.action.input;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class ar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5424a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5425b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5426c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5427d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Class<bh> f5428e;

    public ar() {
        this(null, null, null, null, null, 31, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void defaultInput$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3, e = R.string.help_pick_input_text)
    public static /* synthetic */ void text$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2, e = R.string.help_pick_input_title)
    public static /* synthetic */ void title$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void type$annotations() {
    }

    public ar(String str, String str2, String str3, String str4, Class<bh> cls) {
        this.f5424a = str;
        this.f5425b = str2;
        this.f5426c = str3;
        this.f5427d = str4;
        this.f5428e = cls;
    }

    public /* synthetic */ ar(String str, String str2, String str3, String str4, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? bh.class : cls);
    }

    public final String getType() {
        return this.f5424a;
    }

    public final void setType(String str) {
        this.f5424a = str;
    }

    public final String getTitle() {
        return this.f5425b;
    }

    public final void setTitle(String str) {
        this.f5425b = str;
    }

    public final String getText() {
        return this.f5426c;
    }

    public final void setText(String str) {
        this.f5426c = str;
    }

    public final String getDefaultInput() {
        return this.f5427d;
    }

    public final void setDefaultInput(String str) {
        this.f5427d = str;
    }

    public final Class<bh> getOutputClass() {
        return this.f5428e;
    }

    public final void setOutputClass(Class<bh> cls) {
        this.f5428e = cls;
    }

    public final com.joaomgcd.taskerm.profile.i getEnumType() {
        String str = this.f5424a;
        if (str != null) {
            return (com.joaomgcd.taskerm.profile.i) ((Enum) com.joaomgcd.taskerm.util.ak.a(str, com.joaomgcd.taskerm.profile.i.class, com.joaomgcd.taskerm.util.ak.d.f8966a));
        }
        return null;
    }
}

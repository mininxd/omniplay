package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.keyboard.OutputKeyboardCommand;

/* JADX INFO: loaded from: classes.dex */
public final class ax {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Integer f5451b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5452c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Class<OutputKeyboardCommand> f5453d;

    public ax() {
        this(null, null, null, null, 15, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void dontRestoreKeyboard$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void input$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void timeBetweenInputs$annotations() {
    }

    public ax(String str, Integer num, Boolean bool, Class<OutputKeyboardCommand> cls) {
        this.f5450a = str;
        this.f5451b = num;
        this.f5452c = bool;
        this.f5453d = cls;
    }

    public /* synthetic */ ax(String str, Integer num, Boolean bool, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? OutputKeyboardCommand.class : cls);
    }

    public final String getInput() {
        return this.f5450a;
    }

    public final void setInput(String str) {
        this.f5450a = str;
    }

    public final Integer getTimeBetweenInputs() {
        return this.f5451b;
    }

    public final void setTimeBetweenInputs(Integer num) {
        this.f5451b = num;
    }

    public final Boolean getDontRestoreKeyboard() {
        return this.f5452c;
    }

    public final void setDontRestoreKeyboard(Boolean bool) {
        this.f5452c = bool;
    }

    public final Class<OutputKeyboardCommand> getOutputClass() {
        return this.f5453d;
    }

    public final void setOutputClass(Class<OutputKeyboardCommand> cls) {
        this.f5453d = cls;
    }
}

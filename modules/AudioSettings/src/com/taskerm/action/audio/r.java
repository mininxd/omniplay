package com.joaomgcd.taskerm.action.audio;

import android.annotation.TargetApi;
import com.joaomgcd.taskerm.function.DoNotDisturbSenders;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private g f5000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DoNotDisturbSenders f5001b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Boolean f5002c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private DoNotDisturbSenders f5003d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5004e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5005f;
    private Class<OutputDoNotDisturb> g;

    public r() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void callers$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void categories$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void messageSenders$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void mode$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void repeatCallers$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void suppressedEffects$annotations() {
    }

    public r(g gVar, DoNotDisturbSenders doNotDisturbSenders, Boolean bool, DoNotDisturbSenders doNotDisturbSenders2, String str, String str2, Class<OutputDoNotDisturb> cls) {
        this.f5000a = gVar;
        this.f5001b = doNotDisturbSenders;
        this.f5002c = bool;
        this.f5003d = doNotDisturbSenders2;
        this.f5004e = str;
        this.f5005f = str2;
        this.g = cls;
    }

    public /* synthetic */ r(g gVar, DoNotDisturbSenders doNotDisturbSenders, Boolean bool, DoNotDisturbSenders doNotDisturbSenders2, String str, String str2, Class cls, int i, c.f.b.g gVar2) {
        this((i & 1) != 0 ? (g) null : gVar, (i & 2) != 0 ? (DoNotDisturbSenders) null : doNotDisturbSenders, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? (DoNotDisturbSenders) null : doNotDisturbSenders2, (i & 16) != 0 ? (String) null : str, (i & 32) != 0 ? (String) null : str2, (i & 64) != 0 ? OutputDoNotDisturb.class : cls);
    }

    public final g getMode() {
        return this.f5000a;
    }

    public final void setMode(g gVar) {
        this.f5000a = gVar;
    }

    public final DoNotDisturbSenders getCallers() {
        return this.f5001b;
    }

    public final void setCallers(DoNotDisturbSenders doNotDisturbSenders) {
        this.f5001b = doNotDisturbSenders;
    }

    public final Boolean getRepeatCallers() {
        return this.f5002c;
    }

    public final void setRepeatCallers(Boolean bool) {
        this.f5002c = bool;
    }

    public final DoNotDisturbSenders getMessageSenders() {
        return this.f5003d;
    }

    public final void setMessageSenders(DoNotDisturbSenders doNotDisturbSenders) {
        this.f5003d = doNotDisturbSenders;
    }

    public final String getCategories() {
        return this.f5004e;
    }

    public final void setCategories(String str) {
        this.f5004e = str;
    }

    public final String getSuppressedEffects() {
        return this.f5005f;
    }

    public final void setSuppressedEffects(String str) {
        this.f5005f = str;
    }

    public final Class<OutputDoNotDisturb> getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(Class<OutputDoNotDisturb> cls) {
        this.g = cls;
    }

    @TargetApi(23)
    public final DoNotDisturbSenders getCallersNotNull() {
        DoNotDisturbSenders doNotDisturbSenders = this.f5001b;
        return doNotDisturbSenders != null ? doNotDisturbSenders : DoNotDisturbSenders.Any;
    }

    @TargetApi(23)
    public final DoNotDisturbSenders getMessageSendersNotNull() {
        DoNotDisturbSenders doNotDisturbSenders = this.f5003d;
        return doNotDisturbSenders != null ? doNotDisturbSenders : DoNotDisturbSenders.Any;
    }

    public final boolean getRepeatCallersNotNull() {
        Boolean bool = this.f5002c;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final String getCategoriesNotNull() {
        String str = this.f5004e;
        return str != null ? str : "";
    }

    public final String getSuppressedEffectsNotNull() {
        String str = this.f5005f;
        return str != null ? str : "";
    }
}

package com.joaomgcd.taskerm.action.audio;

import com.joaomgcd.taskerm.function.DoNotDisturbCategory;
import com.joaomgcd.taskerm.function.DoNotDisturbSenders;
import com.joaomgcd.taskerm.function.DoNotDisturbSuppressedEffect;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "dnd")
public final class OutputDoNotDisturb {
    private DoNotDisturbSenders allowCallers;
    private DoNotDisturbSenders allowMessageSenders;
    private DoNotDisturbCategory[] categories;
    private g mode;
    private DoNotDisturbSuppressedEffect[] suppressedEffects;

    public OutputDoNotDisturb(g gVar, DoNotDisturbSenders doNotDisturbSenders, DoNotDisturbSenders doNotDisturbSenders2, DoNotDisturbCategory[] doNotDisturbCategoryArr, DoNotDisturbSuppressedEffect[] doNotDisturbSuppressedEffectArr) {
        c.f.b.k.b(gVar, "mode");
        this.mode = gVar;
        this.allowCallers = doNotDisturbSenders;
        this.allowMessageSenders = doNotDisturbSenders2;
        this.categories = doNotDisturbCategoryArr;
        this.suppressedEffects = doNotDisturbSuppressedEffectArr;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_mode, name = "mode")
    public final g getMode() {
        return this.mode;
    }

    public final void setMode(g gVar) {
        c.f.b.k.b(gVar, "<set-?>");
        this.mode = gVar;
    }

    @TaskerOutputVariable(labelResId = R.string.allow_call_senders, name = "allow_callers")
    public final DoNotDisturbSenders getAllowCallers() {
        return this.allowCallers;
    }

    public final void setAllowCallers(DoNotDisturbSenders doNotDisturbSenders) {
        this.allowCallers = doNotDisturbSenders;
    }

    @TaskerOutputVariable(labelResId = R.string.allow_sms_senders, name = "allow_message_senders")
    public final DoNotDisturbSenders getAllowMessageSenders() {
        return this.allowMessageSenders;
    }

    public final void setAllowMessageSenders(DoNotDisturbSenders doNotDisturbSenders) {
        this.allowMessageSenders = doNotDisturbSenders;
    }

    @TaskerOutputVariable(labelResId = R.string.allow_categories, name = "categories")
    public final DoNotDisturbCategory[] getCategories() {
        return this.categories;
    }

    public final void setCategories(DoNotDisturbCategory[] doNotDisturbCategoryArr) {
        this.categories = doNotDisturbCategoryArr;
    }

    @TaskerOutputVariable(labelResId = R.string.suppressed_effects, name = "suppressed_effects")
    public final DoNotDisturbSuppressedEffect[] getSuppressedEffects() {
        return this.suppressedEffects;
    }

    public final void setSuppressedEffects(DoNotDisturbSuppressedEffect[] doNotDisturbSuppressedEffectArr) {
        this.suppressedEffects = doNotDisturbSuppressedEffectArr;
    }
}

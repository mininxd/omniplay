package com.joaomgcd.taskerm.pattern;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "mt")
public final class RegexMatchesOutputWithGroups extends RegexMatchesOutputBase {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegexMatchesOutputWithGroups(d dVar) {
        super(dVar);
        k.b(dVar, "regexMatches");
    }

    @TaskerOutputVariable(labelResId = R.string.groups, name = "groups")
    public final String[] getGroupsForTasker() {
        return getRegexMatches().getGroupsForTasker();
    }
}

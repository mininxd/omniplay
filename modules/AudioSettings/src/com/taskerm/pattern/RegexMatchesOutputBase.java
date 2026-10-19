package com.joaomgcd.taskerm.pattern;

import c.f.b.k;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.ak;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "mt")
public class RegexMatchesOutputBase {
    private final d regexMatches;

    public RegexMatchesOutputBase(d dVar) {
        k.b(dVar, "regexMatches");
        this.regexMatches = dVar;
    }

    public final d getRegexMatches() {
        return this.regexMatches;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.can_also_be_accessed_as_array, labelResId = R.string.pl_match, name = "match")
    public final String getMatch() {
        return this.regexMatches.e();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.is_match_explained, labelResId = R.string.is_match, name = "match_found")
    public final boolean isMatch() {
        return ak.z(this.regexMatches.e());
    }
}

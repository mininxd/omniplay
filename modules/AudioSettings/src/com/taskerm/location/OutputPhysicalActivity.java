package com.joaomgcd.taskerm.location;

import android.content.Context;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.be;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "pa")
public final class OutputPhysicalActivity {
    private final int id;
    private final String type;

    public OutputPhysicalActivity(Context context, o oVar) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(oVar, "type");
        this.id = oVar.a();
        this.type = aj.a(oVar.b(), context, new Object[0]);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.physical_activity_id_description, labelResId = R.string.word_id, name = be.EXTRA_ID)
    public final int getId() {
        return this.id;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.physical_activity_type, labelResId = R.string.word_type, name = "type")
    public final String getType() {
        return this.type;
    }
}

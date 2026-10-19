package com.joaomgcd.taskerm.event.phone;

import android.content.Context;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import cyanogenmod.app.ProfileManager;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.cd;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cs")
public final class OutputCallScreened {
    static final /* synthetic */ g[] $$delegatedProperties = {x.a(new v(x.a(OutputCallScreened.class), ProfileManager.EXTRA_PROFILE_NAME, "getName()Ljava/lang/String;"))};
    private final String capabilities;
    private final Boolean incoming;
    private final c.e name$delegate;
    private final String number;
    private final String properties;

    @TaskerOutputVariable(labelResId = R.string.test_phone_name, name = ProfileManager.EXTRA_PROFILE_NAME)
    public final String getName() {
        c.e eVar = this.name$delegate;
        g gVar = $$delegatedProperties[0];
        return (String) eVar.b();
    }

    public OutputCallScreened(Context context, String str, Boolean bool, String str2, String str3) {
        k.b(context, "context");
        this.number = str;
        this.incoming = bool;
        this.capabilities = str2;
        this.properties = str3;
        this.name$delegate = f.a(new a(context));
    }

    public /* synthetic */ OutputCallScreened(Context context, String str, Boolean bool, String str2, String str3, int i, c.f.b.g gVar) {
        this(context, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (Boolean) null : bool, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (String) null : str3);
    }

    @TaskerOutputVariable(labelResId = R.string.pl_phone_number, name = "number")
    public final String getNumber() {
        return this.number;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.phone_call_incoming_description, labelResId = R.string.ml_incoming, name = "incoming")
    public final Boolean getIncoming() {
        return this.incoming;
    }

    @TaskerOutputVariable(labelResId = R.string.pl_capabilities, name = "capabilities")
    public final String getCapabilities() {
        return this.capabilities;
    }

    @TaskerOutputVariable(labelResId = R.string.button_label_properties, name = "properties")
    public final String getProperties() {
        return this.properties;
    }

    static final class a extends l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f6708b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context) {
            super(0);
            this.f6708b = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String number = OutputCallScreened.this.getNumber();
            if ((number == null || number.length() == 0) || k.a((Object) OutputCallScreened.this.getNumber(), (Object) "0")) {
                return null;
            }
            return cd.b(this.f6708b, OutputCallScreened.this.getNumber(), "display_name");
        }
    }
}

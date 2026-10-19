package com.joaomgcd.taskerm.function;

import c.e;
import c.f;
import c.f.b.k;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import cyanogenmod.app.ProfileManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class FunctionArgs {
    static final /* synthetic */ g[] $$delegatedProperties = {x.a(new v(x.a(FunctionArgs.class), "functionTextForToString", "getFunctionTextForToString()Ljava/lang/String;")), x.a(new v(x.a(FunctionArgs.class), "functionText", "getFunctionText()Ljava/lang/String;")), x.a(new v(x.a(FunctionArgs.class), "trimmedParameters", "getTrimmedParameters()[Ljava/lang/String;"))};
    public static final Companion Companion = new Companion(null);
    private final String component;
    private final e functionText$delegate;
    private final e functionTextForToString$delegate;
    private final boolean hasParenthesis;
    private final String name;
    private final String[] parameters;
    private final e trimmedParameters$delegate;

    public static final String replaceSpecialCharactersForInput(String str) {
        return Companion.replaceSpecialCharactersForInput(str);
    }

    public final String getFunctionText() {
        e eVar = this.functionText$delegate;
        g gVar = $$delegatedProperties[1];
        return (String) eVar.b();
    }

    public final String getFunctionTextForToString() {
        e eVar = this.functionTextForToString$delegate;
        g gVar = $$delegatedProperties[0];
        return (String) eVar.b();
    }

    public final String[] getTrimmedParameters() {
        e eVar = this.trimmedParameters$delegate;
        g gVar = $$delegatedProperties[2];
        return (String[]) eVar.b();
    }

    public FunctionArgs(String str, String str2, String[] strArr, boolean z) {
        k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
        k.b(strArr, "parameters");
        this.name = str;
        this.component = str2;
        this.parameters = strArr;
        this.hasParenthesis = z;
        this.functionTextForToString$delegate = f.a(new FunctionArgs$functionTextForToString$2(this));
        this.functionText$delegate = f.a(new FunctionArgs$functionText$2(this));
        this.trimmedParameters$delegate = f.a(new FunctionArgs$trimmedParameters$2(this));
    }

    public /* synthetic */ FunctionArgs(String str, String str2, String[] strArr, boolean z, int i, c.f.b.g gVar) {
        this(str, str2, strArr, (i & 8) != 0 ? true : z);
    }

    public final String getComponent() {
        return this.component;
    }

    public final boolean getHasParenthesis() {
        return this.hasParenthesis;
    }

    public final String getName() {
        return this.name;
    }

    public final String[] getParameters() {
        return this.parameters;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(this.hasParenthesis ? "(" : "");
        sb.append(getFunctionTextForToString());
        sb.append(this.hasParenthesis ? ")" : "");
        String string = sb.toString();
        if (this.component == null) {
            return string;
        }
        return this.component + '.' + string;
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(c.f.b.g gVar) {
            this();
        }

        public final String replaceSpecialCharactersForInput(String str) {
            k.b(str, "functionText");
            ReplaceSpecialMode replaceSpecialMode = ReplaceSpecialMode.ToString;
            c.k[] specials = FunctionBaseKt.getSpecials();
            return FunctionBaseKt.replaceSpecialStuff(str, replaceSpecialMode, (c.k[]) Arrays.copyOf(specials, specials.length));
        }
    }
}

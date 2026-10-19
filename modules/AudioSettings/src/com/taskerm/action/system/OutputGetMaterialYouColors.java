package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "my")
public final class OutputGetMaterialYouColors {
    private final Context context;
    private final boolean withHashTags;

    public OutputGetMaterialYouColors(Context context, boolean z) {
        c.f.b.k.b(context, "context");
        this.context = context;
        this.withHashTags = z;
    }

    public final Context getContext() {
        return this.context;
    }

    public final boolean getWithHashTags() {
        return this.withHashTags;
    }

    private final String getHex(int i) {
        return com.joaomgcd.taskerm.util.aj.a(i, this.context, this.withHashTags);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_0_description, labelResId = R.string.material_you_accent1_0, name = "accent1_0")
    public final String getAccent1_0() {
        return getHex(android.R.color.system_accent1_0);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_10_description, labelResId = R.string.material_you_accent1_10, name = "accent1_10")
    public final String getAccent1_10() {
        return getHex(android.R.color.system_accent1_10);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_50_description, labelResId = R.string.material_you_accent1_50, name = "accent1_50")
    public final String getAccent1_50() {
        return getHex(android.R.color.system_accent1_50);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_100_description, labelResId = R.string.material_you_accent1_100, name = "accent1_100")
    public final String getAccent1_100() {
        return getHex(android.R.color.system_accent1_100);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_200_description, labelResId = R.string.material_you_accent1_200, name = "accent1_200")
    public final String getAccent1_200() {
        return getHex(android.R.color.system_accent1_200);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_300_description, labelResId = R.string.material_you_accent1_300, name = "accent1_300")
    public final String getAccent1_300() {
        return getHex(android.R.color.system_accent1_300);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_400_description, labelResId = R.string.material_you_accent1_400, name = "accent1_400")
    public final String getAccent1_400() {
        return getHex(android.R.color.system_accent1_400);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_500_description, labelResId = R.string.material_you_accent1_500, name = "accent1_500")
    public final String getAccent1_500() {
        return getHex(android.R.color.system_accent1_500);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_600_description, labelResId = R.string.material_you_accent1_600, name = "accent1_600")
    public final String getAccent1_600() {
        return getHex(android.R.color.system_accent1_600);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_700_description, labelResId = R.string.material_you_accent1_700, name = "accent1_700")
    public final String getAccent1_700() {
        return getHex(android.R.color.system_accent1_700);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_800_description, labelResId = R.string.material_you_accent1_800, name = "accent1_800")
    public final String getAccent1_800() {
        return getHex(android.R.color.system_accent1_800);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_900_description, labelResId = R.string.material_you_accent1_900, name = "accent1_900")
    public final String getAccent1_900() {
        return getHex(android.R.color.system_accent1_900);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent1_1000_description, labelResId = R.string.material_you_accent1_1000, name = "accent1_1000")
    public final String getAccent1_1000() {
        return getHex(android.R.color.system_accent1_1000);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_0_description, labelResId = R.string.material_you_accent2_0, name = "accent2_0")
    public final String getAccent2_0() {
        return getHex(android.R.color.system_accent2_0);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_10_description, labelResId = R.string.material_you_accent2_10, name = "accent2_10")
    public final String getAccent2_10() {
        return getHex(android.R.color.system_accent2_10);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_50_description, labelResId = R.string.material_you_accent2_50, name = "accent2_50")
    public final String getAccent2_50() {
        return getHex(android.R.color.system_accent2_50);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_100_description, labelResId = R.string.material_you_accent2_100, name = "accent2_100")
    public final String getAccent2_100() {
        return getHex(android.R.color.system_accent2_100);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_200_description, labelResId = R.string.material_you_accent2_200, name = "accent2_200")
    public final String getAccent2_200() {
        return getHex(android.R.color.system_accent2_200);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_300_description, labelResId = R.string.material_you_accent2_300, name = "accent2_300")
    public final String getAccent2_300() {
        return getHex(android.R.color.system_accent2_300);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_400_description, labelResId = R.string.material_you_accent2_400, name = "accent2_400")
    public final String getAccent2_400() {
        return getHex(android.R.color.system_accent2_400);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_500_description, labelResId = R.string.material_you_accent2_500, name = "accent2_500")
    public final String getAccent2_500() {
        return getHex(android.R.color.system_accent2_500);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_600_description, labelResId = R.string.material_you_accent2_600, name = "accent2_600")
    public final String getAccent2_600() {
        return getHex(android.R.color.system_accent2_600);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_700_description, labelResId = R.string.material_you_accent2_700, name = "accent2_700")
    public final String getAccent2_700() {
        return getHex(android.R.color.system_accent2_700);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_800_description, labelResId = R.string.material_you_accent2_800, name = "accent2_800")
    public final String getAccent2_800() {
        return getHex(android.R.color.system_accent2_800);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_900_description, labelResId = R.string.material_you_accent2_900, name = "accent2_900")
    public final String getAccent2_900() {
        return getHex(android.R.color.system_accent2_900);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent2_1000_description, labelResId = R.string.material_you_accent2_1000, name = "accent2_1000")
    public final String getAccent2_1000() {
        return getHex(android.R.color.system_accent2_1000);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_0_description, labelResId = R.string.material_you_accent3_0, name = "accent3_0")
    public final String getAccent3_0() {
        return getHex(android.R.color.system_accent3_0);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_10_description, labelResId = R.string.material_you_accent3_10, name = "accent3_10")
    public final String getAccent3_10() {
        return getHex(android.R.color.system_accent3_10);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_50_description, labelResId = R.string.material_you_accent3_50, name = "accent3_50")
    public final String getAccent3_50() {
        return getHex(android.R.color.system_accent3_50);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_100_description, labelResId = R.string.material_you_accent3_100, name = "accent3_100")
    public final String getAccent3_100() {
        return getHex(android.R.color.system_accent3_100);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_200_description, labelResId = R.string.material_you_accent3_200, name = "accent3_200")
    public final String getAccent3_200() {
        return getHex(android.R.color.system_accent3_200);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_300_description, labelResId = R.string.material_you_accent3_300, name = "accent3_300")
    public final String getAccent3_300() {
        return getHex(android.R.color.system_accent3_300);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_400_description, labelResId = R.string.material_you_accent3_400, name = "accent3_400")
    public final String getAccent3_400() {
        return getHex(android.R.color.system_accent3_400);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_500_description, labelResId = R.string.material_you_accent3_500, name = "accent3_500")
    public final String getAccent3_500() {
        return getHex(android.R.color.system_accent3_500);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_600_description, labelResId = R.string.material_you_accent3_600, name = "accent3_600")
    public final String getAccent3_600() {
        return getHex(android.R.color.system_accent3_600);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_700_description, labelResId = R.string.material_you_accent3_700, name = "accent3_700")
    public final String getAccent3_700() {
        return getHex(android.R.color.system_accent3_700);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_800_description, labelResId = R.string.material_you_accent3_800, name = "accent3_800")
    public final String getAccent3_800() {
        return getHex(android.R.color.system_accent3_800);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_900_description, labelResId = R.string.material_you_accent3_900, name = "accent3_900")
    public final String getAccent3_900() {
        return getHex(android.R.color.system_accent3_900);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_accent3_1000_description, labelResId = R.string.material_you_accent3_1000, name = "accent3_1000")
    public final String getAccent3_1000() {
        return getHex(android.R.color.system_accent3_1000);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_0_description, labelResId = R.string.material_you_neutral1_0, name = "neutral1_0")
    public final String getNeutral1_0() {
        return getHex(android.R.color.system_neutral1_0);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_10_description, labelResId = R.string.material_you_neutral1_10, name = "neutral1_10")
    public final String getNeutral1_10() {
        return getHex(android.R.color.system_neutral1_10);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_50_description, labelResId = R.string.material_you_neutral1_50, name = "neutral1_50")
    public final String getNeutral1_50() {
        return getHex(android.R.color.system_neutral1_50);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_100_description, labelResId = R.string.material_you_neutral1_100, name = "neutral1_100")
    public final String getNeutral1_100() {
        return getHex(android.R.color.system_neutral1_100);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_200_description, labelResId = R.string.material_you_neutral1_200, name = "neutral1_200")
    public final String getNeutral1_200() {
        return getHex(android.R.color.system_neutral1_200);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_300_description, labelResId = R.string.material_you_neutral1_300, name = "neutral1_300")
    public final String getNeutral1_300() {
        return getHex(android.R.color.system_neutral1_300);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_400_description, labelResId = R.string.material_you_neutral1_400, name = "neutral1_400")
    public final String getNeutral1_400() {
        return getHex(android.R.color.system_neutral1_400);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_500_description, labelResId = R.string.material_you_neutral1_500, name = "neutral1_500")
    public final String getNeutral1_500() {
        return getHex(android.R.color.system_neutral1_500);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_600_description, labelResId = R.string.material_you_neutral1_600, name = "neutral1_600")
    public final String getNeutral1_600() {
        return getHex(android.R.color.system_neutral1_600);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_700_description, labelResId = R.string.material_you_neutral1_700, name = "neutral1_700")
    public final String getNeutral1_700() {
        return getHex(android.R.color.system_neutral1_700);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_800_description, labelResId = R.string.material_you_neutral1_800, name = "neutral1_800")
    public final String getNeutral1_800() {
        return getHex(android.R.color.system_neutral1_800);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_900_description, labelResId = R.string.material_you_neutral1_900, name = "neutral1_900")
    public final String getNeutral1_900() {
        return getHex(android.R.color.system_neutral1_900);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral1_1000_description, labelResId = R.string.material_you_neutral1_1000, name = "neutral1_1000")
    public final String getNeutral1_1000() {
        return getHex(android.R.color.system_neutral1_1000);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_0_description, labelResId = R.string.material_you_neutral2_0, name = "neutral2_0")
    public final String getNeutral2_0() {
        return getHex(android.R.color.system_neutral2_0);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_10_description, labelResId = R.string.material_you_neutral2_10, name = "neutral2_10")
    public final String getNeutral2_10() {
        return getHex(android.R.color.system_neutral2_10);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_50_description, labelResId = R.string.material_you_neutral2_50, name = "neutral2_50")
    public final String getNeutral2_50() {
        return getHex(android.R.color.system_neutral2_50);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_100_description, labelResId = R.string.material_you_neutral2_100, name = "neutral2_100")
    public final String getNeutral2_100() {
        return getHex(android.R.color.system_neutral2_100);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_200_description, labelResId = R.string.material_you_neutral2_200, name = "neutral2_200")
    public final String getNeutral2_200() {
        return getHex(android.R.color.system_neutral2_200);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_300_description, labelResId = R.string.material_you_neutral2_300, name = "neutral2_300")
    public final String getNeutral2_300() {
        return getHex(android.R.color.system_neutral2_300);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_400_description, labelResId = R.string.material_you_neutral2_400, name = "neutral2_400")
    public final String getNeutral2_400() {
        return getHex(android.R.color.system_neutral2_400);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_500_description, labelResId = R.string.material_you_neutral2_500, name = "neutral2_500")
    public final String getNeutral2_500() {
        return getHex(android.R.color.system_neutral2_500);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_600_description, labelResId = R.string.material_you_neutral2_600, name = "neutral2_600")
    public final String getNeutral2_600() {
        return getHex(android.R.color.system_neutral2_600);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_700_description, labelResId = R.string.material_you_neutral2_700, name = "neutral2_700")
    public final String getNeutral2_700() {
        return getHex(android.R.color.system_neutral2_700);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_800_description, labelResId = R.string.material_you_neutral2_800, name = "neutral2_800")
    public final String getNeutral2_800() {
        return getHex(android.R.color.system_neutral2_800);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_900_description, labelResId = R.string.material_you_neutral2_900, name = "neutral2_900")
    public final String getNeutral2_900() {
        return getHex(android.R.color.system_neutral2_900);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.material_you_neutral2_1000_description, labelResId = R.string.material_you_neutral2_1000, name = "neutral2_1000")
    public final String getNeutral2_1000() {
        return getHex(android.R.color.system_neutral2_1000);
    }
}

package com.joaomgcd.taskerm.action.input;

import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "pc")
public final class OutputGetImagePixelColors {
    private final String color;
    private final String[] colors;

    public OutputGetImagePixelColors(String str, String[] strArr) {
        this.color = str;
        this.colors = strArr;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.pixel_color_description, labelResId = R.string.pixel_color, name = "color")
    public final String getColor() {
        return this.color;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.all_pixel_colors_description, labelResId = R.string.all_pixel_colors, name = "colors")
    public final String[] getColors() {
        return this.colors;
    }
}

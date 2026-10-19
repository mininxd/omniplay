package com.joaomgcd.taskerm.google.vision;

import c.f.b.g;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cv")
public abstract class OutputCloudVision<T> {
    private String imageSize;

    /* JADX WARN: Multi-variable type inference failed */
    public OutputCloudVision() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public abstract T[] getItems();

    public OutputCloudVision(String str) {
        this.imageSize = str;
    }

    public /* synthetic */ OutputCloudVision(String str, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str);
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.google_cloud_vision_image_size_description, labelResId = R.string.google_cloud_vision_image_size, name = "image_size")
    public final String getImageSize() {
        return this.imageSize;
    }

    public final void setImageSize(String str) {
        this.imageSize = str;
    }
}

package com.joaomgcd.taskerm.action.system;

import android.net.Uri;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import java.util.HashMap;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "cl")
public final class OutputGetClipboard {
    private final String extras;
    private final String htmlText;
    private final String imageUri;
    private final String[] mimetypes;
    private final String text;
    private final String uri;

    public OutputGetClipboard(String str, String str2, String str3, String str4, String[] strArr, String str5) {
        this.text = str;
        this.htmlText = str2;
        this.uri = str3;
        this.imageUri = str4;
        this.mimetypes = strArr;
        this.extras = str5;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.current_clipboard_text, labelResId = R.string.pl_text, name = "text")
    public final String getText() {
        return this.text;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.current_clipboard_html, labelResId = R.string.pl_html_text, name = "html_text")
    public final String getHtmlText() {
        return this.htmlText;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.current_clipboard_uri, labelResId = R.string.pl_uri, name = "uri")
    public final String getUri() {
        return this.uri;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.current_clipboard_image, labelResId = R.string.pl_image_uri, name = "image_uri")
    public final String getImageUri() {
        return this.imageUri;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.current_clipboard_mimetypes, labelResId = R.string.pl_mime_types, name = "mimetypes")
    public final String[] getMimetypes() {
        return this.mimetypes;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.current_clipboard_extras, labelResId = R.string.pl_extras, name = "extras")
    public final String getExtras() {
        return this.extras;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OutputGetClipboard(com.joaomgcd.taskerm.e.a aVar) {
        String[] strArr;
        c.f.b.k.b(aVar, "clipboardContents");
        String strB = aVar.b();
        String strD = aVar.d();
        Uri uriC = aVar.c();
        String string = uriC != null ? uriC.toString() : null;
        Uri uriF = aVar.f();
        String string2 = uriF != null ? uriF.toString() : null;
        List<String> listE = aVar.e();
        if (listE != null) {
            Object[] array = listE.toArray(new String[0]);
            if (array == null) {
                throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        } else {
            strArr = null;
        }
        HashMap<String, Object> mapG = aVar.g();
        this(strB, strD, string, string2, strArr, mapG != null ? com.joaomgcd.taskerm.util.aj.a((Object) mapG) : null);
    }
}

package com.joaomgcd.taskerm.util;

import java.nio.charset.Charset;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class da extends cx<String> {
    @Override // com.joaomgcd.taskerm.util.cx
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String b(byte[] bArr, String str) {
        c.f.b.k.b(bArr, "receiver$0");
        c.f.b.k.b(str, "contentType");
        Charset charsetP = gr.p(str);
        c.f.b.k.a((Object) charsetP, "Utils.contentTypeToCharset(contentType)");
        return new String(bArr, charsetP);
    }
}

package com.joaomgcd.taskerm.net;

import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.ca;

/* JADX INFO: loaded from: classes.dex */
public class d extends e<String, ad> {
    public /* synthetic */ d(String str, String str2, b.a.l lVar, ca caVar, int i, c.f.b.g gVar) {
        this(str, str2, (i & 4) != 0 ? (b.a.l) null : lVar, (i & 8) != 0 ? (ca) null : caVar);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(String str, String str2, b.a.l<String> lVar, ca caVar) {
        super(str, str2, "POST", String.class, ad.class, lVar, caVar, null);
        c.f.b.k.b(str, "body");
        c.f.b.k.b(str2, "url");
    }
}

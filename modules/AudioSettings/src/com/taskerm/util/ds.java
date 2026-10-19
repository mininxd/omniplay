package com.joaomgcd.taskerm.util;

import android.content.res.XmlResourceParser;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ds {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c.f.a.a<XmlResourceParser> f9319a;

    /* JADX WARN: Multi-variable type inference failed */
    public ds(c.f.a.a<? extends XmlResourceParser> aVar) {
        c.f.b.k.b(aVar, "parserGetter");
        this.f9319a = aVar;
    }

    public final HashMap<String, String> a(String str) {
        c.f.b.k.b(str, "tag");
        HashMap<String, String> map = new HashMap<>();
        XmlResourceParser xmlResourceParserInvoke = this.f9319a.invoke();
        int eventType = xmlResourceParserInvoke.getEventType();
        while (eventType != 1) {
            if (true ^ c.f.b.k.a((Object) xmlResourceParserInvoke.getName(), (Object) str)) {
                xmlResourceParserInvoke.next();
            } else {
                int attributeCount = xmlResourceParserInvoke.getAttributeCount();
                for (int i = 0; i < attributeCount; i++) {
                    map.put(xmlResourceParserInvoke.getAttributeName(i), xmlResourceParserInvoke.getAttributeValue(i));
                }
                return map;
            }
        }
        return map;
    }
}

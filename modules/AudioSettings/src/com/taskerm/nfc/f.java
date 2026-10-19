package com.joaomgcd.taskerm.nfc;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public enum f {
    Text,
    Uri,
    Application;


    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f8021d = new a(null);

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final f a(String str) {
            String str2 = str;
            if (str2 == null || str2.length() == 0) {
                return f.Text;
            }
            try {
                return f.valueOf(str);
            } catch (Exception unused) {
                return f.Text;
            }
        }
    }
}

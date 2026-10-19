package com.joaomgcd.taskerm.google;

import android.content.Context;
import c.f.a.b;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.retrofit.ErrorTaskerServer;
import com.joaomgcd.taskerm.retrofit.e;
import com.joaomgcd.taskerm.signin.c;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final <T> T a(Context context, Class<T> cls, String str, String str2, String[] strArr) {
        k.b(context, "context");
        k.b(cls, "apiClass");
        k.b(str, "baseUrl");
        k.b(str2, "account");
        k.b(strArr, "scopes");
        return new e(cls, str, ErrorTaskerServer.class, C0155a.f6981a, c.f8605a.b(context, str2, true, strArr), null, null, 96, null).a();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.google.a$a, reason: collision with other inner class name */
    static final class C0155a extends l implements b<ErrorTaskerServer, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0155a f6981a = new C0155a();

        C0155a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(ErrorTaskerServer errorTaskerServer) {
            k.b(errorTaskerServer, "it");
            return errorTaskerServer.getErrorMessage();
        }
    }
}

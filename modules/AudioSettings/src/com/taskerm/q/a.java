package com.joaomgcd.taskerm.q;

import c.f.b.k;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0223a f8338a = new C0223a(null);

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.q.a$a, reason: collision with other inner class name */
    public static final class C0223a {
        private C0223a() {
        }

        public /* synthetic */ C0223a(c.f.b.g gVar) {
            this();
        }

        public final String a(c.j.g<?> gVar, String str, String str2) {
            k.b(gVar, "property");
            k.b(str, "suffix");
            return a(gVar.f(), str, str2);
        }

        public final String a(String str, String str2, String str3) {
            k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
            k.b(str2, "suffix");
            if (str3 != null) {
                return str3;
            }
            return str + str2;
        }
    }
}

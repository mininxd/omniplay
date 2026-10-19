package com.joaomgcd.taskerm.b;

import android.app.Activity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5886a = a.f5887a;

    b.a.l<String> a(Activity activity, int i, String str, boolean z, boolean z2);

    com.joaomgcd.taskerm.util.i b();

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ a f5887a = new a();

        private a() {
        }

        public final ArrayList<c> a() {
            ArrayList<c> arrayListD = c.a.j.d(n.f5931a, j.f5908a);
            arrayListD.addAll(new h());
            return arrayListD;
        }
    }
}

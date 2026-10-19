package com.joaomgcd.taskerm.j;

import android.content.Context;
import c.f.b.k;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public interface d extends Comparable<d> {
    boolean a();

    boolean a(Context context);

    boolean a(Context context, boolean z);

    OutputStream b(String str, boolean z);

    boolean b();

    String c();

    long d();

    d f();

    File g();

    boolean h();

    InputStream j();

    boolean k();

    File l();

    String m();

    InputStream o();

    public static final class a {
        public static boolean a(d dVar) {
            return dVar.h();
        }

        public static File b(d dVar) {
            return dVar.g().getParentFile();
        }

        public static String c(d dVar) {
            return c.e.c.a(dVar.g());
        }

        public static int a(d dVar, d dVar2) {
            k.b(dVar2, "other");
            return dVar.g().compareTo(dVar2.g());
        }

        public static String d(d dVar) {
            String string = dVar.g().toString();
            k.a((Object) string, "rawFile.toString()");
            return string;
        }

        public static InputStream e(d dVar) {
            InputStream inputStreamJ = dVar.j();
            if (inputStreamJ != null) {
                return inputStreamJ;
            }
            throw new RuntimeException("Couldn't get input stream from " + dVar.g());
        }

        public static boolean a(d dVar, Context context) {
            k.b(context, "context");
            return dVar.a(context, true);
        }
    }
}

package com.joaomgcd.taskerm.u;

import android.content.Context;
import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.l.d;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class c extends com.joaomgcd.taskerm.u.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f8816a = {x.a(new v(x.a(c.class), "linesSpecific", "getLinesSpecific()Ljava/util/ArrayList;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f8817b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f8818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private InputStream f8819d;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.joaomgcd.taskerm.u.a
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public ArrayList<String> a() {
        e eVar = this.f8817b;
        g gVar = f8816a[0];
        return (ArrayList) eVar.b();
    }

    static final class a extends l implements c.f.a.a<ArrayList<String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8820a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ArrayList<String> invoke() {
            return new ArrayList<>();
        }
    }

    public c(Context context, InputStream inputStream) {
        k.b(context, "context");
        k.b(inputStream, "inputStream");
        this.f8818c = context;
        this.f8819d = inputStream;
        this.f8817b = f.a(a.f8820a);
    }

    @Override // com.joaomgcd.taskerm.u.a
    public void d() {
        Reader inputStreamReader = new InputStreamReader(this.f8819d, d.f2083a);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return;
                } else {
                    a().add(line);
                }
            } catch (Exception e2) {
                bn.a("StreamLineReader", "Couldn't read whole stream", e2);
                return;
            } catch (OutOfMemoryError unused) {
                bn.d("StreamLineReader", "Couldn't read whole stream. Out of memory");
                a().removeAll(a());
                return;
            }
        }
    }
}

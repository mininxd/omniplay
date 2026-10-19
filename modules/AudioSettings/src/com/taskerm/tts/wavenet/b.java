package com.joaomgcd.taskerm.tts.wavenet;

import android.content.Context;
import c.f.b.k;
import c.q;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f8787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f8788b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f8789c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f8790d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final AudioEncoding f8791e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Integer f8792f;
    private final Float g;
    private final Float h;
    private final Integer i;

    public b(Context context, String str, e eVar, boolean z, AudioEncoding audioEncoding, Integer num, Float f2, Float f3, Integer num2) {
        k.b(context, "context");
        k.b(str, "text");
        k.b(eVar, "voice");
        k.b(audioEncoding, "encoding");
        this.f8787a = context;
        this.f8788b = str;
        this.f8789c = eVar;
        this.f8790d = z;
        this.f8791e = audioEncoding;
        this.f8792f = num;
        this.g = f2;
        this.h = f3;
        this.i = num2;
    }

    public final Context b() {
        return this.f8787a;
    }

    public final String c() {
        return this.f8788b;
    }

    public final boolean d() {
        return this.f8790d;
    }

    public /* synthetic */ b(Context context, String str, e eVar, boolean z, AudioEncoding audioEncoding, Integer num, Float f2, Float f3, Integer num2, int i, c.f.b.g gVar) {
        this(context, str, eVar, (i & 8) != 0 ? false : z, (i & 16) != 0 ? AudioEncoding.MP3 : audioEncoding, (i & 32) != 0 ? (Integer) null : num, (i & 64) != 0 ? (Float) null : f2, (i & 128) != 0 ? (Float) null : f3, (i & 256) != 0 ? (Integer) null : num2);
    }

    public final AudioEncoding e() {
        return this.f8791e;
    }

    public final Integer f() {
        return this.f8792f;
    }

    public final Float g() {
        return this.g;
    }

    public final Float h() {
        return this.h;
    }

    public final Integer i() {
        return this.i;
    }

    public final String a() {
        String strA;
        e eVar = this.f8789c;
        if (eVar instanceof d) {
            strA = ((d) this.f8789c).a().getName();
        } else {
            if (!(eVar instanceof f)) {
                throw new c.i();
            }
            strA = ((f) this.f8789c).a();
        }
        if (strA != null) {
            return strA;
        }
        throw new RuntimeException("Voice name must be provided to synthesize");
    }

    public int hashCode() {
        int iHashCode = this.f8788b.hashCode() ^ a().hashCode();
        Float f2 = this.g;
        int iHashCode2 = iHashCode ^ (f2 != null ? f2.hashCode() : 0);
        Float f3 = this.h;
        return iHashCode2 ^ (f3 != null ? f3.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!k.a(getClass(), obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            b bVar = (b) obj;
            return ((k.a((Object) this.f8788b, (Object) bVar.f8788b) ^ true) || (k.a((Object) a(), (Object) bVar.a()) ^ true) || (k.a(this.g, bVar.g) ^ true) || (k.a(this.h, bVar.h) ^ true)) ? false : true;
        }
        throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.tts.wavenet.InputSynthesize");
    }
}

package com.joaomgcd.taskerm.d;

import android.content.Context;
import android.media.AudioManager;
import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import com.joaomgcd.taskerm.util.aj;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6079a = {x.a(new v(x.a(a.class), "audioManager", "getAudioManager()Landroid/media/AudioManager;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f6080b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AudioManager.OnAudioFocusChangeListener f6081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f6082d;

    public final AudioManager a() {
        e eVar = this.f6080b;
        g gVar = f6079a[0];
        return (AudioManager) eVar.b();
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.d.a$a, reason: collision with other inner class name */
    static final class C0118a extends l implements c.f.a.a<AudioManager> {
        C0118a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final AudioManager invoke() {
            return (AudioManager) a.this.c().getSystemService("audio");
        }
    }

    public a(Context context) {
        k.b(context, "context");
        this.f6082d = context;
        this.f6080b = f.a(new C0118a());
    }

    public final Context c() {
        return this.f6082d;
    }

    static final class b implements AudioManager.OnAudioFocusChangeListener {
        b() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            if (aj.a(Integer.valueOf(i), -1, -2, -3)) {
                a.this.b();
            }
        }
    }

    public final b.a.b a(Integer num) {
        Integer numValueOf;
        b bVar = new b();
        AudioManager audioManagerA = a();
        if (audioManagerA != null) {
            numValueOf = Integer.valueOf(audioManagerA.requestAudioFocus(bVar, num != null ? num.intValue() : 3, 3));
        } else {
            numValueOf = null;
        }
        if (numValueOf != null && numValueOf.intValue() == 0) {
            b.a.b bVarA = b.a.b.a((Throwable) new RuntimeException("Audio Focus Request Failed"));
            k.a((Object) bVarA, "Completable.error(Runtim…o Focus Request Failed\"))");
            return bVarA;
        }
        this.f6081c = bVar;
        b.a.b bVarA2 = b.a.b.a();
        k.a((Object) bVarA2, "Completable.complete()");
        return bVarA2;
    }

    public final void b() {
        AudioManager audioManagerA;
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.f6081c;
        if (onAudioFocusChangeListener != null && (audioManagerA = a()) != null) {
            audioManagerA.abandonAudioFocus(onAudioFocusChangeListener);
        }
        this.f6081c = (AudioManager.OnAudioFocusChangeListener) null;
    }
}

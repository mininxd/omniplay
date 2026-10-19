package com.joaomgcd.taskerm.tts.wavenet;

import android.media.MediaPlayer;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dk;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8797a = {x.a(new v(x.a(g.class), "client", "getClient()Lcom/joaomgcd/taskerm/tts/wavenet/APIWaveNet;")), x.a(new v(x.a(g.class), "mediaPlayer", "getMediaPlayer()Landroid/media/MediaPlayer;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f8798b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8799c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f8800d;

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.tts.wavenet.a d() {
        c.e eVar = this.f8798b;
        c.j.g gVar = f8797a[0];
        return (com.joaomgcd.taskerm.tts.wavenet.a) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaPlayer e() {
        c.e eVar = this.f8799c;
        c.j.g gVar = f8797a[1];
        return (MediaPlayer) eVar.b();
    }

    static final class e extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.tts.wavenet.b f8807b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(com.joaomgcd.taskerm.tts.wavenet.b bVar) {
            super(0);
            this.f8807b = bVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() throws IOException {
            a();
            return s.f2131a;
        }

        public final void a() throws IOException {
            String str = (String) g.this.c(this.f8807b).b();
            MediaPlayer mediaPlayerE = g.this.e();
            k.a((Object) str, "base64");
            aj.a(mediaPlayerE, str, this.f8807b.i());
            aj.a(g.this.e()).b();
        }
    }

    static final class f extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.tts.wavenet.c f8809b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(com.joaomgcd.taskerm.tts.wavenet.c cVar) {
            super(0);
            this.f8809b = cVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            g gVar = g.this;
            Object objB = g.this.c(this.f8809b.b()).b();
            k.a(objB, "synthesizeBase64(inputFi…Synthesize).blockingGet()");
            gVar.a((String) objB, this.f8809b.a());
        }
    }

    static final class a extends l implements c.f.a.a<com.joaomgcd.taskerm.tts.wavenet.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8801a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.tts.wavenet.a invoke() {
            return com.joaomgcd.taskerm.tts.wavenet.a.f8783a.a();
        }
    }

    public g(String str) {
        k.b(str, "apiKey");
        this.f8800d = str;
        this.f8798b = c.f.a(a.f8801a);
        this.f8799c = c.f.a(c.f8803a);
    }

    public final String c() {
        return this.f8800d;
    }

    static final class c extends l implements c.f.a.a<MediaPlayer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8803a = new c();

        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MediaPlayer invoke() {
            return new MediaPlayer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str, File file) {
        byte[] bArrO = ak.o(str);
        k.a((Object) bArrO, "bytes");
        c.e.c.a(file, bArrO);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File b(com.joaomgcd.taskerm.tts.wavenet.b bVar) {
        return dk.a(bVar.b(), String.valueOf(bVar.hashCode()), (String) null, 2, (Object) null);
    }

    static final class d extends l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.tts.wavenet.b f8805b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(com.joaomgcd.taskerm.tts.wavenet.b bVar) {
            super(0);
            this.f8805b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            c.k kVar;
            if (this.f8805b.d()) {
                File fileB = g.this.b(this.f8805b);
                if (fileB.exists() && fileB.length() > 0) {
                    return dk.e(fileB);
                }
            }
            if (n.a(this.f8805b.c(), "<speak", false, 2, (Object) null)) {
                kVar = new c.k(null, this.f8805b.c());
            } else {
                kVar = new c.k(this.f8805b.c(), null);
            }
            String str = (String) kVar.c();
            String str2 = (String) kVar.d();
            String strA = this.f8805b.a();
            String languageCodeFromName = new Voice(null, strA, null, 4, null).getLanguageCodeFromName();
            String audioContent = g.this.d().a(g.this.c(), new RequestSynthesize(new AudioConfig(this.f8805b.e(), this.f8805b.g(), this.f8805b.h(), null, this.f8805b.f(), 8, null), new Input(str, str2), new Voice(languageCodeFromName, strA, null, 4, null))).b().getAudioContent();
            if (audioContent != null) {
                if (this.f8805b.d()) {
                    g.this.a(audioContent, g.this.b(this.f8805b));
                }
                if (audioContent != null) {
                    return audioContent;
                }
            }
            throw new RuntimeException("Couldn't get Wavenet Response");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<String> c(com.joaomgcd.taskerm.tts.wavenet.b bVar) {
        return com.joaomgcd.taskerm.rx.i.b(new d(bVar));
    }

    public final b.a.l<cq<Boolean, ErrorWaveNet>> a(com.joaomgcd.taskerm.tts.wavenet.b bVar) {
        k.b(bVar, "input");
        return h.b(com.joaomgcd.taskerm.rx.i.d(new e(bVar)));
    }

    public final void a() {
        try {
            MediaPlayer mediaPlayerE = e();
            mediaPlayerE.reset();
            mediaPlayerE.stop();
            mediaPlayerE.release();
        } catch (Exception e2) {
            bn.a("WaveNet", "Error shutting up wavenet", (Throwable) e2);
        }
    }

    public final b.a.l<cq<Boolean, ErrorWaveNet>> a(com.joaomgcd.taskerm.tts.wavenet.c cVar) {
        k.b(cVar, "inputFile");
        return h.b(com.joaomgcd.taskerm.rx.i.d(new f(cVar)));
    }

    static final class b extends l implements c.f.a.a<j> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final j invoke() {
            Voice[] voices = g.this.d().a(g.this.c()).b().getVoices();
            if (voices == null) {
                throw new RuntimeException("No voices returned from WaveNet");
            }
            ArrayList arrayList = new ArrayList();
            for (Voice voice : voices) {
                if (voice.isWaveNet()) {
                    arrayList.add(voice);
                }
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : arrayList) {
                String languageCodeFromName = ((Voice) obj).getLanguageCodeFromName();
                Object arrayList2 = linkedHashMap.get(languageCodeFromName);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    linkedHashMap.put(languageCodeFromName, arrayList2);
                }
                ((List) arrayList2).add(obj);
            }
            ArrayList arrayList3 = new ArrayList(linkedHashMap.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new RuntimeException("Voice must have language code");
                }
                Object[] array = ((Collection) entry.getValue()).toArray(new Voice[0]);
                if (array != null) {
                    arrayList3.add(new i(str, (Voice[]) array));
                } else {
                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            j jVar = new j();
            jVar.addAll(arrayList3);
            return jVar;
        }
    }

    public final b.a.l<cq<j, ErrorWaveNet>> b() {
        return h.b(com.joaomgcd.taskerm.rx.i.b(new b()));
    }
}

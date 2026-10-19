package com.joaomgcd.taskerm.action.a;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import c.f.b.k;
import c.s;
import com.joaomgcd.taskerm.helper.actions.execute.l;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.tts.wavenet.ErrorWaveNet;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cq;
import java.io.File;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class f extends l<h> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f4926a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static com.joaomgcd.taskerm.tts.wavenet.g f4927b;

    public static final com.joaomgcd.taskerm.tts.wavenet.g j_() {
        a aVar = f4926a;
        return f4927b;
    }

    static final class b extends c.f.b.l implements c.f.a.b<cq<Boolean, ErrorWaveNet>, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f4928a = new b();

        b() {
            super(1);
        }

        public final void a(cq<Boolean, ErrorWaveNet> cqVar) {
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(cq<Boolean, ErrorWaveNet> cqVar) {
            a(cqVar);
            return s.f2131a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<h, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(cVar, "action");
        k.b(bundle, "taskVars");
        k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(h hVar) {
        b.a.l<cq<Boolean, ErrorWaveNet>> lVarA;
        com.joaomgcd.taskerm.d.a aVar;
        k.b(hVar, "input");
        com.joaomgcd.taskerm.tts.wavenet.g gVar = new com.joaomgcd.taskerm.tts.wavenet.g(a((com.joaomgcd.taskerm.action.h) hVar));
        f4927b = gVar;
        String text = hVar.getText();
        if (text == null) {
            return new cm("No text to say");
        }
        String voice = hVar.getVoice();
        if (voice == null) {
            return new cm("No voice set");
        }
        Integer speed = hVar.getSpeed();
        float fA = aj.a((speed != null ? speed.intValue() : 8.0f) / 8.0f, 0.25f, 4.0f);
        Integer pitch = hVar.getPitch();
        float fA2 = aj.a((pitch != null ? pitch.intValue() : 5.0f) - 20.0f, -20.0f, 20.0f);
        String file = hVar.getFile();
        Integer stream = hVar.getStream();
        com.joaomgcd.taskerm.tts.wavenet.b bVar = new com.joaomgcd.taskerm.tts.wavenet.b(h(), text, new com.joaomgcd.taskerm.tts.wavenet.f(voice), true, null, null, Float.valueOf(fA2), Float.valueOf(fA), stream, 48, null);
        if (file == null) {
            if (k.a((Object) hVar.getRespectAudioFocus(), (Object) true)) {
                aVar = new com.joaomgcd.taskerm.d.a(h());
                aVar.a(stream).b();
            } else {
                aVar = null;
            }
            lVarA = gVar.a(bVar).a(new c(aVar));
        } else {
            File fileA = h().a(file, i().n(), false, true, false);
            if (fileA != null) {
                k.a((Object) fileA, "service.getSDPath(filePa…ltError(\"File not valid\")");
                lVarA = gVar.a(new com.joaomgcd.taskerm.tts.wavenet.c(fileA, bVar));
            } else {
                return new cm("File not valid");
            }
        }
        b.a.l<cq<Boolean, ErrorWaveNet>> lVarA2 = lVarA.a(d.f4930a);
        if (k.a((Object) hVar.getContinueImmediately(), (Object) true)) {
            k.a((Object) lVarA2, "single");
            i.a(lVarA2, h(), b.f4928a);
            return new co();
        }
        cq<Boolean, ErrorWaveNet> cqVarB = lVarA2.b();
        k.a((Object) cqVarB, "single.blockingGet()");
        return cqVarB;
    }

    static final class c implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.d.a f4929a;

        c(com.joaomgcd.taskerm.d.a aVar) {
            this.f4929a = aVar;
        }

        @Override // b.a.d.a
        public final void run() {
            com.joaomgcd.taskerm.d.a aVar = this.f4929a;
            if (aVar != null) {
                aVar.b();
            }
        }
    }

    static final class d implements b.a.d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f4930a = new d();

        d() {
        }

        @Override // b.a.d.a
        public final void run() {
            f.f4926a.a((com.joaomgcd.taskerm.tts.wavenet.g) null);
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final void a(com.joaomgcd.taskerm.tts.wavenet.g gVar) {
            f.f4927b = gVar;
        }
    }
}

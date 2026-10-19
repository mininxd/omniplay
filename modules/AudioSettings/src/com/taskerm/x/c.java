package com.joaomgcd.taskerm.x;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import c.f.b.k;
import c.f.b.l;
import c.f.b.w;
import c.s;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ch;
import com.joaomgcd.taskerm.util.dm;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f9428a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.b<String, s> f9429b;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.x.c$c, reason: collision with other inner class name */
    public enum EnumC0252c {
        FreeForm,
        WebSearch
    }

    static final class d extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.d f9440b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ f f9441c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b f9442d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f9443e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(w.d dVar, f fVar, b bVar, b.a.k.d dVar2) {
            super(0);
            this.f9440b = dVar;
            this.f9441c = fVar;
            this.f9442d = bVar;
            this.f9443e = dVar2;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [T, android.speech.SpeechRecognizer] */
        public final void a() {
            w.d dVar = this.f9440b;
            ?? CreateSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(c.this.f9428a);
            CreateSpeechRecognizer.setRecognitionListener(this.f9441c);
            try {
                CreateSpeechRecognizer.startListening(this.f9442d.b());
            } catch (Throwable th) {
                i.a(this.f9443e, th);
            }
            dVar.f2028a = CreateSpeechRecognizer;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c(Context context, c.f.a.b<? super String, s> bVar) {
        k.b(context, "context");
        this.f9428a = context;
        this.f9429b = bVar;
    }

    public /* synthetic */ c(Context context, c.f.a.b bVar, int i, c.f.b.g gVar) {
        this(context, (i & 2) != 0 ? (c.f.a.b) null : bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str) {
        c.f.a.b<String, s> bVar = this.f9429b;
        if (bVar != null) {
            bVar.invoke(str);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f9431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f9432b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Integer f9433c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final EnumC0252c f9434d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final String f9435e;

        public b(Context context, String str, Integer num, EnumC0252c enumC0252c, String str2) {
            k.b(context, "context");
            this.f9431a = context;
            this.f9432b = str;
            this.f9433c = num;
            this.f9434d = enumC0252c;
            this.f9435e = str2;
        }

        public final Integer c() {
            return this.f9433c;
        }

        private final String a() {
            EnumC0252c enumC0252c = this.f9434d;
            if (enumC0252c == null) {
                return null;
            }
            switch (enumC0252c) {
                case FreeForm:
                    return "free_form";
                case WebSearch:
                    return "web_search";
                default:
                    throw new c.i();
            }
        }

        public final Intent b() {
            Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
            intent.putExtra("calling_package", this.f9431a.getPackageName());
            intent.putExtra("android.speech.extra.PROMPT", this.f9432b);
            intent.putExtra("android.speech.extra.MAX_RESULTS", this.f9433c);
            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", a());
            if (!TextUtils.isEmpty(this.f9435e)) {
                intent.putExtra("android.speech.extra.LANGUAGE", this.f9435e);
            }
            return intent;
        }
    }

    public static final class a extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final boolean f9430a;

        public final boolean a() {
            return this.f9430a;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, String str, Integer num, EnumC0252c enumC0252c, String str2, boolean z) {
            super(context, str, num, enumC0252c, str2);
            k.b(context, "context");
            this.f9430a = z;
        }
    }

    public final b.a.l<com.joaomgcd.taskerm.x.b> a(a aVar) {
        k.b(aVar, "args");
        if (aVar.a() || !new ch(this.f9428a).a()) {
            return b(aVar);
        }
        return a((b) aVar);
    }

    public final b.a.l<com.joaomgcd.taskerm.x.b> a(b bVar) {
        k.b(bVar, "args");
        b.a.l lVarC = aj.a(bVar.b(), this.f9428a).c(new g(bVar));
        k.a((Object) lVarC, "args.intent.startActivit…rgs.maxResults)\n        }");
        return lVarC;
    }

    static final class g<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f9452a;

        g(b bVar) {
            this.f9452a = bVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.x.b apply(Intent intent) {
            ArrayList<String> stringArrayListExtra;
            float[] floatArrayExtra;
            k.b(intent, "it");
            try {
                stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            } catch (Throwable unused) {
                stringArrayListExtra = null;
            }
            if (stringArrayListExtra == null) {
                throw new RuntimeException("Could not get text from speech: no results");
            }
            try {
                floatArrayExtra = intent.getFloatArrayExtra("android.speech.extra.CONFIDENCE_SCORES");
            } catch (Throwable unused2) {
                floatArrayExtra = null;
            }
            return com.joaomgcd.taskerm.x.e.b(stringArrayListExtra, floatArrayExtra, this.f9452a.c());
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, android.speech.SpeechRecognizer] */
    public final b.a.l<com.joaomgcd.taskerm.x.b> b(b bVar) {
        k.b(bVar, "args");
        b.a.k.d dVarF = b.a.k.d.f();
        k.a((Object) dVarF, "SingleSubject.create<RecognizedSpeeches>()");
        f fVar = new f(dm.f(), dVarF, bVar);
        w.d dVar = new w.d();
        dVar.f2028a = (SpeechRecognizer) 0;
        i.e(new d(dVar, fVar, bVar, dVarF));
        b.a.l lVarB = dVarF.b(new e(dVar));
        k.a((Object) lVarB, "result.doOnDispose {\n   …}\n            }\n        }");
        return lVarB;
    }

    public static final class f implements RecognitionListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f9447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f9448c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b f9449d;

        private final String a(int i) {
            switch (i) {
                case 1:
                    return "Network operation timed out";
                case 2:
                    return "Other network related errors";
                case 3:
                    return "Audio recording error";
                case 4:
                    return "Server sends error status";
                case 5:
                    return "Other client side errors";
                case 6:
                    return "No speech input";
                case 7:
                    return "No recognition result matched";
                case 8:
                    return "RecognitionService busy";
                case 9:
                    return "Insufficient permissions";
                default:
                    return "Unknown Error";
            }
        }

        @Override // android.speech.RecognitionListener
        public void onBufferReceived(byte[] bArr) {
        }

        @Override // android.speech.RecognitionListener
        public void onPartialResults(Bundle bundle) {
        }

        @Override // android.speech.RecognitionListener
        public void onRmsChanged(float f2) {
        }

        f(long j, b.a.k.d dVar, b bVar) {
            this.f9447b = j;
            this.f9448c = dVar;
            this.f9449d = bVar;
        }

        static final class b extends l implements c.f.a.a<ArrayList<String>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Bundle f9451a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Bundle bundle) {
                super(0);
                this.f9451a = bundle;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ArrayList<String> invoke() {
                Bundle bundle = this.f9451a;
                if (bundle != null) {
                    return bundle.getStringArrayList("results_recognition");
                }
                return null;
            }
        }

        static final class a extends l implements c.f.a.a<float[]> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Bundle f9450a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Bundle bundle) {
                super(0);
                this.f9450a = bundle;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final float[] invoke() {
                Bundle bundle = this.f9450a;
                if (bundle != null) {
                    return bundle.getFloatArray("confidence_scores");
                }
                return null;
            }
        }

        private final ArrayList<String> a(Bundle bundle) {
            return (ArrayList) aj.a((c.f.a.b) null, new b(bundle), 1, (Object) null);
        }

        private final float[] b(Bundle bundle) {
            return (float[]) aj.a((c.f.a.b) null, new a(bundle), 1, (Object) null);
        }

        @Override // android.speech.RecognitionListener
        public void onReadyForSpeech(Bundle bundle) {
            c.this.a("onReadyForSpeech");
        }

        @Override // android.speech.RecognitionListener
        public void onEvent(int i, Bundle bundle) {
            c.this.a("onEvent: " + i);
        }

        @Override // android.speech.RecognitionListener
        public void onBeginningOfSpeech() {
            c.this.a("onBeginningOfSpeech");
        }

        @Override // android.speech.RecognitionListener
        public void onEndOfSpeech() {
            c.this.a("onEndOfSpeech");
        }

        @Override // android.speech.RecognitionListener
        public void onError(int i) {
            c.this.a("Error: " + i);
            if (i == 7 && dm.f() - this.f9447b < 500) {
                c.this.a("ERROR_NO_MATCH too soon. Ignoring.");
                return;
            }
            i.a(this.f9448c, "Error recognizing speech: " + a(i));
        }

        @Override // android.speech.RecognitionListener
        public void onResults(Bundle bundle) {
            ArrayList<String> arrayListA = a(bundle);
            if (arrayListA != null) {
                this.f9448c.d_(com.joaomgcd.taskerm.x.e.b(arrayListA, b(bundle), this.f9449d.c()));
            } else {
                i.a(this.f9448c, "Could not get text from speech: no results");
            }
        }
    }

    static final class e implements b.a.d.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.d f9445b;

        e(w.d dVar) {
            this.f9445b = dVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.a
        public final void run() {
            SpeechRecognizer speechRecognizer = (SpeechRecognizer) this.f9445b.f2028a;
            if (speechRecognizer != null) {
                try {
                    speechRecognizer.setRecognitionListener(null);
                    speechRecognizer.stopListening();
                    speechRecognizer.cancel();
                    speechRecognizer.destroy();
                } catch (Throwable th) {
                    c.this.a("Error disposing speechRecognizer: " + th);
                }
            }
        }
    }
}

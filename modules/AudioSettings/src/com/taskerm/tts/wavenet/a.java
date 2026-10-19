package com.joaomgcd.taskerm.tts.wavenet;

import b.a.l;
import c.f.b.k;
import f.c.o;
import f.c.t;

/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0240a f8783a = C0240a.f8784a;

    @f.c.f(a = "voices")
    l<ResponseVoices> a(@t(a = "key") String str);

    @o(a = "./text:synthesize")
    l<ResponseSynthesize> a(@t(a = "key") String str, @f.c.a RequestSynthesize requestSynthesize);

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.tts.wavenet.a$a, reason: collision with other inner class name */
    public static final class C0240a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ C0240a f8784a = new C0240a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final a f8785b = (a) new com.joaomgcd.taskerm.retrofit.e(a.class, "https://texttospeech.googleapis.com/v1/", ErrorWaveNet.class, C0241a.f8786a, null, null, null, 112, null).a();

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.tts.wavenet.a$a$a, reason: collision with other inner class name */
        static final class C0241a extends c.f.b.l implements c.f.a.b<ErrorWaveNet, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0241a f8786a = new C0241a();

            C0241a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(ErrorWaveNet errorWaveNet) {
                k.b(errorWaveNet, "it");
                return errorWaveNet.getErrorMessage();
            }
        }

        private C0240a() {
        }

        public final a a() {
            return f8785b;
        }
    }
}

package com.joaomgcd.taskerm.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.session.MediaSessionManager;
import android.os.Handler;
import android.view.KeyEvent;
import androidx.core.app.NotificationCompat;
import c.f.a.q;
import c.f.b.v;
import c.f.b.w;
import c.f.b.x;
import c.l.n;
import c.s;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ai;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.aq;
import com.joaomgcd.taskerm.util.dp;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7763a = {x.a(new v(x.a(a.class), "mediaSessionManager", "getMediaSessionManager()Landroid/media/session/MediaSessionManager;")), x.a(new v(x.a(a.class), "audioManager", "getAudioManager()Landroid/media/AudioManager;")), x.a(new v(x.a(a.class), "volumeLongPressListenerClass", "getVolumeLongPressListenerClass()Ljava/lang/Class;")), x.a(new v(x.a(a.class), "mediaKeyListenerClass", "getMediaKeyListenerClass()Ljava/lang/Class;")), x.a(new v(x.a(a.class), "mediaSessionManagerCallbackClass", "getMediaSessionManagerCallbackClass()Ljava/lang/Class;")), x.a(new v(x.a(a.class), "setOnVolumeKeyLongPressListener", "getSetOnVolumeKeyLongPressListener()Lcom/joaomgcd/taskerm/util/Actions;")), x.a(new v(x.a(a.class), "setOnMediaKeyListener", "getSetOnMediaKeyListener()Lcom/joaomgcd/taskerm/util/Actions;")), x.a(new v(x.a(a.class), "dispatchVolumeKeyEvent", "getDispatchVolumeKeyEvent()Lcom/joaomgcd/taskerm/util/Actions;")), x.a(new v(x.a(a.class), "uiSoundsStreamType", "getUiSoundsStreamType()I"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0197a f7764b = new C0197a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f7765c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f7766d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f7767e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f7768f;
    private final c.e g;
    private b.a.k.b<com.joaomgcd.taskerm.media.b> h;
    private Object i;
    private final c.e j;
    private final c.e k;
    private final c.e l;
    private final c.e m;
    private final Context n;

    public static final boolean a(String str, String str2) {
        return f7764b.b(str, str2);
    }

    private final MediaSessionManager d() {
        c.e eVar = this.f7765c;
        c.j.g gVar = f7763a[0];
        return (MediaSessionManager) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AudioManager e() {
        c.e eVar = this.f7766d;
        c.j.g gVar = f7763a[1];
        return (AudioManager) eVar.b();
    }

    private final Class<?> f() {
        c.e eVar = this.f7767e;
        c.j.g gVar = f7763a[2];
        return (Class) eVar.b();
    }

    private final Class<?> g() {
        c.e eVar = this.f7768f;
        c.j.g gVar = f7763a[3];
        return (Class) eVar.b();
    }

    private final com.joaomgcd.taskerm.util.c<MediaSessionManager> h() {
        c.e eVar = this.j;
        c.j.g gVar = f7763a[5];
        return (com.joaomgcd.taskerm.util.c) eVar.b();
    }

    private final com.joaomgcd.taskerm.util.c<MediaSessionManager> i() {
        c.e eVar = this.k;
        c.j.g gVar = f7763a[6];
        return (com.joaomgcd.taskerm.util.c) eVar.b();
    }

    public static final class e extends c.f.b.l implements q<Object, Method, Object[], s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7775b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f7776c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f7777d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(c.f.a.b bVar, boolean z, boolean z2) {
            super(3);
            this.f7775b = bVar;
            this.f7776c = z;
            this.f7777d = z2;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.media.a$e$a, reason: collision with other inner class name */
        static final class C0198a extends c.f.b.l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ KeyEvent f7778a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ e f7779b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0198a(KeyEvent keyEvent, e eVar) {
                super(0);
                this.f7778a = keyEvent;
                this.f7779b = eVar;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                try {
                    a.this.e().adjustSuggestedStreamVolume(this.f7778a.getKeyCode() == 24 ? 1 : -1, Integer.MIN_VALUE, 5);
                } catch (Exception e2) {
                    bn.b("MediaSession", "Error dispatching volume event to system: " + ai.a(e2));
                }
            }
        }

        @Override // c.f.a.q
        public final s a(Object obj, Method method, Object[] objArr) {
            c.f.b.k.b(obj, "<anonymous parameter 0>");
            c.f.b.k.b(method, "<anonymous parameter 1>");
            c.f.b.k.b(objArr, "args");
            if (objArr.length != 1) {
                throw new ClassCastException("args is not size 1");
            }
            Object obj2 = objArr[0];
            if (!(obj2 instanceof KeyEvent)) {
                obj2 = null;
            }
            KeyEvent keyEvent = (KeyEvent) obj2;
            if (keyEvent == null) {
                throw new ClassCastException("args[0] is not " + KeyEvent.class.getName());
            }
            C0198a c0198a = new C0198a(keyEvent, this);
            bn.b("MediaSession", "Long Volume: " + keyEvent.getKeyCode() + " - " + keyEvent.getAction() + " - " + keyEvent.getRepeatCount());
            if (keyEvent.getAction() != 0) {
                a.this.h = (b.a.k.b) null;
                return null;
            }
            if (a.this.h == null) {
                a.this.h = a.this.a((c.f.a.b<? super com.joaomgcd.taskerm.media.c, s>) this.f7775b);
            }
            com.joaomgcd.taskerm.media.b bVar = keyEvent.getKeyCode() == 25 ? com.joaomgcd.taskerm.media.b.Down : com.joaomgcd.taskerm.media.b.Up;
            if (bVar == com.joaomgcd.taskerm.media.b.Up && !this.f7776c) {
                c0198a.a();
                return null;
            }
            if (bVar != com.joaomgcd.taskerm.media.b.Down || this.f7777d) {
                b.a.k.b bVar2 = a.this.h;
                if (bVar2 == null) {
                    return null;
                }
                bVar2.b_(bVar);
                return s.f2131a;
            }
            c0198a.a();
            return null;
        }
    }

    static final class l extends c.f.b.l implements c.f.a.a<Integer> {
        l() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            return ((Number) a.this.c("getUiSoundsStreamType", "uistream").a()).intValue();
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<MediaSessionManager> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MediaSessionManager invoke() {
            Object systemService = a.this.c().getSystemService("media_session");
            if (systemService != null) {
                return (MediaSessionManager) systemService;
            }
            throw new c.q("null cannot be cast to non-null type android.media.session.MediaSessionManager");
        }
    }

    public a(Context context) {
        c.f.b.k.b(context, "context");
        this.n = context;
        this.f7765c = c.f.a(new g());
        this.f7766d = c.f.a(new b());
        this.f7767e = c.f.a(m.f7788a);
        this.f7768f = c.f.a(f.f7780a);
        this.g = c.f.a(h.f7782a);
        this.j = c.f.a(new k());
        this.k = c.f.a(new j());
        this.l = c.f.a(new d());
        this.m = c.f.a(new l());
    }

    public final Context c() {
        return this.n;
    }

    static final class b extends c.f.b.l implements c.f.a.a<AudioManager> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final AudioManager invoke() {
            Object systemService = a.this.c().getSystemService("audio");
            if (systemService != null) {
                return (AudioManager) systemService;
            }
            throw new c.q("null cannot be cast to non-null type android.media.AudioManager");
        }
    }

    static final class m extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final m f7788a = new m();

        m() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.media.session.MediaSessionManager$OnVolumeKeyLongPressListener");
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f7780a = new f();

        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.media.session.MediaSessionManager$OnMediaKeyListener");
        }
    }

    static final class h extends c.f.b.l implements c.f.a.a<Class<?>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f7782a = new h();

        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Class<?> invoke() {
            return Class.forName("android.media.session.MediaSessionManager$Callback");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.k.b<com.joaomgcd.taskerm.media.b> a(c.f.a.b<? super com.joaomgcd.taskerm.media.c, s> bVar) {
        b.a.k.b<com.joaomgcd.taskerm.media.b> bVarJ = b.a.k.b.j();
        w.b bVar2 = new w.b();
        bVar2.f2026a = 0;
        c.f.b.k.a((Object) bVarJ, "subject");
        com.joaomgcd.taskerm.rx.i.b((b.a.h) bVarJ, 1, TimeUnit.SECONDS).d(new c(bVar2, this, bVar));
        c.f.b.k.a((Object) bVarJ, "PublishSubject.create<Vo…)\n            }\n        }");
        return bVarJ;
    }

    static final class c<T> implements b.a.d.f<com.joaomgcd.taskerm.media.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ w.b f7770a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a f7771b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7772c;

        c(w.b bVar, a aVar, c.f.a.b bVar2) {
            this.f7770a = bVar;
            this.f7771b = aVar;
            this.f7772c = bVar2;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.media.b bVar) {
            c.f.b.k.b(bVar, "volumeKey");
            if (this.f7771b.h == null) {
                return;
            }
            bn.b("MediaSession", "Reporting volume long press - " + bVar + ':' + this.f7770a.f2026a);
            c.f.a.b bVar2 = this.f7772c;
            w.b bVar3 = this.f7770a;
            int i = bVar3.f2026a;
            bVar3.f2026a = i + 1;
            bVar2.invoke(new com.joaomgcd.taskerm.media.c(bVar, i));
        }
    }

    private final Object a(boolean z, boolean z2, c.f.a.b<? super com.joaomgcd.taskerm.media.c, s> bVar) throws IOException {
        Object objA;
        Class<?> clsF = f();
        c.f.b.k.a((Object) clsF, "volumeLongPressListenerClass");
        Context context = this.n;
        e eVar = new e(bVar, z2, z);
        if (clsF.isInterface()) {
            objA = Proxy.newProxyInstance(clsF.getClassLoader(), new Class[]{clsF}, new dp(eVar));
        } else {
            objA = com.android.b.e.a.a(clsF).a(context.getCacheDir()).a(new dp(eVar)).a();
        }
        this.i = objA;
        return objA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final com.joaomgcd.taskerm.util.c<MediaSessionManager> b(String str, String str2) {
        return Cdo.b(d(), str, str2, false, 4, null);
    }

    static final class k extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends MediaSessionManager>> {
        k() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<MediaSessionManager> invoke() {
            return a.this.b("setOnVolumeKeyLongPressListener", "volumlongpress");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final aq<AudioManager> c(String str, String str2) {
        return Cdo.a((Object) e(), str, str2, false, 4, (Object) null);
    }

    static final class j extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends MediaSessionManager>> {
        j() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<MediaSessionManager> invoke() {
            return a.this.b("setOnMediaKeyListener", "mediakey");
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.util.c<? extends MediaSessionManager>> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.util.c<MediaSessionManager> invoke() {
            return a.this.b("dispatchVolumeKeyEvent", "volukeyevent");
        }
    }

    public final void a(boolean z, boolean z2, Handler handler, c.f.a.b<? super com.joaomgcd.taskerm.media.c, s> bVar) {
        c.f.b.k.b(handler, "handler");
        c.f.b.k.b(bVar, "callback");
        a(a(z, z2, bVar), handler);
    }

    public final void a() {
        a((Object) null, (Handler) null);
    }

    private final boolean a(Object obj, Handler handler) {
        return a(obj, handler, h(), "long volume", "SET_VOLUME_KEY_LONG_PRESS_LISTENER");
    }

    public static final class i implements OnMediaKeyListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f7783a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f7784b;

        i(c.f.a.b bVar, boolean z) {
            this.f7783a = bVar;
            this.f7784b = z;
        }

        @Override // com.joaomgcd.taskerm.media.OnMediaKeyListener
        public boolean onMediaKey(KeyEvent keyEvent) {
            c.f.b.k.b(keyEvent, NotificationCompat.CATEGORY_EVENT);
            this.f7783a.invoke(keyEvent);
            return this.f7784b;
        }
    }

    public final void a(boolean z, Handler handler, c.f.a.b<? super KeyEvent, s> bVar) {
        c.f.b.k.b(handler, "handler");
        c.f.b.k.b(bVar, "callback");
        Class<?> clsG = g();
        c.f.b.k.a((Object) clsG, "mediaKeyListenerClass");
        b(Cdo.a(clsG, this.n, new i(bVar, z)), handler);
    }

    public final void b() {
        b((Object) null, (Handler) null);
    }

    private final boolean b(Object obj, Handler handler) {
        try {
            return a(obj, handler, i(), "media key", "SET_MEDIA_KEY_LISTENER");
        } catch (Throwable unused) {
            return false;
        }
    }

    private final boolean a(Object obj, Handler handler, com.joaomgcd.taskerm.util.c<MediaSessionManager> cVar, String str, String str2) {
        try {
            cVar.a(obj, handler);
            return true;
        } catch (Throwable th) {
            if (obj == null) {
                return false;
            }
            String str3 = "Can't handle " + str + " presses. Need android.permission." + str2 + " permission via ADB";
            bn.b("MediaSession", str3, th);
            ak.c(str3, this.n);
            return false;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.media.a$a, reason: collision with other inner class name */
    public static final class C0197a {
        private C0197a() {
        }

        public /* synthetic */ C0197a(c.f.b.g gVar) {
            this();
        }

        public final String a(String str, String str2) {
            c.f.b.k.b(str, "startText");
            c.f.b.k.b(str2, "stopText");
            String str3 = "continuous(" + str;
            if (ak.z(str2)) {
                str3 = str3 + ',' + str2;
            }
            return str3 + ")";
        }

        public final boolean b(String str, String str2) {
            String strA;
            c.f.b.k.b(str, "countText");
            c.f.b.k.b(str2, "toMatch");
            String str3 = str2;
            if (!n.b((CharSequence) str3, (CharSequence) "continuous", false, 2, (Object) null)) {
                return c.f.b.k.a((Object) str, (Object) str2);
            }
            String strB = n.b(str2, "continuous(", (String) null, 2, (Object) null);
            String strA2 = (String) null;
            if (n.b((CharSequence) str3, (CharSequence) ",", false, 2, (Object) null)) {
                strA2 = n.a(n.b(strB, ",", (String) null, 2, (Object) null), ")", (String) null, 2, (Object) null);
                strA = n.a(strB, ",", (String) null, 2, (Object) null);
            } else {
                strA = n.a(strB, ")", (String) null, 2, (Object) null);
            }
            try {
                int i = Integer.parseInt(str);
                int i2 = Integer.parseInt(strA);
                return i >= i2 && i < (strA2 != null ? Integer.parseInt(strA2) + i2 : Integer.MAX_VALUE);
            } catch (NumberFormatException unused) {
                return false;
            }
        }
    }
}

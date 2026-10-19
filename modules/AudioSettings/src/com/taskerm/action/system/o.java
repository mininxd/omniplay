package com.joaomgcd.taskerm.action.system;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.ds;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class o extends AppBasic {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f5807a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "assistActivity", "getAssistActivity()Landroid/content/pm/ActivityInfo;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "interactionServiceInfo", "getInteractionServiceInfo()Landroid/content/pm/ServiceInfo;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "applicationInfo", "getApplicationInfo()Landroid/content/pm/ApplicationInfo;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "interactionServiceName", "getInteractionServiceName()Ljava/lang/String;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(o.class), "recognitionServiceName", "getRecognitionServiceName()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f5808b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f5809c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f5810d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f5811e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f5812f;

    public final ActivityInfo a() {
        c.e eVar = this.f5808b;
        c.j.g gVar = f5807a[0];
        return (ActivityInfo) eVar.b();
    }

    public final ServiceInfo b() {
        c.e eVar = this.f5809c;
        c.j.g gVar = f5807a[1];
        return (ServiceInfo) eVar.b();
    }

    public final ApplicationInfo c() {
        c.e eVar = this.f5810d;
        c.j.g gVar = f5807a[2];
        return (ApplicationInfo) eVar.b();
    }

    public final String d() {
        c.e eVar = this.f5811e;
        c.j.g gVar = f5807a[3];
        return (String) eVar.b();
    }

    public final String e() {
        c.e eVar = this.f5812f;
        c.j.g gVar = f5807a[4];
        return (String) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(Context context, String str) {
        super(context, str);
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "packageName");
        this.f5808b = c.f.a(new b(context, str));
        this.f5809c = c.f.a(new c(context, str));
        this.f5810d = c.f.a(new a());
        this.f5811e = c.f.a(new d(str));
        this.f5812f = c.f.a(new e(context, str));
    }

    static final class b extends c.f.b.l implements c.f.a.a<ActivityInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5814a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5815b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context, String str) {
            super(0);
            this.f5814a = context;
            this.f5815b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ActivityInfo invoke() {
            Object next;
            ActivityInfo activityInfo;
            Iterator<T> it = com.joaomgcd.taskerm.util.ah.t(this.f5814a, "android.intent.action.ASSIST").iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                activityInfo = (ActivityInfo) next;
            } while (!c.f.b.k.a((Object) (activityInfo != null ? activityInfo.packageName : null), (Object) this.f5815b));
            return (ActivityInfo) next;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<ServiceInfo> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5816a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5817b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, String str) {
            super(0);
            this.f5816a = context;
            this.f5817b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ServiceInfo invoke() {
            Object next;
            ServiceInfo serviceInfo;
            Iterator<T> it = com.joaomgcd.taskerm.util.ah.s(this.f5816a, "android.service.voice.VoiceInteractionService").iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                serviceInfo = (ServiceInfo) next;
            } while (!c.f.b.k.a((Object) (serviceInfo != null ? serviceInfo.packageName : null), (Object) this.f5817b));
            return (ServiceInfo) next;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<ApplicationInfo> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ApplicationInfo invoke() {
            ServiceInfo serviceInfoB = o.this.b();
            if (serviceInfoB != null) {
                return serviceInfoB.applicationInfo;
            }
            return null;
        }
    }

    static final class d extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f5819b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str) {
            super(0);
            this.f5819b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String str;
            ServiceInfo serviceInfoB = o.this.b();
            if (serviceInfoB == null || (str = serviceInfoB.name) == null) {
                ActivityInfo activityInfoA = o.this.a();
                str = activityInfoA != null ? activityInfoA.name : null;
            }
            if (str != null) {
                return o.this.a(this.f5819b, str);
            }
            return null;
        }
    }

    static final class e extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Context f5821b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f5822c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Context context, String str) {
            super(0);
            this.f5821b = context;
            this.f5822c = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            ApplicationInfo applicationInfoC;
            try {
                ServiceInfo serviceInfoB = o.this.b();
                if (serviceInfoB == null || (applicationInfoC = o.this.c()) == null) {
                    return null;
                }
                String str = new ds(new a(this.f5821b.getPackageManager().getResourcesForApplication(applicationInfoC), serviceInfoB)).a("voice-interaction-service").get("recognitionService");
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    return null;
                }
                return o.this.a(this.f5822c, str);
            } catch (Throwable unused) {
                return null;
            }
        }

        static final class a extends c.f.b.l implements c.f.a.a<XmlResourceParser> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Resources f5823a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ServiceInfo f5824b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(Resources resources, ServiceInfo serviceInfo) {
                super(0);
                this.f5823a = resources;
                this.f5824b = serviceInfo;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final XmlResourceParser invoke() {
                XmlResourceParser xml = this.f5823a.getXml(this.f5824b.metaData.getInt("android.voice_interaction"));
                c.f.b.k.a((Object) xml, "resourcesForApplication.…roid.voice_interaction\"))");
                return xml;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String a(String str, String str2) {
        return str + '/' + c.l.n.a(str2, str, "", false, 4, (Object) null);
    }
}

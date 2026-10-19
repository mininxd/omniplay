package com.joaomgcd.taskerm.controlsprovider;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.service.controls.Control;
import android.service.controls.ControlsProviderService;
import android.service.controls.actions.BooleanAction;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.FloatAction;
import android.service.controls.templates.ControlButton;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.service.controls.templates.ToggleRangeTemplate;
import android.service.controls.templates.ToggleTemplate;
import android.util.Log;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.l.n;
import c.s;
import com.joaomgcd.taskerm.action.input.PowerMenuActionType;
import com.joaomgcd.taskerm.action.input.StoredPowerMenuAction;
import com.joaomgcd.taskerm.action.input.StoredPowerMenuActions;
import com.joaomgcd.taskerm.action.input.m;
import com.joaomgcd.taskerm.util.ActivitySecondaryApp;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Flow;
import java.util.function.Consumer;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.be;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;

/* JADX INFO: loaded from: classes.dex */
public final class ControlsProviderServiceTasker extends ControlsProviderService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f6040a = {x.a(new v(x.a(ControlsProviderServiceTasker.class), "defaultIcon", "getDefaultIcon()Landroid/graphics/drawable/Icon;")), x.a(new v(x.a(ControlsProviderServiceTasker.class), "taskCurrentRanges", "getTaskCurrentRanges()Ljava/util/HashMap;")), x.a(new v(x.a(ControlsProviderServiceTasker.class), "taskCurrentStates", "getTaskCurrentStates()Ljava/util/HashMap;")), x.a(new v(x.a(ControlsProviderServiceTasker.class), "disposables", "getDisposables()Lio/reactivex/disposables/CompositeDisposable;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f6041b = c.f.a(new e());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f6042c = c.f.a(j.f6076a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f6043d = c.f.a(k.f6077a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f6044e = c.f.a(f.f6068a);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Flow.Subscriber<? super Control> f6045f;

    /* JADX INFO: Access modifiers changed from: private */
    public final ControlsProviderServiceTasker b() {
        return this;
    }

    private final Icon c() {
        c.e eVar = this.f6041b;
        c.j.g gVar = f6040a[0];
        return (Icon) eVar.b();
    }

    private final HashMap<String, Float> d() {
        c.e eVar = this.f6042c;
        c.j.g gVar = f6040a[1];
        return (HashMap) eVar.b();
    }

    private final HashMap<String, Boolean> e() {
        c.e eVar = this.f6043d;
        c.j.g gVar = f6040a[2];
        return (HashMap) eVar.b();
    }

    private final b.a.b.a f() {
        c.e eVar = this.f6044e;
        c.j.g gVar = f6040a[3];
        return (b.a.b.a) eVar.b();
    }

    static final class c<T> implements Flow.Publisher<Control> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f6055b;

        c(List list) {
            this.f6055b = list;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.controlsprovider.ControlsProviderServiceTasker$c$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Flow.Subscriber f6057b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Flow.Subscriber subscriber) {
                super(0);
                this.f6057b = subscriber;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                StoredPowerMenuActions storedPowerMenuActionsA = m.a(ControlsProviderServiceTasker.this);
                for (String str : c.this.f6055b) {
                    Control controlE = ControlsProviderServiceTasker.this.e(str);
                    if (controlE == null) {
                        controlE = (Control) aj.a((c.f.a.b) null, new a(str, this, storedPowerMenuActionsA), 1, (Object) null);
                    }
                    if (controlE != null) {
                        this.f6057b.onSubscribe(new b());
                        this.f6057b.onNext(controlE);
                    }
                }
                com.joaomgcd.taskerm.event.net.d.a(ControlsProviderServiceTasker.this, false);
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.controlsprovider.ControlsProviderServiceTasker$c$1$a */
            static final class a extends l implements c.f.a.a<Control> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ String f6058a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ AnonymousClass1 f6059b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ StoredPowerMenuActions f6060c;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                a(String str, AnonymousClass1 anonymousClass1, StoredPowerMenuActions storedPowerMenuActions) {
                    super(0);
                    this.f6058a = str;
                    this.f6059b = anonymousClass1;
                    this.f6060c = storedPowerMenuActions;
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final Control invoke() {
                    b.a.l<Control> control;
                    StoredPowerMenuAction storedPowerMenuActionById = this.f6060c.byId(this.f6058a);
                    if (storedPowerMenuActionById == null || (control = storedPowerMenuActionById.getControl(ControlsProviderServiceTasker.this.b())) == null) {
                        return null;
                    }
                    return control.b();
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.controlsprovider.ControlsProviderServiceTasker$c$1$b */
            public static final class b implements Flow.Subscription {
                b() {
                }

                @Override // java.util.concurrent.Flow.Subscription
                public void cancel() {
                    Log.d("ControlTest", "cancel");
                }

                @Override // java.util.concurrent.Flow.Subscription
                public void request(long j) {
                    Log.d("ControlTest", "request " + j);
                }
            }
        }

        @Override // java.util.concurrent.Flow.Publisher
        public final void subscribe(Flow.Subscriber<? super Control> subscriber) {
            ControlsProviderServiceTasker.this.a(subscriber);
            com.joaomgcd.taskerm.rx.i.c(new AnonymousClass1(subscriber));
        }
    }

    static final class d<T> implements Flow.Publisher<Control> {
        d() {
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.controlsprovider.ControlsProviderServiceTasker$d$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<s> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Flow.Subscriber f6063b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Flow.Subscriber subscriber) {
                super(0);
                this.f6063b = subscriber;
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.controlsprovider.ControlsProviderServiceTasker$d$1$a */
            static final class a extends l implements c.f.a.a<Control> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ StoredPowerMenuAction f6064a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ AnonymousClass1 f6065b;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                a(StoredPowerMenuAction storedPowerMenuAction, AnonymousClass1 anonymousClass1) {
                    super(0);
                    this.f6064a = storedPowerMenuAction;
                    this.f6065b = anonymousClass1;
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final Control invoke() {
                    return this.f6064a.getControl(ControlsProviderServiceTasker.this.b()).b();
                }
            }

            public final void a() {
                Iterator<StoredPowerMenuAction> it = m.a(ControlsProviderServiceTasker.this).iterator();
                while (it.hasNext()) {
                    Control control = (Control) aj.a((c.f.a.b) null, new a(it.next(), this), 1, (Object) null);
                    if (control != null) {
                        this.f6063b.onNext(control);
                    }
                }
                fx fxVarE = fx.e(ControlsProviderServiceTasker.this.b());
                c.f.b.k.a((Object) fxVarE, "TaskerData.getActive(context)");
                ArrayList<fs> arrayListX = fxVarE.x();
                c.f.b.k.a((Object) arrayListX, "TaskerData.getActive(con…beticallySortedNamedTasks");
                Iterator<T> itA = c.k.h.c(c.a.j.p(arrayListX), new b()).a();
                while (itA.hasNext()) {
                    this.f6063b.onNext((Control) itA.next());
                }
                this.f6063b.onComplete();
                com.joaomgcd.taskerm.event.net.d.a(ControlsProviderServiceTasker.this, true);
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.controlsprovider.ControlsProviderServiceTasker$d$1$b */
            static final class b extends l implements c.f.a.b<fs, Control> {
                b() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final Control invoke(fs fsVar) {
                    return ControlsProviderServiceTasker.this.b(fsVar);
                }
            }
        }

        @Override // java.util.concurrent.Flow.Publisher
        public final void subscribe(Flow.Subscriber<? super Control> subscriber) {
            com.joaomgcd.taskerm.rx.i.c(new AnonymousClass1(subscriber));
        }
    }

    static final class h extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f6074a = new h();

        h() {
            super(0);
        }

        public final void a() {
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }
    }

    static final class i extends l implements c.f.a.b<Control, s> {
        i() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(Control control) {
            a(control);
            return s.f2131a;
        }

        public final void a(Control control) {
            c.f.b.k.b(control, "it");
            Flow.Subscriber<? super Control> subscriberA = ControlsProviderServiceTasker.this.a();
            if (subscriberA != null) {
                subscriberA.onNext(control);
            }
        }
    }

    static final class e extends l implements c.f.a.a<Icon> {
        e() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Icon invoke() {
            return Icon.createWithResource(ControlsProviderServiceTasker.this.b(), R.mipmap.cust_app_main_icon);
        }
    }

    private final Icon a(fs fsVar) {
        if (fsVar == null) {
            return c();
        }
        if (!fsVar.y()) {
            return c();
        }
        Bitmap bitmap = (Bitmap) aj.a((c.f.a.b) null, new b(fsVar), 1, (Object) null);
        if (bitmap == null) {
            return c();
        }
        Icon iconCreateWithBitmap = Icon.createWithBitmap(bitmap);
        c.f.b.k.a((Object) iconCreateWithBitmap, "Icon.createWithBitmap(bitmap)");
        return iconCreateWithBitmap;
    }

    static final class b extends l implements c.f.a.a<Bitmap> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ fs f6053b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(fs fsVar) {
            super(0);
            this.f6053b = fsVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Bitmap invoke() {
            return this.f6053b.C().a(ControlsProviderServiceTasker.this.b(), 50, 50, "ControlsProviderServiceTasker");
        }
    }

    static final class j extends l implements c.f.a.a<HashMap<String, Float>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f6076a = new j();

        j() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, Float> invoke() {
            return new HashMap<>();
        }
    }

    static final class k extends l implements c.f.a.a<HashMap<String, Boolean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final k f6077a = new k();

        k() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, Boolean> invoke() {
            return new HashMap<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float a(String str) {
        Float fValueOf = d().get(str);
        if (fValueOf == null) {
            fValueOf = Float.valueOf(0.0f);
        }
        c.f.b.k.a((Object) fValueOf, "taskCurrentRanges[this] ?: 0f");
        return fValueOf.floatValue();
    }

    private final void a(String str, float f2) {
        d().put(str, Float.valueOf(f2));
    }

    private final boolean b(String str) {
        Boolean bool = e().get(str);
        if (bool == null) {
            bool = false;
        }
        c.f.b.k.a((Object) bool, "taskCurrentStates[this] ?: false");
        return bool.booleanValue();
    }

    private final void a(String str, boolean z) {
        e().put(str, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Control b(fs fsVar) {
        String strC;
        String strD;
        RangeTemplate rangeTemplateInvoke;
        if (fsVar == null || (strC = c(fsVar)) == null || (strD = d(fsVar)) == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        fsVar.a((Context) this, false, (Set<String>) hashSet, true, false, false);
        boolean zContains = hashSet.contains(fs.b(1));
        boolean zContains2 = hashSet.contains(fs.b(2));
        Control.StatefulBuilder statefulBuilder = new Control.StatefulBuilder(strC, PendingIntent.getActivity(b(), 0, new Intent(b(), (Class<?>) ActivitySecondaryApp.class), 268435456));
        statefulBuilder.setTitle(strD);
        statefulBuilder.setSubtitle("Trigger " + strD);
        statefulBuilder.setDeviceType(-1);
        statefulBuilder.setControlId(strC);
        statefulBuilder.setStatus(1);
        statefulBuilder.setCustomIcon(a(fsVar));
        if (zContains || zContains2) {
            ControlButton controlButton = new ControlButton(b(strC), be.EXTRA_ID);
            a aVar = new a(fsVar, strD, strC, zContains, zContains2);
            if (zContains && zContains2) {
                rangeTemplateInvoke = new ToggleRangeTemplate(strC, controlButton, aVar.invoke());
            } else if (zContains) {
                rangeTemplateInvoke = new ToggleTemplate(strD, controlButton);
            } else {
                rangeTemplateInvoke = aVar.invoke();
            }
        } else {
            rangeTemplateInvoke = new StatelessTemplate(strD);
        }
        statefulBuilder.setControlTemplate(rangeTemplateInvoke);
        return statefulBuilder.build();
    }

    static final class a extends l implements c.f.a.a<RangeTemplate> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ fs f6047b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f6048c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f6049d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f6050e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f6051f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(fs fsVar, String str, String str2, boolean z, boolean z2) {
            super(0);
            this.f6047b = fsVar;
            this.f6048c = str;
            this.f6049d = str2;
            this.f6050e = z;
            this.f6051f = z2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final RangeTemplate invoke() {
            return new RangeTemplate(this.f6049d, 0.0f, 100.0f, ControlsProviderServiceTasker.this.a(this.f6049d), 1.0f, null);
        }
    }

    private final String c(fs fsVar) {
        String strD = d(fsVar);
        if (strD == null) {
            return null;
        }
        return "supertaskcontrolyeahbaby" + strD;
    }

    private final String d(fs fsVar) {
        if (fsVar != null && fsVar.m()) {
            return fsVar.l();
        }
        return null;
    }

    private final String c(String str) {
        return n.a(str, (CharSequence) "supertaskcontrolyeahbaby");
    }

    private final fs d(String str) {
        return fx.e(b()).h(c(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Control e(String str) {
        fs fsVarD = d(str);
        if (fsVarD != null) {
            return b(fsVarD);
        }
        return null;
    }

    @Override // android.service.controls.ControlsProviderService
    public Flow.Publisher<Control> createPublisherForAllAvailable() {
        return new d();
    }

    @Override // android.service.controls.ControlsProviderService
    public void performControlAction(String str, ControlAction controlAction, Consumer<Integer> consumer) {
        c.f.b.k.b(str, "controlId");
        c.f.b.k.b(controlAction, "action");
        c.f.b.k.b(consumer, "consumer");
        if (a(str, controlAction, consumer)) {
            return;
        }
        b(str, controlAction, consumer);
    }

    private final boolean a(String str, ControlAction controlAction, Consumer<Integer> consumer) {
        boolean zB;
        float fA;
        Flow.Subscriber<? super Control> subscriber;
        if (e(str) == null) {
            return false;
        }
        if (controlAction instanceof BooleanAction) {
            zB = ((BooleanAction) controlAction).getNewState();
            a(str, zB);
        } else {
            zB = b(str);
        }
        String strValueOf = String.valueOf(zB);
        if (controlAction instanceof FloatAction) {
            fA = ((FloatAction) controlAction).getNewValue();
            a(str, fA);
        } else {
            fA = a(str);
        }
        ExecuteService.a(this, c(str), strValueOf, String.valueOf(c.g.a.a(fA)), "powermenu");
        consumer.accept(1);
        Control controlE = e(str);
        if (controlE != null && (subscriber = this.f6045f) != null) {
            subscriber.onNext(controlE);
        }
        return true;
    }

    private final void b(String str, ControlAction controlAction, Consumer<Integer> consumer) {
        String commandPrefix;
        StoredPowerMenuAction storedPowerMenuActionById = m.a(this).byId(str);
        if (storedPowerMenuActionById == null || (commandPrefix = storedPowerMenuActionById.getCommandPrefix()) == null) {
            return;
        }
        PowerMenuActionType type = storedPowerMenuActionById.getType();
        String strInvoke = new g(type, commandPrefix, controlAction instanceof BooleanAction ? Boolean.valueOf(((BooleanAction) controlAction).getNewState()) : storedPowerMenuActionById.getActive(), controlAction instanceof FloatAction ? Float.valueOf(((FloatAction) controlAction).getNewValue()) : storedPowerMenuActionById.getRangeCurrent(), controlAction).invoke();
        String str2 = strInvoke;
        if (str2 == null || str2.length() == 0) {
            consumer.accept(2);
            return;
        }
        if (type != PowerMenuActionType.Button) {
            consumer.accept(1);
        } else {
            consumer.accept(1);
        }
        ah.v(this, strInvoke);
    }

    static final class g extends l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ PowerMenuActionType f6069a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6070b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Boolean f6071c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Float f6072d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ ControlAction f6073e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(PowerMenuActionType powerMenuActionType, String str, Boolean bool, Float f2, ControlAction controlAction) {
            super(0);
            this.f6069a = powerMenuActionType;
            this.f6070b = str;
            this.f6071c = bool;
            this.f6072d = f2;
            this.f6073e = controlAction;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            switch (this.f6069a) {
                case Button:
                    return this.f6070b;
                case Toggle:
                    if (this.f6071c == null) {
                        return this.f6070b;
                    }
                    return this.f6070b + "=:=" + this.f6071c;
                case Range:
                    if (this.f6072d == null) {
                        return this.f6070b;
                    }
                    return this.f6070b + "=:=" + this.f6072d;
                case ToggleRange:
                    String str = this.f6070b;
                    if (this.f6071c != null) {
                        str = str + "=:=" + this.f6071c;
                    }
                    if (this.f6072d != null) {
                        str = str + "=:=" + this.f6072d;
                    }
                    if (this.f6073e instanceof BooleanAction) {
                        return str + "=:=toggle";
                    }
                    if (!(this.f6073e instanceof FloatAction)) {
                        return str;
                    }
                    return str + "=:=range";
                case NoAction:
                    return null;
                default:
                    throw new c.i();
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        f().a(com.joaomgcd.taskerm.rx.i.a(com.joaomgcd.taskerm.rx.b.a(this), this, h.f6074a));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.joaomgcd.taskerm.rx.b.b(this);
        f().b();
    }

    static final class f extends l implements c.f.a.a<b.a.b.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final f f6068a = new f();

        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.b.a invoke() {
            return new b.a.b.a();
        }
    }

    @com.joaomgcd.taskerm.rx.d
    public final void onStoredPowerMenuAction(StoredPowerMenuAction storedPowerMenuAction) {
        c.f.b.k.b(storedPowerMenuAction, "action");
        ControlsProviderServiceTasker controlsProviderServiceTasker = this;
        f().a(com.joaomgcd.taskerm.rx.i.a(storedPowerMenuAction.getControl(controlsProviderServiceTasker), controlsProviderServiceTasker, new i()));
    }

    public final Flow.Subscriber<? super Control> a() {
        return this.f6045f;
    }

    public final void a(Flow.Subscriber<? super Control> subscriber) {
        this.f6045f = subscriber;
    }

    @Override // android.service.controls.ControlsProviderService
    public Flow.Publisher<Control> createPublisherFor(List<String> list) {
        c.f.b.k.b(list, "controlIds");
        return new c(list);
    }
}

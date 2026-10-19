package com.joaomgcd.taskerm.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import c.f.b.v;
import c.f.b.x;
import com.joaomgcd.taskerm.util.aj;
import java.util.HashMap;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ce;
import net.dinglisch.android.taskerm.cs;
import net.dinglisch.android.taskerm.dl;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ce {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8208a = {x.a(new v(x.a(a.class), "projectIndex", "getProjectIndex()I"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0216a f8209b = new C0216a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.joaomgcd.taskerm.profile.b f8210d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f8211e = c.f.a(new d());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private HashMap f8212f;

    private final int c() {
        c.e eVar = this.f8211e;
        c.j.g gVar = f8208a[0];
        return ((Number) eVar.b()).intValue();
    }

    @Override // net.dinglisch.android.taskerm.ce
    protected boolean a() {
        return false;
    }

    public void b() {
        if (this.f8212f != null) {
            this.f8212f.clear();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        b();
    }

    static final class d extends c.f.b.l implements c.f.a.a<Integer> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            return a.this.getArguments().getInt("pi");
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.a$a, reason: collision with other inner class name */
    public static final class C0216a {
        private C0216a() {
        }

        public /* synthetic */ C0216a(c.f.b.g gVar) {
            this();
        }

        public final a a(Context context, Handler handler, int i, dl dlVar) {
            c.f.b.k.b(dlVar, "project");
            a aVar = new a();
            aVar.f12161c = handler;
            Bundle bundle = new Bundle();
            bundle.putInt("pi", i);
            j jVarB = dlVar.b();
            if (jVarB != null) {
                bundle.putString("v", aj.a((Object) jVarB));
            }
            aVar.setArguments(bundle);
            return aVar;
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        a(bundle);
        Bundle arguments = getArguments();
        Activity activity = getActivity();
        Activity activity2 = activity;
        View viewInflate = LayoutInflater.from(activity2).inflate(R.layout.projectproperties, (ViewGroup) null);
        c.f.b.k.a((Object) activity, "a");
        c.f.b.k.a((Object) viewInflate, "root");
        com.joaomgcd.taskerm.profile.b bVar = new com.joaomgcd.taskerm.profile.b(activity, viewInflate, R.string.ppselect_text_project_variables, R.string.ppselect_text_project_variables_help, new f(c()));
        a(viewInflate);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity2);
        builder.setTitle(cs.a(activity2, R.string.dialog_title_project_properties, new Object[0]));
        builder.setView(viewInflate);
        builder.setPositiveButton(cs.a(activity2, R.string.button_label_ok, new Object[0]), new b(bVar));
        builder.setNegativeButton(cs.a(activity2, R.string.button_label_cancel, new Object[0]), new c());
        builder.setView(viewInflate);
        String string = arguments.getString("v");
        bVar.a(string != null ? (j) com.joaomgcd.taskerm.n.b.a().a(string, j.class) : null);
        this.f8210d = bVar;
        return builder.create();
    }

    static final class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.profile.b f8214b;

        b(com.joaomgcd.taskerm.profile.b bVar) {
            this.f8214b = bVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            a.this.a(this.f8214b.a());
        }
    }

    static final class c implements DialogInterface.OnClickListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (a.this.f12161c != null) {
                a.this.f12161c.sendEmptyMessage(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(j jVar) {
        if (this.f12161c != null) {
            Message messageObtainMessage = this.f12161c.obtainMessage();
            messageObtainMessage.what = 0;
            c.f.b.k.a((Object) messageObtainMessage, NotificationCompat.CATEGORY_MESSAGE);
            Bundle bundle = new Bundle();
            if (jVar != null) {
                bundle.putString("v", aj.a((Object) jVar));
            }
            messageObtainMessage.setData(bundle);
            this.f12161c.sendMessage(messageObtainMessage);
        }
    }

    public final void a(Activity activity) {
        a(activity, "taskproperties");
    }
}

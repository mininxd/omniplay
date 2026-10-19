package com.joaomgcd.taskerm.dialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.TimePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.contacts.ContactData;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityPickShortcut;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestCallScreeningAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestDoNotDisturbAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestManageExternalStorageAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestMissingPermissions;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestNotificationAccess;
import com.joaomgcd.taskerm.genericaction.GenericActionActivitySelectApp;
import com.joaomgcd.taskerm.genericaction.GenericActionPickContact;
import com.joaomgcd.taskerm.genericaction.GenericActionPickContactWithMimeType;
import com.joaomgcd.taskerm.navigationbar.IconProvider;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.GenericActionActivityGrantUriPermission;
import com.joaomgcd.taskerm.util.bu;
import com.joaomgcd.taskerm.util.ck;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cm;
import com.joaomgcd.taskerm.util.co;
import com.joaomgcd.taskerm.util.cp;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dk;
import com.joaomgcd.taskerm.util.dm;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.FileSelect;
import net.dinglisch.android.taskerm.Kid;
import net.dinglisch.android.taskerm.ca;
import net.dinglisch.android.taskerm.cd;
import net.dinglisch.android.taskerm.cs;
import net.dinglisch.android.taskerm.dh;
import net.dinglisch.android.taskerm.dl;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;
import net.dinglisch.android.taskerm.ge;
import net.dinglisch.android.taskerm.gm;
import net.dinglisch.android.taskerm.gn;
import net.dinglisch.android.taskerm.hg;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static com.joaomgcd.taskerm.securesettings.ai f6405a;

    static final class bj extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bj f6520a = new bj();

        bj() {
            super(1);
        }

        public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            a(xVar);
            return c.s.f2131a;
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, int i3) {
        return a(activity, i2, i3, 0, 8, (Object) null);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, String str, String str2) {
        return a(activity, str, str2, 0, 8, (Object) null);
    }

    public static final String a() {
        return "DialogMessage";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int b(boolean z2) {
        return z2 ? 1 : 0;
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> c(Activity activity, int i2, int i3) {
        return b(activity, i2, i3, 0, 8, (Object) null);
    }

    public static final b.a.l<cl> j(Activity activity) {
        return a(activity, (cm) null, 2, (Object) null);
    }

    static final class bs extends c.f.b.l implements c.f.a.b<Message, com.joaomgcd.taskerm.dialog.x> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bs f6532a = new bs();

        bs() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.x invoke(Message message) {
            c.f.b.k.b(message, "it");
            return new com.joaomgcd.taskerm.dialog.x(message);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(c.f.a.b<? super com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>>, c.s> bVar, c.f.a.b<? super com.joaomgcd.taskerm.dialog.x, Boolean> bVar2) {
        c.f.b.k.b(bVar, "block");
        return a(bVar, bVar2, bs.f6532a);
    }

    public static /* synthetic */ b.a.l a(c.f.a.b bVar, c.f.a.b bVar2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bVar2 = (c.f.a.b) null;
        }
        return a((c.f.a.b<? super com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>>, c.s>) bVar, (c.f.a.b<? super com.joaomgcd.taskerm.dialog.x, Boolean>) bVar2);
    }

    /* JADX INFO: Add missing generic type declarations: [TDialogMessage] */
    static final class bt<TDialogMessage> extends c.f.b.l implements c.f.a.a<b.a.k.d<TDialogMessage>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f6533a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f6534b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f6535c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        bt(c.f.a.b bVar, c.f.a.b bVar2, c.f.a.b bVar3) {
            super(0);
            this.f6533a = bVar;
            this.f6534b = bVar2;
            this.f6535c = bVar3;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.k.d<TDialogMessage> invoke() {
            b.a.k.d<TDialogMessage> dVarF = b.a.k.d.f();
            c.f.b.k.a((Object) dVarF, "SingleSubject.create<TDialogMessage>()");
            this.f6535c.invoke(new com.joaomgcd.taskerm.dialog.ah(dVarF, new a()));
            return dVarF;
        }

        static final class a extends c.f.b.l implements c.f.a.m<b.a.k.d<TDialogMessage>, Message, c.s> {
            a() {
                super(2);
            }

            @Override // c.f.a.m
            public /* synthetic */ c.s invoke(Object obj, Message message) {
                a((b.a.k.d) obj, message);
                return c.s.f2131a;
            }

            public final void a(b.a.k.d<TDialogMessage> dVar, Message message) {
                c.f.b.k.b(dVar, "singleSubject");
                c.f.b.k.b(message, "message");
                com.joaomgcd.taskerm.dialog.x xVar = (com.joaomgcd.taskerm.dialog.x) bt.this.f6533a.invoke(message);
                if (bt.this.f6534b == null || !((Boolean) bt.this.f6534b.invoke(xVar)).booleanValue()) {
                    dVar.d_(xVar);
                }
            }
        }
    }

    public static final <TDialogMessage extends com.joaomgcd.taskerm.dialog.x> b.a.l<TDialogMessage> a(c.f.a.b<? super com.joaomgcd.taskerm.dialog.ah<b.a.k.d<TDialogMessage>>, c.s> bVar, c.f.a.b<? super TDialogMessage, Boolean> bVar2, c.f.a.b<? super Message, ? extends TDialogMessage> bVar3) {
        c.f.b.k.b(bVar, "block");
        c.f.b.k.b(bVar3, "getDialogMessage");
        return com.joaomgcd.taskerm.rx.i.f(new bt(bVar3, bVar2, bVar));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(com.joaomgcd.taskerm.dialog.h hVar) {
        c.f.b.k.b(hVar, "args");
        return a(new bm(hVar), (c.f.a.b) null, 2, (Object) null);
    }

    static final class bm extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.dialog.h f6523a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        bm(com.joaomgcd.taskerm.dialog.h hVar) {
            super(1);
            this.f6523a = hVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            a(ahVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            c.f.b.k.b(ahVar, "it");
            com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar2 = ahVar;
            com.joaomgcd.taskerm.util.h hVarB = this.f6523a.b();
            String strA = hVarB != null ? hVarB.a() : null;
            com.joaomgcd.taskerm.util.h hVarC = this.f6523a.c();
            String strA2 = hVarC != null ? hVarC.a() : null;
            com.joaomgcd.taskerm.util.h hVarD = this.f6523a.d();
            String strA3 = hVarD != null ? hVarD.a() : null;
            com.joaomgcd.taskerm.util.h hVarE = this.f6523a.e();
            String strA4 = hVarE != null ? hVarE.a() : null;
            com.joaomgcd.taskerm.util.h hVarF = this.f6523a.f();
            ge geVarA = ge.a(ahVar2, strA, strA2, strA3, strA4, hVarF != null ? hVarF.a() : null, this.f6523a.g(), this.f6523a.h(), this.f6523a.i(), this.f6523a.j(), this.f6523a.k());
            geVarA.a(this.f6523a.l());
            this.f6523a.a(geVarA);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, String str, int i3) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, str, R.string.button_label_yes, R.string.button_label_no, i3, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, String str, int i3, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            i3 = -1;
        }
        return a(activity, i2, str, i3);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, int i3, int i4) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, i3, R.string.button_label_yes, R.string.button_label_no, i4, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = -1;
        }
        return a(activity, i2, i3, i4);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, String str, String str2, int i2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, str, str2, R.string.button_label_yes, R.string.button_label_no, i2, false, 0, null, 0, null, null, 4032, null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        return a(activity, str, str2, i2);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity, String str, String str2, int i2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, str, str2, R.string.button_label_enabled_on, R.string.button_label_enabled_off, i2, false, 0, null, 0, null, null, 4032, null));
    }

    public static /* synthetic */ b.a.l b(Activity activity, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        return b(activity, str, str2, i2);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> c(Activity activity, String str, String str2, int i2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, str, str2, R.string.word_true, R.string.word_false, i2, false, 0, null, 0, null, null, 4032, null));
    }

    public static /* synthetic */ b.a.l c(Activity activity, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        return c(activity, str, str2, i2);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> d(Activity activity, String str, String str2, int i2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, str, str2, R.string.pl_enable, R.string.ml_disable, i2, false, 0, null, 0, null, null, 4032, null));
    }

    public static /* synthetic */ b.a.l d(Activity activity, String str, String str2, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        return d(activity, str, str2, i2);
    }

    public static /* synthetic */ b.a.l a(Activity activity, String str, String str2, int i2, boolean z2, String str3, Integer num, int i3, Integer num2, List list, int i4, Object obj) {
        return a(activity, str, str2, (i4 & 8) != 0 ? -1 : i2, (i4 & 16) != 0 ? false : z2, (i4 & 32) != 0 ? (String) null : str3, (i4 & 64) != 0 ? (Integer) null : num, (i4 & 128) != 0 ? 0 : i3, (i4 & 256) != 0 ? Integer.valueOf(gn.c(activity)) : num2, (List<String>) ((i4 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? (List) null : list));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, String str, String str2, int i2, boolean z2, String str3, Integer num, int i3, Integer num2, List<String> list) {
        int iB;
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        if (num != null) {
            iB = num.intValue() | b(z2);
        } else {
            iB = b(z2);
        }
        return a(new com.joaomgcd.taskerm.dialog.h(activity, str, str2, R.string.button_label_ok, R.string.button_label_cancel, i2, z2, iB, str3, i3, num2, list));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, String str, int i3, boolean z2, String str2) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, str, R.string.button_label_ok, R.string.button_label_cancel, i3, z2, 0, str2, 0, (Integer) null, 1664, (c.f.b.g) null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, String str, int i3, boolean z2, String str2, int i4, Object obj) {
        int i5 = (i4 & 8) != 0 ? -1 : i3;
        boolean z3 = (i4 & 16) != 0 ? false : z2;
        if ((i4 & 32) != 0) {
            str2 = (String) null;
        }
        return a(activity, i2, str, i5, z3, str2);
    }

    public static final b.a.l<String> a(Activity activity, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, Integer num, Integer num2, List<String> list) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(hVar, "title");
        return a(activity, hVar.a(), hVar3 != null ? hVar3.a() : null, 0, true, hVar2 != null ? hVar2.a() : null, num, num2 != null ? num2.intValue() : 0, null, list, 264, null).c(o.f6564a);
    }

    static final class o<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final o f6564a = new o();

        o() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            String strJ = xVar.j();
            if (strJ != null) {
                return strJ;
            }
            throw new RuntimeException("No text entered");
        }
    }

    public static final b.a.l<String> a(Activity activity, int i2, Integer num, com.joaomgcd.taskerm.util.h hVar, Integer num2, Integer num3, List<String> list) {
        c.f.b.k.b(activity, "activity");
        Activity activity2 = activity;
        return a(activity, new com.joaomgcd.taskerm.util.k(activity2, i2, new Object[0]), num != null ? new com.joaomgcd.taskerm.util.k(activity2, num.intValue(), new Object[0]) : null, hVar, num2, num3, list);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, int i3, int i4, boolean z2, String str) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, i3, R.string.button_label_ok, R.string.button_label_cancel, i4, z2, 0, str, 0, (Integer) null, 1664, (c.f.b.g) null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, int i3, int i4, boolean z2, String str, int i5, Object obj) {
        int i6 = (i5 & 8) != 0 ? -1 : i4;
        boolean z3 = (i5 & 16) != 0 ? false : z2;
        if ((i5 & 32) != 0) {
            str = (String) null;
        }
        return a(activity, i2, i3, i6, z3, str);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, int i3, int i4, boolean z2) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, i3, -1, R.string.button_label_cancel, i4, z2, 0, (String) null, 0, (Integer) null, 1920, (c.f.b.g) null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, int i3, int i4, boolean z2, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = -1;
        }
        if ((i5 & 16) != 0) {
            z2 = false;
        }
        return a(activity, i2, i3, i4, z2);
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, int i3, com.joaomgcd.taskerm.util.h hVar, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            hVar = (com.joaomgcd.taskerm.util.h) null;
        }
        return a(activity, i2, i3, hVar);
    }

    static final class bp extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bp f6527a = new bp();

        bp() {
            super(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.x(1, null, 2, null == true ? 1 : 0));
        }
    }

    static final class bq extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bq f6528a = new bq();

        bq() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            return Boolean.valueOf(a(xVar));
        }

        public final boolean a(com.joaomgcd.taskerm.dialog.x xVar) {
            return xVar.g();
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, int i3, com.joaomgcd.taskerm.util.h hVar) {
        c.f.b.k.b(activity, "activity");
        if (hVar == null) {
            hVar = new com.joaomgcd.taskerm.util.k(activity, i3, new Object[0]);
        }
        return com.joaomgcd.taskerm.q.b.a(activity, hVar, bp.f6527a, bq.f6528a, new br(activity, i2, i3));
    }

    static final class br extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6529a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6530b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f6531c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        br(Activity activity, int i, int i2) {
            super(0);
            this.f6529a = activity;
            this.f6530b = i;
            this.f6531c = i2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return y.a(this.f6529a, this.f6530b, this.f6531c, R.string.button_label_stop_reminding);
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6406a = new a();

        a() {
            super(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.x(1, null, 2, null == true ? 1 : 0));
        }
    }

    static final class b extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6478a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            return Boolean.valueOf(a(xVar));
        }

        public final boolean a(com.joaomgcd.taskerm.dialog.x xVar) {
            return xVar.g();
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6537a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.h f6538b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.h f6539c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.h f6540d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.util.h f6541e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Activity activity, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4) {
            super(0);
            this.f6537a = activity;
            this.f6538b = hVar;
            this.f6539c = hVar2;
            this.f6540d = hVar3;
            this.f6541e = hVar4;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return y.a(new com.joaomgcd.taskerm.dialog.h(this.f6537a, this.f6538b, this.f6539c, this.f6540d, this.f6541e, new com.joaomgcd.taskerm.util.k(this.f6537a, R.string.button_label_stop_reminding, new Object[0]), false, 0, null, 0, null, null, null, 8128, null));
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, com.joaomgcd.taskerm.util.h hVar, com.joaomgcd.taskerm.util.h hVar2, com.joaomgcd.taskerm.util.h hVar3, com.joaomgcd.taskerm.util.h hVar4, com.joaomgcd.taskerm.util.h hVar5) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(hVar, "title");
        c.f.b.k.b(hVar2, "text");
        c.f.b.k.b(hVar3, "positiveLabel");
        c.f.b.k.b(hVar4, "negativeLabel");
        c.f.b.k.b(hVar5, "overrideStopRemindingKey");
        return com.joaomgcd.taskerm.q.b.a(activity, hVar5, a.f6406a, b.f6478a, new c(activity, hVar, hVar2, hVar3, hVar4));
    }

    static final class v extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final v f6577a = new v();

        v() {
            super(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.x(1, null, 2, null == true ? 1 : 0));
        }
    }

    static final class w extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final w f6578a = new w();

        w() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            return Boolean.valueOf(a(xVar));
        }

        public final boolean a(com.joaomgcd.taskerm.dialog.x xVar) {
            return xVar.g();
        }
    }

    static final class x extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6579a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6580b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f6581c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        x(Activity activity, int i, String str) {
            super(0);
            this.f6579a = activity;
            this.f6580b = i;
            this.f6581c = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return y.a(this.f6579a, this.f6580b, this.f6581c, R.string.button_label_stop_reminding, false, (String) null, 48, (Object) null);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2, String str) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "text");
        return com.joaomgcd.taskerm.q.b.a(activity, new com.joaomgcd.taskerm.util.j(str), v.f6577a, w.f6578a, new x(activity, i2, str));
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$y, reason: collision with other inner class name */
    static final class C0132y extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0132y f6582a = new C0132y();

        C0132y() {
            super(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.x(1, null, 2, null == true ? 1 : 0));
        }
    }

    static final class z extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final z f6583a = new z();

        z() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            return Boolean.valueOf(a(xVar));
        }

        public final boolean a(com.joaomgcd.taskerm.dialog.x xVar) {
            return xVar.g();
        }
    }

    static final class aa extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6407a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6408b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f6409c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        aa(Activity activity, int i, int i2) {
            super(0);
            this.f6407a = activity;
            this.f6408b = i;
            this.f6409c = i2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return y.a(this.f6407a, this.f6408b, this.f6409c, R.string.button_label_stop_reminding, false, (String) null, 48, (Object) null);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity, int i2, int i3) {
        c.f.b.k.b(activity, "activity");
        Activity activity2 = activity;
        return com.joaomgcd.taskerm.q.b.a(activity2, new com.joaomgcd.taskerm.util.k(activity2, i3, new Object[0]), C0132y.f6582a, z.f6583a, new aa(activity, i2, i3));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity, int i2, int i3, int i4) {
        c.f.b.k.b(activity, "activity");
        return b(activity, i2, com.joaomgcd.taskerm.util.aj.a(i3, activity, new Object[0]), i4);
    }

    public static /* synthetic */ b.a.l b(Activity activity, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = -1;
        }
        return b(activity, i2, i3, i4);
    }

    static final class ab extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ab f6410a = new ab();

        ab() {
            super(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.x(1, null, 2, null == true ? 1 : 0));
        }
    }

    static final class ac extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ac f6411a = new ac();

        ac() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            return Boolean.valueOf(a(xVar));
        }

        public final boolean a(com.joaomgcd.taskerm.dialog.x xVar) {
            return xVar.g();
        }
    }

    public static /* synthetic */ b.a.l b(Activity activity, int i2, String str, int i3, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            i3 = -1;
        }
        return b(activity, i2, str, i3);
    }

    static final class ad extends c.f.b.l implements c.f.a.a<b.a.l<com.joaomgcd.taskerm.dialog.x>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6412a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6413b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f6414c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f6415d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ad(Activity activity, int i, String str, int i2) {
            super(0);
            this.f6412a = activity;
            this.f6413b = i;
            this.f6414c = str;
            this.f6415d = i2;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> invoke() {
            return y.a(new com.joaomgcd.taskerm.dialog.h(this.f6412a, this.f6413b, this.f6414c, R.string.button_label_ok, this.f6415d, R.string.button_label_stop_reminding, false, 0, (String) null, 0, (Integer) null, 1984, (c.f.b.g) null));
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity, int i2, String str, int i3) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "text");
        return com.joaomgcd.taskerm.q.b.a(activity, new com.joaomgcd.taskerm.util.j(str), ab.f6410a, ac.f6411a, new ad(activity, i2, str, i3));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, int i2) {
        c.f.b.k.b(activity, "activity");
        return b(activity, R.string.tip_dialog_title, i2, 0, 8, (Object) null);
    }

    static final class bn<T> implements b.a.d.f<com.joaomgcd.taskerm.dialog.x> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6524a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6525b;

        bn(Activity activity, String str) {
            this.f6524a = activity;
            this.f6525b = str;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.e()) {
                com.joaomgcd.taskerm.util.ah.a((Context) this.f6524a, this.f6525b, false, false, true, 6, (Object) null);
            }
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity, int i2, String str) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "learnMoreUrl");
        b.a.l<com.joaomgcd.taskerm.dialog.x> lVarB = b(activity, R.string.tip_dialog_title, i2, R.string.learn_more).b(new bn(activity, str));
        c.f.b.k.a((Object) lVarB, "dialogOkStopReminding(ac…reUrl, reuseTab = true)\n}");
        return lVarB;
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, String str) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "text");
        return b(activity, R.string.tip_dialog_title, str, 0, 8, (Object) null);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity, int i2) {
        c.f.b.k.b(activity, "activity");
        return d(activity, R.string.tip_dialog_title, i2);
    }

    public static final b.a.b.b c(Activity activity, int i2) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.a(b(activity, R.string.warning_dialog_title, i2, 0, 8, (Object) null), activity, bj.f6520a);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> c(Activity activity, int i2, String str) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, str, R.string.button_label_ok, 0, 0, false, 0, (String) null, 0, (Integer) null, 2032, (c.f.b.g) null));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, String str, String str2, com.joaomgcd.taskerm.util.h hVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "title");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, new com.joaomgcd.taskerm.util.j(str), str2 != null ? new com.joaomgcd.taskerm.util.j(str2) : null, new com.joaomgcd.taskerm.util.k(activity, R.string.button_label_ok, new Object[0]), hVar, null, false, 0, null, 0, null, null, null, 8160, null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, String str, String str2, com.joaomgcd.taskerm.util.h hVar, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            hVar = (com.joaomgcd.taskerm.util.h) null;
        }
        return a(activity, str, str2, hVar);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> d(Activity activity, int i2, int i3) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, i2, i3, R.string.button_label_ok, 0, 0, false, 0, (String) null, 0, (Integer) null, 2032, (c.f.b.g) null));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> d(Activity activity, int i2) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.warning_dialog_title, com.joaomgcd.taskerm.util.f.f9323b.b(activity, i2), 0, 0, 0, false, 0, (String) null, 0, (Integer) null, 2040, (c.f.b.g) null));
    }

    public static /* synthetic */ b.a.l a(Activity activity, fy fyVar, String str, List list, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = (String) null;
        }
        if ((i2 & 8) != 0) {
            list = fyVar != null ? fyVar.b(-2, fs.c.Alpha) : null;
        }
        if ((i2 & 16) != 0) {
            z2 = false;
        }
        return a(activity, fyVar, str, (List<? extends fs>) list, z2);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0093  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final b.a.l<com.joaomgcd.taskerm.dialog.ae<fs>> a(Activity activity, fy fyVar, String str, List<? extends fs> list, boolean z2) {
        Integer numValueOf;
        boolean z3;
        c.f.b.k.b(activity, "activity");
        Bundle bundle = null;
        if (list == null) {
            return null;
        }
        if (z2 && list.size() == 1) {
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.ae(new com.joaomgcd.taskerm.dialog.ab(3, bundle, 2, null == true ? 1 : 0), list.get(0), null, 4, null));
        }
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            numValueOf = null;
        } else {
            Iterator<? extends fs> it = list.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                }
                fs next = it.next();
                if (next.l() != null) {
                    String strL = next.l();
                    c.f.b.k.a((Object) strL, "it.name");
                    if (strL == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase = strL.toLowerCase();
                    c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                    if (str == null) {
                        throw new c.q("null cannot be cast to non-null type java.lang.String");
                    }
                    String lowerCase2 = str.toLowerCase();
                    c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                    if (c.f.b.k.a((Object) lowerCase, (Object) lowerCase2)) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                } else {
                    z3 = false;
                }
                if (z3) {
                    break;
                }
                i2++;
            }
            numValueOf = Integer.valueOf(i2);
        }
        return com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.taskselect_title, list, true, bf.f6499a, null, null, null, null, null, numValueOf, null, null, null, null, null, 64480, null));
    }

    static final class bf extends c.f.b.l implements c.f.a.b<fs, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bf f6499a = new bf();

        bf() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(fs fsVar) {
            c.f.b.k.b(fsVar, "it");
            String strL = fsVar.l();
            c.f.b.k.a((Object) strL, "it.name");
            return new com.joaomgcd.taskerm.dialog.w(strL, fsVar.C(), false, 4, null);
        }
    }

    static final class bh extends c.f.b.l implements c.f.a.b<String, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bh f6501a = new bh();

        bh() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(String str) {
            c.f.b.k.b(str, "it");
            return new com.joaomgcd.taskerm.dialog.w(str, null, false, 6, null);
        }
    }

    public static final b.a.l<String> a(Activity activity, List<String> list) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(list, "dataVars");
        b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.dialog_title_variable_select, list, false, bh.f6501a, null, null, null, null, null, null, null, null, null, null, null, 65504, null)).c(bg.f6500a);
        c.f.b.k.a((Object) lVarC, "dialogSingleChoice(args)…)\n\n        selected\n    }");
        return lVarC;
    }

    static final class bg<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bg f6500a = new bg();

        bg() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.ae<String> aeVar) {
            c.f.b.k.b(aeVar, "it");
            String strB = aeVar.b();
            if (strB != null) {
                return !c.l.n.a(strB, "%", false, 2, (Object) null) ? dh.a(strB) : strB;
            }
            throw new RuntimeException("No variable selected");
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_need_notification_listener, R.string.dc_need_notification_listener, R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null));
    }

    static final class av extends c.f.b.l implements c.f.a.a<cl> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6456a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.securesettings.p f6457b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6458c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        av(Activity activity, com.joaomgcd.taskerm.securesettings.p pVar, boolean z) {
            super(0);
            this.f6456a = activity;
            this.f6457b = pVar;
            this.f6458c = z;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$av$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Boolean, com.joaomgcd.taskerm.securesettings.ai> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ com.joaomgcd.taskerm.securesettings.ai invoke(Boolean bool) {
                return a(bool.booleanValue());
            }

            public static /* synthetic */ com.joaomgcd.taskerm.securesettings.ai a(AnonymousClass1 anonymousClass1, boolean z, int i, Object obj) {
                if ((i & 1) != 0) {
                    z = false;
                }
                return anonymousClass1.a(z);
            }

            public final com.joaomgcd.taskerm.securesettings.ai a(boolean z) {
                com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, av.this.f6456a, R.string.dc_getting_settings, false, 4, null);
                com.joaomgcd.taskerm.securesettings.ai aiVarB = com.joaomgcd.taskerm.securesettings.ai.f8479a.a(av.this.f6456a, z ? av.this.f6457b : null).b();
                com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                c.f.b.k.a((Object) aiVarB, "result");
                return aiVarB;
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cl invoke() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            com.joaomgcd.taskerm.securesettings.ai aiVar = y.f6405a;
            if (aiVar != null) {
                y.f6405a = (com.joaomgcd.taskerm.securesettings.ai) null;
                com.joaomgcd.taskerm.securesettings.aj ajVarB = AnonymousClass1.a(anonymousClass1, false, 1, null).a(aiVar).b();
                if (ajVarB.size() == 0) {
                    y.a(new com.joaomgcd.taskerm.dialog.h(this.f6456a, R.string.dialog_title_setting, R.string.no_differences_found, 0, 0, 0, false, 0, (String) null, 0, (Integer) null, 2040, (c.f.b.g) null)).b();
                    return new cm("no differences found");
                }
                Activity activity = this.f6456a;
                int i = R.string.dialog_title_setting;
                c.f.b.k.a((Object) ajVarB, "differences");
                com.joaomgcd.taskerm.securesettings.s sVar = (com.joaomgcd.taskerm.securesettings.s) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, i, ajVarB, false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b();
                if (sVar == null) {
                    return new cm("no setting selected");
                }
                if (this.f6458c && sVar.a().g() && sVar.b().g() && ((com.joaomgcd.taskerm.dialog.x) y.a(this.f6456a, R.string.dialog_title_setting, R.string.dc_is_toggle_custom_setting_want, 0, 8, (Object) null).b()).d()) {
                    sVar.b().a(com.joaomgcd.taskerm.securesettings.aa.f8460b);
                }
                return new cp(sVar.b());
            }
            if (!y.a(new com.joaomgcd.taskerm.dialog.h(this.f6456a, R.string.dialog_title_setting, R.string.dc_want_to_find_or_list_setting, R.string.ml_find, R.string.dialog_title_setting, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).b().e()) {
                y.f6405a = AnonymousClass1.a(anonymousClass1, false, 1, null);
                y.a(new com.joaomgcd.taskerm.dialog.h(this.f6456a, R.string.dialog_title_setting, R.string.dc_ok_change_setting_then_come_back_and_press_again, 0, 0, 0, false, 0, (String) null, 0, (Integer) null, 2040, (c.f.b.g) null)).b();
                return new co();
            }
            com.joaomgcd.taskerm.securesettings.r rVar = (com.joaomgcd.taskerm.securesettings.r) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6456a, R.string.dialog_title_setting, anonymousClass1.a(true), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b();
            if (rVar == null) {
                return new cm("no setting selected");
            }
            return new cp(rVar);
        }
    }

    public static final b.a.l<cl> a(Activity activity, com.joaomgcd.taskerm.securesettings.p pVar, boolean z2) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(pVar, "selectedSecureSettingType");
        return com.joaomgcd.taskerm.rx.i.b(new av(activity, pVar, z2));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> b(Activity activity) {
        c.f.b.k.b(activity, "activity");
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            b.a.l<com.joaomgcd.taskerm.dialog.x> lVarA = b.a.l.a((b.a.o) new bo(activity));
            c.f.b.k.a((Object) lVarA, "Single.create<DialogMess…GATIVE_BUTTON))\n        }");
            return lVarA;
        }
        return a(activity, "volume_long_press_grant", "android.permission.SET_VOLUME_KEY_LONG_PRESS_LISTENER", R.string.dc_volume_key_permission_explain, false, false, false, 112, (Object) null);
    }

    static final class bo<T> implements b.a.o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6526a;

        bo(Activity activity) {
            this.f6526a = activity;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.o
        public final void a(b.a.m<com.joaomgcd.taskerm.dialog.x> mVar) {
            c.f.b.k.b(mVar, "it");
            com.joaomgcd.taskerm.util.ak.c("Need at least Android 8 to handle long volume press events", this.f6526a);
            mVar.a(new com.joaomgcd.taskerm.dialog.x(1, null, 2, null == true ? 1 : 0));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> c(Activity activity) {
        c.f.b.k.b(activity, "activity");
        if (com.joaomgcd.taskerm.util.f.f9323b.o()) {
            b.a.l<com.joaomgcd.taskerm.dialog.x> lVarA = b.a.l.a(new com.joaomgcd.taskerm.dialog.x(0, null, 2, null == true ? 1 : 0));
            c.f.b.k.a((Object) lVarA, "Single.just(DialogMessag…nt.WHAT_POSITIVE_BUTTON))");
            return lVarA;
        }
        return a(activity, "media_key_listener_grant", "android.permission.SET_MEDIA_KEY_LISTENER", R.string.dc_media_key_listener_permission_explain, false, false, false, 112, (Object) null);
    }

    static final class g<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6551a;

        g(Activity activity) {
            this.f6551a = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> apply(Boolean bool) {
            c.f.b.k.b(bool, "hasRoot");
            if (bool.booleanValue()) {
                return y.d(this.f6551a, R.string.an_adb_wifi, R.string.dc_adb_wifi_root_not_needed);
            }
            return y.a(this.f6551a, "adb_wifi", "android.permission.ADB_WIFI", R.string.dc_adb_wifi_permission_explain, false, false, false, 80, (Object) null);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> d(Activity activity) {
        c.f.b.k.b(activity, "activity");
        b.a.l lVarA = net.dinglisch.android.taskerm.az.b(activity).a(new g(activity));
        c.f.b.k.a((Object) lVarA, "Init.canRootWithDialog(a… = false)\n        }\n    }");
        return lVarA;
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> e(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(activity, "device_owner", "android.permission.BIND_DEVICE_ADMIN", R.string.dc_device_owner_permission_explain, false, false, false, 112, (Object) null);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> f(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(activity, "dump_grant", "android.permission.DUMP", R.string.dc_dump_permission_explain, false, false, false, 112, (Object) null);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> g(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return b(activity, R.string.an_screen_capture, R.string.dc_screencapture_permission_explain_short).a(new au(activity));
    }

    static final class au<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6455a;

        au(Activity activity) {
            this.f6455a = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return !xVar.d() ? b.a.l.a(xVar) : y.h(this.f6455a);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> h(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(activity, "screencapture_grant", "PROJECT_MEDIA", R.string.dc_screencapture_permission_explain, false, false, true, 48, (Object) null);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> i(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return e(activity, R.string.dc_read_logs_permission_logcat_explain);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> e(Activity activity, int i2) {
        c.f.b.k.b(activity, "activity");
        return a(activity, "read_logs_grant", "android.permission.READ_LOGS", i2, true, false, false, 96, (Object) null);
    }

    public static /* synthetic */ b.a.l a(Activity activity, String str, String str2, int i2, boolean z2, boolean z3, boolean z4, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            str = "secure_setting_grant";
        }
        if ((i3 & 4) != 0) {
            str2 = "android.permission.WRITE_SECURE_SETTINGS";
        }
        return a(activity, str, str2, (i3 & 8) != 0 ? R.string.dc_secure_settings_permission_explain : i2, (i3 & 16) != 0 ? false : z2, (i3 & 32) != 0 ? true : z3, (i3 & 64) != 0 ? false : z4);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(Activity activity, String str, String str2, int i2, boolean z2, boolean z3, boolean z4) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "helpId");
        c.f.b.k.b(str2, "permission");
        b.a.l lVarA = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(i2, activity, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).a(new aw(activity, str, z3, z4, str2, z2));
        c.f.b.k.a((Object) lVarA, "dialogTextBox(\n        A…Single.just(it)\n        }");
        return lVarA;
    }

    static final class aw<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6460a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6461b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6462c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6463d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f6464e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f6465f;

        aw(Activity activity, String str, boolean z, boolean z2, String str2, boolean z3) {
            this.f6460a = activity;
            this.f6461b = str;
            this.f6462c = z;
            this.f6463d = z2;
            this.f6464e = str2;
            this.f6465f = z3;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.x> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass1());
            }
            return b.a.l.a(xVar);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$aw$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.dialog.x> {
            AnonymousClass1() {
                super(0);
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$aw$1$1, reason: invalid class name and collision with other inner class name */
            static final class C01291 extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.dialog.x> {
                C01291() {
                    super(0);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final com.joaomgcd.taskerm.dialog.x invoke() {
                    int i = 2;
                    Bundle bundle = null;
                    com.joaomgcd.taskerm.util.bt btVarA = com.joaomgcd.taskerm.util.bt.a.a(com.joaomgcd.taskerm.util.bt.f9101c, (Context) aw.this.f6460a, 0, 2, (Object) null);
                    if (!btVarA.f()) {
                        y.d(aw.this.f6460a, R.string.dt_storage_access, R.string.dc_to_access_help_tasker_needs_storage).d(new b.a.d.g<Throwable, b.a.p<? extends com.joaomgcd.taskerm.dialog.x>>() { // from class: com.joaomgcd.taskerm.dialog.y.aw.1.1.1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // b.a.d.g
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public final b.a.l<com.joaomgcd.taskerm.dialog.x> apply(Throwable th) {
                                c.f.b.k.b(th, "<anonymous parameter 0>");
                                return b.a.l.a(new com.joaomgcd.taskerm.dialog.x(0, null, 2, null == true ? 1 : 0));
                            }
                        }).b();
                    }
                    if (!btVarA.b(aw.this.f6460a).b().b()) {
                        return new com.joaomgcd.taskerm.dialog.x(1, bundle, i, null == true ? 1 : 0);
                    }
                    ActionEdit.a(aw.this.f6460a, aw.this.f6461b, (String) null);
                    return new com.joaomgcd.taskerm.dialog.x(0, null == true ? 1 : 0, i, null == true ? 1 : 0);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.x invoke() {
                StringBuilder sb;
                String str;
                C01291 c01291 = new C01291();
                if (!aw.this.f6462c || !net.dinglisch.android.taskerm.az.b(aw.this.f6460a).b().booleanValue()) {
                    return c01291.invoke();
                }
                if (!y.a(new com.joaomgcd.taskerm.dialog.h(aw.this.f6460a, R.string.pl_use_root, R.string.dc_since_device_rooted_grant_perimssion, R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).b().d()) {
                    return c01291.invoke();
                }
                if (aw.this.f6463d) {
                    sb = new StringBuilder();
                    sb.append("appops set ");
                    sb.append(aw.this.f6460a.getPackageName());
                    sb.append(' ');
                    sb.append(aw.this.f6464e);
                    str = " allow";
                } else {
                    sb = new StringBuilder();
                    sb.append("pm grant ");
                    sb.append(aw.this.f6460a.getPackageName());
                    sb.append(' ');
                    str = aw.this.f6464e;
                }
                sb.append(str);
                if (ck.f9218a.a(new com.joaomgcd.taskerm.util.p(sb.toString(), true, 10000L, false, 8, null)).b().a()) {
                    y.a(new com.joaomgcd.taskerm.dialog.h(aw.this.f6460a, R.string.button_label_done, R.string.dc_permissions_granted, R.string.button_label_ok, 0, 0, false, 0, (String) null, 0, (Integer) null, 2032, (c.f.b.g) null)).b();
                }
                if (aw.this.f6465f) {
                    y.d(aw.this.f6460a, R.string.dt_restart_needed, R.string.dc_restart_needed_permissions).b();
                    ck.f9218a.a(new com.joaomgcd.taskerm.util.p("am force-stop " + aw.this.f6460a.getPackageName(), true, 10000L, false, 8, null)).b();
                }
                return new com.joaomgcd.taskerm.dialog.x(0, null, 2, null == true ? 1 : 0);
            }
        }
    }

    public static /* synthetic */ b.a.l a(Activity activity, cm cmVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            cmVar = com.joaomgcd.taskerm.util.bt.f9101c.b(activity);
        }
        return a(activity, cmVar);
    }

    public static final b.a.l<cl> a(Activity activity, cm cmVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cmVar, "error");
        Activity activity2 = activity;
        if (com.joaomgcd.taskerm.util.bt.a.k(com.joaomgcd.taskerm.util.bt.f9101c, activity2, 0, 2, null).g()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.l lVarA2 = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.dc_notification_access_permission_explain, activity2, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).a(new t(activity, cmVar));
        c.f.b.k.a((Object) lVarA2, "dialogTextBox(\n         …st(error)\n        }\n    }");
        return lVarA2;
    }

    static final class t<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6574a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ cm f6575b;

        t(Activity activity, cm cmVar) {
            this.f6574a = activity;
            this.f6575b = cmVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                return new GenericActionActivityRequestNotificationAccess().run(this.f6574a);
            }
            return b.a.l.a(this.f6575b);
        }
    }

    public static /* synthetic */ b.a.l b(Activity activity, cm cmVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            cmVar = com.joaomgcd.taskerm.util.bt.f9101c.b(activity);
        }
        return b(activity, cmVar);
    }

    public static final b.a.l<cl> b(Activity activity, cm cmVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cmVar, "error");
        Activity activity2 = activity;
        if (com.joaomgcd.taskerm.util.bt.a.l(com.joaomgcd.taskerm.util.bt.f9101c, activity2, 0, 2, null).g()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.l lVarA2 = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.dc_manage_external_storage_permission_explain, activity2, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).a(new s(activity, cmVar));
        c.f.b.k.a((Object) lVarA2, "dialogTextBox(\n         …st(error)\n        }\n    }");
        return lVarA2;
    }

    static final class s<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6572a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ cm f6573b;

        s(Activity activity, cm cmVar) {
            this.f6572a = activity;
            this.f6573b = cmVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                return new GenericActionActivityRequestManageExternalStorageAccess().run(this.f6572a);
            }
            return b.a.l.a(this.f6573b);
        }
    }

    public static /* synthetic */ b.a.l c(Activity activity, cm cmVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            cmVar = com.joaomgcd.taskerm.util.bt.f9101c.b(activity);
        }
        return c(activity, cmVar);
    }

    public static final b.a.l<cl> c(Activity activity, cm cmVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cmVar, "error");
        Activity activity2 = activity;
        if (com.joaomgcd.taskerm.util.bt.a.l(com.joaomgcd.taskerm.util.bt.f9101c, activity2, 0, 2, null).g()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.l lVarA2 = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.background_location_permission_justification, activity2, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).a(new p(activity, cmVar));
        c.f.b.k.a((Object) lVarA2, "dialogTextBox(\n         …st(error)\n        }\n    }");
        return lVarA2;
    }

    static final class p<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6565a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ cm f6566b;

        p(Activity activity, cm cmVar) {
            this.f6565a = activity;
            this.f6566b = cmVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                return new GenericActionActivityRequestMissingPermissions(new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"}, null, 2, 0 == true ? 1 : 0).run(this.f6565a);
            }
            return b.a.l.a(this.f6566b);
        }
    }

    public static /* synthetic */ b.a.l d(Activity activity, cm cmVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            cmVar = com.joaomgcd.taskerm.util.bt.f9101c.b(activity);
        }
        return d(activity, cmVar);
    }

    public static final b.a.l<cl> d(Activity activity, cm cmVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cmVar, "error");
        if (com.joaomgcd.taskerm.util.f.f9323b.n()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        Activity activity2 = activity;
        if (com.joaomgcd.taskerm.util.bt.a.m(com.joaomgcd.taskerm.util.bt.f9101c, activity2, 0, 2, null).g()) {
            b.a.l<cl> lVarA2 = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA2, "Single.just(SimpleResultSuccess())");
            return lVarA2;
        }
        b.a.l lVarA3 = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.dc_call_screener_explain, activity2, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).a(new q(activity, cmVar));
        c.f.b.k.a((Object) lVarA3, "dialogTextBox(\n         …st(error)\n        }\n    }");
        return lVarA3;
    }

    static final class q<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6567a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ cm f6568b;

        q(Activity activity, cm cmVar) {
            this.f6567a = activity;
            this.f6568b = cmVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                return new GenericActionActivityRequestCallScreeningAccess().run(this.f6567a);
            }
            return b.a.l.a(this.f6568b);
        }
    }

    public static /* synthetic */ b.a.l e(Activity activity, cm cmVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            cmVar = com.joaomgcd.taskerm.util.bt.f9101c.b(activity);
        }
        return e(activity, cmVar);
    }

    public static final b.a.l<cl> e(Activity activity, cm cmVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cmVar, "error");
        Activity activity2 = activity;
        if (com.joaomgcd.taskerm.util.bt.a.o(com.joaomgcd.taskerm.util.bt.f9101c, activity2, 0, 2, null).g()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.l lVarA2 = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.dc_need_root_access, activity2, new Object[0]), R.string.button_label_ok, 0, 0, false, 0, (String) null, 0, (Integer) null, 2032, (c.f.b.g) null)).a(new u(cmVar));
        c.f.b.k.a((Object) lVarA2, "dialogTextBox(\n         … Single.just(error)\n    }");
        return lVarA2;
    }

    static final class u<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ cm f6576a;

        u(cm cmVar) {
            this.f6576a = cmVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<cm> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return b.a.l.a(this.f6576a);
        }
    }

    public static /* synthetic */ b.a.l f(Activity activity, cm cmVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            cmVar = com.joaomgcd.taskerm.util.bt.f9101c.b(activity);
        }
        return f(activity, cmVar);
    }

    public static final b.a.l<cl> f(Activity activity, cm cmVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(cmVar, "error");
        Activity activity2 = activity;
        if (com.joaomgcd.taskerm.util.bt.a.n(com.joaomgcd.taskerm.util.bt.f9101c, activity2, 0, 2, null).g()) {
            b.a.l<cl> lVarA = b.a.l.a(new co());
            c.f.b.k.a((Object) lVarA, "Single.just(SimpleResultSuccess())");
            return lVarA;
        }
        b.a.l lVarA2 = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.dc_need_dnd_access, activity2, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).a(new r(activity, cmVar));
        c.f.b.k.a((Object) lVarA2, "dialogTextBox(\n         …st(error)\n        }\n    }");
        return lVarA2;
    }

    static final class r<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6569a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ cm f6570b;

        r(Activity activity, cm cmVar) {
            this.f6569a = activity;
            this.f6570b = cmVar;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<? extends cl> apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            if (xVar.d()) {
                return new GenericActionActivityRequestDoNotDisturbAccess().run(this.f6569a).d(new b.a.d.g<Throwable, b.a.p<? extends cl>>() { // from class: com.joaomgcd.taskerm.dialog.y.r.1
                    @Override // b.a.d.g
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public final b.a.l<cm> apply(Throwable th) {
                        c.f.b.k.b(th, "it");
                        return b.a.l.a(r.this.f6570b);
                    }
                });
            }
            return b.a.l.a(this.f6570b);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> k(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.dt_missing_permissions, com.joaomgcd.taskerm.util.aj.a(R.string.dc_draw_overlay_permission_explain, activity, new Object[0]), R.string.button_label_ok, R.string.button_label_no, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> a(com.joaomgcd.taskerm.dialog.c cVar) {
        c.f.b.k.b(cVar, "args");
        return com.joaomgcd.taskerm.rx.i.b(new n(cVar));
    }

    static final class n extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.dialog.x> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.dialog.c f6563a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        n(com.joaomgcd.taskerm.dialog.c cVar) {
            super(0);
            this.f6563a = cVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.x invoke() {
            int i = 2;
            Bundle bundle = null;
            try {
                new ActionDialogHTML(this.f6563a.b(), this.f6563a.c()).run(this.f6563a.a()).b();
                return new com.joaomgcd.taskerm.dialog.x(0, bundle, i, null == true ? 1 : 0);
            } catch (Exception unused) {
                return new com.joaomgcd.taskerm.dialog.x(1, null == true ? 1 : 0, i, null == true ? 1 : 0);
            }
        }
    }

    static final class i extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6553a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(Activity activity) {
            super(1);
            this.f6553a = activity;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            a(ahVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            c.f.b.k.b(ahVar, "it");
            gm.a(this.f6553a, ahVar).a(this.f6553a);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> l(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return a(new i(activity), (c.f.a.b) null, 2, (Object) null);
    }

    public static final b.a.l<cq<String, com.joaomgcd.taskerm.util.ad>> a(Context context) {
        c.f.b.k.b(context, "context");
        return a(context, net.dinglisch.android.taskerm.cq.a(), (List) null, false, 12, (Object) null);
    }

    public static final b.a.l<cq<String, com.joaomgcd.taskerm.util.ad>> a(Context context, Intent intent, List<String> list, boolean z2) {
        c.f.b.k.b(context, "context");
        b.a.l lVarC = new GenericActionActivitySelectApp(intent, list, z2).run(context).c(ax.f6469a);
        c.f.b.k.a((Object) lVarC, "GenericActionActivitySel…Unknown error\"))\n    }\n\n}");
        return lVarC;
    }

    public static /* synthetic */ b.a.l a(Context context, Intent intent, List list, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            intent = (Intent) null;
        }
        if ((i2 & 4) != 0) {
            list = (List) null;
        }
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        return a(context, intent, (List<String>) list, z2);
    }

    static final class ax<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final ax f6469a = new ax();

        ax() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final cq<String, com.joaomgcd.taskerm.util.ad> apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            if (clVar instanceof cq) {
                return (cq) clVar;
            }
            return clVar instanceof cm ? new cq<>(false, (String) null, new com.joaomgcd.taskerm.util.ad(((cm) clVar).c())) : new cq<>(false, (String) null, new com.joaomgcd.taskerm.util.ad("Unknown error"));
        }
    }

    public static final class be implements com.joaomgcd.taskerm.helper.x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f6497a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f6498b;

        public be(Context context, String str) {
            c.f.b.k.b(context, "context");
            c.f.b.k.b(str, net.dinglisch.android.taskerm.be.EXTRA_ID);
            this.f6497a = context;
            this.f6498b = str;
        }

        @Override // com.joaomgcd.taskerm.helper.x
        public String a() {
            return this.f6498b;
        }

        public String toString() {
            return bu.a(a(), this.f6497a) + " (" + c.l.n.a(a(), (CharSequence) "android.permission.") + ')';
        }
    }

    public static final b.a.l<String> b(Activity activity, List<String> list) {
        List<String> listL;
        c.f.b.k.b(activity, "activity");
        if (list != null) {
            listL = list;
        } else {
            String[] strArr = net.dinglisch.android.taskerm.bp.f11897e;
            c.f.b.k.a((Object) strArr, "KNOWN_PERMS");
            listL = c.a.d.l(strArr);
            c.a.j.a(listL, com.joaomgcd.taskerm.util.bt.f9101c.b());
        }
        List<String> list2 = listL;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new be(activity, (String) it.next()));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (hashSet.add(((be) obj).toString())) {
                arrayList2.add(obj);
            }
        }
        b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.app_class_permissions, arrayList2, false, null, null, null, true, null, null, null, null, null, null, null, null, 65400, null)).c(bd.f6496a);
        c.f.b.k.a((Object) lVarC, "dialogSingleChoice(Dialo… selected\")\n            }");
        return lVarC;
    }

    static final class bd<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final bd f6496a = new bd();

        bd() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.ae<be> aeVar) {
            String strA;
            c.f.b.k.b(aeVar, "it");
            be beVarB = aeVar.b();
            if (beVarB == null || (strA = beVarB.a()) == null) {
                throw new RuntimeException("No permission selected");
            }
            return strA;
        }
    }

    @TargetApi(26)
    public static /* synthetic */ b.a.l a(Activity activity, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = com.joaomgcd.taskerm.notification.at.f8046b.d().a();
        }
        return b(activity, str);
    }

    static final class bc extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.dialog.ae<NotificationChannel>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6493a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6494b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        bc(Activity activity, String str) {
            super(0);
            this.f6493a = activity;
            this.f6494b = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.ae<NotificationChannel> invoke() {
            List<NotificationChannel> listA;
            com.joaomgcd.taskerm.dialog.ae<NotificationChannel> aeVar = new com.joaomgcd.taskerm.dialog.ae<>();
            if (com.joaomgcd.taskerm.util.f.f9323b.o() || (listA = com.joaomgcd.taskerm.notification.al.a(this.f6493a, this.f6494b)) == null) {
                return aeVar;
            }
            if (listA.isEmpty()) {
                y.d(this.f6493a, R.string.dc_notification_category, R.string.dc_you_didnt_create_any_categories_yet).b();
                return aeVar;
            }
            com.joaomgcd.taskerm.dialog.ae<NotificationChannel> aeVar2 = (com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6493a, R.string.dc_notification_category, listA, false, a.f6495a, Integer.valueOf(R.string.tip_long_click_to_delete), null, null, null, null, null, null, null, null, null, null, 65480, null)).b();
            if (!aeVar2.a().o()) {
                return aeVar2;
            }
            com.joaomgcd.taskerm.util.ak.b(com.joaomgcd.taskerm.util.aj.a(R.string.message_deleted, this.f6493a, new Object[0]), this.f6493a);
            y.b(this.f6493a, R.string.dc_notification_category, R.string.dc_delete_categories_doesnt_change_properties, 0, 8, (Object) null).b();
            Activity activity = this.f6493a;
            NotificationChannel notificationChannelB = aeVar2.b();
            com.joaomgcd.taskerm.notification.al.c(activity, notificationChannelB != null ? notificationChannelB.getId() : null);
            return (com.joaomgcd.taskerm.dialog.ae) y.a(this.f6493a, (String) null, 2, (Object) null).b();
        }

        static final class a extends c.f.b.l implements c.f.a.b<NotificationChannel, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final a f6495a = new a();

            a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(NotificationChannel notificationChannel) {
                c.f.b.k.b(notificationChannel, "it");
                return new com.joaomgcd.taskerm.dialog.w(notificationChannel.getName().toString(), null, false, 6, null);
            }
        }
    }

    @TargetApi(26)
    public static final b.a.l<com.joaomgcd.taskerm.dialog.ae<NotificationChannel>> b(Activity activity, String str) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.b(new bc(activity, str));
    }

    static final class ay extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6470a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ay(Activity activity) {
            super(0);
            this.f6470a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            String strInvoke;
            com.joaomgcd.taskerm.dialog.i iVar = (com.joaomgcd.taskerm.dialog.i) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6470a, R.string.dt_select_time_option, c.a.j.d(new com.joaomgcd.taskerm.dialog.i(this.f6470a, R.string.dc_wait_for_some_time_button_press, new a()), new com.joaomgcd.taskerm.dialog.i(this.f6470a, R.string.dc_trigger_every_second_while_button_pressed, new b())), false, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).b()).b();
            return (iVar == null || (strInvoke = iVar.b().invoke()) == null) ? "" : strInvoke;
        }

        static final class a extends c.f.b.l implements c.f.a.a<String> {
            a() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return ((com.joaomgcd.taskerm.dialog.x) y.a(ay.this.f6470a, R.string.dc_how_much_time_to_wait_to_trigger, "1", 0, true, (String) null, 40, (Object) null).b()).j();
            }
        }

        static final class b extends c.f.b.l implements c.f.a.a<String> {
            b() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String strJ = ((com.joaomgcd.taskerm.dialog.x) y.a(ay.this.f6470a, R.string.dc_how_much_time_to_wait_to_trigger, "0", 0, true, (String) null, 40, (Object) null).b()).j();
                String str = strJ;
                if (str == null || str.length() == 0) {
                    return "";
                }
                String strJ2 = ((com.joaomgcd.taskerm.dialog.x) y.a(ay.this.f6470a, R.string.dc_stop_triggering_at_which_time, "", 0, true, (String) null, 40, (Object) null).b()).j();
                if (strJ2 == null) {
                    strJ2 = "";
                }
                return com.joaomgcd.taskerm.media.a.f7764b.a(strJ, strJ2);
            }
        }
    }

    public static final b.a.l<String> m(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.b(new ay(activity));
    }

    static final class af extends c.f.b.l implements c.f.a.b<BluetoothDevice, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final af f6418a = new af();

        af() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(BluetoothDevice bluetoothDevice) {
            c.f.b.k.a((Object) bluetoothDevice, "it");
            return new com.joaomgcd.taskerm.dialog.w(com.joaomgcd.taskerm.util.aj.h(bluetoothDevice), null, false, 6, null);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.ae<BluetoothDevice>> n(Activity activity) {
        c.f.b.k.b(activity, "activity");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        c.f.b.k.a((Object) defaultAdapter, "BluetoothAdapter.getDefaultAdapter()");
        Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
        c.f.b.k.a((Object) bondedDevices, "BluetoothAdapter.getDefaultAdapter().bondedDevices");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : bondedDevices) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) obj;
            c.f.b.k.a((Object) bluetoothDevice, "it");
            if (hashSet.add(bluetoothDevice.getAddress())) {
                arrayList.add(obj);
            }
        }
        return com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.an_bluetooth_connection, c.a.j.i((Iterable) arrayList), true, af.f6418a, null, null, null, null, null, null, null, null, null, null, null, 65504, null));
    }

    static final class j<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f6554a = new j();

        j() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.ae<String> aeVar) {
            c.f.b.k.b(aeVar, "it");
            String strB = aeVar.b();
            if (strB == null) {
                throw new RuntimeException("Nothing selected");
            }
            if (strB == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = strB.toLowerCase();
            c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
            return String.valueOf(Boolean.parseBoolean(lowerCase));
        }
    }

    public static final b.a.l<String> a(Activity activity, com.joaomgcd.taskerm.util.i iVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(iVar, "title");
        Activity activity2 = activity;
        String strA = iVar.a(activity2);
        Integer[] numArr = {Integer.valueOf(R.string.word_true), Integer.valueOf(R.string.word_false)};
        ArrayList arrayList = new ArrayList(numArr.length);
        for (Integer num : numArr) {
            arrayList.add(com.joaomgcd.taskerm.util.aj.a(num.intValue(), activity2, new Object[0]));
        }
        b.a.l<String> lVarC = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, strA, arrayList, false, null, null, null, null, null, null, null, null, null, null, null, null, null, 131064, null)).c(j.f6554a);
        c.f.b.k.a((Object) lVarC, "dialogSingleChoice(Dialo…oolean().toString()\n    }");
        return lVarC;
    }

    private static final b.a.l<String> a(Activity activity, net.dinglisch.android.taskerm.aw.a aVar, boolean z2) {
        b.a.l<String> lVarC = a(new k(activity, aVar, z2), (c.f.a.b) null, 2, (Object) null).c(new l(activity));
        c.f.b.k.a((Object) lVarC, "withHandler({\n    IconSe…ception(\"Invalid icon\")\n}");
        return lVarC;
    }

    static final class k extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6555a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ net.dinglisch.android.taskerm.aw.a f6556b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6557c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(Activity activity, net.dinglisch.android.taskerm.aw.a aVar, boolean z) {
            super(1);
            this.f6555a = activity;
            this.f6556b = aVar;
            this.f6557c = z;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            a(ahVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            c.f.b.k.b(ahVar, "it");
            net.dinglisch.android.taskerm.aw.a((Context) this.f6555a, (Handler) ahVar, this.f6556b, this.f6557c, false).a(this.f6555a);
        }
    }

    static final class l<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6558a;

        l(Activity activity) {
            this.f6558a = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(com.joaomgcd.taskerm.dialog.x xVar) {
            Integer numL;
            String string;
            c.f.b.k.b(xVar, "it");
            Bundle bundleM = xVar.m();
            if (bundleM == null || (numL = xVar.l()) == null || numL.intValue() != 1) {
                throw new RuntimeException("Not selected");
            }
            String string2 = bundleM.getString(ProfileManager.EXTRA_PROFILE_NAME);
            int i = bundleM.getInt("tint", 0);
            net.dinglisch.android.taskerm.g gVar = new net.dinglisch.android.taskerm.g(string2);
            if (i != 0) {
                gVar.c(i);
            }
            Uri uriC = gVar.c(this.f6558a);
            if (uriC == null || (string = uriC.toString()) == null) {
                throw new RuntimeException("Invalid icon");
            }
            return string;
        }
    }

    public static final b.a.l<String> a(Activity activity, boolean z2) {
        c.f.b.k.b(activity, "activity");
        return a(activity, net.dinglisch.android.taskerm.aw.a.Holo, z2);
    }

    public static /* synthetic */ b.a.l a(Activity activity, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return a(activity, z2);
    }

    public static final b.a.l<String> b(Activity activity, boolean z2) {
        c.f.b.k.b(activity, "activity");
        return a(activity, net.dinglisch.android.taskerm.aw.a.Material, z2);
    }

    public static /* synthetic */ b.a.l b(Activity activity, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return b(activity, z2);
    }

    public static final b.a.l<String> c(Activity activity, boolean z2) {
        c.f.b.k.b(activity, "activity");
        return a(activity, net.dinglisch.android.taskerm.aw.a.Misc, z2);
    }

    public static /* synthetic */ b.a.l c(Activity activity, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return c(activity, z2);
    }

    public static /* synthetic */ b.a.l a(Activity activity, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        if ((i2 & 8) != 0) {
            z4 = true;
        }
        if ((i2 & 16) != 0) {
            z5 = true;
        }
        if ((i2 & 32) != 0) {
            z6 = true;
        }
        if ((i2 & 64) != 0) {
            z7 = false;
        }
        if ((i2 & 128) != 0) {
            z8 = false;
        }
        return a(activity, z2, z3, z4, z5, z6, z7, z8);
    }

    public static final b.a.l<String[]> a(Activity activity, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.b(new bb(z4, z5, z6, z3, z8, z7, activity, z2));
    }

    static final class bb extends c.f.b.l implements c.f.a.a<String[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f6485a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f6486b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6487c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6488d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f6489e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f6490f;
        final /* synthetic */ Activity g;
        final /* synthetic */ boolean h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        bb(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Activity activity, boolean z7) {
            super(0);
            this.f6485a = z;
            this.f6486b = z2;
            this.f6487c = z3;
            this.f6488d = z4;
            this.f6489e = z5;
            this.f6490f = z6;
            this.g = activity;
            this.h = z7;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            String[] strArr;
            List listA;
            String strA;
            ArrayList arrayList = new ArrayList();
            if (this.f6485a) {
                arrayList.add(new c.k(Integer.valueOf(R.string.application_icon), Integer.valueOf(R.drawable.mw_browse_files_apps)));
            }
            if (!Kid.a()) {
                c.a.j.a(arrayList, new c.k[]{new c.k(Integer.valueOf(R.string.word_holo), Integer.valueOf(R.drawable.hl_aaa_ext_android)), new c.k(Integer.valueOf(R.string.word_material), Integer.valueOf(R.drawable.mw_action_android)), new c.k(Integer.valueOf(R.string.settings_tab_misc), Integer.valueOf(R.drawable.cust_cookie))});
            }
            if (this.f6486b) {
                arrayList.add(new c.k(Integer.valueOf(R.string.pl_file), Integer.valueOf(R.drawable.mw_browse_files_file)));
            }
            if (this.f6487c) {
                arrayList.add(new c.k(Integer.valueOf(R.string.pl_url), Integer.valueOf(R.drawable.mw_browse_files_url)));
            }
            if (this.f6488d) {
                arrayList.add(new c.k(Integer.valueOf(R.string.current_wallpaper), Integer.valueOf(R.drawable.mw_device_wallpaper)));
            }
            if (this.f6489e) {
                arrayList.add(new c.k(Integer.valueOf(R.string.clipboard_image), Integer.valueOf(R.drawable.hd_content_paste)));
            }
            if (this.f6490f) {
                arrayList.add(new c.k(Integer.valueOf(R.string.base64_image), Integer.valueOf(R.drawable.mw_action_code)));
            }
            c.k kVar = (c.k) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.g, R.string.cn_image, arrayList, false, new b(), null, null, null, null, null, null, null, null, null, null, null, 65504, null)).b()).b();
            if (kVar == null) {
                throw new RuntimeException("Didn't select image type");
            }
            int iIntValue = ((Number) kVar.a()).intValue();
            switch (iIntValue) {
                case R.string.pl_file /* 2131297996 */:
                    strArr = (String[]) y.a((Context) this.g, "image/*", this.h, (String) null, false, false, false, 120, (Object) null).b();
                    break;
                case R.string.pl_url /* 2131298278 */:
                    String strJ = y.a(new com.joaomgcd.taskerm.dialog.h(this.g, R.string.pl_url, (String) null, 0, 0, 0, true, 0, (String) null, 0, (Integer) null, 1976, (c.f.b.g) null)).b().j();
                    if (strJ != null) {
                        return new String[]{strJ};
                    }
                    throw new RuntimeException("Didn't enter a URL");
                case R.string.settings_tab_misc /* 2131298551 */:
                    Object objB = y.c(this.g, false, 2, (Object) null).b();
                    c.f.b.k.a(objB, "dialogBuiltInIconMisc(activity).blockingGet()");
                    return new String[]{(String) objB};
                case R.string.word_holo /* 2131298906 */:
                    Object objB2 = y.a(this.g, false, 2, (Object) null).b();
                    c.f.b.k.a(objB2, "dialogBuiltInIconHolo(activity).blockingGet()");
                    return new String[]{(String) objB2};
                case R.string.word_material /* 2131298922 */:
                    Object objB3 = y.b(this.g, false, 2, (Object) null).b();
                    c.f.b.k.a(objB3, "dialogBuiltInIconMaterial(activity).blockingGet()");
                    return new String[]{(String) objB3};
                case R.string.application_icon /* 2131299091 */:
                    strArr = new String[1];
                    String strC = IconProvider.f7827a.c((String) ((cq) y.a((Context) this.g, (Intent) null, (List) null, false, 14, (Object) null).b()).d());
                    if (strC == null) {
                        throw new RuntimeException("Didn't select an app");
                    }
                    strArr[0] = strC;
                    break;
                    break;
                case R.string.base64_image /* 2131299884 */:
                    String strJ2 = y.a(new com.joaomgcd.taskerm.dialog.h(this.g, com.joaomgcd.taskerm.util.aj.a(R.string.base64_image, this.g), null, null, null, null, true, 0, com.joaomgcd.taskerm.util.aj.a(R.string.base64_image_prompt, this.g, new Object[0]), 0, null, null, null, 7868, null)).b().j();
                    if (strJ2 != null) {
                        return new String[]{com.joaomgcd.taskerm.m.b.a() + com.joaomgcd.taskerm.m.a.f7754a.b(strJ2)};
                    }
                    throw new RuntimeException("Didn't enter anything");
                case R.string.current_wallpaper /* 2131299917 */:
                    List listB = c.a.j.b(Integer.valueOf(R.string.launcher_wallpaper), Integer.valueOf(R.string.lockscreen_wallpaper));
                    a aVar = new a();
                    if (this.h) {
                        listA = ((com.joaomgcd.taskerm.dialog.as) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.e(this.g, new com.joaomgcd.taskerm.util.k(this.g, iIntValue, new Object[0]), listB, false, aVar, null, null, null, null, null, null, null, null, 8168, null)).b()).b();
                    } else {
                        Integer num = (Integer) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.g, iIntValue, listB, false, aVar, null, null, null, null, null, null, null, null, null, null, null, 65512, null)).b()).b();
                        if (num != null) {
                            listA = c.a.j.a(Integer.valueOf(num.intValue()));
                        } else {
                            throw new RuntimeException("Type of wallpaper not selected");
                        }
                    }
                    List list = listA;
                    ArrayList arrayList2 = new ArrayList(c.a.j.a((Iterable) list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        int iIntValue2 = ((Number) it.next()).intValue();
                        if (iIntValue2 == R.string.launcher_wallpaper) {
                            strA = IconProvider.f7827a.a(hg.a.Launcher);
                        } else if (iIntValue2 == R.string.lockscreen_wallpaper) {
                            strA = IconProvider.f7827a.a(hg.a.Lock);
                        } else {
                            throw new RuntimeException("Invalid type of wallpaper");
                        }
                        arrayList2.add(strA);
                    }
                    Object[] array = arrayList2.toArray(new String[0]);
                    if (array == null) {
                        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    strArr = (String[]) array;
                    break;
                    break;
                case R.string.clipboard_image /* 2131300381 */:
                    strArr = new String[]{IconProvider.f7827a.a()};
                    break;
                default:
                    throw new RuntimeException("Not a valid type of image selection");
            }
            return strArr;
        }

        static final class b extends c.f.b.l implements c.f.a.b<c.k<? extends Integer, ? extends Integer>, com.joaomgcd.taskerm.dialog.w> {
            b() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(c.k<Integer, Integer> kVar) {
                c.f.b.k.b(kVar, "it");
                return new com.joaomgcd.taskerm.dialog.w(com.joaomgcd.taskerm.util.aj.a(kVar.a().intValue(), bb.this.g, new Object[0]), new net.dinglisch.android.taskerm.g(bb.this.g.getResources(), kVar.b().intValue()), false, 4, null);
            }
        }

        static final class a extends c.f.b.l implements c.f.a.b<Integer, com.joaomgcd.taskerm.dialog.w> {
            a() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ com.joaomgcd.taskerm.dialog.w invoke(Integer num) {
                return a(num.intValue());
            }

            public final com.joaomgcd.taskerm.dialog.w a(int i) {
                return new com.joaomgcd.taskerm.dialog.w(com.joaomgcd.taskerm.util.aj.a(i, bb.this.g, new Object[0]), null, false, 6, null);
            }
        }
    }

    public static /* synthetic */ b.a.l a(Context context, String str, boolean z2, String str2, boolean z3, boolean z4, boolean z5, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "*/*";
        }
        boolean z6 = (i2 & 4) != 0 ? false : z2;
        if ((i2 & 8) != 0) {
            str2 = (String) null;
        }
        return a(context, str, z6, str2, (i2 & 16) == 0 ? z3 : false, (i2 & 32) != 0 ? true : z4, (i2 & 64) != 0 ? true : z5);
    }

    static final class ba extends c.f.b.l implements c.f.a.a<String[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6479a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f6480b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f6481c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f6482d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f6483e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ boolean f6484f;
        final /* synthetic */ boolean g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ba(Context context, boolean z, boolean z2, String str, String str2, boolean z3, boolean z4) {
            super(0);
            this.f6479a = context;
            this.f6480b = z;
            this.f6481c = z2;
            this.f6482d = str;
            this.f6483e = str2;
            this.f6484f = z3;
            this.g = z4;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            Intent intent;
            if ((this.f6479a instanceof Activity) && !(!((Boolean) com.joaomgcd.taskerm.util.bt.a.a(com.joaomgcd.taskerm.util.bt.f9101c, (Activity) this.f6479a, 0, 2, (Object) null).b()).booleanValue())) {
                throw new RuntimeException("No file read permissions");
            }
            if (this.f6480b && !this.f6481c) {
                intent = FileSelect.a(this.f6479a, null, this.f6482d, FileSelect.b.File, null, true, true);
            } else {
                intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                intent.setType(this.f6483e);
                intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", this.f6481c);
                intent.putExtra("android.provider.extra.INITIAL_URI", com.joaomgcd.taskerm.util.ak.C(this.f6482d));
                intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
            }
            c.f.b.k.a((Object) intent, "intent");
            Object[] array = dk.a(com.joaomgcd.taskerm.util.aj.a(intent, this.f6479a).b(), this.f6479a, this.f6480b, this.f6484f, this.g).toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public static final b.a.l<String[]> a(Context context, String str, boolean z2, String str2, boolean z3, boolean z4, boolean z5) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(str, "type");
        return com.joaomgcd.taskerm.rx.i.b(new ba(context, z3, z2, str2, str, z4, z5));
    }

    static final class az extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f6473a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f6474b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f6475c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6476d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f6477e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        az(Context context, boolean z, String str, boolean z2, boolean z3) {
            super(0);
            this.f6473a = context;
            this.f6474b = z;
            this.f6475c = str;
            this.f6476d = z2;
            this.f6477e = z3;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            Intent intentA;
            if ((this.f6473a instanceof Activity) && !(!((Boolean) com.joaomgcd.taskerm.util.bt.a.a(com.joaomgcd.taskerm.util.bt.f9101c, (Activity) this.f6473a, 0, 2, (Object) null).b()).booleanValue())) {
                throw new RuntimeException("No file read permissions");
            }
            if (this.f6474b) {
                intentA = FileSelect.a(this.f6473a, null, this.f6475c, FileSelect.b.Dir, null, true, true);
            } else {
                intentA = net.dinglisch.android.taskerm.as.a(true);
                intentA.putExtra("android.provider.extra.INITIAL_URI", com.joaomgcd.taskerm.util.ak.C(this.f6475c));
                intentA.putExtra("android.intent.extra.LOCAL_ONLY", true);
            }
            c.f.b.k.a((Object) intentA, "intent");
            Object[] array = dk.a(com.joaomgcd.taskerm.util.aj.a(intentA, this.f6473a).b(), this.f6473a, this.f6474b, this.f6476d, this.f6477e).toArray(new String[0]);
            if (array != null) {
                return (String) c.a.d.c(array);
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    public static final b.a.l<String> a(Context context, boolean z2, String str, boolean z3, boolean z4) {
        c.f.b.k.b(context, "context");
        return com.joaomgcd.taskerm.rx.i.b(new az(context, z3, str, z2, z4));
    }

    static final class aq<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final aq f6442a = new aq();

        aq() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> apply(cl clVar) {
            c.f.b.k.b(clVar, "it");
            if (!clVar.b()) {
                return b.a.l.b(new RuntimeException("Couldn't get shortcut"));
            }
            Intent intent = (Intent) ((cq) clVar).d();
            if (intent == null) {
                return b.a.l.b(new RuntimeException("No shortcut data"));
            }
            return b.a.l.a(intent.toUri(0));
        }
    }

    public static final b.a.l<String> b(Context context) {
        c.f.b.k.b(context, "context");
        b.a.l lVarA = new GenericActionActivityPickShortcut().run(context).a(aq.f6442a);
        c.f.b.k.a((Object) lVarA, "GenericActionActivityPic…payload.toUri(0))\n    }\n}");
        return lVarA;
    }

    public static /* synthetic */ b.a.l d(Activity activity, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        return d(activity, z2);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.at> d(Activity activity, boolean z2) {
        c.f.b.k.b(activity, "activity");
        List<Sensor> listAc = com.joaomgcd.taskerm.util.ah.ac(activity);
        if (listAc == null) {
            b.a.l<com.joaomgcd.taskerm.dialog.at> lVarB = b.a.l.b(new RuntimeException("Device has no sensors"));
            c.f.b.k.a((Object) lVarB, "Single.error(RuntimeExce…\"Device has no sensors\"))");
            return lVarB;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listAc.iterator();
        while (true) {
            boolean z3 = true;
            if (!it.hasNext()) {
                b.a.l<com.joaomgcd.taskerm.dialog.at> lVarA = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.cn_sensor, arrayList, false, an.f6436a, Integer.valueOf(R.string.tip_sensor_long_click_info), new ao(activity), true, null, null, null, null, null, null, null, null, 65288, null)).a((b.a.d.g) new ap(activity));
                c.f.b.k.a((Object) lVarA, "dialogSingleChoice(Dialo…orString)\n        }\n    }");
                return lVarA;
            }
            Object next = it.next();
            Sensor sensor = (Sensor) next;
            if (!z2) {
                c.f.b.k.a((Object) sensor, "it");
                if (com.joaomgcd.taskerm.util.aj.d(sensor)) {
                    z3 = false;
                }
            }
            if (z3) {
                arrayList.add(next);
            }
        }
    }

    static final class an extends c.f.b.l implements c.f.a.b<Sensor, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final an f6436a = new an();

        an() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(Sensor sensor) {
            c.f.b.k.a((Object) sensor, "it");
            return new com.joaomgcd.taskerm.dialog.w(com.joaomgcd.taskerm.util.aj.c(sensor), null, false, 6, null);
        }
    }

    static final class ao extends c.f.b.l implements c.f.a.b<Sensor, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6437a;

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$ao$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f6438a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
                c.f.b.k.b(xVar, "it");
            }

            @Override // c.f.a.b
            public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
                a(xVar);
                return c.s.f2131a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ao(Activity activity) {
            super(1);
            this.f6437a = activity;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(Sensor sensor) {
            a(sensor);
            return c.s.f2131a;
        }

        public final void a(Sensor sensor) {
            Activity activity = this.f6437a;
            c.f.b.k.a((Object) sensor, "it");
            com.joaomgcd.taskerm.rx.i.a(y.c(activity, R.string.cn_sensor, com.joaomgcd.taskerm.util.aj.b(sensor, this.f6437a)), this.f6437a, AnonymousClass1.f6438a);
        }
    }

    static final class ap<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6439a;

        ap(Activity activity) {
            this.f6439a = activity;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$ap$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.dialog.at> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.dialog.ae f6441b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(com.joaomgcd.taskerm.dialog.ae aeVar) {
                super(0);
                this.f6441b = aeVar;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.at invoke() {
                String strValueOf;
                Sensor sensor = (Sensor) this.f6441b.b();
                if (sensor == null) {
                    throw new RuntimeException(com.joaomgcd.taskerm.util.aj.a(R.string.word_cancelled, ap.this.f6439a, new Object[0]));
                }
                com.joaomgcd.taskerm.dialog.x xVarB = y.a(new com.joaomgcd.taskerm.dialog.h(ap.this.f6439a, R.string.dt_name_or_type, R.string.dc_name_or_type, R.string.pl_name, R.string.pl_type, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).b();
                if (xVarB.f()) {
                    throw new RuntimeException(com.joaomgcd.taskerm.util.aj.a(R.string.word_cancelled, ap.this.f6439a, new Object[0]));
                }
                if (xVarB.d()) {
                    strValueOf = com.joaomgcd.taskerm.util.aj.c(sensor);
                } else {
                    strValueOf = String.valueOf(sensor.getType());
                }
                return new com.joaomgcd.taskerm.dialog.at(sensor, strValueOf);
            }
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.at> apply(com.joaomgcd.taskerm.dialog.ae<Sensor> aeVar) {
            c.f.b.k.b(aeVar, "it");
            return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass1(aeVar));
        }
    }

    public static final b.a.l<net.dinglisch.android.taskerm.y> a(Activity activity, Sensor sensor, String str) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(sensor, "sensor");
        c.f.b.k.b(str, "variableName");
        com.joaomgcd.taskerm.util.ah.a(activity, true);
        return com.joaomgcd.taskerm.rx.i.b(new bi(activity, str, sensor));
    }

    static final class bi extends c.f.b.l implements c.f.a.a<net.dinglisch.android.taskerm.y> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6502a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f6503b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Sensor f6504c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        bi(Activity activity, String str, Sensor sensor) {
            super(0);
            this.f6502a = activity;
            this.f6503b = str;
            this.f6504c = sensor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v11, types: [T, com.joaomgcd.taskerm.dialog.h] */
        /* JADX WARN: Type inference failed for: r2v4, types: [T, com.joaomgcd.taskerm.dialog.h] */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final net.dinglisch.android.taskerm.y invoke() {
            net.dinglisch.android.taskerm.y yVar;
            SensorManager sensorManagerAb = com.joaomgcd.taskerm.util.ah.ab(this.f6502a);
            if (sensorManagerAb == null) {
                return new net.dinglisch.android.taskerm.y();
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            AnonymousClass2 anonymousClass2 = AnonymousClass2.f6506a;
            com.joaomgcd.taskerm.util.az azVarA = com.joaomgcd.taskerm.util.ba.a("setup");
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(sensorManagerAb, azVarA, anonymousClass1);
            try {
                if (com.joaomgcd.taskerm.util.aj.f(this.f6504c)) {
                    yVar = anonymousClass3.invoke();
                } else {
                    c.f.b.w.d dVar = new c.f.b.w.d();
                    dVar.f2028a = new com.joaomgcd.taskerm.dialog.h(this.f6502a, "", "", 0, 0, 0, false, 0, null, 0, null, null, 4088, null);
                    ArrayList arrayList = new ArrayList();
                    a aVar = new a(arrayList, 5, dVar);
                    com.joaomgcd.taskerm.dialog.ac acVarA = com.joaomgcd.taskerm.dialog.ac.a.a(com.joaomgcd.taskerm.dialog.ac.f6217a, this.f6502a, R.string.dc_getting_sensor_value, false, 4, null);
                    sensorManagerAb.registerListener(aVar, this.f6504c, 2, 200, azVarA.d());
                    int i = 0;
                    do {
                        try {
                            try {
                                if (arrayList.isEmpty()) {
                                    dm.a(100L);
                                    i++;
                                } else {
                                    com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                                    dVar.f2028a = new com.joaomgcd.taskerm.dialog.h(this.f6502a, String.valueOf(this.f6504c.getName()), anonymousClass2.invoke((float[]) c.a.j.a((List) arrayList, 0)), 0, 0, 0, false, 0, null, 0, null, null, 4088, null);
                                    y.a((com.joaomgcd.taskerm.dialog.h) dVar.f2028a).b();
                                    sensorManagerAb.unregisterListener(aVar);
                                    if (arrayList.size() <= 5) {
                                        ArrayList arrayList2 = arrayList;
                                        ArrayList arrayList3 = new ArrayList(c.a.j.a((Iterable) arrayList2, 10));
                                        Iterator it = arrayList2.iterator();
                                        while (it.hasNext()) {
                                            arrayList3.add(new c.k(new net.dinglisch.android.taskerm.x(net.dinglisch.android.taskerm.ar.b.Matches, anonymousClass1.a(0), c.a.d.a((float[]) it.next(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null)), net.dinglisch.android.taskerm.y.a.Or));
                                        }
                                        ArrayList arrayList4 = arrayList3;
                                        ArrayList arrayList5 = arrayList4;
                                        ArrayList arrayList6 = new ArrayList(c.a.j.a((Iterable) arrayList5, 10));
                                        Iterator it2 = arrayList5.iterator();
                                        while (it2.hasNext()) {
                                            arrayList6.add((net.dinglisch.android.taskerm.x) ((c.k) it2.next()).a());
                                        }
                                        ArrayList arrayList7 = arrayList6;
                                        ArrayList arrayList8 = arrayList4;
                                        ArrayList arrayList9 = new ArrayList(c.a.j.a((Iterable) arrayList8, 10));
                                        Iterator it3 = arrayList8.iterator();
                                        while (it3.hasNext()) {
                                            arrayList9.add((net.dinglisch.android.taskerm.y.a) ((c.k) it3.next()).b());
                                        }
                                        net.dinglisch.android.taskerm.y yVar2 = new net.dinglisch.android.taskerm.y(arrayList7, arrayList9);
                                        azVarA.g();
                                        com.joaomgcd.taskerm.util.ah.a(this.f6502a, false);
                                        return yVar2;
                                    }
                                    yVar = anonymousClass3.invoke();
                                }
                            } catch (Throwable th) {
                                com.joaomgcd.taskerm.util.ak.c(th.getMessage(), this.f6502a);
                                yVar = new net.dinglisch.android.taskerm.y();
                                com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                            }
                        } catch (Throwable th2) {
                            com.joaomgcd.taskerm.dialog.ac.a(acVarA, null, 1, null);
                            throw th2;
                        }
                    } while (i <= 30);
                    throw new RuntimeException("Couldn't get sensor value");
                }
                azVarA.g();
                com.joaomgcd.taskerm.util.ah.a(this.f6502a, false);
                return yVar;
            } catch (Throwable th3) {
                azVarA.g();
                com.joaomgcd.taskerm.util.ah.a(this.f6502a, false);
                throw th3;
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$bi$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Integer, String> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ String invoke(Integer num) {
                return a(num.intValue());
            }

            public final String a(int i) {
                return '%' + bi.this.f6503b + (i + 1);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$bi$2, reason: invalid class name */
        static final class AnonymousClass2 extends c.f.b.l implements c.f.a.b<float[], String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass2 f6506a = new AnonymousClass2();

            AnonymousClass2() {
                super(1);
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$bi$2$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<Float, String> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final AnonymousClass1 f6507a = new AnonymousClass1();

                AnonymousClass1() {
                    super(1);
                }

                @Override // c.f.a.b
                public /* synthetic */ String invoke(Float f2) {
                    return a(f2.floatValue());
                }

                public final String a(float f2) {
                    return com.joaomgcd.taskerm.util.aj.b(f2, 5);
                }
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(float[] fArr) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n");
                sb.append(fArr != null ? c.a.d.a(fArr, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, AnonymousClass1.f6507a, 30, (Object) null) : null);
                return sb.toString();
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$bi$3, reason: invalid class name */
        static final class AnonymousClass3 extends c.f.b.l implements c.f.a.a<net.dinglisch.android.taskerm.y> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ SensorManager f6509b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ com.joaomgcd.taskerm.util.az f6510c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f6511d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(SensorManager sensorManager, com.joaomgcd.taskerm.util.az azVar, AnonymousClass1 anonymousClass1) {
                super(0);
                this.f6509b = sensorManager;
                this.f6510c = azVar;
                this.f6511d = anonymousClass1;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r15v0, types: [T, com.joaomgcd.taskerm.dialog.h] */
            /* JADX WARN: Type inference failed for: r1v1, types: [T, com.joaomgcd.taskerm.dialog.h] */
            /* JADX WARN: Type inference failed for: r5v1, types: [T, float[]] */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final net.dinglisch.android.taskerm.y invoke() {
                String strJ;
                Float fA;
                Float fA2;
                c.f.b.w.a aVar = new c.f.b.w.a();
                aVar.f2025a = true;
                c.f.b.w.d dVar = new c.f.b.w.d();
                ?? r5 = (float[]) 0;
                dVar.f2028a = r5;
                c.f.b.w.d dVar2 = new c.f.b.w.d();
                dVar2.f2028a = r5;
                c.f.b.w.d dVar3 = new c.f.b.w.d();
                int i = 0;
                c.f.b.g gVar = null;
                dVar3.f2028a = new com.joaomgcd.taskerm.dialog.h(bi.this.f6502a, bi.this.f6504c.getName() + " - First Situation", "", 0, 0, i, false, 0, null, 0, null, null, 4088, gVar);
                a aVar2 = new a(aVar, dVar, dVar2, dVar3);
                this.f6509b.registerListener(aVar2, bi.this.f6504c, 2, 200, this.f6510c.d());
                y.a((com.joaomgcd.taskerm.dialog.h) dVar3.f2028a).b();
                aVar.f2025a = false;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                Integer num = null;
                dVar3.f2028a = new com.joaomgcd.taskerm.dialog.h(bi.this.f6502a, bi.this.f6504c.getName() + " - Second Situation", "", i2, i, 0 == true ? 1 : 0, false, i3, null, i4, num, null, 4088, gVar);
                y.a((com.joaomgcd.taskerm.dialog.h) dVar3.f2028a).b();
                this.f6509b.unregisterListener(aVar2);
                this.f6510c.g();
                com.joaomgcd.taskerm.dialog.x xVarB = y.a(new com.joaomgcd.taskerm.dialog.h(bi.this.f6502a, R.string.cn_sensor, "0", i2, i, (int) (0 == true ? 1 : 0), true, i3, "- First State: " + AnonymousClass2.f6506a.invoke((float[]) dVar.f2028a) + "\n\n- Second State: " + AnonymousClass2.f6506a.invoke((float[]) dVar2.f2028a) + "\n\nWhat margin of error do you want to give these values?", i4, num, 1720, (c.f.b.g) null)).b();
                if (xVarB == null || (strJ = xVarB.j()) == null || (fA = c.l.n.a(strJ)) == null) {
                    return new net.dinglisch.android.taskerm.y();
                }
                float fFloatValue = fA.floatValue();
                float[] fArr = (float[]) dVar.f2028a;
                if (fArr != null) {
                    ArrayList arrayList = new ArrayList(fArr.length);
                    int length = fArr.length;
                    int i5 = 0;
                    int i6 = 0;
                    while (i5 < length) {
                        float f2 = fArr[i5];
                        int i7 = i6 + 1;
                        float[] fArr2 = (float[]) dVar2.f2028a;
                        arrayList.add((fArr2 == null || (fA2 = c.a.d.a(fArr2, i6)) == null) ? null : com.joaomgcd.taskerm.util.aj.a(bi.this.f6504c, this.f6511d.a(i6), f2, fA2.floatValue(), fFloatValue));
                        i5++;
                        i6 = i7;
                    }
                    List<net.dinglisch.android.taskerm.y> listF = c.a.j.f((Iterable) arrayList);
                    if (listF != null) {
                        net.dinglisch.android.taskerm.y yVar = new net.dinglisch.android.taskerm.y();
                        int size = 0;
                        for (net.dinglisch.android.taskerm.y yVar2 : listF) {
                            yVar.addAll(yVar2);
                            yVar.a(size, size + 1, yVar2.a(0, 1));
                            size += yVar2.size();
                        }
                        return yVar;
                    }
                }
                return new net.dinglisch.android.taskerm.y();
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$bi$3$a */
            public static final class a implements SensorEventListener {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.a f6512a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.d f6513b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.d f6514c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.d f6515d;

                @Override // android.hardware.SensorEventListener
                public void onAccuracyChanged(Sensor sensor, int i) {
                }

                a(c.f.b.w.a aVar, c.f.b.w.d dVar, c.f.b.w.d dVar2, c.f.b.w.d dVar3) {
                    this.f6512a = aVar;
                    this.f6513b = dVar;
                    this.f6514c = dVar2;
                    this.f6515d = dVar3;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v0, types: [T, float[]] */
                @Override // android.hardware.SensorEventListener
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float[] fArrD;
                    String strA;
                    float[] fArr;
                    if (sensorEvent == null || (fArr = sensorEvent.values) == null) {
                        fArrD = null;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        int length = fArr.length;
                        for (int i = 0; i < length; i++) {
                            float f2 = fArr[i];
                            if (f2 != 0.0f) {
                                arrayList.add(Float.valueOf(f2));
                            }
                        }
                        fArrD = c.a.j.d((Collection<Float>) arrayList);
                    }
                    ?? r0 = fArrD;
                    if (this.f6512a.f2025a) {
                        this.f6513b.f2028a = r0;
                    } else {
                        this.f6514c.f2028a = r0;
                    }
                    com.joaomgcd.taskerm.dialog.h hVar = (com.joaomgcd.taskerm.dialog.h) this.f6515d.f2028a;
                    if (r0 == 0 || (strA = c.a.d.a((float[]) r0, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null)) == null) {
                        strA = "No Value";
                    }
                    hVar.a(strA);
                }
            }
        }

        public static final class a implements SensorEventListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ ArrayList f6516a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f6517b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ c.f.b.w.d f6518c;

            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            a(ArrayList arrayList, int i, c.f.b.w.d dVar) {
                this.f6516a = arrayList;
                this.f6517b = i;
                this.f6518c = dVar;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] fArr;
                Object next;
                if (sensorEvent == null || (fArr = sensorEvent.values) == null) {
                    return;
                }
                float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
                c.f.b.k.a((Object) fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
                if (fArrCopyOf != null) {
                    Iterator it = this.f6516a.iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Arrays.equals((float[]) next, fArrCopyOf));
                    if (((float[]) next) != null) {
                        return;
                    }
                    this.f6516a.add(fArrCopyOf);
                    if (this.f6516a.size() > this.f6517b) {
                        ((com.joaomgcd.taskerm.dialog.h) this.f6518c.f2028a).a();
                    } else {
                        ((com.joaomgcd.taskerm.dialog.h) this.f6518c.f2028a).a(c.a.j.a(this.f6516a, " or ", null, null, 0, null, C0131a.f6519a, 30, null));
                    }
                }
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$bi$a$a, reason: collision with other inner class name */
            static final class C0131a extends c.f.b.l implements c.f.a.b<float[], String> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final C0131a f6519a = new C0131a();

                C0131a() {
                    super(1);
                }

                @Override // c.f.a.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String invoke(float[] fArr) {
                    c.f.b.k.b(fArr, "it");
                    return c.a.d.a(fArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (c.f.a.b) null, 62, (Object) null);
                }
            }
        }
    }

    public static /* synthetic */ b.a.l a(Activity activity, int i2, String str, boolean z2, com.joaomgcd.taskerm.b.c cVar, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = com.joaomgcd.taskerm.settings.ad.a(activity);
        }
        if ((i3 & 4) != 0) {
            str = "localhost";
        }
        if ((i3 & 8) != 0) {
            z2 = false;
        }
        if ((i3 & 16) != 0) {
            cVar = (com.joaomgcd.taskerm.b.c) null;
        }
        return a(activity, i2, str, z2, cVar);
    }

    public static final b.a.l<String> a(Activity activity, int i2, String str, boolean z2, com.joaomgcd.taskerm.b.c cVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "host");
        if (!z2 || net.dinglisch.android.taskerm.az.a((Context) activity, (Boolean) true)) {
            b.a.l<String> lVarB = com.joaomgcd.taskerm.util.ah.b((Context) activity, 375).a(true).a((b.a.d.g) new d(cVar, activity)).a((b.a.d.g) new e(activity, i2, str, z2)).b(new f(activity));
            c.f.b.k.a((Object) lVarB, "activity.unlockApi(Actio…tions.Consumer { })\n    }");
            return lVarB;
        }
        b.a.l<String> lVarB2 = b.a.l.b(new RuntimeException("Sorry, this only works with a rooted device"));
        c.f.b.k.a((Object) lVarB2, "Single.error(RuntimeExce…s with a rooted device\"))");
        return lVarB2;
    }

    static final class d<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.b.c f6542a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f6543b;

        d(com.joaomgcd.taskerm.b.c cVar, Activity activity) {
            this.f6542a = cVar;
            this.f6543b = activity;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$d$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.b.c, com.joaomgcd.taskerm.dialog.w> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(com.joaomgcd.taskerm.b.c cVar) {
                c.f.b.k.b(cVar, "it");
                return new com.joaomgcd.taskerm.dialog.w(cVar.b().a(d.this.f6543b), null, false, 6, null);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<com.joaomgcd.taskerm.dialog.ae<com.joaomgcd.taskerm.b.c>> apply(Boolean bool) {
            c.f.b.k.b(bool, "it");
            if (this.f6542a == null) {
                return com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6543b, R.string.pl_command, com.joaomgcd.taskerm.b.c.f5886a.a(), true, new AnonymousClass1(), null, null, null, null, null, null, null, null, null, null, null, 65504, null));
            }
            return b.a.l.a(new com.joaomgcd.taskerm.dialog.ae(new com.joaomgcd.taskerm.dialog.ab(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0), this.f6542a, null, 4, null));
        }
    }

    static final class e<T, R> implements b.a.d.g<T, b.a.p<? extends R>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6545a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6546b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f6547c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f6548d;

        e(Activity activity, int i, String str, boolean z) {
            this.f6545a = activity;
            this.f6546b = i;
            this.f6547c = str;
            this.f6548d = z;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<String> apply(com.joaomgcd.taskerm.dialog.ae<com.joaomgcd.taskerm.b.c> aeVar) {
            c.f.b.k.b(aeVar, "it");
            com.joaomgcd.taskerm.b.c cVarB = aeVar.b();
            if (cVarB == null) {
                throw new RuntimeException("No command type selected");
            }
            return cVarB.a(this.f6545a, this.f6546b, this.f6547c, this.f6548d, true);
        }
    }

    static final class f<T> implements b.a.d.f<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6549a;

        f(Activity activity) {
            this.f6549a = activity;
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(String str) {
            c.f.b.k.b(str, "it");
            com.joaomgcd.taskerm.rx.i.a(y.a(this.f6549a, R.string.tip_adb_wifi_command_depends_device), this.f6549a, new b.a.d.f<com.joaomgcd.taskerm.dialog.x>() { // from class: com.joaomgcd.taskerm.dialog.y.f.1
                @Override // b.a.d.f
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final void accept(com.joaomgcd.taskerm.dialog.x xVar) {
                    c.f.b.k.b(xVar, "it");
                }
            });
        }
    }

    public static /* synthetic */ Object a(Class cls, Activity activity, String str, int i2, String str2, String str3, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            str2 = (String) null;
        }
        String str4 = str2;
        if ((i3 & 16) != 0) {
            str3 = (String) null;
        }
        return a((Class<?>) cls, activity, str, i2, str4, str3);
    }

    public static final Object a(Class<?> cls, Activity activity, String str, int i2, String str2, String str3) {
        String str4;
        Integer numC;
        Class<?> cls2 = cls;
        String strA = str2;
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(str, "methodName");
        int i3 = i2 + 1;
        String str5 = "Parameter " + i3;
        if (str3 != null) {
            str4 = str3;
        } else {
            str4 = com.joaomgcd.taskerm.util.ak.j(str) + ": " + str5;
        }
        if (c.f.b.k.a(cls2, String.class) || c.f.b.k.a(cls2, String[].class)) {
            if (str4 == null) {
                throw new c.q("null cannot be cast to non-null type java.lang.String");
            }
            String lowerCase = str4.toLowerCase();
            c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
            if (c.l.n.b((CharSequence) lowerCase, (CharSequence) "package", false, 2, (Object) null)) {
                cls2 = AppBasic.class;
            }
        }
        if (c.f.b.k.a(cls2, int[].class)) {
            cls2 = Integer.TYPE;
        } else if (c.f.b.k.a(cls2, long[].class)) {
            cls2 = Long.TYPE;
        } else if (c.f.b.k.a(cls2, float[].class)) {
            cls2 = Float.TYPE;
        }
        if (c.f.b.k.a(cls2, Boolean.class) || c.f.b.k.a(cls2, Boolean.TYPE)) {
            return Boolean.valueOf(c(activity, str4, strA, R.string.button_label_cancel).b().d());
        }
        if (c.f.b.k.a(cls2, Integer.TYPE) || c.f.b.k.a(cls2, Integer.class) || c.f.b.k.a(cls2, Long.TYPE) || c.f.b.k.a(cls2, Long.class)) {
            String str6 = null;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            if (strA == null) {
                strA = com.joaomgcd.taskerm.util.aj.a(R.string.f_zero_length_num, activity, str5);
            }
            String strJ = a(new com.joaomgcd.taskerm.dialog.h(activity, str4, str6, i4, i5, i6, true, 0, strA, 2, null, null, 3256, null)).b().j();
            if (strJ != null && (numC = c.l.n.c(strJ)) != null) {
                return numC;
            }
            throw new RuntimeException("No number entered for parameter " + i3);
        }
        if (c.f.b.k.a(cls2, String.class) || c.f.b.k.a(cls2, CharSequence.class)) {
            String strJ2 = a(new com.joaomgcd.taskerm.dialog.h(activity, str4, null, 0, 0, 0, true, 0, str2, 0, null, null, 3768, null)).b().j();
            if (strJ2 != null) {
                return strJ2;
            }
            throw new RuntimeException("No text entered for parameter " + i3);
        }
        if (!c.f.b.k.a(cls2, AppBasic.class)) {
            return "";
        }
        String str7 = (String) ((cq) a((Context) activity, (Intent) null, (List) null, false, 14, (Object) null).b()).d();
        if (str7 != null) {
            return str7;
        }
        throw new RuntimeException("No app selected for parameter " + i3);
    }

    static final class bk<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6521a;

        bk(Activity activity) {
            this.f6521a = activity;
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String apply(Integer num) {
            c.f.b.k.b(num, "it");
            return (String) c.a.d.k(com.joaomgcd.taskerm.k.a.f7559a.a(this.f6521a)).get(num.intValue());
        }
    }

    public static final b.a.l<String> o(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return p(activity).c(new bk(activity));
    }

    static final class bl extends c.f.b.l implements c.f.a.a<Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6522a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        bl(Activity activity) {
            super(0);
            this.f6522a = activity;
        }

        @Override // c.f.a.a
        public /* synthetic */ Integer invoke() {
            return Integer.valueOf(a());
        }

        public final int a() {
            if (!new com.joaomgcd.taskerm.util.bt(this.f6522a, 0, c.a.j.a("android.permission.READ_PHONE_STATE"), 2, (c.f.b.g) null).b(this.f6522a).b().b()) {
                throw new RuntimeException("Need permission");
            }
            Integer numC = ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6522a, R.string.pl_sim_card, c.a.d.k(com.joaomgcd.taskerm.k.a.f7559a.a(this.f6522a)), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).c();
            if (numC != null) {
                return numC.intValue();
            }
            throw new RuntimeException("No SIM card selected");
        }
    }

    public static final b.a.l<Integer> p(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.b(new bl(activity));
    }

    public static /* synthetic */ b.a.l a(Context context, String str, com.joaomgcd.taskerm.genericaction.b bVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = (String) null;
        }
        if ((i2 & 4) != 0) {
            bVar = (com.joaomgcd.taskerm.genericaction.b) null;
        }
        return a(context, str, bVar);
    }

    static final class ah extends c.f.b.l implements c.f.a.a<ContactData> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f6421a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.genericaction.b f6422b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f6423c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ah(String str, com.joaomgcd.taskerm.genericaction.b bVar, Context context) {
            super(0);
            this.f6421a = str;
            this.f6422b = bVar;
            this.f6423c = context;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ContactData invoke() {
            ContactData contactData;
            String str = this.f6421a;
            cl clVarB = (str == null || str.length() == 0 ? new GenericActionPickContact(this.f6422b) : new GenericActionPickContactWithMimeType(this.f6421a)).run(this.f6423c).b();
            if (!clVarB.b()) {
                throw new RuntimeException(clVarB.a());
            }
            if (!(clVarB instanceof cq)) {
                clVarB = null;
            }
            cq cqVar = (cq) clVarB;
            if (cqVar == null || (contactData = (ContactData) cqVar.d()) == null) {
                throw new RuntimeException("Couldn't get contact");
            }
            return contactData;
        }
    }

    public static final b.a.l<ContactData> a(Context context, String str, com.joaomgcd.taskerm.genericaction.b bVar) {
        c.f.b.k.b(context, "context");
        return com.joaomgcd.taskerm.rx.i.b(new ah(str, bVar, context));
    }

    static final class ai extends c.f.b.l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6424a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ai(Activity activity) {
            super(0);
            this.f6424a = activity;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            Activity activity = this.f6424a;
            String[] strArrA = cs.a(this.f6424a, R.array.contact_select_types);
            c.f.b.k.a((Object) strArrA, "MyRes.getStringArray(act…ray.contact_select_types)");
            Integer numC = ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, R.string.an_contacts, c.a.d.k(strArrA), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).c();
            if (numC == null) {
                throw new RuntimeException("Did not select a group");
            }
            switch (numC.intValue()) {
                case 0:
                    String name = ((ContactData) y.a(this.f6424a, (String) null, com.joaomgcd.taskerm.genericaction.b.Contact, 2, (Object) null).b()).getName();
                    if (name != null) {
                        return name;
                    }
                    throw new RuntimeException("Contact doesn't have a name");
                case 1:
                    return "C:FAV";
                case 2:
                    String[] strArrA2 = cd.a((Context) this.f6424a, false);
                    Activity activity2 = this.f6424a;
                    int i = R.string.an_contacts;
                    c.f.b.k.a((Object) strArrA2, "groups");
                    String str = (String) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity2, i, c.a.d.k(strArrA2), false, null, null, null, null, null, null, null, null, null, null, null, null, 65528, null)).b()).b();
                    if (str != null) {
                        String str2 = "CG:" + str;
                        if (str2 != null) {
                            return str2;
                        }
                    }
                    throw new RuntimeException("No contact group selected");
                default:
                    return "C:ANY";
            }
        }
    }

    public static final b.a.l<String> q(Activity activity) {
        c.f.b.k.b(activity, "activity");
        return com.joaomgcd.taskerm.rx.i.b(new ai(activity));
    }

    public static /* synthetic */ b.a.l a(Activity activity, com.joaomgcd.taskerm.dialog.aq aqVar, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            aqVar = (com.joaomgcd.taskerm.dialog.aq) null;
        }
        if ((i2 & 4) != 0) {
            str = (String) null;
        }
        if ((i2 & 8) != 0) {
            str2 = (String) null;
        }
        return a(activity, aqVar, str, str2);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.aq> a(Activity activity, com.joaomgcd.taskerm.dialog.aq aqVar, String str, String str2) {
        c.f.b.k.b(activity, "activity");
        Calendar calendar = Calendar.getInstance();
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<PickedTime>()");
        com.joaomgcd.taskerm.rx.i.e(new ar(activity, new as(dVarF), aqVar, calendar, str, str2, dVarF));
        return dVarF;
    }

    static final class as extends c.f.b.l implements c.f.a.q<TimePicker, Integer, Integer, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f6451a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        as(b.a.k.d dVar) {
            super(3);
            this.f6451a = dVar;
        }

        @Override // c.f.a.q
        public /* synthetic */ c.s a(TimePicker timePicker, Integer num, Integer num2) {
            a(timePicker, num.intValue(), num2.intValue());
            return c.s.f2131a;
        }

        public final void a(TimePicker timePicker, int i, int i2) {
            c.f.b.k.b(timePicker, "view");
            this.f6451a.d_(new com.joaomgcd.taskerm.dialog.aq(i, i2));
        }
    }

    static final class ar extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6443a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.q f6444b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.dialog.aq f6445c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Calendar f6446d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f6447e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ String f6448f;
        final /* synthetic */ b.a.k.d g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ar(Activity activity, c.f.a.q qVar, com.joaomgcd.taskerm.dialog.aq aqVar, Calendar calendar, String str, String str2, b.a.k.d dVar) {
            super(0);
            this.f6443a = activity;
            this.f6444b = qVar;
            this.f6445c = aqVar;
            this.f6446d = calendar;
            this.f6447e = str;
            this.f6448f = str2;
            this.g = dVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            Activity activity = this.f6443a;
            c.f.a.q qVar = this.f6444b;
            Object aaVar = qVar;
            if (qVar != null) {
                aaVar = new com.joaomgcd.taskerm.dialog.aa(qVar);
            }
            TimePickerDialog.OnTimeSetListener onTimeSetListener = (TimePickerDialog.OnTimeSetListener) aaVar;
            com.joaomgcd.taskerm.dialog.aq aqVar = this.f6445c;
            int iA = aqVar != null ? aqVar.a() : this.f6446d.get(11);
            com.joaomgcd.taskerm.dialog.aq aqVar2 = this.f6445c;
            TimePickerDialog timePickerDialog = new TimePickerDialog(activity, onTimeSetListener, iA, aqVar2 != null ? aqVar2.b() : this.f6446d.get(12), true);
            if (com.joaomgcd.taskerm.util.ak.z(this.f6447e)) {
                timePickerDialog.setTitle(this.f6447e);
            }
            if (com.joaomgcd.taskerm.util.ak.z(this.f6448f)) {
                timePickerDialog.setMessage(this.f6448f);
            }
            timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.joaomgcd.taskerm.dialog.y.ar.1
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    com.joaomgcd.taskerm.rx.i.a(ar.this.g, new RuntimeException("Cancelled"));
                }
            });
            timePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.joaomgcd.taskerm.dialog.y.ar.2
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    com.joaomgcd.taskerm.rx.i.a(ar.this.g, new RuntimeException("Dismissed"));
                }
            });
            timePickerDialog.show();
        }
    }

    public static /* synthetic */ b.a.l a(Activity activity, com.joaomgcd.taskerm.dialog.ap apVar, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            apVar = (com.joaomgcd.taskerm.dialog.ap) null;
        }
        if ((i2 & 4) != 0) {
            str = (String) null;
        }
        if ((i2 & 8) != 0) {
            str2 = (String) null;
        }
        return a(activity, apVar, str, str2);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.ap> a(Activity activity, com.joaomgcd.taskerm.dialog.ap apVar, String str, String str2) {
        c.f.b.k.b(activity, "activity");
        Calendar calendar = Calendar.getInstance();
        b.a.k.d dVarF = b.a.k.d.f();
        c.f.b.k.a((Object) dVarF, "SingleSubject.create<PickedDate>()");
        com.joaomgcd.taskerm.rx.i.e(new aj(activity, new ak(dVarF), apVar, calendar, str, str2, dVarF));
        return dVarF;
    }

    static final class ak extends c.f.b.l implements c.f.a.r<DatePicker, Integer, Integer, Integer, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b.a.k.d f6433a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ak(b.a.k.d dVar) {
            super(4);
            this.f6433a = dVar;
        }

        @Override // c.f.a.r
        public /* synthetic */ c.s a(DatePicker datePicker, Integer num, Integer num2, Integer num3) {
            a(datePicker, num.intValue(), num2.intValue(), num3.intValue());
            return c.s.f2131a;
        }

        public final void a(DatePicker datePicker, int i, int i2, int i3) {
            c.f.b.k.b(datePicker, "view");
            this.f6433a.d_(new com.joaomgcd.taskerm.dialog.ap(i, i2 + 1, i3));
        }
    }

    static final class aj extends c.f.b.l implements c.f.a.a<c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6425a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.r f6426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.dialog.ap f6427c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Calendar f6428d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f6429e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ String f6430f;
        final /* synthetic */ b.a.k.d g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        aj(Activity activity, c.f.a.r rVar, com.joaomgcd.taskerm.dialog.ap apVar, Calendar calendar, String str, String str2, b.a.k.d dVar) {
            super(0);
            this.f6425a = activity;
            this.f6426b = rVar;
            this.f6427c = apVar;
            this.f6428d = calendar;
            this.f6429e = str;
            this.f6430f = str2;
            this.g = dVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            Activity activity = this.f6425a;
            c.f.a.r rVar = this.f6426b;
            Object zVar = rVar;
            if (rVar != null) {
                zVar = new com.joaomgcd.taskerm.dialog.z(rVar);
            }
            DatePickerDialog.OnDateSetListener onDateSetListener = (DatePickerDialog.OnDateSetListener) zVar;
            com.joaomgcd.taskerm.dialog.ap apVar = this.f6427c;
            int iA = apVar != null ? apVar.a() : this.f6428d.get(1);
            com.joaomgcd.taskerm.dialog.ap apVar2 = this.f6427c;
            int iB = apVar2 != null ? apVar2.b() - 1 : this.f6428d.get(2);
            com.joaomgcd.taskerm.dialog.ap apVar3 = this.f6427c;
            DatePickerDialog datePickerDialog = new DatePickerDialog(activity, onDateSetListener, iA, iB, apVar3 != null ? apVar3.c() : this.f6428d.get(5));
            if (com.joaomgcd.taskerm.util.ak.z(this.f6429e)) {
                datePickerDialog.setTitle(this.f6429e);
            }
            if (com.joaomgcd.taskerm.util.ak.z(this.f6430f)) {
                datePickerDialog.setMessage(this.f6430f);
            }
            datePickerDialog.setOnCancelListener(new a());
            datePickerDialog.setOnDismissListener(new b());
            datePickerDialog.show();
        }

        static final class a implements DialogInterface.OnCancelListener {
            a() {
            }

            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                com.joaomgcd.taskerm.rx.i.a(aj.this.g, new RuntimeException("Cancelled"));
            }
        }

        static final class b implements DialogInterface.OnDismissListener {
            b() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                com.joaomgcd.taskerm.rx.i.a(aj.this.g, new RuntimeException("Dismissed"));
            }
        }
    }

    static final class am extends c.f.b.l implements c.f.a.b<dl, com.joaomgcd.taskerm.dialog.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final am f6435a = new am();

        am() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.dialog.w invoke(dl dlVar) {
            c.f.b.k.b(dlVar, "project");
            String strL = dlVar.l();
            c.f.b.k.a((Object) strL, "project.name");
            return new com.joaomgcd.taskerm.dialog.w(strL, dlVar.n(), false, 4, null);
        }
    }

    public static /* synthetic */ b.a.l a(Activity activity, fx fxVar, int i2, List list, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            List<dl> listB = fxVar.B();
            c.f.b.k.a((Object) listB, "taskerData.projects");
            list = c.a.j.i((Iterable) listB);
        }
        return a(activity, fxVar, i2, (List<? extends dl>) list);
    }

    public static final b.a.l<c.k<Integer, dl>> a(Activity activity, fx fxVar, int i2, List<? extends dl> list) {
        c.f.b.k.b(activity, "context");
        c.f.b.k.b(fxVar, "taskerData");
        c.f.b.k.b(list, "projects");
        am amVar = am.f6435a;
        Activity activity2 = activity;
        Integer numValueOf = Integer.valueOf(com.joaomgcd.taskerm.settings.ad.e(activity2));
        Integer numValueOf2 = Integer.valueOf(gn.c(activity2));
        b.a.l<c.k<Integer, dl>> lVarC = com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, i2, list, false, amVar, null, null, true, null, numValueOf2, numValueOf, null, null, null, null, null, 63840, null)).c(al.f6434a);
        c.f.b.k.a((Object) lVarC, "dialogSingleChoice(args)…elected\")\n        )\n    }");
        return lVarC;
    }

    static final class al<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final al f6434a = new al();

        al() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.k<Integer, dl> apply(com.joaomgcd.taskerm.dialog.ae<dl> aeVar) {
            c.f.b.k.b(aeVar, "it");
            Integer numC = aeVar.c();
            if (numC == null) {
                throw new RuntimeException("No project selected");
            }
            Integer numValueOf = Integer.valueOf(numC.intValue());
            dl dlVarB = aeVar.b();
            if (dlVarB != null) {
                return new c.k<>(numValueOf, dlVarB);
            }
            throw new RuntimeException("No project selected");
        }
    }

    static final class at extends c.f.b.l implements c.f.a.a<ScanResult> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6452a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.b f6453b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        at(Activity activity, c.f.a.b bVar) {
            super(0);
            this.f6452a = activity;
            this.f6453b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ScanResult invoke() {
            if (!com.joaomgcd.taskerm.util.ah.af(this.f6452a)) {
                if (!((com.joaomgcd.taskerm.dialog.x) y.a(this.f6452a, R.string.warning_dialog_title, R.string.dc_need_location_access_wifi_cell, 0, 8, (Object) null).b()).d()) {
                    throw new RuntimeException("Didn't want enable location");
                }
                cl clVarB = new GenericActionActivityEnableLocation().run(this.f6452a).b();
                if (!clVarB.b()) {
                    throw new RuntimeException(clVarB.a());
                }
            }
            List<ScanResult> listT = com.joaomgcd.taskerm.util.ah.t(this.f6452a);
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object obj : listT) {
                ScanResult scanResult = (ScanResult) obj;
                c.f.a.b bVar = this.f6453b;
                c.f.b.k.a((Object) scanResult, "it");
                if (hashSet.add((String) bVar.invoke(scanResult))) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                ScanResult scanResult2 = (ScanResult) obj2;
                c.f.a.b bVar2 = this.f6453b;
                c.f.b.k.a((Object) scanResult2, "it");
                if (com.joaomgcd.taskerm.util.ak.z((String) bVar2.invoke(scanResult2))) {
                    arrayList2.add(obj2);
                }
            }
            ScanResult scanResult3 = (ScanResult) ((com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(this.f6452a, R.string.pl_wifi, arrayList2, false, new a(), null, null, null, null, null, null, null, null, null, null, null, 65512, null)).b()).b();
            if (scanResult3 != null) {
                return scanResult3;
            }
            throw new RuntimeException("No network selected");
        }

        static final class a extends c.f.b.l implements c.f.a.b<ScanResult, com.joaomgcd.taskerm.dialog.w> {
            a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(ScanResult scanResult) {
                c.f.a.b bVar = at.this.f6453b;
                c.f.b.k.a((Object) scanResult, "it");
                return new com.joaomgcd.taskerm.dialog.w((String) bVar.invoke(scanResult), null, false, 6, null);
            }
        }
    }

    public static final b.a.l<ScanResult> a(Activity activity, c.f.a.b<? super ScanResult, String> bVar) {
        c.f.b.k.b(activity, "context");
        c.f.b.k.b(bVar, "scanResultToString");
        return com.joaomgcd.taskerm.rx.i.b(new at(activity, bVar));
    }

    static final class ag extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>>, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6419a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f6420b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ag(Activity activity, int i) {
            super(1);
            this.f6419a = activity;
            this.f6420b = i;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            a(ahVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.ah<b.a.k.d<com.joaomgcd.taskerm.dialog.x>> ahVar) {
            c.f.b.k.b(ahVar, "it");
            net.dinglisch.android.taskerm.o.a(this.f6419a, ahVar, this.f6420b).a(this.f6419a);
        }
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.x> f(Activity activity, int i2) {
        c.f.b.k.b(activity, "context");
        return a(new ag(activity, i2), (c.f.a.b) null, 2, (Object) null);
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.ae<String>> a(Activity activity, com.joaomgcd.taskerm.util.h hVar) {
        c.f.b.k.b(activity, "context");
        c.f.b.k.b(hVar, "title");
        return com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, hVar.a(), c.a.j.i(ca.a(activity.getContentResolver(), -1, true, true).keySet()), false, null, null, null, null, null, null, null, null, null, null, null, null, null, 131064, null));
    }

    public static final b.a.l<com.joaomgcd.taskerm.dialog.ae<String>> b(Activity activity, com.joaomgcd.taskerm.util.h hVar) {
        c.f.b.k.b(activity, "context");
        c.f.b.k.b(hVar, "title");
        String strA = hVar.a();
        List<String> listB = ca.b(activity.getContentResolver(), (String) null);
        c.f.b.k.a((Object) listB, "MyCalendar.getTitleList(…xt.contentResolver, null)");
        return com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity, strA, listB, false, null, null, null, null, null, null, null, null, null, null, null, null, null, 131064, null));
    }

    static final class ae extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.dialog.x, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f6416a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f6417b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ae(c.f.a.a aVar, Activity activity) {
            super(1);
            this.f6416a = aVar;
            this.f6417b = activity;
        }

        @Override // c.f.a.b
        public /* synthetic */ c.s invoke(com.joaomgcd.taskerm.dialog.x xVar) {
            a(xVar);
            return c.s.f2131a;
        }

        public final void a(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.a.a aVar;
            c.f.b.k.b(xVar, "it");
            if (xVar.d() && (aVar = this.f6416a) != null) {
            }
            if (xVar.e()) {
                c.f.a.a aVar2 = this.f6416a;
                if (aVar2 != null) {
                }
                com.joaomgcd.taskerm.util.ah.a((Context) this.f6417b, "https://tasker.joaoapps.com/userguide/en/variables.html#json", false, false, false, 14, (Object) null);
            }
        }
    }

    public static final b.a.b.b a(Activity activity, int i2, com.joaomgcd.taskerm.util.h hVar, c.f.a.a<c.s> aVar) {
        c.f.b.k.b(activity, "activity");
        c.f.b.k.b(hVar, "text");
        Activity activity2 = activity;
        return com.joaomgcd.taskerm.rx.i.a(a(activity, com.joaomgcd.taskerm.util.aj.a(i2, activity2, new Object[0]), hVar.a(), new com.joaomgcd.taskerm.util.k(activity2, R.string.learn_more, new Object[0])), activity2, new ae(aVar, activity));
    }

    public static /* synthetic */ b.a.b.b a(Activity activity, int i2, com.joaomgcd.taskerm.util.h hVar, c.f.a.a aVar, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            aVar = (c.f.a.a) null;
        }
        return a(activity, i2, hVar, (c.f.a.a<c.s>) aVar);
    }

    static final class m extends c.f.b.l implements c.f.a.a<com.joaomgcd.taskerm.j.d> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Activity f6559a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        m(Activity activity) {
            super(0);
            this.f6559a = activity;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.dialog.y$m$1, reason: invalid class name */
        static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.j.d, com.joaomgcd.taskerm.j.d> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.j.d invoke(com.joaomgcd.taskerm.j.d dVar) {
                c.f.b.k.b(dVar, "receiver$0");
                if (net.dinglisch.android.taskerm.as.a((Context) m.this.f6559a, dVar.g().getAbsoluteFile(), true, true)) {
                    return dVar;
                }
                if (!((com.joaomgcd.taskerm.dialog.x) y.a(m.this.f6559a, R.string.ml_grant_doctree_access, R.string.dc_need_document_tree_access, 0, false, (String) null, 56, (Object) null).b()).d()) {
                    throw new RuntimeException("Doesn't have needed permission");
                }
                cl clVarB = new GenericActionActivityGrantUriPermission(dVar.g()).run(m.this.f6559a).b();
                if (clVarB.b()) {
                    return dVar;
                }
                throw new RuntimeException(clVarB.a());
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.j.d invoke() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            ArrayList<String> arrayListA = net.dinglisch.android.taskerm.as.a(this.f6559a);
            if (arrayListA.size() == 1) {
                Activity activity = this.f6559a;
                c.f.b.k.a((Object) arrayListA, "options");
                Object objE = c.a.j.e((List<? extends Object>) arrayListA);
                c.f.b.k.a(objE, "options.first()");
                return anonymousClass1.invoke(com.joaomgcd.taskerm.j.a.a((Context) activity, (String) objE, false, 2, (Object) null));
            }
            Activity activity2 = this.f6559a;
            int i = R.string.external_storage;
            c.f.b.k.a((Object) arrayListA, "options");
            com.joaomgcd.taskerm.dialog.ae aeVar = (com.joaomgcd.taskerm.dialog.ae) com.joaomgcd.taskerm.dialog.k.a(new com.joaomgcd.taskerm.dialog.ad(activity2, i, arrayListA, true, com.joaomgcd.taskerm.util.f.f9323b.e() ? a.f6561a : new b(), null, null, null, null, null, null, null, null, null, null, false, 32736, null)).b();
            Activity activity3 = this.f6559a;
            String str = (String) aeVar.b();
            if (str == null) {
                throw new RuntimeException("Nothing selected");
            }
            return anonymousClass1.invoke(com.joaomgcd.taskerm.j.a.a((Context) activity3, str, false, 2, (Object) null));
        }

        static final class a extends c.f.b.l implements c.f.a.b<String, com.joaomgcd.taskerm.dialog.w> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final a f6561a = new a();

            a() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(String str) {
                c.f.b.k.b(str, "path");
                return new com.joaomgcd.taskerm.dialog.w(str, null, false, 6, null);
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<String, com.joaomgcd.taskerm.dialog.w> {
            b() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.joaomgcd.taskerm.dialog.w invoke(String str) {
                c.f.b.k.b(str, "path");
                return new com.joaomgcd.taskerm.dialog.w(dk.a(dk.c(m.this.f6559a, str), m.this.f6559a, str), null, false, 6, null);
            }
        }
    }

    @TargetApi(30)
    public static final b.a.l<com.joaomgcd.taskerm.j.d> r(Activity activity) {
        c.f.b.k.b(activity, "context");
        return com.joaomgcd.taskerm.rx.i.b(new m(activity));
    }

    public static final b.a.l<com.joaomgcd.taskerm.f.g> s(Activity activity) {
        c.f.b.k.b(activity, "activity");
        if (com.joaomgcd.taskerm.settings.ad.h(activity)) {
            b.a.l lVarC = a(new com.joaomgcd.taskerm.dialog.h(activity, R.string.wrap_in_backticks, R.string.should_wrap_description_in_backticks_question, R.string.four_spaces, R.string.backticks, 0, false, 0, (String) null, 0, (Integer) null, 2016, (c.f.b.g) null)).c(h.f6552a);
            c.f.b.k.a((Object) lVarC, "dialogTextBox(ArgsDialog…escriptionOptions(true) }");
            return lVarC;
        }
        b.a.l<com.joaomgcd.taskerm.f.g> lVarA = b.a.l.a(new com.joaomgcd.taskerm.f.g(false));
        c.f.b.k.a((Object) lVarA, "Single.just(ExportDescriptionOptions(false))");
        return lVarA;
    }

    static final class h<T, R> implements b.a.d.g<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f6552a = new h();

        h() {
        }

        @Override // b.a.d.g
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.joaomgcd.taskerm.f.g apply(com.joaomgcd.taskerm.dialog.x xVar) {
            c.f.b.k.b(xVar, "it");
            return !xVar.e() ? new com.joaomgcd.taskerm.f.g(false) : new com.joaomgcd.taskerm.f.g(true);
        }
    }
}

package com.joaomgcd.taskerm.util;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f9012a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "items", "getItems()Lkotlin/sequences/Sequence;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "mimetypes", "getMimetypes()Lkotlin/sequences/Sequence;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "description", "getDescription()Landroid/content/ClipDescription;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ClipData f9013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f9014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f9015d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f9016e;

    public final c.k.g<ClipData.Item> a() {
        c.e eVar = this.f9014c;
        c.j.g gVar = f9012a[0];
        return (c.k.g) eVar.b();
    }

    public final c.k.g<String> b() {
        c.e eVar = this.f9015d;
        c.j.g gVar = f9012a[1];
        return (c.k.g) eVar.b();
    }

    public final ClipDescription c() {
        c.e eVar = this.f9016e;
        c.j.g gVar = f9012a[2];
        return (ClipDescription) eVar.b();
    }

    public aw(Context context) {
        ClipboardManager clipboardManagerE;
        this.f9013b = (context == null || (clipboardManagerE = ah.E(context)) == null) ? null : clipboardManagerE.getPrimaryClip();
        this.f9014c = c.f.a(new b());
        this.f9015d = c.f.a(new c());
        this.f9016e = c.f.a(new a());
    }

    static final class b extends c.f.b.l implements c.f.a.a<c.k.g<? extends ClipData.Item>> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.k.g<ClipData.Item> invoke() {
            ClipData clipData = aw.this.f9013b;
            if (clipData != null) {
                return c.k.h.a(new au(clipData));
            }
            return null;
        }
    }

    static final class c extends c.f.b.l implements c.f.a.a<c.k.g<? extends String>> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c.k.g<String> invoke() {
            ClipData clipData = aw.this.f9013b;
            if (clipData != null) {
                return c.k.h.a(new av(clipData));
            }
            return null;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<ClipDescription> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final ClipDescription invoke() {
            ClipData clipData = aw.this.f9013b;
            if (clipData != null) {
                return clipData.getDescription();
            }
            return null;
        }
    }
}

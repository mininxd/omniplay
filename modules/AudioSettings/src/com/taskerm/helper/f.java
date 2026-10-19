package com.joaomgcd.taskerm.helper;

import android.app.Activity;
import net.dinglisch.android.taskerm.ce;

/* JADX INFO: loaded from: classes.dex */
public final class f extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f7249a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(f.class), "themeId", "getThemeId()Ljava/lang/Integer;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f7250b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ce f7251c;

    @Override // com.joaomgcd.taskerm.helper.d
    public Integer a() {
        c.e eVar = this.f7250b;
        c.j.g gVar = f7249a[0];
        return (Integer) eVar.b();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ce ceVar) {
        super(null);
        c.f.b.k.b(ceVar, "dialogFragment");
        this.f7251c = ceVar;
        this.f7250b = c.f.a(new a());
    }

    public final ce c() {
        return this.f7251c;
    }

    @Override // com.joaomgcd.taskerm.helper.d
    public Activity b() {
        return this.f7251c.getActivity();
    }

    static final class a extends c.f.b.l implements c.f.a.a<Integer> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke() {
            Integer numValueOf = Integer.valueOf(f.this.c().getArguments().getInt("theme"));
            return numValueOf.intValue() == 0 ? (Integer) null : numValueOf;
        }
    }
}

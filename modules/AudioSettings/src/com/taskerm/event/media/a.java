package com.joaomgcd.taskerm.event.media;

import android.content.Context;
import android.content.res.Resources;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.util.bt;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.EventEdit;
import net.dinglisch.android.taskerm.al;
import net.dinglisch.android.taskerm.an;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.joaomgcd.taskerm.event.c<e, c, d> {
    @Override // com.joaomgcd.taskerm.f.d
    public boolean k() {
        return true;
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.event.media.a$a, reason: collision with other inner class name */
    static final class C0141a extends l implements c.f.a.b<e, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0141a f6679a = new C0141a();

        C0141a() {
            super(1);
        }

        public final boolean a(e eVar) {
            k.b(eVar, "it");
            return true;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(e eVar) {
            return Boolean.valueOf(a(eVar));
        }
    }

    public a() {
        super(new al(2081, R.string.en_music_track_changed, 130, "music_track_changed", null, null, false, false, 5, Integer.valueOf(R.string.pl_output_variables), "", 1, 1, Integer.valueOf(R.string.pl_track), "t:1:?", 1, 1, Integer.valueOf(R.string.pl_album), "t:1:?", 1, 1, Integer.valueOf(R.string.pl_artist), "t:1:?", 1, 1, Integer.valueOf(R.string.pl_package), "apppakc", 0, 0, Integer.valueOf(R.string.pl_type), "", 0));
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String[] b(Context context, e eVar) {
        k.b(context, "context");
        return bt.f9101c.y();
    }

    @Override // com.joaomgcd.taskerm.event.b
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public d c() {
        return new d(this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public e d() {
        return new e(null, null, null, null, null, null, 63, null);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public c a(EventEdit eventEdit) {
        k.b(eventEdit, "hasArgsEdit");
        return new c(eventEdit, this);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer e() {
        return 5191;
    }

    @Override // com.joaomgcd.taskerm.f.d
    public Integer a(Resources resources, int i, an anVar) {
        k.b(resources, "res");
        return Integer.valueOf(R.array.play_state_sources);
    }

    @Override // com.joaomgcd.taskerm.f.d
    public com.joaomgcd.taskerm.v.d<e> g() {
        return new com.joaomgcd.taskerm.v.d<>(C0141a.f6679a, "all_metadata");
    }
}

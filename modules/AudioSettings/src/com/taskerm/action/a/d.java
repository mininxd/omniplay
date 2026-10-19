package com.joaomgcd.taskerm.action.a;

import c.f.b.l;
import c.q;
import c.s;
import com.joaomgcd.taskerm.dialog.ac;
import com.joaomgcd.taskerm.dialog.ad;
import com.joaomgcd.taskerm.dialog.ae;
import com.joaomgcd.taskerm.dialog.k;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityInsertGoogleApiKey;
import com.joaomgcd.taskerm.tts.wavenet.ErrorWaveNet;
import com.joaomgcd.taskerm.tts.wavenet.Voice;
import com.joaomgcd.taskerm.tts.wavenet.i;
import com.joaomgcd.taskerm.tts.wavenet.j;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import java.util.ArrayList;
import java.util.Iterator;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ActionEdit;
import net.dinglisch.android.taskerm.gh;

/* JADX INFO: loaded from: classes.dex */
public final class d extends com.joaomgcd.taskerm.helper.actions.c<h> {

    static final class a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ h f4918b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(h hVar) {
            super(0);
            this.f4918b = hVar;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.action.a.d$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.b<Boolean, s> {
            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            public /* synthetic */ s invoke(Boolean bool) {
                a(bool.booleanValue());
                return s.f2131a;
            }

            public final void a(boolean z) {
                ErrorWaveNet errorWaveNet;
                String apiKey = a.this.f4918b.getApiKey(d.this.u());
                String str = apiKey;
                if ((str == null || str.length() == 0) || z) {
                    cl clVarB = new GenericActionActivityInsertGoogleApiKey().run(d.this.u()).b();
                    if (!(clVarB instanceof cq)) {
                        clVarB = null;
                    }
                    cq cqVar = (cq) clVarB;
                    if (cqVar == null || (apiKey = (String) cqVar.d()) == null) {
                        return;
                    }
                }
                ac acVarA = ac.a.a(ac.f6217a, d.this.u(), R.string.dc_getting_voices, false, 4, null);
                try {
                    cq<j, ErrorWaveNet> cqVarB = new com.joaomgcd.taskerm.tts.wavenet.g(apiKey).b().b();
                    if (!cqVarB.b() && (errorWaveNet = (ErrorWaveNet) cqVarB.e()) != null) {
                        if (((x) y.a(d.this.u(), R.string.word_error, errorWaveNet.getErrorMessage() + ".\n\nWant to re-enter your API Key?", 0, 8, (Object) null).b()).d()) {
                            a(true);
                        }
                        ac.a(acVarA, null, 1, null);
                        return;
                    }
                    j jVarD = cqVarB.d();
                    if (jVarD == null) {
                        ak.c("Couldn't get voices", d.this.u());
                        ac.a(acVarA, null, 1, null);
                        return;
                    }
                    j jVar = jVarD;
                    ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) jVar, 10));
                    Iterator<i> it = jVar.iterator();
                    while (it.hasNext()) {
                        arrayList.add(it.next().a());
                    }
                    Object[] array = arrayList.toArray(new Voice[0][]);
                    if (array == null) {
                        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    Voice voice = (Voice) ((ae) k.a(new ad(d.this.u(), R.string.pl_voice, c.a.d.a((Object[][]) array), true, null, null, null, null, null, null, null, null, null, null, null, null, 65520, null)).b()).b();
                    if (voice == null) {
                        ac.a(acVarA, null, 1, null);
                        return;
                    }
                    String name = voice.getName();
                    if (name == null) {
                        ac.a(acVarA, null, 1, null);
                    } else {
                        d.this.a(1, name);
                        ac.a(acVarA, null, 1, null);
                    }
                } catch (Throwable th) {
                    ac.a(acVarA, null, 1, null);
                    throw th;
                }
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            new AnonymousClass1().a(false);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(ActionEdit actionEdit, com.joaomgcd.taskerm.action.a<h, ?, ?> aVar) {
        super(actionEdit, aVar);
        c.f.b.k.b(actionEdit, "actionEdit");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public /* bridge */ /* synthetic */ void a(com.joaomgcd.taskerm.helper.actions.b bVar, com.joaomgcd.taskerm.helper.i iVar, Object obj) {
        a((com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c>) bVar, (com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?>) iVar, (h) obj);
    }

    @Override // com.joaomgcd.taskerm.helper.actions.c, com.joaomgcd.taskerm.helper.l
    public void a(net.dinglisch.android.taskerm.c cVar) {
        c.f.b.k.b(cVar, "configurable");
        super.a(cVar);
        gh.a(u(), R.string.tip_google_api_key);
    }

    public void a(com.joaomgcd.taskerm.helper.actions.b<net.dinglisch.android.taskerm.c> bVar, com.joaomgcd.taskerm.helper.i<?, ?, ?, ?, ?> iVar, h hVar) {
        c.f.b.k.b(bVar, "args");
        c.f.b.k.b(iVar, "helperActivityActionEdit");
        c.f.b.k.b(hVar, "input");
        com.joaomgcd.taskerm.rx.i.c(new a(hVar));
    }

    @Override // com.joaomgcd.taskerm.helper.l
    public boolean b(int i) {
        return aj.a(Integer.valueOf(i), 1);
    }
}

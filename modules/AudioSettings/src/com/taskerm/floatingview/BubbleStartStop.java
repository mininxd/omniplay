package com.joaomgcd.taskerm.floatingview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import b.a.b;
import b.a.h;
import c.a.j;
import c.f.b.k;
import c.f.b.l;
import c.f.b.w;
import c.s;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.securesettings.f;
import com.joaomgcd.taskerm.securesettings.o;
import com.joaomgcd.taskerm.securesettings.p;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gi;

/* JADX INFO: loaded from: classes.dex */
public final class BubbleStartStop extends Bubble {
    private final String i;
    private final boolean j;

    static final class a extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f6871b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.f.a.a f6872c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(c.f.a.a aVar, c.f.a.a aVar2) {
            super(0);
            this.f6871b = aVar;
            this.f6872c = aVar2;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.BubbleStartStop$a$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<s> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            public /* synthetic */ s invoke() {
                a();
                return s.f2131a;
            }

            public final void a() {
                ImageView imageViewIcon = BubbleStartStop.this.getImageViewIcon();
                if (imageViewIcon != null) {
                    imageViewIcon.setImageResource(R.drawable.hd_av_stop);
                }
                TextView textViewLabel = BubbleStartStop.this.getTextViewLabel();
                if (textViewLabel != null) {
                    textViewLabel.setText("Click to stop " + BubbleStartStop.this.i);
                }
            }
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            ImageView imageViewIcon = BubbleStartStop.this.getImageViewIcon();
            if (imageViewIcon != null) {
                imageViewIcon.setImageResource(R.drawable.hd_ab_av_play);
            }
            TextView textViewLabel = BubbleStartStop.this.getTextViewLabel();
            if (textViewLabel != null) {
                textViewLabel.setText("Click to start " + BubbleStartStop.this.i);
            }
            BubbleStartStop.this.B().b();
            h onClickSender = BubbleStartStop.this.getOnClickSender();
            w.b bVar = new w.b();
            bVar.f2026a = 1;
            if (BubbleStartStop.this.getAcceptVolumeButtons()) {
                Context context = BubbleStartStop.this.getContext();
                k.a((Object) context, "context");
                Context context2 = BubbleStartStop.this.getContext();
                k.a((Object) context2, "context");
                Context context3 = BubbleStartStop.this.getContext();
                k.a((Object) context3, "context");
                Context context4 = BubbleStartStop.this.getContext();
                k.a((Object) context4, "context");
                onClickSender = h.b((Iterable) j.b(onClickSender, h.b((Iterable) j.b(new o(context, new f(p.System, "volume_music_speaker", false, 0, 0, 28, null), null, 4, null).a(), new o(context2, new f(p.System, "volume_ring_speaker", false, 0, 0, 28, null), null, 4, null).a(), new o(context3, new f(p.System, "volume_alarm_speaker", false, 0, 0, 28, null), null, 4, null).a(), new o(context4, new f(p.System, "volume_voice_earpiece", false, 0, 0, 28, null), null, 4, null).a())).b((b.a.d.f) new C0148a(bVar))));
                k.a((Object) onClickSender, "Observable.merge(listOf(…olumes\n                ))");
            }
            onClickSender.c();
            this.f6871b.invoke();
            i.e(new AnonymousClass1());
            onClickSender.c();
            this.f6872c.invoke();
            BubbleStartStop.this.D().b();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.floatingview.BubbleStartStop$a$a, reason: collision with other inner class name */
        static final class C0148a<T> implements b.a.d.f<com.joaomgcd.taskerm.securesettings.h> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ w.b f6874a;

            C0148a(w.b bVar) {
                this.f6874a = bVar;
            }

            @Override // b.a.d.f
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final void accept(com.joaomgcd.taskerm.securesettings.h hVar) {
                k.b(hVar, "it");
                new gi(8000 / this.f6874a.f2026a, 100, (short) 50).a(3);
                this.f6874a.f2026a++;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BubbleStartStop(Context context, String str, boolean z) {
        super(context);
        k.b(context, "context");
        k.b(str, "verb");
        this.i = str;
        this.j = z;
    }

    public final boolean getAcceptVolumeButtons() {
        return this.j;
    }

    @Override // com.joaomgcd.taskerm.floatingview.Bubble, com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected void a(Context context, LinearLayout linearLayout) {
        k.b(linearLayout, "root");
        super.a(context, linearLayout);
    }

    public final b a(c.f.a.a<s> aVar, c.f.a.a<s> aVar2) {
        k.b(aVar, "start");
        k.b(aVar2, "stop");
        return i.d(new a(aVar, aVar2));
    }
}

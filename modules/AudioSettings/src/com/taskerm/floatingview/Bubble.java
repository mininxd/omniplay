package com.joaomgcd.taskerm.floatingview;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import b.a.h;
import b.a.k.b;
import c.e;
import c.f;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import com.joaomgcd.taskerm.util.aj;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.go;

/* JADX INFO: loaded from: classes.dex */
public class Bubble extends FloatingViewBase {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ g[] f6868a = {x.a(new v(x.a(Bubble.class), "onClickSender", "getOnClickSender()Lio/reactivex/subjects/PublishSubject;"))};
    private TextView i;
    private ImageView j;
    private final e k;

    /* JADX INFO: Access modifiers changed from: private */
    final b<Bubble> getOnClickSender() {
        e eVar = this.k;
        g gVar = f6868a[0];
        return (b) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected void a(Exception exc, Intent intent) {
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean a() {
        return true;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean b() {
        return true;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean c() {
        return false;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected boolean d() {
        return false;
    }

    protected int getImageViewResId() {
        return R.id.imageview_clip_sender;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getLayoutResourceId() {
        return R.layout.bubble;
    }

    protected int getTextViewResId() {
        return R.id.imageview_clip_sender_label;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected int getYDp() {
        return 50;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Bubble(Context context) {
        super(context);
        k.b(context, "context");
        this.k = f.a(a.f6869a);
    }

    protected final TextView getTextViewLabel() {
        return this.i;
    }

    protected final void setTextViewLabel(TextView textView) {
        this.i = textView;
    }

    protected final ImageView getImageViewIcon() {
        return this.j;
    }

    protected final void setImageViewIcon(ImageView imageView) {
        this.j = imageView;
    }

    static final class a extends l implements c.f.a.a<b<Bubble>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f6869a = new a();

        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b<Bubble> invoke() {
            return b.j();
        }
    }

    /* JADX INFO: renamed from: getOnClickSender, reason: collision with other method in class */
    public final h<Bubble> m2getOnClickSender() {
        h<Bubble> hVarJ = getOnClickSender().f().j();
        k.a((Object) hVarJ, "onClickSender.publish().autoConnect()");
        return hVarJ;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected void a(Context context, LinearLayout linearLayout) {
        k.b(linearLayout, "root");
        int iC = go.c(context);
        TextView textView = (TextView) linearLayout.findViewById(getTextViewResId());
        aj.a(textView, iC);
        this.i = textView;
        ImageView imageView = (ImageView) linearLayout.findViewById(getImageViewResId());
        aj.a(imageView, iC);
        this.j = imageView;
    }

    @Override // com.joaomgcd.taskerm.floatingview.FloatingViewBase
    protected void e() {
        super.e();
        getOnClickSender().b_(this);
    }
}

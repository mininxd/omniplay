package com.joaomgcd.taskerm.helper.actions;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import c.f.b.k;
import c.f.b.l;
import c.l.n;
import c.q;
import c.s;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.ak;
import java.io.File;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.HTMLView;

/* JADX INFO: loaded from: classes.dex */
public final class j extends com.joaomgcd.taskerm.helper.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HTMLView f7242b;

    static final class a extends l implements c.f.a.b<x, s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7244b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(1);
            this.f7244b = str;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(x xVar) {
            a(xVar);
            return s.f2131a;
        }

        public final void a(x xVar) {
            k.b(xVar, "result");
            if (xVar.d()) {
                ah.a(j.this.f7242b, "Missing Help", "Please add the help file for '" + this.f7244b + "'.\n\nThanks in advance!", (File[]) null, 4, (Object) null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(HTMLView hTMLView) {
        super(hTMLView);
        k.b(hTMLView, "htmlView");
        this.f7242b = hTMLView;
    }

    private final void a(WebView webView) {
        String stringExtra = this.f7242b.getIntent().getStringExtra("url");
        if (stringExtra != null) {
            webView.loadUrl(this.f7242b.a("en", stringExtra));
        }
    }

    public final <T> void a(WebView webView, WebResourceRequest webResourceRequest, T t, c.f.a.b<? super T, Boolean> bVar, c.f.a.b<? super T, Boolean> bVar2) {
        Uri url;
        String string;
        k.b(webView, "webView");
        k.b(bVar, "isError");
        k.b(bVar2, "shouldSendEmailAboutIt");
        String stringExtra = this.f7242b.getIntent().getStringExtra("url");
        if (stringExtra == null || TextUtils.isEmpty(stringExtra) || !n.a(stringExtra, "help/", false, 2, (Object) null) || webResourceRequest == null || (url = webResourceRequest.getUrl()) == null || (string = url.toString()) == null) {
            return;
        }
        k.a((Object) string, "request?.url?.toString() ?: return");
        if (n.a(string, "file://", false, 2, (Object) null)) {
            ak.b("Local help file not found, redirecting to online help.", this.f7242b);
            a(webView);
            return;
        }
        if (n.b((CharSequence) string, (CharSequence) "/en/", false, 2, (Object) null)) {
            if (bVar2.invoke(t).booleanValue()) {
                a(y.a((Activity) this.f7242b, R.string.ml_help_this_screen, R.string.dc_help_doesnt_exist_contact_developer, 0, false, (String) null, 56, (Object) null), new a(stringExtra));
                return;
            } else {
                com.joaomgcd.taskerm.helper.j.a(this, y.d(this.f7242b, R.string.ml_help_this_screen, R.string.dc_help_doesnt_exist_check_network), (c.f.a.b) null, 2, (Object) null);
                return;
            }
        }
        if (bVar.invoke(t).booleanValue()) {
            ak.b("Help not found, redirecting to english.", this.f7242b);
            webView.loadUrl(this.f7242b.a("en", stringExtra));
            a(webView);
        }
    }

    public final boolean a(WebResourceError webResourceError) {
        String string;
        k.b(webResourceError, "receiver$0");
        CharSequence description = webResourceError.getDescription();
        if (description == null || (string = description.toString()) == null) {
            return false;
        }
        return n.b((CharSequence) string, (CharSequence) "ERR_FILE_NOT_FOUND", false, 2, (Object) null);
    }

    public final boolean b(WebResourceError webResourceError) {
        String string;
        k.b(webResourceError, "receiver$0");
        CharSequence description = webResourceError.getDescription();
        if (description == null || (string = description.toString()) == null) {
            return false;
        }
        if (string == null) {
            throw new q("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = string.toLowerCase();
        k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
        if (lowerCase != null) {
            return n.b((CharSequence) lowerCase, (CharSequence) "internet", false, 2, (Object) null);
        }
        return false;
    }
}

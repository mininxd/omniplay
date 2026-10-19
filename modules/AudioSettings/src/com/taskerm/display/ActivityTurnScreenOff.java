package com.joaomgcd.taskerm.display;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import b.a.d.f;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ch;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityTurnScreenOff extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.a.b.b f6585a;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f6585a = ch.f9195b.a(this).a(new a(), new b());
    }

    static final class a implements b.a.d.a {
        a() {
        }

        @Override // b.a.d.a
        public final void run() {
            ActivityTurnScreenOff.this.finish();
        }
    }

    static final class b<T> implements f<Throwable> {
        b() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(Throwable th) {
            k.b(th, "it");
            ActivityTurnScreenOff.this.finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        b.a.b.b bVar = this.f6585a;
        if (bVar != null) {
            bVar.b();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            Window window = getWindow();
            View decorView = window != null ? window.getDecorView() : null;
            if (decorView != null) {
                decorView.setSystemUiVisibility(5894);
            }
        }
    }
}

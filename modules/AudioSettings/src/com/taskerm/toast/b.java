package com.joaomgcd.taskerm.toast;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import c.f.b.k;
import c.q;
import cyanogenmod.app.ProfileManager;

/* JADX INFO: loaded from: classes.dex */
public final class b extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.joaomgcd.taskerm.toast.a f8763a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Toast f8764b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context, Toast toast) {
        super(context);
        k.b(context, "base");
        k.b(toast, "toast");
        this.f8764b = toast;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        Context baseContext = getBaseContext();
        k.a((Object) baseContext, "baseContext");
        Context applicationContext = baseContext.getApplicationContext();
        k.a((Object) applicationContext, "baseContext.applicationContext");
        return new a(this, applicationContext);
    }

    private final class a extends ContextWrapper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f8765a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(b bVar, Context context) {
            super(context);
            k.b(context, "base");
            this.f8765a = bVar;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String str) {
            Object systemService;
            k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
            if (k.a((Object) "window", (Object) str)) {
                b bVar = this.f8765a;
                Object systemService2 = getBaseContext().getSystemService(str);
                if (systemService2 == null) {
                    throw new q("null cannot be cast to non-null type android.view.WindowManager");
                }
                systemService = new WindowManagerC0239b(bVar, (WindowManager) systemService2);
            } else {
                systemService = super.getSystemService(str);
            }
            k.a(systemService, "if (Context.WINDOW_SERVI…er.getSystemService(name)");
            return systemService;
        }
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.toast.b$b, reason: collision with other inner class name */
    private final class WindowManagerC0239b implements WindowManager {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f8766a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final WindowManager f8767b;

        public WindowManagerC0239b(b bVar, WindowManager windowManager) {
            k.b(windowManager, "base");
            this.f8766a = bVar;
            this.f8767b = windowManager;
        }

        @Override // android.view.WindowManager
        public Display getDefaultDisplay() {
            Display defaultDisplay = this.f8767b.getDefaultDisplay();
            k.a((Object) defaultDisplay, "base.defaultDisplay");
            return defaultDisplay;
        }

        @Override // android.view.WindowManager
        public void removeViewImmediate(View view) {
            k.b(view, "view");
            this.f8767b.removeViewImmediate(view);
        }

        @Override // android.view.ViewManager
        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            k.b(view, "view");
            k.b(layoutParams, "params");
            try {
                Log.d("WindowManagerWrapper", "WindowManager's addView(view, params) has been hooked.");
                this.f8767b.addView(view, layoutParams);
            } catch (WindowManager.BadTokenException e2) {
                String message = e2.getMessage();
                if (message == null) {
                    message = "WindowManager.BadTokenException";
                }
                Log.i("WindowManagerWrapper", message);
                com.joaomgcd.taskerm.toast.a aVar = this.f8766a.f8763a;
                if (aVar != null) {
                    aVar.a(this.f8766a.f8764b);
                }
            } catch (Throwable th) {
                Log.e("WindowManagerWrapper", "[addView]", th);
            }
        }

        @Override // android.view.ViewManager
        public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
            k.b(view, "view");
            k.b(layoutParams, "params");
            this.f8767b.updateViewLayout(view, layoutParams);
        }

        @Override // android.view.ViewManager
        public void removeView(View view) {
            k.b(view, "view");
            this.f8767b.removeView(view);
        }
    }
}

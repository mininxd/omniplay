package com.joaomgcd.taskerm.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import cyanogenmod.app.ProfileManager;
import java.util.Iterator;
import java.util.List;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.fy;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityAssistantActions extends Activity {

    static final class a extends c.f.b.l implements c.f.a.a<c.s> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ c.s invoke() {
            a();
            return c.s.f2131a;
        }

        public final void a() {
            String action;
            Intent intent = ActivityAssistantActions.this.getIntent();
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Received assistant request: ");
            sb.append(action);
            sb.append("; data: ");
            Intent intent2 = ActivityAssistantActions.this.getIntent();
            sb.append(intent2 != null ? intent2.getData() : null);
            net.dinglisch.android.taskerm.bn.b("AssistantActions", sb.toString());
            int iHashCode = action.hashCode();
            if (iHashCode == -1173171990) {
                if (action.equals("android.intent.action.VIEW")) {
                    ActivityAssistantActions.this.b();
                }
            } else if (iHashCode == 1084288546 && action.equals("ACTION_COMMAND")) {
                ActivityAssistantActions.this.a();
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.joaomgcd.taskerm.rx.i.c(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a() {
        Uri data;
        String queryParameter;
        Intent intent = getIntent();
        if (intent == null || (data = intent.getData()) == null || (queryParameter = data.getQueryParameter("command")) == null) {
            return;
        }
        ah.v(this, queryParameter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x006c  */
    public final void b() {
        Uri data;
        Object next;
        boolean z;
        try {
            try {
                Intent intent = getIntent();
                if (intent == null || (data = intent.getData()) == null) {
                    finish();
                    return;
                }
                cg cgVarA = a(data);
                if (cgVarA == null) {
                    cgVarA = b(data);
                }
                if (cgVarA == null) {
                    finish();
                    return;
                }
                fx fxVarE = fx.e(this);
                c.f.b.k.a((Object) fxVarE, "TaskerData.getActive(this)");
                Iterator<T> it = fxVarE.u().keySet().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    String str = (String) next;
                    if (str != null) {
                        String lowerCase = str.toLowerCase();
                        c.f.b.k.a((Object) lowerCase, "(this as java.lang.String).toLowerCase()");
                        String strA = cgVarA.a();
                        if (strA == null) {
                            throw new c.q("null cannot be cast to non-null type java.lang.String");
                        }
                        String lowerCase2 = strA.toLowerCase();
                        c.f.b.k.a((Object) lowerCase2, "(this as java.lang.String).toLowerCase()");
                        if (c.f.b.k.a((Object) lowerCase, (Object) lowerCase2)) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                } while (!z);
                String str2 = (String) next;
                String str3 = str2;
                if (str3 == null || str3.length() == 0) {
                    if (!ah.a(this, 2093, R.string.en_assistant_action, R.string.dc_assistant_actions_explained, "https://youtu.be/gGa4OfxmlzU")) {
                        return;
                    }
                    finish();
                } else {
                    ExecuteService.a(this, str2, cgVarA.b(), cgVarA.c());
                    finish();
                }
            } catch (Throwable th) {
                net.dinglisch.android.taskerm.bn.c("AssistantActions", "Error running assistant action", th);
            }
        } catch (Throwable th2) {
            finish();
            throw th2;
        }
    }

    private final cg a(Uri uri) {
        b.a.l lVarA;
        com.joaomgcd.taskerm.dialog.ae aeVar;
        fs fsVar;
        String strL;
        String queryParameter = uri.getQueryParameter(ProfileManager.EXTRA_PROFILE_NAME);
        if (queryParameter == null || (lVarA = com.joaomgcd.taskerm.dialog.y.a((Activity) this, (fy) fx.e(this), queryParameter, (List) null, true, 8, (Object) null)) == null || (aeVar = (com.joaomgcd.taskerm.dialog.ae) lVarA.b()) == null || (fsVar = (fs) aeVar.b()) == null || (strL = fsVar.l()) == null) {
            return null;
        }
        return new cg(queryParameter, strL, null, null, 12, null);
    }

    private final cg b(Uri uri) {
        String strA;
        String queryParameter = uri.getQueryParameter("task");
        if (queryParameter == null) {
            return null;
        }
        com.joaomgcd.taskerm.event.app.c.a(this, queryParameter);
        String queryParameter2 = uri.getQueryParameter("par1");
        String queryParameter3 = uri.getQueryParameter("par2");
        String str = queryParameter;
        c.l.i iVarA = c.l.k.a(c(), str, 0, 2, null);
        if (iVarA != null) {
            c.l.f fVarA = iVarA.c().a(1);
            if (fVarA == null || (strA = fVarA.a()) == null) {
                return null;
            }
            if (queryParameter2 == null) {
                c.l.f fVarA2 = iVarA.c().a(2);
                queryParameter2 = fVarA2 != null ? fVarA2.a() : null;
            }
            if (queryParameter3 == null) {
                c.l.f fVarA3 = iVarA.c().a(3);
                queryParameter3 = fVarA3 != null ? fVarA3.a() : null;
            }
        } else {
            c.l.i iVarA2 = c.l.k.a(d(), str, 0, 2, null);
            if (iVarA2 != null) {
                c.l.f fVarA4 = iVarA2.c().a(1);
                if (fVarA4 == null || (strA = fVarA4.a()) == null) {
                    return null;
                }
                if (queryParameter2 == null) {
                    c.l.f fVarA5 = iVarA2.c().a(2);
                    queryParameter2 = fVarA5 != null ? fVarA5.a() : null;
                }
            } else {
                strA = queryParameter;
            }
        }
        return new cg(queryParameter, strA, queryParameter2, queryParameter3);
    }

    private final c.l.k c() {
        return new c.l.k("(.+?) with (.+) and (.+)");
    }

    private final c.l.k d() {
        return new c.l.k("(.+?) with (.+)");
    }
}

package com.joaomgcd.taskerm.action.audio;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.joaomgcd.taskerm.inputoutput.j f4994a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4995b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4996c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4997d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Boolean f4998e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Boolean f4999f;

    public q() {
        this(null, null, null, null, null, null, 63, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void appPackage$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void appPackageIgnore$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1, d = true)
    public static /* synthetic */ void appPackageOriginal$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void getFullInfo$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void ignoreAppsThatCantBeLaunched$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public q(com.joaomgcd.taskerm.inputoutput.j jVar, String str, String str2, String str3, Boolean bool, Boolean bool2) {
        this.f4994a = jVar;
        this.f4995b = str;
        this.f4996c = str2;
        this.f4997d = str3;
        this.f4998e = bool;
        this.f4999f = bool2;
    }

    public /* synthetic */ q(com.joaomgcd.taskerm.inputoutput.j jVar, String str, String str2, String str3, Boolean bool, Boolean bool2, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? new com.joaomgcd.taskerm.inputoutput.j() : jVar, (i & 2) != 0 ? (String) null : str, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? (String) null : str3, (i & 16) != 0 ? (Boolean) null : bool, (i & 32) != 0 ? (Boolean) null : bool2);
    }

    public final com.joaomgcd.taskerm.inputoutput.j getOutputClass() {
        return this.f4994a;
    }

    public final void setOutputClass(com.joaomgcd.taskerm.inputoutput.j jVar) {
        this.f4994a = jVar;
    }

    public final String getAppPackage() {
        return this.f4995b;
    }

    public final void setAppPackage(String str) {
        this.f4995b = str;
    }

    public final String getAppPackageOriginal() {
        return this.f4996c;
    }

    public final void setAppPackageOriginal(String str) {
        this.f4996c = str;
    }

    public final String getAppPackageIgnore() {
        return this.f4997d;
    }

    public final void setAppPackageIgnore(String str) {
        this.f4997d = str;
    }

    public final Boolean getIgnoreAppsThatCantBeLaunched() {
        return this.f4998e;
    }

    public final void setIgnoreAppsThatCantBeLaunched(Boolean bool) {
        this.f4998e = bool;
    }

    public final Boolean getGetFullInfo() {
        return this.f4999f;
    }

    public final void setGetFullInfo(Boolean bool) {
        this.f4999f = bool;
    }

    private final List<String> a(String str) {
        List<String> listB;
        return (str == null || (listB = c.l.n.b((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null)) == null) ? c.a.j.a() : listB;
    }

    private final boolean a(List<String> list) {
        boolean z;
        List<String> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                String str = (String) it.next();
                if (c.l.n.b((CharSequence) str, (CharSequence) "%", false, 2, (Object) null) || c.f(str) || (c.g(str) && c.e(str))) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        return !z && list.size() <= 1;
    }

    public final List<String> getAppPackages() {
        return a(this.f4995b);
    }

    public final List<String> getAppPackagesOriginal() {
        return a(this.f4996c);
    }

    public final List<String> getAppPackagesIgnore() {
        String str = this.f4997d;
        if (str != null) {
            return c.l.n.b((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null);
        }
        return null;
    }

    public final int getAppPackagesSize() {
        return getAppPackages().size();
    }

    public final boolean isEmpty() {
        return getAppPackagesSize() == 0;
    }

    public final boolean getHasJustOneApp() {
        return a(getAppPackages());
    }

    public final boolean getHasJustOneAppOriginal() {
        return a(getAppPackagesOriginal());
    }

    public final boolean getHasMost() {
        List<String> appPackages = getAppPackages();
        if ((appPackages instanceof Collection) && appPackages.isEmpty()) {
            return false;
        }
        Iterator<T> it = appPackages.iterator();
        while (it.hasNext()) {
            if (c.f((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public final boolean getHasLast() {
        List<String> appPackages = getAppPackages();
        if ((appPackages instanceof Collection) && appPackages.isEmpty()) {
            return false;
        }
        Iterator<T> it = appPackages.iterator();
        while (it.hasNext()) {
            if (c.g((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public final boolean getShouldGetFullInfo() {
        Boolean bool = this.f4999f;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}

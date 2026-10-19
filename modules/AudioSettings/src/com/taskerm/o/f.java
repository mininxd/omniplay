package com.joaomgcd.taskerm.o;

/* JADX INFO: loaded from: classes.dex */
final class f extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f8117a;

    public f(int i) {
        String str;
        super(i, null);
        switch (i) {
            case 1:
                str = "license error: Invalid package name";
                break;
            case 2:
                str = "license error: The application requested a license check for a package whose UID (package, user ID pair) does not match that of the requesting application.";
                break;
            case 3:
                str = "license error: The application (package name) was not recognized by Google Play.";
                break;
            case 4:
            default:
                str = "Unknown response code for license check.";
                break;
            case 5:
                str = "license error: Invalid public key";
                break;
            case 6:
                str = "No permission to check for license";
                break;
        }
        this.f8117a = str;
    }

    public final String b() {
        return this.f8117a;
    }
}

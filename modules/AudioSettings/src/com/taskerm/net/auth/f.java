package com.joaomgcd.taskerm.net.auth;

import c.a.z;
import c.k;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    /* JADX INFO: Access modifiers changed from: private */
    public static final HashMap<String, HashMap<String, String>> b() {
        return z.b(new k("https://accounts.google.com/o/oauth2/v2/auth", z.b(new k("access_type", "offline"), new k("prompt", "consent"))));
    }
}

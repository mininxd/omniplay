package com.joaomgcd.taskerm.net.auth;

import androidx.annotation.Keep;
import c.f.b.k;
import com.joaomgcd.taskerm.net.ab;
import com.joaomgcd.taskerm.util.dm;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class AuthInfo {
    private final String accessToken;
    private final Long accessTokenExpiresOn;
    private final String refreshToken;

    public AuthInfo(String str, String str2, Long l) {
        k.b(str2, "accessToken");
        this.refreshToken = str;
        this.accessToken = str2;
        this.accessTokenExpiresOn = l == null ? null : Long.valueOf(dm.f() + ((l.longValue() - 300) * 1000));
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final Long getAccessTokenExpiresOn() {
        return this.accessTokenExpiresOn;
    }

    public final boolean isExpired() {
        Long l = this.accessTokenExpiresOn;
        return (l != null ? l.longValue() : Long.MAX_VALUE) < dm.f();
    }

    public final c.k<String, String> getAuthHeader() {
        return ab.a(this.accessToken);
    }

    public final String getAuthHeaderKey() {
        return getAuthHeader().a();
    }

    public final String getAuthHeaderValue() {
        return getAuthHeader().b();
    }
}

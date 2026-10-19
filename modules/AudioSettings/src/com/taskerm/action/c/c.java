package com.joaomgcd.taskerm.action.c;

import android.content.Context;
import c.f.b.g;
import c.f.b.k;
import com.joaomgcd.taskerm.action.h;
import com.joaomgcd.taskerm.inputoutput.j;

/* JADX INFO: loaded from: classes.dex */
public final class c implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f5041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f5042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Integer f5044d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5045e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5046f;
    private j g;

    public c() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void action$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void image$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void maxResults$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 6)
    public static /* synthetic */ void overrideApiKey$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void uploadSize$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void webDetectionType$annotations() {
    }

    public c(a aVar, b bVar, String str, Integer num, String str2, String str3, j jVar) {
        this.f5041a = aVar;
        this.f5042b = bVar;
        this.f5043c = str;
        this.f5044d = num;
        this.f5045e = str2;
        this.f5046f = str3;
        this.g = jVar;
    }

    @Override // com.joaomgcd.taskerm.action.h
    public String getApiKey(Context context) {
        k.b(context, "context");
        return h.a.a(this, context);
    }

    public /* synthetic */ c(a aVar, b bVar, String str, Integer num, String str2, String str3, j jVar, int i, g gVar) {
        this((i & 1) != 0 ? (a) null : aVar, (i & 2) != 0 ? (b) null : bVar, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? (Integer) null : num, (i & 16) != 0 ? (String) null : str2, (i & 32) != 0 ? (String) null : str3, (i & 64) != 0 ? new j() : jVar);
    }

    public final a getAction() {
        return this.f5041a;
    }

    public final void setAction(a aVar) {
        this.f5041a = aVar;
    }

    public final b getWebDetectionType() {
        return this.f5042b;
    }

    public final void setWebDetectionType(b bVar) {
        this.f5042b = bVar;
    }

    public final String getImage() {
        return this.f5043c;
    }

    public final void setImage(String str) {
        this.f5043c = str;
    }

    public final Integer getMaxResults() {
        return this.f5044d;
    }

    public final void setMaxResults(Integer num) {
        this.f5044d = num;
    }

    public final String getUploadSize() {
        return this.f5045e;
    }

    public final void setUploadSize(String str) {
        this.f5045e = str;
    }

    @Override // com.joaomgcd.taskerm.action.h
    public String getOverrideApiKey() {
        return this.f5046f;
    }

    public void setOverrideApiKey(String str) {
        this.f5046f = str;
    }

    public final j getOutputClass() {
        return this.g;
    }

    public final void setOutputClass(j jVar) {
        this.g = jVar;
    }
}

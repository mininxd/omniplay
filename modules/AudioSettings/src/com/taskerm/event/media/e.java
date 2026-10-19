package com.joaomgcd.taskerm.event.media;

import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6686a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6687b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f6688c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f6689d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private f f6690e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Class<OutputMusicTrackChanged> f6691f;

    public e() {
        this(null, null, null, null, null, null, 63, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 3)
    public static /* synthetic */ void album$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 4)
    public static /* synthetic */ void app$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void artist$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 5)
    public static /* synthetic */ void playState$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void track$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void variables$annotations() {
    }

    public e(String str, String str2, String str3, String str4, f fVar, Class<OutputMusicTrackChanged> cls) {
        this.f6686a = str;
        this.f6687b = str2;
        this.f6688c = str3;
        this.f6689d = str4;
        this.f6690e = fVar;
        this.f6691f = cls;
    }

    public /* synthetic */ e(String str, String str2, String str3, String str4, f fVar, Class cls, int i, g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (f) null : fVar, (i & 32) != 0 ? OutputMusicTrackChanged.class : cls);
    }

    public final String getTrack() {
        return this.f6686a;
    }

    public final void setTrack(String str) {
        this.f6686a = str;
    }

    public final String getArtist() {
        return this.f6687b;
    }

    public final void setArtist(String str) {
        this.f6687b = str;
    }

    public final String getAlbum() {
        return this.f6688c;
    }

    public final void setAlbum(String str) {
        this.f6688c = str;
    }

    public final String getApp() {
        return this.f6689d;
    }

    public final void setApp(String str) {
        this.f6689d = str;
    }

    public final f getPlayState() {
        return this.f6690e;
    }

    public final void setPlayState(f fVar) {
        this.f6690e = fVar;
    }

    public final Class<OutputMusicTrackChanged> getVariables() {
        return this.f6691f;
    }

    public final void setVariables(Class<OutputMusicTrackChanged> cls) {
        this.f6691f = cls;
    }
}

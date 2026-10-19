package com.joaomgcd.taskerm.action.net;

/* JADX INFO: loaded from: classes.dex */
public final class ba {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5614b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<OutputNFCTag> f5615c;

    public ba() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void write$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void writeType$annotations() {
    }

    public ba(String str, String str2, Class<OutputNFCTag> cls) {
        this.f5613a = str;
        this.f5614b = str2;
        this.f5615c = cls;
    }

    public /* synthetic */ ba(String str, String str2, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? OutputNFCTag.class : cls);
    }

    public final String getWrite() {
        return this.f5613a;
    }

    public final void setWrite(String str) {
        this.f5613a = str;
    }

    public final String getWriteType() {
        return this.f5614b;
    }

    public final void setWriteType(String str) {
        this.f5614b = str;
    }

    public final Class<OutputNFCTag> getOutputClass() {
        return this.f5615c;
    }

    public final void setOutputClass(Class<OutputNFCTag> cls) {
        this.f5615c = cls;
    }

    public final com.joaomgcd.taskerm.nfc.f getWriteTypeEnum() {
        return com.joaomgcd.taskerm.nfc.f.f8021d.a(this.f5614b);
    }
}

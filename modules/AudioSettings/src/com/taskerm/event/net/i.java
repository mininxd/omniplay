package com.joaomgcd.taskerm.event.net;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f6697b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<OutputBluetoothDevice> f6698c;

    public i() {
        this(null, null, null, 7, null);
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 2)
    public static /* synthetic */ void address$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 1)
    public static /* synthetic */ void name$annotations() {
    }

    @com.joaomgcd.taskerm.inputoutput.b(a = 0)
    public static /* synthetic */ void outputClass$annotations() {
    }

    public i(String str, String str2, Class<OutputBluetoothDevice> cls) {
        this.f6696a = str;
        this.f6697b = str2;
        this.f6698c = cls;
    }

    public /* synthetic */ i(String str, String str2, Class cls, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (String) null : str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? OutputBluetoothDevice.class : cls);
    }

    public final String getName() {
        return this.f6696a;
    }

    public final void setName(String str) {
        this.f6696a = str;
    }

    public final String getAddress() {
        return this.f6697b;
    }

    public final void setAddress(String str) {
        this.f6697b = str;
    }

    public final Class<OutputBluetoothDevice> getOutputClass() {
        return this.f6698c;
    }

    public final void setOutputClass(Class<OutputBluetoothDevice> cls) {
        this.f6698c = cls;
    }
}

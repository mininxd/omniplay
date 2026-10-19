package com.dolby.dax;

/* JADX INFO: loaded from: classes.dex */
public enum DsTuning {
    internal_speaker(0),
    hdmi(1),
    miracast(2),
    headphone_port(3),
    bluetooth(4),
    usb(5);

    private static final String[] PORT_NAMES = {"internal_speaker", "hdmi", "miracast", "headphone_port", "bluetooth", "usb", "other"};
    private int id_;

    DsTuning(int i) {
        this.id_ = i;
    }

    public int toInt() {
        return this.id_;
    }

    @Override // java.lang.Enum
    public String toString() {
        return PORT_NAMES[this.id_];
    }

    public String defaultName() {
        return toString();
    }
}

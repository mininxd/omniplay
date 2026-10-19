package com.dolby.dax;

/* JADX INFO: loaded from: classes2.dex */
public enum DsTuning {
    internal_speaker(0),
    hdmi(1),
    miracast(2),
    headphone_port(3),
    bluetooth(4),
    usb(5);

    private static final String[] PORT_NAMES = {"internal_speaker", "hdmi", "miracast", "headphone_port", "bluetooth", "usb", "other"};
    private int id_;

    DsTuning(int id) {
        this.id_ = id;
    }

    public int toInt() {
        return this.id_;
    }

    @Override // java.lang.Enum
    public String toString() {
        String name = PORT_NAMES[this.id_];
        return name;
    }

    public String defaultName() {
        return toString();
    }
}

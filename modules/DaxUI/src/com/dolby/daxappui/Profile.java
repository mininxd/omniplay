package com.dolby.daxappui;

/* JADX INFO: loaded from: classes.dex */
class Profile {
    private int mIconNormal;
    private int mIconSelected;

    Profile(int i, int i2) {
        this.mIconSelected = i;
        this.mIconNormal = i2;
    }

    int getIcon(boolean z) {
        return z ? this.mIconSelected : this.mIconNormal;
    }
}

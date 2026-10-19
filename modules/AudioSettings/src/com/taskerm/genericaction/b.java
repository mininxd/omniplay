package com.joaomgcd.taskerm.genericaction;

import c.f.b.k;

/* JADX INFO: loaded from: classes.dex */
public enum b {
    Contact("vnd.android.cursor.dir/contact"),
    Person("vnd.android.cursor.dir/person"),
    Phonev2("vnd.android.cursor.dir/phone_v2"),
    PostalAddressV2("vnd.android.cursor.dir/postal-address_v2"),
    PostalAddress("vnd.android.cursor.dir/postal-address"),
    Emailv2("vnd.android.cursor.dir/email_v2"),
    Group("vnd.android.cursor.dir/group");

    private final String i;

    b(String str) {
        k.b(str, "type");
        this.i = str;
    }

    public final String a() {
        return this.i;
    }
}

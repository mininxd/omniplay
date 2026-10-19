package com.joaomgcd.taskerm.dialog;

import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum ao implements ag {
    Normal(R.string.ml_format_normal, 0),
    EmailAddress(R.string.pl_email_address, 32),
    EmailSubject(R.string.pl_email_subject, 48),
    Filter(R.string.word_filter, 176),
    LongMessage(R.string.pl_long_message, 80),
    Password(R.string.pl_password, 128),
    PersonName(R.string.pl_person_name, 96),
    Phonetic(R.string.pl_phonetic, 192),
    PostalAddress(R.string.pl_postal_address, 112),
    ShortMessage(R.string.pl_short_message, 64),
    Uri(R.string.pl_uri, 16),
    VisiblePassword(R.string.pl_visible_password, 144);

    private final int n;
    private final int o;

    ao(int i, int i2) {
        this.n = i;
        this.o = i2;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int a() {
        return this.n;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int b() {
        return this.o;
    }
}

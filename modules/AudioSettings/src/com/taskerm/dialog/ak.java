package com.joaomgcd.taskerm.dialog;

import androidx.core.app.NotificationCompat;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public enum ak implements ag {
    AutoComplete(R.string.pl_auto_complete, 65536),
    AutoCorrect(R.string.pl_auto_correct, 32768),
    CapsCharacters(R.string.pl_caps_text, NotificationCompat.FLAG_BUBBLE),
    CapsSentences(R.string.pl_caps_sentences, 16384),
    CapsWords(R.string.pl_caps_words, 8192),
    NoSuggestions(R.string.pl_no_suggestions, 524288),
    Multiline(R.string.pl_multiline, 131072);

    private final int i;
    private final int j;

    ak(int i, int i2) {
        this.i = i;
        this.j = i2;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int a() {
        return this.i;
    }

    @Override // com.joaomgcd.taskerm.dialog.ag
    public int b() {
        return this.j;
    }
}

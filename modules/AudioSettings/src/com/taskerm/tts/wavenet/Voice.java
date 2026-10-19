package com.joaomgcd.taskerm.tts.wavenet;

import androidx.annotation.Keep;
import c.l.n;
import com.joaomgcd.taskerm.util.ak;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class Voice {
    private final String languageCode;
    private final String name;
    private final SsmlVoiceGender ssmlGender;

    public Voice(String str, String str2, SsmlVoiceGender ssmlVoiceGender) {
        this.languageCode = str;
        this.name = str2;
        this.ssmlGender = ssmlVoiceGender;
    }

    public /* synthetic */ Voice(String str, String str2, SsmlVoiceGender ssmlVoiceGender, int i, c.f.b.g gVar) {
        this(str, (i & 2) != 0 ? (String) null : str2, (i & 4) != 0 ? (SsmlVoiceGender) null : ssmlVoiceGender);
    }

    public final String getLanguageCode() {
        return this.languageCode;
    }

    public final String getName() {
        return this.name;
    }

    public final SsmlVoiceGender getSsmlGender() {
        return this.ssmlGender;
    }

    private final String getLocaleLanguage() {
        List listB;
        String languageCodeFromName = getLanguageCodeFromName();
        if (languageCodeFromName == null || (listB = n.b((CharSequence) languageCodeFromName, new String[]{"-"}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        return (String) listB.get(0);
    }

    private final String getLocaleCountry() {
        List listB;
        String languageCodeFromName = getLanguageCodeFromName();
        if (languageCodeFromName == null || (listB = n.b((CharSequence) languageCodeFromName, new String[]{"-"}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        return (String) listB.get(1);
    }

    public final Locale getLocale() {
        return new Locale(getLocaleLanguage(), getLocaleCountry());
    }

    private final String getVariant() {
        String strC;
        String str = this.name;
        return String.valueOf((str == null || (strC = ak.c(str, getLanguageCodeFromName())) == null) ? null : ak.a(strC, "-Wavenet-"));
    }

    public String toString() {
        return getLocale().getDisplayName() + ' ' + getVariant() + " (" + this.ssmlGender + ')';
    }

    public final String getLanguageCodeFromName() {
        List listB;
        String str = this.name;
        if (str == null || (listB = n.b((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null)) == null) {
            return null;
        }
        return ((String) listB.get(0)) + '-' + ((String) listB.get(1));
    }

    public final boolean isWaveNet() {
        String str = this.name;
        if (str != null) {
            return n.b((CharSequence) str, (CharSequence) "Wavenet", false, 2, (Object) null);
        }
        return false;
    }
}

package com.joaomgcd.taskerm.nfc;

import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.TagTechnology;
import c.f.b.k;
import c.f.b.l;
import c.i;
import c.s;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static com.joaomgcd.taskerm.nfc.b f8011a;

    public static final <T extends TagTechnology, R> R a(T t, c.f.a.b<? super T, ? extends R> bVar) {
        k.b(t, "receiver$0");
        k.b(bVar, "block");
        try {
            t.connect();
            R rInvoke = bVar.invoke(t);
            try {
                t.close();
                return rInvoke;
            } catch (IOException unused) {
                throw new RuntimeException("Can't disconnect from tag");
            }
        } catch (IOException unused2) {
            throw new RuntimeException("Can't connect to tag");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(Ndef ndef, String str, f fVar) {
        if (!ndef.isWritable()) {
            throw new RuntimeException("Tag is not writable");
        }
        a(ndef, new b(str, fVar));
    }

    static final class b extends l implements c.f.a.b<Ndef, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8013a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f8014b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, f fVar) {
            super(1);
            this.f8013a = str;
            this.f8014b = fVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(Ndef ndef) throws IOException, FormatException {
            a(ndef);
            return s.f2131a;
        }

        public final void a(Ndef ndef) throws IOException, FormatException {
            k.b(ndef, "receiver$0");
            ndef.writeNdefMessage(d.b(this.f8013a, this.f8014b));
        }
    }

    static final class c extends l implements c.f.a.b<NdefFormatable, s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8015a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f8016b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, f fVar) {
            super(1);
            this.f8015a = str;
            this.f8016b = fVar;
        }

        @Override // c.f.a.b
        public /* synthetic */ s invoke(NdefFormatable ndefFormatable) throws IOException, FormatException {
            a(ndefFormatable);
            return s.f2131a;
        }

        public final void a(NdefFormatable ndefFormatable) throws IOException, FormatException {
            k.b(ndefFormatable, "receiver$0");
            ndefFormatable.format(d.b(this.f8015a, this.f8016b));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(NdefFormatable ndefFormatable, String str, f fVar) {
        a(ndefFormatable, new c(str, fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NdefMessage b(String str, f fVar) {
        switch (e.f8017a[fVar.ordinal()]) {
            case 1:
                return new NdefMessage(NdefRecord.createTextRecord(null, str), new NdefRecord[0]);
            case 2:
                return new NdefMessage(NdefRecord.createUri(str), new NdefRecord[0]);
            case 3:
                return new NdefMessage(NdefRecord.createApplicationRecord(str), new NdefRecord[0]);
            default:
                throw new i();
        }
    }

    static final class a extends l implements c.f.a.b<Ndef, NdefMessage> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8012a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final NdefMessage invoke(Ndef ndef) {
            k.b(ndef, "receiver$0");
            return ndef.getNdefMessage();
        }
    }

    private static final NdefRecord c(Ndef ndef) {
        NdefMessage cachedNdefMessage;
        NdefRecord[] records;
        if (ndef == null) {
            return null;
        }
        try {
            cachedNdefMessage = (NdefMessage) a(ndef, a.f8012a);
        } catch (Exception unused) {
            cachedNdefMessage = ndef.getCachedNdefMessage();
        }
        if (cachedNdefMessage == null || (records = cachedNdefMessage.getRecords()) == null) {
            return null;
        }
        return (NdefRecord) c.a.d.a(records, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String d(Ndef ndef) {
        NdefRecord ndefRecordC = c(ndef);
        if (ndefRecordC != null) {
            return a(ndefRecordC);
        }
        return null;
    }

    private static final String a(NdefRecord ndefRecord) {
        if (ndefRecord.getTnf() != 1 || !Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
            return null;
        }
        byte[] payload = ndefRecord.getPayload();
        boolean z = ((byte) (payload[0] & ((byte) 128))) == ((byte) 0);
        byte b2 = (byte) (payload[0] & 63);
        int length = (payload.length - 1) - b2;
        k.a((Object) payload, "payloadBytes");
        Charset charsetForName = Charset.forName("US-ASCII");
        k.a((Object) charsetForName, "Charset.forName(\"US-ASCII\")");
        new String(payload, 1, b2, charsetForName);
        int i = b2 + 1;
        Charset charsetForName2 = Charset.forName(z ? "UTF-8" : "UTF-16");
        k.a((Object) charsetForName2, "if (isUTF8) Charset.forN…Charset.forName(\"UTF-16\")");
        return new String(payload, i, length, charsetForName2);
    }

    public static final Uri a(Ndef ndef) {
        k.b(ndef, "receiver$0");
        NdefRecord ndefRecordC = c(ndef);
        if (ndefRecordC != null) {
            return ndefRecordC.toUri();
        }
        return null;
    }
}

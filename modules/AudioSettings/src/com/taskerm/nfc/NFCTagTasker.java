package com.joaomgcd.taskerm.nfc;

import android.net.Uri;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcBarcode;
import android.nfc.tech.TagTechnology;
import c.a.j;
import c.f.b.k;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.q;
import c.s;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.aj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.dinglisch.android.taskerm.bn;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "nfc")
public final class NFCTagTasker {
    static final /* synthetic */ g[] $$delegatedProperties = {x.a(new v(x.a(NFCTagTasker.class), "techMap", "getTechMap()Ljava/util/HashMap;"))};
    private final Tag tag;
    private final c.e techMap$delegate;

    public final HashMap<Class<TagTechnology>, TagTechnology> getTechMap() {
        c.e eVar = this.techMap$delegate;
        g gVar = $$delegatedProperties[0];
        return (HashMap) eVar.b();
    }

    static final class a extends l implements c.f.a.b<TagTechnology, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f7990a = new a();

        a() {
            super(1);
        }

        public final boolean a(TagTechnology tagTechnology) {
            k.b(tagTechnology, "receiver$0");
            return true;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(TagTechnology tagTechnology) {
            return Boolean.valueOf(a(tagTechnology));
        }
    }

    static final class c extends l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f7993b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ f f7994c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, f fVar) {
            super(0);
            this.f7993b = str;
            this.f7994c = fVar;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            Ndef ndef = (Ndef) NFCTagTasker.this.getTechMap().get(Ndef.class);
            if (ndef != null) {
                d.b(ndef, this.f7993b, this.f7994c);
                return;
            }
            NdefFormatable ndefFormatable = (NdefFormatable) NFCTagTasker.this.getTechMap().get(NdefFormatable.class);
            if (ndefFormatable != null) {
                d.b(ndefFormatable, this.f7993b, this.f7994c);
                return;
            }
            throw new RuntimeException("Tag can't be written to");
        }
    }

    public NFCTagTasker(Tag tag) {
        k.b(tag, "tag");
        this.tag = tag;
        this.techMap$delegate = c.f.a(new b());
    }

    public final Tag getTag() {
        return this.tag;
    }

    public final String getIdString() {
        byte[] id = this.tag.getId();
        k.a((Object) id, "tag.id");
        return aj.b(id);
    }

    static final class b extends l implements c.f.a.a<HashMap<Class<TagTechnology>, TagTechnology>> {
        b() {
            super(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final HashMap<Class<TagTechnology>, TagTechnology> invoke() {
            HashMap<Class<TagTechnology>, TagTechnology> map = new HashMap<>();
            String[] techList = NFCTagTasker.this.getTag().getTechList();
            k.a((Object) techList, "tag.techList");
            for (String str : techList) {
                try {
                    Class<?> cls = Class.forName(str);
                    if (cls == null) {
                        throw new q("null cannot be cast to non-null type java.lang.Class<android.nfc.tech.TagTechnology>");
                    }
                    map.put(cls, (TagTechnology) Cdo.a(cls, "get", (String) null, 2, (Object) null).a(NFCTagTasker.this.getTag()));
                } catch (Exception e2) {
                    bn.c("NFC", "Couldn't get tech: " + e2);
                }
            }
            return map;
        }
    }

    public final List<String> getTechTypes() {
        Set<Class<TagTechnology>> setKeySet = getTechMap().keySet();
        k.a((Object) setKeySet, "techMap.keys");
        Set<Class<TagTechnology>> set = setKeySet;
        ArrayList arrayList = new ArrayList(j.a(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            Class cls = (Class) it.next();
            k.a((Object) cls, "it");
            arrayList.add(cls.getName());
        }
        return arrayList;
    }

    private final <T extends TagTechnology> T getTech() {
        k.a(4, "T");
        TagTechnology tagTechnology = getTechMap().get(TagTechnology.class);
        k.a(1, "T?");
        return (T) tagTechnology;
    }

    public final boolean isPresent() {
        Boolean bool;
        try {
            Collection<TagTechnology> collectionValues = getTechMap().values();
            k.a((Object) collectionValues, "techMap.values");
            TagTechnology tagTechnology = (TagTechnology) j.c(collectionValues);
            bool = tagTechnology != null ? (Boolean) d.a(tagTechnology, a.f7990a) : null;
        } catch (Exception unused) {
            bool = false;
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean isWritable() {
        Iterator<Map.Entry<Class<TagTechnology>, TagTechnology>> it = getTechMap().entrySet().iterator();
        while (it.hasNext()) {
            TagTechnology value = it.next().getValue();
            if ((value instanceof Ndef) || (value instanceof NdefFormatable)) {
                return true;
            }
        }
        return false;
    }

    public final String getPayload() {
        Iterator<Map.Entry<Class<TagTechnology>, TagTechnology>> it = getTechMap().entrySet().iterator();
        while (it.hasNext()) {
            TagTechnology value = it.next().getValue();
            if (value instanceof Ndef) {
                Ndef ndef = (Ndef) value;
                String strD = d.d(ndef);
                if (strD != null) {
                    return strD;
                }
                Uri uriA = d.a(ndef);
                if (uriA != null) {
                    return uriA.toString();
                }
                return null;
            }
            if (value instanceof NfcBarcode) {
                byte[] barcode = ((NfcBarcode) value).getBarcode();
                k.a((Object) barcode, "tech.barcode");
                return new String(barcode, c.l.d.f2083a);
            }
        }
        return null;
    }

    public static /* synthetic */ b.a.b write$default(NFCTagTasker nFCTagTasker, String str, f fVar, int i, Object obj) {
        if ((i & 2) != 0) {
            fVar = f.Text;
        }
        return nFCTagTasker.write(str, fVar);
    }

    public final b.a.b write(String str, f fVar) {
        k.b(fVar, "type");
        String str2 = str;
        if (!(str2 == null || str2.length() == 0)) {
            return i.d(new c(str, fVar));
        }
        b.a.b bVarA = b.a.b.a();
        k.a((Object) bVarA, "Completable.complete()");
        return bVarA;
    }

    public final boolean getHasTextPayload() {
        Ndef ndef = (Ndef) getTechMap().get(Ndef.class);
        return (ndef == null || d.a(ndef) != null || d.d(ndef) == null) ? false : true;
    }
}

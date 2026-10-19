package com.joaomgcd.taskerm.profile;

import android.text.Editable;
import android.widget.EditText;
import c.s;

/* JADX INFO: loaded from: classes.dex */
final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final EditText f8333a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.f.a.b<String, String> f8334b;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.profile.m$1, reason: invalid class name */
    static final class AnonymousClass1 extends c.f.b.l implements c.f.a.b<String, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final AnonymousClass1 f8335a = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(String str) {
            c.f.b.k.b(str, "it");
            return str;
        }
    }

    static final class a extends c.f.b.l implements c.f.a.a<s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f8337b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f8337b = str;
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            EditText editTextA = m.this.a();
            if (editTextA != null) {
                editTextA.setText(this.f8337b);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public m(EditText editText, c.f.a.b<? super String, String> bVar) {
        c.f.b.k.b(bVar, "changer");
        this.f8333a = editText;
        this.f8334b = bVar;
    }

    public /* synthetic */ m(EditText editText, AnonymousClass1 anonymousClass1, int i, c.f.b.g gVar) {
        this(editText, (i & 2) != 0 ? AnonymousClass1.f8335a : anonymousClass1);
    }

    protected final EditText a() {
        return this.f8333a;
    }

    public final String a(Object obj, c.j.g<?> gVar) {
        Editable text;
        String string;
        c.f.b.k.b(gVar, "property");
        c.f.a.b<String, String> bVar = this.f8334b;
        EditText editText = this.f8333a;
        if (editText == null || (text = editText.getText()) == null || (string = text.toString()) == null) {
            throw new RuntimeException("Missing value");
        }
        return bVar.invoke(string);
    }

    public final void a(Object obj, c.j.g<?> gVar, String str) {
        c.f.b.k.b(gVar, "property");
        c.f.b.k.b(str, "value");
        if (str.length() == 0) {
            return;
        }
        com.joaomgcd.taskerm.rx.i.e(new a(str));
    }
}

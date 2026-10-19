package com.joaomgcd.taskerm.pattern;

import android.app.Activity;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.h;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class e extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f8203c = new a(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(String str, String str2) {
        super(f.b(str), str2);
        k.b(str, "patternString");
        k.b(str2, "toMatch");
    }

    @Override // com.joaomgcd.taskerm.pattern.d
    public RegexMatchesOutputBase d() {
        return new RegexMatchesOutputBase(this);
    }

    public static final class a implements com.joaomgcd.taskerm.pattern.a {
        @Override // com.joaomgcd.taskerm.pattern.a
        public boolean a() {
            return false;
        }

        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.e$a$a, reason: collision with other inner class name */
        static final class C0214a extends l implements c.f.a.a<String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Activity f8204a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0214a(Activity activity) {
                super(0);
                this.f8204a = activity;
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                String str = (String) y.a(this.f8204a, R.string.simple_pattern, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.insert_an_example_of_something_simple_pattern), (120 & 8) != 0 ? (h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
                y.b(this.f8204a, R.string.simple_pattern, R.string.now_create_variables_long_click_simple_pattern, 0, 8, (Object) null).b();
                return str;
            }
        }

        @Override // com.joaomgcd.taskerm.pattern.a
        public b.a.l<String> a(Activity activity) {
            k.b(activity, "activity");
            return i.b(new C0214a(activity));
        }

        @Override // com.joaomgcd.taskerm.pattern.a
        public List<String> a(String str) {
            return d.f8194b.a(str != null ? f.b(str) : null);
        }
    }
}

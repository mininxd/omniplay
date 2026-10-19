package com.joaomgcd.taskerm.pattern;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Keep;
import c.f.a.m;
import c.f.b.g;
import c.f.b.k;
import c.f.b.l;
import c.f.b.w;
import c.l.n;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.h;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class RegexHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Activity f8166a;

    static final class a extends l implements c.f.a.b<HelpTypes, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8182a = new a();

        a() {
            super(1);
        }

        public final boolean a(HelpTypes helpTypes) {
            k.b(helpTypes, "it");
            return true;
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(HelpTypes helpTypes) {
            return Boolean.valueOf(a(helpTypes));
        }
    }

    static final class e extends l implements c.f.a.b<HelpTypes, Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f8187a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Boolean invoke(HelpTypes helpTypes) {
            return Boolean.valueOf(a(helpTypes));
        }

        public final boolean a(HelpTypes helpTypes) {
            k.b(helpTypes, "it");
            return !helpTypes.isGroup();
        }
    }

    public RegexHelper(Activity activity) {
        k.b(activity, "activity");
        this.f8166a = activity;
    }

    public final Activity b() {
        return this.f8166a;
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'Word' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    @Keep
    private static final class HelpTypes {
        private static final /* synthetic */ HelpTypes[] $VALUES;
        public static final HelpTypes AnyOfCharacters;
        public static final HelpTypes Digit;
        public static final HelpTypes Group;
        public static final HelpTypes NamedGroup;
        public static final HelpTypes NoneOfCharacters;
        public static final HelpTypes NotWord;
        public static final HelpTypes WhiteSpace;
        public static final HelpTypes Word;
        private final c.f.a.b<Activity, String> get;
        private final boolean isGroup;
        private final int titleResId;

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$2, reason: invalid class name */
        static final class AnonymousClass2 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass2 f8168a = new AnonymousClass2();

            AnonymousClass2() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return "(AJIKJI!)(/){{][€])";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$3, reason: invalid class name */
        static final class AnonymousClass3 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass3 f8169a = new AnonymousClass3();

            AnonymousClass3() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return "\\w";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$4, reason: invalid class name */
        static final class AnonymousClass4 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass4 f8170a = new AnonymousClass4();

            AnonymousClass4() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return "\\W";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$5, reason: invalid class name */
        static final class AnonymousClass5 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass5 f8171a = new AnonymousClass5();

            AnonymousClass5() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return "\\s";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$8, reason: invalid class name */
        static final class AnonymousClass8 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass8 f8174a = new AnonymousClass8();

            AnonymousClass8() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return "\\d";
            }
        }

        public static HelpTypes valueOf(String str) {
            return (HelpTypes) Enum.valueOf(HelpTypes.class, str);
        }

        public static HelpTypes[] values() {
            return (HelpTypes[]) $VALUES.clone();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        protected HelpTypes(String str, int i, int i2, c.f.a.b bVar, boolean z) {
            super(str, i);
            k.b(bVar, "get");
            this.titleResId = i2;
            this.get = bVar;
            this.isGroup = z;
        }

        /* synthetic */ HelpTypes(String str, int i, int i2, c.f.a.b bVar, boolean z, int i3, g gVar) {
            this(str, i, i2, bVar, (i3 & 4) != 0 ? false : z);
        }

        public final c.f.a.b<Activity, String> getGet() {
            return this.get;
        }

        public final int getTitleResId() {
            return this.titleResId;
        }

        public final boolean isGroup() {
            return this.isGroup;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f8167a = new AnonymousClass1();

            AnonymousClass1() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "activity");
                Object objB = y.a(activity, R.string.named_group, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.insert_named_group), (120 & 8) != 0 ? (h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b();
                k.a(objB, "dialogInput(activity, R.…amed_group).blockingGet()");
                return "(?<" + ak.q((String) objB) + ">AJIKJI!)(/){{][€])";
            }
        }

        static {
            HelpTypes helpTypes = new HelpTypes("NamedGroup", 0, R.string.named_group, AnonymousClass1.f8167a, true);
            NamedGroup = helpTypes;
            HelpTypes helpTypes2 = new HelpTypes("Group", 1, R.string.group, AnonymousClass2.f8168a, true);
            Group = helpTypes2;
            boolean z = false;
            int i = 4;
            g gVar = null;
            HelpTypes helpTypes3 = new HelpTypes("Word", 2, R.string.word_character, AnonymousClass3.f8169a, z, i, gVar);
            Word = helpTypes3;
            HelpTypes helpTypes4 = new HelpTypes("NotWord", 3, R.string.not_word_character, AnonymousClass4.f8170a, z, i, gVar);
            NotWord = helpTypes4;
            HelpTypes helpTypes5 = new HelpTypes("WhiteSpace", 4, R.string.whitespace, AnonymousClass5.f8171a, z, i, gVar);
            WhiteSpace = helpTypes5;
            HelpTypes helpTypes6 = new HelpTypes("AnyOfCharacters", 5, R.string.any_of_certain_characters, AnonymousClass6.f8172a, z, i, gVar);
            AnyOfCharacters = helpTypes6;
            HelpTypes helpTypes7 = new HelpTypes("NoneOfCharacters", 6, R.string.none_of_certain_characters, AnonymousClass7.f8173a, z, i, gVar);
            NoneOfCharacters = helpTypes7;
            HelpTypes helpTypes8 = new HelpTypes("Digit", 7, R.string.digit, AnonymousClass8.f8174a, z, i, gVar);
            Digit = helpTypes8;
            $VALUES = new HelpTypes[]{helpTypes, helpTypes2, helpTypes3, helpTypes4, helpTypes5, helpTypes6, helpTypes7, helpTypes8};
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$6, reason: invalid class name */
        static final class AnonymousClass6 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass6 f8172a = new AnonymousClass6();

            AnonymousClass6() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return '[' + ((String) y.a(activity, R.string.any_of_certain_characters, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.which_characters_do_you_want_to_allow), (120 & 8) != 0 ? (h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b()) + ']';
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$HelpTypes$7, reason: invalid class name */
        static final class AnonymousClass7 extends l implements c.f.a.b<Activity, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass7 f8173a = new AnonymousClass7();

            AnonymousClass7() {
                super(1);
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity) {
                k.b(activity, "it");
                return "[^" + ((String) y.a(activity, R.string.none_of_certain_characters, (120 & 4) != 0 ? (Integer) null : Integer.valueOf(R.string.which_characters_dont_you_want_to_allow), (120 & 8) != 0 ? (h) null : null, (120 & 16) != 0 ? (Integer) null : null, (120 & 32) != 0 ? (Integer) null : null, (List<String>) ((120 & 64) != 0 ? (List) null : null)).b()) + ']';
            }
        }
    }

    public final b.a.l<String> a() {
        return i.b(new c());
    }

    static final class c extends l implements c.f.a.a<String> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            if (!y.b(RegexHelper.this.b(), R.string.ml_matches_regex, R.string.these_are_just_few_regex_examples_learn_more, R.string.learn_more).b().e() || com.joaomgcd.taskerm.q.b.a(RegexHelper.this.b(), aj.a(R.string.these_are_just_few_regex_examples_learn_more, RegexHelper.this.b()))) {
                return RegexHelper.this.c();
            }
            ah.a((Context) RegexHelper.this.b(), "https://www.regular-expressions.info/tutorial.html", false, false, false, 14, (Object) null);
            return "";
        }
    }

    static final class b extends l implements c.f.a.b<HelpTypes, String> {
        b() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(HelpTypes helpTypes) {
            k.b(helpTypes, "it");
            return aj.a(helpTypes.getTitleResId(), RegexHelper.this.b(), new Object[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ HelpTypes a(RegexHelper regexHelper, String str, String str2, c.f.a.b bVar, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = aj.a(R.string.ml_matches_regex, regexHelper.f8166a, new Object[0]);
        }
        if ((i & 4) != 0) {
            bVar = a.f8182a;
        }
        return regexHelper.a(str, str2, bVar);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    private final HelpTypes a(String str, String str2, c.f.a.b<? super HelpTypes, Boolean> bVar) {
        String str3;
        Activity activity = this.f8166a;
        if (str != null) {
            str3 = str2 + ": " + str;
            if (str3 == null) {
                str3 = str2;
            }
        } else {
            str3 = str2;
        }
        return (HelpTypes) com.joaomgcd.taskerm.dialog.k.a(activity, str3, HelpTypes.class, new b(), bVar).b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v16, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v5, types: [T, java.lang.String] */
    public final String c() {
        String strA;
        HelpTypes helpTypesA = a(this, null, null, null, 7, null);
        if (helpTypesA.isGroup()) {
            w.d dVar = new w.d();
            dVar.f2028a = "";
            String strInvoke = helpTypesA.getGet().invoke(this.f8166a);
            if (strInvoke == null) {
                throw new RuntimeException("No value for group");
            }
            d dVar2 = new d(strInvoke, dVar);
            String strA2 = aj.a(R.string.add_to_regex_group, this.f8166a, new Object[0]);
            dVar.f2028a = a(dVar2.invoke(), strA2);
            while (true) {
                if (((x) y.a(this.f8166a, R.string.group, aj.a(R.string.add_group_content_question, this.f8166a, new Object[0]) + ' ' + dVar2.invoke(), 0, 8, (Object) null).b()).d()) {
                    dVar.f2028a = ((String) dVar.f2028a) + a(dVar2.invoke(), strA2);
                } else {
                    return a(dVar2.invoke());
                }
            }
        } else {
            String strInvoke2 = helpTypesA.getGet().invoke(this.f8166a);
            return (strInvoke2 == null || (strA = a(strInvoke2)) == null) ? "" : strA;
        }
    }

    static final class d extends l implements c.f.a.a<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f8185a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ w.d f8186b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, w.d dVar) {
            super(0);
            this.f8185a = str;
            this.f8186b = dVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return n.a(this.f8185a, "AJIKJI!)(/){{][€]", (String) this.f8186b.f2028a, false, 4, (Object) null);
        }
    }

    private final String a(String str, String str2) {
        String strA;
        String strInvoke = a(str, str2, e.f8187a).getGet().invoke(this.f8166a);
        return (strInvoke == null || (strA = a(strInvoke)) == null) ? "" : strA;
    }

    @Keep
    private enum RepeatTypes {
        None(R.string.word_none, AnonymousClass1.f8175a),
        ZeroOrMore(R.string.zero_or_more, AnonymousClass2.f8176a),
        OneOrMore(R.string.once_or_more, AnonymousClass3.f8177a),
        ExactNumber(R.string.exact_number, AnonymousClass4.f8178a),
        Minimum(R.string.minimum_number_of_time, AnonymousClass5.f8179a),
        Maximum(R.string.maximum_number_of_time, AnonymousClass6.f8180a),
        MinAndMax(R.string.between_minimum_and_max_times, AnonymousClass7.f8181a);

        private final m<Activity, String, String> get;
        private final int titleResId;

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass1 f8175a = new AnonymousClass1();

            AnonymousClass1() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "<anonymous parameter 0>");
                k.b(str, "<anonymous parameter 1>");
                return "";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$2, reason: invalid class name */
        static final class AnonymousClass2 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass2 f8176a = new AnonymousClass2();

            AnonymousClass2() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "<anonymous parameter 0>");
                k.b(str, "<anonymous parameter 1>");
                return "*";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$3, reason: invalid class name */
        static final class AnonymousClass3 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass3 f8177a = new AnonymousClass3();

            AnonymousClass3() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "<anonymous parameter 0>");
                k.b(str, "<anonymous parameter 1>");
                return "+";
            }
        }

        RepeatTypes(int i, m mVar) {
            k.b(mVar, "get");
            this.titleResId = i;
            this.get = mVar;
        }

        public final m<Activity, String, String> getGet() {
            return this.get;
        }

        public final int getTitleResId() {
            return this.titleResId;
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$4, reason: invalid class name */
        static final class AnonymousClass4 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass4 f8178a = new AnonymousClass4();

            AnonymousClass4() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "activity");
                k.b(str, "toRepeat");
                return '{' + com.joaomgcd.taskerm.pattern.b.b(activity, str, R.string.how_many_times_repeat) + '}';
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$5, reason: invalid class name */
        static final class AnonymousClass5 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass5 f8179a = new AnonymousClass5();

            AnonymousClass5() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "activity");
                k.b(str, "toRepeat");
                return '{' + com.joaomgcd.taskerm.pattern.b.b(activity, str, R.string.how_many_times_repeat_min) + ",}";
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$6, reason: invalid class name */
        static final class AnonymousClass6 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass6 f8180a = new AnonymousClass6();

            AnonymousClass6() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "activity");
                k.b(str, "toRepeat");
                return "{1," + com.joaomgcd.taskerm.pattern.b.b(activity, str, R.string.how_many_times_repeat_max) + '}';
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.pattern.RegexHelper$RepeatTypes$7, reason: invalid class name */
        static final class AnonymousClass7 extends l implements m<Activity, String, String> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final AnonymousClass7 f8181a = new AnonymousClass7();

            AnonymousClass7() {
                super(2);
            }

            @Override // c.f.a.m
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(Activity activity, String str) {
                k.b(activity, "activity");
                k.b(str, "toRepeat");
                return '{' + com.joaomgcd.taskerm.pattern.b.b(activity, str, R.string.how_many_times_repeat_min) + ',' + com.joaomgcd.taskerm.pattern.b.b(activity, str, R.string.how_many_times_repeat_max) + '}';
            }
        }
    }

    static final class f extends l implements c.f.a.b<RepeatTypes, String> {
        f() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(RepeatTypes repeatTypes) {
            k.b(repeatTypes, "it");
            return aj.a(repeatTypes.getTitleResId(), RegexHelper.this.b(), new Object[0]);
        }
    }

    private final String a(String str) {
        RepeatTypes repeatTypes = (RepeatTypes) com.joaomgcd.taskerm.dialog.k.a(this.f8166a, aj.a(R.string.repeat, this.f8166a, new Object[0]) + " " + str, RepeatTypes.class, new f(), com.joaomgcd.taskerm.dialog.k.v.f6374a).b();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String strInvoke = repeatTypes.getGet().invoke(this.f8166a, str);
        if (strInvoke == null) {
            strInvoke = "";
        }
        sb.append((Object) strInvoke);
        return sb.toString();
    }
}

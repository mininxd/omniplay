package com.joaomgcd.taskerm.action.f;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import cyanogenmod.app.ProfileManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;
import net.dinglisch.android.taskerm.fs;
import net.dinglisch.android.taskerm.fx;
import net.dinglisch.android.taskerm.gw;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class u extends com.joaomgcd.taskerm.helper.actions.execute.l<ab> {
    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public boolean a() {
        return false;
    }

    static final class a extends c.f.b.l implements c.f.a.m<String, Object, c.s> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.inputoutput.a.b f5272a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ u f5273b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f5274c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ab f5275d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ List f5276e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(com.joaomgcd.taskerm.inputoutput.a.b bVar, u uVar, List list, ab abVar, List list2) {
            super(2);
            this.f5272a = bVar;
            this.f5273b = uVar;
            this.f5274c = list;
            this.f5275d = abVar;
            this.f5276e = list2;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(String str, Object obj) {
            a(str, obj);
            return c.s.f2131a;
        }

        public final void a(String str, Object obj) {
            com.joaomgcd.taskerm.inputoutput.a.a aVar;
            c.f.b.k.b(str, ProfileManager.EXTRA_PROFILE_NAME);
            if (com.joaomgcd.taskerm.util.ak.x(str)) {
                if (obj != null ? obj instanceof String : true) {
                    aVar = new com.joaomgcd.taskerm.inputoutput.a.a((String) null, str, (String) obj);
                } else {
                    if (obj != null ? obj instanceof Collection : true) {
                        aVar = new com.joaomgcd.taskerm.inputoutput.a.a((String) null, str, (Collection<?>) obj);
                    } else {
                        if (!(obj != null ? obj instanceof Object[] : true)) {
                            return;
                        } else {
                            aVar = new com.joaomgcd.taskerm.inputoutput.a.a((String) null, str, (Object[]) obj);
                        }
                    }
                }
                this.f5272a.a(this.f5273b.h(), aVar);
                return;
            }
            if (obj != null ? obj instanceof String : true) {
                gw.a(this.f5273b.h(), str, (String) obj, "FormatDateTime");
                return;
            }
            if (!(obj instanceof Collection)) {
                if (!(obj instanceof Object[])) {
                    return;
                } else {
                    obj = c.a.d.k((Object[]) obj);
                }
            }
            Collection collection = (Collection) obj;
            if (collection.size() > 1) {
                u uVar = this.f5273b;
                Collection collection2 = collection;
                ArrayList arrayList = new ArrayList(c.a.j.a(collection2, 10));
                Iterator it = collection2.iterator();
                while (it.hasNext()) {
                    arrayList.add(String.valueOf(it.next()));
                }
                uVar.a(str, arrayList);
            }
        }
    }

    static final class b extends c.f.b.l implements c.f.a.m<String, String, c.s> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f5278b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ab f5279c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ List f5280d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(List list, ab abVar, List list2) {
            super(2);
            this.f5278b = list;
            this.f5279c = abVar;
            this.f5280d = list2;
        }

        @Override // c.f.a.m
        public /* synthetic */ c.s invoke(String str, String str2) {
            a(str, str2);
            return c.s.f2131a;
        }

        public final void a(String str, String str2) {
            fs fsVarF;
            c.f.b.k.b(str2, ProfileManager.EXTRA_PROFILE_NAME);
            if (str == null || (fsVarF = u.this.f()) == null) {
                return;
            }
            fsVarF.h(str);
            fx fxVarE = fx.e(u.this.h());
            c.f.b.k.a((Object) fxVarE, "TaskerData.getActive(service)");
            com.joaomgcd.taskerm.util.aj.a(fxVarE, fsVarF, str2, str);
        }
    }

    static final class c extends c.f.b.l implements c.f.a.q<String, String, Boolean, String[]> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ab f5282b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(ab abVar) {
            super(3);
            this.f5282b = abVar;
        }

        @Override // c.f.a.q
        public /* synthetic */ String[] a(String str, String str2, Boolean bool) {
            return a(str, str2, bool.booleanValue());
        }

        public static /* synthetic */ String[] a(c cVar, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return cVar.a(str, str2, z);
        }

        public final String[] a(String str, String str2, boolean z) {
            Collection collectionInvoke;
            String str3 = str;
            if (str3 == null || str3.length() == 0) {
                return null;
            }
            if (c.l.n.b((CharSequence) str3, (CharSequence) "%", false, 2, (Object) null)) {
                str = com.joaomgcd.taskerm.util.ak.a(str, u.this.h(), u.this.j());
            }
            boolean z2 = !z && this.f5282b.getDoMathsNotNull();
            if (str2 == null) {
                str2 = com.joaomgcd.taskerm.action.f.c.b(str);
            }
            c.f.b.k.a((Object) str, "valueToUse");
            List listB = c.l.n.b((CharSequence) str, new String[]{str2}, false, 0, 6, (Object) null);
            a aVar = new a(z2);
            b bVar = new b(aVar);
            List<String> list = listB;
            ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
            for (String str4 : list) {
                if (com.joaomgcd.taskerm.util.ak.v(str4)) {
                    List listA = com.joaomgcd.taskerm.util.aj.a((List) com.joaomgcd.taskerm.util.ak.b(str4, u.this.h(), u.this.j()));
                    if (listA == null) {
                        collectionInvoke = bVar.invoke(str4);
                    } else {
                        List list2 = listA;
                        collectionInvoke = new ArrayList(c.a.j.a((Iterable) list2, 10));
                        Iterator it = list2.iterator();
                        while (it.hasNext()) {
                            collectionInvoke.add(aVar.invoke((String) it.next()));
                        }
                    }
                } else {
                    collectionInvoke = bVar.invoke(str4);
                }
                arrayList.add((List) collectionInvoke);
            }
            Object[] array = c.a.j.a((Iterable) arrayList).toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
        }

        static final class a extends c.f.b.l implements c.f.a.b<String, String> {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f5284b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(boolean z) {
                super(1);
                this.f5284b = z;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke(String str) {
                c.f.b.k.b(str, "value");
                if (c.l.n.b((CharSequence) str, (CharSequence) "%", false, 2, (Object) null)) {
                    if (this.f5284b) {
                        ExecuteService executeServiceH = u.this.h();
                        String strM = u.this.i().m();
                        c.f.b.k.a((Object) strM, "action.displayName");
                        str = com.joaomgcd.taskerm.util.ak.a(str, executeServiceH, strM, 0, u.this.j(), (16 & 16) != 0 ? false : false);
                        if (str == null) {
                            str = "";
                        }
                    } else {
                        str = com.joaomgcd.taskerm.util.ak.a(str, u.this.h(), u.this.j());
                    }
                } else if (this.f5284b) {
                    ExecuteService executeServiceH2 = u.this.h();
                    String strM2 = u.this.i().m();
                    c.f.b.k.a((Object) strM2, "action.displayName");
                    str = com.joaomgcd.taskerm.util.ak.a(str, executeServiceH2, strM2, 0, u.this.j(), (16 & 16) != 0 ? false : false);
                }
                return str != null ? str : "";
            }
        }

        static final class b extends c.f.b.l implements c.f.a.b<String, List<? extends String>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.f.a.b f5285a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(c.f.a.b bVar) {
                super(1);
                this.f5285a = bVar;
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final List<String> invoke(String str) {
                c.f.b.k.b(str, "value");
                return c.a.j.a(this.f5285a.invoke(str));
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ab, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    private final String a(List<String> list, int i) {
        String str;
        if (list != null && (str = (String) c.a.j.a((List) list, i)) != null) {
            return str;
        }
        if (list != null) {
            return (String) c.a.j.h((List) list);
        }
        return null;
    }

    private final String a(String[] strArr, int i) {
        String str;
        if (strArr != null && (str = (String) c.a.d.a(strArr, i)) != null) {
            return str;
        }
        if (strArr != null) {
            return (String) c.a.d.f(strArr);
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ab abVar) {
        ArrayList arrayListA;
        DateTime dateTime;
        DateTime dateTimeB;
        ArrayList arrayList;
        String str;
        aj aiVar;
        c.f.b.k.b(abVar, "input");
        c cVar = new c(abVar);
        l inputType = abVar.getInputType();
        if (inputType == null) {
            return cn.a("No input type provided");
        }
        String[] strArrA = c.a(cVar, abVar.getInputFormats(), abVar.getInputSeparator(), false, 2, null);
        Calendar calendar = Calendar.getInstance();
        c.f.b.k.a((Object) calendar, "Calendar.getInstance()");
        DateTimeZone dateTimeZoneA = DateTimeZone.a(calendar.getTimeZone());
        int i = 0;
        if (inputType == l.Now) {
            arrayListA = c.a.j.a(DateTime.a(dateTimeZoneA));
        } else {
            String[] strArr = (String[]) com.joaomgcd.taskerm.util.aj.a((Object[]) c.a(cVar, abVar.getInputs(), abVar.getInputSeparator(), false, 2, null));
            if (strArr == null) {
                return cn.a("No input dates provided");
            }
            ArrayList arrayList2 = new ArrayList(strArr.length);
            int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                String str2 = strArr[i2];
                int i4 = i3 + 1;
                switch (inputType) {
                    case Millis:
                    case MillisUTC:
                        Long lD = c.l.n.d(str2);
                        if (lD == null) {
                            return cn.a("Invalid millis \"" + str2 + '\"');
                        }
                        long jLongValue = lD.longValue();
                        if (inputType == l.Millis) {
                            dateTime = new DateTime(jLongValue, dateTimeZoneA);
                        } else {
                            dateTime = new DateTime(jLongValue, DateTimeZone.f13409a);
                            continue;
                        }
                        arrayList2.add(dateTime);
                        i2++;
                        i3 = i4;
                        break;
                        break;
                    case Seconds:
                    case SecondsUTC:
                        Long lD2 = c.l.n.d(str2);
                        if (lD2 == null) {
                            return cn.a("Invalid seconds \"" + str2 + '\"');
                        }
                        long jLongValue2 = lD2.longValue() * 1000;
                        if (inputType == l.Seconds) {
                            dateTime = new DateTime(jLongValue2, dateTimeZoneA);
                            continue;
                        } else {
                            dateTime = new DateTime(jLongValue2, DateTimeZone.f13409a);
                        }
                        arrayList2.add(dateTime);
                        i2++;
                        i3 = i4;
                        break;
                        break;
                    case ISO8601:
                        dateTimeB = org.joda.time.format.i.b().b(str2);
                        break;
                    case Custom:
                        String strA = a(strArrA, i3);
                        if (strA == null) {
                            return cn.a("No custom format provided");
                        }
                        dateTimeB = org.joda.time.format.a.a(strA).b(str2);
                        break;
                        break;
                    case Now:
                        return cn.a("Now doesn't need an input");
                    default:
                        throw new c.i();
                }
                dateTime = dateTimeB;
                arrayList2.add(dateTime);
                i2++;
                i3 = i4;
            }
            arrayListA = arrayList2;
        }
        Boolean getAllDetails = abVar.getGetAllDetails();
        boolean zBooleanValue = getAllDetails != null ? getAllDetails.booleanValue() : false;
        List<Integer> outputOffsetsSeconds = abVar.getOutputOffsetsSeconds();
        Iterable iterable = arrayListA;
        ArrayList arrayList3 = new ArrayList(c.a.j.a(iterable, 10));
        int i5 = 0;
        for (Object obj : iterable) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                c.a.j.b();
            }
            DateTime dateTimeB2 = (DateTime) obj;
            Integer num = (Integer) c.a.j.a((List) outputOffsetsSeconds, i5);
            if (num == null) {
                num = (Integer) c.a.j.h((List) outputOffsetsSeconds);
            }
            if (num != null) {
                if (abVar.getOutputOffsetTypeNotNull() == ak.Days) {
                    dateTimeB2 = dateTimeB2.a(((num.intValue() / 60) / 60) / 24);
                } else {
                    dateTimeB2 = dateTimeB2.b(num.intValue());
                }
            }
            if (zBooleanValue) {
                c.f.b.k.a((Object) dateTimeB2, "dateTimeAfterOffset");
                aiVar = new aj(dateTimeB2);
            } else {
                c.f.b.k.a((Object) dateTimeB2, "dateTimeAfterOffset");
                aiVar = new ai(dateTimeB2);
            }
            arrayList3.add(aiVar);
            i5 = i6;
        }
        ArrayList arrayList4 = arrayList3;
        String[] strArr2 = (String[]) com.joaomgcd.taskerm.util.aj.a((Object[]) cVar.a(abVar.getOutputFormats(), abVar.getOutputFormatSeparator(), true));
        if (strArr2 != null) {
            ArrayList arrayList5 = new ArrayList(strArr2.length);
            for (String str3 : strArr2) {
                org.joda.time.format.b bVarA = org.joda.time.format.a.a(c.l.n.a(str3, "u", "e", false, 4, (Object) null));
                ArrayList arrayList6 = new ArrayList();
                Iterator it = arrayList4.iterator();
                while (it.hasNext()) {
                    String strA2 = bVarA != null ? bVarA.a(((ai) it.next()).getDateTime()) : null;
                    if (strA2 != null) {
                        arrayList6.add(strA2);
                    }
                }
                arrayList5.add(arrayList6);
            }
            arrayList = arrayList5;
        } else {
            arrayList = null;
        }
        List listA = com.joaomgcd.taskerm.util.aj.a((List) arrayList);
        List<String> listA2 = listA != null ? c.a.j.a((Iterable) listA) : null;
        com.joaomgcd.taskerm.inputoutput.a.b bVar = new com.joaomgcd.taskerm.inputoutput.a.b();
        ExecuteService executeServiceH = h();
        Object[] array = arrayList4.toArray(new ai[0]);
        if (array != null) {
            bVar.a(executeServiceH, array);
            bVar.a(h(), c.a.j.f((List) arrayList4));
            List<String> formattedVariableNames = abVar.getFormattedVariableNames();
            List listA3 = (List) com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new d(formattedVariableNames), 1, (Object) null);
            if (listA3 == null) {
                listA3 = c.a.j.a();
            }
            List list = listA3;
            String str4 = (String) c.a.j.h((List) formattedVariableNames);
            a aVar = new a(bVar, this, arrayList4, abVar, listA2);
            b bVar2 = new b(arrayList4, abVar, listA2);
            for (Object obj2 : list) {
                int i7 = i + 1;
                if (i < 0) {
                    c.a.j.b();
                }
                String str5 = (String) obj2;
                String strA3 = a(listA2, i);
                aVar.a(str5, strA3);
                bVar2.a(strA3, str5);
                i = i7;
            }
            if (str4 != null) {
                if (listA2 == null || (str = (String) c.a.j.a((List) listA2, list.size())) == null) {
                    str = listA2 != null ? (String) c.a.j.h((List) listA2) : null;
                }
                bVar2.a(str, str4);
                aVar.a(str4, str);
                aVar.a(str4, listA2 != null ? c.a.j.b((Iterable) listA2, list.size()) : null);
                c.s sVar = c.s.f2131a;
            }
            c.s sVar2 = c.s.f2131a;
            return cn.a(bVar);
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    static final class d extends c.f.b.l implements c.f.a.a<List<? extends String>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f5286a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(List list) {
            super(0);
            this.f5286a = list;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            return c.a.j.b(this.f5286a, 1);
        }
    }
}

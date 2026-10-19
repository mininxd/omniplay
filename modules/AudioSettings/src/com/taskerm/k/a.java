package com.joaomgcd.taskerm.k;

import android.annotation.TargetApi;
import android.content.Context;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import c.a.j;
import c.a.z;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.ah;
import com.joaomgcd.taskerm.util.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0177a f7559a = new C0177a(null);

    @TargetApi(22)
    public static final SmsManager a(Context context, String str) {
        return f7559a.b(context, str);
    }

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.k.a$a, reason: collision with other inner class name */
    public static final class C0177a {
        private C0177a() {
        }

        public /* synthetic */ C0177a(g gVar) {
            this();
        }

        private final SubscriptionManager b(Context context) {
            Object systemService = context.getSystemService("telephony_subscription_service");
            if (systemService != null) {
                return (SubscriptionManager) systemService;
            }
            throw new q("null cannot be cast to non-null type android.telephony.SubscriptionManager");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<SubscriptionInfo> c(Context context) throws SecurityException {
            return a.f7559a.b(context).getActiveSubscriptionInfoList();
        }

        private final PhoneAccountHandle a(SubscriptionInfo subscriptionInfo, Context context) throws SecurityException {
            TelecomManager telecomManagerQ;
            TelephonyManager telephonyManagerAp;
            if (subscriptionInfo == null || (telecomManagerQ = ah.q(context)) == null || (telephonyManagerAp = ah.ap(context)) == null) {
                return null;
            }
            List<PhoneAccountHandle> callCapablePhoneAccounts = telecomManagerQ.getCallCapablePhoneAccounts();
            k.a((Object) callCapablePhoneAccounts, "callCapablePhoneAccounts");
            for (PhoneAccountHandle phoneAccountHandle : callCapablePhoneAccounts) {
                PhoneAccount phoneAccount = telecomManagerQ.getPhoneAccount(phoneAccountHandle);
                k.a((Object) phoneAccount, "phoneAccount");
                Integer numA = b.a(telephonyManagerAp, phoneAccount);
                if (numA != null && numA.intValue() == subscriptionInfo.getSubscriptionId()) {
                    return phoneAccountHandle;
                }
            }
            return null;
        }

        private final SubscriptionInfo d(Context context, String str) {
            List<SubscriptionInfo> listC = c(context);
            if (listC == null) {
                return null;
            }
            int iIntValue = 0;
            boolean zB = n.b((CharSequence) str, (CharSequence) "=:=", false, 2, (Object) null);
            String strA = zB ? n.a(str, "=:=", (String) null, 2, (Object) null) : str;
            ArrayList arrayList = new ArrayList();
            for (Object obj : listC) {
                if (k.a((Object) ((SubscriptionInfo) obj).getDisplayName(), (Object) strA)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (arrayList2.isEmpty()) {
                return null;
            }
            if (zB) {
                Integer numC = n.c(n.b(str, "=:=", (String) null, 2, (Object) null));
                iIntValue = (numC != null ? numC.intValue() : 0) - 1;
            }
            return (SubscriptionInfo) j.a((List) arrayList2, iIntValue);
        }

        public final SubscriptionInfo a(Context context, String str) {
            k.b(context, "context");
            if (str != null) {
                return a.f7559a.d(context, str);
            }
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:24:0x005e  */
        @TargetApi(22)
        public final String[] a(Context context) {
            String[] strArr;
            List listA;
            k.b(context, "context");
            if (f.f9323b.c()) {
                return new String[0];
            }
            List<SubscriptionInfo> listC = c(context);
            if (listC == null) {
                strArr = new String[0];
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = listC.iterator();
                while (it.hasNext()) {
                    CharSequence displayName = ((SubscriptionInfo) it.next()).getDisplayName();
                    String string = displayName != null ? displayName.toString() : null;
                    if (string != null) {
                        arrayList.add(string);
                    }
                }
                Object[] array = arrayList.toArray(new String[0]);
                if (array == null) {
                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                strArr = (String[]) array;
                if (strArr == null) {
                    strArr = new String[0];
                }
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : strArr) {
                Object arrayList2 = linkedHashMap.get(str);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    linkedHashMap.put(str, arrayList2);
                }
                ((List) arrayList2).add(str);
            }
            List<c.k> listD = z.d(linkedHashMap);
            ArrayList arrayList3 = new ArrayList(j.a((Iterable) listD, 10));
            for (c.k kVar : listD) {
                List list = (List) kVar.b();
                if (list.size() == 1) {
                    listA = j.a(kVar.a());
                } else {
                    List list2 = list;
                    List arrayList4 = new ArrayList(j.a((Iterable) list2, 10));
                    int i = 0;
                    for (Object obj : list2) {
                        int i2 = i + 1;
                        if (i < 0) {
                            j.b();
                        }
                        arrayList4.add(((String) obj) + "=:=" + i2);
                        i = i2;
                    }
                    listA = arrayList4;
                }
                arrayList3.add(listA);
            }
            Object[] array2 = j.a((Iterable) arrayList3).toArray(new String[0]);
            if (array2 != null) {
                return (String[]) array2;
            }
            throw new q("null cannot be cast to non-null type kotlin.Array<T>");
        }

        private final SmsManager a() {
            return SmsManager.getDefault();
        }

        @TargetApi(22)
        public final SmsManager b(Context context, String str) {
            k.b(context, "context");
            if (f.f9323b.c()) {
                return a();
            }
            if (str != null) {
                if (!(str.length() == 0)) {
                    C0177a c0177a = this;
                    SubscriptionInfo subscriptionInfoD = c0177a.d(context, str);
                    if (subscriptionInfoD == null) {
                        return c0177a.a();
                    }
                    SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(subscriptionInfoD.getSubscriptionId());
                    return smsManagerForSubscriptionId != null ? smsManagerForSubscriptionId : c0177a.a();
                }
            }
            return a();
        }

        @TargetApi(23)
        public final PhoneAccountHandle c(Context context, String str) {
            C0177a c0177a;
            SubscriptionInfo subscriptionInfoD;
            k.b(context, "context");
            if (f.f9323b.d()) {
                return null;
            }
            String str2 = str;
            if ((str2 == null || str2.length() == 0) || (subscriptionInfoD = (c0177a = this).d(context, str)) == null) {
                return null;
            }
            return c0177a.a(subscriptionInfoD, context);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.k.a$a$a, reason: collision with other inner class name */
        @TargetApi(23)
        public static final class C0178a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final Context f7560a;

            public C0178a(Context context) {
                k.b(context, "context");
                this.f7560a = context;
            }

            private final List<SubscriptionInfo> b() {
                if (f.f9323b.d()) {
                    return new ArrayList();
                }
                List listC = a.f7559a.c(this.f7560a);
                if (listC == null) {
                    return new ArrayList();
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : listC) {
                    CharSequence displayName = ((SubscriptionInfo) obj).getDisplayName();
                    if ((displayName != null ? displayName.toString() : null) != null) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            }

            private final List<C0179a> c() {
                String string;
                List<SubscriptionInfo> listB = b();
                ArrayList arrayList = new ArrayList(j.a((Iterable) listB, 10));
                for (SubscriptionInfo subscriptionInfo : listB) {
                    int subscriptionId = subscriptionInfo.getSubscriptionId();
                    CharSequence displayName = subscriptionInfo.getDisplayName();
                    if (displayName == null || (string = displayName.toString()) == null) {
                        throw new RuntimeException("Name can't be null here! Contact dev");
                    }
                    arrayList.add(new C0179a(subscriptionId, string));
                }
                return arrayList;
            }

            public final List<b> a() {
                ArrayList arrayList;
                List<C0179a> listC = c();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj : listC) {
                    String strB = ((C0179a) obj).b();
                    Object arrayList2 = linkedHashMap.get(strB);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        linkedHashMap.put(strB, arrayList2);
                    }
                    ((List) arrayList2).add(obj);
                }
                List listD = z.d(linkedHashMap);
                ArrayList arrayList3 = new ArrayList(j.a((Iterable) listD, 10));
                Iterator it = listD.iterator();
                while (it.hasNext()) {
                    List list = (List) ((c.k) it.next()).b();
                    if (list.size() == 1) {
                        List<C0179a> list2 = list;
                        arrayList = new ArrayList(j.a((Iterable) list2, 10));
                        for (C0179a c0179a : list2) {
                            arrayList.add(new b(c0179a.a(), c0179a.b(), null));
                        }
                    } else {
                        List list3 = list;
                        arrayList = new ArrayList(j.a((Iterable) list3, 10));
                        int i = 0;
                        for (Object obj2 : list3) {
                            int i2 = i + 1;
                            if (i < 0) {
                                j.b();
                            }
                            C0179a c0179a2 = (C0179a) obj2;
                            arrayList.add(new b(c0179a2.a(), c0179a2.b(), Integer.valueOf(i)));
                            i = i2;
                        }
                    }
                    arrayList3.add(arrayList);
                }
                return j.a((Iterable) arrayList3);
            }

            public final b a(int i) {
                Object next;
                Iterator<T> it = a().iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if (((b) next).a() == i) {
                        return (b) next;
                    }
                }
                next = null;
                return (b) next;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.k.a$a$a$a, reason: collision with other inner class name */
            public static class C0179a {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                private final int f7561a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                private final String f7562b;

                public C0179a(int i, String str) {
                    k.b(str, "nameNoIndex");
                    this.f7561a = i;
                    this.f7562b = str;
                }

                public final int a() {
                    return this.f7561a;
                }

                public final String b() {
                    return this.f7562b;
                }
            }

            public final String b(int i) {
                b bVarA = a(i);
                if (bVarA != null) {
                    return bVarA.c();
                }
                return null;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.k.a$a$a$b */
            public static final class b extends C0179a {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                private final Integer f7563a;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(int i, String str, Integer num) {
                    super(i, str);
                    k.b(str, "nameNoIndex");
                    this.f7563a = num;
                }

                public final String c() {
                    if (f.f9323b.d()) {
                        return null;
                    }
                    if (this.f7563a == null) {
                        return b();
                    }
                    return b() + "=:=" + (this.f7563a.intValue() + 1);
                }
            }
        }
    }
}

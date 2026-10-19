package com.joaomgcd.taskerm.keyboard;

import android.app.Activity;
import android.content.Context;
import c.a.z;
import com.joaomgcd.taskerm.dialog.x;
import com.joaomgcd.taskerm.dialog.y;
import com.joaomgcd.taskerm.function.FunctionArgs;
import com.joaomgcd.taskerm.function.FunctionBaseKt;
import com.joaomgcd.taskerm.util.ad;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import com.joaomgcd.taskerm.util.dm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class v extends ArrayList<g> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f7602a = new a(null);

    public static final class a {

        static final class b extends c.f.b.l implements c.f.a.a<cq<? extends Object, ? extends ad>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f7605a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ String f7606b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Integer f7607c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ boolean f7608d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(Context context, String str, Integer num, boolean z) {
                super(0);
                this.f7605a = context;
                this.f7606b = str;
                this.f7607c = num;
                this.f7608d = z;
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.v$a$b$a, reason: collision with other inner class name */
            static final class C0185a extends c.f.b.l implements c.f.a.b<d, Boolean> {

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                final /* synthetic */ v f7610b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                final /* synthetic */ int f7611c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                final /* synthetic */ c.f.b.w.d f7612d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C0185a(v vVar, int i, c.f.b.w.d dVar) {
                    super(1);
                    this.f7610b = vVar;
                    this.f7611c = i;
                    this.f7612d = dVar;
                }

                @Override // c.f.a.b
                public /* synthetic */ Boolean invoke(d dVar) {
                    return Boolean.valueOf(a(dVar));
                }

                /* JADX WARN: Type inference failed for: r2v3, types: [T, com.joaomgcd.taskerm.keyboard.OutputKeyboardCommand] */
                public final boolean a(d dVar) throws InterruptedException {
                    c.f.b.k.b(dVar, "receiver$0");
                    v vVar = this.f7610b;
                    ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) vVar, 10));
                    for (g gVar : vVar) {
                        if (this.f7611c > 0) {
                            Thread.sleep(this.f7611c);
                        }
                        arrayList.add(Boolean.valueOf(gVar.a(dVar)));
                    }
                    ArrayList arrayList2 = arrayList;
                    if (b.this.f7608d) {
                        dm.a(500L);
                    }
                    this.f7612d.f2028a = new OutputKeyboardCommand(dVar.b(), dVar.c(), dVar.d(), dVar.e());
                    ArrayList arrayList3 = arrayList2;
                    if ((arrayList3 instanceof Collection) && arrayList3.isEmpty()) {
                        return true;
                    }
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        ((Boolean) it.next()).booleanValue();
                    }
                    return true;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r4v1, types: [T, com.joaomgcd.taskerm.keyboard.OutputKeyboardCommand] */
            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final cq<? extends Object, ? extends ad> invoke() throws IllegalAccessException, InstantiationException {
                v vVarA = v.f7602a.a(this.f7605a, this.f7606b);
                if (vVarA.size() == 0) {
                    u uVar = new u();
                    uVar.a("0");
                    vVarA.add(uVar);
                }
                Integer num = this.f7607c;
                if (num == null) {
                    return cn.a("Time between inputs not provided");
                }
                int iIntValue = num.intValue();
                c.f.b.w.d dVar = new c.f.b.w.d();
                dVar.f2028a = (OutputKeyboardCommand) 0;
                Boolean boolB = d.f7574a.a(this.f7605a, 5000L, this.f7608d, new C0185a(vVarA, iIntValue, dVar)).b();
                c.f.b.k.a((Object) boolB, "result");
                if (boolB.booleanValue()) {
                    return new cq<>(true, (OutputKeyboardCommand) dVar.f2028a, null);
                }
                return cn.a("Could't send inputs");
            }
        }

        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final c a(Context context) {
            c.f.b.k.b(context, "context");
            com.joaomgcd.taskerm.keyboard.b bVar = new com.joaomgcd.taskerm.keyboard.b("selectPositions", q.class, "Select Positions", null, 8, null);
            bVar.a(C0184a.f7603a);
            com.joaomgcd.taskerm.keyboard.b bVar2 = new com.joaomgcd.taskerm.keyboard.b("selectAll", p.class, "Select All Text", null, 8, null);
            bVar2.a();
            com.joaomgcd.taskerm.keyboard.b bVar3 = new com.joaomgcd.taskerm.keyboard.b("deleteAll", l.class, "Delete All Text", null, 8, null);
            bVar3.a();
            return new c(context, new com.joaomgcd.taskerm.keyboard.b("write", t.class, aj.a(R.string.dt_enter_text, context, new Object[0]), null, 8, null), new com.joaomgcd.taskerm.keyboard.b("wait", u.class, aj.a(R.string.dt_wait_some_time_ms, context, new Object[0]), null, 8, null), new com.joaomgcd.taskerm.keyboard.b("replace", o.class, "Replace Text", null, 8, null), new com.joaomgcd.taskerm.keyboard.b("select", r.class, "Select Text", null, 8, null), new com.joaomgcd.taskerm.keyboard.b("selectRegex", s.class, "Select Text With Regex", null, 8, null), bVar, new com.joaomgcd.taskerm.keyboard.b("cursor", j.class, "Set Cursor Position", null, 8, null), bVar2, new com.joaomgcd.taskerm.keyboard.b("delete", k.class, "Delete Text", null, 8, null), bVar3);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.v$a$a, reason: collision with other inner class name */
        static final class C0184a extends c.f.b.l implements c.f.a.b<Activity, b.a.l<String>> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0184a f7603a = new C0184a();

            C0184a() {
                super(1);
            }

            /* JADX INFO: renamed from: com.joaomgcd.taskerm.keyboard.v$a$a$1, reason: invalid class name */
            static final class AnonymousClass1 extends c.f.b.l implements c.f.a.a<String> {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                final /* synthetic */ Activity f7604a;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Activity activity) {
                    super(0);
                    this.f7604a = activity;
                }

                @Override // c.f.a.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public final String invoke() {
                    x xVar = (x) y.a(this.f7604a, "Start Position", null, 0, true, null, null, 0, null, null, 1000, null).b();
                    if (!xVar.d()) {
                        return "";
                    }
                    x xVar2 = (x) y.a(this.f7604a, "End Position", null, 0, true, null, null, 0, null, null, 1000, null).b();
                    if (!xVar2.d()) {
                        return "";
                    }
                    return xVar.j() + ',' + xVar2.j();
                }
            }

            @Override // c.f.a.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b.a.l<String> invoke(Activity activity) {
                c.f.b.k.b(activity, "it");
                return com.joaomgcd.taskerm.rx.i.b(new AnonymousClass1(activity));
            }
        }

        public final b.a.l<cl> a(Context context, String str, Integer num, boolean z) {
            c.f.b.k.b(context, "context");
            return com.joaomgcd.taskerm.rx.i.b(new b(context, str, num, z));
        }

        public final v a(Context context, String str) throws IllegalAccessException, InstantiationException {
            Class<? extends m> clsC;
            c.f.b.k.b(context, "context");
            v vVar = new v();
            if (str != null) {
                List<FunctionArgs> functionArgs = FunctionBaseKt.getFunctionArgs(str);
                w.a.C0186a c0186aC = w.f7613a.c();
                ArrayList arrayList = new ArrayList(c0186aC.size());
                for (Map.Entry<Integer, String> entry : c0186aC.entrySet()) {
                    arrayList.add(new c.k(entry.getValue(), entry.getKey()));
                }
                Object[] array = arrayList.toArray(new c.k[0]);
                if (array != null) {
                    c.k[] kVarArr = (c.k[]) array;
                    HashMap<String, Integer> mapB = z.b((c.k[]) Arrays.copyOf(kVarArr, kVarArr.length));
                    Map<Integer, String> mapB2 = w.f7613a.b();
                    ArrayList arrayList2 = new ArrayList(mapB2.size());
                    for (Map.Entry<Integer, String> entry2 : mapB2.entrySet()) {
                        arrayList2.add(new c.k(entry2.getValue(), entry2.getKey()));
                    }
                    Object[] array2 = arrayList2.toArray(new c.k[0]);
                    if (array2 != null) {
                        c.k[] kVarArr2 = (c.k[]) array2;
                        HashMap mapB3 = z.b((c.k[]) Arrays.copyOf(kVarArr2, kVarArr2.length));
                        Map<Integer, String> mapA = w.f7613a.a();
                        ArrayList arrayList3 = new ArrayList(mapA.size());
                        for (Map.Entry<Integer, String> entry3 : mapA.entrySet()) {
                            arrayList3.add(new c.k(entry3.getValue(), entry3.getKey()));
                        }
                        Object[] array3 = arrayList3.toArray(new c.k[0]);
                        if (array3 != null) {
                            c.k[] kVarArr3 = (c.k[]) array3;
                            HashMap mapB4 = z.b((c.k[]) Arrays.copyOf(kVarArr3, kVarArr3.length));
                            for (FunctionArgs functionArgs2 : functionArgs) {
                                String name = functionArgs2.getName();
                                if (functionArgs2.getHasParenthesis()) {
                                    String functionText = functionArgs2.getFunctionText();
                                    if (functionText == null) {
                                        functionText = "";
                                    }
                                    com.joaomgcd.taskerm.keyboard.b bVarB = v.f7602a.a(context).b(name);
                                    if (bVarB != null && (clsC = bVarB.c()) != null) {
                                        m mVarNewInstance = clsC.newInstance();
                                        mVarNewInstance.a(functionText);
                                        vVar.add(mVarNewInstance);
                                    }
                                } else {
                                    ArrayList<n> arrayListA = n.f7599a.a(name, mapB);
                                    if (arrayListA != null) {
                                        vVar.addAll(arrayListA);
                                    } else {
                                        Integer num = (Integer) mapB3.get(name);
                                        if (num != null) {
                                            vVar.add(new h(num.intValue()));
                                        } else {
                                            Integer num2 = (Integer) mapB4.get(name);
                                            if (num2 != null) {
                                                vVar.add(new i(num2.intValue()));
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    } else {
                        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                } else {
                    throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            return vVar;
        }
    }

    public int a() {
        return super.size();
    }

    public boolean a(g gVar) {
        return super.contains(gVar);
    }

    public int b(g gVar) {
        return super.indexOf(gVar);
    }

    public int c(g gVar) {
        return super.lastIndexOf(gVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof g) {
            return a((g) obj);
        }
        return false;
    }

    public boolean d(g gVar) {
        return super.remove(gVar);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof g) {
            return b((g) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj instanceof g) {
            return c((g) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        if (obj instanceof g) {
            return d((g) obj);
        }
        return false;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return a();
    }
}

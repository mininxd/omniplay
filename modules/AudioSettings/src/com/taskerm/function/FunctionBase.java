package com.joaomgcd.taskerm.function;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import c.a.j;
import c.b.a;
import c.f.a.b;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.App;
import com.joaomgcd.taskerm.util.AppBasic;
import com.joaomgcd.taskerm.util.Cdo;
import com.joaomgcd.taskerm.util.ac;
import com.joaomgcd.taskerm.util.aj;
import com.joaomgcd.taskerm.util.ak;
import com.joaomgcd.taskerm.util.cb;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.cq;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public abstract class FunctionBase<TInput, TOutput> {
    public static final Companion Companion = new Companion(null);

    public static final <T extends FunctionBase<?, ?>> boolean callsFunction(String str, Class<T> cls) {
        return Companion.callsFunction(str, cls);
    }

    public static final FunctionBase<?, ?> get(FunctionArgs functionArgs) {
        return Companion.get(functionArgs);
    }

    public static final List<FunctionBase<?, ?>> getAll() {
        return Companion.getAll();
    }

    public abstract TOutput doIt(Context context, TInput tinput);

    protected abstract Class<TInput> getInputClass();

    public int getMinApi() {
        return 21;
    }

    protected abstract int getNameResId();

    public abstract Class<TOutput> getOutputClass();

    protected String[] getPermissions(Context context, TInput tinput) {
        k.b(context, "context");
        return null;
    }

    public Integer getTipResId() {
        return null;
    }

    public boolean getTrimParameters() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class InputValue {
        private final int index;
        private final Class<?> type;
        private final Object value;

        public InputValue(Object obj, int i, Class<?> cls) {
            k.b(cls, "type");
            this.value = obj;
            this.index = i;
            this.type = cls;
        }

        public final int getIndex() {
            return this.index;
        }

        public final Class<?> getType() {
            return this.type;
        }

        public final Object getValue() {
            return this.value;
        }
    }

    public final List<cb<FunctionInput>> getPropertiesAndAnnotations() {
        return Cdo.a((Class<?>) getInputClass(), FunctionInput.class);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0078  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Long] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.joaomgcd.taskerm.util.AppBasic] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final TInput getInput(String[] strArr, Context context) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        List<cb<FunctionInput>> propertiesAndAnnotations = getPropertiesAndAnnotations();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = propertiesAndAnnotations.iterator();
        while (it.hasNext()) {
            cb cbVar = (cb) it.next();
            Class<?> clsA = cbVar.a();
            int iIndex = ((FunctionInput) cbVar.d()).index();
            Object objValueOf = strArr[iIndex];
            if (clsA.isEnum()) {
                objValueOf = ak.a((String) objValueOf, clsA, (b) null, 2, (Object) null);
            } else if (k.a(clsA, String.class)) {
                continue;
            } else if (k.a(clsA, Boolean.class) || k.a(clsA, Boolean.TYPE)) {
                objValueOf = Boolean.valueOf(Boolean.parseBoolean(objValueOf));
            } else if (k.a(clsA, File.class)) {
                if (context instanceof ExecuteService) {
                    objValueOf = ((ExecuteService) context).a((String) objValueOf, "Tasker Function", false, true, true);
                } else {
                    objValueOf = 0;
                }
            } else if (k.a(clsA, Integer.TYPE) || k.a(clsA, Integer.class)) {
                objValueOf = n.c((String) objValueOf);
                if (objValueOf == 0) {
                    throw new ExceptionFunctions("Parameter " + (iIndex + 1) + " should be a number but cannot be converted to it");
                }
            } else if (k.a(clsA, Long.class) || k.a(clsA, Long.TYPE)) {
                objValueOf = n.d((String) objValueOf);
                if (objValueOf == 0) {
                    throw new ExceptionFunctions("Parameter " + (iIndex + 1) + " should be a number but cannot be converted to it");
                }
            } else if (k.a(clsA, AppBasic.class)) {
                try {
                    objValueOf = App.Companion.a(context, objValueOf);
                } catch (Exception e2) {
                    throw new ExceptionFunctions("Parameter " + (iIndex + 1) + " should be an app but cannot be converted to it: " + e2.getMessage());
                }
            } else {
                objValueOf = 0;
            }
            arrayList.add(new InputValue(objValueOf, iIndex, clsA));
        }
        List listA = j.a((Iterable) arrayList, new Comparator<T>() { // from class: com.joaomgcd.taskerm.function.FunctionBase$getInput$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return a.a(Integer.valueOf(((FunctionBase.InputValue) t).getIndex()), Integer.valueOf(((FunctionBase.InputValue) t2).getIndex()));
            }
        });
        ArrayList arrayList2 = new ArrayList(j.a((Iterable) listA, 10));
        Iterator it2 = listA.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((InputValue) it2.next()).getType());
        }
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(j.a((Iterable) listA, 10));
        Iterator it3 = listA.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((InputValue) it3.next()).getValue());
        }
        ArrayList arrayList5 = arrayList4;
        Class<TInput> inputClass = getInputClass();
        Object[] array = arrayList3.toArray(new Class[0]);
        if (array != null) {
            Class[] clsArr = (Class[]) array;
            Constructor<TInput> constructor = inputClass.getConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
            Object[] array2 = arrayList5.toArray(new Object[0]);
            if (array2 != null) {
                TInput tinputNewInstance = constructor.newInstance(Arrays.copyOf(array2, array2.length));
                if (tinputNewInstance instanceof InputForFunction) {
                    ((InputForFunction) tinputNewInstance).setRawInput(strArr);
                }
                return tinputNewInstance;
            }
            throw new q("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final cl doIt(ExecuteService executeService, String[] strArr) {
        cq cqVarA;
        k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        k.b(strArr, "params");
        try {
            cqVarA = cn.a(doIt(executeService, getInput(strArr, executeService)));
        } catch (Exception e2) {
            cqVarA = cn.a(new ac(e2));
        }
        return cqVarA;
    }

    public final String[] getPermissions(Context context, String[] strArr) {
        k.b(context, "context");
        k.b(strArr, "params");
        try {
            return getPermissions(context, getInput(strArr, context));
        } catch (Exception unused) {
            return null;
        }
    }

    public final String getName(Context context) {
        k.b(context, "context");
        return aj.a(getNameResId(), context, new Object[0]);
    }

    public static final class Companion {
        public static /* synthetic */ void all$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        public final FunctionBase<?, ?> get(FunctionArgs functionArgs) {
            k.b(functionArgs, "args");
            try {
                try {
                    FunctionBase<?, ?> functionBase = (FunctionBase) Class.forName("com.joaomgcd.taskerm.function." + ak.f(functionArgs.getName())).newInstance();
                    if (functionBase != null) {
                        return functionBase;
                    }
                    throw new RuntimeException(functionArgs.getName() + ": null instance");
                } catch (Throwable unused) {
                    throw new RuntimeException(functionArgs.getName() + ": couldn't create instance");
                }
            } catch (Throwable unused2) {
                throw new RuntimeException(functionArgs.getName() + " is not a valid function name");
            }
        }

        public final List<FunctionBase<?, ?>> getAll() {
            return j.b(new LocationsDistance(), new AddThrottleNotifications(), new RemoveThrottleNotifications(), new GetThrottledNotifications(), new GetCurrentAppAndActivity(), new ShortcutDialog(), new UninstallApp(), new CheckRoot(), new CheckADBWifi(), new GetSims(), new StopVibrate(), new WireGuardSetTunnel(), new TermuxCommand(), new WaitForFileEvent(), new FilePathToContentUri(), new LaunchAssistant(), new GenerateUUID(), new CheckTorch(), new GetMusicActive(), new ListStorageVolumes(), new GetMaxAudioVolumes());
        }

        public final <T extends FunctionBase<?, ?>> boolean callsFunction(String str, Class<T> cls) {
            k.b(cls, "functionClass");
            List<FunctionArgs> functionArgs = FunctionBaseKt.getFunctionArgs(str);
            if ((functionArgs instanceof Collection) && functionArgs.isEmpty()) {
                return false;
            }
            Iterator<T> it = functionArgs.iterator();
            while (it.hasNext()) {
                FunctionBase functionBase = (FunctionBase) aj.a((b) null, new FunctionBase$Companion$callsFunction$1$functionBase$1((FunctionArgs) it.next()), 1, (Object) null);
                if (functionBase != null ? k.a(functionBase.getClass(), cls) : false) {
                    return true;
                }
            }
            return false;
        }
    }
}

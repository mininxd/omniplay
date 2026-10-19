package com.joaomgcd.taskerm.function;

import android.content.Context;
import c.f.b.g;
import c.f.b.k;
import c.l.n;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class SetNotificationPolicy extends FunctionBase<InputSetNotificationPolicy, OutputSetNotificationPolicy> {
    public static final Companion Companion = new Companion(null);

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected int getNameResId() {
        return R.string.check_adb_wifi;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    protected Class<InputSetNotificationPolicy> getInputClass() {
        return InputSetNotificationPolicy.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<OutputSetNotificationPolicy> getOutputClass() {
        return OutputSetNotificationPolicy.class;
    }

    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public OutputSetNotificationPolicy doIt(Context context, InputSetNotificationPolicy inputSetNotificationPolicy) {
        k.b(context, "context");
        k.b(inputSetNotificationPolicy, "input");
        return new OutputSetNotificationPolicy(inputSetNotificationPolicy.set(context));
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(g gVar) {
            this();
        }

        public final String getFlagsString(DoNotDisturbSenders doNotDisturbSenders, DoNotDisturbSenders doNotDisturbSenders2, List<? extends DoNotDisturbCategory> list, List<? extends DoNotDisturbSuppressedEffect> list2, boolean z) {
            k.b(doNotDisturbSenders, "selectedCallSenders");
            k.b(doNotDisturbSenders2, "selectedSmsSenders");
            k.b(list, "selectedCategories");
            k.b(list2, "selectedSuppressedEffects");
            int flags = SetNotificationPolicyKt.getFlags(list);
            if (doNotDisturbSenders != DoNotDisturbSenders.None) {
                flags |= DoNotDisturbCategory.Calls.getId();
            }
            if (doNotDisturbSenders2 != DoNotDisturbSenders.None) {
                flags |= DoNotDisturbCategory.Messages.getId();
            }
            if (z) {
                flags |= DoNotDisturbCategory.RepeatCallers.getId();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(doNotDisturbSenders.getId());
            sb.append(',');
            sb.append(doNotDisturbSenders2.getId());
            sb.append(',');
            sb.append(flags);
            sb.append(',');
            sb.append(SetNotificationPolicyKt.getFlags(list2));
            return sb.toString();
        }

        private final int getFlags(String str) {
            Integer numC = n.c(str);
            if (numC != null) {
                return numC.intValue();
            }
            throw new RuntimeException("Invalid dnd flags: " + str);
        }

        public final InputSetNotificationPolicy getInputFromFlags(String str) {
            if (str == null) {
                throw new RuntimeException("No flags to get dnd policy from");
            }
            try {
                List listB = n.b((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
                Companion companion = this;
                return new InputSetNotificationPolicy(companion.getFlags((String) listB.get(2)), companion.getFlags((String) listB.get(0)), companion.getFlags((String) listB.get(1)), companion.getFlags((String) listB.get(3)));
            } catch (Throwable th) {
                throw new RuntimeException("Couldn't get dnd policy from input flags: " + th);
            }
        }
    }
}

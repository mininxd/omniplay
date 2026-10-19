package com.joaomgcd.taskerm.command;

import c.a.j;
import c.e;
import c.f;
import c.f.b.l;
import c.f.b.v;
import c.f.b.x;
import c.j.g;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputObject;
import com.joaomgcd.taskerm.inputoutput.TaskerOutputVariable;
import com.joaomgcd.taskerm.util.aj;
import java.util.List;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
@TaskerOutputObject(varPrefix = "command")
public final class TaskerCommand {
    static final /* synthetic */ g[] $$delegatedProperties = {x.a(new v(x.a(TaskerCommand.class), "prefix", "getPrefix()Ljava/lang/String;")), x.a(new v(x.a(TaskerCommand.class), "parameters", "getParameters()[Ljava/lang/String;")), x.a(new v(x.a(TaskerCommand.class), "parameter", "getParameter()Ljava/lang/String;")), x.a(new v(x.a(TaskerCommand.class), "textSplit", "getTextSplit()Ljava/util/List;"))};
    private final String text;
    private final e prefix$delegate = f.a(new c());
    private final e parameters$delegate = f.a(new b());
    private final e parameter$delegate = f.a(new a());
    private final e textSplit$delegate = f.a(new d());

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> getTextSplit() {
        e eVar = this.textSplit$delegate;
        g gVar = $$delegatedProperties[3];
        return (List) eVar.b();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.command_parameter_description, labelResId = R.string.command_parameter, name = "parameter")
    public final String getParameter() {
        e eVar = this.parameter$delegate;
        g gVar = $$delegatedProperties[2];
        return (String) eVar.b();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.command_parameters_description, labelResId = R.string.command_parameters, name = "parameters")
    public final String[] getParameters() {
        e eVar = this.parameters$delegate;
        g gVar = $$delegatedProperties[1];
        return (String[]) eVar.b();
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.command_prefix_description, labelResId = R.string.command_prefix, name = "prefix")
    public final String getPrefix() {
        e eVar = this.prefix$delegate;
        g gVar = $$delegatedProperties[0];
        return (String) eVar.b();
    }

    public TaskerCommand(String str) {
        this.text = str;
    }

    @TaskerOutputVariable(htmlLabelResId = R.string.command_text_description, labelResId = R.string.command_text, name = "text")
    public final String getText() {
        return this.text;
    }

    public final boolean isValid() {
        String str = this.text;
        return !(str == null || str.length() == 0);
    }

    static final class c extends l implements c.f.a.a<String> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            List textSplit = TaskerCommand.this.getTextSplit();
            if (textSplit != null) {
                return (String) j.a(textSplit, 0);
            }
            return null;
        }
    }

    static final class b extends l implements c.f.a.a<String[]> {
        b() {
            super(0);
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.command.TaskerCommand$b$1, reason: invalid class name */
        static final class AnonymousClass1 extends l implements c.f.a.a<List<? extends String>> {
            AnonymousClass1() {
                super(0);
            }

            @Override // c.f.a.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final List<String> invoke() {
                List textSplit = TaskerCommand.this.getTextSplit();
                if (textSplit != null) {
                    return j.b((Iterable) textSplit, 1);
                }
                return null;
            }
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String[] invoke() {
            List list = (List) aj.a((c.f.a.b) null, new AnonymousClass1(), 1, (Object) null);
            if (list != null) {
                Object[] array = list.toArray(new String[0]);
                if (array == null) {
                    throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                }
                String[] strArr = (String[]) array;
                if (strArr != null) {
                    return strArr;
                }
            }
            return new String[0];
        }
    }

    static final class a extends l implements c.f.a.a<String> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return (String) c.a.d.a(TaskerCommand.this.getParameters(), 0);
        }
    }

    static final class d extends l implements c.f.a.a<List<? extends String>> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<String> invoke() {
            String text = TaskerCommand.this.getText();
            if (text != null) {
                return n.b((CharSequence) text, new String[]{"=:="}, false, 0, 6, (Object) null);
            }
            return null;
        }
    }
}

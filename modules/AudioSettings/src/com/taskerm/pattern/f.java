package com.joaomgcd.taskerm.pattern;

import c.l.k;
import c.l.n;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    private static final String a(String str, String str2, String str3, boolean z) {
        String str4;
        String str5 = str2 + "([a-zA-Z_]+)";
        String str6 = z ? "" : "?";
        if (n.a(str2, "\\", false, 2, (Object) null)) {
            str4 = str2;
        } else {
            str4 = "\\" + str2;
        }
        String strA = n.a(str, str4, "TOhmmm@£@][{£@][Thisisprefixbonazna!!!£@[]}{@[", false, 4, (Object) null);
        if (!z) {
            strA = new k(str5 + "$").a(strA, "(?<$1>" + str3 + '+' + str6 + ")THISis876389712917987981gtheEND!!!!!@@£@£@§@£€]@[}]£@");
        }
        String strA2 = new k(str5).a(strA, "(?<$1>" + str3 + '+' + str6 + ')');
        return n.a(n.a(new k(str2).a(strA2, str3 + '+' + str6), "THISis876389712917987981gtheEND!!!!!@@£@£@§@£€]@[}]£@", "$", false, 4, (Object) null), "TOhmmm@£@][{£@][Thisisprefixbonazna!!!£@[]}{@[", str2, false, 4, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String b(String str) {
        return "(?i)" + a(a(n.a(n.a(str, "(", "\\(", false, 4, (Object) null), ")", "\\)", false, 4, (Object) null), "\\$", "(.|\\\\n)", false), "#", "\\\\d", true);
    }
}

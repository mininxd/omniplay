package com.joaomgcd.taskerm.function;

import android.content.Context;
import android.content.SharedPreferences;
import c.a.j;
import c.f.b.k;
import c.i;
import c.l.n;
import c.q;
import com.joaomgcd.taskerm.util.ak;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FunctionBaseKt {
    private static final String COMMA_REPLACER = "SUPERDUPERCOMMA####44###$$$";
    private static final char FUNCTION_CLOSE = ')';
    private static final String FUNCTION_COMPONENT_SEPARATOR = ".";
    private static final char FUNCTION_OPEN = '(';
    private static final char FUNCTION_SEPARATOR = ',';
    private static final String PARENTHESIS_REPLACER_CLOSE = "SUPERDUPERPARENTHESISCLOSE####44###$$$";
    private static final String PARENTHESIS_REPLACER_OPEN = "SUPERDUPERPARENTHESISOPEN####44###$$$";
    private static final String SLASH_REPLACER = "SUPERDUPERSLASH####44###$$$";

    public static final SharedPreferences getPreferencesFunctions(Context context) {
        k.b(context, "receiver$0");
        return context.getSharedPreferences("taskerfunctionsprefs", 0);
    }

    private static final c.l.k getFunctionArgsSplitPattern() {
        return new c.l.k("(([^,]+?)\\(((?:.|\n)*?)(\\),|\\)$))|([^,]+)");
    }

    private static final boolean getContainsParenthesis(String str) {
        if (str == null) {
            return false;
        }
        String str2 = str;
        return n.b((CharSequence) str2, (CharSequence) "(", false, 2, (Object) null) && n.b((CharSequence) str2, (CharSequence) ")", false, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c.k<Character, String>[] getSpecials() {
        return new c.k[]{new c.k<>('\\', SLASH_REPLACER), new c.k<>(Character.valueOf(FUNCTION_SEPARATOR), COMMA_REPLACER), new c.k<>(Character.valueOf(FUNCTION_OPEN), PARENTHESIS_REPLACER_OPEN), new c.k<>(Character.valueOf(FUNCTION_CLOSE), PARENTHESIS_REPLACER_CLOSE)};
    }

    private static final List<String> getSplitIntoFunctionCalls(String str) {
        if (str == null) {
            return j.a();
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        ReplaceSpecialMode replaceSpecialMode = ReplaceSpecialMode.Normal;
        c.k<Character, String>[] specials = getSpecials();
        String strReplaceSpecialStuff = replaceSpecialStuff(str, replaceSpecialMode, (c.k[]) Arrays.copyOf(specials, specials.length));
        FunctionBaseKt$splitIntoFunctionCalls$1 functionBaseKt$splitIntoFunctionCalls$1 = new FunctionBaseKt$splitIntoFunctionCalls$1(arrayList, sb);
        String str2 = strReplaceSpecialStuff;
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < str2.length(); i2++) {
            char cCharAt = str2.charAt(i2);
            if (z) {
                if (cCharAt == ',') {
                    z = false;
                }
            } else if (cCharAt == ',' && i == 0) {
                functionBaseKt$splitIntoFunctionCalls$1.invoke2();
            } else {
                sb.append(cCharAt);
                boolean z2 = i > 0;
                switch (cCharAt) {
                    case '(':
                        i++;
                        break;
                    case ')':
                        i--;
                        break;
                }
                if (z2 && i == 0) {
                    functionBaseKt$splitIntoFunctionCalls$1.invoke2();
                    z = true;
                }
            }
        }
        if (!(sb.length() == 0)) {
            functionBaseKt$splitIntoFunctionCalls$1.invoke2();
        }
        return arrayList;
    }

    private static final boolean isStartOfArray(String str) {
        return n.a(str, "[", false, 2, (Object) null) && !n.a(str, "\\[", false, 2, (Object) null);
    }

    private static final boolean isEndOfArray(String str) {
        return n.b(str, "]", false, 2, (Object) null) && !n.b(str, "\\]", false, 2, (Object) null);
    }

    public static final List<FunctionArgs> getFunctionArgs(String str) {
        ArrayList arrayListA;
        if (str == null) {
            return j.a();
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : getSplitIntoFunctionCalls(str)) {
            if (getContainsParenthesis(str2)) {
                String strA = ak.A(n.a(str2, "(", (String) null, 2, (Object) null));
                if (strA == null) {
                    continue;
                } else {
                    String strA2 = ak.A(n.c(n.b(str2, "(", (String) null, 2, (Object) null), ")", null, 2, null));
                    if (strA2 == null) {
                        arrayListA = j.a();
                    } else {
                        List<String> listB = n.b((CharSequence) strA2, new String[]{","}, false, 0, 6, (Object) null);
                        ArrayList arrayList2 = new ArrayList(j.a((Iterable) listB, 10));
                        for (String str3 : listB) {
                            ReplaceSpecialMode replaceSpecialMode = ReplaceSpecialMode.Reverse;
                            c.k<Character, String>[] specials = getSpecials();
                            arrayList2.add(replaceSpecialStuff(str3, replaceSpecialMode, (c.k[]) Arrays.copyOf(specials, specials.length)));
                        }
                        arrayListA = arrayList2;
                    }
                    String str4 = (String) null;
                    String str5 = strA;
                    if (n.b((CharSequence) str5, (CharSequence) FUNCTION_COMPONENT_SEPARATOR, false, 2, (Object) null)) {
                        List listB2 = n.b((CharSequence) str5, new String[]{FUNCTION_COMPONENT_SEPARATOR}, false, 0, 6, (Object) null);
                        str4 = (String) listB2.get(0);
                        strA = (String) listB2.get(1);
                    }
                    String str6 = strA;
                    String str7 = str4;
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    Iterator it = arrayListA.iterator();
                    while (true) {
                        boolean z = false;
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String str8 = (String) it.next();
                            if (isStartOfArray(str8)) {
                                if (isEndOfArray(str8)) {
                                    arrayList3.add(ak.a(ak.a(str8, "["), "]"));
                                } else {
                                    arrayList4.clear();
                                    arrayList4.add(ak.a(str8, "["));
                                    z = true;
                                }
                            } else if (isEndOfArray(str8)) {
                                arrayList4.add(ak.a(str8, "]"));
                                arrayList3.add(j.a(arrayList4, ",", null, null, 0, null, FunctionBaseKt$functionArgs$1$1$1.INSTANCE, 30, null));
                            } else {
                                String strA3 = n.a(n.a(str8, "\\[", "[", false, 4, (Object) null), "\\]", "]", false, 4, (Object) null);
                                if (z) {
                                    arrayList4.add(strA3);
                                } else {
                                    arrayList3.add(strA3);
                                }
                            }
                        }
                    }
                    ArrayList arrayList5 = arrayList3;
                    ArrayList arrayList6 = new ArrayList(j.a((Iterable) arrayList5, 10));
                    Iterator it2 = arrayList5.iterator();
                    while (it2.hasNext()) {
                        arrayList6.add((String) it2.next());
                    }
                    Object[] array = arrayList6.toArray(new String[0]);
                    if (array != null) {
                        arrayList.add(new FunctionArgs(str6, str7, (String[]) array, false, 8, null));
                    } else {
                        throw new q("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            } else {
                arrayList.add(new FunctionArgs(str2, null, new String[0], false));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String replaceSpecialStuff(String str, ReplaceSpecialMode replaceSpecialMode, c.k<Character, String>... kVarArr) {
        String strA;
        String str2 = str;
        for (c.k<Character, String> kVar : kVarArr) {
            char cCharValue = kVar.a().charValue();
            StringBuilder sb = new StringBuilder();
            sb.append('\\');
            sb.append(cCharValue);
            String string = sb.toString();
            String strB = kVar.b();
            String strValueOf = String.valueOf(cCharValue);
            switch (replaceSpecialMode) {
                case Reverse:
                    strA = n.a(str2, strB, strValueOf, false, 4, (Object) null);
                    break;
                case Normal:
                    strA = n.a(str2, string, strB, false, 4, (Object) null);
                    break;
                case ToString:
                    strA = n.a(str2, strValueOf, string, false, 4, (Object) null);
                    break;
                default:
                    throw new i();
            }
            str2 = strA;
        }
        return str2;
    }
}

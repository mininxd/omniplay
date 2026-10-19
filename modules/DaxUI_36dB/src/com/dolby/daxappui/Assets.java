package com.dolby.daxappui;

import android.content.Context;
import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public class Assets {
    private static Typeface sFontBold;
    private static Typeface sFontLight;
    private static Typeface sFontRegular;

    enum FontType {
        REGULAR,
        LIGHT,
        BOOK,
        BOLD
    }

    public static void init(Context context) {
        sFontRegular = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Regular.otf");
        sFontLight = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Light.otf");
        sFontBold = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-Semibold.otf");
    }

    /* JADX INFO: renamed from: com.dolby.daxappui.Assets$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dolby$daxappui$Assets$FontType = new int[FontType.values().length];

        static {
            try {
                $SwitchMap$com$dolby$daxappui$Assets$FontType[FontType.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dolby$daxappui$Assets$FontType[FontType.BOLD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dolby$daxappui$Assets$FontType[FontType.REGULAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static Typeface getFont(FontType fontType) {
        int i = AnonymousClass1.$SwitchMap$com$dolby$daxappui$Assets$FontType[fontType.ordinal()];
        if (i == 1) {
            return sFontLight;
        }
        if (i == 2) {
            return sFontBold;
        }
        return sFontRegular;
    }
}

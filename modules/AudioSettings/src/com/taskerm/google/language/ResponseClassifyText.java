package com.joaomgcd.taskerm.google.language;

import androidx.annotation.Keep;
import c.f.b.g;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ResponseClassifyText {
    private final ClassificationCategory[] categories;

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseClassifyText() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ResponseClassifyText(ClassificationCategory[] classificationCategoryArr) {
        this.categories = classificationCategoryArr;
    }

    public /* synthetic */ ResponseClassifyText(ClassificationCategory[] classificationCategoryArr, int i, g gVar) {
        this((i & 1) != 0 ? (ClassificationCategory[]) null : classificationCategoryArr);
    }

    public final ClassificationCategory[] getCategories() {
        return this.categories;
    }

    @Keep
    public static final class ClassificationCategory {
        private final Float confidence;
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public ClassificationCategory() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public ClassificationCategory(Float f2, String str) {
            this.confidence = f2;
            this.name = str;
        }

        public /* synthetic */ ClassificationCategory(Float f2, String str, int i, g gVar) {
            this((i & 1) != 0 ? (Float) null : f2, (i & 2) != 0 ? (String) null : str);
        }

        public final Float getConfidence() {
            return this.confidence;
        }

        public final String getName() {
            return this.name;
        }
    }
}

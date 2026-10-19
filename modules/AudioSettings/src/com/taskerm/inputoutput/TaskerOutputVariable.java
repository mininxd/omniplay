package com.joaomgcd.taskerm.inputoutput;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskerOutputVariable {
    int htmlLabelResId() default 2131300380;

    int index() default -1;

    int labelResId();

    int maxApi() default Integer.MAX_VALUE;

    int minApi() default -1;

    String name();
}

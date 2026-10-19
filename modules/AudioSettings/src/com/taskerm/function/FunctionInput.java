package com.joaomgcd.taskerm.function;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionInput {
    int explanationResId();

    int index();
}

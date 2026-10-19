package com.joaomgcd.taskerm.inputoutput;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes.dex */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface b {
    int a();

    boolean b() default false;

    a c() default a.Default;

    boolean d() default false;

    int e() default 0;

    boolean f() default false;

    String g() default "";
}

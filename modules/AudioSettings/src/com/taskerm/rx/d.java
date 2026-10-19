package com.joaomgcd.taskerm.rx;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface d {
    ThreadMode a() default ThreadMode.Main;
}

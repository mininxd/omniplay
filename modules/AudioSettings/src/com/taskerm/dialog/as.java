package com.joaomgcd.taskerm.dialog;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class as<T> extends p<x> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<T> f6284a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<Integer> f6285b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public as(x xVar, List<? extends T> list, List<Integer> list2) {
        super(xVar, null);
        c.f.b.k.b(xVar, "message");
        c.f.b.k.b(list, "selectedItems");
        c.f.b.k.b(list2, "selectedIndexes");
        this.f6284a = list;
        this.f6285b = list2;
    }

    public final List<T> b() {
        return this.f6284a;
    }

    public final List<Integer> c() {
        return this.f6285b;
    }
}

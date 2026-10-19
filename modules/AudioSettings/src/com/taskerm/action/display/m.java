package com.joaomgcd.taskerm.action.display;

/* JADX INFO: loaded from: classes.dex */
public enum m {
    Normal(1.0d),
    Small(0.8476190476190476d),
    Smaller(0.7d),
    Smallest(0.6d),
    Large(1.0952380952380953d),
    Larger(1.1904761904761905d),
    Largest(1.2857142857142858d);

    private final double i;

    m(double d2) {
        this.i = d2;
    }

    public final double a() {
        return this.i;
    }
}

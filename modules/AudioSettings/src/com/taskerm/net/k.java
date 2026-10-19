package com.joaomgcd.taskerm.net;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Integer f7902a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<String, String> f7903b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Long f7904c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private h f7905d;

    public k() {
        this(null, null, null, null, 15, null);
    }

    public k(Integer num, Map<String, String> map, Long l, h hVar) {
        this.f7902a = num;
        this.f7903b = map;
        this.f7904c = l;
        this.f7905d = hVar;
    }

    public /* synthetic */ k(Integer num, Map map, Long l, h hVar, int i, c.f.b.g gVar) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Map) null : map, (i & 4) != 0 ? (Long) null : l, (i & 8) != 0 ? (h) null : hVar);
    }

    public final Integer a() {
        return this.f7902a;
    }

    public final void a(h hVar) {
        this.f7905d = hVar;
    }

    public final void a(Integer num) {
        this.f7902a = num;
    }

    public final void a(Long l) {
        this.f7904c = l;
    }

    public final void a(Map<String, String> map) {
        this.f7903b = map;
    }

    public final Map<String, String> b() {
        return this.f7903b;
    }

    public final Long c() {
        return this.f7904c;
    }

    public final h d() {
        return this.f7905d;
    }
}

package com.joaomgcd.taskerm.function;

import c.s;

/* JADX INFO: loaded from: classes.dex */
public abstract class IFunctionNoOutput<TInput> extends FunctionBase<TInput, s> {
    @Override // com.joaomgcd.taskerm.function.FunctionBase
    public Class<s> getOutputClass() {
        return s.class;
    }
}

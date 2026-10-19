package com.joaomgcd.taskerm.keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputConnection;

/* JADX INFO: loaded from: classes.dex */
public final class InputMethodServiceTasker extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    @Override // android.inputmethodservice.InputMethodService
    public View onCreateInputView() {
        return null;
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onPress(int i) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onRelease(int i) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onText(CharSequence charSequence) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeDown() {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeLeft() {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeRight() {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeUp() {
    }

    static final class a extends c.f.b.l implements c.f.a.a<InputConnection> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final InputConnection invoke() {
            InputConnection currentInputConnection = InputMethodServiceTasker.this.getCurrentInputConnection();
            c.f.b.k.a((Object) currentInputConnection, "currentInputConnection");
            return currentInputConnection;
        }
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onBindInput() {
        super.onBindInput();
        com.joaomgcd.taskerm.rx.b.c(new d(new a()));
    }

    @Override // android.inputmethodservice.InputMethodService
    public void onUnbindInput() {
        super.onUnbindInput();
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onKey(int i, int[] iArr) {
        InputConnection currentInputConnection = getCurrentInputConnection();
        if (currentInputConnection != null) {
            if (i == -5) {
                if (TextUtils.isEmpty(currentInputConnection.getSelectedText(0))) {
                    currentInputConnection.deleteSurroundingText(1, 0);
                    return;
                } else {
                    currentInputConnection.commitText("", 1);
                    return;
                }
            }
            currentInputConnection.commitText(String.valueOf((char) i), 1);
        }
    }
}

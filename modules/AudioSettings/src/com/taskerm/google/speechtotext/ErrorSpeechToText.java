package com.joaomgcd.taskerm.google.speechtotext;

import android.content.Context;
import androidx.annotation.Keep;
import c.f.b.k;
import com.joaomgcd.taskerm.util.ab;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class ErrorSpeechToText implements ab {
    private final Error error;

    public ErrorSpeechToText(Error error) {
        k.b(error, "error");
        this.error = error;
    }

    public final Error getError() {
        return this.error;
    }

    public void toToast(Context context) {
        k.b(context, "context");
        ab.a.a(this, context);
    }

    @Override // com.joaomgcd.taskerm.util.ab
    public String getErrorMessage() {
        return this.error.getMessage() + " (Code " + this.error.getCode() + ')';
    }

    @Keep
    public static final class Error {
        private final Integer code;
        private final String message;
        private final String status;

        public Error(Integer num, String str, String str2) {
            this.code = num;
            this.message = str;
            this.status = str2;
        }

        public final Integer getCode() {
            return this.code;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getStatus() {
            return this.status;
        }
    }
}

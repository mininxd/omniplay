package com.joaomgcd.taskerm.google.drive.io;

import android.content.Context;
import c.f.b.k;
import com.joaomgcd.taskerm.retrofit.ErrorTaskerServer;
import com.joaomgcd.taskerm.util.ab;

/* JADX INFO: loaded from: classes.dex */
public final class ErrorPayloadGoogleDrive extends ErrorTaskerServer implements ab {
    private final ErrorTaskerServer.Error errorTasker;

    public static /* synthetic */ ErrorPayloadGoogleDrive copy$default(ErrorPayloadGoogleDrive errorPayloadGoogleDrive, ErrorTaskerServer.Error error, int i, Object obj) {
        if ((i & 1) != 0) {
            error = errorPayloadGoogleDrive.errorTasker;
        }
        return errorPayloadGoogleDrive.copy(error);
    }

    public final ErrorTaskerServer.Error component1() {
        return this.errorTasker;
    }

    public final ErrorPayloadGoogleDrive copy(ErrorTaskerServer.Error error) {
        return new ErrorPayloadGoogleDrive(error);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ErrorPayloadGoogleDrive) && k.a(this.errorTasker, ((ErrorPayloadGoogleDrive) obj).errorTasker);
        }
        return true;
    }

    public int hashCode() {
        ErrorTaskerServer.Error error = this.errorTasker;
        if (error != null) {
            return error.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ErrorPayloadGoogleDrive(errorTasker=" + this.errorTasker + ")";
    }

    public ErrorPayloadGoogleDrive(ErrorTaskerServer.Error error) {
        super(error);
        this.errorTasker = error;
    }

    public final ErrorTaskerServer.Error getErrorTasker() {
        return this.errorTasker;
    }

    public void toToast(Context context) {
        k.b(context, "context");
        ab.a.a(this, context);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ErrorPayloadGoogleDrive(String str) {
        this(new ErrorTaskerServer.Error(new ErrorTaskerServer.ErrorFromList[]{new ErrorTaskerServer.ErrorFromList("local", "local error", str, null, null)}, 0, str));
        k.b(str, "error");
    }
}

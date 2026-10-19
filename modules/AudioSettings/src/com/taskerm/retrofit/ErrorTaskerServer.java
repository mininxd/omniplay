package com.joaomgcd.taskerm.retrofit;

import androidx.annotation.Keep;
import c.f.b.l;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class ErrorTaskerServer {
    private final Error error;

    public ErrorTaskerServer(Error error) {
        this.error = error;
    }

    public final Error getError() {
        return this.error;
    }

    static final class a extends l implements c.f.a.b<ErrorFromList, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8353a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke(ErrorFromList errorFromList) {
            c.f.b.k.b(errorFromList, "it");
            return errorFromList.getFullErrorMessage();
        }
    }

    public final String getErrorMessage() {
        String message;
        ErrorFromList[] errors;
        Error error = this.error;
        if (error == null || (errors = error.getErrors()) == null || (message = c.a.d.a(errors, "\n\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, a.f8353a, 30, (Object) null)) == null) {
            Error error2 = this.error;
            message = error2 != null ? error2.getMessage() : null;
        }
        return message != null ? message : "Unknown Error";
    }

    @Keep
    public static final class Error {
        private final int code;
        private final ErrorFromList[] errors;
        private final String message;

        public static /* synthetic */ Error copy$default(Error error, ErrorFromList[] errorFromListArr, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                errorFromListArr = error.errors;
            }
            if ((i2 & 2) != 0) {
                i = error.code;
            }
            if ((i2 & 4) != 0) {
                str = error.message;
            }
            return error.copy(errorFromListArr, i, str);
        }

        public final ErrorFromList[] component1() {
            return this.errors;
        }

        public final int component2() {
            return this.code;
        }

        public final String component3() {
            return this.message;
        }

        public final Error copy(ErrorFromList[] errorFromListArr, int i, String str) {
            return new Error(errorFromListArr, i, str);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Error) {
                    Error error = (Error) obj;
                    if (c.f.b.k.a(this.errors, error.errors)) {
                        if (!(this.code == error.code) || !c.f.b.k.a((Object) this.message, (Object) error.message)) {
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            ErrorFromList[] errorFromListArr = this.errors;
            int iHashCode = (((errorFromListArr != null ? Arrays.hashCode(errorFromListArr) : 0) * 31) + this.code) * 31;
            String str = this.message;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Error(errors=" + Arrays.toString(this.errors) + ", code=" + this.code + ", message=" + this.message + ")";
        }

        public Error(ErrorFromList[] errorFromListArr, int i, String str) {
            this.errors = errorFromListArr;
            this.code = i;
            this.message = str;
        }

        public final int getCode() {
            return this.code;
        }

        public final ErrorFromList[] getErrors() {
            return this.errors;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    @Keep
    public static final class ErrorFromList {
        private final String domain;
        private final String location;
        private final String locationType;
        private final String message;
        private final String reason;

        public static /* synthetic */ ErrorFromList copy$default(ErrorFromList errorFromList, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = errorFromList.domain;
            }
            if ((i & 2) != 0) {
                str2 = errorFromList.reason;
            }
            String str6 = str2;
            if ((i & 4) != 0) {
                str3 = errorFromList.message;
            }
            String str7 = str3;
            if ((i & 8) != 0) {
                str4 = errorFromList.locationType;
            }
            String str8 = str4;
            if ((i & 16) != 0) {
                str5 = errorFromList.location;
            }
            return errorFromList.copy(str, str6, str7, str8, str5);
        }

        public final String component1() {
            return this.domain;
        }

        public final String component2() {
            return this.reason;
        }

        public final String component3() {
            return this.message;
        }

        public final String component4() {
            return this.locationType;
        }

        public final String component5() {
            return this.location;
        }

        public final ErrorFromList copy(String str, String str2, String str3, String str4, String str5) {
            return new ErrorFromList(str, str2, str3, str4, str5);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ErrorFromList)) {
                return false;
            }
            ErrorFromList errorFromList = (ErrorFromList) obj;
            return c.f.b.k.a((Object) this.domain, (Object) errorFromList.domain) && c.f.b.k.a((Object) this.reason, (Object) errorFromList.reason) && c.f.b.k.a((Object) this.message, (Object) errorFromList.message) && c.f.b.k.a((Object) this.locationType, (Object) errorFromList.locationType) && c.f.b.k.a((Object) this.location, (Object) errorFromList.location);
        }

        public int hashCode() {
            String str = this.domain;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.reason;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.message;
            int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.locationType;
            int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.location;
            return iHashCode4 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "ErrorFromList(domain=" + this.domain + ", reason=" + this.reason + ", message=" + this.message + ", locationType=" + this.locationType + ", location=" + this.location + ")";
        }

        public ErrorFromList(String str, String str2, String str3, String str4, String str5) {
            this.domain = str;
            this.reason = str2;
            this.message = str3;
            this.locationType = str4;
            this.location = str5;
        }

        public final String getDomain() {
            return this.domain;
        }

        public final String getLocation() {
            return this.location;
        }

        public final String getLocationType() {
            return this.locationType;
        }

        public final String getMessage() {
            return this.message;
        }

        public final String getReason() {
            return this.reason;
        }

        public final String getFullErrorMessage() {
            StringBuilder sb = new StringBuilder();
            String str = this.message;
            if (str == null) {
                str = "Unknown error";
            }
            sb.append(str);
            if (this.locationType != null && this.location != null) {
                sb.append(" in " + this.locationType + ' ' + this.location);
            }
            String string = sb.toString();
            c.f.b.k.a((Object) string, "StringBuilder().apply {\n…\n            }.toString()");
            return string;
        }
    }
}

package com.joaomgcd.taskerm.google.vision;

import android.graphics.Bitmap;
import androidx.annotation.Keep;
import c.f.b.g;
import c.f.b.k;
import com.joaomgcd.taskerm.util.aj;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class RequestAnnotate {
    public static final a Companion = new a(null);
    private final Request[] requests;

    public static final RequestAnnotate generateRequest(com.joaomgcd.taskerm.google.vision.a aVar, Bitmap bitmap, int i) {
        return Companion.a(aVar, bitmap, i);
    }

    public RequestAnnotate(Request[] requestArr) {
        k.b(requestArr, "requests");
        this.requests = requestArr;
    }

    public final Request[] getRequests() {
        return this.requests;
    }

    @Keep
    public static final class Request {
        private final Feature[] features;
        private final a image;

        public static final class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final String f7077a;

            public a(String str) {
                k.b(str, "content");
                this.f7077a = str;
            }
        }

        public Request(a aVar, Feature[] featureArr) {
            k.b(aVar, "image");
            k.b(featureArr, "features");
            this.image = aVar;
            this.features = featureArr;
        }

        public final Feature[] getFeatures() {
            return this.features;
        }

        public final a getImage() {
            return this.image;
        }

        @Keep
        public static final class Feature {
            private final int maxResults;
            private final com.joaomgcd.taskerm.google.vision.a type;

            public Feature(com.joaomgcd.taskerm.google.vision.a aVar, int i) {
                k.b(aVar, "type");
                this.type = aVar;
                this.maxResults = i;
            }

            public final int getMaxResults() {
                return this.maxResults;
            }

            public final com.joaomgcd.taskerm.google.vision.a getType() {
                return this.type;
            }
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final RequestAnnotate a(com.joaomgcd.taskerm.google.vision.a aVar, Bitmap bitmap, int i) {
            k.b(aVar, "type");
            k.b(bitmap, "image");
            byte[] bArrA = com.joaomgcd.taskerm.m.b.a(bitmap, Bitmap.CompressFormat.JPEG, 80);
            k.a((Object) bArrA, "image.getByteArray(Bitmap.CompressFormat.JPEG, 80)");
            return new RequestAnnotate(new Request[]{new Request(new Request.a(aj.c(bArrA)), new Request.Feature[]{new Request.Feature(aVar, i)})});
        }
    }
}

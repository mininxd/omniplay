package com.joaomgcd.taskerm.util;

/* JADX INFO: loaded from: classes.dex */
public final class dn {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f9304a = new a(null);

    public static final String a(Integer num) {
        return f9304a.a(num);
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(c.f.b.g gVar) {
            this();
        }

        public final String a(Integer num) {
            if (num == null) {
                return "";
            }
            if (num.intValue() == -1004) {
                return "File or network";
            }
            if (num.intValue() == -1007) {
                return "Bitstream is not conforming to the related coding standard or file spec";
            }
            if (num.intValue() == 200) {
                return "The video is streamed and its container is not valid for progressive playback i.e the video's index (e.g moov atom) is not at the start of the file.";
            }
            if (num.intValue() == 100) {
                return "Media server died";
            }
            if (num.intValue() == -110) {
                return "Some operation takes too long to complete, usually more than 3-5 seconds";
            }
            if (num.intValue() == -1010) {
                return "Bitstream is conforming to the related coding standard or file spec, but the media framework does not support the feature.";
            }
            return num.intValue() == Integer.MIN_VALUE ? "low-level system error" : "Unknown";
        }
    }
}

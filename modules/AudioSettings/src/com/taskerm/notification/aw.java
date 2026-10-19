package com.joaomgcd.taskerm.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.dk;
import cyanogenmod.os.Build;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public final class aw implements com.joaomgcd.taskerm.notification.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ c.j.g[] f8055a = {c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "sessionToken", "getSessionToken()Landroid/media/session/MediaSession$Token;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "controller", "getController()Landroid/media/session/MediaController;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "metadata", "getMetadata()Landroid/media/MediaMetadata;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "queue", "getQueue()Ljava/util/List;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "controls", "getControls()Landroid/media/session/MediaController$TransportControls;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "allMetadata", "getAllMetadata()Ljava/util/Map;")), c.f.b.x.a(new c.f.b.v(c.f.b.x.a(aw.class), "art", "getArt()Ljava/lang/String;"))};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.e f8056b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c.e f8057c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.e f8058d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c.e f8059e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c.e f8060f;
    private final c.e g;
    private final c.e h;
    private final Context i;
    private final StatusBarNotification j;

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaSession.Token E() {
        c.e eVar = this.f8056b;
        c.j.g gVar = f8055a[0];
        return (MediaSession.Token) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaController F() {
        c.e eVar = this.f8057c;
        c.j.g gVar = f8055a[1];
        return (MediaController) eVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaMetadata G() {
        c.e eVar = this.f8058d;
        c.j.g gVar = f8055a[2];
        return (MediaMetadata) eVar.b();
    }

    private final List<MediaSession.QueueItem> I() {
        c.e eVar = this.f8059e;
        c.j.g gVar = f8055a[3];
        return (List) eVar.b();
    }

    private final MediaController.TransportControls J() {
        c.e eVar = this.f8060f;
        c.j.g gVar = f8055a[4];
        return (MediaController.TransportControls) eVar.b();
    }

    private final int a(float f2, int i) {
        return (int) (f2 * (100.0f / i));
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String e() {
        c.e eVar = this.h;
        c.j.g gVar = f8055a[6];
        return (String) eVar.b();
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Map<String, Object> p() {
        c.e eVar = this.g;
        c.j.g gVar = f8055a[5];
        return (Map) eVar.b();
    }

    public aw(Context context, StatusBarNotification statusBarNotification) {
        c.f.b.k.b(context, "context");
        c.f.b.k.b(statusBarNotification, "notification");
        this.i = context;
        this.j = statusBarNotification;
        this.f8056b = c.f.a(new h());
        this.f8057c = c.f.a(new c());
        this.f8058d = c.f.a(new f());
        this.f8059e = c.f.a(new g());
        this.f8060f = c.f.a(new d());
        this.g = c.f.a(new a());
        this.h = c.f.a(new b());
    }

    public final Context B() {
        return this.i;
    }

    public final StatusBarNotification C() {
        return this.j;
    }

    public void D() {
        com.joaomgcd.taskerm.notification.a.C0206a.b(this);
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String q() {
        return com.joaomgcd.taskerm.notification.a.C0206a.a(this);
    }

    static final class h extends c.f.b.l implements c.f.a.a<MediaSession.Token> {
        h() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MediaSession.Token invoke() {
            Bundle bundle;
            Notification notification = aw.this.C().getNotification();
            Object obj = (notification == null || (bundle = notification.extras) == null) ? null : bundle.get(NotificationCompat.EXTRA_MEDIA_SESSION);
            if (!(obj instanceof MediaSession.Token)) {
                obj = null;
            }
            return (MediaSession.Token) obj;
        }
    }

    public String toString() {
        return q();
    }

    static final class c extends c.f.b.l implements c.f.a.a<MediaController> {
        c() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MediaController invoke() {
            MediaSession.Token tokenE = aw.this.E();
            if (tokenE != null) {
                return new MediaController(aw.this.B(), tokenE);
            }
            return null;
        }
    }

    static final class f extends c.f.b.l implements c.f.a.a<MediaMetadata> {
        f() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MediaMetadata invoke() {
            MediaController mediaControllerF = aw.this.F();
            if (mediaControllerF != null) {
                return mediaControllerF.getMetadata();
            }
            return null;
        }
    }

    static final class g extends c.f.b.l implements c.f.a.a<List<MediaSession.QueueItem>> {
        g() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<MediaSession.QueueItem> invoke() {
            MediaController mediaControllerF = aw.this.F();
            if (mediaControllerF != null) {
                return mediaControllerF.getQueue();
            }
            return null;
        }
    }

    private final PlaybackState H() {
        MediaController mediaControllerF = F();
        if (mediaControllerF != null) {
            return mediaControllerF.getPlaybackState();
        }
        return null;
    }

    static final class d extends c.f.b.l implements c.f.a.a<MediaController.TransportControls> {
        d() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final MediaController.TransportControls invoke() {
            MediaController mediaControllerF = aw.this.F();
            if (mediaControllerF != null) {
                return mediaControllerF.getTransportControls();
            }
            return null;
        }
    }

    public final boolean r() {
        return com.joaomgcd.taskerm.util.aj.a(this.j, NotificationCompat.EXTRA_MEDIA_SESSION);
    }

    static final class a extends c.f.b.l implements c.f.a.a<Map<String, ? extends Object>> {
        a() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Map<String, Object> invoke() {
            MediaMetadata mediaMetadataG;
            if (!aw.this.r() || (mediaMetadataG = aw.this.G()) == null) {
                return null;
            }
            return com.joaomgcd.taskerm.util.aj.a(mediaMetadataG, aw.this.B());
        }
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String h() {
        String packageName = this.j.getPackageName();
        c.f.b.k.a((Object) packageName, "notification.packageName");
        return packageName;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String a() {
        CharSequence text;
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG == null || (text = mediaMetadataG.getText("android.media.metadata.TITLE")) == null) {
            return null;
        }
        return text.toString();
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String c() {
        CharSequence text;
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG == null || (text = mediaMetadataG.getText("android.media.metadata.ALBUM")) == null) {
            return null;
        }
        return text.toString();
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String b() {
        CharSequence text;
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG == null || (text = mediaMetadataG.getText("android.media.metadata.ARTIST")) == null) {
            return null;
        }
        return text.toString();
    }

    private final Bitmap K() {
        Bundle bundle;
        Notification notification = this.j.getNotification();
        Object obj = (notification == null || (bundle = notification.extras) == null) ? null : bundle.get(NotificationCompat.EXTRA_LARGE_ICON);
        if (!(obj instanceof Bitmap)) {
            obj = null;
        }
        return (Bitmap) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    public final Bitmap L() {
        Icon largeIcon;
        Drawable drawableLoadDrawable;
        Bitmap bitmapA;
        if (com.joaomgcd.taskerm.util.f.f9323b.d()) {
            return K();
        }
        Notification notification = this.j.getNotification();
        return (notification == null || (largeIcon = notification.getLargeIcon()) == null || (drawableLoadDrawable = largeIcon.loadDrawable(this.i)) == null || (bitmapA = com.joaomgcd.taskerm.m.b.a(drawableLoadDrawable)) == null) ? K() : bitmapA;
    }

    static final class b extends c.f.b.l implements c.f.a.a<String> {
        b() {
            super(0);
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            Bitmap bitmap;
            String strA = aw.this.a("android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI");
            if (aw.this.a(strA)) {
                return strA;
            }
            MediaMetadata mediaMetadataG = aw.this.G();
            if (mediaMetadataG == null || (bitmap = mediaMetadataG.getBitmap("android.media.metadata.ART")) == null) {
                MediaMetadata mediaMetadataG2 = aw.this.G();
                bitmap = mediaMetadataG2 != null ? mediaMetadataG2.getBitmap("android.media.metadata.ALBUM_ART") : null;
            }
            if (bitmap == null) {
                MediaMetadata mediaMetadataG3 = aw.this.G();
                bitmap = mediaMetadataG3 != null ? mediaMetadataG3.getBitmap("android.media.metadata.DISPLAY_ICON") : null;
            }
            if (bitmap == null) {
                bitmap = aw.this.L();
            }
            Bitmap bitmap2 = bitmap;
            if (bitmap2 != null) {
                return com.joaomgcd.taskerm.m.b.a(bitmap2, aw.this.B(), null, "notifications", null, null, 26, null);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean a(String str) throws IOException {
        if (str == null) {
            return false;
        }
        if ((!c.f.b.k.a((Object) h(), (Object) "com.spotify.music")) || !dk.g(str)) {
            return true;
        }
        Uri uriC = com.joaomgcd.taskerm.util.ak.C(str);
        if (uriC == null) {
            return false;
        }
        InputStream inputStream = (InputStream) com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new e(uriC), 1, (Object) null);
        if (inputStream != null) {
            inputStream.close();
        }
        return inputStream != null;
    }

    static final class e extends c.f.b.l implements c.f.a.a<InputStream> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Uri f8066b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(Uri uri) {
            super(0);
            this.f8066b = uri;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final InputStream invoke() {
            return aw.this.B().getContentResolver().openInputStream(this.f8066b);
        }
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public boolean g() {
        PlaybackState playbackStateH = H();
        return playbackStateH != null && playbackStateH.getState() == 3;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String i() {
        PlaybackState playbackStateH = H();
        if (playbackStateH == null) {
            return null;
        }
        switch (playbackStateH.getState()) {
            case 0:
                return "none";
            case 1:
                return "stopped";
            case 2:
                return "paused";
            case 3:
                return "playing";
            case 4:
                return "fast forwarding";
            case 5:
                return "rewinding";
            case 6:
                return "buffering";
            case 7:
                return "error";
            case 8:
                return "connecting";
            case 9:
                return "skipping to previous";
            case 10:
                return "skipping to next";
            case 11:
                return "skipping to queue item";
            default:
                return Build.UNKNOWN;
        }
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Long f() {
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG != null) {
            return Long.valueOf(mediaMetadataG.getLong("android.media.metadata.DURATION"));
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String j() {
        CharSequence text;
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG == null || (text = mediaMetadataG.getText("android.media.metadata.GENRE")) == null) {
            return null;
        }
        return text.toString();
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Integer l() {
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG != null) {
            return Integer.valueOf((int) mediaMetadataG.getLong("android.media.metadata.NUM_TRACKS"));
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Integer m() {
        Rating ratingS = s();
        if (ratingS == null || !ratingS.isRated()) {
            return null;
        }
        switch (ratingS.getRatingStyle()) {
            case 1:
                return Integer.valueOf(ratingS.hasHeart() ? 100 : 0);
            case 2:
                return Integer.valueOf(ratingS.isThumbUp() ? 100 : 0);
            case 3:
                return Integer.valueOf(a(ratingS.getStarRating(), 3));
            case 4:
                return Integer.valueOf(a(ratingS.getStarRating(), 4));
            case 5:
                return Integer.valueOf(a(ratingS.getStarRating(), 5));
            case 6:
                return Integer.valueOf((int) ratingS.getPercentRating());
            default:
                return null;
        }
    }

    public final Rating s() {
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG != null) {
            return mediaMetadataG.getRating("android.media.metadata.USER_RATING");
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Integer k() {
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG != null) {
            return Integer.valueOf((int) mediaMetadataG.getLong("android.media.metadata.TRACK_NUMBER"));
        }
        return null;
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public Long d() {
        MediaMetadata mediaMetadataG = G();
        if (mediaMetadataG == null) {
            return null;
        }
        long j = mediaMetadataG.getLong("android.media.metadata.YEAR");
        if (j == 0) {
            return null;
        }
        return Long.valueOf(j);
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String[] n() {
        MediaDescription description;
        CharSequence title;
        List<MediaSession.QueueItem> listI = I();
        if (listI == null) {
            return null;
        }
        List<MediaSession.QueueItem> list = listI;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        for (MediaSession.QueueItem queueItem : list) {
            arrayList.add((queueItem == null || (description = queueItem.getDescription()) == null || (title = description.getTitle()) == null) ? null : title.toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.joaomgcd.taskerm.notification.a
    public String[] o() {
        MediaDescription description;
        Uri iconUri;
        List<MediaSession.QueueItem> listI = I();
        if (listI == null) {
            return null;
        }
        List<MediaSession.QueueItem> list = listI;
        ArrayList arrayList = new ArrayList(c.a.j.a((Iterable) list, 10));
        for (MediaSession.QueueItem queueItem : list) {
            arrayList.add((queueItem == null || (description = queueItem.getDescription()) == null || (iconUri = description.getIconUri()) == null) ? null : iconUri.toString());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final c.s t() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.pause();
        return c.s.f2131a;
    }

    public final c.s u() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.play();
        return c.s.f2131a;
    }

    public final c.s v() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.skipToNext();
        return c.s.f2131a;
    }

    public final c.s w() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.skipToPrevious();
        return c.s.f2131a;
    }

    public final c.s x() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.stop();
        return c.s.f2131a;
    }

    public final c.s y() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.fastForward();
        return c.s.f2131a;
    }

    public final c.s z() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ == null) {
            return null;
        }
        transportControlsJ.rewind();
        return c.s.f2131a;
    }

    public final void A() {
        MediaController.TransportControls transportControlsJ = J();
        if (transportControlsJ != null) {
            if (g()) {
                transportControlsJ.pause();
            } else {
                transportControlsJ.play();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String a(String... strArr) {
        String string;
        for (String str : strArr) {
            MediaMetadata mediaMetadataG = G();
            if (mediaMetadataG != null && (string = mediaMetadataG.getString(str)) != null) {
                return string;
            }
        }
        return null;
    }
}

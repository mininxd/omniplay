package com.joaomgcd.taskerm.t;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.util.Log;
import b.a.d.f;
import b.a.k.d;
import c.f.b.k;
import c.f.b.l;
import c.q;
import c.s;
import com.joaomgcd.taskerm.genericaction.GenericActionActivityRequestScreenCapture;
import com.joaomgcd.taskerm.rx.i;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cq;
import java.io.File;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MediaProjection f8721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MediaRecorder f8722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AudioRecord f8723c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private d<File> f8724d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private File f8725e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private File f8726f;
    private final Context g;

    /* JADX INFO: renamed from: com.joaomgcd.taskerm.t.c$c, reason: collision with other inner class name */
    static final class C0237c extends l implements c.f.a.a<s> {
        C0237c() {
            super(0);
        }

        @Override // c.f.a.a
        public /* synthetic */ s invoke() {
            a();
            return s.f2131a;
        }

        public final void a() {
            MediaRecorder mediaRecorder;
            AudioRecord audioRecord;
            try {
                try {
                    try {
                        MediaRecorder mediaRecorder2 = c.this.f8722b;
                        if (mediaRecorder2 != null) {
                            mediaRecorder2.stop();
                        }
                        mediaRecorder = c.this.f8722b;
                        if (mediaRecorder != null) {
                            mediaRecorder.release();
                        }
                    } catch (Throwable th) {
                        MediaRecorder mediaRecorder3 = c.this.f8722b;
                        if (mediaRecorder3 != null) {
                            mediaRecorder3.release();
                        }
                        throw th;
                    }
                } catch (Exception unused) {
                    mediaRecorder = c.this.f8722b;
                    if (mediaRecorder != null) {
                    }
                }
                try {
                    AudioRecord audioRecord2 = c.this.f8723c;
                    if (audioRecord2 != null) {
                        audioRecord2.stop();
                    }
                    audioRecord = c.this.f8723c;
                    if (audioRecord != null) {
                        audioRecord.release();
                    }
                } catch (Exception unused2) {
                    audioRecord = c.this.f8723c;
                    if (audioRecord != null) {
                    }
                } catch (Throwable th2) {
                    AudioRecord audioRecord3 = c.this.f8723c;
                    if (audioRecord3 != null) {
                        audioRecord3.release();
                    }
                    throw th2;
                }
                try {
                    MediaProjection mediaProjection = c.this.f8721a;
                    if (mediaProjection != null) {
                        mediaProjection.stop();
                    }
                    File file = c.this.f8725e;
                    if (file == null) {
                        if (c.this.f8724d != null) {
                            c.this.a("No File To Save");
                        }
                        c.this.f8724d = (d) null;
                        c.this.f8722b = (MediaRecorder) null;
                        c.this.f8721a = (MediaProjection) null;
                        File file2 = (File) null;
                        c.this.f8725e = file2;
                        c.this.f8726f = file2;
                        return;
                    }
                    if (c.this.f8724d != null) {
                        c.this.a(file);
                    }
                    c.this.f8724d = (d) null;
                    c.this.f8722b = (MediaRecorder) null;
                    c.this.f8721a = (MediaProjection) null;
                    File file3 = (File) null;
                    c.this.f8725e = file3;
                    c.this.f8726f = file3;
                } catch (Exception e2) {
                    if (c.this.f8724d != null) {
                        c.this.a(e2);
                    }
                }
            } catch (Throwable th3) {
                c.this.f8724d = (d) null;
                c.this.f8722b = (MediaRecorder) null;
                c.this.f8721a = (MediaProjection) null;
                File file4 = (File) null;
                c.this.f8725e = file4;
                c.this.f8726f = file4;
                throw th3;
            }
        }
    }

    public c(Context context) {
        k.b(context, "context");
        this.g = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(String str) {
        a(new com.joaomgcd.taskerm.t.a(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a(Throwable th) {
        d<File> dVar = this.f8724d;
        if (dVar != null && dVar.g()) {
            dVar.a_(th);
        }
        this.f8724d = (d) null;
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<File> b(String str) {
        b.a.l<File> lVarB = b.a.l.b(new com.joaomgcd.taskerm.t.a(str));
        k.a((Object) lVarB, "Single.error(ExceptionScreenCapture(error))");
        return lVarB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final b.a.l<File> b(Throwable th) {
        b.a.l<File> lVarB = b.a.l.b(th);
        k.a((Object) lVarB, "Single.error(error)");
        return lVarB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final s a(File file) {
        d<File> dVar = this.f8724d;
        if (dVar == null) {
            return null;
        }
        dVar.d_(file);
        return s.f2131a;
    }

    public final boolean a() {
        return this.f8726f != null;
    }

    public final File b() {
        if (a()) {
            return this.f8725e;
        }
        return null;
    }

    public final b.a.l<File> a(com.joaomgcd.taskerm.t.b bVar) {
        k.b(bVar, "args");
        File file = this.f8726f;
        if (file != null) {
            b.a.l<File> lVarA = b.a.l.a(file);
            k.a((Object) lVarA, "Single.just(it)");
            return lVarA;
        }
        this.f8724d = d.f();
        b.a.l<File> lVarB = b(bVar).b(new a());
        k.a((Object) lVarB, "startRecording(args).doO…lyStartedRecording = it }");
        return lVarB;
    }

    static final class a<T> implements f<File> {
        a() {
        }

        @Override // b.a.d.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void accept(File file) {
            k.b(file, "it");
            c.this.f8726f = file;
        }
    }

    static final class b extends l implements c.f.a.a<b.a.l<File>> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.joaomgcd.taskerm.t.b f8729b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(com.joaomgcd.taskerm.t.b bVar) {
            super(0);
            this.f8729b = bVar;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final b.a.l<File> invoke() throws InterruptedException {
            com.joaomgcd.taskerm.j.d dVarA;
            cl clVarB = new GenericActionActivityRequestScreenCapture().run(c.this.g).b();
            if (!clVarB.b()) {
                c cVar = c.this;
                String string = c.this.g.getString(R.string.dc_screen_capture_no_permissions);
                k.a((Object) string, "context.getString(R.stri…n_capture_no_permissions)");
                return cVar.b(string);
            }
            c cVar2 = c.this;
            if (clVarB != null) {
                cVar2.f8721a = (MediaProjection) ((cq) clVarB).d();
                MediaProjection mediaProjection = c.this.f8721a;
                if (mediaProjection == null) {
                    c cVar3 = c.this;
                    String string2 = c.this.g.getString(R.string.dc_screen_capture_no_permissions);
                    k.a((Object) string2, "context.getString(R.stri…n_capture_no_permissions)");
                    return cVar3.b(string2);
                }
                Thread.sleep(1000L);
                Point pointA = gr.a(c.this.g);
                k.a((Object) pointA, "Utils.getRealDisplaySize(context)");
                int i = pointA.x;
                int i2 = pointA.y;
                Point pointE = this.f8729b.e();
                if (pointE == null) {
                    pointE = new Point(i, i2);
                }
                Integer numF = this.f8729b.f();
                int iIntValue = numF != null ? numF.intValue() : 5000000;
                String str = this.f8729b.a() + '/' + (this.f8729b.b() + ".mp4");
                c cVar4 = c.this;
                MediaRecorder mediaRecorder = com.joaomgcd.taskerm.util.f.f9323b.h() ? new MediaRecorder(c.this.g) : new MediaRecorder();
                mediaRecorder.setOnErrorListener(new a(pointE, iIntValue, str, mediaProjection, i, i2));
                mediaRecorder.setOnInfoListener(C0236b.f8736a);
                mediaRecorder.setVideoSource(2);
                if (this.f8729b.c()) {
                    mediaRecorder.setAudioSource(1);
                }
                mediaRecorder.setOutputFormat(2);
                if (this.f8729b.c()) {
                    mediaRecorder.setAudioEncodingBitRate(96000);
                    mediaRecorder.setAudioChannels(1);
                    mediaRecorder.setAudioSamplingRate(48000);
                    mediaRecorder.setAudioEncoder(3);
                }
                Integer numD = this.f8729b.d();
                mediaRecorder.setVideoEncoder(numD != null ? numD.intValue() : 2);
                mediaRecorder.setVideoFrameRate(30);
                mediaRecorder.setVideoSize(pointE.x, pointE.y);
                mediaRecorder.setVideoEncodingBitRate(iIntValue);
                mediaRecorder.setMaxDuration(0);
                File file = new File(str);
                c.this.f8725e = file;
                File parentFile = file.getParentFile();
                if (parentFile != null && (dVarA = com.joaomgcd.taskerm.j.a.a(parentFile, c.this.g, false, 2, (Object) null)) != null) {
                    dVarA.a(c.this.g);
                }
                mediaRecorder.setOutputFile(str);
                try {
                    mediaRecorder.prepare();
                    Resources resources = c.this.g.getResources();
                    k.a((Object) resources, "context.resources");
                    mediaProjection.createVirtualDisplay("ScreenSharingDemo", i, i2, resources.getDisplayMetrics().densityDpi, 16, mediaRecorder.getSurface(), null, null);
                    mediaRecorder.start();
                    cVar4.f8722b = mediaRecorder;
                    File file2 = c.this.f8725e;
                    if (file2 == null) {
                        return c.this.b("No output file");
                    }
                    return b.a.l.a(file2);
                } catch (Exception e2) {
                    Exception exc = e2;
                    c.this.a(exc);
                    return c.this.b(exc);
                }
            }
            throw new q("null cannot be cast to non-null type com.joaomgcd.taskerm.util.SimpleResultWithPayload<android.media.projection.MediaProjection, com.joaomgcd.taskerm.util.ErrorPayloadString>");
        }

        static final class a implements MediaRecorder.OnErrorListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Point f8731b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ int f8732c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ String f8733d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ MediaProjection f8734e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            final /* synthetic */ int f8735f;
            final /* synthetic */ int g;

            a(Point point, int i, String str, MediaProjection mediaProjection, int i2, int i3) {
                this.f8731b = point;
                this.f8732c = i;
                this.f8733d = str;
                this.f8734e = mediaProjection;
                this.f8735f = i2;
                this.g = i3;
            }

            @Override // android.media.MediaRecorder.OnErrorListener
            public final void onError(MediaRecorder mediaRecorder, int i, int i2) {
                c.this.a("Error: " + i + ':' + i2);
            }
        }

        /* JADX INFO: renamed from: com.joaomgcd.taskerm.t.c$b$b, reason: collision with other inner class name */
        static final class C0236b implements MediaRecorder.OnInfoListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0236b f8736a = new C0236b();

            C0236b() {
            }

            @Override // android.media.MediaRecorder.OnInfoListener
            public final void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
                Log.v("ScreenCapturer", "Info: " + i + ':' + i2);
            }
        }
    }

    @TargetApi(31)
    private final b.a.l<File> b(com.joaomgcd.taskerm.t.b bVar) {
        b.a.l<File> lVarA = i.a(new b(bVar)).a();
        k.a((Object) lVarA, "getInBackground {\n      …result)\n        }.cache()");
        return lVarA;
    }

    public final b.a.l<File> c() {
        i.e(new C0237c());
        return this.f8724d;
    }
}

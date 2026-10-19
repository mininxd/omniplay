package com.joaomgcd.taskerm.action.display;

import android.graphics.Point;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.joaomgcd.taskerm.util.cl;
import com.joaomgcd.taskerm.util.cn;
import com.joaomgcd.taskerm.util.dm;
import java.io.File;
import java.util.List;
import net.dinglisch.android.taskerm.ExecuteService;

/* JADX INFO: loaded from: classes.dex */
public final class z extends com.joaomgcd.taskerm.helper.actions.execute.l<ag> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(ExecuteService executeService, net.dinglisch.android.taskerm.c cVar, Bundle bundle, com.joaomgcd.taskerm.action.a<ag, ?, ?> aVar) {
        super(executeService, cVar, bundle, aVar);
        c.f.b.k.b(executeService, NotificationCompat.CATEGORY_SERVICE);
        c.f.b.k.b(cVar, "action");
        c.f.b.k.b(bundle, "taskVars");
        c.f.b.k.b(aVar, "actionBase");
    }

    @Override // com.joaomgcd.taskerm.helper.actions.execute.l
    public cl a(ag agVar) {
        c.f.b.k.b(agVar, "input");
        l mode = agVar.getMode();
        if (mode == null) {
            return cn.a("No mode specified");
        }
        com.joaomgcd.taskerm.t.c cVarD = com.joaomgcd.taskerm.util.ah.c(h()).d();
        switch (mode) {
            case Start:
                return a(agVar, cVarD);
            case Stop:
                return a(cVarD);
            case Query:
                return b(cVarD);
            default:
                throw new c.i();
        }
    }

    private final cl a(com.joaomgcd.taskerm.t.c cVar) {
        File fileB;
        b.a.l<File> lVarC = cVar.c();
        if (lVarC == null || (fileB = lVarC.b()) == null) {
            return cn.a("No capturing is currently being done");
        }
        return cn.a(new OutputScreenCapture(com.joaomgcd.taskerm.util.aj.b(fileB), false));
    }

    private final cl b(com.joaomgcd.taskerm.t.c cVar) {
        File fileB = cVar.b();
        return cn.a(new OutputScreenCapture(fileB != null ? com.joaomgcd.taskerm.util.aj.b(fileB) : null, fileB != null));
    }

    private final cl a(ag agVar, com.joaomgcd.taskerm.t.c cVar) {
        File file;
        Point point;
        al alVar;
        List listB;
        if (cVar.a()) {
            return cn.a("Already capturing screen");
        }
        File fileA = h().a(agVar.getFile(), i().n(), false, true, true);
        if (fileA != null) {
            c.f.b.k.a((Object) fileA, "service.getSDPath(input.…\"Invalid file or folder\")");
            if (fileA.isDirectory()) {
                file = new File(fileA, "capture_" + com.joaomgcd.taskerm.util.aj.a(dm.f(), "yyyy_MM_dd_HH_mm_ss") + ".mp4");
            } else {
                file = fileA;
            }
            Boolean captureSound = agVar.getCaptureSound();
            boolean zBooleanValue = captureSound != null ? captureSound.booleanValue() : false;
            String resolution = agVar.getResolution();
            if (resolution == null || (listB = c.l.n.b((CharSequence) resolution, new String[]{","}, false, 0, 6, (Object) null)) == null) {
                point = null;
            } else {
                String str = (String) c.a.j.a(listB, 0);
                Integer numC = str != null ? c.l.n.c(str) : null;
                String str2 = (String) c.a.j.a(listB, 1);
                Integer numC2 = str2 != null ? c.l.n.c(str2) : null;
                point = (numC == null || numC2 == null) ? null : new Point(numC.intValue(), numC2.intValue());
            }
            String videoEncoder = agVar.getVideoEncoder();
            Integer numValueOf = (videoEncoder == null || (alVar = (al) com.joaomgcd.taskerm.util.aj.a((c.f.a.b) null, new a(videoEncoder), 1, (Object) null)) == null) ? null : Integer.valueOf(alVar.a());
            String bitrate = agVar.getBitrate();
            File fileB = cVar.a(new com.joaomgcd.taskerm.t.b(file, zBooleanValue, numValueOf, point, bitrate != null ? c.l.n.c(bitrate) : null)).b();
            c.f.b.k.a((Object) fileB, "file");
            return cn.a(new OutputScreenCapture(com.joaomgcd.taskerm.util.aj.b(fileB), true));
        }
        return cn.a("Invalid file or folder");
    }

    static final class a extends c.f.b.l implements c.f.a.a<al> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f5143a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str) {
            super(0);
            this.f5143a = str;
        }

        @Override // c.f.a.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final al invoke() {
            return al.valueOf(this.f5143a);
        }
    }
}

package com.joaomgcd.taskerm.function;

import androidx.core.app.NotificationCompat;
import c.f.b.k;
import com.joaomgcd.taskerm.util.am;
import java.io.File;
import net.danlew.android.joda.R;

/* JADX INFO: loaded from: classes.dex */
public final class InputWaitForFileEvent {
    private final am event;
    private final File filePath;
    private final long timeoutSeconds;

    @FunctionInput(explanationResId = R.string.pl_event, index = 1)
    public static /* synthetic */ void event$annotations() {
    }

    @FunctionInput(explanationResId = R.string.pl_path, index = 0)
    public static /* synthetic */ void filePath$annotations() {
    }

    @FunctionInput(explanationResId = R.string.pl_timeout_seconds, index = 2)
    public static /* synthetic */ void timeoutSeconds$annotations() {
    }

    public InputWaitForFileEvent(File file, am amVar, long j) {
        k.b(file, "filePath");
        k.b(amVar, NotificationCompat.CATEGORY_EVENT);
        this.filePath = file;
        this.event = amVar;
        this.timeoutSeconds = j;
    }

    public final File getFilePath() {
        return this.filePath;
    }

    public final am getEvent() {
        return this.event;
    }

    public final long getTimeoutSeconds() {
        return this.timeoutSeconds;
    }
}

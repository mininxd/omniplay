package com.sonyericsson.soundenhancement;

import android.media.AudioManager;
import android.media.AudioRecord;
import android.os.Environment;
import com.sonyericsson.soundenhancement.ahc.Logger;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ShortBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AutomaticHeadphoneWorker {
    private static final String AHC_PATH = "/AHC";
    private static final boolean BACKUP_FILES = false;
    private static final String DATA_PATH = "/data";
    private static final String DEBUG_PATH = "/debug";
    private static final String FILE_NAME = "/hscomp.pcm";
    private static final int HSCOMP_JNI_RES_ERROR = -1;
    private static final int HSCOMP_JNI_RES_HI_IMP = 4;
    private static final int HSCOMP_JNI_RES_INSUFFICIENT_DATA = 2;
    private static final int HSCOMP_JNI_RES_MORE = 0;
    private static final int HSCOMP_JNI_RES_NEW_HS_DETECTED = 5;
    private static final int HSCOMP_JNI_RES_NO_FILTER = 3;
    private static final int HSCOMP_JNI_RES_SAME_HS_DETECTED = 1;
    public static final int HSCOMP_RESULT_ERROR = -1;
    public static final int HSCOMP_RESULT_HI_IMP = 3;
    public static final int HSCOMP_RESULT_INSUFFICIENT_DATA = 1;
    public static final int HSCOMP_RESULT_MUSIC_STOPPED = 5;
    public static final int HSCOMP_RESULT_NEW_HS_DETECTED = 4;
    public static final int HSCOMP_RESULT_NO_FILTER = 2;
    public static final int HSCOMP_RESULT_REC_ERROR = -2;
    public static final int HSCOMP_RESULT_SAME_HS_DETECTED = 0;
    private static final int IDLE = 1;
    private static final int NBR_BANDS = 5;
    private static final int NBR_COEFFS = 5;
    private static final int NBR_SRS = 3;
    private static final int RECORDER_BUFFER_SIZE_BYTES = 32768;
    private static final int RECORDER_BUFFER_SIZE_SHORTS = 16384;
    private static final int RECORDING = 2;
    private static final boolean SAVE_PCM_FILE = false;
    private static final boolean VV = false;
    private static boolean mInitialized;
    private static int mNbrFilters;
    private static final String[] mSamplingrateNames;
    private static final String[] mSamplingrateStrings;
    private AudioManager mAudioManager;
    private AudioRecord mAudioRecord = null;
    private Thread mWorkerThread = null;
    private int mRecordingState = 1;
    private int mCounter = 0;
    private boolean mSendFilterAfterAnalyze = true;
    private BufferedOutputStream mStream = null;
    private AnalyzeCallback mCb = null;
    private float[] mSavedFilter = null;
    private int mResult = 0;
    private String mFilePath = "";

    public interface AnalyzeCallback {
        void analyzeFinished(int i);
    }

    private static native int closeHsCompNative();

    private native int handleHsCompBufferNative(ShortBuffer shortBuffer, float[] fArr, String str);

    private static native int initHsCompNative();

    private static native int resetHsCompNative();

    private static native int resetRetryHsCompNative();

    static {
        Logger.LOGD("Loading JNI Library");
        System.loadLibrary("hscomp_jni");
        mSamplingrateStrings = new String[]{"ahc_sr=48000;", "ahc_sr=96000;", "ahc_sr=192000;"};
        mSamplingrateNames = new String[]{"48 kHz", "96 kHz", "192 kHz"};
        mNbrFilters = 0;
        mInitialized = false;
        Logger.LOGD("Initializing JNI Library");
        initializeJni("AutomaticHeadphoneWorker: ");
    }

    public AutomaticHeadphoneWorker(AudioManager audioManager) {
        this.mAudioManager = null;
        this.mAudioManager = audioManager;
    }

    public boolean start(AnalyzeCallback cb) {
        return start(cb, true);
    }

    public boolean start(AnalyzeCallback cb, boolean sendFilter) {
        Logger.LOGD("start: RecState " + this.mRecordingState + " sendFilter " + sendFilter);
        if (this.mRecordingState != 1) {
            return false;
        }
        this.mCb = cb;
        this.mSendFilterAfterAnalyze = sendFilter;
        if (this.mResult == 1) {
            resetRetryHsCompNative();
        } else {
            resetHsCompNative();
        }
        return startRecording();
    }

    public void stop() {
        Logger.LOGD("stop: RecState " + this.mRecordingState);
        if (this.mRecordingState != 2) {
            Logger.LOGE("stop: Trying to stop recording when idle");
            return;
        }
        stopRecording(true);
        this.mCb = null;
    }

    public void setFilePath(String filePath) {
        this.mFilePath = filePath;
    }

    public void clearFilter() {
        Logger.LOGD("clearFilter");
        this.mAudioManager.setParameters("ahc_clear=1");
    }

    public int getFilterSize() {
        return mNbrFilters * 5 * 3;
    }

    public float[] getCurrentFilter() {
        float[] fArr = this.mSavedFilter;
        if (fArr == null) {
            return null;
        }
        return (float[]) fArr.clone();
    }

    private static boolean initializeJni(String func) {
        if (!mInitialized) {
            mNbrFilters = initHsCompNative();
            mInitialized = true;
            if (mNbrFilters < 0) {
                Logger.LOGE(func + "Could not initialize JNI");
                closeHsCompNative();
                mInitialized = false;
                return false;
            }
            Logger.LOGD(func + "Initialized correctly. " + mNbrFilters + " filters created.");
        }
        return true;
    }

    private boolean startRecording() {
        Logger.LOGD("startRecording: mRecordingState " + this.mRecordingState);
        if (this.mRecordingState == 1) {
            this.mRecordingState = 2;
            if (!initializeJni("startRecording: ") || !createAudioRecord("startRecording: ")) {
                return false;
            }
            this.mAudioManager.setParameters("ahc_recording=1");
            this.mAudioRecord.startRecording();
            startNewWorkerThread();
        }
        return true;
    }

    private void stopRecording(boolean stopThread) {
        Logger.LOGD("stopRecording: mRecordingState " + this.mRecordingState + " stopThread " + stopThread);
        if (this.mRecordingState == 2) {
            this.mAudioManager.setParameters("ahc_recording=0");
            if (stopThread) {
                killWorkerThread();
            }
            AudioRecord audioRecord = this.mAudioRecord;
            if (audioRecord != null) {
                audioRecord.stop();
                this.mAudioRecord.release();
                this.mAudioRecord = null;
            }
            this.mRecordingState = 1;
        }
    }

    public void sendFilterParams(String func, float[] filter) {
        if (filter != null) {
            Logger.LOGD(func + "sendFilterParams mNbrFilters = " + mNbrFilters + "filter len = " + filter.length);
            int nbrSr = filter.length / 25;
            DecimalFormat decFor = new DecimalFormat("0.000000000", new DecimalFormatSymbols(Locale.ENGLISH));
            decFor.setNegativePrefix("-");
            for (int j = 0; j < nbrSr; j++) {
                int srOffset = j * 5 * 5;
                for (int i = 0; i < mNbrFilters; i++) {
                    int startIndex = (i * 5) + srOffset;
                    String b0 = decFor.format(filter[startIndex + 0]);
                    String b1 = decFor.format(filter[startIndex + 1]);
                    String b2 = decFor.format(filter[startIndex + 2]);
                    String a1 = decFor.format(filter[startIndex + 3]);
                    String a2 = decFor.format(filter[startIndex + 4]);
                    String out = mSamplingrateStrings[j] + "ahc_band=" + i + ";ahc_b0=" + b0 + ";ahc_b1=" + b1 + ";ahc_b2=" + b2 + ";ahc_a1=" + a1 + ";ahc_a2=" + a2;
                    Logger.LOGD(func + out);
                    this.mAudioManager.setParameters(out);
                }
            }
            this.mAudioManager.setParameters("ahc_finished=1");
        }
    }

    private void saveFilter(String func, float[] filter) {
        Logger.LOGD(func + "saveFilter");
        this.mSavedFilter = new float[mNbrFilters * 5 * 3];
        for (int i = 0; i < mNbrFilters * 5 * 3; i++) {
            this.mSavedFilter[i] = filter[i];
        }
    }

    private void handleFinished(int result) {
        stopRecording(false);
        Logger.LOGD("handleFinished result " + result);
        this.mResult = result;
        AnalyzeCallback analyzeCallback = this.mCb;
        if (analyzeCallback != null) {
            analyzeCallback.analyzeFinished(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void work() {
        ShortBuffer sBuffer = ShortBuffer.allocate(16384);
        int i = mNbrFilters;
        if (i <= 0) {
            Logger.LOGE("work: AHC failed initialization. " + mNbrFilters + " returned, but at least 1 is needed.");
            handleFinished(-1);
            return;
        }
        float[] filter = new float[i * 5 * 3];
        for (int i2 = 0; i2 < 6; i2++) {
            this.mAudioRecord.read(sBuffer.array(), 0, 16384);
            sBuffer.clear();
        }
        while (!Thread.interrupted()) {
            int shortsRead = readData(sBuffer);
            if (shortsRead < 0) {
                return;
            }
            sBuffer.flip();
            try {
                int res = handleHsCompBufferNative(sBuffer, filter, this.mFilePath);
                if (res < 0) {
                    Logger.LOGE("work: Error when handling audio buffer in JNI");
                    handleFinished(-1);
                    return;
                }
                if (res > 0) {
                    stopRecording(false);
                    if (res == 2) {
                        Logger.LOGD("work: Insufficient data received");
                        handleFinished(1);
                        return;
                    }
                    if (res == 1) {
                        Logger.LOGD("work: Same Headset Detected");
                        handleFinished(0);
                        return;
                    }
                    if (res == 4) {
                        Logger.LOGD("work: High impedance headset detected");
                        handleFinished(3);
                        return;
                    }
                    if (res == 5) {
                        Logger.LOGD("work: New Headset Detected");
                        onJniFinished(res, filter);
                        return;
                    }
                    Logger.LOGD("work: Data was received, but no filter could be created");
                    handleFinished(2);
                    return;
                }
                if (!this.mAudioManager.isMusicActive()) {
                    Logger.LOGD("work: Music stopped");
                    handleFinished(5);
                    return;
                }
                sBuffer.clear();
            } catch (Exception e) {
                Logger.LOGE("work: Could not handle data");
                e.printStackTrace();
                handleFinished(-1);
                return;
            }
        }
    }

    private int readData(ShortBuffer sBuffer) {
        int shortsRead = this.mAudioRecord.read(sBuffer.array(), 0, 16384);
        if (-2 == shortsRead || -3 == shortsRead || -1 == shortsRead) {
            Logger.LOGE("readData: Could not read data: " + shortsRead);
            handleFinished(-2);
            return -1;
        }
        return shortsRead;
    }

    private void onJniFinished(int res, float[] filter) {
        String tempString = "onJniFinished: JNI has finished analyzing audio. res = " + res + ". Filter:\n";
        for (int i = 0; i < mNbrFilters * 5 * 3; i++) {
            if (i != 0 && i % 5 == 0) {
                tempString = tempString.concat("\n");
            }
            int srNameIdx = i / 25;
            if (i % 25 == 0 && srNameIdx < mSamplingrateNames.length) {
                tempString = tempString.concat("Sample rate " + mSamplingrateNames[srNameIdx] + "\n");
            }
            tempString = tempString.concat(filter[i] + "  ");
        }
        Logger.LOGI(tempString);
        saveFilter("onJniFinished: ", filter);
        if (this.mSendFilterAfterAnalyze) {
            sendFilterParams("onJniFinished: ", filter);
        }
        handleFinished(4);
    }

    private void killWorkerThread() {
        Thread thread = this.mWorkerThread;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.mWorkerThread.interrupt();
        while (this.mWorkerThread.isAlive()) {
        }
        this.mWorkerThread = null;
    }

    private void startNewWorkerThread() {
        killWorkerThread();
        this.mWorkerThread = new Thread("AutomaticHeadphoneWorker:readWorker:" + this.mCounter) { // from class: com.sonyericsson.soundenhancement.AutomaticHeadphoneWorker.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                AutomaticHeadphoneWorker.this.work();
            }
        };
        this.mCounter = this.mCounter + 1;
        this.mWorkerThread.start();
    }

    private boolean createFileStream() {
        String path = getAhcSubfolderPath(DATA_PATH);
        if (path == null) {
            Logger.LOGE("createFileStream: Could not create data path");
            return false;
        }
        String updatedFilename = path + FILE_NAME;
        Logger.LOGD("createFileStream: Creating file " + updatedFilename);
        if (!ensureFileExists(updatedFilename)) {
            Logger.LOGE("createFileStream: Could not create file: " + updatedFilename);
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(updatedFilename);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 32768);
            this.mStream = bufferedOutputStream;
            return true;
        } catch (Exception e) {
            Logger.LOGE("createFileStream: Could not apply filename: " + updatedFilename);
            e.printStackTrace();
            return false;
        }
    }

    private void closeFileStream(String func) {
        BufferedOutputStream bufferedOutputStream = this.mStream;
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
                this.mStream = null;
            } catch (Exception e) {
                Logger.LOGE(func + "Could not close file");
                e.printStackTrace();
            }
        }
    }

    private boolean createAudioRecord(String func) {
        boolean success = true;
        try {
            this.mAudioRecord = new AudioRecord(0, 44100, 12, 2, 32768);
            if (1 != this.mAudioRecord.getState()) {
                Logger.LOGE(func + "Could not create AudioRecord");
                success = false;
            }
        } catch (Exception e) {
            Logger.LOGE(func + "Could not create AudioRecord");
            e.printStackTrace();
            success = false;
        }
        if (!success) {
            this.mAudioRecord = null;
            handleFinished(-2);
        }
        return success;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    private boolean ensureFileExists(String str) {
        boolean zIsDirectory;
        ?? sb;
        ?? r0;
        ?? r1 = "Could not create directory: ";
        String strReplaceAll = str.replaceAll("[/][^/]+$", "");
        File file = new File(strReplaceAll);
        File file2 = new File(str);
        if (file2.exists()) {
            return true;
        }
        boolean zExists = file.exists();
        if (zExists) {
            zIsDirectory = file.isDirectory();
            if (!zIsDirectory) {
                r1 = zIsDirectory;
                Logger.LOGE("ensureFileExists: Directory points to a file: " + strReplaceAll);
                return false;
            }
        } else {
            zExists = false;
            try {
                try {
                    zExists = file.mkdirs();
                    r1 = r1;
                    if (!zExists) {
                        r0 = r1;
                        sb = new StringBuilder();
                        sb.append("ensureFileExists: ");
                        sb.append(r0);
                        sb.append(strReplaceAll);
                        Logger.LOGE(sb.toString());
                        return false;
                    }
                } catch (Throwable th) {
                    if (zExists) {
                        throw th;
                    }
                    r0 = r1;
                    sb = new StringBuilder();
                }
            } catch (Exception e) {
                e.printStackTrace();
                r1 = r1;
                if (0 == 0) {
                    r0 = r1;
                    sb = new StringBuilder();
                }
                r1 = zIsDirectory;
                file2.createNewFile();
                return true;
            }
        }
        try {
            r1 = zIsDirectory;
            file2.createNewFile();
            return true;
        } catch (Exception e2) {
            Logger.LOGE("ensureFileExists: Could not create file: " + str);
            e2.printStackTrace();
            return false;
        }
    }

    private String getAhcSubfolderPath(String subFolderName) {
        String folderPath = Environment.getExternalStorageDirectory().getPath() + AHC_PATH + subFolderName;
        File subFolder = new File(folderPath);
        boolean success = true;
        if (!subFolder.exists()) {
            success = subFolder.mkdirs();
        } else if (!subFolder.isDirectory()) {
            Logger.LOGE("getAhcSubfolderPath: Directory points to a file: " + folderPath);
            success = false;
        }
        if (!success) {
            Logger.LOGE("getAhcSubfolderPath: Failed to create path " + folderPath);
            return null;
        }
        return folderPath;
    }

    public boolean copyDebugFiles(String debugFilesPath) {
        return true;
    }

    private void copyFolder(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                if (!dest.mkdir()) {
                    Logger.LOGW("Failed to make directory");
                    return;
                }
                Logger.LOGD("Directory copied from " + src + "  to " + dest);
            }
            String[] files = src.list();
            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    copyFolder(srcFile, destFile);
                }
                Logger.LOGD("Copied " + files.length + " files");
                return;
            }
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            try {
                byte[] buffer = new byte[1024];
                in = new FileInputStream(src);
                out = new FileOutputStream(dest);
                while (true) {
                    int length = in.read(buffer);
                    if (length > 0) {
                        out.write(buffer, 0, length);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            Logger.LOGW("Failed to close file");
                        }
                    }
                }
                in.close();
                try {
                    out.close();
                } catch (IOException e2) {
                    Logger.LOGW("Failed to close file");
                }
            } catch (Throwable th) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e3) {
                        Logger.LOGW("Failed to close file");
                    }
                }
                if (out == null) {
                    throw th;
                }
                try {
                    out.close();
                    throw th;
                } catch (IOException e4) {
                    Logger.LOGW("Failed to close file");
                    throw th;
                }
            }
        } catch (IOException e5) {
            throw e5;
        }
    }
}

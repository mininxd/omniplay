package com.joaomgcd.taskerm.b;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class w implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    OutputStream f5972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Socket f5973b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private InputStream f5975d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f5977f;
    private boolean g;
    private int h;
    private x i;
    private boolean j;
    private HashMap<Integer, z> k = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f5974c = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Thread f5976e = b();

    private w() {
    }

    public static w a(Socket socket, x xVar) throws IOException {
        w wVar = new w();
        wVar.i = xVar;
        wVar.f5973b = socket;
        wVar.f5975d = socket.getInputStream();
        wVar.f5972a = socket.getOutputStream();
        socket.setTcpNoDelay(true);
        return wVar;
    }

    private Thread b() {
        return new Thread(new Runnable() { // from class: com.joaomgcd.taskerm.b.w.1
            /* JADX WARN: Code duplicated, block: B:55:0x00e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            public void run() {
                z zVar;
                byte[] bArrA;
                while (!w.this.f5976e.isInterrupted()) {
                    try {
                        y.a aVarA = y.a.a(w.this.f5975d);
                        if (y.a(aVarA)) {
                            switch (aVarA.f5985a) {
                                case 1163086915:
                                case 1163154007:
                                case 1497451343:
                                    if (this.g && (zVar = (z) w.this.k.get(Integer.valueOf(aVarA.f5987c))) != null) {
                                        synchronized (zVar) {
                                            if (aVarA.f5985a == 1497451343) {
                                                zVar.a(aVarA.f5986b);
                                                zVar.b();
                                                zVar.notify();
                                            } else if (aVarA.f5985a == 1163154007) {
                                                zVar.a(aVarA.g);
                                                zVar.a();
                                            } else if (aVarA.f5985a == 1163086915) {
                                                this.k.remove(Integer.valueOf(aVarA.f5987c));
                                                zVar.c();
                                            }
                                        }
                                    }
                                    break;
                                case 1213486401:
                                    if (aVarA.f5986b != 1) {
                                        continue;
                                    } else {
                                        if (this.j) {
                                            bArrA = y.a(3, this.i.a());
                                        } else {
                                            bArrA = y.a(2, this.i.a(aVarA.g));
                                            this.j = true;
                                        }
                                        this.f5972a.write(bArrA);
                                        this.f5972a.flush();
                                    }
                                    break;
                                case 1314410051:
                                    synchronized (this) {
                                        this.h = aVarA.f5987c;
                                        this.g = true;
                                        this.notifyAll();
                                        break;
                                    }
                                    break;
                                default:
                                    continue;
                            }
                            synchronized (this) {
                                w.this.c();
                                this.notifyAll();
                                this.f5977f = false;
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                synchronized (this) {
                    w.this.c();
                    this.notifyAll();
                    this.f5977f = false;
                }
            }
        });
    }

    public void a() throws InterruptedException, IOException {
        if (this.g) {
            throw new IllegalStateException("Already connected");
        }
        this.f5972a.write(y.a());
        this.f5972a.flush();
        this.f5977f = true;
        this.f5976e.start();
        synchronized (this) {
            if (!this.g) {
                wait();
            }
            if (!this.g) {
                throw new IOException("Connection failed");
            }
        }
    }

    public z a(String str) throws InterruptedException, IOException {
        int i = this.f5974c + 1;
        this.f5974c = i;
        if (!this.f5977f) {
            throw new IllegalStateException("connect() must be called first");
        }
        synchronized (this) {
            if (!this.g) {
                wait();
            }
            if (!this.g) {
                throw new IOException("Connection failed");
            }
        }
        z zVar = new z(this, i);
        this.k.put(Integer.valueOf(i), zVar);
        this.f5972a.write(y.a(i, str));
        this.f5972a.flush();
        synchronized (zVar) {
            zVar.wait();
        }
        if (zVar.e()) {
            throw new ConnectException("Stream open actively rejected by remote peer");
        }
        return zVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Iterator<z> it = this.k.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (IOException unused) {
            }
        }
        this.k.clear();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f5976e == null) {
            return;
        }
        this.f5973b.close();
        this.f5976e.interrupt();
        try {
            this.f5976e.join();
        } catch (InterruptedException unused) {
        }
    }
}

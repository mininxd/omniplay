package com.joaomgcd.taskerm.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class bc {
    public static String a(File file) {
        try {
            return a(new FileInputStream(file));
        } catch (FileNotFoundException e2) {
            Log.e("MD5", "Exception while getting FileInputStream", e2);
            return null;
        }
    }

    public static String a(InputStream inputStream) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[8192];
            while (true) {
                try {
                    try {
                        int i = inputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        messageDigest.update(bArr, 0, i);
                    } catch (IOException e2) {
                        throw new RuntimeException("Unable to process file for MD5", e2);
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        Log.e("MD5", "Exception on closing MD5 input stream", e3);
                    }
                    throw th;
                }
            }
            String strReplace = String.format("%32s", new BigInteger(1, messageDigest.digest()).toString(16)).replace(' ', '0');
            try {
                inputStream.close();
            } catch (IOException e4) {
                Log.e("MD5", "Exception on closing MD5 input stream", e4);
            }
            return strReplace;
        } catch (NoSuchAlgorithmException e5) {
            Log.e("MD5", "Exception while getting digest", e5);
            return null;
        }
    }
}

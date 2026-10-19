package com.joaomgcd.taskerm.b;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5980a = {0, 1, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 0, 48, 33, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0, 4, 20};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static byte[] f5981b = new byte[f5980a.length];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private KeyPair f5982c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private v f5983d;

    static {
        for (int i = 0; i < f5981b.length; i++) {
            f5981b[i] = (byte) f5980a[i];
        }
    }

    private static byte[] a(RSAPublicKey rSAPublicKey) {
        BigInteger bit = BigInteger.ZERO.setBit(32);
        BigInteger modulus = rSAPublicKey.getModulus();
        BigInteger bigIntegerModPow = BigInteger.ZERO.setBit(2048).modPow(BigInteger.valueOf(2L), modulus);
        BigInteger bigIntegerModInverse = modulus.remainder(bit).modInverse(bit);
        int[] iArr = new int[64];
        int[] iArr2 = new int[64];
        BigInteger bigInteger = modulus;
        int i = 0;
        while (i < 64) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigIntegerModPow.divideAndRemainder(bit);
            BigInteger bigInteger2 = bigIntegerArrDivideAndRemainder[0];
            iArr2[i] = bigIntegerArrDivideAndRemainder[1].intValue();
            BigInteger[] bigIntegerArrDivideAndRemainder2 = bigInteger.divideAndRemainder(bit);
            bigInteger = bigIntegerArrDivideAndRemainder2[0];
            iArr[i] = bigIntegerArrDivideAndRemainder2[1].intValue();
            i++;
            bigIntegerModPow = bigInteger2;
        }
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(524).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putInt(64);
        byteBufferOrder.putInt(bigIntegerModInverse.negate().intValue());
        for (int i2 : iArr) {
            byteBufferOrder.putInt(i2);
        }
        for (int i3 : iArr2) {
            byteBufferOrder.putInt(i3);
        }
        byteBufferOrder.putInt(rSAPublicKey.getPublicExponent().intValue());
        return byteBufferOrder.array();
    }

    public static x a(v vVar, File file, File file2) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        x xVar = new x();
        byte[] bArr = new byte[(int) file.length()];
        byte[] bArr2 = new byte[(int) file2.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        fileInputStream.read(bArr);
        fileInputStream2.read(bArr2);
        fileInputStream.close();
        fileInputStream2.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        xVar.f5982c = new KeyPair(keyFactory.generatePublic(new X509EncodedKeySpec(bArr2)), keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArr)));
        xVar.f5983d = vVar;
        return xVar;
    }

    public static x a(v vVar) throws NoSuchAlgorithmException {
        x xVar = new x();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        xVar.f5982c = keyPairGenerator.genKeyPair();
        xVar.f5983d = vVar;
        return xVar;
    }

    public byte[] a(byte[] bArr) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(1, this.f5982c.getPrivate());
        cipher.update(f5981b);
        return cipher.doFinal(bArr);
    }

    public byte[] a() throws IOException {
        byte[] bArrA = a((RSAPublicKey) this.f5982c.getPublic());
        StringBuilder sb = new StringBuilder(720);
        sb.append(this.f5983d.a(bArrA));
        sb.append(" unknown@unknown");
        sb.append((char) 0);
        return sb.toString().getBytes("UTF-8");
    }

    public void a(File file, File file2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
        fileOutputStream.write(this.f5982c.getPrivate().getEncoded());
        fileOutputStream2.write(this.f5982c.getPublic().getEncoded());
        fileOutputStream.close();
        fileOutputStream2.close();
    }
}

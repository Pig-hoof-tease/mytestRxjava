package com.mytestrxjava.utils.XGUtils;

/**
 * Created by Yomoo on 2017/10/9.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA
{
    private static final String KEY_SHA = "SHA";
    private static final String KEY_SHA1 = "SHA-1";
    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static String byteArrayToHexString(byte[] paramArrayOfByte)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        int i = 0;
        while (i < paramArrayOfByte.length)
        {
            localStringBuffer.append(byteToHexString(paramArrayOfByte[i]));
            i += 1;
        }
        return localStringBuffer.toString();
    }

    private static String byteToHexString(byte paramByte)
    {
        int i = paramByte;
        if (paramByte < 0) {
            i = paramByte + 256;
        }
        paramByte = (byte) (i / 16);
        return hexDigits[paramByte] + hexDigits[(i % 16)];
    }

    public static String encryptSHA(String paramString)
    {
        if (StringUtils.isEmpty(paramString)) {
            return "";
        }
        MessageDigest localMessageDigest = null;
        try {
            localMessageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        localMessageDigest.update(paramString.getBytes());
        return byteArrayToHexString(localMessageDigest.digest());
    }

    public static byte[] encryptSHA(byte[] paramArrayOfByte)
    {
        MessageDigest localMessageDigest = null;
        try {
            localMessageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        localMessageDigest.update(paramArrayOfByte);
        return localMessageDigest.digest();
    }
}

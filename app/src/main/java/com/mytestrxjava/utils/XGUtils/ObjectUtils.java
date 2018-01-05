package com.mytestrxjava.utils.XGUtils;

/**
 * Created by Yomoo on 2017/10/9.
 */

public class ObjectUtils
{
    private ObjectUtils()
    {
        throw new AssertionError();
    }

    public static <V> int compare(V paramV1, V paramV2)
    {
        if (paramV1 == null)
        {
            if (paramV2 == null) {
                return 0;
            }
            return -1;
        }
        if (paramV2 == null) {
            return 1;
        }
        return ((Comparable)paramV1).compareTo(paramV2);
    }

    public static boolean isEquals(Object actual, Object expected)
    {
        return actual == expected || (actual == null ? expected == null : actual.equals(expected));
    }

    public static String nullStrToEmpty(Object paramObject)
    {
        if (paramObject == null) {
            return "";
        }
        if ((paramObject instanceof String)) {
            return (String)paramObject;
        }
        return paramObject.toString();
    }

    public static int[] transformIntArray(Integer[] paramArrayOfInteger)
    {
        int[] arrayOfInt = new int[paramArrayOfInteger.length];
        int i = 0;
        while (i < paramArrayOfInteger.length)
        {
            arrayOfInt[i] = paramArrayOfInteger[i].intValue();
            i += 1;
        }
        return arrayOfInt;
    }

    public static Integer[] transformIntArray(int[] paramArrayOfInt)
    {
        Integer[] arrayOfInteger = new Integer[paramArrayOfInt.length];
        int i = 0;
        while (i < paramArrayOfInt.length)
        {
            arrayOfInteger[i] = Integer.valueOf(paramArrayOfInt[i]);
            i += 1;
        }
        return arrayOfInteger;
    }

    public static long[] transformLongArray(Long[] paramArrayOfLong)
    {
        long[] arrayOfLong = new long[paramArrayOfLong.length];
        int i = 0;
        while (i < paramArrayOfLong.length)
        {
            arrayOfLong[i] = paramArrayOfLong[i].longValue();
            i += 1;
        }
        return arrayOfLong;
    }

    public static Long[] transformLongArray(long[] paramArrayOfLong)
    {
        Long[] arrayOfLong = new Long[paramArrayOfLong.length];
        int i = 0;
        while (i < paramArrayOfLong.length)
        {
            arrayOfLong[i] = Long.valueOf(paramArrayOfLong[i]);
            i += 1;
        }
        return arrayOfLong;
    }
}


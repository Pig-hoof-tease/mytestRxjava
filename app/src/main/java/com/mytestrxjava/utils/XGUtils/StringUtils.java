package com.mytestrxjava.utils.XGUtils;

/**
 * Created by Yomoo on 2017/10/9.
 */


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    public static String capitalizeFirstLetter(String paramString) {
        if (isEmpty(paramString)) {
            return paramString;
        }

        char c = paramString.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c))
                ? paramString
                : new StringBuilder(paramString.length()).append(
                Character.toUpperCase(c))
                .append(paramString.substring(1))
                .toString();
    }

    public static String fullWidthToHalfWidth(String paramString) {
        if (isEmpty(paramString)) {
            return paramString;
        }

        char[] source = paramString.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] =' ';
                // } else if (source[i] == 12290) {
                // source[i] = ‘.‘;
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    public static String getHrefInnerHtml(String paramString) {
        if (isEmpty(paramString)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg,
                Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(paramString);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return paramString;
    }

    public static String getNameByURL(String paramString) {
        return paramString.substring(paramString.lastIndexOf("/") + 1);
    }

    public static String halfWidthToFullWidth(String paramString) {
        if (isEmpty(paramString)) {
            return paramString;
        }

        char[] source = paramString.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] ==' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == ‘.‘) {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    public static String htmlEscapeCharsToString(String paramString) {
        if (isEmpty(paramString)) {
            return paramString;
        }
        return paramString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static boolean isBlank(String paramString) {
        return (paramString == null) || (paramString.trim().length() == 0);
    }

    public static boolean isEmpty(CharSequence paramCharSequence) {
        return (paramCharSequence == null) || (paramCharSequence.length() == 0);
    }

    public static boolean isEquals(String paramString1, String paramString2) {
        return ObjectUtils.isEquals(paramString1, paramString2);
    }

    public static String nullStrToEmpty(Object paramObject) {
        if (paramObject == null) {
            return "";
        }
        if ((paramObject instanceof String)) {
            return (String) paramObject;
        }
        return paramObject.toString();
    }

    public static String utf8Encode(String paramString) {
        if (!isEmpty(paramString) && paramString.getBytes().length != paramString.length()) {
            try {
                return URLEncoder.encode(paramString, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return paramString;
    }

    public static String utf8Encode(String paramString1, String paramString2) {
        if ((!isEmpty(paramString1)) && (paramString1.getBytes().length != paramString1.length())) {
            try {
                paramString1 = URLEncoder.encode(paramString1, "UTF-8");
                return paramString1;
            } catch (UnsupportedEncodingException e) {
            }
            return paramString1;
        }

        return paramString2;
    }
    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }
}


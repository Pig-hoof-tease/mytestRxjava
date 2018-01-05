package com.mytestrxjava.utils;

import android.widget.Toast;

import com.mytestrxjava.MyApplication;

public class ToastUtil {

    private static Toast toast;

    /**
     * 自定义Toast
     *
     * @param message
     */
    public static void showToastShort(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showToastLong(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(),
                    message,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}

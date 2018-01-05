package com.mytestrxjava.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mytestrxjava.entity.BaseEntity;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Yomoo on 2017/9/25.
 */

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {
    private static final String TAG="BaseObserver";
    private Context mContext;
    protected BaseObserver(Context context){
        this.mContext =context.getApplicationContext();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.e(TAG, "error:" + d.toString());
    }

    @Override
    public void onNext(@NonNull BaseEntity<T> tBaseEntity) {
        Log.e(TAG, "error:" + tBaseEntity.toString());
        if (tBaseEntity.isSuccess()){
            T t= tBaseEntity.getData();
            onHandleSuccess(t);
        }else{
            onHandleError(tBaseEntity.getMsg());
        }
    }
    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }


    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}

package com.mytestrxjava.http;

import com.mytestrxjava.utils.ToastUtil;

import butterknife.internal.Utils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yomoo on 2017/9/25.
 */

/***
 * 网络连接
 */
public class RxSchedulers {
    public static <T> ObservableTransformer<T,T> compose(){
        return new ObservableTransformer<T,T>(){

            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {

                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                ToastUtil.showToastShort("网络错误"+disposable.toString());
                            }
                        }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}

package android.com.rxjava.rxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BaseImpl implements Base {

    /**
     * 最简单的使用
     */
    @Override
    public void simpleUse() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("aa");
//                emitter.onError(new Throwable("error"));   //  调用onError
                emitter.tryOnError(new Throwable("tryOnError")); //  调用错误 与onError不用 不传递错误
//                emitter.onComplete(); //  完成的调用
//                上面这三个方法调用都会拦截 下游的传递即:
//                emitter.isDisposed(); 为true了

                emitter.onNext("vv");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("xx", "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.d("xx", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void switchThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                printThreadName("subscribe");
                emitter.onNext("aaa");
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread()) // 测试多个subscribeOn
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        printThreadName("map");
                        return s + "a";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        printThreadName("observe");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void printThreadName(String s) {
        String name = Thread.currentThread().getName();
        Log.d("xx", s + ": printThreadName: " + name);
    }
}

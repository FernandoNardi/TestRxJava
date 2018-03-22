package br.nardi.testrxjava.examples;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nardi on 21/03/2018.
 */

public class Chapter4 {

    private static final String TAG = Chapter4.class.getSimpleName();

    public Chapter4() { }

    public static void print(String message) {
        String threadName = Thread.currentThread().getName();
        Log.d(TAG, threadName + " : " + message);
    }

    public void example1() {
        Observable<Integer> integerObservable = Observable.create(source -> {
            Log.d(TAG, "In subscribe");
            source.onNext(1);
            source.onNext(2);
            source.onNext(3);
            source.onNext(4);
            source.onNext(5);
            source.onComplete();
        });

        Log.d(TAG, "Created Observable");

        Log.d(TAG, "Subscribing to Observable");

        integerObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> {
                    Log.d(TAG, "In next(): " + i);
                });

        Log.d(TAG, "Finished");
    }

    public void example2() {
        Observable<Integer> observable = Observable.create(source -> {
            print("In subscribe");
            source.onNext(1);
            source.onNext(2);
            source.onNext(3);
        });

        observable.subscribeOn(Schedulers.computation())
                .doOnNext(value -> print("(a) : " + value))
                .observeOn(Schedulers.newThread())
                .doOnNext(value -> print("(b) : " + value))
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread()) // This has no effect.
                .doOnNext(value -> print("(c) : " + value))
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> print("(d) : " + value));
    }
}

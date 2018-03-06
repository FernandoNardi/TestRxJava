package br.nardi.testrxjava.examples;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.nardi.testrxjava.models.Car;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nardi on 05/03/2018.
 */

public class Chapter1 {

    private List<Car> cars = new ArrayList<>();
    private static final String TAG = Chapter1.class.getSimpleName();

    public Chapter1() {}

    private void population() {
        cars.add(new Car(1, 1, 70000, 2018, "fiat", "uno"));
        cars.add(new Car(2, 1, 100000, 2015, "fiat", "punto"));
        cars.add(new Car(3, 3, 95000, 2014, "fiat", "bravo"));
        cars.add(new Car(4, 2, 89000, 2017, "fiat", "palio"));
        cars.add(new Car(5, 1, 61000, 2017, "fiat", "strada"));
        cars.add(new Car(6, 3, 110000, 2011, "fiat", "uno"));
        cars.add(new Car(7, 1, 60000, 2009, "fiat", "mobi"));
    }

    private Observable<Car> getBestSellingCarsObservable() {
        population();
        return Observable.fromIterable(cars);
    }

    public void main() {
        Observable<Car> carsObservable = getBestSellingCarsObservable();

        carsObservable.subscribeOn(Schedulers.io())
                .filter(car -> car.type == 1)
                .filter(car -> car.price < 90000)
                .map(car -> car.year + " " + car.make + " " + car.model)
                .distinct()
                .take(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(test -> {
                    Log.d(TAG, "Chapter 1: " + test);
                });
//         .subscribe(new Observer<Car>() {
//             @Override
//             public void onSubscribe(@NonNull Disposable d) {
//                 Log.d(TAG, "In onSubscribe()");
//             }
//
//             @Override
//             public void onNext(@NonNull Car car) {
//                 Log.d(TAG, "In onNext(). Code: " + car.code);
//             }
//
//             @Override
//             public void onError(@NonNull Throwable e) {
//                 Log.d(TAG, "In onError()");
//             }
//
//             @Override
//             public void onComplete() {
//                 Log.d(TAG, "In onComplete()");
//             }
//         });
    }
}

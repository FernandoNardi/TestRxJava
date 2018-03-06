package br.nardi.testrxjava.examples;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.nardi.testrxjava.models.User;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nardi on 05/03/2018.
 */

public class Chapter2 {

    private static final String TAG = Chapter2.class.getSimpleName();

    public Chapter2() {
    }

    private List<User> population() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, true));
        users.add(new User(2, false));
        users.add(new User(3, false));
        users.add(new User(4, true));
        users.add(new User(5, false));
        users.add(new User(6, true));
        users.add(new User(7, true));
        users.add(new User(8, true));
        users.add(new User(9, false));
        users.add(new User(10, true));
        return users;
    }

    public void main() {
        // example 1
        List<User> users1 = population();
        List<User> usersHasBlog1 = getUsersWithABlogDeclarative(users1);
        Log.d(TAG, "Example 1 Chapter 2: " + usersHasBlog1.size());

        // example 2
        List<User> users2 = population();
        List<User> usersHasBlog2 = getUsersWithABlogImperative(users2);
        Log.d(TAG, "Example 2 Chapter 2: " + usersHasBlog2.size());


        // example with RXJava
        List<User> users = population();
        Observable<User> userObservable = getUsersWithABlog(users);
        Log.d(TAG, "Example with RXJava Chapter 2\n");

        userObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "In onSubscribe()");
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "In onNext(). Code: " + user.code);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "In onComplete()");
                    }
                });
    }

    /**
     * Returns all users with a blog.
     * <p>
     * Usando o nosso exemplo, digamos que a chamada user.hasBlog()é uma operação de rede que bloqueia o segmento atual até que uma resposta seja recebida
     * Assim, o método não pode ser chamado a partir do segmento UI e é necessário algum tipo de abordagem que chame esse método de um segmento de fundo.
     * Usando RxJava, a solução para este problema é trivial.
     *
     * @param users the users
     * @return an Observable emitting users that have a blog
     */
    public Observable<User> getUsersWithABlog(List<User> users) {
        return Observable.fromIterable(users)
                .filter(user -> user.hasBlogNetwork())
                .subscribeOn(Schedulers.io());
    }

    /**
     * Returns all users with a blog.
     *
     * @param users the users
     * @return users that have a blog
     */
    public List<User> getUsersWithABlogDeclarative(List<User> users) {
        return users.stream()
                .filter(user -> user.hasBlog())
                .collect(Collectors.toList());
    }

    /**
     * Returns all users with a blog.
     *
     * @param users the users
     * @return users that have a blog
     */
    public List<User> getUsersWithABlogImperative(List<User> users) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (user.hasBlog()) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    /**
     * Push vs. Pull
     */
    public void main2() {
        Log.d(TAG, "Creating Observable.");
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("test_1");
            emitter.onNext("test_2");
            emitter.onComplete();
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext(): " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete()");
            }
        });
        Log.d(TAG, "After subscribing.");
    }


    /**
     * Operator
     */
    public void main3() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onNext(5);
            emitter.onComplete();
        })
                .map(val -> val * 3)
                .filter(val -> val % 2 == 0)
                .subscribe(i -> {
                    Log.d(TAG, "val: " + i);
                });
    }
}

package br.nardi.testrxjava.examples;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.nardi.testrxjava.models.Post;
import br.nardi.testrxjava.models.User;
import br.nardi.testrxjava.models.UserDetail;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nardi on 15/03/2018.
 */

public class Chapter3 {

    private static final String TAG = Chapter3.class.getSimpleName();

    public Chapter3() { }

    private List<Post> population() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("with a single Operator (i.e. just with .filter())..."));
        posts.add(new Post("This design makes reasoning simpler as it guarantees that each..."));
        posts.add(new Post("emitted item"));
        posts.add(new Post("A common real-world example of using .flatMap() is for implementing nested network calls. Say we have an Observable<User>, and for each User, we’d like to make a network call to retrieve that user’s detailed information."));
        posts.add(new Post("specified "));
        posts.add(new Post("However, unlike .map(), the function specified should return an object of type Observable."));
        posts.add(new Post("Created tests"));
        return posts;
    }

    private List<User> populationUsers() {
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

    private List<UserDetail> populationUsersDetail() {
        List<UserDetail> usersDetails = new ArrayList<>();
        usersDetails.add(new UserDetail(1, 30, 2));
        usersDetails.add(new UserDetail(2, 18, 6));
        usersDetails.add(new UserDetail(3, 21, 10));
        usersDetails.add(new UserDetail(4, 55, 8));
        usersDetails.add(new UserDetail(5, 42, 4));
        usersDetails.add(new UserDetail(6, 36, 3));
        usersDetails.add(new UserDetail(7, 36, 9));
        usersDetails.add(new UserDetail(8, 79, 7));
        usersDetails.add(new UserDetail(9, 17, 1));
        usersDetails.add(new UserDetail(10, 20, 5));
        return usersDetails;
    }

    private User getUser(int userId) {
        try {
            new Thread(() -> { }).sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<User> users = populationUsers();
        return users.stream()
                .filter(user -> user.code == userId)
                .findAny()
                .orElse(null);
    }

    private UserDetail getUserDetail(User user) {
        try {
            new Thread(() -> { }).sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<UserDetail> usersDetails = populationUsersDetail();
        return usersDetails.stream()
                .filter(userDetail -> userDetail.userCode == user.code)
                .findAny()
                .orElse(null);
    }

    public void mapWithFilter() {
        Observable<Post> postObservable = Observable.fromIterable(population());

        postObservable.map(post -> post.text)
                .filter(text -> text.length() < 65)
                .subscribe(text -> {
                    // Receive text that is less than 65 characters
                    Log.d(TAG, "mapWithFilter(): " + text);
                });
    }

    public void flatMap() {
        Observable<User> userObservable = Observable.fromCallable(() -> getUser(10));

        Observable<UserDetail> userDetailObservable = userObservable.flatMap(user -> Observable.fromCallable(() -> getUserDetail(user)));

        userDetailObservable.subscribe(userDetail -> {
            // Received user's detail information here
            Log.d(TAG, "flatMap(): " + userDetail.toString());
        });
    }

    public void concatMap() {
        Observable<User> userObservable = Observable.fromCallable(() -> getUser(10));

        Observable<UserDetail> userDetailObservable = userObservable.concatMap(user -> {
            return Observable.fromCallable(() -> getUserDetail(user));
        });

        userDetailObservable.subscribe(userDetail -> {
            // Received user's detail information here
            Log.d(TAG, "flatMap(): " + userDetail.toString());
        });
    }
}

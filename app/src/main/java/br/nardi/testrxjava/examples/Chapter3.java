package br.nardi.testrxjava.examples;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.nardi.testrxjava.models.Post;
import io.reactivex.Observable;

/**
 * Created by Nardi on 15/03/2018.
 */

public class Chapter3 {

    private static final String TAG = Chapter2.class.getSimpleName();

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

    public void mapWithFilter() {
        Observable<Post> postObservable = Observable.fromIterable(population());

        postObservable.map(post -> post.text)
                .filter(text -> text.length() < 65)
                .subscribe(text -> {
                    // Receive text that is less than 65 characters
                    Log.d(TAG, "mapWithFilter(): " + text);
                });
    }


}

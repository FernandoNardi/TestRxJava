package br.nardi.testrxjava.examples;

import android.util.Log;

import org.junit.Test;

import br.nardi.testrxjava.models.User;
import io.reactivex.Observable;

import static org.junit.Assert.*;

/**
 * Created by Nardi on 07/03/2018.
 */
public class Chapter2Test {

    @Test
    public void getUserWithId() {
        User user = new Chapter2().getUserWithId(1);
        user.hasBlog();
    }

    @Test
    public void just() throws Exception {
        new Chapter2().just();
    }

    @Test
    public void fromArray() throws Exception {
        new Chapter2().fromArray();
    }

    @Test
    public void fromIterable() throws Exception {
        new Chapter2().fromIterable();
    }

    @Test
    public void range() throws Exception {
        new Chapter2().range();
    }

    @Test
    public void fromCallable() {
        new Chapter2().fromCallable();
    }

    @Test
    public void defer() {
        new Chapter2().defer();
    }

    @Test
    public void lazyObservable() {
        new Chapter2().lazyObservable();
    }

    @Test
    public void notLazyObservable() {
        new Chapter2().notLazyObservable();
    }
}
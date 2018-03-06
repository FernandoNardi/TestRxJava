package br.nardi.testrxjava.models;

/**
 * Created by Nardi on 05/03/2018.
 */

public class User {

    public int code;
    private Boolean hasBlog;

    public User(int code, Boolean hasBlog) {
        this.code = code;
        this.hasBlog = hasBlog;
    }

    public Boolean hasBlog() {
        return hasBlog;
    }

    public Boolean hasBlogNetwork() {
        try {
            new Thread(() -> {}).sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasBlog;
    }
}

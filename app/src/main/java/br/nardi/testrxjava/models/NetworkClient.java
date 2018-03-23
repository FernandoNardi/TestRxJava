package br.nardi.testrxjava.models;

import java.util.Random;

/**
 * Created by Nardi on 22/03/2018.
 */

public class NetworkClient {

    Random random = new Random();

    public User2 fetchUser(String userName) {
        randomSleep();
        return new User2(userName);
    }

    void randomSleep() {
        try {
            Thread.sleep(random.nextInt(3) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

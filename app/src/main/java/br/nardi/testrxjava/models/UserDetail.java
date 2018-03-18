package br.nardi.testrxjava.models;

/**
 * Created by Nardi on 15/03/2018.
 */

public class UserDetail {

    public int code;
    public int age;
    public int userCode;

    public UserDetail(int code, int age, int userCode) {
        this.code = code;
        this.age = age;
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "code=" + code +
                ", age=" + age +
                ", userCode=" + userCode +
                '}';
    }
}

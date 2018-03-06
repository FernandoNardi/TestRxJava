package br.nardi.testrxjava.models;

/**
 * Created by Nardi on 05/03/2018.
 */

public class Car {

    public int code;
    public int type;
    public float price;
    public int year;
    public String make;
    public String model;

    public Car(int code, int type, float price, int year, String make, String model) {
        this.code = code;
        this.type = type;
        this.price = price;
        this.year = year;
        this.make = make;
        this.model = model;
    }
}

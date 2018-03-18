package br.nardi.testrxjava.examples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nardi on 15/03/2018.
 */
public class Chapter3Test {

    @Test
    public void mapWithFilter() {
        new Chapter3().mapWithFilter();
    }

    @Test
    public void flatMap() {
        new Chapter3().flatMap();
    }
}
package br.nardi.testrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.nardi.testrxjava.examples.Chapter1;
import br.nardi.testrxjava.examples.Chapter2;
import br.nardi.testrxjava.examples.Chapter3;
import br.nardi.testrxjava.examples.Chapter4;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Test Chapter 1
         */
        // new Chapter1().main();

        /**
         * Test Chapter 2
         */
        //new Chapter2().main();
        //new Chapter2().main2();
        //new Chapter2().main3();
//        new Chapter2().main4();

        new Chapter4().example3_1();

    }
}

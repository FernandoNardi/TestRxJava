package br.nardi.testrxjava.examples;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nardi on 28/03/2018.
 */

public class Chapter5 {

    private static final String TAG = Chapter5.class.getSimpleName();

     /**
      * Writes {@code text} to the file system.
      *
      * @param context a Context
      * @param filename the name of the File
      * @param text the String of text to write
      * @return true if the text was successfully written, otherwise, false
      */
    public boolean writeTextToFile1(Context context, String filename, String text) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Writes {@code text} to the filesystem.
     *
     * @param context a Context
     * @param filename the name of the File
     * @param text the String of text to write
     * @return An Observable emitting a boolean indicating whether or not the text was successfully written.
     */
    public Observable<Boolean> writeTextToFile2(Context context, String filename, String text) {
        return Observable.fromCallable(() -> {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
            return true;
        }).subscribeOn(Schedulers.io());
    }

    /**
     * Writes {@code text} to the filesystem.
     *
     * @param context a Context
     * @param filename the name of the File
     * @param text the String of text to write
     * @return A Completable
     */
    public Completable writeTextToFile3(Context context, String filename, String text) {
        return Completable.fromAction(() -> {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        }).subscribeOn(Schedulers.io());
    }
}

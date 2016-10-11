package com.petterpet.trackmailtaskone.utils;

/**
 * Created by obabichev on 11/10/16.
 */

public class ThreadUtils {

    public static void sleep(long millis) {
        long timeStart = System.currentTimeMillis();
        while (System.currentTimeMillis() < timeStart + millis) {
            try {
                Thread.sleep(timeStart + millis - System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

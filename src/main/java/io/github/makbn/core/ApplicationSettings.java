package io.github.makbn.core;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class ApplicationSettings {
    private static int defaultLimitationNumber = 25;
    private static int defaultFetchTimerDelay = 10000;
    private static int defaultFetchTimerInterval = 120000;
    private static int defaultBodyMaxLength = 1500;
    private static String defaultUsername = "JustinTrudeau";

    public static int getDefaultLimitationNumber() {
        return defaultLimitationNumber;
    }


    public static String getDefaultUsername() {
        return defaultUsername;
    }

    public static long getFetchTimerDelay() {
        return defaultFetchTimerDelay;
    }

    public static long getFetchTimerInterval() {
        return defaultFetchTimerInterval;
    }

    public static int getBodyMaxLength() {
        return defaultBodyMaxLength;
    }
}

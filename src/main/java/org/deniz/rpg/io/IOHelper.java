package org.deniz.rpg.io;

public class IOHelper {
    private static final int WAIT_MILLISECOND = 500;
    private static IOHelper self;

    private IOHelper() {

    }

    public static IOHelper getInstance() {
        if (self == null) {
            self = new IOHelper();
        }
        return self;
    }

    public void sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(String text) {
        System.out.println(text);
    }

    public void writeAndSleep(String text, int milliSecond) {
        System.out.println(text);
        sleep(milliSecond);
    }

    public void writeAndSleep(String text) {
        System.out.println(text);
        sleep(WAIT_MILLISECOND);
    }

}

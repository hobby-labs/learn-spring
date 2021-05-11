package com.github.hobbylabs.lettuceandthreadnum.thread;

public class SomeThread extends Thread {
    private long counter = 0;
    public void run() {
        while(true) {
            // busy loop...
            ++counter;
//            try {
//                synchronized (this) {
//                    wait();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

package me.gleeming.galaxy.thread.runnable;

import lombok.SneakyThrows;

/**
 * Used to handle errors that are thrown
 * while using a runnable.
 */
public abstract class SafeRunnable implements Runnable {

    public abstract void runSafe() throws Exception;

    @Override @SneakyThrows
    public void run() {
        runSafe();
    }
}

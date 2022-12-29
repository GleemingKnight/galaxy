package me.gleeming.galaxy.thread.runnable;

import lombok.SneakyThrows;

/**
 * Used to handle errors that are thrown
 * while using a runnable.
 */
public abstract class ErrorRunnable implements Runnable {

    public void runSafe() throws Exception {
        // Meant to be implemented
    }

    public void runIgnored() {
    }

    @Override @SneakyThrows
    public void run() {
        runSafe();

        try {
            runIgnored();
        } catch (Exception ignored) {
            // The exception is ignored.
        }
    }

    /**
     * Creates a new error runnable
     * that will ignore thrown errors.
     * @param runnable Runnable
     */
    public static ErrorRunnable of(Runnable runnable) {
        return new ErrorRunnable() {
            @Override
            public void runIgnored() {
                runnable.run();
            }
        };
    }
}

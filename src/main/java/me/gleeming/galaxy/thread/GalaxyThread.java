package me.gleeming.galaxy.thread;

/**
 * A utility class used to create
 * infinitely repeating threads.
 */
public abstract class GalaxyThread extends Thread {

    public GalaxyThread() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try { tick(); }
            catch(Exception exception) { exception(exception); }
        }
    }

    /**
     * Called on each tick of the thread.
     */
    public abstract void tick();

    /**
     * Called whenever an exception is caught.
     * @param exception Exception
     */
    public void exception(Exception exception) {
        exception.printStackTrace();
    }

}

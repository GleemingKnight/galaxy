package me.gleeming.galaxy.log;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LogType {

    /**
     * A normal log that is providing useful information.
     */
    INFO(0),

    /**
     * A log thrown whenever an error is caught.
     */
    ERROR(1),

    /**
     * A log type that should only be used
     * in order to debug the actual galaxy
     * framework.
     */
    DEBUG(2);

    private final int priority;

}

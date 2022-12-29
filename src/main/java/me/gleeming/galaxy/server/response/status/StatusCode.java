package me.gleeming.galaxy.server.response.status;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusCode {

    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request");

    /**
     * The numerical code of the status.
     */
    private final int code;

    /**
     * The description of the status.
     */
    private final String message;

    /**
     * Converts the status code into the format
     * that the http client will recognize.
     * @return The formatted status code.
     */
    public String getStatus() {
        return code + " " + message;
    }

}

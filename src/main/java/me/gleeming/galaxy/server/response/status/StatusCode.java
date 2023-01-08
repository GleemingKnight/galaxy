package me.gleeming.galaxy.server.response.status;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusCode {

    /**
     * Indicates that the request was successful
     * and has been handled accordingly.
     */
    OK(200, "OK"),

    /**
     * Indicates that the request could not be handled
     * due to an error made by the client.
     */
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

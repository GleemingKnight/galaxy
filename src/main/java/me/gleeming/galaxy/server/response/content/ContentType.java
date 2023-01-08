package me.gleeming.galaxy.server.response.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContentType {

    /**
     * Signals that a response contains
     * text formatted as json.
     */
    JSON("application/json"),

    /**
     * Signals that a response contains
     * text formatted as xml.
     */
    XML("application/xml");

    /**
     * The usage according to the http protocol.
     */
    private final String usage;

}

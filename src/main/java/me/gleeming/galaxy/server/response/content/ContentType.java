package me.gleeming.galaxy.server.response.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ContentType {

    JSON("application/json"), XML("application/xml");

    private final String usage;

}

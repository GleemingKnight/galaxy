package me.gleeming.galaxy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.gleeming.galaxy.config.GalaxyConfig;
import me.gleeming.galaxy.log.LogHandler;
import me.gleeming.galaxy.server.GalaxyServer;

@RequiredArgsConstructor
@Getter
public class Galaxy {

    /**
     * The main instance of the provided
     * galaxy configuration class.
     */
    private final GalaxyConfig config;

    /**
     * The main instance of the galaxy server.
     */
    private GalaxyServer server;

    /**
     * The main instance of the log handler.
     */
    private LogHandler logHandler;

    /**
     * Starts and enables the galaxy application.
     */
    public void start() {
        server = new GalaxyServer(this);
        server.run();
        logHandler = new LogHandler();
    }

    /**
     * Shutdowns the program and exits cleanly.
     */
    public void shutdown() {
        server.close();
    }

}
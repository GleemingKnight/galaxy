package me.gleeming.galaxy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.gleeming.galaxy.config.GalaxyConfig;
import me.gleeming.galaxy.event.EventHandler;
import me.gleeming.galaxy.initialize.InitializeHandler;
import me.gleeming.galaxy.initialize.controller.ControllerHandler;
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
     * The main instance of the initialize handler.
     */
    private InitializeHandler initializeHandler;

    /**
     * The main instance of the event handler.
     */
    private EventHandler eventHandler;

    /**
     * The main instance of the controller handler.
     */
    private ControllerHandler controllerHandler;

    /**
     * Starts and enables the galaxy application.
     */
    public void start() {
        server = new GalaxyServer(this);
        server.run();

        logHandler = new LogHandler(this);
        initializeHandler = new InitializeHandler(this);
        eventHandler = new EventHandler(this);
        controllerHandler = new ControllerHandler(this);
        controllerHandler.a();

        initializeHandler.initialize();
    }

    /**
     * Shutdowns the program and exits cleanly.
     */
    public void shutdown() {
        server.close();
    }

    /**
     * @Controller
     * public class BananaController {
     *
     *  @Populate
     *  private final BananaService service;
     *
     *
     *  @GET
     *  public Banana getBanana() {
     *
     *  }
     * }
     */
}
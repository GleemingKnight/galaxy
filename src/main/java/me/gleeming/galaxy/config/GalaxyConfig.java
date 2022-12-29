package me.gleeming.galaxy.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.gleeming.galaxy.Galaxy;

@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true, fluent = true)
@Getter @Setter
public class GalaxyConfig {

    /**
     * The main class of the star application.
     */
    private final Class<?> mainClass;

    /**
     * The amount of threads that are working
     * on accepting clients and handling
     * their requests. Increasing this will
     * improve performance when handling
     * a lot of traffic.
     */
    private int clientThreads = 5;

    /**
     * The port that the server will use
     * to bind to and run the server from.
     */
    private int port = 8080;

    /**
     * Initializes a new galaxy application
     * using the current configuration.
     * @return Galaxy Application
     */
    public Galaxy build() {
        return new Galaxy(this);
    }


    /**
     * Initializes a new galaxy configuration.
     * @param obj An object of your main class
     * @return Galaxy Config
     */
    public static GalaxyConfig of(Object obj) {
        return of(obj.getClass());
    }
}

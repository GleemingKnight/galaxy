package me.gleeming.galaxy.initialize.controller;

import lombok.RequiredArgsConstructor;
import me.gleeming.galaxy.Galaxy;
import me.gleeming.galaxy.event.EventListener;
import me.gleeming.galaxy.event.ExampleEvent;

@RequiredArgsConstructor
public class ControllerHandler {

    /**
     * An instanceof of the main galaxy class.
     */
    private final Galaxy galaxy;

    public void a() {
        galaxy.getEventHandler().register(this);
    }

    @EventListener
    public void banana(ExampleEvent event) {

    }

}

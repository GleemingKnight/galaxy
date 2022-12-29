package com.example;

import me.gleeming.galaxy.config.GalaxyConfig;

public class Example {

    public Example() {

    }

    public static void main(String[] args) {
        GalaxyConfig.of(Example.class).build().start();
    }

}

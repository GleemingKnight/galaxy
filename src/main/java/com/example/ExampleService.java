package com.example;

import lombok.Getter;
import me.gleeming.galaxy.initialize.populate.Populate;
import me.gleeming.galaxy.initialize.service.Service;

@Service @Getter
public class ExampleService {

    private final String banana = "a";

    @Populate
    private ExampleServiceTwo exampleServiceTwo;

    @Populate
    private Object obj;

}

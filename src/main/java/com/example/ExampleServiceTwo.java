package com.example;

import lombok.Getter;
import me.gleeming.galaxy.initialize.populate.Populate;
import me.gleeming.galaxy.initialize.service.Service;

@Service @Getter
public class ExampleServiceTwo {

    @Populate
    private ExampleService exampleService;

    private final String apple = "b";


}

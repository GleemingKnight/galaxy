package me.gleeming.galaxy.initialize.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation used to denote a class
 * as being a http controller.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {

    /**
     * The path of the controller mapping.
     * @return Path as provided in the URL.
     */
    String path() default "";

}

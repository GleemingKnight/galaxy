package me.gleeming.galaxy.initialize;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.gleeming.galaxy.Galaxy;
import me.gleeming.galaxy.initialize.controller.Controller;
import me.gleeming.galaxy.initialize.populate.Populate;
import me.gleeming.galaxy.initialize.service.Service;
import me.gleeming.galaxy.util.Reflection;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles the auto initialization of classes.
 */
@RequiredArgsConstructor
public class InitializeHandler {

    /**
     * An instance of the main class.
     */
    private final Galaxy galaxy;

    /**
     * Contains the services that have been instantiated.
     */
    private final Map<Class<?>, Object> services = new HashMap<>();

    /**
     * The fields awaiting the class to be instantiated to populate.
     */
    private final Map<Class<?>, List<Map.Entry<Object, Field>>> awaitingPopulation = new HashMap<>();

    /**
     * Retrieves all the classes using the package name
     * and then initalizes them.
     */
    public void initialize() {
        List<Class<?>> classes = Reflection.findClasses(galaxy.getConfig().mainClass().getPackageName());

        classes.stream()
                .filter(cl -> cl.getAnnotation(Service.class) != null)
                .forEach(this::initialize);

        classes.stream()
                .filter(cl -> cl.getAnnotation(Controller.class) != null)
                .forEach(this::initialize);
    }

    /**
     * Retrieves the fields that need to be populated.
     * @param cl The class in reference.
     * @return The fields needing to be populated.
     */
    public List<Field> getPopulating(Class<?> cl) {
        return Arrays.stream(cl.getDeclaredFields())
                .filter(field -> field.getAnnotation(Populate.class) != null)
                .collect(Collectors.toList());
    }

    /**
     * Initializes the class using reflection
     * and also auto populates any of the
     * fields that require population.
     * @param cl The class being initialized.
     */
    @SneakyThrows
    public void initialize(Class<?> cl) {
        Object obj = cl.getConstructor().newInstance();
        services.put(cl, obj);

        getPopulating(cl).forEach(field -> {
            if (services.containsKey(field.getType())) {
                Reflection.setField(obj, field, services.get(field.getType()));
                return;
            }

            List<Map.Entry<Object, Field>> awaiting = awaitingPopulation.getOrDefault(field.getType(), new ArrayList<>());
            awaiting.add(new AbstractMap.SimpleEntry<>(obj, field));
            awaitingPopulation.put(field.getType(), awaiting);
        });

        if (awaitingPopulation.containsKey(cl)) {
            awaitingPopulation.remove(cl).forEach(entry ->
                    Reflection.setField(entry.getKey(), entry.getValue(), obj));
        }
    }

}

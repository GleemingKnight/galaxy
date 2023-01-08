package me.gleeming.galaxy.event;

import lombok.RequiredArgsConstructor;
import me.gleeming.galaxy.Galaxy;

import java.lang.reflect.Method;
import java.util.*;

@RequiredArgsConstructor
public class EventHandler {

    /**
     * An instance of the main galaxy class.
     */
    private final Galaxy galaxy;

    /**
     * The methods that are awaiting events.
     */
    private final Map<Class<?>, List<Map.Entry<Object, Method>>> methods = new HashMap<>();

    /**
     * Registers all methods that are listening
     * for an event in the denoted object.
     * @param object The object being registered.
     */
    public void register(Object object) {
        Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(EventListener.class) != null)
                .forEach(method -> register(object, method));
    }

    /**
     * Attempts to register the method as
     * an event listener.
     * @param object The parent class of the method.
     * @param method The event listener.
     */
    public void register(Object object, Method method) {
        EventListener eventListener = method.getAnnotation(EventListener.class);

        if (eventListener == null) {
            // TODO: use log handler to cry about this
            return;
        }

        if (method.getParameters().length == 0) {
            // TODO: cry about this
            return;
        }

        Class<?> eventType = method.getParameters()[0].getType();

        if (eventType.getAnnotation(Event.class) == null) {
            // TODO: cry about this
            return;
        }

        List<Map.Entry<Object, Method>> methods = this.methods.getOrDefault(eventType, new ArrayList<>());
        methods.add(new AbstractMap.SimpleEntry<>(object, method));
        this.methods.put(eventType, methods);
    }
}

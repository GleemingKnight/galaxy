package me.gleeming.galaxy.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class Reflection {

    /**
     * Uses reflection to update the field while
     * ignoring all accessibility options.
     * @param parent The parent class of the field.
     * @param field The field that will be updated.
     * @param object The object to set as the value of the field.
     */
    @SneakyThrows
    public static void setField(Object parent, Field field, Object object) {
        field.setAccessible(true);
        field.set(parent, object);
    }

    /**
     * Finds all the classes within the given
     * package name.
     * @param packageName The package name.
     * @return The classes that were found.
     */
    public static List<Class<?>> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());
    }

    /**
     * Loads the class by combining the class name with the package.
     * @param className The name of the class.
     * @param packageName The name of the package.
     * @return The loaded class.
     */
    @SneakyThrows
    private static Class<?> getClass(String className, String packageName) {
        return Class.forName(packageName + "."
                + className.substring(0, className.lastIndexOf('.')));
    }
}

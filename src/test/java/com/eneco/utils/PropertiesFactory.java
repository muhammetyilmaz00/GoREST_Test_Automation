package com.eneco.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFactory {

    // Cache for loaded properties files
    private static final Map<String, Properties> propertiesCache = new HashMap<>();

    /**
     * Load a properties file and cache it to avoid reloading multiple times.
     * @param filePath The path to the properties file.
     * @return Properties object corresponding to the given file path.
     */
    public static Properties loadProperties(String filePath) throws IOException {
        if (propertiesCache.containsKey(filePath)) {
            return propertiesCache.get(filePath);
        }

        // Load and cache properties file
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            propertiesCache.put(filePath, properties);
            return properties;
        }
    }

    /**
     * Retrieves a property value from a given properties file.
     * @param filePath The path to the properties file.
     * @param key The key whose corresponding value is to be returned.
     * @return The value corresponding to the key, or null if not found.
     */
    public static String getProperty(String filePath, String key) {
        try {
            Properties properties = loadProperties(filePath);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Could not load properties file: " + filePath + ". Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a property value from the default application properties file.
     * @param key The key whose corresponding value is to be returned.
     * @return The value corresponding to the key, or null if not found.
     */
    public static String getPropertyFromApplication(String key) {
        String defaultPath = "src/test/resources/config/application.properties";
        return getProperty(defaultPath, key);
    }

    public static String getPropertyFromConfiguration(String key) {
        String defaultPath = "configuration.properties";
        return getProperty(defaultPath, key);
    }
}

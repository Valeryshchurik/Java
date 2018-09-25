package com.shchuryk.resource;
import java.util.ResourceBundle;
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("text");
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
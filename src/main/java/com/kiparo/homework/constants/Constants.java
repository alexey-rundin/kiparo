package com.kiparo.homework.constants;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public final class Constants {
    public static final String BASE_URL = "https://api2.kiparo.com/static/";
    public static final String JSON_FILE = "it_news.json";
    public static final String XML_FILE = "it_news.xml";

    private Constants() {
        throw new IllegalStateException("Constants class");
    }
}

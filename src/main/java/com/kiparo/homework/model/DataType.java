package com.kiparo.homework.model;

import com.kiparo.homework.constants.Constants;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public enum DataType {
    JSON, XML;

    public static String getFileUrl(DataType dataType) {
        switch (dataType) {
            case XML: return Constants.BASE_URL + Constants.XML_FILE;
            case JSON: return Constants.BASE_URL + Constants.JSON_FILE;
            default: throw new IllegalArgumentException("Incorrect DataType passed.");
        }
    }

    public static String getFileName(DataType dataType) {
        switch (dataType) {
            case XML: return Constants.XML_FILE;
            case JSON: return Constants.JSON_FILE;
            default: throw new IllegalArgumentException("Incorrect DataType passed.");
        }
    }
}

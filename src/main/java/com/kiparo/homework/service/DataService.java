package com.kiparo.homework.service;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public interface DataService {

    void parseAndSaveJSONData();

    void parseAndSaveXMLData();

    void printAllNews();

    void findAndPrintNewsByKeyword(String keyword);
}

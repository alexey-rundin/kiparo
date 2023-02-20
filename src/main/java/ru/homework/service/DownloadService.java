package ru.homework.service;

import java.io.IOException;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public interface DownloadService {
    void downloadFile(String url, String fileName) throws IOException;
}

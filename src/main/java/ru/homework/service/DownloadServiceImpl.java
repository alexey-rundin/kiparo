package ru.homework.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class DownloadServiceImpl implements DownloadService {
    @Override
    public void downloadFile(String urlString, String fileName) throws IOException {
        URL url = new URL(urlString);
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream())) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
        }
    }
}

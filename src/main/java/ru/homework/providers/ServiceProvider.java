package ru.homework.providers;

import ru.homework.service.DataService;
import ru.homework.service.DownloadService;
import ru.homework.service.FindService;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public interface ServiceProvider {
    DownloadService getDownloadService();

    DataService getDataService();

    FindService getFindService(DataService dataService);
}

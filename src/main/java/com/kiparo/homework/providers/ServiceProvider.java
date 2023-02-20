package com.kiparo.homework.providers;

import com.kiparo.homework.service.FindService;
import com.kiparo.homework.service.DataService;
import com.kiparo.homework.service.DownloadService;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public interface ServiceProvider {
    DownloadService getDownloadService();

    DataService getDataService();

    FindService getFindService(DataService dataService);
}

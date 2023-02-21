package com.kiparo.homework.providers;

import com.kiparo.homework.service.DataService;
import com.kiparo.homework.service.DataServiceImpl;
import com.kiparo.homework.service.DownloadService;
import com.kiparo.homework.service.DownloadServiceImpl;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class ServiceProviderImpl implements ServiceProvider {
    @Override
    public DownloadService getDownloadService() {
        return new DownloadServiceImpl();
    }

    @Override
    public DataService getDataService() {
        return new DataServiceImpl();
    }
}

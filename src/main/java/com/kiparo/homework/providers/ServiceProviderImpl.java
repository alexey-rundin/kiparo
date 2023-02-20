package com.kiparo.homework.providers;

import com.kiparo.homework.service.*;
import ru.homework.kiparo.service.*;
import ru.homework.service.*;

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

    @Override
    public FindService getFindService(DataService dataService) {
        return new FindServiceImpl(dataService);
    }
}

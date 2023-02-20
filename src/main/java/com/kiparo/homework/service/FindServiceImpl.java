package com.kiparo.homework.service;

import com.kiparo.homework.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class FindServiceImpl implements FindService {
    private final DataService dataService;

    public FindServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public List<NewsItem> findByKeyword(String keyword) {
        List<NewsItem> newsData = dataService.getNewsData();

        List<NewsItem> filteredData = new ArrayList<>();
        newsData.forEach(item ->
                item.getKeywordsList()
                        .stream()
                        .filter(keyItem -> keyItem.equals(keyword))
                        .map(keyItem -> item)
                        .forEach(filteredData::add));

        return filteredData;
    }
}

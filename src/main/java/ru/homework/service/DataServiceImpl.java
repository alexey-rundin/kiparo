package ru.homework.service;

import ru.homework.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class DataServiceImpl implements DataService {
    private final List<NewsItem> newsItemsData = new ArrayList<>();

    @Override
    public List<NewsItem> getNewsData() {
        if (!this.newsItemsData.isEmpty()) {
            return this.newsItemsData;
        }
        return new ArrayList<>();
    }

    @Override
    public void saveNewsData(List<NewsItem> newsData) {
        this.newsItemsData.addAll(newsData);
    }
}

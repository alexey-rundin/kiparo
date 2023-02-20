package com.kiparo.homework.service;

import com.kiparo.homework.model.NewsItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class DataServiceImpl implements DataService {
    private final List<NewsItem> newsItemsData = new ArrayList<>();

    @Override
    public List<NewsItem> getNewsData() {
        if (!this.newsItemsData.isEmpty()) {
            return this.newsItemsData
                    .stream()
                    .sorted(Comparator.comparingInt(NewsItem::getId))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void saveNewsData(List<NewsItem> newsData) {
        this.newsItemsData.addAll(newsData);
    }
}

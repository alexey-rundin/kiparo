package ru.homework.service;

import ru.homework.model.NewsItem;

import java.util.List;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public interface DataService {
    List<NewsItem> getNewsData();
    void saveNewsData(List<NewsItem> newsData);
}

package com.kiparo.homework.service;

import com.google.gson.Gson;
import com.kiparo.homework.model.DataType;
import com.kiparo.homework.model.News;
import com.kiparo.homework.model.NewsItem;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class DataServiceImpl implements DataService {
    private final List<NewsItem> newsItemsData = new ArrayList<>();

    @Override
    public void parseAndSaveJSONData() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(DataType.getFileName(DataType.JSON))) {
            News news = gson.fromJson(reader, News.class);
            this.newsItemsData.addAll(news.getNewsItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseAndSaveXMLData() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(News.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();

            File file = new File(DataType.getFileName(DataType.XML));
            if (file.isFile()) {
                News news = (News) um.unmarshal(file);
                this.newsItemsData.addAll(news.getNewsItems());
            } else {
                throw new FileNotFoundException("Файл не существует");
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAllNews() {
        if (!this.newsItemsData.isEmpty()) {
            print(this.newsItemsData);
        }
    }

    @Override
    public void findAndPrintNewsByKeyword(String keyword) {
        if (!keyword.isEmpty()) {
            List<NewsItem> filteredData = new ArrayList<>();
            this.newsItemsData.forEach(item ->
                    item.getKeywords()
                            .stream()
                            .filter(keyItem -> keyItem.equals(keyword))
                            .map(keyItem -> item)
                            .forEach(filteredData::add));
            if (!filteredData.isEmpty()) {
                print(filteredData);
            } else {
                System.out.println("По ключевому слову '" + keyword + "' новостей не найдено.");
            }
        } else {
            System.out.println("Ключевое слово не должно быть пустым");
        }
    }

    private void print(List<NewsItem> printItems) {
        if (!printItems.isEmpty()) {
            for (NewsItem item : printItems) {
                System.out.println("-------------------------------");
                System.out.println("ID: " + item.getId());
                System.out.println("Дата: " + item.getDate());
                System.out.println("Заголовок: " + item.getTitle());
                System.out.println("Описание: " + item.getDescription());
                System.out.println("Поисковые теги: " + item.getKeywords());
            }
        } else {
            System.out.println("Что-то пошло не так. Данные отсутствуют.");
        }
    }
}

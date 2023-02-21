package com.kiparo.homework.service;

import com.google.gson.Gson;
import com.kiparo.homework.model.DataType;
import com.kiparo.homework.model.ItNews;
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
            ItNews itNews = gson.fromJson(reader, ItNews.class);
            this.newsItemsData.addAll(itNews.getNewsItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseAndSaveXMLData() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ItNews.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();

            File file = new File(DataType.getFileName(DataType.XML));
            if (file.isFile()) {
                ItNews itNews = (ItNews) um.unmarshal(file);
                this.newsItemsData.addAll(itNews.getNewsObj().getNewsItems());
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
        List<NewsItem> filteredData = new ArrayList<>();
        this.newsItemsData.forEach(item ->
                item.getKeywordsList()
                        .stream()
                        .filter(keyItem -> keyItem.equals(keyword))
                        .map(keyItem -> item)
                        .forEach(filteredData::add));
        if (!filteredData.isEmpty()) {
            print(filteredData);
        }
    }

    private void print(List<NewsItem> printItems) {
        if (!printItems.isEmpty()) {
            for (NewsItem item : printItems) {
                System.out.println("-------------------------------");
                System.out.println("ID: " + item.getId());
                System.out.println("Date: " + item.getDate());
                System.out.println("Title: " + item.getTitle());
                System.out.println("Description: " + item.getDescription());
            }
        } else {
            System.out.println("Что-то пошло не так. Данные отсутствуют.");
        }
    }
}

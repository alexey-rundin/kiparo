package com.kiparo.homework;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import com.kiparo.homework.model.DataType;
import com.kiparo.homework.model.ItNews;
import com.kiparo.homework.model.NewsItem;
import com.kiparo.homework.providers.ServiceProvider;
import com.kiparo.homework.providers.ServiceProviderImpl;
import com.kiparo.homework.service.DataService;
import com.kiparo.homework.service.DownloadService;
import com.kiparo.homework.service.FindService;

import java.io.*;
import java.util.List;
import java.util.Scanner;


/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class Main {
    private static final ServiceProvider serviceProvider = new ServiceProviderImpl();
    private static final DownloadService downloadService = serviceProvider.getDownloadService();
    private static final DataService dataService = serviceProvider.getDataService();
    private static final FindService findService = serviceProvider.getFindService(dataService);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Нажмите 1 что-бы скачать JSON, 2 - XML: ");
        int selectedFileType = scanner.nextInt();

        switch (selectedFileType) {
            case 1: {
                System.out.println("Выбрано скачивание JSON");
                try {
                    System.out.println("Скачиваем JSON");
                    downloadService.downloadFile(DataType.getFileUrl(DataType.JSON), DataType.getFileName(DataType.JSON));

                    System.out.println("Сохраняем полученные данные");
                    saveJSONDataFromFile();
                    System.out.println("Полученные данные успешно сохранены.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case 2: {
                System.out.println("Выбрано скачивание XML");
                try {
                    System.out.println("Скачиваем XML");
                    downloadService.downloadFile(DataType.getFileUrl(DataType.XML), DataType.getFileName(DataType.XML));

                    System.out.println("Сохраняем полученные данные");
                    saveXMLDataFromFile();
                    System.out.println("Полученные данные успешно сохранены.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                System.out.println("Введите 1 или 2.");
        }


        System.out.print("1 - вывести все новости, 2 - поиск по keyword. Введите 1 или 2: ");
        int selector = scanner.nextInt();
        switch (selector) {
            case 1: {
                System.out.println("Выводим все новости");
                printAllNews();
                break;
            }
            case 2: {
                System.out.println("Поиск по keyword");

                System.out.println("Временно для поиска по ключевому слову используется слово: 'apple'");
                List<NewsItem> appleKeywordData = findService.findByKeyword("apple");
                if (!appleKeywordData.isEmpty()) {
                    for (NewsItem item : appleKeywordData) {
                        System.out.println("Title: " + item.getTitle());
                        System.out.println("Description: " + item.getDescription());
                        for (String keyword : item.getKeywordsList()) {
                            System.out.println("Keyword: " + keyword);
                        }
                    }
                }

                break;
            }
            default:
                System.out.println("Введите 1 или 2.");
        }

    }

    private static void saveJSONDataFromFile() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(DataType.getFileName(DataType.JSON))) {
            ItNews itNews = gson.fromJson(reader, ItNews.class);
            dataService.saveNewsData(itNews.getNewsItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveXMLDataFromFile() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ItNews.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();

            File file = new File(DataType.getFileName(DataType.XML));
            if (file.isFile()) {
                ItNews itNews = (ItNews) um.unmarshal(file);
                dataService.saveNewsData(itNews.getNewsObj().getNewsItems());
            } else {
                throw new FileNotFoundException("Файл не существует");
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAllNews() {
        List<NewsItem> newsData = dataService.getNewsData();

        if (!newsData.isEmpty()) {
            for (NewsItem item : newsData) {
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

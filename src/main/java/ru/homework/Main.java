package ru.homework;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import ru.homework.model.DataType;
import ru.homework.model.ItNews;
import ru.homework.model.Keywords;
import ru.homework.model.NewsItem;
import ru.homework.providers.ServiceProvider;
import ru.homework.providers.ServiceProviderImpl;
import ru.homework.service.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class Main {
    public static void main(String[] args) {
        ServiceProvider serviceProvider = new ServiceProviderImpl();
        DownloadService downloadService = serviceProvider.getDownloadService();
        DataService dataService = serviceProvider.getDataService();
        FindService findService = serviceProvider.getFindService(dataService);
        try {
            downloadService.downloadFile(DataType.getFileUrl(DataType.XML), DataType.getFileName(DataType.XML));
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Нажмите 1 что-бы скачать JSON, 2 - XML: ");
//        int enteredValue = scanner.nextInt();
//        fileTypeSelector(enteredValue);
//
//        System.out.print("1 - вывести все новости, 2 - поиск по keyword. Введите 1 или 2: ");
//        int selector = scanner.nextInt();
//        switch (selector) {
//            case 1: {
//                System.out.println("Выводим все новости");
//                break;
//            }
//            case 2: {
//                System.out.println("Поиск по keyword");
//                break;
//            }
//            default:
//                System.out.println("Введите 1 или 2.");
//        }


        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ItNews.class);
            Unmarshaller um = jaxbContext.createUnmarshaller();

            File file = new File(DataType.getFileName(DataType.XML));
            if (file.isFile()) {
                ItNews itNews = (ItNews) um.unmarshal(file);

                dataService.saveNewsData(itNews.getNews().getNewsItems());
            } else {
                throw new FileNotFoundException("Файл не существует");
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        List<NewsItem> newsData = dataService.getNewsData();

        if (!newsData.isEmpty()) {
            for (NewsItem item : newsData) {
                System.out.println("Title: " + item.getTitle());
            }
        }


        List<NewsItem> appleKeywordData = findService.findByKeyword("apple");
        if (!appleKeywordData.isEmpty()) {
            for (NewsItem item : appleKeywordData) {
                System.out.println("Title: " + item.getTitle());
                System.out.println("Description: " + item.getDescription());
                for (String keyword : item.getKeywords().getKeywordsList()) {
                    System.out.println("Keyword: " + keyword);
                }
            }
        }

    }

    private static void fileTypeSelector(int enteredValue) {
        switch (enteredValue) {
            case 1: {
                System.out.println("Выбрано скачивание JSON");
                System.out.println("Скачиваем JSON");
                break;
            }
            case 2: {
                System.out.println("Выбрано скачивание XML");
                System.out.println("Скачиваем XML");

                break;
            }
            default:
                System.out.println("Введите 1 или 2.");
        }
    }
}

package com.kiparo.homework;

import com.kiparo.homework.model.DataType;
import com.kiparo.homework.providers.ServiceProvider;
import com.kiparo.homework.providers.ServiceProviderImpl;
import com.kiparo.homework.service.DataService;
import com.kiparo.homework.service.DownloadService;

import java.io.IOException;
import java.util.Scanner;


/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
public class Main {
    private static final ServiceProvider serviceProvider = new ServiceProviderImpl();
    private static final DownloadService downloadService = serviceProvider.getDownloadService();
    private static final DataService dataService = serviceProvider.getDataService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Нажмите 1 что-бы скачать JSON, 2 - XML: ");
        downloader(scanner);


        System.out.print("1 - вывести все новости, 2 - поиск по keyword. Введите 1 или 2: ");
        printerWithFinder(scanner);
    }

    private static void printerWithFinder(Scanner scanner) {
        int selector = scanner.nextInt();
        switch (selector) {
            case 1: {
                System.out.println("Выводим все новости");
                dataService.printAllNews();
                break;
            }
            case 2: {
                System.out.println("Поиск по keyword");
                System.out.print("Введите ключевое слово для поиска: ");
                String keyword = scanner.next();
                dataService.findAndPrintNewsByKeyword(keyword);
                break;
            }
            default:
                System.out.println("Введите 1 или 2.");
        }
    }

    private static void downloader(Scanner scanner) {
        int selectedFileType = scanner.nextInt();
        switch (selectedFileType) {
            case 1: {
                System.out.println("Выбрано скачивание JSON");
                try {
                    System.out.println("Скачиваем JSON");
                    downloadService.downloadFile(DataType.getFileUrl(DataType.JSON), DataType.getFileName(DataType.JSON));

                    System.out.println("Обрабатываем полученные данные");
                    dataService.parseAndSaveJSONData();
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

                    System.out.println("Обрабатываем полученные данные");
                    dataService.parseAndSaveXMLData();
                    System.out.println("Полученные данные успешно сохранены.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:
                System.out.println("Введите 1 или 2.");
        }
    }
}

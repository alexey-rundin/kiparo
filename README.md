#### Необходимо создать консольное приложение на Java или Kotlin.

В приложении должно использоваться:
1. Парсер для JSON с использованием библиотеки GSON/Moshi или другой подобной библиотеки
2. XML парсер (любой из встроенных в java/kotlin или сторонний)
3. HTTPUrlConnector, OkHttp или любая библиотека для загрузки файла с данными (json и xml) по web ссылке указанной ниже. 
Можно использовать любую другую технологию, класс, библиотеку.
4. Обработка исключительных ситуаций (нет интернета, файл не доступен или поврежден и тд.)
5. Использование многопоточности любым способом по желанию
6. Желательно использование наследования, полиморфизма и интерфейсов

#### Задача: 
Приложение, по выбору пользователя, должно скачивать json или xml файл. \
Затем всю информацию из этих файлов парсим и закидываем в коллекцию. \
Затем пользователю предлагается несколько функций, которые приложение должно делать.

1. Вывод всех новостей отсортированных по дате (даты должны отображаться в красивом виде: Число Месяц Год, месяц должен быть написан словами)
2. Поиск новостей по полю keywords

#### Пример использования:
- Выводим сообщение “Нажмите 1 что-бы скачать JSON, 2 - XML”
- Скачиваем, парсим
- Выводим пользователю “1 - вывести все новости, 2 - поиск по keyword ”
- И так далее.

Не тратьте время на красивый интерфейс, в этом задании в первую очередь важно, как вы построите классы, связи между ними, структуру приложения, какие классы и интерфейсы создадите.

#### Ссылки для загрузки данных:
- https://api2.kiparo.com/static/it_news.json
- https://api2.kiparo.com/static/it_news.xml

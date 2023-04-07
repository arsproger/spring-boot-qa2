# Данный проект является реализацией тестового задания Java F22. Он представляет собой сервис, который принимает и обрабатывает HTTP запросы, используя Java и Spring Boot.

# База данных

В качестве базы данных для этого проекта использовалась H2. Для создания связей между таблицами был использован
Hibernate ORM.

# Сборщик

В качестве сборщика использовался Gradle.

# Авторизация

Для авторизации была использована in-memory авторизация Spring Security с логином "user" и паролем "user".

# Функционал

### Проект реализует следующий функционал:

* Принимает HTTP запросы и отвечает на них соответствующими статусами.

* Сохраняет все объекты в базе данных.

### Реализует следующие запросы:

* GET /city — получение всех городов из базы;

* GET /street/city/{id} — получение всех улиц города;

* POST /shop/ — создание магазина;

* GET /shop/?city=&street=&open=0/1 — получение списка магазинов.

* Параметр open: 0 - закрыт, 1 - открыт.

  Данный статус определяется исходя из параметров
  "Время открытия", "Время закрытия" и текущего времени сервера.

# Объекты

## Проект включает следующие объекты:

### Магазин:

* Название

* Город

* Улица

* Дом

* Время открытия

* Время закрытия

### Город:

* Название

### Улица:

* Название

* Город

# Подготовительные действия

## Для успешной работы проекта необходимо иметь следующие инструменты:

* JDK 8 или выше

* Spring Boot

* База данных H2

* Сборщик проекта Gradle

# Для установки необходимых инструментов можно воспользоваться соответствующими инструкциями, доступными на официальных сайтах.

## Доступы

* Для авторизации в проекте используется in-memory авторизация Spring Security с логином "user" и паролем "user".

# Запуск проекта

*  Клонируйте репозиторий с проектом на свой компьютер.

*  Убедитесь, что на вашем компьютере установлены Java (версия 8 и выше) и Gradle.

*  Откройте терминал и перейдите в директорию с проектом.

*  Выполните команду gradle build, чтобы собрать проект и сгенерировать jar-файл.

*  Запустите проект, выполнив команду gradle bootRun

*  После запуска проект будет доступен по адресу http://localhost:8080/.

*  Для доступа к защищенным ресурсам (например, для выполнения запроса на создание магазина) необходимо авторизоваться.
   Для этого используйте логин user и пароль user.

# Дополнительные комментарии:

При первом запуске проекта будет создана база данных H2 в памяти компьютера.
Для каждого последующего запуска база
данных будет очищаться и заполняться заново тестовыми данными.

Для работы с базой данных в проекте используется Spring Data JPA.
Если вы захотите использовать другую реляционную
СУБД (например, PostgreSQL), необходимо будет изменить настройки в файле application.properties.

# T1 Homework 2

Реализована простая система управления задачами с использованием Spring Boot и Spring Data JPA.\
Можно создавать, просматривать, обновлять и удалять задачи.

Модель данных:\
Сущность Task с полями: id, title, description, dueDate, completed.

Скрипт по созданию таблицы описан в файле [data.sql](https://github.com/dgrishin95/t1_homework2/blob/master/src/main/resources/data.sql).\
Валидация (поле title не должно быть пустым) реализована как на уровне базы, так и на уровне приложения.

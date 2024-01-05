This is backend part of the Master-Detail Document table. Used frameworks - Spring Boot and Data, ORM - Hibernate, DataBase - PostgreSQL.
Useful configuration provided in application.properties file.

###Инструкция по развертыванию базы данных.
Вам понадобится база данных PostgreSQL.
Создать новую базу данных master_detail
Заходим в файл application.properties и проделываем указанные изменения: 
В блоке #Connection:
spring.datasource.url=jdbc:postgresql://localhost:5432/master_detail
spring.datasource.username=*ваш username*
spring.datasource.password=*ваш пароль*
В блоке #JPA:
spring.jpa.hibernate.ddl-auto= create

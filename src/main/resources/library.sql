CREATE TABLE books (

                       title  varchar(255) not null ,
                       author varchar(255)  not null ,
                       year integer not null ,
                       isbn varchar(255) not null primary key
);

insert into books (title, author, year, isbn) values
('Spring Start Here: Learn what you need and learn it well','Laurentiu Spilca', 2021, '9781617298691'),
('Building RESTful Web Services with Spring 5','Ludovic Dewailly', 2018, '9781788475891');



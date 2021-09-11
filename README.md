# BookManagementSystem2
SpringBoot, PostgreSQL, Thymeleaf, and Bootstrap web application with pagination and table sorting.

<img width="1680" alt="Screen Shot 2021-09-11 at 1 19 26 AM" src="https://user-images.githubusercontent.com/78386606/132938519-021b4e49-393a-4f51-af7e-892767f76394.png">

<img width="1680" alt="2 create" src="https://user-images.githubusercontent.com/78386606/132938897-03bb0fe7-614f-47e2-8c37-ba3bb2a93be7.png">
<img width="1680" alt="3 update" src="https://user-images.githubusercontent.com/78386606/132938900-7cfdc165-d95c-48ba-accb-bede7409f731.png">
<img width="1680" alt="4 error page" src="https://user-images.githubusercontent.com/78386606/132938905-8bc96d53-45fd-4000-8fe0-b4ef0eb76d7d.png">


Creating the Postgres database
Terminal commands (in order):

psql
<br>
CREATE DATABASE books;
<br>
\c books
<br>
CREATE TABLE book (
<br>
id bigint,
<br>
isbn varchar(30),
<br>
title varchar(100),
<br>
author varchar(100),
<br>
status varchar(15),
<br>
PRIMARY KEY (id)
<br>
);
<br>
GRANT ALL PRIVILEGES ON DATABASE books to yourusername;
<br>
GRANT ALL PRIVILEGES ON DATABASE books to postgres;
<br>
\q
<br>

<b>Table Structure</b>
<br>
<img width="633" alt="Screen Shot 2021-09-10 at 10 29 14 PM" src="https://user-images.githubusercontent.com/78386606/132938601-4f1c97dc-244d-4566-ada7-d44812689c7f.png">




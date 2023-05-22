# FlashFood
## overall explanation
### This is a course project built by the following group members:
### Xiao Wang
### Wei Li
### Xinpeng Liu
### Jayden Wang


<!-- ABOUT THE PROJECT -->


The Java version of the project is `jdk8`, the main technologies include `Spring Boot` + `Mybatis Plus`, the database uses `MySQL`, and `Redis` is used to cache and optimize queries.

<p align="right">(<a href="#悦刻外卖-readme">back to top</a>)</p>

### Framworks

* [![JDk8][JDK8.com]][JDK8-url]
* [![Spring][Spring.com]][Spring-url]
* [![Spring Boot][SpringBoot.com]][SpringBoot-url]
* [![MySQL][MySQL.com]][MySQL-url]
* [![Mybatis Plus][MybatisPlus.com]][MybatisPlus-url]
* [![Redis][Redis.com]][Redis-url]
* [![Vue][Vue.js]][Vue-url]
* [![ElementUI][ElementUI.com]][ElementUI-url]
* [![IntelliJ IDEA][IntelliJ IDEA.com]][IntelliJ IDEA-url]

<p align="right">(<a href="#FlashFood-readme">back to top</a>)</p>





## Setting

* JDK8 
* MySQL This project uses the MySQL database, please ensure that you can successfully connect to the MySQL database before running this project
* Redis
 This project uses Redis cache, please make sure you can successfully connect to the Redis database before running this project.
* Maven This project is built using Maven. When you open the project for the first time, IntelliJ IDEA will automatically download Maven dependencies.


* **Database** </p>
  No matter what database **management tool** you use, please create an easy-to-remember database name in the database, such as `FlashFood`, and then import the `sql` of this 
   [`db.sql` file]([https://github.com/riverify/rikky-takeaway/blob/main/sql/db.sql](https://github.com/jaydenwang2333/FlashFood/tree/main/sql)) in folder.
   After that, you need to go back to the `src/main/resources` directory of this project, find the `application.yml` file, and supplement the connection information of the database.


* **Redis** </p>
  After you have installed Redis and successfully connected to the Redis database, you need to go back to the `src/main/resources` directory of this project and find `application.yml`
     File, which supplements the connection information of Redis.


* **Email** </p>
  This mailbox is used to send the verification code. This part is very confused because gmail has smething protection. Although the SMTP service of gmail mailbox is free and does not require additional configuration, but its protection policy is very confused. It will send you an warming email to you email.

   You will get an `authorization code`, which is your email password `password`, you can configure it in the email configuration information in `application.yml`.


* **File storage location configuration** </p>
 Since the system needs to store images uploaded by users, it is necessary to configure a file storage location. It is recommended to right-click the `img` folder in IntelliJ IDEA, `Copy Path`
   , choose to copy the absolute path,
   Then configure it in the file storage location configuration information of `application.yml`.

The default account for background login is `admin`, and the password is `123456`.
The mobile user interface is adapted to the mobile phone, and the layout may be disordered when opened directly by the computer, so please use the mobile browser to open it.
Or use the developer tools to simulate the mobile browser to open.



- Backend management system default link: http://localhost:8080/backend/index.html
- Customer user mobile default link: http://localhost:8080/front/index.html



This project is a food deliver management system for learning the development of Spring Boot. The functions are relatively limited, but there are also some functions, such as:

* User registration
* User login
* User address management
* User order management
* User shopping cart
* Staff management
* Dishes management
* Dishes classification management
* Package management
* Employee order management


# FlashFood / UFood
A goup project for CS520.
Developed by **Xiao Wang**, **Wei Li**, **Xinpeng Liu**, **Jayden Wang**.
## Overall explanation

<!-- ABOUT THE PROJECT -->
This project is an online food management & ordering system for learning the development of SpringBoot. The functions are relatively limited, but basic are implemented, such as:

* User registration / login
* User address management
* User orders' history
* User making orders
* Staff management
* Adding / Removing dishes
* Category management
* Price change
* Order Info Receiving


The Java version of the project is `jdk8`, the main technologies include `Spring Boot` + `Mybatis Plus`, the database uses `MySQL`, and `Redis` is used to cache and optimize queries.



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







## Setting

* JDK8 
* MySQL 
* Redis
* Maven

This project uses the MySQL database, please ensure that you can successfully connect to the MySQL database before running this project

 This project uses Redis cache, please make sure you can successfully connect to the Redis database before running this project.

 This project is built using Maven. When you open the project for the first time, IntelliJ IDEA will automatically download Maven dependencies.

**All required settings will be made to src/main/resources/application.yml**


* **Database** </p>
Please find database settings in /src/main/resources/application.yml

  Please create an easy-to-remember database name in the database, such as 'flashfood', and then import the 'sql' of this 
   ['db.sql' file](https://github.com/jaydenwang2333/FlashFood/tree/main/sql) in folder.
   After that, you need to go back to the `/src/main/resources` directory of this project, find the 'application.yml' file, and supplement the connection information of the database.


* **Redis** </p>
  It is recommended to run 'FLUSHALL' in redis and clear the cache in the browser each time before you run the application


### RUN
Find /src/main/java/main/FlashFoodApplication.java and run.

Manager's Page: http://localhost:8080/backend/index.html

The default account for background login is `admin`, and the password is `123456`.

User's Page: http://localhost:8080/front/index.html

Use your email and varification code to log in.

The mobile user interface is adapted to the mobile phone, and the layout may be disordered when opened directly by the computer, so please adapt the shape of the browser page to see the full picture.

It is recommended to use the developer tools which are built in browsers like safari or chrome to simulate the mobile browser when openning user page.


[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white

[Next-url]: https://nextjs.org/

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB

[React-url]: https://reactjs.org/

[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D

[Vue-url]: https://vuejs.org/

[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white

[Angular-url]: https://angular.io/

[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00

[Svelte-url]: https://svelte.dev/

[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white

[Laravel-url]: https://laravel.com

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white

[Bootstrap-url]: https://getbootstrap.com

[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white

[JQuery-url]: https://jquery.com

[Java.com]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white

[Java-url]: https://www.java.com/en/

[Python.com]: https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white

[Python-url]: https://www.python.org/

[Spring.com]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white

[Spring-url]: https://spring.io/

[SpringBoot.com]: https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot

[SpringBoot-url]: https://spring.io/projects/spring-boot

[MyBatis.com]: https://img.shields.io/badge/MyBatis-2779BD?style=for-the-badge&logo=mybatis&logoColor=white

[MyBatis-url]: https://mybatis.org/mybatis-3/

[MySQL.com]: https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white

[MySQL-url]: https://www.mysql.com/

[MybatisPlus.com]: https://img.shields.io/badge/MyBatis_Plus-2779BD?style=for-the-badge&logo=mybatis&logoColor=white

[MybatisPlus-url]: https://mybatis.plus/

[Redis.com]: https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white

[Redis-url]: https://redis.io/

[ElementUI.com]: https://img.shields.io/badge/Element_UI-4FC08D?style=for-the-badge&logo=elementdotio&logoColor=white

[ElementUI-url]: https://element.eleme.io/

[JDK8.com]: https://img.shields.io/badge/Java_8-ED8B00?style=for-the-badge&logo=java&logoColor=white

[JDK8-url]: https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

[IntelliJ IDEA.com]: https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white

[IntelliJ IDEA-url]: https://www.jetbrains.com/idea/

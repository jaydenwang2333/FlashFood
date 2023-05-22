# FlashFood
## overall explanation
### This is a course project built by the following group members:
### Xiao Wang
### Wei Li
### Xinpeng Liu
### Jayden Wang


<!-- ABOUT THE PROJECT -->


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

[contributors-shield]: https://img.shields.io/github/contributors/riverify/rikky-takeaway.svg?style=for-the-badge

[contributors-url]: https://github.com/riverify/rikky-takeaway/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/riverify/rikky-takeaway.svg?style=for-the-badge

[forks-url]: https://github.com/riverify/rikky-takeaway/network/members

[stars-shield]: https://img.shields.io/github/stars/riverify/rikky-takeaway.svg?style=for-the-badge

[stars-url]: https://github.com/riverify/rikky-takeaway/stargazers

[issues-shield]: https://img.shields.io/github/issues/riverify/rikky-takeaway.svg?style=for-the-badge

[issues-url]: https://github.com/riverify/rikky-takeaway/issues

[license-shield]: https://img.shields.io/github/license/riverify/rikky-takeaway.svg?style=for-the-badge

[license-url]: https://github.com/riverify/rikky-takeaway/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/riverify

[product-screenshot]: images/screenshot.png

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

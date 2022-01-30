
## About The Project
This project has been created for n11 TalentHub Bootcamp. This application is a simple Loan application system that receives loan application requests and returns the loan application result to the client. 
If clients do not have any approved loan application, they can update some information about themselves and apply again. If you want to deeply understand what is required for this project please read the pdf written in Turkish. [2022_01_18_n11_TalentHub_Java_Spring_Bootcamp_Bitirme_Projesi.pdf](
https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser/blob/main/2022_01_18_n11_TalentHub_Java_Spring_Bootcamp_Bitirme_Projesi.pdf)

In short, all codes were written for the purpose of understanding the concepts of Backend Development, Database Systems, Spring Boot, RESTful API, SOLID and Clean Code principles, Unit and Integration Tests, Design patterns, Log Mechanism, Frontend Development, Documentation with implementing a loan application system.

## Api Details
This Project includes 4 basic operations (New Client Loan Application, Updated Client Loan Application, Client Deletion, Retrieving Client's All Loan Applications).

## Before Getting Started
This Project has two implemented parts(frontend and backend). Before run them properly you need to download some technologies and make some configuration mentioned below. 

### Fronted Requirements
Please download [Node.js](https://nodejs.org) first. After download Node.js open the project [directory](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser/tree/main/frontend) run this comment below to install node packages which are needed to run properly frontend part of the project.

  ```sh
  npm install
  ```
After downloading packages you can simply run the comment in the same directory writen below to run frontend part of the project.

  ```sh
  npm start
  ```

### Backend Requirements
Please download [Java](https://www.java.com/) and [JDK](https://www.oracle.com/java/technologies/downloads/) first.
Backend configuration details and dependencies used by Maven are in the file named [pom.xml](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser/blob/main/backend/pom.xml). Configuration is written in [application.properties](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser/blob/main/backend/src/main/resources/application.properties) under the folder resources. To run the applications using your local database you must change configurations in the files mentioned above.

I recommend running backend part of the project with [Intellij](https://www.jetbrains.com/idea/)

## Usage

### Frontend Usage
- To Apply Loan press "Yeni Kullanıcı Kredi Başvurusu" button and fill shown fields in opened window.
- To Get Results of Loan Applications  press "Başvuru Sonucum" button and fill shown fields in opened window.
- To Update Client infos and apply press "Bilgilerimi Güncelle ve Yeni Başvuru" button and fill shown fields in opened window.

### Backend Usage
You can perform api using swagger documentation or postman collection in the directory named [resources](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser/tree/main/backend/src/main/resources)
Also you can open localhost:8080/swagger-ui.html in the [browser](localhost:8080/swagger-ui.html) for performing Swagger.
Logs are saved in the file log.txt under the folder [logging](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser/tree/main/backend/src/main/resources/logging)

### Tech
- [Java](https://www.java.com/tr/)
- [Java Spring Boot Framework](https://spring.io/projects/spring-boot)
- [React.js](https://tr.reactjs.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Maven](https://maven.apache.org/)
- [Swagger](https://swagger.io/)
- [Postman](https://www.postman.com/)
- [Lombok](https://projectlombok.org/)
- [MapStruct](https://mapstruct.org/)
- [JpaRepository](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)
- [Slf4j](https://projectlombok.org/api/lombok/extern/slf4j/Slf4j.html)

## License
Distributed under the MIT License. See `LICENSE.txt` for more information.

## Contact
Osman Ural Keser - [@linkedin](https://www.linkedin.com/in/osmanuralkeser/)  
Gmail: osmanuralkeser@gmail.com

Project Link: [https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-uralkeser](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-OyaEr) 
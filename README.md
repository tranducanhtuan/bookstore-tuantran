# bookstore-tuantran

## Table of contents
- [1. Techstack](#1-techstack)
  * [1.1. DB](#11-db)
  * [1.2. Backend](#12-backend)
  * [1.3. Frontend](#13-frontend)
- [2. System Architecture](#2-system-architecture)
  * [2.1. Backend](#21-backend)
  * [2.2. Frontend](#22-frontend)
  * [2.3. Infrastructures](#23-infrastructures)
  * [2.4. In-Service layering](#24-in-service-layering)
- [3. Software development principles or patterns or practices](#3-software-development-principles-or-patterns-or-practices)
- [4. Database tables design](#4-database-tables-design)
- [5. Package structure](#5-package-structure)
- [6. Setup local environment to run project](#6-setup-local-environment-to-run-project)
  * [6.1. Preparation](#61-preparation)
  * [6.2. Start Service](#62-start-services)
- [7. Testing](#7-testing)
  * [7.1. Postman](#71-postman)
  * [7.2. Test introdution](#72-test-introdution)
  * [7.3. Verify in H2 database](#73-verify-in-h2-database)
- [8. TODO](#8-todo)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>


## 1. Techstack
### 1.1. DB
- Current: H2 DB (but saved in file so it's still keep your data even service is restarted): help fast build up project & testing.
- Next: Postgres or MariaDB will be considered.
### 1.2. Backend
- Java 8.
- Spring, Spring Boot: robust, fast setup, huge community (todo next: can use Spring Cloud Netflix for microservices).
- Testing: Junit/Mockito/PowerMock (todo).
### 1.3. Frontend
- We can use SPA like ReactJS, AngularJS -> high performance & good user experience.

## 2. System Architecture 
### 2.1. Backend
![service_architect-Backend](https://user-images.githubusercontent.com/45473913/117583512-b3069900-b131-11eb-8fdc-80193ddc82b3.png)

### 2.2. Frontend  
![service_architect-Frontend](https://user-images.githubusercontent.com/45473913/117582995-05928600-b12f-11eb-8883-708de76ab30c.png)

### 2.3. Infrastructures
- **_Backend:_**  
  ![service_architect-AWS backend infrastructure](https://user-images.githubusercontent.com/45473913/117583005-0cb99400-b12f-11eb-9512-11dc804dbca5.png)
  
- **_Frontend:_**  
![service_architect-AWS Frontend Infrastructure](https://user-images.githubusercontent.com/45473913/117583008-1216de80-b12f-11eb-818c-68698802d928.png)
  
### 2.4. In-Service layering  
  ![image](https://user-images.githubusercontent.com/45473913/115496389-b5dd3f00-a293-11eb-9826-4616dfe9c115.png)  
  ![image](https://user-images.githubusercontent.com/45473913/115495928-d5c03300-a292-11eb-8ae7-673ee26055c3.png)  

## 3. Software development principles or patterns or practices
- Object oriented design with SOLID.
- RESTful principles: http method rule, endpoint url rule (todo next: stateless).
- Layering architect: as above "In-Service layering" section.
- Profiling environment: local, dev, prod.

## 4. Database tables design
![DB_tables_design](https://user-images.githubusercontent.com/45473913/117583117-9d906f80-b12f-11eb-9039-b57bbfeb4fa9.png)
  
## 5. Package structure
**_Depth 1:_** organization country (jp)  
---**_Depth 2:_** organization classification (co)  
------**_Depth 3:_** Organization name (sb)  
---------**_Depth 4:_** System name (bookstore)  
---------------**_Depth 5:_** Domain name (book)  
------------------**_Depth 6:_** Layer name  
---------------------------**_controller:_** public REST API (VoucherController.java)  
---------------------------**_dto:_** data transfer object, exchange data between client vs service (VoucherDto.java)  
---------------------------**_service:_** biz logic service interface (VoucherService.java)  
---------------------------**_service/impl:_** biz logic service implementation (VoucherServiceImpl.java)  
---------------------------**_repository:_** Data storage in resource layers (VoucherRepository.java)  
---------------------------**_entity:_** persistent object mapping with database table (VoucherJpo.java)  
---------------------------**_mapper:_** convert dto to entity and vice versa  

## 6. Setup local environment to run project
### 6.1. Preparation
- **_Step 1:_** checkout this git repository.  
- **_Step 2:_** import bookstore maven project into your IDE (i'm using IntelliJ)    
- **_Step 3:_** make sure on your IDE, all imported projects are setup to use Java8 & Maven.   
- **_Step 4:_** **_maven clean build_** for your project.    

### 6.2. Start Services
- Start normally as a spring boot project, refer class **_BookStoreServiceApplication.java_**
- Default port is 8080, but you can change it at **_application.yml_**

## 7. Testing
### 7.1. Postman
  Import file **_Bookstore.postman_collection.json_** from root path of git repo into your Postman, 
  it will lead you to URL of gateway service which is the only one we publish to outside.  
  ![image](https://user-images.githubusercontent.com/45473913/117583577-0e388b80-b132-11eb-9b3a-22ded2dc2768.png)
  
### 7.2. Test introdution
All CRUD APIs ran successfully in my side, so they are ready to release:
- Author:
  + Create
  + Find: by author name.
  + Update
  + Delete: you can not delete an author if some book still refer to it.
- Book:
  + Create
  + Find: by book title and author name (it's up to you).
  + Update
  + Delete
  
### 7.3. Verify in H2 database
  Please access this link without password: [http://localhost:8083/h2](http://localhost:8083/h2)  
  ![image](https://user-images.githubusercontent.com/45473913/115384894-bf1dcb80-a201-11eb-8939-815159d84bcd.png)

## 8. TODO
- Design Auth service.
- Authenticate using JWT token.
- Move messages from "MessageCode.java" into "messages.properties" file.
- Customize JPA naming stragegy to use more convenient.
- Write unit tests & add Jacoco code coverage report.
- Create enterprise DB like Postgres/MariaDB...
- Logging & centralize logs using Elastic stack/Splunk.
- Monitoring system using Prothemios & Grafana.
- And some more...
  
## Thank you & best regards.
Tuan Tran (Andy)  

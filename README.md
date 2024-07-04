# Lotto Game

This project is a web application created in Spring Boot which allows users to play well-known lottery game. User provides 6 distinct numbers in range from 1 to 50 for which system generates coupon with unique id. Winning numbers are generated once a week, at specified time. To win user must hit at least 4 matching numbers. Users can check if they won by using ticket unique id.

This project uses modular monolithic application architecture with elements of hexagonal and microservice architecture. The numbers-generator has been separated as an independent microservice.

## Tech

Order System is developed using following technologies: <br>
![image](https://img.shields.io/badge/17-Java-orange?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring) &nbsp;
![image](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white) &nbsp;

Testing:<br>
![image](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Mockito-78A641?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/Testcontainers-9B489A?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/WIREMOCK-lightblue?style=for-the-badge) &nbsp;

Other:<br>
![image](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) &nbsp;

Continuous integration: <br>
[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/DmCGbhvsat4gP2YLSDSfx4/JtzsURR2NSU8SyxJrkdYBo/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/circleci/DmCGbhvsat4gP2YLSDSfx4/JtzsURR2NSU8SyxJrkdYBo/tree/master)

## Specification

- Spring Boot, web application
- Modular monolith hexagonal architecture with one module extracted as microservice
- All modules are containerized using Docker
- Developed using CircleCi - Continuous Integration tool
- Facade design pattern
- NoSQL Database (MongoDB)
- Good coverage with unit tests, including "happy path" integration test
- Scheduled winning numbers generation and results processing
- Netflix-Eureka server used as discovery service
- API Gateway


## Rest-API Endpoints

|              ENDPOINT              | METHOD  |         REQUEST          |       RESPONSE       |                    FUNCTION                     |
|:----------------------------------:|:-------:|:------------------------:|:--------------------:|:-----------------------------------------------:|
|       /lottery/input-numbers       |  POST   | JSON BODY (user numbers) |    JSON (ticket)     |     creates new ticket for given numbers        |
|   /result/get-result/{ticketId}    |  GET    | PATH VARIABLE(ticketId)  |    JSON (ticket)     |    returns lottery result for given ticketId    |
|   /result/get-results/{drawDate}   |  GET    | PATH VARIABLE(drawDate)  |    JSON (results)    |    returns all lottery results for given date   |

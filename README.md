# Lotto Game

This project is a web application created in Spring Boot which allows users to play well-known lottery game. User
provides 6 distinct numbers in range from 1 to 50 for which system generates coupon with unique id. Winning numbers are
generated once a week, at specified time. To win user must hit at least 4 matching numbers. Users can check if they won
by using ticket unique id.

## Table of Content
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Installation](#installation)
- [Rest-API Endpoints](#endpoints)

## Technologies

Order System is developed using following technologies: <br>
![image](https://img.shields.io/badge/17-Java-orange?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring) &nbsp;
![image](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/rabbitmq-%23FF6600.svg?&style=for-the-badge&logo=rabbitmq&logoColor=white) &nbsp;
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) &nbsp;


Testing:<br>
![image](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Mockito-78A641?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/Testcontainers-9B489A?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/WIREMOCK-lightblue?style=for-the-badge) &nbsp;

Other:<br>
![image](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white) &nbsp;
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) &nbsp;

Continuous integration: <br>
[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/DmCGbhvsat4gP2YLSDSfx4/JtzsURR2NSU8SyxJrkdYBo/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/circleci/DmCGbhvsat4gP2YLSDSfx4/JtzsURR2NSU8SyxJrkdYBo/tree/master)

## Architecture

This project uses microservice architecture with elements of hexagonal and modular monolithic application architecture.

I have also implemented distributed configuration pattern using Spring Cloud Config Discovery First approach - this means
that configuration files can be updated easily, without the need to change the application.


|          Service          |                                                   Description                                                   |
|:-------------------------:|:---------------------------------------------------------------------------------------------------------------:|
|     Discovery service     |    Allows services to dynamically find and communicate with each other without hard-coding hostname and port    |
|      Config service       |                      Stores and serves distributed configurations across multiple services                      |
|      Gateway service      |                         Provides routing mechanism and JSON Web Token validation filter                         |
|  Authentication service   |                  Handles registration, login and email confirmation. Generates JSON Web Token                   |
|      Engine service       | Retrieves user numbers, creates tickets and handles results announcing. Modular monolith hexagonal architecture |
| Numbers generator service |              Generates winning numbers at scheduled time. Allows services to fetch winning numbers              |
|       Mail service        |                  Generates and sends emails. Communicating with other services using rabbitMQ                   |


## Installation

Clone the repository:
```bash
 git clone https://github.com/JulianKlewek/Lotto.git
 ```

Install dependencies and build project:
```mvn
./mvnw clean install
 ```
Run application
```mvn
./mvnw spring-boot:run
 ```

Or if you have docker installed you can simply run application using docker-compose file.
```docker
docker-compose --profile prod up -d
```


## Endpoints

|               ENDPOINT                | METHOD |             REQUEST             |                                 RESPONSE                                  |                  FUNCTION                  |
|:-------------------------------------:|:------:|:-------------------------------:|:-------------------------------------------------------------------------:|:------------------------------------------:|
|        /lottery/input-numbers         |  POST  |    JSON BODY (user numbers)     |                        JSON (NumberReceiverResult)                        |    creates new ticket for given numbers    |
|     /result/get-result/{ticketId}     |  GET   |     PATH VARIABLE(ticketId)     |                           JSON (ResultResponse)                           | returns lottery result for given ticketId  |
|    /result/get-results/{drawDate}     |  GET   |     PATH VARIABLE(drawDate)     |                       JSON (WinningResultsResponse)                       | returns all lottery results for given date |
|             /auth/signup              |  POST  | JSON BODY (UserRegisterRequest) |                        JSON (UserRegisterResponse)                        |               registers user               |
|             /auth/signin              |  POST  |  JSON BODY (UserLoginRequest)   |                         JSON (UserLoginResponse)                          |            allows user to login            |
|         /auth/confirm-account         |  GET   |      REQUEST PARAM (token)      |                     JSON (EmailConfirmationResponse)                      |            confirms user email             |

# Hotel Management Microservices

Welcome to the Hotel Management Microservices project. This application is designed to manage various aspects of a hotel, including booking rooms, adding hotels, and handling user ratings.

把微服务调整一下，停止更新的换成新的->eureka；
加一些新业务->支付/队列/缓存/锁；
打包成docker->github action；

## Table of Contents
- [Features](#features)
- [技术栈](#技术栈)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Checking Service Status](#checking-service-status)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features 项目结构
This application provides the following features:
本项目由以下服务组成

### User Service 1
- User registration and authentication. 使用注册和鉴权
- User can book hotel rooms. 用户可以预定酒店房间
- User can cancel bookings. 用户可以放弃预定
- Wallet system to track all transactions for users. 钱包系统可以追溯用户过往交易订单

### Hotel Service 2
- Add hotels with details. 添加酒店信息
- Add rooms to hotels. 添加房间到酒店
- Get hotel details by ID or name. 通过ID/名字获取酒店信息
- Get a list of all available rooms in a hotel. 获取酒店可用房间列表
- Get a list of all booked rooms in a hotel. 获取酒店已预定房间列表

### Booking Service 没看见
- Book rooms in hotels. 预定酒店房间
- Get booking details by booking ID. 通过ID获取预定信息
- Get a list of all bookings. 获取所有预定列表

### Rating Service 3
- Add ratings and reviews for hotels. 为酒店添加评分和评论
- Get all ratings.  获取所有评分
- Get ratings by user ID or hotel ID. 通过用户id/酒店id获取评分

### Service Registry
- Register and discover microservices. 注册发现中心

### Configuration Server
- Manage centralized configurations for microservices. 配置中心

### API Gateway
- Gateway for accessing microservices. api接口网关

## 技术栈
- Java
- Spring Boot
- Spring Cloud
- Spring Cloud Eureka --
- Spring Cloud Config
- Spring Data JPA
- Spring Web
- Spring Data MongoDB
- Spring Data REST
- Spring Cloud Gateway
- Thymeleaf
- MySQL
- MongoDB
- Git


### Usage
- Run each microservice individually to start the Hotel Management system.

### Checking Service Status
- **Eureka Server**:
  - Eureka Dashboard: `http://localhost:8761`
    - You can check the status of all registered microservices here. It will show which services are up and running and their corresponding instances.

Instances currently registered with Eureka:


- **HOTELS-SERVICE**:
  - Availability Zones: UP (1) - `192.168.1.4:hotels-service:8082`

- **RATING-SERVICE**:
  - Availability Zones: UP (1) - `192.168.1.4:rating-service:8083`

- **USERS-SERVICE**:
  - Availability Zones: UP (1) - `192.168.1.4:users-service:8081`


### User Service Routes
- User login: `http://localhost:8086/auth/login`
  - Login on this URL to obtain an access token. You can use this access token to make authenticated requests to other service endpoints.
- Fetch all users: `http://localhost:8086/users/all`
- User registration: `http://localhost:8086/users/register`
- User login: `http://localhost:8086/users/login`
- Book a hotel room: `http://localhost:8086/users/addBooking`
- Complete a Booking hotel: `http://localhost:8086/users/completeBooking/{bookingId}`
- Cancel a booking: `http://localhost:8086/users/cancelBooking/{bookingId}`
- Add wallet Balance transactions: `http://localhost:8086/users/wallet/addMoney/{email}?amount=00.0`
- View wallet Balance transactions: `http://localhost:8086/users/wallet/getBalance/{email}`
- View wallet transactions: `http://localhost:8086/users/wallet/getTransactions/{email}`

### Hotel Service Routes
- Fetch all hotels: `http://localhost:8086/hotels/all`
- Fetch hotel by ID: `http://localhost:8086/hotels/{hotelId}`
- Add a hotel: `http://localhost:8086/hotels/add` 没做
- Add a room to a hotel: `http://localhost:8086/hotels/{hotelId}/rooms/add`
- Book a room in a hotel: `http://localhost:8086/hotels/{hotelId}/bookings/add`
- Get all bookings in a hotel: `http://localhost:8086/hotels/{hotelId}/bookings/all`

### Rating Service Routes
- Fetch all ratings: `http://localhost:8086/ratings/all`
- Add a rating: `http://localhost:8086/ratings/add`
- Fetch ratings by user ID: `http://localhost:8086/ratings/user/{userId}`
- Fetch ratings by hotel ID: `http://localhost:8086/ratings/hotel/{hotelId}`

## 计划

1 创建表 hotel,user,room,booking √
  jpa转为mybaits(plus) hotel rating user √  
  调试crud...  
  整合entity/mapper类为一个模块√

3 更换注册中心eureka为Nacos √ 顺带也是配置中心

4 节点逐步迁移(改nacos客户端依赖，改application,改数据库) 

api-gateway √  hotel-common√ hotel-service√ 

frontend storage account order

provider consumer

rating-service service-registry user-service

4 加新业务? 支付订单 / 点赞酒店 /缓存 /锁 （队列/redis/分布式事务）

5 docker部署...->github action


2 faker->模拟生成数据,jmeter测试

为确保代码能够正常启动，请先配置本地主机映射，将以下映射添加到配置文件中。

```shell
# for hotel-example C:\Windows\System32\drivers\etc\hosts
127.0.0.1 hotel-mysql
127.0.0.1 nacos-server
127.0.0.1 seata-server
127.0.0.1 rocketmq
127.0.0.1 gateway-service
127.0.0.1 hotel-frontend
```
## GetStarted
1 部署环境命令:
```shell
docker-compose -f ./docker-compose/docker-compose-env.yml up -d
```
2 进入git bash配置nacos
```shell
bash./config/scripts/nacos-config-quick.sh
```

## port列表
### env
nacos 8848,(gRPC)9848,9849 http://nacos-server:8848/nacos/index.html 2.0以上要三个口
mysql 3306
rockemqsrv 9876
rocktmqbroker 10909 10911
seata 8091 

### service:
HOTELS-SERVICE 8082
RATING-SERVICE 8083
USERS-SERVICE 8081

GATEWAY_PORT=30010
ACCOUNT_PORT=8012
FRONTEND_PORT=8080
ORDER_PORT=8013
STORAGE_PORT=8011
PRAISE_PROVIDER_PORT=8015
PRAISE_CONSUMER_PORT=8014

## Reference
https://github.com/Amanastel/Hotel-Management-Microservices.git

springCloudAlibaba

## 遇到的问题及解决方案
[Invalid value type for attribute ‘factoryBeanObjectType‘: java.lang.String](https://blog.csdn.net/u013737132/article/details/134938131)
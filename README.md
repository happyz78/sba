This SBA project is for FSD final assignment.

First install mysql

docker pull mysql

create docker network bridge

docker network create --driver bridge sba_bridge

start mysql docker

docker run -p 3306:3306 --network sba_bridge --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql

import ./schema.sql into mysql database.


Build docker file for each mdoel

mvn clean package -pl sba-EurekaServer docker:build


mvn clean package -pl sba-Gateway docker:build


mvn clean package -pl sba-User docker:build


mvn clean package -pl sba-JWTServer docker:build


mvn clean package -pl sba-Training docker:build


mvn clean package -pl sba-Technology docker:build


mvn clean package -pl sba-Search docker:build


mvn clean package -pl sba-Payment docker:build


Start Eureka docker and other model.


docker network create --driver bridge sba_bridge
docker run -d -p 8761:8761 --network sba_bridge --name=sba.eureka  sba.eureka
docker run -d -p 8080:8080 --network sba_bridge --name=sba.gateway  sba.gateway
docker run -d -p 8081:8081 --network sba_bridge --name=sba.jwt  sba.jwt
docker run -d -p 8082:8082 --network sba_bridge --name=sba.user  sba.user
docker run -d -p 8083:8083 --network sba_bridge --name=sba.training  sba.training
docker run -d -p 8084:8084 --network sba_bridge --name=sba.technology  sba.technology
docker run -d -p 8085:8085 --network sba_bridge --name=sba.search  sba.search
docker run -d -p 8086:8086 --network sba_bridge --name=sba.payment  sba.payment

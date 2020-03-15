

cd /$HOME/all-repos/educational-proyect/common-service;
pwd;ls -l;
./mvnw clean install -DskipTests;


cd /$HOME/all-repos/educational-proyect/config-server;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t config-server:v1 .;

cd /$HOME/all-repos/educational-proyect/eureka-server;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t eureka-server:v1 .;

cd /$HOME/all-repos/educational-proyect/product-service;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t product-service:v1 .;

cd /$HOME/all-repos/educational-proyect/item-service;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t item-service:v1 .

cd /$HOME/all-repos/educational-proyect/user-service;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t user-service:v1 .

cd /$HOME/all-repos/educational-proyect/oauth2-service;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t oauth2-service:v1 .

cd /$HOME/all-repos/educational-proyect/zuul-server;
pwd;ls -l;
./mvnw clean install -DskipTests;
docker build -t zuul-server:v1 .

cd /$HOME/all-repos/educational-proyect/zipkin-server;
pwd;ls -l;
docker build -t zipkin-server:v1 .


docker pull postgres:12-alpine;
docker pull rabbitmq:3.8-management-alpine



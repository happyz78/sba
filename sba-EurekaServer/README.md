This EurekaServer project is for FSD final assignment.

The build command is:
`mvn clean package docker:build`

It will build a docker named sba.eureka

Start Docker Container 
`docker run -p 8761:8761 -d sba.eureka`
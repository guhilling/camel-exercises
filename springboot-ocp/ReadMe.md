# Spring Boot with camel and other useful things

# springboot-ocp 


```
mvn install
```


```
mvn spring-boot:run
```


```
curl http://localhost:8090/camel/api-docs
curl http://localhost:8090/camel/ping
```


```
http://localhost:8090/webjars/swagger-ui/3.23.5/index.html?url=/camel/api-docs
```

```
curl http://localhost:8090/camel/restsvc/ping
```


```
docker build -t springboot-ocp .
docker run -d --net primenet --ip 172.18.0.10 --name springboot-ocp springboot-ocp
```

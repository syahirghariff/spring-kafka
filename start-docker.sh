#!/bin/sh

docker run -e BOOTSTRAP_SERVER=192.168.1.118:9092\
       	--restart unless-stopped \
	-d \
	--name spring-kafka \
	-p 8090:8080 \
	spring-kafka 

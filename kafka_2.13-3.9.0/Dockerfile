FROM apache/kafka:3.9.0
 
ARG ADVERTISED_LISTENER

WORKDIR /opt/kafka

RUN KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)" && \
echo "Generated KAFKA_CLUSTER_ID: $KAFKA_CLUSTER_ID" && \
bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/kraft/reconfig-server.properties

# This replace everything globally
#RUN sed -i "s/localhost/$ADVERTISED_LISTENER/g" /opt/kafka/config/kraft/reconfig-server.properties

RUN sed -i "/advertised.listeners/s/localhost/$ADVERTISED_LISTENER/g" config/kraft/reconfig-server.properties

EXPOSE 9092 9093 

ENTRYPOINT ["/bin/bash", "-c", "bin/kafka-server-start.sh config/kraft/reconfig-server.properties"]




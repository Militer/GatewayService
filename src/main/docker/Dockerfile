FROM java:8
VOLUME /tmp
ADD gateway-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]

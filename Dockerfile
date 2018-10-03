FROM openjdk:8-jre
# copy application WAR (with libraries inside)
COPY target/session_service-0.1.0.war /app.war
# specify default command
CMD ["/usr/bin/java", "-Dspring.data.mongodb.uri=${MONGODB_URI}", "-jar", "/app.war"]
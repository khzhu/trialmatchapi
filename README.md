# REST APIs for retrieving clinical and genomic information to trials
## check out the source code from github repository
```
git clone https://github.com/khzhu/matchapi.git
```
## build from source
```
mvn -X clean compile install
```
## deploy api war file to the site
```
cp $MATCHMINER_API_HOME/target/trial-match-api-0.0.1-SNAPSHOT.war $TOMCAT_HOME/webapps/web
```
### restart tomcat
```
$TOMCAT_HOME/bin/catalina.sh stop
$TOMCAT_HOME/bin/catalina.sh start
```
### open your favorite browser and view documents online
```
http://localhost:8081/web/swagger-ui.html
```
## or query trial match API from the terminal
```
curl http://localhost:8081/web/api/matches/genes/ERBB2,BRCA2,BRCA1
```

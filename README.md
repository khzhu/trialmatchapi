#REST APIs for retrieving clinical and genomic information to matched trials
##check out the source code from github repository
```
git clone https://github.com/khzhu/matchapi.git
```
##build from source
```
mvn -X clean compile install
```
##start web service
```
java -jar target/match-api-0.0.1-SNAPSHOT.jar
```
##query api
```
curl http://localhost:8083/api/matches/id/5acb9c0243c582265b9f7734

```

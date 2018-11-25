# Elastic Recipe

https://dzehnder.github.io/hackrecipe/


## How to use the billscanner servlet:

`bildscanning/src/main/resources/dienst-code.json` does not contain valid auth credentials. In ordrder to connect to the google vision API, update them with valid credentials.
Add an environment variable called `GOOGLE_APPLICATION_CREDENTIALS`, the value is the path to the above mentioned json file.

Input Endpoint:
POST a file to `localhost:8080/upload`. The spring servlet will save it in the recource path. 

Output Endpoint:
GET `localhost:8080/scanner` will return a json with the scanned values from the scanned bill in the recource folder.


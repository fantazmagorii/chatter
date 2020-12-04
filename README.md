# Chatter
A dummy chat app

The server expose one endpoint: POST /messages/{type}
For the current example the server should support only 2 types of messages:
* send_text (e.g. http://localhost/messages/send_text)
* send_emotion (e.g. http://localhost/messages/send_emotion)

This endpoint expect one mandatory request body parameter with name payload.

#### Validation:
* In case of send_text the payload length should be between 1 and 160
* In case of send_emotion the payload should be between 2 and 10 and should not contain characters between 0 and 9
* In case the preconditions are not met the server should respond with status code 412 and empty body.
* In case the preconditions are met the server should respond with status code 201 and empty body.
* In case of invalid message type, respond is status code 400 and body: "Unknown message type"

#### Technologies
* SpringBoot app
* Gradle
* Liquibase 
* Postgres
* Docker - A composed docker image of Postgres and the app itself 

##### Launch
In chatter\src\docker\dockerCompose
```
docker-compose build
```

```
docker-compose up
```
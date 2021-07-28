# train-manager-api

## How to build and run in Docker

* Build API

  ```bash
  ./mvnw clean package -DskipTests
  ```

* Run in Docker

  ```
  docker build -t image-name:tag .
  docker run --env-file .env -p 8081:8080 --name container-name image-name
  ```

* Verifying

  ```bash
  curl localhost:8081/api/trains
  ```

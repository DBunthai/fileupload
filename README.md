#### 1. Go to the project directory
> $ cd {path}/fileupload

<br>

#### 2. Start DB and scan service
> ..fileupload$ docker-compose up -d postgres scan
 

<br>

#### 3. Run the following command to build the project
> fileupload$ ./mvnw clean && ./mvnw package

<br>

#### 4. Run the following command to start the project
> fileupload$ docker-compose up -d .

<br>

#### 5. Paste the cURL to Postman or use the cURL

```bash

curl --location 'http://localhost:8080/api/v1/file/upload' \
--header 'accept: */*' \
--header 'Content-Type: multipart/form-data' \
--form 'file=@"./src/main/resources/zfilestorage/dump/dump.txt"'

```

<br>

#### Diagrams

![img.png](img.png)
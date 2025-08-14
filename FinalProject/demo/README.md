## Demo — Spring Boot + AWS Rekognition + Amazon Lex

A Spring Boot 3 application that exposes REST endpoints for:

- Image label detection with AWS Rekognition
- Text detection (OCR) with AWS Rekognition
- Text-based conversations with Amazon Lex (V2)

### Features

- Rekognition: detect labels from uploaded images
- Rekognition: extract text (LINE/WORD) from uploaded images
- Lex: send text to a bot and return the bot’s message

### Tech Stack

- Java 21, Spring Boot 3
- AWS SDK v2 (`rekognition`, `lexruntimev2`, `s3`)
- Maven Wrapper (`./mvnw`)

### Project Layout

- `src/main/java/finalproject/demo/Config/AwsConfig.java`: AWS SDK clients config
- `src/main/java/finalproject/demo/Controller/LexController.java`: REST endpoint for Amazon Lex
- `src/main/java/finalproject/demo/Controller/RekognitionController.java`: REST endpoints for Rekognition
- `src/main/java/finalproject/demo/Service/LexService.java`: Lex integration logic
- `src/main/java/finalproject/demo/Service/RekognitionService.java`: Rekognition integration logic
- `src/main/resources/application.properties`: application config (do NOT store secrets here in production)
- `pom.xml`: dependencies and build

---

## Prerequisites

- JDK 21
- An AWS account with permissions for Rekognition and Lex V2
- A Lex V2 bot configured with known `botId`, `botAliasId`, and `localeId`

## Security note (must read)

- Never commit AWS credentials to source control. If credentials were committed, rotate them immediately in IAM.
- Prefer environment variables or an IAM role (if running on AWS) over storing secrets in `application.properties`.

## Configuration

You can map Spring properties to environment variables so you don’t commit secrets. For example, replace the sensitive values in `src/main/resources/application.properties` with placeholders like below:

```
aws.accessKeyId=${AWS_ACCESS_KEY_ID}
aws.secretAccessKey=${AWS_SECRET_ACCESS_KEY}
aws.rekognitionRegion=${AWS_REKOGNITION_REGION:us-east-2}
aws.lexRegion=${AWS_LEX_REGION:us-east-1}
aws.botId=${AWS_BOT_ID}
aws.botAliasId=${AWS_BOT_ALIAS_ID}
aws.localeId=${AWS_LOCALE_ID:en_US}
```

Then set these environment variables before running the app:

- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
- `AWS_REKOGNITION_REGION` (e.g., `us-east-2`)
- `AWS_LEX_REGION` (e.g., `us-east-1`)
- `AWS_BOT_ID`
- `AWS_BOT_ALIAS_ID`
- `AWS_LOCALE_ID` (e.g., `en_US`)

Alternatively, you can rely on the AWS Default Credentials Provider (environment variables, shared credentials file, or IAM role) and adapt `AwsConfig` to avoid explicit key injection.

## Quick start

Build and run locally:

```bash
./mvnw clean spring-boot:run
```

Or package a jar and run:

```bash
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

App default URL: `http://localhost:8080`

## API Endpoints

### 1) Detect labels from local file upload

- Method: POST
- Path: `/rekognition/labels/local`
- Content-Type: `multipart/form-data` with `file`

Example:

```bash
curl -X POST \
  -F "file=@/absolute/path/to/image.jpg" \
  http://localhost:8080/rekognition/labels/local
```

Response (example):

```json
[
  { "name": "Person", "confidence": 99.12 },
  { "name": "Car", "confidence": 95.33 }
]
```

### 2) Detect text (OCR) from local file upload

- Method: POST
- Path: `/rekognition/text/local`
- Content-Type: `multipart/form-data` with `file`

Example:

```bash
curl -X POST \
  -F "file=@/absolute/path/to/image-with-text.jpg" \
  http://localhost:8080/rekognition/text/local
```

Response (example):

```json
[
  { "detectedText": "HELLO", "confidence": 98.7, "type": "WORD" },
  { "detectedText": "WORLD", "confidence": 97.4, "type": "WORD" }
]
```

### 3) Chat with Amazon Lex (text)

- Method: POST
- Path: `/lex/text`
- Params: `text` (string)

Examples:

```bash
curl -X POST "http://localhost:8080/lex/text?text=Hello"

# or (form-encoded)
curl -X POST http://localhost:8080/lex/text -d "text=Hello"
```

Response (example):

```
Hi there! How can I help you today?
```

## Testing

```bash
./mvnw test
```

## Troubleshooting

- 403 or AccessDenied: Verify AWS credentials, IAM permissions (Rekognition + Lex), and regions.
- Lex empty messages: Ensure bot, alias, and locale IDs are correct and the bot is in Ready state.
- Region mismatch: Make sure Rekognition and Lex calls use the correct regions set in properties.

## Optional: API docs (OpenAPI/Swagger)

Add Swagger UI for live docs by including the dependency in `pom.xml`:

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.5.0</version>
  </dependency>
```

Then run the app and open:

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Optional: Generate Javadoc

```bash
./mvnw javadoc:javadoc
```

Outputs to `target/site/apidocs`.

## Deployment

- Prefer IAM roles (EC2/ECS/EKS/Lambda) over static keys.
- Set environment variables via your platform’s secret manager (e.g., AWS Secrets Manager, Parameter Store).

## License

Add your license here (e.g., MIT).

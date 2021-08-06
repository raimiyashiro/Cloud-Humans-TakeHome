# Cloud Humans Backend Take Home

## Run requirements
- IntelliJ IDEA
- Java 11 & Apache Maven

## Running through IntelliJ
- All you need to do is to start the application using the default `application.properties` profile.

## Running via Terminal
- In the root directory (at the `pom.xml` level), type:
```bash
mvn spring-boot:run
```
If you're using Windows, I encourage you to do that via [Windows Powershell](https://docs.microsoft.com/pt-br/powershell/scripting/overview?view=powershell-7.1)

## Calling the API
Make a `POST` request to `localhost:8080/api/v1/pro/evaluate`, using a JSON structure as the example below:

```json
{
  "age": 35,
  "education_level": "high_school",
  "past_experiences": {
    "sales": false,
    "support": true
  },
  "internet_test": {
    "download_speed": 50.4,
    "upload_speed": 40.2
  },
  "writing_score": 0.6,
  "referral_code": "token1234"
}

```

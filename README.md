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

## Main Technical Decisions

1. DTO

Knowing that the JSON input wouldn't follow the [Java Naming Conventions](https://www.geeksforgeeks.org/java-naming-conventions/), I decided to use the DTO [Data Transfer Object](https://en.wikipedia.org/wiki/Data_transfer_object) pattern. The library responsible for implementing the DTO is [Model Mapper](http://modelmapper.org/), and its implementation can be spotted below:

```java
public EvaluationDTO evaluate(@RequestBody @Valid ProDTO proDTO) {
        var mapper = new ModelMapper();
        var pro = mapper.map(proDTO, Pro.class);

        Evaluation evaluation = this.proService.evaluatePro(pro);
        return mapper.map(evaluation, EvaluationDTO.class);
    }
```

2. Project Architecture

As the aim of this project is to accurately return data based on the user's input, I tried to design the most efficient and readable architecture for this. I found that the following structure would fit:

```yml
src/main/java
  algorithm/ # Where the magic happens!
  controller/ # HTTP REST requests
  dto/ # Only Data Transfer Objects!
  enums/
  exception/ # Exceptions & Exception Handling
  model/ # Java Objects (or domain)
  repository/ # Just to represent a SQL Data Source
  service/ # Brings everything together :)
```

## Relevant Comments about this Project

Taking a look over the code, I think that the best part of it is how clean & maintainable the algorithm's architecture is. When I first designed the methods, I tried to apply some popular best practices that would grant me a readable code. Honestly, if I couldn't reach it, I'm pretty sure that at least I'm almost there.

Now, let us analyze some things that I'm satisfied to deliver in this project:

1. Well-written Unit Tests (see [EligibilityAlgorithmTest](https://github.com/raimiyashiro/Cloud-Humans-TakeHome/blob/master/src/test/java/com/raimiyashiro/cloudHumans/algorithm/EligibilityAlgorithmTest.java) and [ProServiceTest](https://github.com/raimiyashiro/Cloud-Humans-TakeHome/blob/master/src/test/java/com/raimiyashiro/cloudHumans/service/impl/ProServiceTest.java))
2. Input Validations (see [ProDTO](https://github.com/raimiyashiro/Cloud-Humans-TakeHome/blob/master/src/main/java/com/raimiyashiro/cloudHumans/dto/ProDTO.java))
3. The project architecture described previously
4. This rich documentation! :D

Be welcome to drop a :star2: if you want!

- My e-mail address is -> { :package:	dev.raimiyashiro@gmail.com }

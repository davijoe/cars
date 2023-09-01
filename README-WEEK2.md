# Cars 'R' Us

---
### What are the benefits of using a RESTful API
It uses the standard HTTP methods GET,POST,PUT, PATCH, and DELETE as seen in the files */api/MemberController.java* and */api/CarController.java*\
They are platform-agnostic meaning that any client that can make HTTP requests can utilize them. As such we can easily test our endpoints with the Postman application. In both week 1 and 2 we experimented with GET, POST, and PUT requests through Postman.

We used: \
GET http://localhost:8080/api/members \
POST http://localhost:8080/api/members \
PUT http://localhost:8080/api/members/user1 \
Note that a user is specified in the URL on the PUT request, since we need to specify what user we are patching information for.

We chose to format it as JSON for both insertion and extraction. RESTful supports other common formats, like XML, as well.

REST encourages a clear separation of client and server. With the use of DTO's, we have separated the data from the API. Elaborated on this in later question.

---
### What is JSON, and why does JSON fit so well with REST?
JSON is a format used for structuring and representing data. In our case, we have used it for HTTP requests from Postman. If I make a GET request on http://localhost:8080/api/members it will receive the following output:

``` json
{ 
    "username": "user1", 
    "email": "example1@example.com",
    "firstName": "Anders",
    "lastName": "Andersen",
    "street": "Eksempelvænget 1",
    "city": "Eksempelby",
    "zip": "1234",
}
```
We follow this format for PUT and POST.

JSON follows uses a syntax that closely resembles JavaScript object syntax. At least, ChatGPT told me so. And it mentioned it as a good thing, so 'yay'. We will delve into JS later in the semester. For now I believe Chatty.

It is quite easy to read, which obviously makes development faster and less error prone. It doesn't support comments, however. Which means we need to finesse it with some comment data if we need comments.
``` JSON
{
    "_comment": "JSON should support commenting"
}
```
---
### How have you designed simple CRUD endpoints using spring boot and DTOs to separate api from data
Focus on your use of DTOs\
**Create**
- New cars are created using information from the CarRequest DTO
- Endpoint is mapped to "/api/cars"
- I use @RequestBody from the Spring Framework to indicate that the parameter is bound to a HTTP request

**Read**
- I use GetMappings for retrieve a list of cars (/api/cars) or a specific car (/api/cars/5).
- The @PathVariable (Spring Framework) is used to capture "id" in the URL.

**Update**
- I called my methods edit instead of update
- Like Create/POST, they too use the CarRequest DTO.
- Since update/edit is done on a single car at a time in this project, @PathVariable is used again.
- Endpoints are mapped to /api/cars/{id}

**Delete**
- Used to delete existing car by id. 
- Example: /api/cars/5

With this setup, the **CarService** class interacts with the **CarRepository** and perform the CRUD operations 
on the database. The **CarController** handles the incoming HTTP requests and delegate the operations to the **CarService**.
---
### What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
1) In this project, it lets me control the data exposure towards the API consumer. When GET requests are send 
to /api/members and /api/cars, only the data specified in MemberResponse and CarResponse is show. Password are always excluded for members
while "ranking" and "approved" status is only shown when boolean IncludeAll=true

@JsonInclude(JsonInclude.Include.NON_NULL) was also used to remove properties with a null value from the JSON output

2) If we need to change the API in a way that doesn't affect the entities, we can simply modify only the DTO's.
Vice versa, should the domain model need to be changed, we can do that without changing the DTO's.
3) The API receives only the needed data instead of fetching all fields from the domain model resulting in 
better optimized application.
---
### Explain shortly the concept mocking in relation to software testing
Mocking refers to creating simulated objects (mock objects). 

In software testing related to troubleshooting, we use it to isolate issues within our application and thereby (hopefully)
resolve our issues quickly and efficiently. When testing as part of our Test-driven development (TDD) approach, it helps us
isolate the unit we are testing from its dependencies, and as such we can ensure that the unit we are testing
is in fact the primary focus of the test.



---
### How did you mock database access in your tests, using an in-memory database and/or mockito
Refer to your code

---

### Explain the concept Build Server and the role GitHub Actions play here
Summary and / or project reference

---

### Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
Technical use case summary + Refer to project

---

### Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects

*TL:DR*

GitHub (SaaS) for everything. Otherwise, mostly on-premise for dev, and mostly PaaS for prod. Long answer below.

#### Developer profile:
* Almost solely on-premise

*I have used On-premise for my local development and testing environment. My MySQL database is hosted locally on my developer machine as well as the application being build with Maven and then hosted on a local Apache Tomcat web server. It is then accessible on through localhost*

*However, I used GitHub too. GitHub is SaaS.*

#### Production profile:
* IaaS

*I could have used Azure VM's and created a Linux VM, set it up with Java, Maven, etc. and deployed my application there, but this week we worked with Azure App Service for deployment*

* PaaS

*Azure App Service is a managed PaaS used for building and deploying web applications and API's. I deployed my application with Azure App Service directly from my master branch in GitHub. It was configured with runtime stack "Java 17" as Lars has defined as company policy. I used a Java SE embedded web server and not Apache Tomcat as we usually do. I just followed along when it was set up, but according to my web-tutor "Chat GPT", Java SE Embedded Web Server is better for small and simple web applications because it easy to use while also being resource efficient*

* SaaS

**When your GitHub project gets starred:* \
[How to handle fame](https://letmegooglethat.com/?q=How+to+handle+fame&l=1)

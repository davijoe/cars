# Cars 'R' Us
### What are the benefits of using a RESTful API
It uses the standard HTTP methods GET,POST,PUT, PATCH, and DELETE as seen in the files */api/MemberController.java* and */api/CarController.java*\
They are platform-agnostic meaning that any client that can make HTTP requests can utilize them. As such we can easily test our endpoints with the Postman application. In both week 1 and 2 we experimented with GET, POST, and PUT requests through Postman.

We used: \
GET http://localhost:8080/api/members \
POST http://localhost:8080/api/members \
PUT http://localhost:8080/api/members/user1 \
Note that a user is specified in the URL on the PUT request, since we need to specify what user we are patching information for.

We chose to format it as JSON for both insertion and extraction. RESTful supports other common formats, like XML, as well.

REST encourages a clear separation of client and server. In this project 

### What is JSON, and why does JSON fit so well with REST?
   Your short summary here
### How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data
   Focus on your use of DTOs
### What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
   Your short summary here
### Explain shortly the concept mocking in relation to software testing
   Your short summary here
### How did you mock database access in your tests, using an in-memory database and/or mockito
   Refer to your code
### Explain the concept Build Server and the role GitHub Actions play here
   Summary and / or project reference
### Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
   Technical use case summary + Refer to project
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

*When your GitHub project gets starred:*

*https://letmegooglethat.com/?q=How+to+handle+fame&l=1*


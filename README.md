## Customer Rewards API
This application uses Spring boot and other auxiliary library to create an API that returns the rewards points for customer based on certain business logic.

### How to run the application?
```shell
mvn spring-boot:run
```
### Build tool & Dependencies
This is a maven project tested against JDK1.8 and JDK11. Spring boot version 2.7.5

### Test
Junit, Mockito and Spring MVC mock libraries are used for testing
```shell
mvn test
```

### How to hit the API endpoint
GET is the HTTP method used for endpoint as its a non state changing call. Customer ID is passed as part of the URL. If non existing customer ID is passed the API return 0 as rewards, It doesn't say wheher a user exist or not.
[http://127.0.0.1:8080/customer/get_rewards/1](http://127.0.0.1:8080/customer/get_rewards/1)

As a sample seed data. There are 2 customers with IDs 1,2. Rewards point for two customers are calculated accordingly. Init data is available in the path resources/data.sql

#### Response
Response body will contain JSON with rewards field and customer_id field
```json
{
rewards: 60,
customer_id: 1
}
```
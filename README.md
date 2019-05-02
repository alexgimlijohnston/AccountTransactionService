# AccountTransactionService

**Run Configurations:**

Go to: /AccountTransactionService/services


Run: java -jar target/services-1.0-SNAPSHOT.jar server config.yaml

**Endpoints**

POST    /account/ 
```
{
	"accountId":10016, 
	"sortCode":"50-60-23", 
	"balance":2000, 
	"currency":"GBP", 
	"overdraftAmount":100, 
	"lastModifiedTime":"2019-04-02T02:09:18.493+01:00"
}
```

GET     /account/{id} 

POST    /customer/ 
```
{
    "customerId": 900,
    "firstName": "Alex",
    "lastName": "John",
    "address": "London",
    "number": "07798435677",
    "lastModifiedTime": "2019-05-02 18:35:06"
}
```

GET     /customer/{id} 

POST    /transaction/ 
```
{
	"senderAccountId":10012,
	"receiverAccountId":10016,
	"amount":20,
	"currency":"USD"
}
```


**Libraries** 

log4j, junit, h2, hibernate, mockito, powermock, mysql, okhttp3, junit, powermock, dropwizard-test


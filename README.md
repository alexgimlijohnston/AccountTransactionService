# Account Transaction Service

A microservice implementing a RESTful API for money transfers between accounts. Transfers between accounts of differing currencies is also supported.

## Getting Started

`git clone https://github.com/alexgimlijohnston/AccountTransactionService.git`

`mvn install` 

Go to: `cd AccountTransactionService/services`

Run: `java -jar target/services-1.0-SNAPSHOT.jar server config.yaml`

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* log4j, junit, h2, hibernate, mockito, powermock, mysql, okhttp3, junit, powermock, dropwizard-test

## Endpoints

* **GET /account/{id}**
	* Example account id: 10012

* **POST /account/** 
	* Example input json:
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
* **GET /customer/{id}**
	* Example customer id: 400

* **POST /customer/**
	* Example input json:
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
	
* **POST /transaction/**
	* Example input json:
	```
	{
		"senderAccountId":10012,
		"receiverAccountId":10016,
		"amount":20,
		"currency":"USD"
	}
	```


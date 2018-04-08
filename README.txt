Development Technologies:

Java, Spring, Servlet
RESTful: Jersey, Jackson, JSON
Persistence: Hibernate, HSQLDB 
TDD: Junit
Logging: Logback


Create Offer (REST POST):  
http://localhost:8080/merchant-app/rest/offer/create

When create new offer:
	Mandatory input parameters are checked (null or empty)
	Expire date is checked (invalid date format, date time range validation)
	Offer status set to ACTIVE

Json Examples:
{
	"name" : "Banana",
	"price" : 1.2,
	"currency" : "GBP",
	"expireDate" : "2018-04-12 09:00:00",
	"userDescription" : "Equator banana"
}

{
	"name" : "Apple",
	"price" : 2.1,
	"currency" : "EUR",
	"expireDate" : "2018-04-15 20:30:00",
	"userDescription" : "Green apple"
}

{
	"name" : "Cherry",
	"price" : 1.8,
	"currency" : "USD",
	"expireDate" : "2018-08-15 20:30:00",
	"userDescription" : "Sour cherry"
}

{
	"name" : "Milk",
	"price" : 2.3,
	"currency" : "TRY",
	"expireDate" : "2018-05-15 18:30:00",
	"userDescription" : "Fresh Milk"
}


List Offers (REST GET):
http://localhost:8080/merchant-app/rest/offer/list

When get offer list, for each ACTIVE status offer expire date is checked and if offer is expired its status is updated to EXPIRED and persisted to DB and reflect this EXPIRED status to response.


Cancel Offer (REST POST):
http://localhost:8080/merchant-app/rest/offer/cancel/{offerId}

When cancel offer:
	Check if offer exists in DB (if not throws Offer {0} cannot be found exception)
	Check if offer is expired (if offer is expired throws Offer {0} is expired exception)
	Check if offer is already cancelled (if offer is already cancelled throws Offer {0} is already canceled exception)
	Otherwise set cancelDate to offer and update offer status to CANCELED and persist to DB.
	
	
Get Offer (REST GET):
http://localhost:8080/merchant-app/rest/offer/get/{offerId}

When get offer:
	Check if offer exists in DB (if not throws Offer {0} cannot be found exception)
	if offer status is ACTIVE, offer expire date is checked and if offer is expired its status is updated to EXPIRED and persisted to DB and reflect this EXPIRED status to response.
	

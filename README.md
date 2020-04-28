## A Spring Security Using JPA 

#### User Registration 

Api 1. ->POST http://localhost:8080/users

**Must provides addresses.**

```
{
 
     "firstName": "Alex",
     "lastName": "Bob",
     "email": "alex@gmail.com",
     "password": "1234",

     "addressList": [
         {
             "city": "XXX",
             "country": "AUS",
             "streetName": "YYY",
             "postalCode": "1231",
             "type": "Billing"
         },
         {
             "city": "ZZZZ",
             "country": "BD",
             "streetName": "Upadi",
             "postalCode": "1231",
             "type": "Shipping"
         }
     ]
 }
```

####User Login

API 2 -> POST http://localhost:8080/users/login

```
{
   "email" : "alex@gmail.com",
   "password" : "1234"
}
```

1. Collect Token from Headers in postman
format Will be : 

> Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvdmlAZ2ZnbWFpbC5jb20iLCJleHAiOjE1ODgxNjg1MTZ9.tU9Z_sL3008vNlxbT5Ci_qfI9xp_DgsDc-tq_wKpvo-LiDWeo3MuP5Of8TSs-Y1BVxc_MstNr9IIRSqOWbrt_Q


#### Get User Information

API -> GET http://localhost:8080/users/{userId}
API -> GET http://localhost:8080/users/AGbzEYyOTFm8hdWHotCmliZm5Yugpk/addresses
APi -> GET http://localhost:8080/users/AGbzEYyOTFm8hdWHotCmliZm5Yugpk/addresses/{addressId}

For performing user information we need to a insert a token in headers 

2 . Added Token in headers like key value pair.

```
KEY :  Authentication 
value : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvdmlAZ2ZnbWFpbC5jb20iLCJleHAiOjE1ODgxNjg1MTZ9.tU9Z_sL3008vNlxbT5Ci_qfI9xp_DgsDc-tq_wKpvo-LiDWeo3MuP5Of8TSs-Y1BVxc_MstNr9IIRSqOWbrt_Q

```

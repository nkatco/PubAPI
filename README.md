## Pub API
API for a Pub, Restaurant or any other catering.
This project was created for educational purposes, so it has a combined IP and Admin page, which are available at "http://localhost:8062".

<img src="https://github.com/nkatco/PubAPI/assets/67635748/3f536c32-309b-46e6-a5a6-02a90df525cc" alt="drawing" width="550" height="250"/>

### The project uses libraries such as:

1. Spring-boot;
2. Spring-security;
3. Spring-web;
4. Spring-thymeleaf;
5. JWT;
6. Logback slf4j;
7. Lombok.

API allows you to get products by category or any other parameters or preferences. Information about products can be obtained by `/raiden-api/v1/menu`. Also, there is a model of events and posters, information about which can be obtained by "/raiden-api/v1/event". 
The API has no authorization requirements and was created to display products in any service. 

The admin page has an authorization requirement `(admin:KZ2T8zkH)`, you can:
- create or delete a product in it;
- change the product data or assign it a new category;
- create or delete a category.

If you want to use this project for your own purposes, you need to install **MongoDB**. The admin page is available at `http://localhost:8062/login` and `http://localhost:8062/admin`. API is available at `http://localhost:8062/raiden-api/`

# Pub API
API for a Pub, Restaurant or any other catering.
This project was created for educational purposes, so it has a combined IP and Admin page, which are available at "http://localhost:8062".

![image](https://github.com/nkatco/PubAPI/assets/67635748/3f536c32-309b-46e6-a5a6-02a90df525cc) 

The project uses libraries such as:

1. spring-boot;
2. spring-security;
3. spring-web;
4. spring-thymeleaf;
5. jwt;
6. logback slf4j;
7. lombok.

API allows you to get products by category or any other parameters or preferences. Information about products can be obtained by `/raiden-api/v1/menu`. Also, there is a model of events and posters, information about which can be obtained by "/raiden-api/v1/event". 
The API has no authorization requirements and was created to display products in any service. 

The admin page has an authorization requirement `(admin:KZ2T8zkH)`, you can:
- create or delete a product in it;
- change the product data or assign it a new category;
- create or delete a category.

If you want to use this project for your own purposes, you need to install **MongoDB**. The admin page is available at `http://localhost:8062/login` and `http://localhost:8062/admin`. API is available at `http://localhost:8062/raiden-api/`

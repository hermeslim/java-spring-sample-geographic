# java-spring-sample-geographic
1. Git clone into your local
2. switch/checkout to master branch 
3. You will see wccgroup-java
4. Import into your IDE as maven project
5. Let your IDe auto build all the dependencies.
6. This test do not need to install any DB (using h2 memory to run it)
7. Run the applications 
8. After spring has started. In the console copy out the jdbc h2 mem to access the database
 _INFO 10992 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at '**jdbc:h2:mem:5512fb9f-1d8b-4e0b-8099-264e8610941e**'_
 
# h2-console
9. To go to the h2-console you can go to localhost:8080/h2-console 
10. If they request for authenticate type in username: user password: password
11. change the JDBC URL that u copied to <---hdbc:h2:mem: --->
12. then you are able to access the database


# Unit test
1. Right click run all the unit test from the project

# Postman test 
1. use post man to test the REST API
2. configuration: authorization type Basic Auth 
3. username: user & password: password
4. example body request get _localhost:8080/postCode_
{
    "postCode1" : "AB10 1XG",
    "postCode2" : "AB12 9SP"
}

5. example body request put _localhost:8080/postCode/AB12 9SP_
{
    "postCode" : "AB12 9SP",
    "latitude" : "87.148707",
    "longtitude" : "-2.097806"
}


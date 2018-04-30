Booking Microservice

1. Execute the following sql queries to create the database in MySQL

create table bookings (id bigint not null auto_increment, number_of_days integer, plate_number varchar(255), username varchar(255), primary key (id)) engine=InnoDB

2. Update the username and password in the properties files accordingly

3. Supply the profile(dev or qa) and run the service as Spring Boot application

4. Run the integration test as JUnit test
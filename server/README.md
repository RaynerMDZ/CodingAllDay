# Coding All Day Server

To run this server you will need to create a "resources" folder inside the "src/main" path. Then, you will have to create an "application.properties" file inside the "resources" folder.

Then, you will have to modify the content inside the "application.properties" file.

### Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url = jdbc:mysql://localhost:3306/PersonalBlog
spring.datasource.username = ########
spring.datasource.password = ***************

### Hibernate Properties
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
spring.jpa.database-platform = org.hibernate.dialect.MySQL57Dialect
spring.jpa.show-sql = true

### Hibernate ddl auto=update will automatically create the tables
spring.jpa.hibernate.ddl-auto = update


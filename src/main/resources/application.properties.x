welcome.message= \u4E16\u754C\u4F60\u597D

###:In the development process, the cache is usually closed
###:to ensure that the data can be responded to in time during the testing process.
spring.thymeleaf.cache=false


###:for log
#logging.level.org.springframework.web=INFO


###:for Application.java ---> main method
# server.port=0
# server.port=8080


###:for DB - datasource - MySQL
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.url=jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei
spring.datasource.url=jdbc:mysql://localhost:3306/JAVA_FRAMEWORK
spring.datasource.username=root
spring.datasource.password=Capoo840305
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.connection-timeout=30000
#spring.datasource.jndi-name=jdbc/TestDB3

# JPA config
spring.jpa.show-sql=true

# Hibernate config
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
#spring.jpa.hibernate.ddl-auto=none


# Spring Web config
server.servlet.context-path=/cga105g2
# Spring MVC config
spring.mvc.view.prefix=/
# Logback config
logging.level.tw.idv.anthony=debug
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%level] [%t] %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} [%level] [%t] [%line] %msg%n
logging.file.path=./

###:(default)
# spring.thymeleaf.prefix=classpath:/templates/
# spring.thymeleaf.suffix=.html
# spring.thymeleaf.encoding=UTF-8


###:for Upload capacity limit (default)
# spring.servlet.multipart.max-file-size=1MB
# spring.servlet.multipart.max-request-size=10MB
# spring.servlet.multipart.file-size-threshold=0MB


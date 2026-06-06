package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;

public class JPAProperties {

    public static void main(String[] args) {

        SpringApplication.run(JPAProperties.class, args);
        /**
         *  # MySQL DB JDBC start
         *
         * spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
         * spring.datasource.url=jdbc:mysql://localhost/product-catalogue-wishlist-service?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true
         * spring.datasource.username=root
         * spring.datasource.password=root
         * spring.jpa.hibernate.ddl-auto=update
         * spring.jpa.generate-ddl=true
         *
         * # Hibernate Properties
         *
         * spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
         * #below properties will automatically create and updates database schema
         * spring.jpa.properties.hibernate.hbm2ddl.auto=update
         * spring.jpa.show-sql=true
         * spring.jpa.properties.hibernate.format_sql=true
         * spring.jpa.properties.hibernate.connection.pool_size=5
         * spring.jpa.properties.hibernate.current_session_context_class=thread
         * # MySQL DB JDBC end
         *
         */

    }

}

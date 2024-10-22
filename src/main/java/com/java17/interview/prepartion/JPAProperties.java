package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;

public class JPAProperties {

    public static void main(String[] args) {

        SpringApplication.run(JPAProperties.class, args);
        /**
         *  spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
         *         spring.datasource.url=jdbc:mysql://localhost/test
         *         spring.datasource.username=
         *         spring.datasource.password=
         *         spring.jpa.generate-ddl=true
         *         spring.jpa.hibernate.show_sql=true
         *         spring.jpa.hibernate.format_sql=true
         *         spring.jpa.hibernate.hbm2ddl.auto=update
         *
         *         spring.jpa.hibernate.connection.pool_size=5
         *         spring.jpa.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
         *
         */

    }

}

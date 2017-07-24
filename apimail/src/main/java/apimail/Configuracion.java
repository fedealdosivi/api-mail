package apimail;

/**
 * Created by fefe on 14/6/2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@Configuration
public class Configuracion {


    @Autowired
    AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }

    @Bean
    public Connection getConnection(@Value("${db.host}") String host, @Value("${db.name}") String dbName, @Value("${db.username}") String user, @Value("${db.password}") String password){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+host + ":3306/", user, password);

            Statement st = connection.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
            connection.close();
            connection = DriverManager.getConnection("jdbc:mysql://"+host + "/" + dbName, user, password);
            return connection;
        }
        catch (Exception e){
            System.out.print("DAO connection failed.");
            return null;
        }
    }


}

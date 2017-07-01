/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author fefe
 */
@Repository
public class Conexion<T> {
    private Connection conn;

    public Conexion(@Value("${db.username}") String dbUserName, @Value("${db.name}") String dbName, @Value("${db.password}") String dbPassword, @Value("${db.port}") String dbPort, @Value("${db.host}") String dbHost) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            setConn((Connection) DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName,  dbUserName, dbPassword));
        }catch(SQLException e){
            e.getStackTrace();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}

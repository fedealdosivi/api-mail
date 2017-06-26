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
public abstract class Conexion<T> {
    protected Connection conn;

    public Conexion() {

    }

    public Conexion(String dbUsername, String dbName,String dbPassword, String dbPort, String dbHost) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName,  dbUsername, dbPassword);
        }catch(SQLException e){
            e.getStackTrace();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

}

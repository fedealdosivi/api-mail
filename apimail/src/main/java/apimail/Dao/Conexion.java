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
 *
 * @author fefe
 */
@Repository
public class Conexion {
    private Connection conn;
    //private static Conexion instancia;


    //Patron Singleton
    /*
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }*/
    
    //Verifica los drivers en la conexion
    public Conexion() {
        try {
            this.verificarDriver();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //Conecta la base de datos con getConnection y los datos de nuestra base de datos, en caso de no poder ejectura la Excepcion
    public void conectar() throws SQLException {
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mail", "root", "123456");
        } catch (SQLException e) {
            System.err.println("SQLexception: " + e.getMessage());
            throw e;
        }
    }

    //verifica con el class.forName si el driver es el correcto
    private void verificarDriver() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
            throw e;
        }
    }
    
     //Intenta desconectar la base de datos, en caso de no poder arroja excepcion
    public void desconectar() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //get que retorna la conexion
    public Connection getConn() {
        return conn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;

import apimail.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author fefe
 */
@Repository
public class DaoUsuarios extends AbstractDao{

    @Autowired
    public DaoUsuarios(Connection connection) {
        super(connection);
    }

    public void cargarUsuario(Usuario user) {
        try {
            String query = "CALL saveUser(?,?,?,?,?,?,?,?,?)";
            CallableStatement st = this.connection.prepareCall(query);
            st.setString(1, user.getNombre());
            st.setString(2, user.getApellido());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getDireccion());
            st.setInt(6, user.getTelefono());
            st.setString(7, user.getPais());
            st.setString(8, user.getProvincia());
            st.setString(9, user.getCiudad());
            st.execute();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public ArrayList<Usuario> traerTodos() {
        ArrayList<Usuario> lista = new ArrayList();
        try {
            String sq = "CALL getAllUsers()";
            CallableStatement st = this.connection.prepareCall(sq);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            } else {
                System.out.println("trajo cosas");
                while (rs.next()) {
                    Usuario user = new Usuario();
                    user.setId(rs.getInt("IDUSER"));
                    user.setNombre(rs.getString("NAME"));
                    user.setApellido(rs.getString("SURNAME"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setDireccion(rs.getString("ADRESS"));
                    user.setTelefono(rs.getInt("CELLPHONE"));
                    user.setPais(rs.getString("COUNTRY"));
                    user.setProvincia(rs.getString("STATE"));
                    user.setCiudad(rs.getString("CITY"));
                    lista.add(user);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
        return lista;
    }

    public void eliminarUsuario(int id) {
        try {
            String sq = "CALL deleteUser(?)";
            CallableStatement st = this.connection.prepareCall(sq);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException es) {
            es.getStackTrace();
        }
    }

    public Usuario traerUsuarioPorId(int id) {
        Usuario user = null;

        try {
            String sq = "CALL getUserById(?)";
            CallableStatement st = this.connection.prepareCall(sq);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("IDUSER"));
                user.setNombre(rs.getString("NAME"));
                user.setApellido(rs.getString("SURNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setDireccion(rs.getString("ADRESS"));
                user.setTelefono(rs.getInt("CELLPHONE"));
                user.setPais(rs.getString("COUNTRY"));
                user.setProvincia(rs.getString("STATE"));
                user.setCiudad(rs.getString("CITY"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return user;
    }

    public Usuario validarUsuario(String email, String password) {
        Usuario user = null;

        try {
            String sq = "CALL login(?,?)";
            CallableStatement st = this.connection.prepareCall(sq);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("IDUSER"));
                user.setNombre(rs.getString("NAME"));
                user.setApellido(rs.getString("SURNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setDireccion(rs.getString("ADRESS"));
                user.setTelefono(rs.getInt("CELLPHONE"));
                user.setPais(rs.getString("COUNTRY"));
                user.setProvincia(rs.getString("STATE"));
                user.setCiudad(rs.getString("CITY"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return user;
    }

    public Usuario traerUserPorNombre(String nombre) {
        Usuario user = null;
        try {

            String sq = "CALL getUserByName(?)";
            CallableStatement st = this.connection.prepareCall(sq);
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("IDUSER"));
                user.setNombre(rs.getString("NAME"));
                user.setApellido(rs.getString("SURNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setDireccion(rs.getString("ADRESS"));
                user.setTelefono(rs.getInt("CELLPHONE"));
                user.setPais(rs.getString("COUNTRY"));
                user.setProvincia(rs.getString("STATE"));
                user.setCiudad(rs.getString("CITY"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return user;
    }
}

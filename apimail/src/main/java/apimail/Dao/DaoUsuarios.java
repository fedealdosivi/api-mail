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
public class DaoUsuarios{

    @Autowired
    private
    Conexion conn;

    public void cargarUsuario(Usuario user) {
        try {
            String query = "insert into USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = getConn().getConn().prepareStatement(query);
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
            String sq = "select * from USUARIOS";
            PreparedStatement st = getConn().getConn().prepareStatement(sq);
            ResultSet rs = st.executeQuery();
            if (rs == null) {
                System.out.println(" No hay registros en la base de datos");
            } else {
                System.out.println("trajo cosas");
                while (rs.next()) {
                    Usuario usuarios = new Usuario();
                    usuarios.setId(rs.getInt("IDUSUARIO"));
                    usuarios.setNombre(rs.getString("NOMBRE"));
                    usuarios.setApellido(rs.getString("APELLIDO"));
                    usuarios.setPassword(rs.getString("PASSWORD"));
                    usuarios.setEmail(rs.getString("EMAIL"));
                    usuarios.setDireccion(rs.getString("DIRECCION"));
                    usuarios.setTelefono(rs.getInt("TELEFONO"));
                    usuarios.setPais(rs.getString("PAIS"));
                    usuarios.setProvincia(rs.getString("PROVINCIA"));
                    usuarios.setCiudad(rs.getString("CIUDAD"));
                    lista.add(usuarios);
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
            String sq = "delete from USUARIOS where IDUSUARIO=?";
            PreparedStatement st = getConn().getConn().prepareStatement(sq);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException es) {
            es.getStackTrace();
        }
    }

    public Usuario traerUsuarioPorId(int id) {
        Usuario user = null;

        try {
            String sq = "select * from USUARIOS where IDUSUARIO=?";
            PreparedStatement st = getConn().getConn().prepareStatement(sq);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("IDUSUARIO"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellido(rs.getString("APELLIDO"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setDireccion(rs.getString("DIRECCION"));
                user.setTelefono(rs.getInt("TELEFONO"));
                user.setPais(rs.getString("PAIS"));
                user.setProvincia(rs.getString("PROVINCIA"));
                user.setCiudad(rs.getString("CIUDAD"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return user;
    }

    public Usuario validarUsuario(String email, String password) {
        Usuario user = null;

        try {
            String sq = "select * from USUARIOS where EMAIL=? and PASSWORD=?";
            PreparedStatement st = getConn().getConn().prepareStatement(sq);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("IDUSUARIO"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellido(rs.getString("APELLIDO"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setDireccion(rs.getString("DIRECCION"));
                user.setTelefono(rs.getInt("TELEFONO"));
                user.setPais(rs.getString("PAIS"));
                user.setProvincia(rs.getString("PROVINCIA"));
                user.setCiudad(rs.getString("CIUDAD"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return user;
    }

    public Usuario traerUserPorNombre(String nombre) {
        Usuario user = null;
        try {

            String sq = "select * from USUARIOS where NOMBRE LIKE ?";
            PreparedStatement st = getConn().getConn().prepareStatement(sq);
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("IDUSUARIO"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellido(rs.getString("APELLIDO"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setDireccion(rs.getString("DIRECCION"));
                user.setTelefono(rs.getInt("TELEFONO"));
                user.setPais(rs.getString("PAIS"));
                user.setProvincia(rs.getString("PROVINCIA"));
                user.setCiudad(rs.getString("CIUDAD"));
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return user;
    }

    public Conexion getConn() {
        return conn;
    }

    public void setConn(Conexion conn) {
        this.conn = conn;
    }
}

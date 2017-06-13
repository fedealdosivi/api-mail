/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;
import apimail.Model.Usuario;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fefe
 */
@Repository
public class DaoUsuarios {

    @Autowired
    Conexion conn;
    
    /*public DaoUsuarios()
    {
        conn = Conexion.getInstancia();
    }*/
    
    public void cargarUsuario(Usuario user)
    {
        try{
            String query = "insert into USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,IDUSUARIO,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values (?,?,?,?,?,?,?,?,?,?)";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(query);
            st.setString(1,user.getNombre());
            st.setString(2,user.getApellido());
            st.setString(3,user.getEmail());
            st.setString(4,user.getPassword());
            st.setInt(5,user.getId());
            st.setString(6,user.getDireccion());
            st.setInt(7,user.getTelefono());
            st.setString(8,user.getPais());
            st.setString(9,user.getProvincia());
            st.setString(10,user.getCiudad());
            st.execute();
        }
        
        catch(Exception e)
        {
            e.getStackTrace();
        }
        
        finally{
            try {
                conn.desconectar();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
    
    public ArrayList<Usuario> traerTodos()
    {
        ArrayList<Usuario> lista = new ArrayList();
        try
        {
            String sq = "select * from USUARIOS";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            ResultSet rs = st.executeQuery();
            if (rs == null) 
            {
                System.out.println(" No hay registros en la base de datos");
            } 
            else 
            {
                System.out.println("trajo cosas");
                while (rs.next()) 
                {
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
        }
        
        catch(Exception e)
        {
            e.getStackTrace();
            return null;
        }
        
        finally
        {
            try 
            {
                conn.desconectar();
            } 
            catch (Exception x) 
            {
                x.getStackTrace();
            }
        }
        return lista;
    }
    
    public void eliminarUsuario(Usuario user)
    {
        try {
            String sq = "delete from USUARIOS where IDUSUARIO=?";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, user.getId());
            st.execute();
        } catch (SQLException es) {
            es.getStackTrace();
        } finally {
            try {
                conn.desconectar();
            } catch (Exception s) {
                s.getStackTrace();
            }
        }
    }
    
    public Usuario traerUsuarioPorId(int id)
    {
        Usuario user=null;

        try{
            String sq = "select * from USUARIOS where IDUSUARIO=?";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if(rs.next())
            {
                user=new Usuario();
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
        }

        catch(SQLException e)
        {
            e.getStackTrace();
        }

        finally {
            try {
                conn.desconectar();
            } catch (Exception s) {
                s.getStackTrace();
            }
        }

        return user;
    }
    
    public Usuario validarUsuario(String email,String password)
    {
        Usuario user=null;

        try{
            String sq = "select * from USUARIOS where EMAIL=? and PASSWORD=?";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(sq);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if(rs.next())
            {
                user=new Usuario();
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
        }

        catch(SQLException e)
        {
            e.getStackTrace();
        }

        finally {
            try {
                conn.desconectar();
            } catch (Exception s) {
                s.getStackTrace();
            }
        }

        return user;
    }
    
}

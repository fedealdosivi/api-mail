/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Dao;
import apimail.Model.Usuario;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fefe
 */
@Service
public class DaoUsuarios {
    
    private Conexion conn;
    
    public DaoUsuarios()
    {
        conn = Conexion.getInstancia();
    }
    
    public void cargarUsuario(Usuario user)
    {
        try{
            String query = "insert into USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,NICKNAME,IDUSUARIO) values (?,?,?,?,?,?)";
            conn.conectar();
            PreparedStatement st = conn.getConn().prepareStatement(query);
            st.setString(1,user.getNombre());
            st.setString(2,user.getApellido());
            st.setString(3,user.getEmail());
            st.setString(4,user.getPassword());
            st.setString(5,user.getNickName());
            st.setInt(6,user.getId());
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
                    usuarios.setNickName(rs.getString("NICKNAME"));
                    usuarios.setEmail(rs.getString("EMAIL"));
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
                x.printStackTrace();
            }
        }
        return lista;
    }
    
    public void eliminarUsuario(Usuario user)
    {
        //TODO
    }
    
    public Usuario traerUsuarioPorId(int id)
    {
        //TODO
        return null;
    }
    
    public Usuario traerUsuarioIdentico(Usuario u)
    {
        //TODO
        return null;
    }
    
}

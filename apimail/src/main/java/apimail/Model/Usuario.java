/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apimail.Model;

/**
 *
 * @author fefe
 */
public class Usuario {
    
    private int id;
    private static int nextid = 0;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String nickName;
    
    public Usuario() {
        id = nextid++;
        nombre = "";
        apellido="";
        email="";
        password="";
        nickName="";
    }
    
    public Usuario(String nombre,String apellido,String email,String password,String nickName){
        this.nombre=nombre;
        this.apellido=apellido;
        this.email=email;
        this.password=password;
        this.nickName=nickName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getNextid() {
        return nextid;
    }

    public static void setNextid(int nextid) {
        Usuario.nextid = nextid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 17 * hash + (this.apellido != null ? this.apellido.hashCode() : 0);
        hash = 17 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 17 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 17 * hash + (this.nickName != null ? this.nickName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.apellido == null) ? (other.apellido != null) : !this.apellido.equals(other.apellido)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.nickName == null) ? (other.nickName != null) : !this.nickName.equals(other.nickName)) {
            return false;
        }
        return true;
    }
    
}

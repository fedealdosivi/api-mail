
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
    private String direccion;
    private int telefono;
    private String pais;
    private String provincia;
    private String ciudad;
    
    public Usuario() {
        setId(nextid++);
        setNombre("");
        setApellido("");
        setEmail("");
        setPassword("");
        setTelefono(0);
        setDireccion("");
        setPais("");
        setProvincia("");
        setCiudad("");
    }
    
    public Usuario(String nombre,String apellido,String email,String password,String direccion, int telefono,String pais, String provincia, String ciudad){
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setPassword(password);
        this.setCiudad(ciudad);
        this.setDireccion(direccion);
        this.setTelefono(telefono);
        this.setPais(pais);
        this.setProvincia(provincia);
    }
    


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != usuario.id) return false;
        if (telefono != usuario.telefono) return false;
        if (!nombre.equals(usuario.nombre)) return false;
        if (!apellido.equals(usuario.apellido)) return false;
        if (!email.equals(usuario.email)) return false;
        if (!password.equals(usuario.password)) return false;
        if (!direccion.equals(usuario.direccion)) return false;
        if (!pais.equals(usuario.pais)) return false;
        if (!provincia.equals(usuario.provincia)) return false;
        return ciudad.equals(usuario.ciudad);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + apellido.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + telefono;
        result = 31 * result + pais.hashCode();
        result = 31 * result + provincia.hashCode();
        result = 31 * result + ciudad.hashCode();
        return result;
    }
}

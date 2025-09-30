
package CapaEntidad;


public class Usuario {
    private int idUsuario;
    private String nombreUsuario, apellidoPaterno, nombres, contrasena, perfil;
    private String estado;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String apellidoPaterno, String nombres, String contrasena, String perfil, String estado) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
        this.nombres = nombres;
        this.contrasena = contrasena;
        this.perfil = perfil;
        this.estado = estado;
    }

    public Usuario(int idUsuario, String nombreUsuario, String apellidoPaterno, String nombres, String contrasena, String perfil, String estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
        this.nombres = nombres;
        this.contrasena = contrasena;
        this.perfil = perfil;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

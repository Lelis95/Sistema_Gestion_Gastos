/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaEntidad;

/**
 *
 * @author Lelis Carlos
 */
public class Proveedor {
    
    private int idProveedor,idDistrito,idProvincia,idDepartamento;
    private String ruc,razonSocial,direccion,telefono,celular,correo;
    
    //Get y Set

    /**
     * @return the idProveedor
     */
    public int getIdProveedor() {
        return idProveedor;
    }

    /**
     * @param idProveedor the idProveedor to set
     */
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * @return the idDistrito
     */
    public int getIdDistrito() {
        return idDistrito;
    }

    /**
     * @param idDistrito the idDistrito to set
     */
    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    /**
     * @return the idProvincia
     */
    public int getIdProvincia() {
        return idProvincia;
    }

    /**
     * @param idProvincia the idProvincia to set
     */
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    /**
     * @return the idDepartamento
     */
    public int getIdDepartamento() {
        return idDepartamento;
    }

    /**
     * @param idDepartamento the idDepartamento to set
     */
    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    //Constructores
    
    public Proveedor() {
        
    }
    
    public Proveedor(int idProveedor, String ruc, String razonSocial, String direccion, int idDistrito, int idProvincia, int idDepartamento, String telefono, String celular, String correo){
        this.idProveedor = idProveedor;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.idDistrito = idDistrito;
        this.idProvincia = idProvincia;
        this.idDepartamento = idDepartamento;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
    }
    
    public Proveedor(String ruc, String razonSocial, String direccion, int idDistrito, int idProvincia, int idDepartamento, String telefono, String celular, String correo){
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.idDistrito = idDistrito;
        this.idProvincia = idProvincia;
        this.idDepartamento = idDepartamento;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
    }
}

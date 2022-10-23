/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.util.Calendar;
import java.util.Date;    

    
/**
 *
 * @author user
 */
public class Paciente {
    private String cedula;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String email;
    private boolean estado;
    private float cuenta;

    public Paciente(String cedula, String nombres, String apellidos, Date fechaNacimiento, String direccion, String telefono, String email, boolean estado) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.cuenta = 0;
    }
    
    public java.sql.Date getFechaSQL()
    {
        java.sql.Date fechaSQL = new java.sql.Date(this.fechaNacimiento.getTime());
        return fechaSQL;
    }
    
    public float getCuenta() {
        return cuenta;
    }

    public void setCuenta(float cuenta) {
        this.cuenta = cuenta;
    }
    
    public Paciente() {
        this.cedula = "";
        this.nombres = "";
        this.apellidos = "";
        this.fechaNacimiento = Calendar.getInstance().getTime();
        this.direccion = "";
        this.telefono = "";
        this.email = "";
        this.estado = true;
        this.cuenta = 0;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString()
    {
      return this.cedula+" "+this.nombres+" "+this.apellidos+" fechaN:"+this.fechaNacimiento.toString();
    }
    
}

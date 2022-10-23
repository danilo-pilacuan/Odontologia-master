/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author user
 */
public class Radiografia {
    private int idRadiografia;
    private String cedulaPaciente;
    private String ubicacion;
    private String descripcion;
    private Date fechaRadiografia;

    public Radiografia(int idRadiografia, String cedulaPaciente,String ubicacion, String descripcion, Date fechaRadiografia) {
        this.idRadiografia = idRadiografia;
        this.cedulaPaciente= cedulaPaciente;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fechaRadiografia = fechaRadiografia;
    }

    public Radiografia() {
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public int getIdRadiografia() {
        return idRadiografia;
    }

    public void setIdRadiografia(int idRadiografia) {
        this.idRadiografia = idRadiografia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRadiografia() {
        return fechaRadiografia;
    }

    public void setFechaRadiografia(Date fechaRadiografia) {
        this.fechaRadiografia = fechaRadiografia;
    }
    
    public java.sql.Date getFechaSQL()
    {
        java.sql.Date fechaSQL = new java.sql.Date(this.fechaRadiografia.getTime());
        return fechaSQL;
    }
}

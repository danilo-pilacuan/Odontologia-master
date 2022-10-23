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
public class Odontograma {
    private int idOdontograma;
    private String cedulaPaciente;
    private String ubicacion;
    private String descripcion;
    private Date fechaOdontograma;

    public Odontograma(int idOdontograma, String cedulaPaciente,String ubicacion, String descripcion, Date fechaOdontograma) {
        this.idOdontograma = idOdontograma;
        this.cedulaPaciente= cedulaPaciente;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fechaOdontograma = fechaOdontograma;
    }

    public Odontograma() {
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public int getIdOdontograma() {
        return idOdontograma;
    }

    public void setIdOdontograma(int idOdontograma) {
        this.idOdontograma = idOdontograma;
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

    public Date getFechaOdontograma() {
        return fechaOdontograma;
    }

    public void setFechaOdontograma(Date fechaOdontograma) {
        this.fechaOdontograma = fechaOdontograma;
    }
    
    public java.sql.Date getFechaSQL()
    {
        java.sql.Date fechaSQL = new java.sql.Date(this.fechaOdontograma.getTime());
        return fechaSQL;
    }
    
    
}

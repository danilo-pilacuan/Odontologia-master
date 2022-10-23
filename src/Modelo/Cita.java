/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Paciente;
import java.util.Date;

/**
 *
 * @author danil
 */
public class Cita {
    private int idCita;
    private Paciente pacienteCita;
    private Date fechaCita;
    private String horaCita;
    private String razon;
    private String procedimiento;
    private float costo;
    private boolean asistencia;

    public Cita(int idCita, Paciente pacienteCita, Date fechaCita,String horaCita, String razon, String procedimiento, float costo, boolean asistencia) {
        this.idCita = idCita;
        this.pacienteCita = pacienteCita;
        this.fechaCita = fechaCita;
        this.horaCita=horaCita;
        this.razon = razon;
        this.procedimiento = procedimiento;
        this.costo = costo;
        this.asistencia = asistencia;
    }

    public Cita() {
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Paciente getPacienteCita() {
        return pacienteCita;
    }

    public void setPacienteCita(Paciente pacienteCita) {
        this.pacienteCita = pacienteCita;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
    
    public java.sql.Date getFechaCitaSQL()
    {
        java.sql.Date fechaSQL = new java.sql.Date(this.fechaCita.getTime());
        return fechaSQL;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }
    
    @Override
    public String toString()
    {
        return "Cita: id:"+this.idCita+" cedulaPaciente:"+this.pacienteCita.getCedula()+" fecha:"+this.fechaCita.toString()+" hora:"+this.horaCita+" razon:"+this.razon+" procedimiento:"+this.procedimiento+" costo:"+Float.toString(this.costo)+" Asistencia:"+this.asistencia;
    }
    
}

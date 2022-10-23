/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class HistoriaClinica {
    private int idHistoriaClinica;
    private Paciente pacienteHistoriaClinica;
    private String antecedentes;
    private String observaciones;

    public HistoriaClinica(int idHistoriaClinica, Paciente pacienteHistoriaClinica, String antecedentes, String observaciones) {
        this.idHistoriaClinica = idHistoriaClinica;
        this.pacienteHistoriaClinica = pacienteHistoriaClinica;
        this.antecedentes = antecedentes;
        this.observaciones = observaciones;
    }

    public HistoriaClinica() {
    }

    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public Paciente getPacienteHistoriaClinica() {
        return pacienteHistoriaClinica;
    }

    public void setPacienteHistoriaClinica(Paciente pacienteHistoriaClinica) {
        this.pacienteHistoriaClinica = pacienteHistoriaClinica;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
}

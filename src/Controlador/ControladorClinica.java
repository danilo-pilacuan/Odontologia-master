/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.PacienteDAO;
import Modelo.Cita;
import Modelo.Paciente;
import java.util.ArrayList;

/**
 *
 * @author danil
 */
public class ControladorClinica {
    public ArrayList<Paciente> listaPacientes;
    public ArrayList<Cita> listaCitas;

    public ControladorClinica() {
        listaPacientes = new ArrayList<>();
        listaCitas = new ArrayList<>();
    }

    public ArrayList<Paciente> getListaPacientes() {
        listaPacientes = PacienteDAO.getListaPacientes();
        return listaPacientes;
    }

    public void setListaPacientes(ArrayList<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public ArrayList<Cita> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(ArrayList<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }
    
    public int crearPaciente(Paciente nuevoP)
    {
        
        if(PacienteDAO.agregarPaciente(nuevoP)==1)
            return 1;
        else
            return 0;        
    }
    
    public int actualizarPaciente(String cedulaActualizar,Paciente pacienteActualizado)
    {
        if(PacienteDAO.actualizarPaciente(cedulaActualizar, pacienteActualizado)==1)
            return 1;
        else
            return 0;
    }
    public int eliminarPaciente(String cedulaEliminar)
    {
        if(PacienteDAO.eliminarPaciente(cedulaEliminar)==1)
            return 1;
        else
            return 0;
    }
    
    public Paciente obtenerPaciente(String cedula)
    {
        return PacienteDAO.obtenerPacientePorCedula(cedula);
    }
    
    public ArrayList<Paciente> obtenerPacientesPorNombres(String nombres)
    {
        return PacienteDAO.obtenerPacientesPorNombres(nombres);
    }
    
    public ArrayList<Paciente> obtenerPacientesPorApellidos(String apellidos)
    {
        return PacienteDAO.obtenerPacientesPorApellidos(apellidos);
    }
    
    //TODO citas
    
}

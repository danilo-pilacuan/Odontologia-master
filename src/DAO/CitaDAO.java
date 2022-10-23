/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Cita;
import Modelo.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author danil
 */
public class CitaDAO {
    
    public static int agregarCita(Cita nuevaCita)
    {
        try
        {
            String consulta="EXEC prInsertarCita ?,?,?,?,?,?,?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, nuevaCita.getPacienteCita().getCedula());
            pre.setDate(2, nuevaCita.getFechaCitaSQL());
            pre.setString(3, nuevaCita.getHoraCita());
            pre.setString(4, nuevaCita.getRazon());
            pre.setString(5, nuevaCita.getProcedimiento());
            pre.setFloat(6, nuevaCita.getCosto());
            if(nuevaCita.getAsistencia())
                pre.setString(7, "1");
            else
                pre.setString(7, "0");
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
    }
    
    public static int actualizarCita(int idCita,Cita citaActualizada)
    {
        try
        {
            String consulta="update tblCitas set cedulaPaciente=?, fechaCita=?, horaCita=?,razon=?, procedimiento=?, costo= ?,asistencia=? where idCita=?";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, citaActualizada.getPacienteCita().getCedula());
            pre.setDate(2, citaActualizada.getFechaCitaSQL());
            pre.setString(3, citaActualizada.getHoraCita());
            pre.setString(4, citaActualizada.getRazon());
            pre.setString(5, citaActualizada.getProcedimiento());
            pre.setFloat(6, citaActualizada.getCosto());
            if(citaActualizada.getAsistencia())
                pre.setString(7, "1");
            else
                pre.setString(7, "0");
            pre.setInt(8, idCita);
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        
    }
    
    public static ArrayList<Cita> getListaCitas()
    {
        ArrayList<Cita> listaCitas = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblCitas";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                Cita nuevaC = new Cita();
                nuevaC.setIdCita(resultado.getInt(1));
                Paciente pCita=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaC.setPacienteCita(pCita);
                nuevaC.setFechaCita(resultado.getDate(3));
                nuevaC.setHoraCita(resultado.getString(4));
                nuevaC.setRazon(resultado.getString(5));
                nuevaC.setProcedimiento(resultado.getString(6));
                nuevaC.setCosto(resultado.getFloat(7));
                nuevaC.setAsistencia(resultado.getBoolean(8));
                listaCitas.add(nuevaC);
            }
            return listaCitas;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static Cita obtenerCitaPorId(int idCita)
    {
        Cita citaObtenida=null;
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblCitas WHERE idCita="+idCita;
            ResultSet resultado = sql.executeQuery(consulta);
            if(resultado.next())
            {  
                Cita nuevaC = new Cita();
                nuevaC.setIdCita(resultado.getInt(1));
                Paciente pCita=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaC.setPacienteCita(pCita);
                nuevaC.setFechaCita(resultado.getDate(3));
                nuevaC.setHoraCita(resultado.getString(4));
                nuevaC.setRazon(resultado.getString(5));
                nuevaC.setProcedimiento(resultado.getString(6));
                nuevaC.setCosto(resultado.getFloat(7));
                nuevaC.setAsistencia(resultado.getBoolean(8));
                citaObtenida=nuevaC;
            }
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        return citaObtenida;
    }
    
    public static int eliminarCita(int idCitaEliminar)
    {
        try
        {
            
            
            String consulta="DELETE FROM tblCitas WHERE idCita = ?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setInt(1, idCitaEliminar);
            pre.executeUpdate();            
            return 1;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return 0;
        }
        
    }
    
    public static ArrayList<Cita> obtenerCitasPorCedula(String cedula)
    {
        ArrayList<Cita> listaCitas = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblCitas WHERE cedulaPaciente='"+cedula+"'";
            ResultSet resultado = sql.executeQuery(consulta);
            
            while(resultado.next())
            {    
                Cita nuevaC = new Cita();
                nuevaC.setIdCita(resultado.getInt(1));
                Paciente pCita=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaC.setPacienteCita(pCita);
                nuevaC.setFechaCita(resultado.getDate(3));
                nuevaC.setHoraCita(resultado.getString(4));
                nuevaC.setRazon(resultado.getString(5));
                nuevaC.setProcedimiento(resultado.getString(6));
                nuevaC.setCosto(resultado.getFloat(7));
                nuevaC.setAsistencia(resultado.getBoolean(8));
                listaCitas.add(nuevaC);
            }
            return listaCitas;
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
    
    
    public static ArrayList<Cita> obtenerCitasPorFecha(Date fecha)
    {
        ArrayList<Cita> listaCitas = new ArrayList<>();
        try
        {
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            Statement sql = Conexion.getConexion().createStatement();
            
            String consulta = "SELECT * FROM tblCitas WHERE fechaCita=?";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setDate(1, fechaSQL);     
            ResultSet resultado = pre.executeQuery();
            
            while(resultado.next())
            {    
                Cita nuevaC = new Cita();
                nuevaC.setIdCita(resultado.getInt(1));
                Paciente pCita=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaC.setPacienteCita(pCita);
                nuevaC.setFechaCita(resultado.getDate(3));
                nuevaC.setHoraCita(resultado.getString(4));
                nuevaC.setRazon(resultado.getString(5));
                nuevaC.setProcedimiento(resultado.getString(6));
                nuevaC.setCosto(resultado.getFloat(7));
                nuevaC.setAsistencia(resultado.getBoolean(8));
                listaCitas.add(nuevaC);
            }
            return listaCitas;
        }
        catch(Exception ex)
        {
            //System.out.println("YO");
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
    
    public static ArrayList<Cita> obtenerCitasPorNombres(String nombres)
    {
        ArrayList<Cita> listaCitas = new ArrayList<>();
        
        ArrayList<Paciente> listaPacientes = PacienteDAO.obtenerPacientesPorNombres(nombres);
        
        try
        {
            for(Paciente iterP:listaPacientes)
            {
                ArrayList<Cita> listaCitasPorP = obtenerCitasPorCedula(iterP.getCedula());
                for(Cita iterC:listaCitasPorP)
                {
                    listaCitas.add(iterC);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        return listaCitas;
        
    }
    
    public static ArrayList<Cita> obtenerCitasPorApellidos(String apellidos)
    {
        ArrayList<Cita> listaCitas = new ArrayList<>();
        
        ArrayList<Paciente> listaPacientes = PacienteDAO.obtenerPacientesPorApellidos(apellidos);
        
        try
        {
            for(Paciente iterP:listaPacientes)
            {
                ArrayList<Cita> listaCitasPorP = obtenerCitasPorCedula(iterP.getCedula());
                for(Cita iterC:listaCitasPorP)
                {
                    listaCitas.add(iterC);
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        return listaCitas;
        
    }
    
    
}

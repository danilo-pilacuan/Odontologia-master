/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.HistoriaClinica;
import Modelo.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class HistoriaClinicaDAO {
    public static int agregarHistoriaClinica(HistoriaClinica nuevaHistoria)
    {
        try
        {
            String consulta="EXEC prInsertarHistoriaClinica ?,?,?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, nuevaHistoria.getPacienteHistoriaClinica().getCedula());
            pre.setString(2, nuevaHistoria.getAntecedentes());
            pre.setString(3, nuevaHistoria.getObservaciones());
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
    }
    
    public static int actualizarHistoriaClinica(int idHistoriaClinica,HistoriaClinica historiaActualizar)
    {
        try
        {
            String consulta="update tblHistoriasClinicas set cedulaPaciente=?, antecedentes=?, observaciones=? where idHistoriaClinica=?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, historiaActualizar.getPacienteHistoriaClinica().getCedula());
            pre.setString(2, historiaActualizar.getAntecedentes());
            pre.setString(3, historiaActualizar.getObservaciones());
            pre.setInt(4, idHistoriaClinica);
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
    }
    
    public static ArrayList<HistoriaClinica> getListaHistorias()
    {
        ArrayList<HistoriaClinica> listaHistorias = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblHistoriasClinicas;";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                HistoriaClinica nuevaH = new HistoriaClinica();
                nuevaH.setIdHistoriaClinica(resultado.getInt(1));
                Paciente pHistoria=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaH.setPacienteHistoriaClinica(pHistoria);
                nuevaH.setAntecedentes(resultado.getString(3));
                nuevaH.setObservaciones(resultado.getString(4));
                listaHistorias.add(nuevaH);
            }
            return listaHistorias;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static HistoriaClinica obtenerHistoriaPorId(int idHistoria)
    {
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblHistoriasClinicas WHERE idHistoriaClinica="+idHistoria+";";
            ResultSet resultado = sql.executeQuery(consulta);
            if(resultado.next())
            {    
                HistoriaClinica nuevaH = new HistoriaClinica();
                nuevaH.setIdHistoriaClinica(resultado.getInt(1));
                Paciente pHistoria=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaH.setPacienteHistoriaClinica(pHistoria);
                nuevaH.setAntecedentes(resultado.getString(3));
                nuevaH.setObservaciones(resultado.getString(4));
                return nuevaH;
            }
            return null;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static HistoriaClinica obtenerHistoriaPorCedula(String cedulaPaciente)
    {
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblHistoriasClinicas WHERE cedulaPaciente='"+cedulaPaciente+"';";
            ResultSet resultado = sql.executeQuery(consulta);
            if(resultado.next())
            {    
                HistoriaClinica nuevaH = new HistoriaClinica();
                nuevaH.setIdHistoriaClinica(resultado.getInt(1));
                Paciente pHistoria=PacienteDAO.obtenerPacientePorCedula(resultado.getString(2));
                nuevaH.setPacienteHistoriaClinica(pHistoria);
                nuevaH.setAntecedentes(resultado.getString(3));
                nuevaH.setObservaciones(resultado.getString(4));
                return nuevaH;
            }
            return null;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static int eliminarHistoriaPorCedula(String cedulaPaciente)
    {
        HistoriaClinica historiaClinica= new HistoriaClinica();
        try
        {
            
            String consulta = "DELETE FROM tblHistoriasClinicas WHERE cedulaPaciente = ?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, cedulaPaciente);
            pre.executeUpdate();            
            return 1;            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return 0;
        }
    }
    
}

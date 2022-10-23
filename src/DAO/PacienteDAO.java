/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Paciente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author danil
 */
public class PacienteDAO {
    public static int agregarPaciente(Paciente nuevoPaciente)
    {
        try
        {
            String consulta="EXEC prInsertarPaciente ?,?,?,?,?,?,?,?,?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, nuevoPaciente.getCedula());
            pre.setString(2, nuevoPaciente.getNombres());
            pre.setString(3, nuevoPaciente.getApellidos());
            pre.setDate(4, nuevoPaciente.getFechaSQL());
            pre.setString(5, nuevoPaciente.getDireccion());
            pre.setString(6, nuevoPaciente.getTelefono());
            pre.setString(7, nuevoPaciente.getEmail());
            if(nuevoPaciente.getEstado())
                pre.setString(8, "1");
            else
                pre.setString(8, "0");
            pre.setDouble(9, nuevoPaciente.getCuenta());
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        
    }
    
    public static int actualizarPaciente(String cedulaActualizar,Paciente actualizarPaciente)
    {
        try
        {
            String consulta="update tblPacientes set cedula=?, nombres=?, apellidos=?, fechaNacimiento=?, direccion= ?,telefono=?,email=?,estado=?,cuenta=? where cedula=?";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, actualizarPaciente.getCedula());
            pre.setString(2, actualizarPaciente.getNombres());
            pre.setString(3, actualizarPaciente.getApellidos());
            pre.setDate(4, actualizarPaciente.getFechaSQL());
            pre.setString(5, actualizarPaciente.getDireccion());
            pre.setString(6, actualizarPaciente.getTelefono());
            pre.setString(7, actualizarPaciente.getEmail());
            if(actualizarPaciente.getEstado())
                pre.setString(8, "1");
            else
                pre.setString(8, "0");
            pre.setDouble(9, actualizarPaciente.getCuenta());
            pre.setString(10, cedulaActualizar);
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        
    }
    
    public static ArrayList<Paciente> getListaPacientes()
    {
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblPacientes";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                Paciente nuevoP = new Paciente();
                nuevoP.setCedula(resultado.getString(1));
                nuevoP.setNombres(resultado.getString(2));
                nuevoP.setApellidos(resultado.getString(3));
                nuevoP.setFechaNacimiento(resultado.getDate(4));
                nuevoP.setDireccion(resultado.getString(5));
                nuevoP.setTelefono(resultado.getString(6));
                nuevoP.setEmail(resultado.getString(7));
                nuevoP.setEstado(resultado.getBoolean(8));
                nuevoP.setCuenta(resultado.getFloat(9));
                listaPacientes.add(nuevoP);
            }
            return listaPacientes;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
    public static int eliminarPaciente(String cedulaEliminar)
    {
        try
        {
            
            
            String consulta="DELETE FROM tblPacientes WHERE cedula = ?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, cedulaEliminar);
            pre.executeUpdate();            
            
            return 1;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return 0;
        }
        
    }
    
    public static Paciente obtenerPacientePorCedula(String cedula)
    {
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblPacientes WHERE cedula='"+cedula+"'";
            ResultSet resultado = sql.executeQuery(consulta);
            Paciente nuevoP = new Paciente();
            if(resultado.next())
            {
                nuevoP.setCedula(resultado.getString(1));
                nuevoP.setNombres(resultado.getString(2));
                nuevoP.setApellidos(resultado.getString(3));
                nuevoP.setFechaNacimiento(resultado.getDate(4));
                nuevoP.setDireccion(resultado.getString(5));
                nuevoP.setTelefono(resultado.getString(6));
                nuevoP.setEmail(resultado.getString(7));
                nuevoP.setEstado(resultado.getBoolean(8));
                nuevoP.setCuenta(resultado.getFloat(9));
                return nuevoP;         
            }
            else
            {
                return null;
            }
            
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
    public static ArrayList<Paciente> obtenerPacientesPorNombres(String nombres)
    {
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblPacientes WHERE nombres='"+nombres+"'";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                Paciente nuevoP = new Paciente();
                nuevoP.setCedula(resultado.getString(1));
                nuevoP.setNombres(resultado.getString(2));
                nuevoP.setApellidos(resultado.getString(3));
                nuevoP.setFechaNacimiento(resultado.getDate(4));
                nuevoP.setDireccion(resultado.getString(5));
                nuevoP.setTelefono(resultado.getString(6));
                nuevoP.setEmail(resultado.getString(7));
                nuevoP.setEstado(resultado.getBoolean(8));
                nuevoP.setCuenta(resultado.getFloat(9));
                listaPacientes.add(nuevoP);
            }
            return listaPacientes;
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
    public static ArrayList<Paciente> obtenerPacientesPorApellidos(String apellidos)
    {
        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblPacientes WHERE apellidos='"+apellidos+"'";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                Paciente nuevoP = new Paciente();
                nuevoP.setCedula(resultado.getString(1));
                nuevoP.setNombres(resultado.getString(2));
                nuevoP.setApellidos(resultado.getString(3));
                nuevoP.setFechaNacimiento(resultado.getDate(4));
                nuevoP.setDireccion(resultado.getString(5));
                nuevoP.setTelefono(resultado.getString(6));
                nuevoP.setEmail(resultado.getString(7));
                nuevoP.setEstado(resultado.getBoolean(8));
                nuevoP.setCuenta(resultado.getFloat(9));
                listaPacientes.add(nuevoP);
            }
            return listaPacientes;
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    
}

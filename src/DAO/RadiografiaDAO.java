/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Radiografia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class RadiografiaDAO {
    public static int agregarRadiografia(Radiografia nuevaRadiografia)
    {
        try
        {
            String consulta="EXEC prInsertarRadiografia ?,?,?,?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, nuevaRadiografia.getCedulaPaciente());
            pre.setString(2, nuevaRadiografia.getUbicacion());
            pre.setString(3, nuevaRadiografia.getDescripcion());
            pre.setDate(4, nuevaRadiografia.getFechaSQL());
            
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
    }
    
    public static int actualizarRadiografia(int idRadiografia,Radiografia radiografiaActualizada)
    {
        try
        {
            String consulta="update tblRadiografias set cedulaPaciente=?, ubicacion=?, descripcion=?,fechaRadiografia=? where idRadiografia=?";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, radiografiaActualizada.getCedulaPaciente());
            pre.setString(2, radiografiaActualizada.getUbicacion());
            pre.setString(3, radiografiaActualizada.getDescripcion());
            pre.setDate(4, radiografiaActualizada.getFechaSQL());
            pre.setInt(5, idRadiografia);
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        
    }
    
    public static ArrayList<Radiografia> getListaRadiografias()
    {
        ArrayList<Radiografia> listaRadiografias = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblRadiografias";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                Radiografia nuevaR = new Radiografia();
                nuevaR.setIdRadiografia(resultado.getInt(1));
                nuevaR.setCedulaPaciente(resultado.getString(2));
                nuevaR.setUbicacion(resultado.getString(3));
                nuevaR.setDescripcion(resultado.getString(4));
                nuevaR.setFechaRadiografia(resultado.getDate(5));
                listaRadiografias.add(nuevaR);
            }
            return listaRadiografias;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static ArrayList<Radiografia> getRadiografiasPorCedula(String cedulaPaciente)
    {
        ArrayList<Radiografia> listaRadiografias = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblRadiografias WHERE cedulaPaciente='"+cedulaPaciente+"';";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {    
                Radiografia nuevaR = new Radiografia();
                nuevaR.setIdRadiografia(resultado.getInt(1));
                nuevaR.setCedulaPaciente(resultado.getString(2));
                nuevaR.setUbicacion(resultado.getString(3));
                nuevaR.setDescripcion(resultado.getString(4));
                nuevaR.setFechaRadiografia(resultado.getDate(5));
                listaRadiografias.add(nuevaR);
            }
            return listaRadiografias;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static Radiografia getRadiografiaPorId(int idRadiografia)
    {
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblRadiografias WHERE idRadiografia="+idRadiografia+";";
            ResultSet resultado = sql.executeQuery(consulta);
            if(resultado.next())
            {    
                Radiografia nuevaR = new Radiografia();
                nuevaR.setIdRadiografia(resultado.getInt(1));
                nuevaR.setCedulaPaciente(resultado.getString(2));
                nuevaR.setUbicacion(resultado.getString(3));
                nuevaR.setDescripcion(resultado.getString(4));
                nuevaR.setFechaRadiografia(resultado.getDate(5));
                return nuevaR;
            }
            return null;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
}

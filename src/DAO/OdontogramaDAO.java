/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Odontograma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class OdontogramaDAO {
    public static int agregarOdontograma(Odontograma nuevoOdontograma)
    {
        try
        {
            String consulta="EXEC prInsertarOdontograma ?,?,?,?;";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, nuevoOdontograma.getCedulaPaciente());
            pre.setString(2, nuevoOdontograma.getUbicacion());
            pre.setString(3, nuevoOdontograma.getDescripcion());
            pre.setDate(4, nuevoOdontograma.getFechaSQL());
            
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
    }
    
    public static int actualizarOdontograma(int idOdontograma,Odontograma radiografiaActualizada)
    {
        try
        {
            String consulta="update tblOdontogramas set cedulaPaciente=?, ubicacion=?, descripcion=?,fechaOdontograma=? where idOdontograma=?";
            PreparedStatement pre = Conexion.getConexion().prepareStatement(consulta);
            pre.setString(1, radiografiaActualizada.getCedulaPaciente());
            pre.setString(2, radiografiaActualizada.getUbicacion());
            pre.setString(3, radiografiaActualizada.getDescripcion());
            pre.setDate(4, radiografiaActualizada.getFechaSQL());
            pre.setInt(5, idOdontograma);
            pre.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }
        
    }
    
    public static ArrayList<Odontograma> getListaOdontogramas()
    {
        ArrayList<Odontograma> listaOdontogramas = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblOdontogramas";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {
                
                Odontograma nuevoO = new Odontograma();
                nuevoO.setIdOdontograma(resultado.getInt(1));
                nuevoO.setCedulaPaciente(resultado.getString(2));
                nuevoO.setUbicacion(resultado.getString(3));
                nuevoO.setDescripcion(resultado.getString(4));
                nuevoO.setFechaOdontograma(resultado.getDate(5));
                listaOdontogramas.add(nuevoO);
            }
            return listaOdontogramas;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static ArrayList<Odontograma> getOdontogramasPorCedula(String cedulaPaciente)
    {
        ArrayList<Odontograma> listaOdontogramas = new ArrayList<>();
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblOdontogramas WHERE cedulaPaciente='"+cedulaPaciente+"';";
            ResultSet resultado = sql.executeQuery(consulta);
            while(resultado.next())
            {   
                
                Odontograma nuevoO = new Odontograma();
                nuevoO.setIdOdontograma(resultado.getInt(1));
                nuevoO.setCedulaPaciente(resultado.getString(2));
                nuevoO.setUbicacion(resultado.getString(3));
                nuevoO.setDescripcion(resultado.getString(4));
                
                nuevoO.setFechaOdontograma(resultado.getDate(5));
                listaOdontogramas.add(nuevoO);
            }
            return listaOdontogramas;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static Odontograma getOdontogramasPorId(int idOdontograma)
    {
        try
        {
            Statement sql = Conexion.getConexion().createStatement();
            String consulta = "SELECT * FROM tblOdontogramas WHERE idOdontograma="+idOdontograma+";";
            ResultSet resultado = sql.executeQuery(consulta);
            if(resultado.next())
            {    
                Odontograma nuevoO = new Odontograma();
                nuevoO.setIdOdontograma(resultado.getInt(1));
                nuevoO.setCedulaPaciente(resultado.getString(2));
                nuevoO.setUbicacion(resultado.getString(3));
                nuevoO.setDescripcion(resultado.getString(4));
                nuevoO.setFechaOdontograma(resultado.getDate(5));
                return nuevoO;
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

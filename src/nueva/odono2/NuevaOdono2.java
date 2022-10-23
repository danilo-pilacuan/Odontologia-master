/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nueva.odono2;

import DAO.CitaDAO;
import DAO.Conexion;
import DAO.OdontogramaDAO;
import DAO.PacienteDAO;
import DAO.RadiografiaDAO;
import Modelo.Cita;
import Modelo.Odontograma;
import Modelo.Paciente;
import Modelo.Radiografia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author USER
 */
public class NuevaOdono2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String bases="";
        try
        {          
            Paciente nuevo = new Paciente("1724978171","Alex Julian","Lopez Lopez",Calendar.getInstance().getTime(),"La floresta","0987654320","toribio@gmail.com",true);
            nuevo.setCuenta(25.89f);
            
            ArrayList<Paciente> listaP=PacienteDAO.obtenerPacientesPorApellidos("Lopez Lopez");

            for(Paciente iterP: listaP)
            {
                System.out.println(iterP.toString());
            }
            
            for(Cita iterC:CitaDAO.obtenerCitasPorApellidos("Mora Castro"))
            {
                System.out.println(iterC.toString());
            }
            //OdontogramaDAO.agregarOdontograma(new Odontograma(1,"1724978174","11","22",Calendar.getInstance().getTime()));
            //RadiografiaDAO.agregarRadiografia(new Radiografia(1,"1724978174","11","22",Calendar.getInstance().getTime()));
            
            
            //Cita nuevaC = new Cita(1,nuevo,Calendar.getInstance().getTime(),"Dolor de diente","Calza",20.0f,true);
            //CitaDAO.agregarCita(nuevaC);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
    
}

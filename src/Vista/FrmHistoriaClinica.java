/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorClinica;
import DAO.HistoriaClinicaDAO;
import DAO.OdontogramaDAO;
import DAO.PacienteDAO;
import DAO.RadiografiaDAO;
import Modelo.HistoriaClinica;
import Modelo.Odontograma;
import Modelo.Paciente;
import Modelo.Radiografia;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FrmHistoriaClinica extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmHistoriaClinica
     */
    ArrayList<Paciente> listaP;
    ArrayList<Odontograma> listaO;
    ArrayList<Radiografia> listaR;
    Paciente pacienteSeleccionado;
    String cedulaPaciente="";
    
    Odontograma odontoSel;
    Radiografia radioSel;
    ControladorClinica controlClinica;
    public FrmHistoriaClinica() {
        initComponents();
    }
    public FrmHistoriaClinica(ControladorClinica controlClinica) {
        this.controlClinica=controlClinica;
        initComponents();
        tablaDatos.getSelectionModel().addListSelectionListener(setListener());
        tablaRadiografias.getSelectionModel().addListSelectionListener(setListenerR());
        tablaOdontogramas.getSelectionModel().addListSelectionListener(setListenerO());
        pacienteSeleccionado=null;
        listaP=this.controlClinica.getListaPacientes();
        llenarTablaP();
        odontoSel=null;
        radioSel=null;
    }
    
    public void llenarTablaP()
    {
        try
        {
            
            
            DefaultTableModel modelo = (DefaultTableModel)tablaDatos.getModel();
            modelo.setRowCount(0);
            for(Paciente auxP : listaP)
            {
                modelo.addRow(new Object[]{auxP.getCedula(),auxP.getNombres(),auxP.getApellidos()});
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void llenarTablaR()
    {
        try
        {
            
            
            DefaultTableModel modelo = (DefaultTableModel)tablaRadiografias.getModel();
            modelo.setRowCount(0);
            for(Radiografia auxR : RadiografiaDAO.getRadiografiasPorCedula(cedulaPaciente))
            {
                modelo.addRow(new Object[]{auxR.getIdRadiografia(),auxR.getFechaRadiografia().toString(),auxR.getDescripcion()});
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void llenarTablaO()
    {
        try
        {
            
            
            DefaultTableModel modelo = (DefaultTableModel)tablaOdontogramas.getModel();
            modelo.setRowCount(0);
            for(Odontograma auxO : OdontogramaDAO.getOdontogramasPorCedula(cedulaPaciente))
            {
                modelo.addRow(new Object[]{auxO.getIdOdontograma(),auxO.getFechaOdontograma().toString(),auxO.getDescripcion()});
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public int calcularEdad(Date fechaActual,Date fechaNac)
    {
        int edad=0;
        int anioNac=fechaNac.getYear();
        int anioActual=fechaActual.getYear();
        int mesNac=fechaNac.getMonth();
        int mesActual=fechaActual.getMonth();
        int diaNac=fechaNac.getDay();
        int diaActual=fechaActual.getDay();
        
        edad=anioActual-anioNac;
        if(mesActual<mesNac)
        {
            edad-=1;
        }
        else if(mesActual==mesNac)
        {
            if(diaActual<diaNac)
            {
                edad-=1;
            }
        }
        
        return edad;
    }
    
    public ListSelectionListener setListener()
    {
        return new ListSelectionListener(){
          public void valueChanged(ListSelectionEvent event)  
          {
              try
              {
                  if(tablaDatos.getSelectedRow()>=0)
                  {
                    cedulaPaciente=tablaDatos.getValueAt(tablaDatos.getSelectedRow(),0).toString();
                    Paciente pacienteSel = PacienteDAO.obtenerPacientePorCedula(cedulaPaciente);
                    pacienteSeleccionado=pacienteSel;
                    txtCedula.setText(pacienteSel.getCedula());
                    txtNombre.setText(pacienteSel.getNombres());
                    txtApellido.setText(pacienteSel.getApellidos());
                    txtEdad.setText(Integer.toString(calcularEdad(Calendar.getInstance().getTime(),pacienteSel.getFechaNacimiento())));
                    
                    HistoriaClinica historiaPaciente = HistoriaClinicaDAO.obtenerHistoriaPorCedula(cedulaPaciente);
                    if(historiaPaciente!=null)
                    {
                        txtAntecedentes.setText(historiaPaciente.getAntecedentes());
                        txtObservaciones.setText(historiaPaciente.getObservaciones());
                    }
                    
                    llenarTablaO();
                    llenarTablaR();
                    
                    
                  }
                  
              }
              catch(Exception ex)
              {
                  //System.out.println(ex);
              }
          }
        };
    }
    
    public ListSelectionListener setListenerR()
    {
        return new ListSelectionListener(){
          public void valueChanged(ListSelectionEvent event)  
          {
              try
              {
                  if(tablaRadiografias.getSelectedRow()>=0)
                  {
                    int idRadioSel=Integer.parseInt(tablaRadiografias.getValueAt(tablaRadiografias.getSelectedRow(),0).toString());
                    radioSel=RadiografiaDAO.getRadiografiaPorId(idRadioSel);
                    
                  }
                  
              }
              catch(Exception ex)
              {
                  radioSel=null;
                  //System.out.println(ex);
              }
          }
        };
    }
    public ListSelectionListener setListenerO()
    {
        return new ListSelectionListener(){
          public void valueChanged(ListSelectionEvent event)  
          {
              try
              {
                  if(tablaOdontogramas.getSelectedRow()>=0)
                  {
                    int idOdontoSel=Integer.parseInt(tablaOdontogramas.getValueAt(tablaOdontogramas.getSelectedRow(),0).toString());
                    odontoSel=OdontogramaDAO.getOdontogramasPorId(idOdontoSel);
                    
                  }
                  
              }
              catch(Exception ex)
              {
                  odontoSel=null;
                  //System.out.println(ex);
              }
          }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAntecedentes = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaOdontogramas = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaRadiografias = new javax.swing.JTable();
        btnAgregarR = new javax.swing.JButton();
        btnAgregarO = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnVerO = new javax.swing.JButton();
        btnVerR = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Paciente"));

        jLabel8.setText("Criterio de Busqueda");

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cédula", "Nombres", "Apellidos" }));

        jLabel9.setText("Paciente");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cédula", "Nombres", "Apellidos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaDatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxCriterio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Historia Clínica"));

        jLabel5.setText("Antecedentes");

        jLabel6.setText("Observaciones");

        jLabel7.setText("Odontogramas");

        jLabel10.setText("Radiografías");

        txtAntecedentes.setColumns(20);
        txtAntecedentes.setRows(4);
        jScrollPane2.setViewportView(txtAntecedentes);

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(4);
        jScrollPane3.setViewportView(txtObservaciones);

        tablaOdontogramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaOdontogramas);
        if (tablaOdontogramas.getColumnModel().getColumnCount() > 0) {
            tablaOdontogramas.getColumnModel().getColumn(0).setMinWidth(0);
            tablaOdontogramas.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaOdontogramas.getColumnModel().getColumn(1).setMinWidth(100);
            tablaOdontogramas.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        tablaRadiografias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Fecha", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaRadiografias);
        if (tablaRadiografias.getColumnModel().getColumnCount() > 0) {
            tablaRadiografias.getColumnModel().getColumn(0).setMinWidth(0);
            tablaRadiografias.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaRadiografias.getColumnModel().getColumn(1).setMinWidth(100);
            tablaRadiografias.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        btnAgregarR.setText("Agregar");
        btnAgregarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRActionPerformed(evt);
            }
        });

        btnAgregarO.setText("Agregar");
        btnAgregarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarOActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar Historia Clínica");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnVerO.setText("Ver Seleccionado");
        btnVerO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerOActionPerformed(evt);
            }
        });

        btnVerR.setText("Ver Seleccionada");
        btnVerR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(31, 31, 31)
                                .addComponent(btnAgregarR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerR))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(btnAgregarO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVerO))
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addContainerGap(243, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnAgregarO)
                    .addComponent(btnVerO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(btnAgregarR)
                    .addComponent(btnVerR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Paciente"));

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellido");

        jLabel3.setText("Cédula");

        jLabel4.setText("Edad");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(63, 63, 63)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try
        {
            String buscar=txtBuscar.getText();
            if(cbxCriterio.getSelectedIndex()==0)
            {
                if(controlClinica.obtenerPaciente(buscar)!=null)
                {
                    listaP=new ArrayList<Paciente>();
                    listaP.add(controlClinica.obtenerPaciente(buscar));
                }
                else
                {
                    listaP=new ArrayList<Paciente>();
                }
            }
            else if(cbxCriterio.getSelectedIndex()==1)
            {
                listaP=controlClinica.obtenerPacientesPorNombres(buscar);
            }
            else
            {
                listaP=controlClinica.obtenerPacientesPorApellidos(buscar);
            }
            llenarTablaP();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
        if(txtBuscar.getText().equals(""))
        {
            listaP=controlClinica.getListaPacientes();
            llenarTablaP();
        }
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        if(txtBuscar.getText().equals(""))
        {
            listaP=controlClinica.getListaPacientes();
            llenarTablaP();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnVerOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerOActionPerformed
        // TODO add your handling code here:
        if(odontoSel!=null)
        {
            VistaOdontograma frm = new VistaOdontograma(odontoSel);
            frm.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Primero seleccione un odontograma","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnVerOActionPerformed

    private void btnVerRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerRActionPerformed
        // TODO add your handling code here:
        if(radioSel!=null)
        {
            VistaRadiografia frm = new VistaRadiografia(radioSel);
            frm.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Primero seleccione una radiografía","Error",JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("no hay obj");
        }
    }//GEN-LAST:event_btnVerRActionPerformed

    private void btnAgregarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarOActionPerformed
        // TODO add your handling code here:
        if(pacienteSeleccionado!=null)
        {
            AgregarOdontograma frm = new AgregarOdontograma(this,pacienteSeleccionado);
            frm.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Primero seleccione un paciente","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarOActionPerformed

    private void btnAgregarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRActionPerformed
        // TODO add your handling code here:
        if(pacienteSeleccionado!=null)
        {
            AgregarRadiografia frm = new AgregarRadiografia(this,pacienteSeleccionado);
            frm.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Primero seleccione un paciente","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarRActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        try
        {
            if(pacienteSeleccionado!=null)
            {
                HistoriaClinica historiaAct = HistoriaClinicaDAO.obtenerHistoriaPorCedula(pacienteSeleccionado.getCedula());
                historiaAct.setAntecedentes(txtAntecedentes.getText());
                historiaAct.setObservaciones(txtObservaciones.getText());
                if(HistoriaClinicaDAO.actualizarHistoriaClinica(historiaAct.getIdHistoriaClinica(), historiaAct)==1)
                {
                    JOptionPane.showMessageDialog(null,"Historia clínica actualizada con éxito","Éxito",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No se pudo actualizar la historia clínica","Error",JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Primero seleccione un paciente","Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarO;
    private javax.swing.JButton btnAgregarR;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVerO;
    private javax.swing.JButton btnVerR;
    private javax.swing.JComboBox cbxCriterio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTable tablaOdontogramas;
    private javax.swing.JTable tablaRadiografias;
    private javax.swing.JTextArea txtAntecedentes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_PrgMantemiento;

import Capa_Entidades.EAgenteRetenedores;
import Capa_Entidades.EBuenosContribuyentes;
import Capa_Logica.LAgenteRetenedores;
import Capa_Logica.LBuenosContribuyentes;
import Capa_Logica.SesionUsuario;
import Capa_Panel.Importar.JDCargaRegistros;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.read.biff.BiffException;

/**
 *
 * @author EXPERTYA
 */
public class BuenosConAgeRete extends javax.swing.JInternalFrame {
    
     ArrayList<EBuenosContribuyentes> lstabuen = new ArrayList<>();
     ArrayList<EAgenteRetenedores> lstagente=new ArrayList<>();
     String titulos[] = {"RUC", "Razon Social", "Fecha","Resolucion"};
     DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
     String titulos1[] = {"RUC", "Razon Social", "Fecha","Resolucion"};
     DefaultTableModel modTbdatos1 = new DefaultTableModel(titulos, 0);
     JDCargaRegistros jdc;
    /**
     * Creates new form BuenosConAgeRete
     */
    public BuenosConAgeRete() {
        initComponents();
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        this.setTitle("Mantenimiento de Buenos Contribuyentes y Agentes Retenedores.");
        listaagente();
        listabuenos();
        tbagente.setModel(modTbdatos);
        tbagente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbagente.getTableHeader().setReorderingAllowed(false);
        tbagente.getColumnModel().getColumn(0).setPreferredWidth(75);
        tbagente.getColumnModel().getColumn(1).setPreferredWidth(450);
        tbagente.getColumnModel().getColumn(2).setPreferredWidth(75);
        tbagente.getColumnModel().getColumn(3).setPreferredWidth(100);
        //
        tbbuenos.setModel(modTbdatos1);
        tbbuenos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbbuenos.getTableHeader().setReorderingAllowed(false);
        tbbuenos.getColumnModel().getColumn(0).setPreferredWidth(75);
        tbbuenos.getColumnModel().getColumn(1).setPreferredWidth(400);
        tbbuenos.getColumnModel().getColumn(2).setPreferredWidth(75);
        tbbuenos.getColumnModel().getColumn(3).setPreferredWidth(100);
        
   
    }
    
    
    
     private void listaagente(){
         LAgenteRetenedores ltc=new LAgenteRetenedores();
        lstagente=ltc.ListaAgente();
        modTbdatos.setRowCount(0);
        if(lstagente.size()>0){
             for(int i=0;i<lstagente.size();i++){
                 Object nuevafila[] = new Object[4];
                 nuevafila[0] = lstagente.get(i).getRucBC();
                 nuevafila[1] = lstagente.get(i).getRazonSocialBC();
                 nuevafila[2] = lstagente.get(i).getFechaIniBC();
                 nuevafila[3] = lstagente.get(i).getResolucionBC();
                 
                 
                 modTbdatos.addRow(nuevafila);
             }
        }
        tbagente.setModel(modTbdatos);
        lblnroregistros.setText(tbagente.getRowCount() + " Registro(s).");
    }  
     
     
     private void listaagentexfiltro(String letras){
         LAgenteRetenedores ltc=new LAgenteRetenedores();
        lstagente=ltc.ListaFiltro(letras);
        modTbdatos.setRowCount(0);
        if(lstagente.size()>0){
             for(int i=0;i<lstagente.size();i++){
                 Object nuevafila[] = new Object[4];
                 nuevafila[0] = lstagente.get(i).getRucBC();
                 nuevafila[1] = lstagente.get(i).getRazonSocialBC();
                 nuevafila[2] = lstagente.get(i).getFechaIniBC();
                 nuevafila[3] = lstagente.get(i).getResolucionBC();
                 modTbdatos.addRow(nuevafila);
             }
        }
        tbagente.setModel(modTbdatos);
        lblnroregistros.setText(tbagente.getRowCount() + " Registro(s).");
    }  
     
     
      private void listabuenos(){
         LBuenosContribuyentes ltc=new LBuenosContribuyentes();
        lstabuen=ltc.ListaBuenosContribuyentes();
        modTbdatos1.setRowCount(0);
        if(lstabuen.size()>0){
             for(int i=0;i<lstabuen.size();i++){
                 Object nuevafila[] = new Object[4];
                 nuevafila[0] = lstabuen.get(i).getRuc();
                 nuevafila[1] = lstabuen.get(i).getRazonSocial();
                 nuevafila[2] = lstabuen.get(i).getFechaIni();
                 nuevafila[3] = lstabuen.get(i).getResolucion();
                 
                 
                 modTbdatos1.addRow(nuevafila);
             }
        }
        tbbuenos.setModel(modTbdatos1);
        lblnroregistros1.setText(tbbuenos.getRowCount() + " Registro(s).");
    }  
     
    private void listabuenosxfiltro(String letra){
         LBuenosContribuyentes ltc=new LBuenosContribuyentes();
        lstabuen=ltc.ListaContriFiltro(letra);
        modTbdatos1.setRowCount(0);
        if(lstabuen.size()>0){
             for(int i=0;i<lstabuen.size();i++){
                 Object nuevafila[] = new Object[4];
                 nuevafila[0] = lstabuen.get(i).getRuc();
                 nuevafila[1] = lstabuen.get(i).getRazonSocial();
                 nuevafila[2] = lstabuen.get(i).getFechaIni();
                 nuevafila[3] = lstabuen.get(i).getResolucion();
                 
                 
                 modTbdatos1.addRow(nuevafila);
             }
        }
        tbbuenos.setModel(modTbdatos1);
        lblnroregistros1.setText(tbbuenos.getRowCount() + " Registro(s).");
    }  
    
     class PrimeThread implements Runnable {

        PrimeThread() {
        }

        public void run() {
            EBuenosContribuyentes e = new  EBuenosContribuyentes();
            e.setUsuarioCrea(SesionUsuario.misesion.getUsuario());
        
            LBuenosContribuyentes lim = new LBuenosContribuyentes();
            System.out.println("TOTAL: " + lstabuen.size());
            lim.Eliminar();
            for (int i = 0; i < lstabuen.size(); i++) {

                lim.InsertarContribuyentes(lstabuen.get(i));
                System.out.println("GRABANDO: " + (i + 1));
            }
            jdc.setVisible(false);
            jdc.tiempo.stop();
            JOptionPane.showMessageDialog(null, "Se grabó correctamente los datos de los Buenos Contribuyentes.");
            limpiar();
        }
    }
    
      class PrimeThread2 implements Runnable {

        PrimeThread2() {
        }

        public void run() {
            LAgenteRetenedores lim = new LAgenteRetenedores();
            System.out.println("TOTAL: " + lstagente.size());
            lim.Eliminar();
            for (int i = 0; i < lstagente.size(); i++) {
                lim.InsertarAgente(lstagente.get(i));
                System.out.println("GRABANDO: " + (i + 1));
            }
            jdc.setVisible(false);
            jdc.tiempo.stop();
            JOptionPane.showMessageDialog(null, "Se grabó correctamente los Agentes Retenedores.");
            limpiar();
        }
    }
     
     
     private void limpiar(){
         lstabuen.removeAll(lstabuen);
         lstagente.removeAll(lstagente);
     }
    

    
    
    private String fechaformato(Date fecha){
    String fechafor=new SimpleDateFormat("dd-MM-yyyy").format(fecha);
    return fechafor;
    }
    


     private void llenartabla() {
        modTbdatos.setRowCount(0);
        if (lstagente.size() > 0) {
            for (int i = 0; i < lstagente.size(); i++) {
                Object nuevafila[] = new Object[4];
                nuevafila[0] = lstagente.get(i).getRucBC();
                nuevafila[1] = lstagente.get(i).getRazonSocialBC();
                nuevafila[2] = lstagente.get(i).getFechaIniBC();
                nuevafila[3] = lstagente.get(i).getResolucionBC();

                modTbdatos.addRow(nuevafila);
            }
//            tbagente.setModel(modTbdatos);
        }
     }
     
       private void llenartabla1() {
        modTbdatos1.setRowCount(0);
        if (lstabuen.size() > 0) {
            for (int i = 0; i < lstabuen.size(); i++) {
                Object nuevafila[] = new Object[4];
                nuevafila[0] = lstabuen.get(i).getRuc();
                nuevafila[1] = lstabuen.get(i).getRazonSocial();
                nuevafila[2] = lstabuen.get(i).getFechaIni();
                nuevafila[3] = lstabuen.get(i).getResolucion();

                modTbdatos1.addRow(nuevafila);
            }
            tbbuenos.setModel(modTbdatos1);
        }
     }
     
     
      public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = BuenosConAgeRete.this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2)+30;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2)+20;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
      
       private String fechavali(String fecha){
           StringBuilder sbr =  new StringBuilder();
                
                sbr.append(fecha.substring(0,2));
                sbr.append("-");
                sbr.append(fecha.substring(3,5));
                sbr.append("-");
                sbr.append("20");
                sbr.append(fecha.substring(6,8));
                
            return sbr.toString();
                   
    }
       
    private void grabandoyactualizandoprov(){
        LBuenosContribuyentes lp=new LBuenosContribuyentes();
        if(lstabuen.size()>0){
            for (int i = 0; i < lstabuen.size(); i++) {
                //valida
                if(lp.verifRUCrep(lstabuen.get(i).getRuc())==0){
                    //Insertar en proveedores
                    System.out.println("GRABA");
                    lp.InsertarContribuyentes(lstabuen.get(i));
                }else{
                    //Actualizar proveedores
                    System.out.println("ACTUALIZA");
                    lp.ActualizarContribuyentes(lstabuen.get(i));
                
          }
        } 
      }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        btnimportar2 = new javax.swing.JButton();
        tbBC2 = new javax.swing.JScrollPane();
        tbbuenos = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                if (column == 6){
                    return true;
                }else{
                    return false;
                }
            }
        };
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        lblnroregistros1 = new javax.swing.JLabel();
        txtcontri = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        lblnroregistros = new javax.swing.JLabel();
        tbBC = new javax.swing.JScrollPane();
        tbagente = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                if (column == 6){
                    return true;
                }else{
                    return false;
                }
            }
        };
        btnimportar = new javax.swing.JButton();
        btnguardar1 = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        txtfiltroagente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lbltitulo3 = new javax.swing.JLabel();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnimportar2.setText("Importar");
        btnimportar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportar2ActionPerformed(evt);
            }
        });
        jPanel6.add(btnimportar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tbBC2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbBC2KeyTyped(evt);
            }
        });

        tbbuenos.setAutoCreateRowSorter(true);
        tbbuenos.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tbbuenos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbBC2.setViewportView(tbbuenos);

        jPanel6.add(tbBC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 690, 450));

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel6.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 70, 30));

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel6.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 70, 30));

        lblnroregistros1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnroregistros1.setText("0 Registros.");
        jPanel6.add(lblnroregistros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 130, 10));

        txtcontri.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtcontri.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        txtcontri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontriKeyReleased(evt);
            }
        });
        jPanel6.add(txtcontri, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 4, 250, -1));

        jTabbedPane1.addTab("Buenos Contribuyentes", jPanel6);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnroregistros.setText("0 Registros.");
        jPanel12.add(lblnroregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 110, 10));

        tbBC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbBCKeyTyped(evt);
            }
        });

        tbagente.setAutoCreateRowSorter(true);
        tbagente.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tbagente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbagente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbagenteKeyReleased(evt);
            }
        });
        tbBC.setViewportView(tbagente);

        jPanel12.add(tbBC, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 685, 440));

        btnimportar.setText("Importar");
        btnimportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportarActionPerformed(evt);
            }
        });
        jPanel12.add(btnimportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnguardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnguardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 70, 30));

        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnsalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 70, 30));

        txtfiltroagente.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtfiltroagente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        txtfiltroagente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfiltroagenteKeyReleased(evt);
            }
        });
        jPanel12.add(txtfiltroagente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 4, 250, -1));

        jTabbedPane1.addTab("Agentes Retenedores", jPanel12);

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(514, 20));

        lbltitulo3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbltitulo3.setForeground(new java.awt.Color(255, 255, 255));
        lbltitulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitulo3.setText("IMPORTAR BUENOS CONTRIBUYENTES Y AGENTES RETENEDORES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lbltitulo3)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void tbagenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbagenteKeyReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_tbagenteKeyReleased

    private void btnimportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportarActionPerformed
         javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                lstagente.removeAll(lstagente);
                Sheet sheet = workbook.getSheet(0);
                for (int fila = 1; fila < sheet.getRows(); fila++) {
                    //recorremos las filas
                    if(sheet.getCell(0, fila).getContents().toString().trim().length()>0){
                    EAgenteRetenedores etelf = new EAgenteRetenedores();
                    etelf.setRucBC(sheet.getCell(0, fila).getContents());
                    etelf.setRazonSocialBC(sheet.getCell(1, fila).getContents());
                    etelf.setFechaIniBC(fechavali(sheet.getCell(2, fila).getContents()));
                    etelf.setResolucionBC(sheet.getCell(3, fila).getContents());
                    lstagente.add(etelf);    
                    }
                }
                if(tbagente.getRowCount()>0){
                if (JOptionPane.showConfirmDialog(null, "Ya cuenta con informacion existente. ¿Desea Reemplazarla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                llenartabla();
                }    
                }else{
                 llenartabla();
                }
            }
        } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnimportarActionPerformed

    private void btnimportar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportar2ActionPerformed
         javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                if(lstabuen.size()>0){
                    if (JOptionPane.showConfirmDialog(null, "Ya cuenta con informacion existente. ¿Desea Reemplazarla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                   ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                lstabuen.removeAll(lstabuen);
                Sheet sheet = workbook.getSheet(0);
                for (int fila = 1; fila < sheet.getRows(); fila++) {
                    //recorremos las filas
                    if(sheet.getCell(0, fila).getContents().toString().trim().length()>0){
                    EBuenosContribuyentes buen = new EBuenosContribuyentes();
                    buen.setRuc(sheet.getCell(0, fila).getContents());
                    buen.setRazonSocial(sheet.getCell(1, fila).getContents());  
                       if(sheet.getCell(2, fila).getContents()!=null){
                    buen.setFechaIni(sheet.getCell(2, fila).getContents());
                    }else{
                        buen.setFechaIni("No data");
                    }
                       
                       if(sheet.getCell(3, fila).getContents()!=null){
                    buen.setResolucion(sheet.getCell(3, fila).getContents());
                    }else{
                        buen.setResolucion("No data");
                    }   
                    lstabuen.add(buen);    
                    }
                }
                     llenartabla1();     
                }
                } else{
                    if (JOptionPane.showConfirmDialog(null, "Se va a importar Buenos Contribuyentes. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                   ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                lstabuen.removeAll(lstabuen);
                Sheet sheet = workbook.getSheet(0);
                for (int fila = 1; fila < sheet.getRows(); fila++) {
                    //recorremos las filas
                    if(sheet.getCell(0, fila).getContents().toString().trim().length()>0){
                    EBuenosContribuyentes buen = new EBuenosContribuyentes();
                    buen.setRuc(sheet.getCell(0, fila).getContents());
                    buen.setRazonSocial(sheet.getCell(1, fila).getContents());  
                       if(sheet.getCell(2, fila).getContents()!=null){
                    buen.setFechaIni(sheet.getCell(2, fila).getContents());
                    }else{
                        buen.setFechaIni("No data");
                    }
                       
                       if(sheet.getCell(3, fila).getContents()!=null){
                    buen.setResolucion(sheet.getCell(3, fila).getContents());
                    }else{
                        buen.setResolucion("No data");
                    }   
                    lstabuen.add(buen);    
                    }
                }
                     llenartabla1();     
                }
                }    
            }
        } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
            System.out.println(ex);
        }  
    }//GEN-LAST:event_btnimportar2ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
                   
//                 LBuenosContribuyentes bue = new LBuenosContribuyentes();
                    if(lstabuen.size()>0){
                        if (JOptionPane.showConfirmDialog(null, "Se van a grabar Buenos Contribuyentes. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        jdc = new JDCargaRegistros(null, true);
                        SesionUsuario.nroaregistrar = lstabuen.size();
                        PrimeThread prt = new PrimeThread();
                        new Thread(prt).start();
                        jdc.setVisible(true);    
                        }
//                        }  
                    }else{
                        JOptionPane.showMessageDialog(this,"No tiene datos para grabar.");
                    }

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
    dispose();   
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
//                LAgenteRetenedores age = new LAgenteRetenedores();
                    if(lstagente.size()>0){
                        if (JOptionPane.showConfirmDialog(null, "Se van a grabar Agentes Retenedores. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                         jdc = new JDCargaRegistros(null, true);
                          SesionUsuario.nroaregistrar = lstagente.size();
                        PrimeThread2 prt = new PrimeThread2();
                        new Thread(prt).start();
                        jdc.setVisible(true);   
                        }
                    }else{
                        JOptionPane.showMessageDialog(this,"No tiene datos para grabar.");
                    }
                
    }//GEN-LAST:event_btnguardar1ActionPerformed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void tbBC2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBC2KeyTyped
    
    }//GEN-LAST:event_tbBC2KeyTyped

    private void tbBCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBCKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBCKeyTyped

    private void txtfiltroagenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroagenteKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(txtfiltroagente.getText().trim().length()==0){
                listaagente();
            }else{
                listaagentexfiltro(txtfiltroagente.getText().trim());
            }
        }
    }//GEN-LAST:event_txtfiltroagenteKeyReleased

    private void txtcontriKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontriKeyReleased
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(txtcontri.getText().trim().length()==0){
                listabuenos();
            }else{
                listabuenosxfiltro(txtcontri.getText().trim());
            }
        }
    }//GEN-LAST:event_txtcontriKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnimportar;
    private javax.swing.JButton btnimportar2;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JLabel lblnroregistros1;
    public javax.swing.JLabel lbltitulo3;
    private javax.swing.JScrollPane tbBC;
    private javax.swing.JScrollPane tbBC2;
    private javax.swing.JTable tbagente;
    private javax.swing.JTable tbbuenos;
    private javax.swing.JTextField txtcontri;
    private javax.swing.JTextField txtfiltroagente;
    // End of variables declaration//GEN-END:variables
}

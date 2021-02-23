/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Formatear;

import Capa_Entidades.EDataVenta;
import Capa_Entidades.EFormatoDAOTVentas;
import Capa_Logica.LImportar;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
public class InterFrmImportarExoInafVentas extends javax.swing.JInternalFrame {
    ArrayList<EDataVenta> lstdetalle=new ArrayList<>();
    ArrayList<EDataVenta> lstconsolidada=new ArrayList<>();
    ArrayList<EFormatoDAOTVentas> lstdaot=new ArrayList<>();
    String titulos[] = {"Fila","Ruc","RazonSocial","Monto"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmImportarExoInafVentas
     */
    public InterFrmImportarExoInafVentas() {
        initComponents();
        tblstdata.setModel(modTbdatos);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
    }
    
    public boolean esNumeroEntero(String cad) {
        try {
            Integer.parseInt(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void llenartabla(){
        DecimalFormat formato = new DecimalFormat("##0.00", simbolo);
        modTbdatos.setRowCount(0);
        double montototal=0.0;
        if(lstdetalle.size()>0){    
            //consolidar();
            lstconsolidada=lstdetalle;
            for (int i = 0; i < lstconsolidada.size(); i++) {
                Object obj[]=new Object[4];
                obj[0]=lstconsolidada.get(i).getCodigo();
                obj[1]=lstconsolidada.get(i).getNroDocIdentCliente();
                obj[2]=lstconsolidada.get(i).getRazSocialCliente();
                obj[3]=formato.format(Double.valueOf(lstconsolidada.get(i).getBaseImpOpeGravada()));
                montototal=montototal+(Double.valueOf(lstconsolidada.get(i).getBaseImpOpeGravada()));
                modTbdatos.addRow(obj);
            }
        }
        tblstdata.setModel(modTbdatos);
        System.out.println("MONTO: "+ montototal);   
    }
    
    private void consolidar(){
        System.out.println("DETALLE: " + lstdetalle.size());
        for (int i = 0; i < lstdetalle.size(); i++) {
            int val=0;
            double nuevomonto=0.0;
            for (int j = 0; j < lstconsolidada.size(); j++) {
                if(lstdetalle.get(i).getNroDocIdentCliente().equalsIgnoreCase(lstconsolidada.get(j).getNroDocIdentCliente())){
                    val=1;
                    nuevomonto=Double.valueOf(lstconsolidada.get(j).getBaseImpOpeGravada())+Double.valueOf(lstdetalle.get(i).getBaseImpOpeGravada());
                    lstconsolidada.get(j).setBaseImpOpeGravada(nuevomonto+"");
                }
            }
           if(val==0){
             lstconsolidada.add(lstdetalle.get(i));  
           }
        }
        System.out.println("CONSOLIDADO: "+ lstconsolidada.size());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblstdata = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btngrabar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblstdata.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblstdata);

        jButton1.setText("Importar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btngrabar.setText("Grabar");
        btngrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngrabarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btngrabar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btngrabar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                int val=0;
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                lstdetalle.removeAll(lstdetalle);
                Sheet sheet = workbook.getSheet(0);
                System.out.println(sheet.getCell(1, 1).getContents().trim());
                System.out.println(sheet.getCell(0, 1).getContents().trim());
                if(!esNumeroEntero(sheet.getCell(0, 1).getContents().trim())){   
                    val=1;
                    JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
                }
                int cont=0;
                for (int fila = 1; fila < sheet.getRows(); fila++) {
                    //recorremos las filas
                    cont=cont+1;
                    if(sheet.getCell(0, fila).getContents().toString().trim().length()>0){
                        EDataVenta objdata=new EDataVenta();
                        if (val == 1) {
                            break;
                        }
                        if (!esNumeroEntero(sheet.getCell(0, fila).getContents().trim())) {
                            System.out.println("ERROR: " + sheet.getCell(0, fila).getContents());
                        } else {
                            objdata.setCodigo(Integer.valueOf(sheet.getCell(0, fila).getContents()));
                            objdata.setNroDocIdentCliente(sheet.getCell(1,fila).getContents());
                            objdata.setRazSocialCliente(sheet.getCell(2,fila).getContents());
                            objdata.setBaseImpOpeGravada(sheet.getCell(3,fila).getContents().replace(".", "").replace(",", "."));                            
                            lstdetalle.add(objdata);
                        }
                    }
                }
                llenartabla();
            }
        } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btngrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngrabarActionPerformed
        // TODO add your handling code here:
        if(lstconsolidada.size()>0){
            LImportar li=new LImportar();
            for (int i = 0; i < lstconsolidada.size(); i++) {
                li.GrabarVentaInafecto(lstconsolidada.get(i));
            }
            JOptionPane.showMessageDialog(this, "Se grabo.");
        }
    }//GEN-LAST:event_btngrabarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btngrabar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblstdata;
    // End of variables declaration//GEN-END:variables
}

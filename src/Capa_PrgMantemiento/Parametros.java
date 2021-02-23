/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_PrgMantemiento;

import Capa_Entidades.EBanco;
import Capa_Entidades.EParametro;
import Capa_Logica.LBanco;
import Capa_Logica.LParametro;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author EXPERTYA
 */
public class Parametros extends javax.swing.JInternalFrame {
        ArrayList<EBanco> lstban = new ArrayList<>();
        ArrayList<EParametro> lispara = new ArrayList<>();
        
         int opc=0;
    /**
     * Creates new form Parametros
     */
    public Parametros() {
        initComponents();
        this.setTitle("Mantenimiento de Parámetros.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        llenarbanco();
        llenarbanco1();
        listaparametros();


        
    }

        public void listaparametros(){
            ArrayList<EParametro> lstpara = new ArrayList<>();
            LParametro lpa = new LParametro();

            lstpara = lpa.ListaParametros();
            if (lstpara.size() > 0) {
                EParametro obj1 = new EParametro();
                System.out.println(lstpara.size());
                obj1 = lstpara.get(0);
                System.out.println(obj1.getCodParametro());
             
//            txtparametro.setColumns(obj1.getCodParametro());
            txtrazon.setText(obj1.getRazonSocial());
            txtruc.setText(obj1.getRucParametro());
            txtdireccion.setText(obj1.getDireParametro());
            cbobanco.setSelectedItem(obj1.getNomBanco().toString());
            
            txtctasoles.setText(obj1.getCTASoles());
            txtctadolares.setText(obj1.getCTADolares());
            txtccisoles.setText(obj1.getCCISoles().toString());
            txtccidolares.setText(obj1.getCCIDolares());
            txtconsol.setText(obj1.getCTAConSol());
            txtcondol.setText(obj1.getCTAConDol());
            
            cbobanco1.setSelectedItem(obj1.getNomBanco2().toString());
            txtctasol2.setText(obj1.getCTASoles2());
            txtctadol2.setText(obj1.getCTADolares2());
            txtccisol2.setText(obj1.getCCISoles2());
            txtccidol2.setText(obj1.getCCIDolares2());
            txtconsol2.setText(obj1.getCTAConSol2());
            txtcondol2.setText(obj1.getCTAConDol2());
            
            txtigv.setText(""+obj1.getIGV());
            txtcampo1.setText(""+obj1.getCampo1());
            txtcampo2.setText(""+obj1.getCampo2());
            
            txtcuentaigv.setText(obj1.getCuentaIGV());
            txtsolpro.setText(obj1.getCxPSolPro());
            txtdolpro.setText(obj1.getCxPDolPro());
            txtsolrxh.setText(obj1.getCxPSolRxH());
            txtdolrxh.setText(obj1.getCxPDolRxH());        
                }

    }
    
      private void llenarbanco() {
        LBanco limp = new LBanco();
        lstban = limp.ListaBanco();
        cbobanco.removeAllItems();
        cbobanco.addItem("<Seleccione>");
        for (int i = 0; i < lstban.size(); i++) {
            cbobanco.addItem(lstban.get(i).getNomBanco());
        }
    }
    
      
       private void llenarbanco1() {
        LBanco limp = new LBanco();
        lstban = limp.ListaBanco();
       cbobanco1.removeAllItems();
        cbobanco1.addItem("<Seleccione>");
        for (int i = 0; i < lstban.size(); i++) {
            cbobanco1.addItem(lstban.get(i).getNomBanco());
        }
    }
        
         public boolean validar(){
        int bool=0;
            if(txtrazon.getText().toString().trim().length()==0){
                JOptionPane.showMessageDialog(this, "Ingrese Tipo de Razon Social.");
                txtrazon.requestFocus();
                             }else{
                                 if(txtruc.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Ruc.");
                                 txtruc.requestFocus();
                            }else{
                                 if(txtdireccion.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Direccion.");
                                 txtdireccion.requestFocus();
                             }else{
                                 if(cbobanco.getSelectedIndex()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Banco.");
                                 cbobanco.requestFocus();
                             }else{
                                 if(txtctasoles.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Soles.");
                                 txtctasoles.requestFocus();
                             }else{
                                if(txtctadolares.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Dolares.");
                                 txtctadolares.requestFocus();
                             }else{
                                 if(txtccisoles.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Soles.");
                                 txtccisoles.requestFocus();
                             }else{
                                 if(txtccidolares.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Dolares.");
                                 txtccidolares.requestFocus();
                             }else{
                                 if(txtconsol.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Contable Soles.");
                                 txtconsol.requestFocus();  
                             }else{
                                 if(txtcondol.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Contable Dolares.");
                                 txtcondol.requestFocus(); 
                              }else{
                                 if(txtctasol2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Soles.");
                                 txtctasol2.requestFocus();
                               }else{
                                 if(txtctadol2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Dolares.");
                                 txtctadol2.requestFocus();
                              }else{
                                 if(txtccisol2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Soles.");
                                 txtccisol2.requestFocus();
                              }else{
                                 if(txtccidol2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Dolares.");
                                 txtccidol2.requestFocus();
                               }else{
                                 if(txtconsol2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Contable Soles.");
                                 txtconsol2.requestFocus();
                               }else{
                                 if(txtcondol2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Contable Dolares.");
                                 txtcondol2.requestFocus();   
                               }else{
                                 if(txtigv.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese IGV.");
                                 txtigv.requestFocus();
                               }else{
                                 if(txtcampo1.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese CAMPO1.");
                                 txtcampo1.requestFocus();
                               }else{
                                 if(txtcampo2.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese CAMPO2.");
                                 txtcampo2.requestFocus();  
                               }else{
                                 if(txtcuentaigv.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta IGV.");
                                 txtcuentaigv.requestFocus();  
                               }else{
                                 if(txtsolpro.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Soles del Proveedor.");
                                 txtsolpro.requestFocus();  
                               }else{
                                 if(txtdolpro.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Dolares del Proveedor.");
                                 txtdolpro.requestFocus();  
                               }else{
                                 if(txtsolrxh.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Soles.");
                                 txtsolrxh.requestFocus();  
                               }else{
                                 if(txtdolrxh.getText().toString().trim().length()==0){
                                 JOptionPane.showMessageDialog(this, "Ingrese Cuenta Dolares.");
                                 txtdolrxh.requestFocus();    
                                 

                }else{
                    bool=1;
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                                 }
                }
                }
        }
        if(bool==0){
            return false;
        }else{
            return true;
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

        jPanel5 = new javax.swing.JPanel();
        txtctasoles = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        cbobanco = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtccisoles = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtconsol = new javax.swing.JTextField();
        txtctadolares = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtccidolares = new javax.swing.JTextField();
        txtcondol = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbobanco1 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtctasol2 = new javax.swing.JTextField();
        txtccisol2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtconsol2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtcondol2 = new javax.swing.JTextField();
        txtccidol2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtctadol2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtcampo2 = new javax.swing.JTextField();
        txtcampo1 = new javax.swing.JTextField();
        txtigv = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtcuentaigv = new javax.swing.JTextField();
        txtsolpro = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtdolpro = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsolrxh = new javax.swing.JTextField();
        txtrazon = new javax.swing.JTextField();
        txtdolrxh = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        lblborde2 = new javax.swing.JLabel();
        lblborde3 = new javax.swing.JLabel();
        lblborde4 = new javax.swing.JLabel();
        lblborde5 = new javax.swing.JLabel();
        lblborde6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtctasoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtctasoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 130, 20));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel10.setText("Ruc");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 10));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel11.setText("CTA Soles");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 10));

        txtruc.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 130, 20));

        cbobanco.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbobanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        jPanel5.add(cbobanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel14.setText("Banco");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        txtdireccion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 440, 20));

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel15.setText("Dirección");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 10));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText("CCI Soles");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 10));

        txtccisoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtccisoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 130, 20));

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel13.setText("CTA Contable");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 10));

        txtconsol.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtconsol, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 130, 20));

        txtctadolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtctadolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 130, 20));

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel16.setText("CTA Dólares");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, 10));

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel17.setText("CCI Dólares");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, 10));

        txtccidolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtccidolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 130, 20));

        txtcondol.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtcondol, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 130, 20));

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel18.setText("Cta Contable");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, -1, 10));

        cbobanco1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbobanco1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        jPanel5.add(cbobanco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel19.setText("Banco");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel20.setText("CTA Soles");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 10));

        txtctasol2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtctasol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 130, 20));

        txtccisol2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtccisol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 130, 20));

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel21.setText("CCI Soles");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 10));

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel22.setText("CTA Contable");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 10));

        txtconsol2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtconsol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 130, 20));

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel23.setText("CTA Contable");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, -1, 10));

        txtcondol2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtcondol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 130, 20));

        txtccidol2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtccidol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 130, 20));

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel24.setText("CCI Dólares");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, 10));

        txtctadol2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtctadol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 130, 20));

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel25.setText("CTA Dólares");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, -1, 10));

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel26.setText("Retención RxH");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 10));

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel27.setText("IGV");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, 10));

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel28.setText("Retención Fac.");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, 10));

        txtcampo2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtcampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 130, 20));

        txtcampo1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtcampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 130, 20));

        txtigv.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtigv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtigvKeyTyped(evt);
            }
        });
        jPanel5.add(txtigv, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 130, 20));

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel29.setText("CUENTA IGV");
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, -1, 10));

        txtcuentaigv.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtcuentaigv, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 130, 20));

        txtsolpro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtsolpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, 130, 20));

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel30.setText("CxP Sol Proveedor");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, -1, 10));

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel31.setText("CxP Dol Proveedor");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, -1, 10));

        txtdolpro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtdolpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, 130, 20));

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel32.setText("CxP Dol.RxH");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, 10));

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel33.setText("CxP Sol RxH");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, -1, 10));

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel34.setText("Razón Social");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("(Ej. 0.10)");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 50, 20));

        txtsolrxh.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtsolrxh, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, 130, 20));

        txtrazon.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtrazon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 440, 20));

        txtdolrxh.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel5.add(txtdolrxh, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 130, 20));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel5.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 70, 30));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel5.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 70, 30));

        lblborde2.setBackground(new java.awt.Color(255, 255, 255));
        lblborde2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde2.setToolTipText("");
        lblborde2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Cuentas Contables", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel5.add(lblborde2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 280, 130));

        lblborde3.setBackground(new java.awt.Color(255, 255, 255));
        lblborde3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde3.setToolTipText("");
        lblborde3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Datos ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel5.add(lblborde3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 110));

        lblborde4.setBackground(new java.awt.Color(255, 255, 255));
        lblborde4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde4.setToolTipText("");
        lblborde4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Banco Secundario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel5.add(lblborde4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 580, 120));

        lblborde5.setBackground(new java.awt.Color(255, 255, 255));
        lblborde5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde5.setToolTipText("");
        lblborde5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Porcentajes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel5.add(lblborde5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 300, 90));

        lblborde6.setBackground(new java.awt.Color(255, 255, 255));
        lblborde6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde6.setToolTipText("");
        lblborde6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Banco Principal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel5.add(lblborde6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 580, 120));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 18, -1, 490));

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("INGRESAR Y/O MODIFICAR PARÁMETROS");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 20));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
         if(validar()){
                 EParametro objtc = new EParametro();
                 objtc.setRazonSocial(txtrazon.getText().toString());
                 objtc.setRucParametro(txtruc.getText().toString());
                 objtc.setDireParametro(txtdireccion.getText().toString());
                 objtc.setCodBanco1(lstban.get(cbobanco.getSelectedIndex()-1).getCodBanco());
                 
                 objtc.setCTASoles(txtctasoles.getText().toString());
                 objtc.setCTADolares(txtctadolares.getText().toString());
                 objtc.setCCISoles(txtccisoles.getText().toString());
                 objtc.setCCIDolares(txtccidolares.getText().toString());
                 objtc.setCTAConSol(txtconsol.getText().toString());
                 objtc.setCTAConDol(txtcondol.getText().toString());
                 
                 objtc.setCodBanco2(lstban.get(cbobanco1.getSelectedIndex()-1).getCodBanco());
                 objtc.setCTASoles2(txtctasol2.getText().toString());
                 objtc.setCTADolares2(txtctadol2.getText().toString());
                 objtc.setCCISoles2(txtccisol2.getText().toString());
                 objtc.setCCIDolares2(txtccidol2.getText().toString());
                 objtc.setCTAConSol2(txtconsol2.getText().toString());
                 objtc.setCTAConDol2(txtcondol2.getText().toString());
                 
                 objtc.setIGV (Double.parseDouble(txtigv.getText().toString()));
                 objtc.setCampo1(Double.parseDouble(txtcampo1.getText().toString()));
                 objtc.setCampo2(Double.parseDouble(txtcampo2.getText().toString()));
                 
                 objtc.setCuentaIGV(txtcuentaigv.getText().toString());
                 objtc.setCxPSolPro(txtsolpro.getText().toString());
                 objtc.setCxPDolPro(txtdolpro.getText().toString());
                 objtc.setCxPSolRxH(txtsolrxh.getText().toString());
                 objtc.setCxPDolRxH(txtdolrxh.getText().toString());
       //          objtc.setUsuCrea(SesionUsuario.misesion.getUsuario());
                 
             LParametro ltc = new LParametro();
             if (opc == 0) {
                 //modificar
                 if (JOptionPane.showConfirmDialog(null, "Se va MODIFICAR el Parámetro " + txtrazon.getText().toString().trim().toUpperCase() + " - " + txtruc.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                     if (ltc.ActualizarParametro(objtc)) {
                         listaparametros();
                         JOptionPane.showMessageDialog(this, "Se actualizó correctamente el Parámetro " + txtrazon.getText().toString().trim().toUpperCase() + " - " + txtruc.getText().trim() + ".");
                     } else {
                         JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                     }
                 } else {
                     JOptionPane.showMessageDialog(this, "TC Venta que intenta registrar ya existe, ingrese uno nuevo.");
                     txtrazon.requestFocus();
                 }

                    
             }

         }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void txtigvKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtigvKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0' || c > '9') && (c != evt.VK_BACK_SPACE) && (c != '.'))) {
            evt.consume();
        }
        String unitCost = txtigv.getText().trim();
        int dot = unitCost.indexOf('.');
        if (dot > 0) {
            if (txtigv.getCaretPosition() > dot
                && dot + 3 < unitCost.length()) {
                evt.consume();
            }
        }
        if (txtigv.getText().length() >= 4) {
            evt.consume();
        }
    }//GEN-LAST:event_txtigvKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbobanco;
    private javax.swing.JComboBox cbobanco1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblborde2;
    private javax.swing.JLabel lblborde3;
    private javax.swing.JLabel lblborde4;
    private javax.swing.JLabel lblborde5;
    private javax.swing.JLabel lblborde6;
    private javax.swing.JTextField txtcampo1;
    private javax.swing.JTextField txtcampo2;
    private javax.swing.JTextField txtccidol2;
    private javax.swing.JTextField txtccidolares;
    private javax.swing.JTextField txtccisol2;
    private javax.swing.JTextField txtccisoles;
    private javax.swing.JTextField txtcondol;
    private javax.swing.JTextField txtcondol2;
    private javax.swing.JTextField txtconsol;
    private javax.swing.JTextField txtconsol2;
    private javax.swing.JTextField txtctadol2;
    private javax.swing.JTextField txtctadolares;
    private javax.swing.JTextField txtctasol2;
    private javax.swing.JTextField txtctasoles;
    private javax.swing.JTextField txtcuentaigv;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdolpro;
    private javax.swing.JTextField txtdolrxh;
    private javax.swing.JTextField txtigv;
    private javax.swing.JTextField txtrazon;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtsolpro;
    private javax.swing.JTextField txtsolrxh;
    // End of variables declaration//GEN-END:variables
}

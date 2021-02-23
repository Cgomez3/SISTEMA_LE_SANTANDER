/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_PrgMantemiento;

import Capa_Entidades.EDetraccion;
import Capa_Logica.LDetraccion;
import Capa_Logica.SesionUsuario;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class Detraccion extends javax.swing.JInternalFrame {
        String titulos[] = {"Codigo", "TipoDetraccion", "Porcentaje"};
        DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
        ArrayList<EDetraccion> lstdetra =new ArrayList<>();
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
          int opc=0;
    
    /**
     * Creates new form Detraccion
     */
    public Detraccion() {
        initComponents();
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        this.setTitle("Mantenimiento de Conceptos de Detracción.");
        
        tblistadetraccion.setModel(modTbdatos);
        tblistadetraccion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblistadetraccion.getTableHeader().setReorderingAllowed(false);
        tblistadetraccion.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblistadetraccion.getColumnModel().getColumn(1).setPreferredWidth(400);
        tblistadetraccion.getColumnModel().getColumn(2).setPreferredWidth(175);
        
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        listadetraccion();
        if(tblistadetraccion.getRowCount()>0){
            seleccionarfila(0);
            tblistadetraccion.setRowSelectionInterval(0, 0);
        }
        tblistadetraccion.getTableHeader().setReorderingAllowed(false);
    }

        private void limpiar(){
        txtcod.setText("");
        txttipodetra1.setText("");
        txtporcentajedetra.setText("");
        opc=0;
        txtcod.requestFocus(); 
    }
    
    
    
     private void listadetraccion(){
         DecimalFormat formato = new DecimalFormat("##0.0000", simbolo);
         LDetraccion ltc=new LDetraccion();
        lstdetra=ltc.ListaDetraccion();
        modTbdatos.setRowCount(0);
        if(lstdetra.size()>0){
             for(int i=0;i<lstdetra.size();i++){
                 Object nuevafila[] = new Object[4];
                 nuevafila[0] = lstdetra.get(i).getCodDetraccion();
                 nuevafila[1] = lstdetra.get(i).getTipoDetra().toUpperCase();
                 nuevafila[2] = formato.format(lstdetra.get(i).getPorcentajeDetra());
                 
                 modTbdatos.addRow(nuevafila);
             }
        }
        tblistadetraccion.setModel(modTbdatos);
         lblcontador.setText(tblistadetraccion.getRowCount() + " Concepto(s).");
    }  
    
     private void seleccionarfila(int fila){
          if (fila >= 0) {
              txtcod.setText(tblistadetraccion.getValueAt(fila, 0).toString());
              txttipodetra1.setText(tblistadetraccion.getValueAt(fila, 1).toString());
              txtporcentajedetra.setText(tblistadetraccion.getValueAt(fila, 2).toString());

        opc=1;
          }
    }    
    
       public boolean validar(){
        int bool=0;
         if(txtcod.getText().toString().trim().length()==0){
                JOptionPane.showMessageDialog(this, "Ingrese el Codigo");
                txtcod.requestFocus();
            }else{
            if(txttipodetra1.getText().toString().trim().length()==0){
                JOptionPane.showMessageDialog(this, "Ingrese Tipo de Detraccion.");
                txttipodetra1.requestFocus();
            }else{
                if(txtporcentajedetra.getText().toString().trim().length()==0){
                    JOptionPane.showMessageDialog(this, "Ingrese Porcentaje.");
                    txtporcentajedetra.requestFocus();
                }else{
                    bool=1;
                }
            }
        }
        if(bool==0){
            return false;
        }else{
            return true;
        }
    }   
     
       
       public boolean esDecimal(String val){
        String numero1;
        String numero2;
        String punto;
        if (val.trim().length() == 5) {
            numero1 = val.substring(0, 1);
            numero2 = val.substring(2, 5);
            punto = val.substring(1, 2);
            if (esNumero(numero1)) {
                if (punto.trim().equalsIgnoreCase(".")) {
                    if (esNumero(numero2)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }    
       
       
        public boolean esNumero(String val){
        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        txtcod = new javax.swing.JTextField();
        txtporcentajedetra = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistadetraccion = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel5 = new javax.swing.JLabel();
        txttipodetra1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblcontador = new javax.swing.JLabel();
        lblborde1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INGRESAR Y/O MODIFICAR CONCEPTO DE DETRACCIÓN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setText("Código");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setText("Porcentaje");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        btnnuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 70, 30));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 70, 30));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 70, 30));

        txtcod.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodKeyTyped(evt);
            }
        });
        jPanel1.add(txtcod, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 110, 20));

        txtporcentajedetra.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtporcentajedetra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtporcentajedetraActionPerformed(evt);
            }
        });
        txtporcentajedetra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtporcentajedetraKeyTyped(evt);
            }
        });
        jPanel1.add(txtporcentajedetra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 60, 20));

        tblistadetraccion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistadetraccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblistadetraccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblistadetraccionMouseReleased(evt);
            }
        });
        tblistadetraccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblistadetraccionKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblistadetraccion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 590, 320));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("(Ej. 0.10)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 50, 20));

        txttipodetra1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txttipodetra1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttipodetra1KeyTyped(evt);
            }
        });
        jPanel1.add(txttipodetra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 480, 20));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setText("Concepto");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        lblcontador.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblcontador.setText("0 Conceptos.");
        jPanel1.add(lblcontador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 70, -1));

        lblborde1.setBackground(new java.awt.Color(255, 255, 255));
        lblborde1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde1.setToolTipText("");
        lblborde1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Datos de Detracción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        limpiar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
            if(validar()){
                 EDetraccion objtc = new EDetraccion();
            objtc.setCodDetraccion(txtcod.getText().toString());
            objtc.setTipoDetra(txttipodetra1.getText().toString().trim().toUpperCase());
            objtc.setPorcentajeDetra(Double.parseDouble(txtporcentajedetra.getText().toString()));
            objtc.setUsuarioCrea(SesionUsuario.misesion.getUsuario());
            
                LDetraccion ltc = new LDetraccion();
               if(opc==0){
               //agregar
                   if (JOptionPane.showConfirmDialog(null, "Se va GRABAR Detracción " + txtcod.getText().toString().trim().toUpperCase() + " - " + txttipodetra1.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                   if(ltc.InsertarDetraccion(objtc)){
                       JOptionPane.showMessageDialog(this, "Se grabó correctamente la Detraccion" + txtcod.getText().toString().trim().toUpperCase() + " - " + txttipodetra1.getText().trim() + ".");
                       listadetraccion();
                       limpiar();
                   }else{
                       JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                   }
               }
               }
//                   else{
//                  JOptionPane.showMessageDialog(this, "La Detraccion que intenta registrar ya existe, ingrese uno nuevo.");
//                  txtcod.requestFocus();
//               }
             if(opc==1){
               //modificar
               if (JOptionPane.showConfirmDialog(null, "Se va MODIFICAR al cliente " + txtcod.getText().toString().trim().toUpperCase() + " - " + txttipodetra1.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                   if(ltc.ActualizarDetraccion(objtc)){
                       JOptionPane.showMessageDialog(this, "Se actualizó correctamente cliente " + txtcod.getText().toString().trim().toUpperCase() + " - " + txttipodetra1.getText().trim() + ".");
                       listadetraccion();
                       limpiar();
                   }else{
                       JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                   }
               }
            } 
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtcodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();
//        if (((c < '0' || c > '9') && (c != evt.VK_BACK_SPACE) && (c != '.'))) {
//            evt.consume();
//        }
//        String unitCost = txttipodetra.getText().trim();
//        int dot = unitCost.indexOf('.');
//        if (dot > 0) {
//            if (txttipodetra.getCaretPosition() > dot
//                && dot + 3 < unitCost.length()) {
//                evt.consume();
//            }
//        }
//        if (txttipodetra.getText().length() >= 5) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtcodKeyTyped

    private void txtporcentajedetraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtporcentajedetraKeyTyped

        char c = evt.getKeyChar();
        if (((c < '0' || c > '9') && (c != evt.VK_BACK_SPACE) && (c != '.'))) {
            evt.consume();
        }
        String unitCost = txtporcentajedetra.getText().trim();
        int dot = unitCost.indexOf('.');
        if (dot > 0) {
            if (txtporcentajedetra.getCaretPosition() > dot
                && dot + 4 < unitCost.length()) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtporcentajedetraKeyTyped

    private void tblistadetraccionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistadetraccionMouseReleased
        // TODO add your handling code here:
        int fila=tblistadetraccion.getSelectedRow();
        if(fila>=0){
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_tblistadetraccionMouseReleased

    private void tblistadetraccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblistadetraccionKeyReleased
        // TODO add your handling code here:
        int fila = tblistadetraccion.getSelectedRow();
        if(fila>=0){
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
             //  seleccionarfila(fila);
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
              //  seleccionarfila(fila);
            }
        }
    }//GEN-LAST:event_tblistadetraccionKeyReleased

    private void txttipodetra1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipodetra1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipodetra1KeyTyped

    private void txtporcentajedetraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtporcentajedetraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtporcentajedetraActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblborde1;
    private javax.swing.JLabel lblcontador;
    private javax.swing.JTable tblistadetraccion;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtporcentajedetra;
    private javax.swing.JTextField txttipodetra1;
    // End of variables declaration//GEN-END:variables
}

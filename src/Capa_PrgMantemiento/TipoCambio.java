    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_PrgMantemiento;

import Capa_Conexion.BDTipoCambio;
import Capa_Logica.SesionUsuario;
import Capa_Entidades.ETipoCambio;
import Capa_Logica.LTipoCambio;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class TipoCambio extends javax.swing.JInternalFrame {
    public String sw = "";
    int opc=0;
    String titulos[] = {"Fecha", "TCCompra", "TCVenta"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    ArrayList<ETipoCambio> lsttipocambio=new ArrayList<>();
    DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
    public int otrofrm=0;
    public String unframe;
    public String mifecha;
    

   int i = 1;
//    
    /**
     * Creates new form TipoCambio
     */
    public TipoCambio() {
        initComponents();
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        this.setTitle("Mantenimiento de Tipo de Cambio.");
         Date hoy=new Date();
        dcfecha.setDate(hoy);
        tblistatipocambio.setModel(modTbdatos);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        listatipocambio();
        if(tblistatipocambio.getRowCount()>0){
            seleccionarfila(0);
            tblistatipocambio.setRowSelectionInterval(0, 0);
        }
        tblistatipocambio.getTableHeader().setReorderingAllowed(false);
    }
        private void limpiar(){
            Date hoy=new Date();
        dcfecha.setDate(hoy);
        txttccompra.setText("");
        txttcventa.setText("");
        opc=0;
        txttccompra.requestFocus(); 
    }

     public void ingresartc(String mifecha){
        dcfecha.setDate(miFecha(mifecha));
        txttccompra.setText("");
        txttcventa.setText("");
        opc=0;
        txttccompra.requestFocus();
    }    
        
     public boolean esNumero(String val){
        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
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
        
        
       private void listatipocambio(){
        DecimalFormat formato = new DecimalFormat("0.000", simbolo);
        LTipoCambio ltc=new LTipoCambio();
        lsttipocambio=ltc.ListaTipoCambio();
        modTbdatos.setRowCount(0);
        if(lsttipocambio.size()>0){
             for(int i=0;i<lsttipocambio.size();i++){
                 Object nuevafila[] = new Object[3];
//                 nuevafila[0] = lsttipocambio.get(i).getCodTipoCambio();
                 nuevafila[0] = lsttipocambio.get(i).getFecha();
                 nuevafila[1] = formato.format(lsttipocambio.get(i).getPrecioCompra());
                 nuevafila[2] = formato.format(lsttipocambio.get(i).getPrecioVenta());
                 modTbdatos.addRow(nuevafila);
             }
        }
        tblistatipocambio.setModel(modTbdatos);
    }  
        
     public boolean validar(){
        int bool=0;
        if(dcfecha.getDate()==null){
            JOptionPane.showMessageDialog(this, "Ingrese Fecha.");
        }else{
            if(txttccompra.getText().toString().trim().length()==0){
                JOptionPane.showMessageDialog(this, "Ingrese TCCompra.");
                txttccompra.requestFocus();
            }else{
                if(txttcventa.getText().toString().trim().length()==0){
                    JOptionPane.showMessageDialog(this, "Ingrese TCVenta.");
                    txttcventa.requestFocus();
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
       
       
    private boolean validar2donivel(){
        int bool=0;
        if (validar()) {
            if (esDecimal(txttccompra.getText().toString().trim())) {
                if (Float.parseFloat(txttccompra.getText().trim()) == 0) {
                    JOptionPane.showMessageDialog(this, "No puede ingresar como 0 el tipo de cambio de compra.");
                    txttccompra.requestFocus();
                } else {
                    if (esDecimal(txttcventa.getText().toString().trim())) {
                        if (Float.parseFloat(txttcventa.getText().trim()) == 0) {
                            JOptionPane.showMessageDialog(this, "No puede ingresar como 0 el tipo de cambio de venta.");
                        } else {
                            bool=1;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Ingrese formato correcto. (Ejm: 1.212).");
                        txttcventa.requestFocus();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese formato correcto. (Ejm: 1.212).");
                txttccompra.requestFocus();
            }
        }
        if(bool==0){
            return false;
        }else{
            return true;
        }
    } 
     
    String fechaformateador(Date fecha) {
        String fechaletra = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        return fechaletra;
    }    

    private boolean fecharepetida(){
        int bool=0;
        for(int i=0;i<lsttipocambio.size();i++){
            if(fechaformateador(dcfecha.getDate()).toString().trim().equalsIgnoreCase(lsttipocambio.get(i).getFecha().toString().trim())){
                bool=1;
            }
        }
        if(bool==0){
            return true;
        }else{
            return false;
        }
    }
    
     public Date miFecha(String cad) {
        StringBuilder strb = new StringBuilder();
        strb.append(cad.substring(3, 5));
        strb.append("/");
        strb.append(cad.substring(0, 2));
        strb.append("/");
        strb.append(cad.substring(6, 10));
        Date fecha = new Date(strb.toString());
        return fecha;
    }
    
      private void seleccionarfila(int fila){
        DecimalFormat formato = new DecimalFormat("0.000", simbolo);
//        txtcodigo.setText(""+lsttipocambio.get(fila).getCodTipoCambio());
        txttccompra.setText(formato.format(lsttipocambio.get(fila).getPrecioCompra()));
        txttcventa.setText(formato.format(lsttipocambio.get(fila).getPrecioVenta()));
        dcfecha.setDate(miFecha(lsttipocambio.get(fila).getFecha()));
        opc=1;
    }    
        
       private void verificar(){
        if(otrofrm==1){
            double valor=0.0;
            for(int i=0;i<tblistatipocambio.getRowCount();i++){
                if(tblistatipocambio.getValueAt(i, 0).toString().trim().equalsIgnoreCase(mifecha.toString().trim())){
                    valor=Double.valueOf(tblistatipocambio.getValueAt(i, 2).toString().trim());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dcfecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        txttccompra = new javax.swing.JTextField();
        txttcventa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistatipocambio = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel6 = new javax.swing.JLabel();
        lblborde1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INGRESAR Y/O MODIFICAR TIPO DE CAMBIO");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        dcfecha.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel1.add(dcfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 90, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TC COMPRA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 37, 70, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TC VENTA");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 37, 70, -1));

        btnnuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 70, 30));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 70, 30));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 70, 30));

        txttccompra.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txttccompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttccompraKeyTyped(evt);
            }
        });
        jPanel1.add(txttccompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 70, -1));

        txttcventa.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txttcventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttcventaActionPerformed(evt);
            }
        });
        txttcventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttcventaKeyTyped(evt);
            }
        });
        jPanel1.add(txttcventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 70, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("(Ej. 2.621)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 50, 20));

        tblistatipocambio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistatipocambio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblistatipocambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblistatipocambioMouseReleased(evt);
            }
        });
        tblistatipocambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblistatipocambioKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblistatipocambio);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 380, 380));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FECHA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 37, 70, -1));

        lblborde1.setBackground(new java.awt.Color(255, 255, 255));
        lblborde1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde1.setToolTipText("");
        lblborde1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Tipo de Cambio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 380, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttccompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttccompraKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (((c < '0' || c > '9') && (c != evt.VK_BACK_SPACE) && (c != '.'))) {
            evt.consume();
        }
        String unitCost = txttccompra.getText().trim();
        int dot = unitCost.indexOf('.');
        if (dot > 0) {
            if (txttccompra.getCaretPosition() > dot
                && dot + 3 < unitCost.length()) {
                evt.consume();
            }
        }
        if (txttccompra.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txttccompraKeyTyped

    private void txttcventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttcventaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (((c < '0' || c > '9') && (c != evt.VK_BACK_SPACE) && (c != '.'))) {
            evt.consume();
        }
        String unitCost = txttcventa.getText().trim();
        int dot = unitCost.indexOf('.');
        if (dot > 0) {
            if (txttcventa.getCaretPosition() > dot
                && dot + 3 < unitCost.length()) {
                evt.consume();
            }
        }
        if (txttcventa.getText().length() >= 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txttcventaKeyTyped

    private void tblistatipocambioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistatipocambioMouseReleased
        // TODO add your handling code here:
        int fila=tblistatipocambio.getSelectedRow();
        if(fila>=0){
            seleccionarfila(fila);
        }
        
        
        
         int i=tblistatipocambio.getSelectedRow();
            if(fila==i){
                    if(SesionUsuario.lblTC != null && otrofrm==1){
                    SesionUsuario.lblTC.setText(tblistatipocambio.getValueAt(i, 1).toString());
                    this.dispose();
                    }
            }else{
                
                fila=i;
            }
         
        
    }//GEN-LAST:event_tblistatipocambioMouseReleased

    private void tblistatipocambioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblistatipocambioKeyReleased
        // TODO add your handling code here:
        int fila = tblistatipocambio.getSelectedRow();
        if(fila>=0){
            if (evt.getKeyCode() == KeyEvent.VK_UP) {
                seleccionarfila(fila);
            }
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                seleccionarfila(fila);
            }
        }
    }//GEN-LAST:event_tblistatipocambioKeyReleased

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        limpiar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

            if(validar2donivel()){
                 ETipoCambio objtc = new ETipoCambio();
//            objtc.setCodTipoCambio(Integer.parseInt(txtcodigo.getText().toString()));
            objtc.setFecha(fechaformateador(dcfecha.getDate()));
            objtc.setPrecioCompra(Double.parseDouble(txttccompra.getText().toString()));
            objtc.setPrecioVenta(Double.parseDouble(txttcventa.getText().toString()));
            objtc.setUsuCrea(SesionUsuario.misesion.getUsuario());
             
            LTipoCambio ltc = new LTipoCambio();
               if(opc==0){
               //agregar
                   if (JOptionPane.showConfirmDialog(null, "Se va GRABAR taza " + txttccompra.getText().toString().trim().toUpperCase() + " - " + txttcventa.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                   if(ltc.InsertarTipoCambio(objtc)){
                       JOptionPane.showMessageDialog(this, "Se grabó correctamente la taza " + txttccompra.getText().toString().trim().toUpperCase() + " - " + txttcventa.getText().trim() + ".");
                       listatipocambio();
                        if(otrofrm==1){
                           SesionUsuario.lblTC.setText(txttccompra.getText().toString().trim().toUpperCase());
                           SesionUsuario.lblTC.setForeground(Color.green);
                       }
                       limpiar();
                   }else{
                       JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                   }
               }
               }else{
                  txttcventa.requestFocus();
               }
               if(opc==1){
               //modificar
               if (JOptionPane.showConfirmDialog(null, "Se va MODIFICAR la taza " + txttccompra.getText().toString().trim().toUpperCase() + " - " + txttcventa.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                   if(ltc.ActualizarTipoCambio(objtc)){
                       JOptionPane.showMessageDialog(this, "Se actualizó correctamente cliente " + txttccompra.getText().toString().trim().toUpperCase() + " - " + txttcventa.getText().trim() + ".");
                       listatipocambio();
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

    private void txttcventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttcventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttcventaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private com.toedter.calendar.JDateChooser dcfecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblborde1;
    private javax.swing.JTable tblistatipocambio;
    private javax.swing.JTextField txttccompra;
    private javax.swing.JTextField txttcventa;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_PrgMantemiento;
import Capa_Entidades.EProveedor;
import Capa_Entidades.EBanco;
import Capa_Entidades.ECuentaBancaria;
import Capa_Entidades.EMoneda;
import Capa_Logica.LBanco;
import Capa_Logica.LCuentaBancaria;
import Capa_Logica.LProveedor;
import Capa_Logica.SesionUsuario;
import Capa_Panel.Importar.JDCargaImportRegistros;
import Capa_Panel.Importar.JDCargaRegistros;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.DecimalFormatSymbols;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class Personal extends javax.swing.JInternalFrame{ 
    String titulos[] = {"DocPersonal", "TipoDocPersonal", "RazonSocial", "Estado", "Direccion", "Telefono", "Banco", "CuentaSoles", "CCISoles", "CuentaDolares", "CCIDolares"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    ArrayList<EBanco> lstban = new ArrayList<>();
    ArrayList<EMoneda> lstmon = new ArrayList<>();
    ArrayList<ECuentaBancaria> lstcuenta = new ArrayList<>();
    ArrayList<EProveedor> lstproveedor = new ArrayList<>();
    ArrayList<EProveedor> lsttemporal = new ArrayList<>();
    ArrayList<EProveedor> lisp = new ArrayList<>();
    ArrayList<EProveedor> lispexcel = new ArrayList<>();
    JDCargaRegistros jdc;
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    JDCargaImportRegistros carga;
    String moneda;
    String moneda2;
    String moneda3;
    int accion = 0;
    String ruta = "";


    /**
     * Creates new form Proveedores
     */
    public Personal() {
        initComponents();
        this.setLocation(230, 50);
        this.setTitle("Mantenimiento de Personal.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblstproveedores.setModel(modTbdatos);
        tblstproveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstproveedores.getTableHeader().setReorderingAllowed(false);
        tblstproveedores.getColumnModel().getColumn(0).setPreferredWidth(75);
        tblstproveedores.getColumnModel().getColumn(1).setPreferredWidth(25);
        tblstproveedores.getColumnModel().getColumn(2).setPreferredWidth(225);
        tblstproveedores.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblstproveedores.getColumnModel().getColumn(4).setPreferredWidth(125);
        tblstproveedores.getColumnModel().getColumn(5).setPreferredWidth(75);
        tblstproveedores.getColumnModel().getColumn(6).setPreferredWidth(125);
        tblstproveedores.getColumnModel().getColumn(7).setPreferredWidth(75);
        llenarbanco();
        llenartipopro();
        listaproveedor();
        cbotipodoc.setEnabled(false);
        chkactivo.setSelected(true);
        txtccidolares.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtccisoles.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtctadolares.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtctasoles.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtdireccion.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtdocpro.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtrazonsocial.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txttelefono.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        cbobanco.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
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
    
    private void llenartipopro(){
        LProveedor p = new LProveedor();
        lisp=p.ListaTipoProveedor();
        cbotipodoc.removeAllItems();
        cbotipodoc.addItem("<Seleccione>");
         for (int i = 0; i < lisp.size(); i++) {
            cbotipodoc.addItem("DNI");
         }
        cbotipodoc.setSelectedItem("DNI");                    
    }
    
    private void listaproveedor() {
        LProveedor lpro = new LProveedor();
        lstproveedor = lpro.listaPersonal();
        modTbdatos.setRowCount(0);
        if (lstproveedor.size() > 0) {
            for (int i = 0; i < lstproveedor.size(); i++) {
                Object nuevafila[] = new Object[11];
                nuevafila[0] = lstproveedor.get(i).getDocProveedor();
                nuevafila[1] = lstproveedor.get(i).getTipoDocProveedor();
                nuevafila[2] = lstproveedor.get(i).getRazonSocial();
//                 nuevafila[3] = lstproveedor.get(i).getEstado();
                if (lstproveedor.get(i).getEstado() == 0) {
                    nuevafila[3] = "INACTIVO";
                } else {
                    nuevafila[3] = "ACTIVO";
                }
                nuevafila[4] = lstproveedor.get(i).getDireccion();
                nuevafila[5] = lstproveedor.get(i).getTelefono();
                nuevafila[6] = lstproveedor.get(i).getNomBanco();
                nuevafila[7] = lstproveedor.get(i).getCTASoles();
                nuevafila[8] = lstproveedor.get(i).getCTADolares();
                nuevafila[9] = lstproveedor.get(i).getCCISoles();
                nuevafila[10] = lstproveedor.get(i).getCCIDolares();
                modTbdatos.addRow(nuevafila);
            }
        }
        tblstproveedores.setModel(modTbdatos);
        lblnroregistros.setText(tblstproveedores.getRowCount() + " Persona(s)");
    }

 
        class PrimeThread implements Runnable {

        PrimeThread() {
        }

        public void run() {
            LProveedor lim = new LProveedor();
            System.out.println("TOTAL: " + lsttemporal.size());
            for (int i = 0; i < lsttemporal.size(); i++) {
                //valida
                if(lim.verifRUCrep(lsttemporal.get(i).getDocProveedor())==0){
                    //Insertar en proveedores
                    lim.InsertarProveedorTemp(lsttemporal.get(i));
                     System.out.println("GRABANDO: " + (i + 1));
                    
                }else{
                    //Actualizar proveedores
                    lim.ActualizarProveedorTemp(lsttemporal.get(i));  
                        System.out.println("ACTUALIZA: " + (i + 1));
                }
            }
            
            jdc.setVisible(false);
            jdc.tiempo.stop();
            JOptionPane.showMessageDialog(null, "Se grabó correctamente las personas.");
            limpiar();
            limpiar2();
          
        }
        }
    
      private void limpiar2(){
          lsttemporal.removeAll(lsttemporal);
          lstproveedor.removeAll(lstproveedor);
      }
    
      private void limpiar() {
        txtdocpro.setText("");
        txtrazonsocial.setText("");
        txtdireccion.setText("");
        txttelefono.setText("");
        chkactivo.setSelected(true);
        cbobanco.setSelectedIndex(0);
        txtctasoles.setText("");
        txtctadolares.setText("");
        txtccisoles.setText("");
        txtccidolares.setText("");
        accion=0;
    }
    
      private void pintarfila() {
        if (tblstproveedores.getRowCount() > 0) {
            seleccionarfila(0);
            accion = 1;
        } 
      } 
      
    private int verificarruc(String ruc) {
        LProveedor lprov = new LProveedor();
        int existe = lprov.verifRUCrep(ruc);
        return existe;
    }
    
    private void seleccionarfila(int fila) {
        
        if (fila >= 0) {
            txtdocpro.setText(tblstproveedores.getValueAt(fila, 0).toString());

            cbotipodoc.setSelectedItem(tblstproveedores.getValueAt(fila, 1).toString());
            txtdocpro.setEnabled(false);
            txtrazonsocial.setText(tblstproveedores.getValueAt(fila, 2).toString());
            String estado = tblstproveedores.getValueAt(fila, 3).toString();
            txtdireccion.setText(tblstproveedores.getValueAt(fila, 4).toString());
            if (tblstproveedores.getValueAt(fila, 5) != null) {
                txttelefono.setText(tblstproveedores.getValueAt(fila, 5).toString());
            } else {
                txttelefono.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 6).toString().equalsIgnoreCase("-")) {
                cbobanco.setSelectedItem(tblstproveedores.getValueAt(fila, 6).toString().trim());
            } else {
                cbobanco.setSelectedIndex(0);
            }
            if (!tblstproveedores.getValueAt(fila, 7).toString().equalsIgnoreCase("-")) {
                txtctasoles.setText(tblstproveedores.getValueAt(fila, 7).toString().trim());
            } else {
                txtctasoles.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 8).toString().equalsIgnoreCase("-")) {
                txtccisoles.setText(tblstproveedores.getValueAt(fila, 8).toString().trim());
            } else {
                txtccisoles.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 9).toString().equalsIgnoreCase("-")) {
                txtctadolares.setText(tblstproveedores.getValueAt(fila, 9).toString().trim());
            } else {
                txtctadolares.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 10).toString().equalsIgnoreCase("-")) {
                txtccidolares.setText(tblstproveedores.getValueAt(fila, 10).toString().trim());
            } else {
                txtccidolares.setText("");
            }
//            LCuentaBancaria lcuen = new LCuentaBancaria();
//            ECuentaBancaria obj1 = new ECuentaBancaria();
            
//            obj1=lstcuenta;
            String vruc=tblstproveedores.getValueAt(fila, 0).toString();
            System.out.println(vruc);
         
            
            accion = 1;
            if ( estado.equalsIgnoreCase("ACTIVO")) {
                chkactivo.setSelected(true);
            } else {
                chkactivo.setSelected(false);
            }
        }
    }
    
    private int validarcampos() {
        int validar = 0;
          if (cbotipodoc.getSelectedIndex() == 0) {
              JOptionPane.showMessageDialog(this, "Escoja Tipo de Documento.");
              cbotipodoc.requestFocus();
              validar = 1;
              return validar;
            } else {
             validar = 0;
          } 
        if(cbotipodoc.getSelectedItem().equals("DNI")){
                if (txtdocpro.getText().trim().length() != 8) {
                JOptionPane.showMessageDialog(this, "El número del Documento debe contener 8 caracteres.");
                txtdocpro.requestFocus();
                validar = 1;
                return validar;
                }else{
                    validar = 0;
                }
            } else {
                if (txtdocpro.getText().trim().length() != 11) {
                JOptionPane.showMessageDialog(this, "El número del Documento debe contener 11 caracteres.");
                txtdocpro.requestFocus();
                validar = 1;
                return validar;
                }else{
                    validar = 0;
                }          
           }
         if (txtrazonsocial.getText().trim().length() == 0) {
              JOptionPane.showMessageDialog(this, "Ingrese la Razon Social.");
              txtrazonsocial.requestFocus();
              validar = 1;
              return validar;
            } else {
             validar = 0;
          }   
          if (txtdireccion.getText().trim().length() == 0) {
              JOptionPane.showMessageDialog(this, "Ingrese la Direccion.");
              txtdireccion.requestFocus();
              validar = 1;
              return validar;
            } else {
             validar = 0;
          }   
//          if(cbobanco.getSelectedItem().equals("Santander") || cbobanco.getSelectedItem().equals("BCP")){
//              txtctasoles.getText().length();
//              
//          }
          
         
        return validar;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtrazonsocial = new javax.swing.JTextField();
        txtctadolares = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblnroregistros = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbobanco = new javax.swing.JComboBox();
        chkactivo = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        cbotipodoc = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txtdocpro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtctasoles = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtccidolares = new javax.swing.JTextField();
        txtccisoles = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstproveedores = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblborde1 = new javax.swing.JLabel();
        lblborde3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Nro DNI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setText("Nombres y Ape.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setText("Dirección");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setText("Teléfono");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txttelefono.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 90, -1));

        txtdireccion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 287, -1));

        txtrazonsocial.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtrazonsocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrazonsocialKeyReleased(evt);
            }
        });
        jPanel1.add(txtrazonsocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 287, -1));

        txtctadolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtctadolares.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtctadolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtctadolaresKeyReleased(evt);
            }
        });
        jPanel1.add(txtctadolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 128, -1));

        btnnuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnnuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, 70, 30));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 70, 30));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 70, 30));

        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 2, 140, -1));

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblnroregistros.setText("0 Personas");
        jPanel1.add(lblnroregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 110, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setText(" Cuenta Dolares");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        cbobanco.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbobanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        cbobanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbobancoKeyReleased(evt);
            }
        });
        jPanel1.add(cbobanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 90, -1));

        chkactivo.setBackground(new java.awt.Color(255, 255, 255));
        chkactivo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        chkactivo.setText("Activo");
        jPanel1.add(chkactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, 19));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel9.setText("Tipo Doc");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        cbotipodoc.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbotipodoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        jPanel1.add(cbotipodoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 90, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel11.setText("Banco");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        txtdocpro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtdocpro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtdocpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdocproKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdocproKeyReleased(evt);
            }
        });
        jPanel1.add(txtdocpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 90, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText(" Cuenta Soles");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        txtctasoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtctasoles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtctasoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtctasolesKeyReleased(evt);
            }
        });
        jPanel1.add(txtctasoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 128, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel13.setText(" CCI Soles");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel14.setText(" CCI Dolares");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, -1));

        txtccidolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtccidolares.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtccidolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 128, -1));

        txtccisoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtccisoles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtccisoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtccisolesKeyReleased(evt);
            }
        });
        jPanel1.add(txtccisoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 128, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyReleased(evt);
            }
        });

        tblstproveedores.setAutoCreateRowSorter(true);
        tblstproveedores.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstproveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblstproveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblstproveedoresMouseClicked(evt);
            }
        });
        tblstproveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblstproveedoresKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblstproveedores);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 680, 290));

        lblborde1.setBackground(new java.awt.Color(255, 255, 255));
        lblborde1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde1.setToolTipText("");
        lblborde1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Datos Bancarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 270, 130));

        lblborde3.setBackground(new java.awt.Color(255, 255, 255));
        lblborde3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde3.setToolTipText("");
        lblborde3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 400, 130));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INGRESAR Y/O MODIFICAR PERSONAL");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 680, 18));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 698, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed

       limpiar();
       
       accion = 0;
       txtdocpro.setEnabled(true);
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here: 06/12/2014
        int verif = validarcampos();
        int estado = 0;
        System.out.println(verif);
        if (chkactivo.isSelected()) {
            estado = 1;
        } else {
            estado = 0;
        }
        if (verif == 0) {
            LProveedor lprov = new LProveedor();
            EProveedor prov = new EProveedor();
            LCuentaBancaria lcuen = new LCuentaBancaria();
            ECuentaBancaria cuen = new ECuentaBancaria();
            prov.setDocProveedor(txtdocpro.getText());
            prov.setTipoDocProveedor("DNI");
            prov.setRazonSocial(txtrazonsocial.getText());
            prov.setEstado(estado);
            prov.setDireccion(txtdireccion.getText());
            prov.setTelefono(txttelefono.getText());

            switch (accion) {
                case 0: //Agregar
                    if (verificarruc(txtdocpro.getText()) == 1) {
                        JOptionPane.showMessageDialog(this, "El DNI que intenta registrar ya existe, ingrese uno nuevo.");
                        txtdocpro.setText("");
                        txtdocpro.requestFocus();
                    } else {
                        if (JOptionPane.showConfirmDialog(null, "Se va a GRABAR el personal " + prov.getRazonSocial().toUpperCase() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                            if (verificarruc(txtdocpro.getText()) < 2) {
                                prov.setUsuCrea(SesionUsuario.misesion.getUsuario());
                                if (lprov.InsertarPersonal(prov)) {
                                    if (txtctasoles.getText().trim().length() != 0 || txtccisoles.getText().trim().length() != 0) {
                                        cuen.setDocProveedor(txtdocpro.getText());
                                        cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                        cuen.setMoneda("SOLES");
                                        cuen.setCuenta(txtctasoles.getText());
                                        cuen.setCuentaci(txtccisoles.getText());
                                        lcuen.InsertarCuentaBancaria(cuen);
                                    }
                                    if (txtctadolares.getText().trim().length() != 0 || txtccidolares.getText().trim().length() != 0) {
                                        cuen.setDocProveedor(txtdocpro.getText());
                                        cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                        cuen.setMoneda("DOLARES");
                                        cuen.setCuenta(txtctadolares.getText());
                                        cuen.setCuentaci(txtccidolares.getText());
                                        lcuen.InsertarCuentaBancaria(cuen);
                                    }
//                                     }
                                    //si el campo soles tiene data inserto y mando en duro el campo soles
                                    //ski el campo dolares tiene data inserto dolares 
                                    listaproveedor();
                                    System.out.println("GRABANDO");
                                    limpiar();
                                    JOptionPane.showMessageDialog(this, "Se grabó correctamente el personal " + prov.getRazonSocial().toUpperCase() + ".");
                                    lblnroregistros.setText(tblstproveedores.getRowCount() + " Personal");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                                }
                            }

                        }
                    }
                    break;
                case 1: //Actualizar
                    if (JOptionPane.showConfirmDialog(null, "Se va a MODIFICAR el personal " + prov.getRazonSocial().toUpperCase() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        prov.setUsumodi(SesionUsuario.misesion.getUsuario());
                        if (lprov.ActualizarPersonal(prov)) {
                            lcuen.eliminarCuenta(txtdocpro.getText());
                            if (txtctasoles.getText().trim().length() != 0 || txtccisoles.getText().trim().length() != 0) {
                                cuen.setDocProveedor(txtdocpro.getText());
                                cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                cuen.setMoneda("SOLES");
                                cuen.setCuenta(txtctasoles.getText());
                                cuen.setCuentaci(txtccisoles.getText());
                                lcuen.InsertarCuentaBancaria(cuen);
                            }
                            if (txtctadolares.getText().trim().length() != 0 || txtccidolares.getText().trim().length() != 0) {
                                cuen.setDocProveedor(txtdocpro.getText());
                                cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                cuen.setMoneda("DOLARES");
                                cuen.setCuenta(txtctadolares.getText());
                                cuen.setCuentaci(txtccidolares.getText());
                                lcuen.InsertarCuentaBancaria(cuen);
                            }
                            listaproveedor();
                            txtrazonsocial.setEnabled(true);
                            accion = 0;
                            limpiar();
                            JOptionPane.showMessageDialog(this, "Se actualizó correctamente el personal " + prov.getRazonSocial().toUpperCase() + ".");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                        }
                    }
                    pintarfila();
                    lblnroregistros.setText(tblstproveedores.getRowCount() + " Proveedores");
                    break;
            }

        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtdocproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocproKeyTyped
         char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
        if (txtdocpro.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdocproKeyTyped

    private void tblstproveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstproveedoresMouseClicked
         int fila=tblstproveedores.getSelectedRow();
        if(fila>=0){
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_tblstproveedoresMouseClicked
       
    private void tblstproveedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblstproveedoresKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int fila = tblstproveedores.getSelectedRow();
            seleccionarfila(fila);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int fila = tblstproveedores.getSelectedRow();
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_tblstproveedoresKeyReleased

    private void jScrollPane1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyTyped
    
    }//GEN-LAST:event_jScrollPane1KeyTyped

    private void jScrollPane1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int fila = tblstproveedores.getSelectedRow();
            seleccionarfila(fila);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int fila = tblstproveedores.getSelectedRow();
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_jScrollPane1KeyReleased

    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        int fila = tblstproveedores.getSelectedRow();
        if (fila >= 0) {
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void txtdocproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocproKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtrazonsocial.requestFocus();
        }
    }//GEN-LAST:event_txtdocproKeyReleased

    private void txtrazonsocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonsocialKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtdireccion.requestFocus();
        }
    }//GEN-LAST:event_txtrazonsocialKeyReleased

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txttelefono.requestFocus();
        }
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            cbobanco.requestFocus();
        }
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void cbobancoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbobancoKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtctasoles.requestFocus();
        }
    }//GEN-LAST:event_cbobancoKeyReleased

    private void txtctasolesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctasolesKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtctadolares.requestFocus();
        }
    }//GEN-LAST:event_txtctasolesKeyReleased

    private void txtctadolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctadolaresKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtccisoles.requestFocus();
        }
    }//GEN-LAST:event_txtctadolaresKeyReleased

    private void txtccisolesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtccisolesKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_TAB){
            txtccidolares.requestFocus();
        }
    }//GEN-LAST:event_txtccisolesKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbobanco;
    private javax.swing.JComboBox cbotipodoc;
    private javax.swing.JCheckBox chkactivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblborde1;
    private javax.swing.JLabel lblborde3;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JTable tblstproveedores;
    private javax.swing.JTextField txtccidolares;
    private javax.swing.JTextField txtccisoles;
    private javax.swing.JTextField txtctadolares;
    private javax.swing.JTextField txtctasoles;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdocpro;
    private javax.swing.JTextField txtrazonsocial;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}

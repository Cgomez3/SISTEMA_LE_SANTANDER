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
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import static javax.management.Query.gt;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.biff.formula.ParseContext;
import jxl.read.biff.BiffException;

/**
 *
 * @author EXPERTYA
 */
public class Proveedores1 extends javax.swing.JInternalFrame {

    String titulos[] = {"DocProveedor", "TipoDocProveedor", "RazonSocial", "Estado", "Direccion", "Telefono", "Contacto", "CorreoContacto", "Banco", "Tipo Cuenta", "CuentaSoles", "CCISoles", "CuentaDolares", "CCIDolares", "CtaDetracciones"};
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
    int focoTexto = 0;
    String ruta = "";

    /**
     * Creates new form Proveedores
     */
    public Proveedores1() {
        initComponents();
        this.setLocation(230, 50);
        this.setTitle("Mantenimiento de Proveedores.");
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
        txtcontacto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtcorreocontacto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtctadolares.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtctasoles.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtcuentadetracciones.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtdireccion.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtdocpro.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtimportacion.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
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

    private void llenartipopro() {
        LProveedor p = new LProveedor();
        lisp = p.ListaTipoProveedor();
        cbotipodoc.removeAllItems();
        cbotipodoc.addItem("<Seleccione>");
        for (int i = 0; i < lisp.size(); i++) {
            cbotipodoc.addItem("RUC");
        }
    }

    private void listaproveedor() {
        LProveedor lpro = new LProveedor();
        lstproveedor = lpro.listaProveedor();
        modTbdatos.setRowCount(0);
        if (lstproveedor.size() > 0) {
            for (int i = 0; i < lstproveedor.size(); i++) {
                Object nuevafila[] = new Object[15];
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
                nuevafila[6] = lstproveedor.get(i).getContacto();
                nuevafila[7] = lstproveedor.get(i).getCorreoContacto();
                nuevafila[8] = lstproveedor.get(i).getNomBanco();
                nuevafila[9] = lstproveedor.get(i).getTipoCuenta();
                nuevafila[10] = lstproveedor.get(i).getCTASoles();
                nuevafila[11] = lstproveedor.get(i).getCTADolares();
                nuevafila[12] = lstproveedor.get(i).getCCISoles();
                nuevafila[13] = lstproveedor.get(i).getCCIDolares();
                nuevafila[14] = lstproveedor.get(i).getCuentaDetracciones();
                modTbdatos.addRow(nuevafila);
            }
        }
        tblstproveedores.setModel(modTbdatos);
        lblnroregistros.setText(tblstproveedores.getRowCount() + " Proveedor(es)");
    }

    private void filtraproveedor(String nom) {
        LProveedor lpro = new LProveedor();
        lstproveedor = lpro.filtrarProveedor(nom);
        modTbdatos.setRowCount(0);
        if (lstproveedor.size() > 0) {
            for (int i = 0; i < lstproveedor.size(); i++) {
                Object nuevafila[] = new Object[15];
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
                nuevafila[6] = lstproveedor.get(i).getContacto();
                nuevafila[7] = lstproveedor.get(i).getCorreoContacto();
                nuevafila[8] = lstproveedor.get(i).getNomBanco();
                nuevafila[9] = lstproveedor.get(i).getTipoCuenta();
                nuevafila[10] = lstproveedor.get(i).getCTASoles();
                nuevafila[11] = lstproveedor.get(i).getCTADolares();
                nuevafila[12] = lstproveedor.get(i).getCCISoles();
                nuevafila[13] = lstproveedor.get(i).getCCIDolares();
                nuevafila[14] = lstproveedor.get(i).getCuentaDetracciones();
                modTbdatos.addRow(nuevafila);
            }
        }
        tblstproveedores.setModel(modTbdatos);
        lblnroregistros.setText(tblstproveedores.getRowCount() + " Proveedor(es)");
    }

    class PrimeThread implements Runnable {

        PrimeThread() {
        }

        public void run() {
            LProveedor lim = new LProveedor();
            for (int i = 0; i < lsttemporal.size(); i++) {
                if (lim.verifRUCrep(lsttemporal.get(i).getDocProveedor()) == 0) {
                    lim.InsertarProveedorTemp(lsttemporal.get(i));
                } else {
                    lim.ActualizarProveedorTemp(lsttemporal.get(i));
                }
            }
            jdc.setVisible(false);
            jdc.tiempo.stop();
            JOptionPane.showMessageDialog(null, "Se grabó correctamente los Proveedores.");
            limpiar();
            limpiar2();
        }
    }

    private void limpiar2() {
        lsttemporal.removeAll(lsttemporal);
        lstproveedor.removeAll(lstproveedor);
    }

    private void limpiar() {
        txtdocpro.setText("");
        txtrazonsocial.setText("");
        txtdireccion.setText("");
        txttelefono.setText("");
        txtcontacto.setText("");
        txtcorreocontacto.setText("");
        chkactivo.setSelected(true);
        cbobanco.setSelectedIndex(0);
        txtctasoles.setText("");
        txtctadolares.setText("");
        txtccisoles.setText("");
        txtccidolares.setText("");
        accion = 0;
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
            if (tblstproveedores.getValueAt(fila, 4) != null) {
                txtdireccion.setText(tblstproveedores.getValueAt(fila, 4).toString());
            } else {
                txtdireccion.setText("");
            }

            if (tblstproveedores.getValueAt(fila, 5) != null) {
                txttelefono.setText(tblstproveedores.getValueAt(fila, 5).toString());
            } else {
                txttelefono.setText("");
            }
            if (tblstproveedores.getValueAt(fila, 6) != null) {
                txtcontacto.setText(tblstproveedores.getValueAt(fila, 6).toString());
            } else {
                txtcontacto.setText("");
            }
            if (tblstproveedores.getValueAt(fila, 7) != null) {
                txtcorreocontacto.setText(tblstproveedores.getValueAt(fila, 7).toString());
            } else {
                txtcorreocontacto.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 8).toString().equalsIgnoreCase("-")) {
                cbobanco.setSelectedItem(tblstproveedores.getValueAt(fila, 8).toString().trim());
            } else {
                cbobanco.setSelectedIndex(0);
            }
            if (Integer.parseInt(tblstproveedores.getValueAt(fila, 9).toString()) != 0) {
                if (Integer.parseInt(tblstproveedores.getValueAt(fila, 9).toString()) == 1) {
                    rbtCC.setSelected(true);
                } else if (Integer.parseInt(tblstproveedores.getValueAt(fila, 9).toString()) == 2) {
                    rbtCah.setSelected(true);
                }
            } else {
                rbtGrupo.setSelected(rbtCC.getModel(), false);
                rbtGrupo.setSelected(rbtCah.getModel(), false);
            }
            if (!tblstproveedores.getValueAt(fila, 10).toString().equalsIgnoreCase("-")) {
                txtctasoles.setText(tblstproveedores.getValueAt(fila, 10).toString().trim());
            } else {
                txtctasoles.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 11).toString().equalsIgnoreCase("-")) {
                txtccisoles.setText(tblstproveedores.getValueAt(fila, 11).toString().trim());
            } else {
                txtccisoles.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 12).toString().equalsIgnoreCase("-")) {
                txtctadolares.setText(tblstproveedores.getValueAt(fila, 12).toString().trim());
            } else {
                txtctadolares.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 13).toString().equalsIgnoreCase("-")) {
                txtccidolares.setText(tblstproveedores.getValueAt(fila, 13).toString().trim());
            } else {
                txtccidolares.setText("");
            }
            if (!tblstproveedores.getValueAt(fila, 14).toString().equalsIgnoreCase("-")) {
                txtcuentadetracciones.setText(tblstproveedores.getValueAt(fila, 14).toString().trim());
            } else {
                txtcuentadetracciones.setText("");
            }
//            LCuentaBancaria lcuen = new LCuentaBancaria();
//            ECuentaBancaria obj1 = new ECuentaBancaria();

//            obj1=lstcuenta;
            String vruc = tblstproveedores.getValueAt(fila, 0).toString();
            System.out.println(vruc);

            accion = 1;
            if (estado.equalsIgnoreCase("ACTIVO")) {
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
        if (cbotipodoc.getSelectedItem().equals("DNI")) {
            if (txtdocpro.getText().trim().length() != 8) {
                JOptionPane.showMessageDialog(this, "El número del Documento debe contener 8 caracteres.");
                txtdocpro.requestFocus();
                validar = 1;
                return validar;
            } else {
                validar = 0;
            }
        } else if (txtdocpro.getText().trim().length() != 11) {
            JOptionPane.showMessageDialog(this, "El número del Documento debe contener 11 caracteres.");
            txtdocpro.requestFocus();
            validar = 1;
            return validar;
        } else {
            validar = 0;
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

    private int validarVacio() {
        int valor = 0;
        if (txtctasoles.getText().length() == 0 && txtctadolares.getText().length() == 0 && txtccisoles.getText().length() == 0 && txtccidolares.getText().length() == 0) {
            valor = 1;
        }

        return valor;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtGrupo = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtrazonsocial = new javax.swing.JTextField();
        txtctadolares = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtcontacto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtcorreocontacto = new javax.swing.JTextField();
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
        rbtCah = new javax.swing.JRadioButton();
        rbtCC = new javax.swing.JRadioButton();
        txtccidolares = new javax.swing.JTextField();
        txtccisoles = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstproveedores = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btnImportar = new javax.swing.JButton();
        txtimportacion = new javax.swing.JTextField();
        lblborde1 = new javax.swing.JLabel();
        lblborde3 = new javax.swing.JLabel();
        txtcuentadetracciones = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lblborde4 = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INGRESAR Y/O MODIFICAR PROVEEDOR");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 670, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Doc Proveedor");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setText("Razón Social");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setText("Dirección");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setText("Teléfono");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 100, -1, -1));

        txttelefono.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
        });
        jPanel1.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 100, 90, -1));

        txtdireccion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdireccionKeyReleased(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 80, 287, -1));

        txtrazonsocial.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtrazonsocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrazonsocialKeyReleased(evt);
            }
        });
        jPanel1.add(txtrazonsocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 60, 287, -1));

        txtctadolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtctadolares.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtctadolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtctadolaresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtctadolaresKeyTyped(evt);
            }
        });
        jPanel1.add(txtctadolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 160, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel10.setText("Contacto");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 120, -1, -1));

        txtcontacto.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtcontacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontactoKeyReleased(evt);
            }
        });
        jPanel1.add(txtcontacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 120, 120, 20));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel8.setText("Importacion");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, -1, -1));

        txtcorreocontacto.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtcorreocontacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcorreocontactoKeyReleased(evt);
            }
        });
        jPanel1.add(txtcorreocontacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 140, 150, -1));

        btnnuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnnuevo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 223, 70, 30));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 223, 70, 30));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 223, 70, 30));

        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 2, 140, -1));

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblnroregistros.setText("0 Proveedores");
        jPanel1.add(lblnroregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 110, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setText(" Cuenta Dolares");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, -1, -1));

        cbobanco.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbobanco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        cbobanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbobancoKeyReleased(evt);
            }
        });
        jPanel1.add(cbobanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 120, -1));

        chkactivo.setBackground(new java.awt.Color(255, 255, 255));
        chkactivo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        chkactivo.setText("Activo");
        jPanel1.add(chkactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, 19));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel9.setText("Tipo de Doc");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 20, -1, -1));

        cbotipodoc.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbotipodoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        jPanel1.add(cbotipodoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 20, 90, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel11.setText("Banco");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        txtdocpro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtdocpro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtdocpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdocproActionPerformed(evt);
            }
        });
        txtdocpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdocproKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdocproKeyTyped(evt);
            }
        });
        jPanel1.add(txtdocpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 40, 90, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText(" Cuenta Soles");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        txtctasoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtctasoles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtctasoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtctasolesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtctasolesKeyTyped(evt);
            }
        });
        jPanel1.add(txtctasoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 160, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel13.setText(" CCI Soles");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel14.setText(" CCI Dolares");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        rbtCah.setBackground(new java.awt.Color(255, 255, 255));
        rbtGrupo.add(rbtCah);
        rbtCah.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbtCah.setText("CTA. AHORROS");
        rbtCah.setBorder(null);
        jPanel1.add(rbtCah, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, -1, -1));

        rbtCC.setBackground(new java.awt.Color(255, 255, 255));
        rbtGrupo.add(rbtCC);
        rbtCC.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbtCC.setText("CTA. CTE.");
        rbtCC.setBorder(null);
        jPanel1.add(rbtCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, -1, -1));

        txtccidolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtccidolares.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtccidolares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtccidolaresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtccidolaresKeyTyped(evt);
            }
        });
        jPanel1.add(txtccidolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 160, -1));

        txtccisoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtccisoles.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtccisoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtccisolesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtccisolesKeyTyped(evt);
            }
        });
        jPanel1.add(txtccisoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 160, -1));

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel15.setText("Correo Cont.");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 140, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyTyped(evt);
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 710, 230));

        btnImportar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnImportar.setText("Importar");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });
        jPanel1.add(btnImportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 191, -1, -1));

        txtimportacion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel1.add(txtimportacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 191, 150, -1));

        lblborde1.setBackground(new java.awt.Color(255, 255, 255));
        lblborde1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde1.setToolTipText("");
        lblborde1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Datos Bancarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 300, 180));

        lblborde3.setBackground(new java.awt.Color(255, 255, 255));
        lblborde3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde3.setToolTipText("");
        lblborde3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 180));

        txtcuentadetracciones.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jPanel1.add(txtcuentadetracciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 192, 260, 20));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Tipo de Cuenta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 280, 130));

        lblborde4.setBackground(new java.awt.Color(255, 255, 255));
        lblborde4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblborde4.setToolTipText("");
        lblborde4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Import", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(lblborde4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 400, 40));

        btnbuscar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnbuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 223, 70, 30));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Cuenta de Detracciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 300, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed

        accion = 0;
        txtdocpro.setEnabled(true);
        rbtGrupo.setSelected(rbtCC.getModel(), false);
        rbtGrupo.setSelected(rbtCah.getModel(), false);
        cbotipodoc.setSelectedIndex(1);
        limpiar();


    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here: 06/12/2014
            if(MostrarMensajeValidacionNumeroCuenta()){
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
                prov.setTipoDocProveedor("RUC");
                prov.setRazonSocial(txtrazonsocial.getText());
                prov.setEstado(estado);
                prov.setDireccion(txtdireccion.getText());
                prov.setTelefono(txttelefono.getText());
                prov.setContacto(txtcontacto.getText());
                prov.setCorreoContacto(txtcorreocontacto.getText());
//        if(prov.getCodBanco() != 1 || prov.getCodBanco() != 2){
//           prov.setCodBanco(0);
//        }else{
//        prov.setCodBanco(lstban.get(cbobanco.getSelectedIndex()-1).getCodBanco());
//        }
//        prov.setCTASoles(txtctasoles.getText());
//        prov.setCTADolares(txtctadolares.getText());
//        prov.setCCISoles(txtccisoles.getText());
//        prov.setCCIDolares(txtccidolares.getText());
                switch (accion) {
                    case 0: //Agregar
                        System.out.println("ENTRO A AGREGAR");
                        if (verificarruc(txtdocpro.getText()) == 1) {
                            JOptionPane.showMessageDialog(this, "El RUC que intenta registrar ya existe, ingrese uno nuevo.");
                            txtdocpro.setText("");
                            txtdocpro.requestFocus();
                        } else {
                            System.out.println("AKI");
                            if (validarVacio() != 0) {
                                JOptionPane.showMessageDialog(this, "Ingresar una de las cuentas para continuar.");
                                txtctasoles.requestFocus();
                            } else {
                                int tipocuentaVal = 0;
                                if (rbtCC.isSelected() == true) {
                                    tipocuentaVal = 1;
                                } else if (rbtCah.isSelected() == true) {
                                    tipocuentaVal = 2;
                                }
                                if (validarCuenta((String) cbobanco.getSelectedItem(), txtctadolares.getText(), txtctasoles.getText(), tipocuentaVal).trim().length() > 1) {
                                    JOptionPane.showMessageDialog(this, validarCuenta((String) cbobanco.getSelectedItem(), txtctadolares.getText(), txtctasoles.getText(), tipocuentaVal));
                                    selectFocus(focoTexto);
                                } else if (validarCuentaCCI((String) cbobanco.getSelectedItem(), txtccidolares.getText(), txtccisoles.getText()).trim().length() > 1) {
                                    JOptionPane.showMessageDialog(this, validarCuentaCCI((String) cbobanco.getSelectedItem(), txtccidolares.getText(), txtccisoles.getText()));
                                    selectFocus(focoTexto);
                                } else if (JOptionPane.showConfirmDialog(null, "Se va a GRABAR el proveedor " + prov.getRazonSocial().toUpperCase() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                                    if (verificarruc(txtdocpro.getText()) < 2) {
                                        prov.setUsuCrea(SesionUsuario.misesion.getUsuario());
                                        prov.setCuentaDetracciones(txtcuentadetracciones.getText().trim());
                                        if (lprov.InsertarProveedor(prov)) {
//                                     for (int i = 0; i < ; i++) {  
                                            if (txtctasoles.getText().trim().length() != 0 || txtccisoles.getText().trim().length() != 0) {
                                                cuen.setDocProveedor(txtdocpro.getText());
                                                cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                                cuen.setMoneda("SOLES");
                                                cuen.setCuenta(txtctasoles.getText());
                                                cuen.setCuentaci(txtccisoles.getText());
                                                Integer tipocuenta = 0;
                                                if (rbtCC.isSelected() == true) {
                                                    tipocuenta = 1;
                                                } else if (rbtCah.isSelected() == true) {
                                                    tipocuenta = 2;
                                                }
                                                cuen.setTipoCuenta(tipocuenta);
                                                lcuen.InsertarCuentaBancaria(cuen);
                                            }
                                            if (txtctadolares.getText().trim().length() != 0 || txtccidolares.getText().trim().length() != 0) {
                                                cuen.setDocProveedor(txtdocpro.getText());
                                                cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                                cuen.setMoneda("DOLARES");
                                                cuen.setCuenta(txtctadolares.getText());
                                                cuen.setCuentaci(txtccidolares.getText());
                                                Integer tipocuenta = 0;
                                                if (rbtCC.isSelected() == true) {
                                                    tipocuenta = 1;
                                                } else if (rbtCah.isSelected() == true) {
                                                    tipocuenta = 2;
                                                }
                                                cuen.setTipoCuenta(tipocuenta);
                                                lcuen.InsertarCuentaBancaria(cuen);
                                            }
//                                     }
                                            //si el campo soles tiene data inserto y mando en duro el campo soles
                                            //ski el campo dolares tiene data inserto dolares 
                                            //listaproveedor();
                                            System.out.println("GRABANDO");
                                            limpiar();
                                            JOptionPane.showMessageDialog(this, "Se grabó correctamente el proveedor " + prov.getRazonSocial().toUpperCase() + ".");
                                            lblnroregistros.setText(tblstproveedores.getRowCount() + " Proveedores");
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                                        }
                                    }

                                }
                            }
                        }
                        break;
                    case 1: //Actualizar
                        if (validarVacio() != 0) {
                            JOptionPane.showMessageDialog(this, "Ingresar una de las cuentas para continuar.");
                            txtctasoles.requestFocus();
                        } else {
                            int tipocuentaVal = 0;
                            if (rbtCC.isSelected() == true) {
                                tipocuentaVal = 1;
                            } else if (rbtCah.isSelected() == true) {
                                tipocuentaVal = 2;
                            }
                            if (validarCuenta((String) cbobanco.getSelectedItem(), txtctadolares.getText(), txtctasoles.getText(), tipocuentaVal).trim().length() > 1) {
                                JOptionPane.showMessageDialog(this, validarCuenta((String) cbobanco.getSelectedItem(), txtctadolares.getText(), txtctasoles.getText(), tipocuentaVal));
                                selectFocus(focoTexto);
                            } else if (validarCuentaCCI((String) cbobanco.getSelectedItem(), txtccidolares.getText(), txtccisoles.getText()).trim().length() > 1) {
                                JOptionPane.showMessageDialog(this, validarCuentaCCI((String) cbobanco.getSelectedItem(), txtccidolares.getText(), txtccisoles.getText()));
                                selectFocus(focoTexto);
                            } else if (JOptionPane.showConfirmDialog(null, "Se va a MODIFICAR el proveedor " + prov.getRazonSocial().toUpperCase() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                                prov.setUsumodi(SesionUsuario.misesion.getUsuario());
                                prov.setCuentaDetracciones(txtcuentadetracciones.getText().trim());
                                if (lprov.ActualizarProveedor(prov)) {
                                    lcuen.eliminarCuenta(txtdocpro.getText());
//                            for (int i = 0; i < lstcuenta; i++) {  
                                    if (txtctasoles.getText().trim().length() != 0 || txtccisoles.getText().trim().length() != 0) {
                                        cuen.setDocProveedor(txtdocpro.getText());
                                        cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                        cuen.setMoneda("SOLES");
                                        cuen.setCuenta(txtctasoles.getText());
                                        cuen.setCuentaci(txtccisoles.getText());
                                        Integer tipocuenta = 0;
                                        if (rbtCC.isSelected() == true) {
                                            tipocuenta = 1;
                                        } else if (rbtCah.isSelected() == true) {
                                            tipocuenta = 2;
                                        }
                                        cuen.setTipoCuenta(tipocuenta);
                                        lcuen.InsertarCuentaBancaria(cuen);
                                    }
                                    if (txtctadolares.getText().trim().length() != 0 || txtccidolares.getText().trim().length() != 0) {
                                        cuen.setDocProveedor(txtdocpro.getText());
                                        cuen.setCodbanco(lstban.get(cbobanco.getSelectedIndex() - 1).getCodBanco());
                                        cuen.setMoneda("DOLARES");
                                        cuen.setCuenta(txtctadolares.getText());
                                        cuen.setCuentaci(txtccidolares.getText());
                                        Integer tipocuenta = 0;
                                        if (rbtCC.isSelected() == true) {
                                            tipocuenta = 1;
                                        } else if (rbtCah.isSelected() == true) {
                                            tipocuenta = 2;
                                        }
                                        cuen.setTipoCuenta(tipocuenta);
                                        lcuen.InsertarCuentaBancaria(cuen);
                                    }
                                    listaproveedor();
                                    txtrazonsocial.setEnabled(true);
                                    accion = 0;
                                    limpiar();
                                    JOptionPane.showMessageDialog(this, "Se actualizó correctamente el proveedor " + prov.getRazonSocial().toUpperCase() + ".");
                                    pintarfila();
                                    lblnroregistros.setText(tblstproveedores.getRowCount() + " Proveedores");
                                } else {
                                    JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                                }
                            }
                        }

                        break;
                }

            }
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed
    private void selectFocus(int valor) {
        switch (valor) {
            case 1:
                txtctasoles.requestFocus();
                break;
            case 2:
                txtctadolares.requestFocus();
                break;
            case 3:
                txtccisoles.requestFocus();
                break;
            case 4:
                txtccidolares.requestFocus();
                break;
        }
    }

    private String validarCuenta(String banco, String numDolar, String numSoles, int tipocuenta){
        String rpta = "";
        focoTexto = 0;
        switch (banco) {
            case "BCP":

                if (numDolar.trim().length() != 0) {
                    if (numDolar.trim().length() != 13 && tipocuenta == 1) {
                        rpta = "El numero de cuenta en Dolar para " + banco + " tiene que tener 13 digitos";
                        focoTexto = 2;
                    }
                    if (numDolar.trim().length() != 14 && tipocuenta == 2) {
                        rpta = "El numero de cuenta en Dolar para " + banco + " tiene que tener 14 digitos";
                        focoTexto = 2;
                    }
                }
                if (numSoles.trim().length() != 0) {
                    if (numSoles.trim().length() != 13 && tipocuenta == 1) {
                        rpta = "El numero de cuenta en Soles para " + banco + " tiene que tener 13 digitos";
                        focoTexto = 1;
                    }

                    if (numSoles.trim().length() != 14 && tipocuenta == 2) {
                        rpta = "El numero de cuenta en Soles para " + banco + " tiene que tener 14 digitos";
                        focoTexto = 1;
                    }
                }
                break;
            case "Interbank":
                if (numDolar.trim().length() != 0) {
                    if (numDolar.trim().length() != 13) {
                        rpta = "El numero de cuenta en Dolar para " + banco + " tiene que tener 13 digitos";
                        focoTexto = 2;
                    }
                }
                if (numSoles.trim().length() != 0) {
                    if (numSoles.trim().length() != 13) {
                        rpta = "El numero de cuenta en Soles para " + banco + " tiene que tener 13 digitos";
                        focoTexto = 1;
                    }
                }
                break;
        }
        return rpta;
    }

    private boolean MostrarMensajeValidacionNumeroCuenta(){
       boolean rpta = false;
      if(txtctasoles.getText().length()> 0){
          try{
              validaNumero(txtctasoles.getText());
              rpta= true;
          }catch(NumberFormatException num){
              JOptionPane.showMessageDialog(this, "El número de cuenta en Soles es incorrecto, Verifique si tiene espacios en blanco ó algún carácter especial.");
              txtctasoles.requestFocus();
              rpta = false;
          }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "El número de cuenta en Soles es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtctasoles.requestFocus();
              rpta= false;
          }
                  
      }
      if(!rpta){
        return false;
      }
      if(txtctadolares.getText().length()> 0){
          try{
              validaNumero(txtctadolares.getText());
              rpta= true;
          }catch(NumberFormatException num){
              JOptionPane.showMessageDialog(this, "El número de cuenta en Dólares es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtctadolares.requestFocus();
              rpta= false;
          }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "El número de cuenta en Dólares es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtctadolares.requestFocus();
              rpta= false;
          }
                  
      }
      if(!rpta){
        return false;
      }
      if(txtccisoles.getText().length()> 0){
          try{
              validaNumero(txtccisoles.getText());
              rpta= true;
          }catch(NumberFormatException num){
              JOptionPane.showMessageDialog(this, "El número de cuenta CCI en soles es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtccisoles.requestFocus();
              rpta= false;
          }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "El número de cuenta CCI en soles es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtccisoles.requestFocus();
              rpta= false;
          }
                  
      }
      if(!rpta){
        return false;
      }
      if(txtccidolares.getText().length()> 0){
          try{
              validaNumero(txtccidolares.getText());
              rpta= true;
          }catch(NumberFormatException num){
              JOptionPane.showMessageDialog(this, "El número de cuenta CCI en Dólares es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtccidolares.requestFocus();
              rpta= false;
          }catch(Exception ex){
              JOptionPane.showMessageDialog(this, "El número de cuenta CCI en Dólares es incorrecto, Verifique si tiene espacios en blanco o algún carácter especial.");
              txtccidolares.requestFocus();
              rpta= false;
          }
                  
      }
      return rpta;
    }
    private void validaNumero(String numero) throws NumberFormatException, Exception {

        for (int i = 0; i < numero.length(); i++) {
            char valor = numero.charAt(i);
            Integer.parseInt(Character.toString(valor));
            System.out.println(valor);
        }
    }

    private String validarCuentaCCI(String banco, String numDolar, String numSoles) {
        String rpta = "";
        focoTexto = 0;
        switch (banco) {
            case "BCP":
                if (numDolar.trim().length() != 0) {
                    if (numDolar.trim().length() != 20) {
                        rpta = "El numero de cuenta interbancaria en Dolar para " + banco + " tiene que tener 20 digitos";
                        focoTexto = 4;
                    }
                }
                if (numSoles.trim().length() != 0) {
                    if (numSoles.trim().length() != 20) {
                        rpta = "El numero de cuenta interbancaria en Soles para " + banco + " tiene que tener 20 digitos";
                        focoTexto = 3;
                    }
                }
                break;
            case "Interbank":
                if (numDolar.trim().length() != 0) {
                    if (numDolar.trim().length() != 20) {
                        rpta = "El numero de cuenta interbancaria en Dolar para " + banco + " tiene que tener 20 digitos";
                        focoTexto = 4;
                    }
                }
                if (numSoles.trim().length() != 0) {
                    if (numSoles.trim().length() != 20) {
                        rpta = "El numero de cuenta interbancaria en Soles para " + banco + " tiene que tener 20 digitos";
                        focoTexto = 3;
                    }
                }
                break;
        }
        return rpta;
    }
    private void txtdocproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocproKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
        if (txtdocpro.getText().length() >= 11) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdocproKeyTyped

    private void tblstproveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstproveedoresMouseClicked
        int fila = tblstproveedores.getSelectedRow();
        if (fila >= 0) {
            seleccionarfila(fila);

//            ECuentaBancaria cu = new ECuentaBancaria();
//            LCuentaBancaria cuen = new  LCuentaBancaria();
//         
//            lstcuenta = cuen.ListarCuentaXRuc();
        }
    }//GEN-LAST:event_tblstproveedoresMouseClicked

    private void grabandoyactualizandoprov() {
//         LProveedor lp=new LProveedor();
        if (lsttemporal.size() > 0) {
            jdc = new JDCargaRegistros(null, true);
            SesionUsuario.nroaregistrar = lsttemporal.size();
            PrimeThread prt = new PrimeThread();
            new Thread(prt).start();
            jdc.setVisible(true);
        }
    }


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

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                txtimportacion.setText(ruta);
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                lispexcel.removeAll(lispexcel);
                Sheet sheet = workbook.getSheet(0);
                for (int fila = 2; fila < sheet.getRows(); fila++) {
                    //recorremos las filas
                    if (sheet.getCell(1, fila).getContents().toString().trim().length() > 0) {
                        EProveedor pro = new EProveedor();
                        pro.setDocProveedor(sheet.getCell(1, fila).getContents());
                        pro.setRazonSocial(sheet.getCell(2, fila).getContents());
                        pro.setDireccion(sheet.getCell(3, fila).getContents() + " - " + sheet.getCell(4, fila).getContents() + " - " + sheet.getCell(5, fila).getContents());
                        lsttemporal.add(pro);
                    }
                }
                if (lsttemporal.size() > 0) {
                    if (JOptionPane.showConfirmDialog(null, "Se grabarán nuevos proveedores y se reemplazarán los existentes. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        grabandoyactualizandoprov();
                        listaproveedor();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Importar.");
                }
            }

        } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnImportarActionPerformed

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
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtrazonsocial.requestFocus();
        }
    }//GEN-LAST:event_txtdocproKeyReleased

    private void txtrazonsocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonsocialKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtdireccion.requestFocus();
        }
    }//GEN-LAST:event_txtrazonsocialKeyReleased

    private void txtdireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txttelefono.requestFocus();
        }
    }//GEN-LAST:event_txtdireccionKeyReleased

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtcontacto.requestFocus();
        }
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void txtcontactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtcorreocontacto.requestFocus();
        }
    }//GEN-LAST:event_txtcontactoKeyReleased

    private void txtcorreocontactoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreocontactoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cbobanco.requestFocus();
        }
    }//GEN-LAST:event_txtcorreocontactoKeyReleased

    private void cbobancoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbobancoKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtctasoles.requestFocus();
        }
    }//GEN-LAST:event_cbobancoKeyReleased

    private void txtctasolesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctasolesKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtctadolares.requestFocus();
        }
    }//GEN-LAST:event_txtctasolesKeyReleased

    private void txtctadolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctadolaresKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtccisoles.requestFocus();
        }
    }//GEN-LAST:event_txtctadolaresKeyReleased

    private void txtccisolesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtccisolesKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtccidolares.requestFocus();
        }
    }//GEN-LAST:event_txtccisolesKeyReleased

    private void txtccidolaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtccidolaresKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtcuentadetracciones.requestFocus();
        }
    }//GEN-LAST:event_txtccidolaresKeyReleased

    private void txtdocproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdocproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdocproActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        String nomprov = JOptionPane.showInputDialog("Razon Social Proveedor.");
        if (nomprov.trim().length() == 0) {
            listaproveedor();
        } else {
            filtraproveedor(nomprov);
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtctasolesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctasolesKeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            //JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtctasolesKeyTyped

    private void txtctadolaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctadolaresKeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            //JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtctadolaresKeyTyped

    private void txtccisolesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtccisolesKeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            //JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtccisolesKeyTyped

    private void txtccidolaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtccidolaresKeyTyped
        // TODO add your handling code here:
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            //JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtccidolaresKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbobanco;
    private javax.swing.JComboBox cbotipodoc;
    private javax.swing.JCheckBox chkactivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblborde1;
    private javax.swing.JLabel lblborde3;
    private javax.swing.JLabel lblborde4;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JRadioButton rbtCC;
    private javax.swing.JRadioButton rbtCah;
    private javax.swing.ButtonGroup rbtGrupo;
    private javax.swing.JTable tblstproveedores;
    private javax.swing.JTextField txtccidolares;
    private javax.swing.JTextField txtccisoles;
    private javax.swing.JTextField txtcontacto;
    private javax.swing.JTextField txtcorreocontacto;
    private javax.swing.JTextField txtctadolares;
    private javax.swing.JTextField txtctasoles;
    private javax.swing.JTextField txtcuentadetracciones;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdocpro;
    private javax.swing.JTextField txtimportacion;
    private javax.swing.JTextField txtrazonsocial;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}

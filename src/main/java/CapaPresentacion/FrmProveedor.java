/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentacion;

import CapaDatos.UbigeoDAL;
import CapaNegocio.ProveedorBL;
import CapaEntidad.Proveedor;
import CapaDatos.ConexionBD;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Lelis Carlos
 */
public class FrmProveedor extends javax.swing.JFrame {

    /**
     * Creates new form Frm
     */
       private boolean modoEdicion = false; // Controla si estamos editando o agregando
    
    public FrmProveedor() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        habilitarCampos(false); // Iniciar con campos deshabilitados
        habilitarCombos(true);
        cargarCombosUbicacion();
        agregarEventosCombos();
        txtId.setEnabled(false);
    }
    
    private void agregarEventosCombos() {
        cboDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDepartamentoItemStateChanged(evt);
            }
        });
    
        cboProvincia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboProvinciaItemStateChanged(evt);
            }
        });
    }

    private void cboDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String departamentoSeleccionado = cboDepartamento.getSelectedItem().toString();
        
            if (!departamentoSeleccionado.equals("Seleccionar")) {
                // Cargar provincias del departamento seleccionado
                cboProvincia.removeAllItems();
                cboProvincia.addItem("Seleccionar");
                for (String provincia : ubigeoDAL.obtenerProvincias(departamentoSeleccionado)) {
                    cboProvincia.addItem(provincia);
                }
            
                // Limpiar distritos
                cboDistrito.removeAllItems();
                cboDistrito.addItem("Seleccionar");
            } else {
                // Limpiar ambos combos si se selecciona "Seleccionar"
                cboProvincia.removeAllItems();
                cboProvincia.addItem("Seleccionar");
                cboDistrito.removeAllItems();
                cboDistrito.addItem("Seleccionar");
            }
        }
    }

    private void cboProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String departamento = cboDepartamento.getSelectedItem().toString();
            String provinciaSeleccionada = cboProvincia.getSelectedItem().toString();
        
            if (!departamento.equals("Seleccionar") && !provinciaSeleccionada.equals("Seleccionar")) {
                // Cargar distritos de la provincia seleccionada
                cboDistrito.removeAllItems();
                cboDistrito.addItem("Seleccionar");
                for (String distrito : ubigeoDAL.obtenerDistritos(departamento, provinciaSeleccionada)) {
                    cboDistrito.addItem(distrito);
                }
            } else {
                // Limpiar distritos
                cboDistrito.removeAllItems();
                cboDistrito.addItem("Seleccionar");
            }
        }
    }
    
    ConexionBD oConexion = new ConexionBD();
    ProveedorBL proveedorBL = new ProveedorBL();

    // ============ MÉTODOS PARA CONSULTAS ============
    
    private UbigeoDAL ubigeoDAL = new UbigeoDAL();

    
    void cargarCombosUbicacion() {
    try {
        System.out.println("Iniciando carga de combos ubigeo...");
    
        // Cargar departamentos desde ubigeo
        cboDepartamento.removeAllItems();
        cboDepartamento.addItem("Seleccionar");
        
        java.util.List<String> departamentos = ubigeoDAL.obtenerDepartamentos();
        System.out.println("Número de departamentos obtenidos: " + departamentos.size());
        
        for (String departamento : departamentos) {
            cboDepartamento.addItem(departamento);
        }
        
        // Limpiar provincias y distritos
        cboProvincia.removeAllItems();
        cboProvincia.addItem("Seleccionar");
        
        cboDistrito.removeAllItems();
        cboDistrito.addItem("Seleccionar");
        
        System.out.println("Combos cargados exitosamente");
        
    } catch (Exception e) {
        System.out.println("Error en cargarCombosUbicacion: " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error al cargar ubicaciones: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    void consultarTodos() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            
            Connection conn = oConexion.abrirConexion();
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT idProveedor, ruc, razonSocial, direccion, telefono, celular, correo, departamento, provincia, distrito FROM Proveedor ORDER BY razonSocial");
            
            // Definir columnas de la tabla
            model.addColumn("ID");
            model.addColumn("RUC");
            model.addColumn("NOMBRE/RAZÓN SOCIAL");
            model.addColumn("DIRECCIÓN");
            model.addColumn("TELÉFONO");
            model.addColumn("CELULAR");
            model.addColumn("CORREO");
            model.addColumn("DEPARTAMENTO");
            model.addColumn("PROVINCIA");
            model.addColumn("DISTRITO");
            
            while (rs.next()) {
                Object data[] = {
                    rs.getInt("idProveedor"),
                    rs.getString("ruc"),
                    rs.getString("razonSocial"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("celular"),
                    rs.getString("correo"),
                    rs.getString("departamento"),
                    rs.getString("provincia"),
                    rs.getString("distrito")
                };
                model.addRow(data);
            }
            
            tblProveedores.setModel(model);
            // Ocultar la columna ID (columna 0)
            tblProveedores.getColumnModel().getColumn(0).setMinWidth(0);
            tblProveedores.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProveedores.getColumnModel().getColumn(0).setWidth(0);
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString(), "Error en consulta", JOptionPane.ERROR_MESSAGE);
        }
        
        lblResultado.setText("Total de Resultados: " + tblProveedores.getRowCount());
    }
    
    void consultarPorUbicacion() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            
            Connection conn = oConexion.abrirConexion();
            
            String departamento = cboDepartamento.getSelectedItem().toString();
            String provincia = cboProvincia.getSelectedItem().toString();
            String distrito = cboDistrito.getSelectedItem().toString();
            
            StringBuilder sql = new StringBuilder("SELECT idProveedor, ruc, razonSocial, direccion, telefono, celular, correo, departamento, provincia, distrito FROM Proveedor WHERE 1=1");
            
            if (!departamento.equals("Seleccionar")) {
                sql.append(" AND departamento = '").append(departamento).append("'");
            }
            if (!provincia.equals("Seleccionar")) {
                sql.append(" AND provincia = '").append(provincia).append("'");
            }
            if (!distrito.equals("Seleccionar")) {
                sql.append(" AND distrito = '").append(distrito).append("'");
            }
            
            sql.append(" ORDER BY razonSocial");
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql.toString());
            
            // Definir columnas
            model.addColumn("ID");
            model.addColumn("RUC");
            model.addColumn("NOMBRE/RAZÓN SOCIAL");
            model.addColumn("DIRECCIÓN");
            model.addColumn("TELÉFONO");
            model.addColumn("CELULAR");
            model.addColumn("CORREO");
            model.addColumn("DEPARTAMENTO");
            model.addColumn("PROVINCIA");
            model.addColumn("DISTRITO");
            
            while (rs.next()) {
                Object data[] = {
                    rs.getInt("idProveedor"),
                    rs.getString("ruc"),
                    rs.getString("razonSocial"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("celular"),
                    rs.getString("correo"),
                    rs.getString("departamento"),
                    rs.getString("provincia"),
                    rs.getString("distrito")
                };
                model.addRow(data);
            }
            
            tblProveedores.setModel(model);
            // Ocultar la columna ID
            tblProveedores.getColumnModel().getColumn(0).setMinWidth(0);
            tblProveedores.getColumnModel().getColumn(0).setMaxWidth(0);
            tblProveedores.getColumnModel().getColumn(0).setWidth(0);
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en consulta por ubicación: " + e.toString());
        }
        
        lblResultado.setText("Total de Resultados: " + tblProveedores.getRowCount());
    }

    // ============ MÉTODOS PARA CRUD ============
    
    private void habilitarCampos(boolean habilitar) {
        txtRuc.setEnabled(habilitar);
        txtNombre.setEnabled(habilitar);
        txtDireccion.setEnabled(habilitar);
        txtTelefono.setEnabled(habilitar);
        txtCelular.setEnabled(habilitar);
        txtCorreo.setEnabled(habilitar);
        
    }
    
    private void habilitarCombos(boolean habilitar){
        cboDepartamento.setEnabled(habilitar);
        cboProvincia.setEnabled(habilitar);
        cboDistrito.setEnabled(habilitar);
    }
    
    private void limpiarCampos() {
        txtId.setText("");
        txtRuc.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtCorreo.setText("");
        cboDepartamento.setSelectedIndex(0);
        cboProvincia.setSelectedIndex(0);
        cboDistrito.setSelectedIndex(0);
        habilitarCampos(false);
        modoEdicion = false;
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
    }
    
    private boolean validarCampos() {
        if (txtRuc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El RUC es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            txtRuc.requestFocus();
            return false;
        }
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La Razón Social es obligatoria", "Validación", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }
        if (cboDepartamento.getSelectedIndex() == 0 || 
            cboProvincia.getSelectedIndex() == 0 || 
            cboDistrito.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar Departamento, Provincia y Distrito", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void cargarDatosEnCampos(Proveedor proveedor) {
        txtId.setText(String.valueOf(proveedor.getIdProveedor()));
        txtRuc.setText(proveedor.getRuc());
        txtNombre.setText(proveedor.getRazonSocial());
        txtDireccion.setText(proveedor.getDireccion());
        txtTelefono.setText(proveedor.getTelefono());
        txtCelular.setText(proveedor.getCelular());
        txtCorreo.setText(proveedor.getCorreo());
        
        // Seleccionar los combos según los datos del proveedor
        seleccionarCombo(cboDepartamento, proveedor.getDepartamento());
        seleccionarCombo(cboProvincia, proveedor.getProvincia());
        seleccionarCombo(cboDistrito, proveedor.getDistrito());
    }
    
    private void seleccionarCombo(javax.swing.JComboBox<String> combo, String valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(valor)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
        combo.setSelectedIndex(0);
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
        tblProveedores = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtRuc = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cboDistrito = new javax.swing.JComboBox<>();
        cboProvincia = new javax.swing.JComboBox<>();
        cboDepartamento = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnListar = new javax.swing.JButton();
        btnTodos = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULARIO PROVEEDORES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProveedores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 1070, 770));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("REGISTRO Y CONSULTA DE PROVEEDORES");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 34));
        getContentPane().add(lblResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 920, 180, 20));

        jLabel10.setText("Total de Resultado");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 920, 140, 23));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "DATOS PROVEEDOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("RUC");

        jLabel2.setText("NOMBRE/R. SOCIAL");

        jLabel3.setText("DIRECCION");

        jLabel4.setText("TELEFONO");

        jLabel5.setText("CELULAR");

        jLabel6.setText("CORREO");

        jLabel7.setText("ID PROVEEDOR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(45, 45, 45)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(102, 102, 102)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(64, 64, 64)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(68, 68, 68)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(76, 76, 76)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(79, 79, 79)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 300, 360));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPERACIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnSalir))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 300, 160));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "UBICACION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cboDistrito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("DISTRITO");

        jLabel12.setText("PROVINCIA");

        jLabel13.setText("DEPARTAMENTO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(cboDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 610, 80));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CONSULTAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnListar.setText("LISTAR");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnTodos.setText("TODOS");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnListar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTodos)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListar)
                    .addComponent(btnBuscar)
                    .addComponent(btnTodos))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 300, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProveedoresMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tblProveedores.getSelectedRow();
        if (filaSeleccionada >= 0) {
            // Obtener los datos de la fila seleccionada
            int idProveedor = (int) tblProveedores.getValueAt(filaSeleccionada, 0);
            String ruc = tblProveedores.getValueAt(filaSeleccionada, 1).toString();
            String razonSocial = tblProveedores.getValueAt(filaSeleccionada, 2).toString();
            String direccion = tblProveedores.getValueAt(filaSeleccionada, 3).toString();
            String telefono = tblProveedores.getValueAt(filaSeleccionada, 4).toString();
            String celular = tblProveedores.getValueAt(filaSeleccionada, 5).toString();
            String correo = tblProveedores.getValueAt(filaSeleccionada, 6).toString();
            String departamento = tblProveedores.getValueAt(filaSeleccionada, 7).toString();
            String provincia = tblProveedores.getValueAt(filaSeleccionada, 8).toString();
            String distrito = tblProveedores.getValueAt(filaSeleccionada, 9).toString();
            
            // Llenar los campos con los datos del proveedor seleccionado
            txtId.setText(String.valueOf(idProveedor));
            txtRuc.setText(ruc);
            txtNombre.setText(razonSocial);
            txtDireccion.setText(direccion);
            txtTelefono.setText(telefono);
            txtCelular.setText(celular);
            txtCorreo.setText(correo);
            
            seleccionarCombo(cboDepartamento, departamento);
            seleccionarCombo(cboProvincia, provincia);
            seleccionarCombo(cboDistrito, distrito);
            
            habilitarCampos(false);
            modoEdicion = false;
            btnActualizar.setEnabled(false);
        }
    }//GEN-LAST:event_tblProveedoresMouseClicked

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        consultarPorUbicacion();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        // TODO add your handling code here:
        consultarTodos();
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
         String rucABuscar = JOptionPane.showInputDialog(this, "Ingrese el RUC a buscar:", "Buscar Proveedor", JOptionPane.QUESTION_MESSAGE);
    
        if (rucABuscar != null && !rucABuscar.trim().isEmpty()) {
            Proveedor proveedorEncontrado = proveedorBL.buscarProveedorPorRUC(rucABuscar.trim());
            
            if (proveedorEncontrado != null) {
                // Cargar datos en los campos
                txtId.setText(String.valueOf(proveedorEncontrado.getIdProveedor()));
                txtRuc.setText(proveedorEncontrado.getRuc());
                txtNombre.setText(proveedorEncontrado.getRazonSocial());
                txtDireccion.setText(proveedorEncontrado.getDireccion());
                txtTelefono.setText(proveedorEncontrado.getTelefono());
                txtCelular.setText(proveedorEncontrado.getCelular());
                txtCorreo.setText(proveedorEncontrado.getCorreo());
                seleccionarCombo(cboDepartamento, proveedorEncontrado.getDepartamento());
                seleccionarCombo(cboProvincia, proveedorEncontrado.getProvincia());
                seleccionarCombo(cboDistrito, proveedorEncontrado.getDistrito());
                
                // Buscar y seleccionar en la tabla
                DefaultTableModel model = (DefaultTableModel) tblProveedores.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 1).toString().equals(rucABuscar.trim())) {
                        tblProveedores.setRowSelectionInterval(i, i);
                        tblProveedores.scrollRectToVisible(tblProveedores.getCellRect(i, 0, true));
                        break;
                    }
                }
                
                JOptionPane.showMessageDialog(this, "Proveedor encontrado", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un proveedor con RUC: " + rucABuscar, "Búsqueda", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
         if (!validarCampos()) return;
        
        Proveedor proveedor = new Proveedor();
        proveedor.setRuc(txtRuc.getText().trim());
        proveedor.setRazonSocial(txtNombre.getText().trim());
        proveedor.setDireccion(txtDireccion.getText().trim());
        proveedor.setTelefono(txtTelefono.getText().trim());
        proveedor.setCelular(txtCelular.getText().trim());
        proveedor.setCorreo(txtCorreo.getText().trim());
        proveedor.setDepartamento(cboDepartamento.getSelectedItem().toString());
        proveedor.setProvincia(cboProvincia.getSelectedItem().toString());
        proveedor.setDistrito(cboDistrito.getSelectedItem().toString());
        
        int resultado = proveedorBL.agregarProveedor(proveedor);
        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Proveedor agregado exitosamente con ID: " + resultado);
            limpiarCampos();
            consultarTodos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el proveedor. Verifique que el RUC no esté duplicado.");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(
        this, 
        "¿Está seguro que desea salir al menú principal?", 
        "Confirmar Salida", 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
        );
    
        // Si el usuario confirma (presiona Sí)
        if (respuesta == JOptionPane.YES_OPTION) {
            // Cierra el formulario actual
            this.dispose();
        }
    // Si el usuario cancela (presiona No), no hace nada
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un proveedor de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar el proveedor: " + txtNombre.getText() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idProveedor = Integer.parseInt(txtId.getText());
            int resultado = proveedorBL.eliminarProveedor(idProveedor);
            if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Proveedor eliminado exitosamente");
                limpiarCampos();
                consultarTodos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (!validarCampos()) return;
        if (txtId.getText().isEmpty()) return;
        
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(Integer.parseInt(txtId.getText()));
        proveedor.setRuc(txtRuc.getText().trim());
        proveedor.setRazonSocial(txtNombre.getText().trim());
        proveedor.setDireccion(txtDireccion.getText().trim());
        proveedor.setTelefono(txtTelefono.getText().trim());
        proveedor.setCelular(txtCelular.getText().trim());
        proveedor.setCorreo(txtCorreo.getText().trim());
        proveedor.setDepartamento(cboDepartamento.getSelectedItem().toString());
        proveedor.setProvincia(cboProvincia.getSelectedItem().toString());
        proveedor.setDistrito(cboDistrito.getSelectedItem().toString());
        
        int resultado = proveedorBL.actualizarProveedor(proveedor);
        if (resultado == 1) {
            JOptionPane.showMessageDialog(this, "Proveedor actualizado exitosamente");
            limpiarCampos();
            consultarTodos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el proveedor");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un proveedor de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        habilitarCampos(true);
        modoEdicion = true;
        btnAgregar.setEnabled(false);
        btnActualizar.setEnabled(true);
        txtRuc.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        habilitarCampos(true);
        modoEdicion = false;
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        txtRuc.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTodos;
    private javax.swing.JComboBox<String> cboDepartamento;
    private javax.swing.JComboBox<String> cboDistrito;
    private javax.swing.JComboBox<String> cboProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

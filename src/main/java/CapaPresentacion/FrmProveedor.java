/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package CapaPresentacion;

import CapaNegocio.ProveedorBL;
import CapaEntidad.Proveedor;
import CapaDatos.ConexionBD;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        cargarCombosUbicacion();
        //consultarTodos(); // Cargar todos los proveedores al iniciar
        //lblTitulo.setText("REGISTRO Y CONSULTA DE PROVEEDORES");
    }

    ConexionBD oConexion = new ConexionBD();
    ProveedorBL proveedorBL = new ProveedorBL();

    // ============ MÉTODOS PARA CONSULTAS ============
    
    void cargarCombosUbicacion() {
        try {
            // Cargar departamentos únicos de la base de datos
            Statement stDepa = oConexion.abrirConexion().createStatement();
            ResultSet rsDepa = stDepa.executeQuery("SELECT DISTINCT departamento FROM Proveedor WHERE departamento IS NOT NULL ORDER BY departamento");
            cboDepartamento.removeAllItems();
            cboDepartamento.addItem("Seleccionar");
            while (rsDepa.next()) {
                cboDepartamento.addItem(rsDepa.getString("departamento"));
            }
            rsDepa.close();
            stDepa.close();
            
            // Cargar provincias únicas
            Statement stProv = oConexion.abrirConexion().createStatement();
            ResultSet rsProv = stProv.executeQuery("SELECT DISTINCT provincia FROM Proveedor WHERE provincia IS NOT NULL ORDER BY provincia");
            cboProvincia.removeAllItems();
            cboProvincia.addItem("Seleccionar");
            while (rsProv.next()) {
                cboProvincia.addItem(rsProv.getString("provincia"));
            }
            rsProv.close();
            stProv.close();
            
            // Cargar distritos únicos
            Statement stDist = oConexion.abrirConexion().createStatement();
            ResultSet rsDist = stDist.executeQuery("SELECT DISTINCT distrito FROM Proveedor WHERE distrito IS NOT NULL ORDER BY distrito");
            cboDistrito.removeAllItems();
            cboDistrito.addItem("Seleccionar");
            while (rsDist.next()) {
                cboDistrito.addItem(rsDist.getString("distrito"));
            }
            rsDist.close();
            stDist.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar ubicaciones: " + e.toString());
        }
    }
    
    void consultarTodos() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            Statement st = oConexion.abrirConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT id_Proveedor, ruc, razonSocial, direccion, telefono, correo, departamento, provincia, distrito FROM Proveedor ORDER BY razonSocial");
            
            // Definir columnas de la tabla
            model.addColumn("ID");
            model.addColumn("RUC");
            model.addColumn("NOMBRE/RAZÓN SOCIAL");
            model.addColumn("DIRECCIÓN");
            model.addColumn("TELÉFONO");
            model.addColumn("CORREO");
            model.addColumn("DEPARTAMENTO");
            model.addColumn("PROVINCIA");
            model.addColumn("DISTRITO");
            
            while (rs.next()) {
                Object data[] = {
                    rs.getInt("id_Proveedor"),
                    rs.getString("ruc"),
                    rs.getString("razonSocial"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
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
            String departamento = cboDepartamento.getSelectedItem().toString();
            String provincia = cboProvincia.getSelectedItem().toString();
            String distrito = cboDistrito.getSelectedItem().toString();
            
            StringBuilder sql = new StringBuilder("SELECT id_Proveedor, ruc, razonSocial, direccion, telefono, correo, departamento, provincia, distrito FROM Proveedor WHERE 1=1");
            
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
            
            Statement st = oConexion.abrirConexion().createStatement();
            ResultSet rs = st.executeQuery(sql.toString());
            
            // Definir columnas
            model.addColumn("ID");
            model.addColumn("RUC");
            model.addColumn("NOMBRE/RAZÓN SOCIAL");
            model.addColumn("DIRECCIÓN");
            model.addColumn("TELÉFONO");
            model.addColumn("CORREO");
            model.addColumn("DEPARTAMENTO");
            model.addColumn("PROVINCIA");
            model.addColumn("DISTRITO");
            
            while (rs.next()) {
                Object data[] = {
                    rs.getInt("id_Proveedor"),
                    rs.getString("ruc"),
                    rs.getString("razonSocial"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
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
        cboDistrito = new javax.swing.JComboBox<>();
        cboProvincia = new javax.swing.JComboBox<>();
        cboDepartamento = new javax.swing.JComboBox<>();
        btnListar = new javax.swing.JButton();
        btnTodos = new javax.swing.JButton();
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
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        lblResultado = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FORMULARIO PROVEEDORES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        cboDistrito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jLabel1.setText("RUC");

        jLabel2.setText("NOMBRE/R. SOCIAL");

        jLabel3.setText("DIRECCION");

        jLabel4.setText("TELEFONO");

        jLabel5.setText("CELULAR");

        jLabel6.setText("CORREO");

        jLabel7.setText("ID PROVEEDOR");

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("REGISTRO Y CONSULTA DE PROVEEDORES");

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel10.setText("Total de Resultado");

        jLabel11.setText("DISTRITO");

        jLabel12.setText("PROVINCIA");

        jLabel13.setText("DEPARTAMENTO");

        btnBuscar.setText("BUSCAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(txtRuc)
                            .addComponent(txtNombre)
                            .addComponent(txtDireccion)
                            .addComponent(txtTelefono)
                            .addComponent(txtCelular)
                            .addComponent(txtCorreo))))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboDepartamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(btnSalir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(48, 48, 48)
                .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListar)
                    .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizar)
                            .addComponent(btnAgregar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnEliminar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnBuscar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
        habilitarCampos(true);
        modoEdicion = false;
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(false);
        txtRuc.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

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
            consultarTodos(); // Actualizar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el proveedor");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

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
            consultarTodos(); // Actualizar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el proveedor");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

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
                consultarTodos(); // Actualizar la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
                consultarPorUbicacion();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        // TODO add your handling code here:
                consultarTodos();
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
                this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            String correo = tblProveedores.getValueAt(filaSeleccionada, 5).toString();
            String departamento = tblProveedores.getValueAt(filaSeleccionada, 6).toString();
            String provincia = tblProveedores.getValueAt(filaSeleccionada, 7).toString();
            String distrito = tblProveedores.getValueAt(filaSeleccionada, 8).toString();
            
            // Llenar los campos con los datos del proveedor seleccionado
            txtId.setText(String.valueOf(idProveedor));
            txtRuc.setText(ruc);
            txtNombre.setText(razonSocial);
            txtDireccion.setText(direccion);
            txtTelefono.setText(telefono);
            txtCorreo.setText(correo);
            
            seleccionarCombo(cboDepartamento, departamento);
            seleccionarCombo(cboProvincia, provincia);
            seleccionarCombo(cboDistrito, distrito);
            
            habilitarCampos(false);
            modoEdicion = false;
            btnAgregar.setEnabled(true);
            btnActualizar.setEnabled(false);
        }
    }//GEN-LAST:event_tblProveedoresMouseClicked

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

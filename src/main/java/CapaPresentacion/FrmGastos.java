package CapaPresentacion;
import CapaEntidad.Gastos;
import CapaNegocio.GastosBL;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author e-p-l
 */
public class FrmGastos extends javax.swing.JFrame {

GastosBL oGastoBL = new GastosBL();
/* void listar(){
        DefaultTableModel mtabla = new DefaultTableModel();
        String [] titulos = {
            "IdGasto",
            "IdProveedor",
            "FechaGasto",
            "TipoDocumento",
            "NumeroDocumento",
            "Concepto",
            "Moneda",
            "Importe",
        };
        mtabla.setColumnIdentifiers(titulos);
        List<Gastos> lista=oGastoBL.listar();
        for(Gastos empresa:lista){
            Object data [] ={empresa.getIdgasto(),
                empresa.getIdProveedor(),
                empresa.getFechaGasto(),
                empresa.getTipoDocumento(),
                empresa.getNumeroDocumento(),
                empresa.getConcepto(),
                empresa.getMoneda(),
                empresa.getImporte(),
                empresa.getIdProveedor(),
                };
            mtabla.addRow(data);
        }
        lbListar.setModel(mtabla);
        
    }*/
void listar() {
        DefaultTableModel mtabla = new DefaultTableModel();
        String[] titulos = {
            "Id_Gasto", "Id_Proveedor", "Fecha_Gasto", "Tipo_Documento",
            "Numero_Documento", "Concepto", "Moneda", "Importe"
        };
        mtabla.setColumnIdentifiers(titulos);
        
        List<Gastos> lista = oGastoBL.listar();
        for (Gastos gasto : lista) {
            Object[] data = {
                gasto.getIdgasto(),
                gasto.getIdProveedor(),
                gasto.getFechaGasto(),
                gasto.getTipoDocumento(),
                gasto.getNumeroDocumento(),
                gasto.getConcepto(),
                gasto.getMoneda(),
                gasto.getImporte()
            };
            mtabla.addRow(data);
        }
        tblListar.setModel(mtabla);
        actualizarResultados();
    }
private void deshabilitarCampos() {
    txtId.setEnabled(false);
    txtIdProveedor.setEnabled(false);
    txtNumDoc.setEnabled(false);
    txtaConcepto.setEnabled(false);
    txtImporte.setEnabled(false);
    cboTipoDoc.setEnabled(false);
    cboMoneda.setEnabled(false);
    txtFechaGasto.setEnabled(false);
}

private void habilitarCampos() {
    txtId.setEnabled(true);
    txtIdProveedor.setEnabled(true);
    txtNumDoc.setEnabled(true);
    txtaConcepto.setEnabled(true);
    txtImporte.setEnabled(true);
    cboTipoDoc.setEnabled(true);
    cboMoneda.setEnabled(true);
    txtFechaGasto.setEnabled(true);
}
  
void activarBotones(boolean botonGuardar, boolean botonEditar, boolean botonEliminar) {
        btnGuardar.setEnabled(botonGuardar);
        btnActualizar.setEnabled(botonEditar);
        btnEditar.setEnabled(botonEditar);
        btnEliminar.setEnabled(botonEliminar);
    }
 private void actualizarResultados() {
        int total = tblListar.getRowCount();
        lbResultado.setText("Total de resultados: " + total);
    }
    
 private boolean validarCampos() {
        if (txtFechaGasto.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNumDoc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de documento", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtaConcepto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el concepto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtImporte.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el importe", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtIdProveedor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del proveedor", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            new BigDecimal(txtImporte.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Importe debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(txtIdProveedor.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Proveedor debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
     void limpiar() {
        txtId.setText("");
        txtFechaGasto.setDate(null);
        cboTipoDoc.setSelectedIndex(0);
        txtNumDoc.setText("");
        txtaConcepto.setText("");
        cboMoneda.setSelectedIndex(0);
        txtImporte.setText("");
        txtIdProveedor.setText("");
        //activarBotones(true, false, false);
    }
     private void cargarTablaBusqueda(List<Gastos> lista) {
        DefaultTableModel mtabla = new DefaultTableModel();
        String[] titulos = {
            "Id_Gasto", "Id_Proveedor", "Fecha_Gasto", "Tipo_Documento",
            "Numero_Documento", "Concepto", "Moneda", "Importe"
        };
        mtabla.setColumnIdentifiers(titulos);
        
        for (Gastos gasto : lista) {
            Object[] data = {
                gasto.getIdgasto(),
                gasto.getIdProveedor(),
                gasto.getFechaGasto(),
                gasto.getTipoDocumento(),
                gasto.getNumeroDocumento(),
                gasto.getConcepto(),
                gasto.getMoneda(),
                gasto.getImporte()
            };
            mtabla.addRow(data);
        }
        tblListar.setModel(mtabla);
        actualizarResultados();
    }
    /**
     * Creates new form FrmGastos
     */
    public FrmGastos() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
       deshabilitarCampos(); // Al iniciar, todo deshabilitado
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        cboTipoDoc = new javax.swing.JComboBox<>();
        txtNumDoc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaConcepto = new javax.swing.JTextArea();
        txtImporte = new javax.swing.JTextField();
        txtIdProveedor = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListar = new javax.swing.JTable();
        cboMoneda = new javax.swing.JComboBox<>();
        txtFechaGasto = new com.toedter.calendar.JDateChooser();
        lbResultado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnTodo = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GASTOS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LISTA DE GASTOS ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 14, 206, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id Gasto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha Gasto");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo Documento");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Numero Documento");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Concepto");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Moneda");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, 20));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Importe");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("IdProveedor");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        txtId.setBackground(new java.awt.Color(0, 0, 51));
        txtId.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(255, 255, 255));
        txtId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtId.setEnabled(false);
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 110, -1));

        cboTipoDoc.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoDoc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        cboTipoDoc.setForeground(new java.awt.Color(0, 0, 0));
        cboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura", "Recibo", "Otro" }));
        jPanel1.add(cboTipoDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 150, 30));

        txtNumDoc.setBackground(new java.awt.Color(255, 255, 255));
        txtNumDoc.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtNumDoc.setForeground(new java.awt.Color(0, 0, 0));
        txtNumDoc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtNumDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 130, 30));

        txtaConcepto.setBackground(new java.awt.Color(255, 255, 255));
        txtaConcepto.setColumns(20);
        txtaConcepto.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtaConcepto.setForeground(new java.awt.Color(0, 0, 0));
        txtaConcepto.setRows(5);
        jScrollPane1.setViewportView(txtaConcepto);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 240, 80));

        txtImporte.setBackground(new java.awt.Color(255, 255, 255));
        txtImporte.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtImporte.setForeground(new java.awt.Color(0, 0, 0));
        txtImporte.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 130, 30));

        txtIdProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtIdProveedor.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtIdProveedor.setForeground(new java.awt.Color(0, 0, 0));
        txtIdProveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtIdProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 130, 30));

        tblListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblListar);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 530, 290));

        cboMoneda.setBackground(new java.awt.Color(255, 255, 255));
        cboMoneda.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        cboMoneda.setForeground(new java.awt.Color(0, 0, 0));
        cboMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soles", "Dolares" }));
        jPanel1.add(cboMoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 130, 30));
        jPanel1.add(txtFechaGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 190, 30));

        lbResultado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbResultado.setForeground(new java.awt.Color(255, 255, 255));
        lbResultado.setText("Total de Resultado");
        jPanel1.add(lbResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 490, 130, 30));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 255, 255)), "Consultas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setBackground(new java.awt.Color(0, 102, 153));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR POR CONCEPTO");
        btnBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 150, 30));

        btnTodo.setBackground(new java.awt.Color(0, 102, 153));
        btnTodo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnTodo.setText("LISTAR TODO");
        btnTodo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        btnTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodoActionPerformed(evt);
            }
        });
        jPanel2.add(btnTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 110, 30));

        btnListar.setBackground(new java.awt.Color(0, 102, 153));
        btnListar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnListar.setForeground(new java.awt.Color(255, 255, 255));
        btnListar.setText("BUSCAR POR ID");
        btnListar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel2.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 350, 100));
        jPanel2.getAccessibleContext().setAccessibleName("Consultas");

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Botones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setBackground(new java.awt.Color(0, 51, 102));
        btnActualizar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 30));

        btnEliminar.setBackground(new java.awt.Color(0, 51, 102));
        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 30));

        btnGuardar.setBackground(new java.awt.Color(0, 51, 102));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("AGREGAR");
        btnGuardar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 90, 30));

        btnEditar.setBackground(new java.awt.Color(0, 51, 102));
        btnEditar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR");
        btnEditar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 90, 30));

        btnNuevo.setBackground(new java.awt.Color(0, 51, 102));
        btnNuevo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("NUEVO");
        btnNuevo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel3.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 90, 30));

        btnSalir.setBackground(new java.awt.Color(0, 51, 102));
        btnSalir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("SALIR");
        btnSalir.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 90, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 400, 130));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      
      String concepto = JOptionPane.showInputDialog(this, "Ingrese el concepto a buscar:");
        if (concepto != null && !concepto.trim().isEmpty()) {
            List<Gastos> lista = oGastoBL.buscarPorConcepto(concepto);
            cargarTablaBusqueda(lista);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      
        if (!validarCampos()) return;
        
        try {
            Gastos g = new Gastos();
            g.setFechaGasto(new java.sql.Date(txtFechaGasto.getDate().getTime()));
            g.setTipoDocumento(cboTipoDoc.getSelectedItem().toString());
            g.setNumeroDocumento(txtNumDoc.getText());
            g.setConcepto(txtaConcepto.getText());
            g.setMoneda(cboMoneda.getSelectedItem().toString());
            g.setImporte(new BigDecimal(txtImporte.getText()));
            g.setIdProveedor(Integer.parseInt(txtIdProveedor.getText()));

            int r = oGastoBL.agregar(g);
            if (r > 0) {
                JOptionPane.showMessageDialog(this, "Gasto agregado correctamente ✅");
                limpiar();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar gasto ❌");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
      habilitarCampos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
            String idProveedorStr = JOptionPane.showInputDialog(this, "Ingrese el ID del proveedor:");
        if (idProveedorStr != null && !idProveedorStr.trim().isEmpty()) {
            try {
                int idProveedor = Integer.parseInt(idProveedorStr);
                List<Gastos> lista = oGastoBL.buscarPorProveedor(idProveedor);
                cargarTablaBusqueda(lista);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID Proveedor debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       limpiar();
       habilitarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        
        if (!validarCampos()) return;
        
        try {
            Gastos g = new Gastos();
            g.setIdgasto(Integer.parseInt(txtId.getText()));
            g.setIdProveedor(Integer.parseInt(txtIdProveedor.getText()));
            g.setFechaGasto(new java.sql.Date(txtFechaGasto.getDate().getTime()));
            g.setTipoDocumento(cboTipoDoc.getSelectedItem().toString());
            g.setNumeroDocumento(txtNumDoc.getText());
            g.setConcepto(txtaConcepto.getText());
            g.setMoneda(cboMoneda.getSelectedItem().toString());
            g.setImporte(new BigDecimal(txtImporte.getText()));
            int r = oGastoBL.actualizar(g);
            if (r > 0) {
                JOptionPane.showMessageDialog(this, "Gasto actualizado correctamente ✅");
                limpiar();
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar gasto ❌");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
        int fila = tblListar.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un gasto de la tabla para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int idGasto = Integer.parseInt(tblListar.getValueAt(fila, 0).toString());
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este gasto?", "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            int r = oGastoBL.eliminar(idGasto);
            if (r > 0) {
                JOptionPane.showMessageDialog(this, "Gasto eliminado correctamente ✅");
                listar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar gasto ❌");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
        int fila = tblListar.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un gasto de la tabla para editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Cargar datos del gasto seleccionado en los controles
        txtId.setText(tblListar.getValueAt(fila, 0).toString());
        txtIdProveedor.setText(tblListar.getValueAt(fila, 1).toString());
        // La fecha necesitaría conversión específica
        cboTipoDoc.setSelectedItem(tblListar.getValueAt(fila, 3).toString());
        txtNumDoc.setText(tblListar.getValueAt(fila, 4).toString());
        txtaConcepto.setText(tblListar.getValueAt(fila, 5).toString());
        cboMoneda.setSelectedItem(tblListar.getValueAt(fila, 6).toString());
        txtImporte.setText(tblListar.getValueAt(fila, 7).toString());
        
        activarBotones(false, true, true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodoActionPerformed
        listar();
    }//GEN-LAST:event_btnTodoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnTodo;
    private javax.swing.JComboBox<String> cboMoneda;
    private javax.swing.JComboBox<String> cboTipoDoc;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel lbResultado;
    private javax.swing.JTable tblListar;
    private com.toedter.calendar.JDateChooser txtFechaGasto;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNumDoc;
    private javax.swing.JTextArea txtaConcepto;
    // End of variables declaration//GEN-END:variables
}

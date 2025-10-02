
package CapaPresentacion;

import CapaEntidad.Usuario;
import CapaNegocio.UsuarioBL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FrmUsuario extends javax.swing.JFrame {
    
    UsuarioBL oUsuarioBL = new UsuarioBL();

    
    void listar(){
        DefaultTableModel mtabla = new DefaultTableModel();
        String [] titulos = {
            "Id_Usuario",
            "Nombre Usuario",
            "Apellido Paterno",
            "Nombres",
            "Contraseña",
            "Perfil",
            "Estado"
        };
        mtabla.setColumnIdentifiers(titulos);
        List<Usuario> lista=oUsuarioBL.listar();
        for(Usuario usua:lista){
            Object data [] ={usua.getIdUsuario(),
                usua.getNombreUsuario(),
                usua.getApellidoPaterno(),
                usua.getNombres(),
                usua.getContrasena(),
                usua.getPerfil(),
                usua.getEstado()
                };
            mtabla.addRow(data);
        }
        tblUsuarios.setModel(mtabla);
        
    }
    
 
    
    public FrmUsuario() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        listar();
    }
    
    void limpiar(){
       txtIdUsuario.setText("");  
       txtNomUsuario.setText("");    
       txtApePaterno.setText("");
       txtNombres.setText("");    
       txtContraseña.setText("");
       cboPerfil.setSelectedIndex(0); 
       cboEstado.setSelectedIndex(0);
    }
    
    void insertar() {
        try {
            Usuario ousuario = new Usuario(
            txtNomUsuario.getText(),
            txtApePaterno.getText(),
            txtNombres.getText(),
            txtContraseña.getText(),
            cboPerfil.getSelectedItem().toString(),
            cboEstado.getSelectedItem().toString());
            
            int r = oUsuarioBL.agregarUsuario(ousuario);
            if (r > 0) {
                JOptionPane.showMessageDialog(this, "Usuario insertado correctamente");
                listar();
                limpiar();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + e.getMessage());
        }
    }
    
    
    void actualizar() {
        try {
            if (txtIdUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar");
                return;
            }
            Usuario ousuario = new Usuario(
                Integer.parseInt(txtIdUsuario.getText()),
                txtNomUsuario.getText(),
                txtApePaterno.getText(),
                txtNombres.getText(),
                txtContraseña.getText(),
                cboPerfil.getSelectedItem().toString(),
                cboEstado.getSelectedItem().toString());
            
            int r = oUsuarioBL.actualizarUsuario(ousuario);
            if (r > 0) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
                listar();
                limpiar();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }
    
    
    void eliminar() {
        try {
            if (txtIdUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese ID de usuario a eliminar");
                return;
            }
            int id = Integer.parseInt(txtIdUsuario.getText());
            int r = oUsuarioBL.eliminarUsuario(id);
            if (r > 0) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente");
                listar();
                limpiar();
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
        }
    }
    
    

    
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
        txtIdUsuario = new javax.swing.JTextField();
        txtNomUsuario = new javax.swing.JTextField();
        txtApePaterno = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cboEstado = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        cboPerfil = new javax.swing.JComboBox<>();
        txtNombres = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 17, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nom Usuario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Ape Paterno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Perfil");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Estado");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("idUsuario");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
        jPanel1.add(txtIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 180, -1));
        jPanel1.add(txtNomUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, -1));
        jPanel1.add(txtApePaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 180, -1));

        btnInsertar.setBackground(new java.awt.Color(102, 102, 102));
        btnInsertar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });
        jPanel1.add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        btnListar.setBackground(new java.awt.Color(102, 102, 102));
        btnListar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel1.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        btnActualizar.setBackground(new java.awt.Color(102, 102, 102));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(102, 102, 102));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, -1));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        jPanel1.add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 180, -1));

        tblUsuarios.setBackground(new java.awt.Color(204, 204, 204));
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsuarios);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 510, 240));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nombres");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        jPanel1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 180, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Contraseña");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        cboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Operador" }));
        jPanel1.add(cboPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 180, -1));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 180, -1));

        btnSalir.setBackground(new java.awt.Color(102, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("REGISTRO DE USUARIO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        insertar();
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        listar();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        int fila = tblUsuarios.getSelectedRow();
    if (fila >= 0) {
        txtIdUsuario.setText(tblUsuarios.getValueAt(fila, 0).toString());
        txtNomUsuario.setText(tblUsuarios.getValueAt(fila, 1).toString());
        txtApePaterno.setText(tblUsuarios.getValueAt(fila, 2).toString());
        txtNombres.setText(tblUsuarios.getValueAt(fila, 3).toString());
        txtContraseña.setText(tblUsuarios.getValueAt(fila, 4).toString());
        cboPerfil.setSelectedItem(tblUsuarios.getValueAt(fila, 5).toString());

        // Estado puede ser "Activo"/"Inactivo" o 1/0 según cómo lo guardes
        Object estado = tblUsuarios.getValueAt(fila, 6);
        if (estado.toString().equals("1")) {
            cboEstado.setSelectedItem("Activo");
        } else if (estado.toString().equals("0")) {
            cboEstado.setSelectedItem("Inactivo");
        } else {
            cboEstado.setSelectedItem(estado.toString());
        }
    }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Desea salir del sistema?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

    if (opcion == JOptionPane.YES_OPTION) {
        System.exit(0); // Cierra toda la aplicación
    }
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboPerfil;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtApePaterno;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNomUsuario;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}

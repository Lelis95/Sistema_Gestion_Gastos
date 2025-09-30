
package CapaPresentacion;

import CapaDatos.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class FrmAcceso extends javax.swing.JFrame {

    
    public FrmAcceso() {
        initComponents();
        setLocationRelativeTo(null);
    }

    
    // verificar acceso usando SP sp_verificarAcceso(p_usuario, p_clave)
private boolean verificarAcceso(String login, String pass) {
    String call = "{CALL sp_verificarAcceso(?, ?)}";
    try (Connection con = new ConexionBD().abrirConexion();
         CallableStatement cs = con.prepareCall(call)) {

        cs.setString(1, login);
        cs.setString(2, pass);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("existe") > 0;
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error en verificarAcceso: " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}

// obtener nombre (usa sp_obtenerNombreEmpleado(p_usuario) que devuelve columna "nombre")
private String obtenerNombreUsuario(String usuario) {
    String call = "{CALL sp_obtenerNombreEmpleado(?)}";
    try (Connection con = new ConexionBD().abrirConexion();
         CallableStatement cs = con.prepareCall(call)) {

        cs.setString(1, usuario);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getString("nombre");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error en obtenerNombreEmpleado: " + e.getMessage());
        e.printStackTrace();
    }
    return "";
}

// obtener id (usa sp_obtenerIdEmpleado(p_usuario) que devuelve columna "idEmpleado")
private int obtenerIdUsuario(String usuario) {
    String call = "{CALL sp_obtenerIdEmpleado(?)}";
    try (Connection con = new ConexionBD().abrirConexion();
         CallableStatement cs = con.prepareCall(call)) {

        cs.setString(1, usuario);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idEmpleado");
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error en obtenerIdEmpleado: " + e.getMessage());
        e.printStackTrace();
    }
    return -1;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAcceder = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtLogin = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Inciar Sesion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Password");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Login");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        btnAcceder.setText("Acceder");
        btnAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccederActionPerformed(evt);
            }
        });
        jPanel2.add(btnAcceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));
        jPanel2.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 120, -1));
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 120, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 20, 300, 240));

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
         String usuario = txtLogin.getText().trim();
         String clave = new String(txtPass.getPassword()).trim();

    if (usuario.isEmpty() || clave.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese usuario y clave.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (verificarAcceso(usuario, clave)) {
        JOptionPane.showMessageDialog(this, "Bienvenido.", "Acceso", JOptionPane.INFORMATION_MESSAGE);

        // Si es admin, lo mandamos también a FrmPrincipal
        if (usuario.equalsIgnoreCase("admin")) {
            FrmPrincipal principal = new FrmPrincipal("Administrador", 0);
            principal.setVisible(true);
            this.dispose();
        } else {
            String nombreUsuario = obtenerNombreUsuario(usuario);
            int idUsuario = obtenerIdUsuario(usuario);

            FrmPrincipal principal = new FrmPrincipal(nombreUsuario, idUsuario);
            principal.setVisible(true);
            this.dispose();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        txtPass.setText("");
        txtPass.requestFocus();
    }
    }//GEN-LAST:event_btnAccederActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea salir?",
            "Confirmación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
    );

    if (respuesta == JOptionPane.YES_OPTION) {
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
            java.util.logging.Logger.getLogger(FrmAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAcceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAcceso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcceder;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}

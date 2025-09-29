
package CapaPresentacion;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class FrmAcceso extends javax.swing.JFrame {

    
    public FrmAcceso() {
        initComponents();
    }

    
    private String obtenerNombreGastos(String usuario) {
        try (Connection con =  Conexion.getConexion();
                  CallableStatement cs = con.prepareCall("----")) {
            
            cs.setString(1, usuario);
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "------------" + e.getMessage());
            }
            return "";
        }
    }
    
    private String obtenerNombreEmpleado(String usuario) {
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall("{CALL sp_obtenerNombreEmpleado(?)}")) {

            cs.setString(1, usuario);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en obtenerNombreEmpleado: " + e.getMessage());
        }
        return "";
    }

    private int obtenerIdEmpleado(String usuario) {
        try (Connection con = Conexion.getConexion();
             CallableStatement cs = con.prepareCall("-----")) {

            cs.setString(1, usuario);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                return rs.getInt("idEmpleado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en obtenerIdEmpleado: " + e.getMessage());
        }
        return -1;
    }
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
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));
        jPanel2.add(txtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 120, -1));
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 120, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 275, 210));

        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
        String usuario = txtLogin.getText().trim();
        String clave = new String (txtPass.getPassword()).trim();
        
        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y clave.", "Advertencia",JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (verificarAcceso(usuario, clave)) {
            JOptionPane.showMessageDialog(this, "Bienvenido", "Acceso", JOptionPane.INFORMATION_MESSAGE);
            
            if (usuario.equalsIgnoreCase("admin")) {
                Form1 adminForm = new Form1();
                adminForm.setVisible(true);
                this.dispose();
            }
            else {
                String nombreGastos = obtenerNombreGastos(usuario);
                int idUsuario = obtenerIdUsiario(usuario);
                this.dispose();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Usuario o clave incorretos.","Error",JOptionPane.ERROR_MESSAGE);
            txtPass.setText("");
            txtPass.requestFocus();
        }
        
    }//GEN-LAST:event_btnAccederActionPerformed

    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eam.ingesoft.standalone.gui.vista;

/**
 *
 * @author lenovo
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCustomer = new javax.swing.JButton();
        btnTarjetas = new javax.swing.JButton();
        btnVentanaConsumo = new javax.swing.JButton();
        btnPagoConsumos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuCliente = new javax.swing.JMenu();
        jMenuItemGestionCliente = new javax.swing.JMenuItem();
        jMenuProductos = new javax.swing.JMenu();
        jMenuItemGestionProducto = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemConsumo = new javax.swing.JMenuItem();
        jMenuItemPagoConsumo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCustomer.setText("GESTION CUSTOMER");
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnTarjetas.setText("GESTION TARJETAS");
        btnTarjetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarjetasActionPerformed(evt);
            }
        });

        btnVentanaConsumo.setText("CONSUMO TARJETA DE CREDITO");
        btnVentanaConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentanaConsumoActionPerformed(evt);
            }
        });

        btnPagoConsumos.setText("PAGO DE CONSUMOS");
        btnPagoConsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoConsumosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("BANQUITO");

        jMenuCliente.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\customer.png")); // NOI18N
        jMenuCliente.setText("Cliente");

        jMenuItemGestionCliente.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\customer-add.png")); // NOI18N
        jMenuItemGestionCliente.setText("Crear Customer");
        jMenuItemGestionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionClienteActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemGestionCliente);

        jMenuBar.add(jMenuCliente);

        jMenuProductos.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\tarjeta-de-credito.png")); // NOI18N
        jMenuProductos.setText("Productos");

        jMenuItemGestionProducto.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\creditcard.png")); // NOI18N
        jMenuItemGestionProducto.setText("Crear productos");
        jMenuProductos.add(jMenuItemGestionProducto);

        jMenuBar.add(jMenuProductos);

        jMenu1.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\datafono.png")); // NOI18N
        jMenu1.setText("Consumo");

        jMenuItemConsumo.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\tienda-online.png")); // NOI18N
        jMenuItemConsumo.setText("Tarjeta de credito");
        jMenuItemConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsumoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemConsumo);

        jMenuItemPagoConsumo.setIcon(new javax.swing.ImageIcon("D:\\EAM\\Quinto Semestre\\Programacion Avanzada\\Primer Corte\\banco\\standalone\\src\\main\\java\\co\\edu\\eam\\ingesoft\\standalone\\gui\\imagenes\\cash.png")); // NOI18N
        jMenuItemPagoConsumo.setText("Pagar Consumo");
        jMenuItemPagoConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPagoConsumoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPagoConsumo);

        jMenuBar.add(jMenu1);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnCustomer)
                        .addGap(63, 63, 63)
                        .addComponent(btnTarjetas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(btnVentanaConsumo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(btnPagoConsumos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCustomer)
                    .addComponent(btnTarjetas))
                .addGap(42, 42, 42)
                .addComponent(btnVentanaConsumo)
                .addGap(39, 39, 39)
                .addComponent(btnPagoConsumos)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        // TODO add your handling code here:
        new VentanaGestionCustomer().setVisible(true);
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnTarjetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarjetasActionPerformed
        // TODO add your handling code here:
        new VentanaGestionProductos().setVisible(true);
    }//GEN-LAST:event_btnTarjetasActionPerformed

    private void btnVentanaConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentanaConsumoActionPerformed
        // TODO add your handling code here:
        VentanaCreditCardConsume v = new VentanaCreditCardConsume();
        v.setVisible(true);
    }//GEN-LAST:event_btnVentanaConsumoActionPerformed

    private void jMenuItemGestionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionClienteActionPerformed
        // TODO add your handling code here:
        new VentanaGestionCustomer().setVisible(true);
    }//GEN-LAST:event_jMenuItemGestionClienteActionPerformed

    private void jMenuItemConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsumoActionPerformed
        // TODO add your handling code here:
        VentanaCreditCardConsume v = new VentanaCreditCardConsume();
        v.setVisible(true);
    }//GEN-LAST:event_jMenuItemConsumoActionPerformed

    private void btnPagoConsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoConsumosActionPerformed
        // TODO add your handling code here:}
        new VentanaCreditCardPayment().setVisible(true);
    }//GEN-LAST:event_btnPagoConsumosActionPerformed

    private void jMenuItemPagoConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPagoConsumoActionPerformed
        // TODO add your handling code here:
        new VentanaCreditCardPayment().setVisible(true);
    }//GEN-LAST:event_jMenuItemPagoConsumoActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnPagoConsumos;
    private javax.swing.JButton btnTarjetas;
    private javax.swing.JButton btnVentanaConsumo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenuItem jMenuItemConsumo;
    private javax.swing.JMenuItem jMenuItemGestionCliente;
    private javax.swing.JMenuItem jMenuItemGestionProducto;
    private javax.swing.JMenuItem jMenuItemPagoConsumo;
    private javax.swing.JMenu jMenuProductos;
    // End of variables declaration//GEN-END:variables
}

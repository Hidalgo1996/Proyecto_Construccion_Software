/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

/**
 *
 * @author kevin
 */
public class Inter_fecha_calendario extends javax.swing.JInternalFrame {

    /**
     * Creates new form Inter_fecha_calendario
     */
    public Inter_fecha_calendario() {
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

        jPanel1 = new javax.swing.JPanel();
        text_field_id_calendario = new javax.swing.JTextField();
        text_box_hora_partido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        text_box_fecha_partido = new javax.swing.JTextField();
        combo_box_categoria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        boton_guardar_calendario = new javax.swing.JButton();
        text_box_partidos = new javax.swing.JTextField();
        text_box_lugar_partido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        boton_limpiar_calendario = new javax.swing.JButton();
        text_box_dorsal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        text_field_buscar = new javax.swing.JTextField();
        boton_buscar_calendario = new javax.swing.JButton();
        boton_editar = new javax.swing.JButton();
        boton_borrar_calendario = new javax.swing.JButton();
        combo_box_buscar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_calendario = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setTitle("Ingreso fecha calendario");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        text_field_id_calendario.setBackground(new java.awt.Color(255, 255, 255));
        text_field_id_calendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_field_id_calendarioActionPerformed(evt);
            }
        });
        jPanel1.add(text_field_id_calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 158, -1));

        text_box_hora_partido.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_box_hora_partido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 158, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Hora del partido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ingreso");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fecha del partido:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        text_box_fecha_partido.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_box_fecha_partido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 158, -1));

        combo_box_categoria.setBackground(new java.awt.Color(255, 255, 255));
        combo_box_categoria.setForeground(new java.awt.Color(0, 0, 0));
        combo_box_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(combo_box_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 161, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Id calendario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Lugar del partido:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        boton_guardar_calendario.setBackground(new java.awt.Color(0, 204, 204));
        boton_guardar_calendario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_guardar_calendario.setForeground(new java.awt.Color(0, 0, 0));
        boton_guardar_calendario.setText("Guardar");
        boton_guardar_calendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardar_calendarioActionPerformed(evt);
            }
        });
        jPanel1.add(boton_guardar_calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 91, -1));

        text_box_partidos.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_box_partidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 161, -1));

        text_box_lugar_partido.setBackground(new java.awt.Color(255, 255, 255));
        text_box_lugar_partido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_box_lugar_partidoActionPerformed(evt);
            }
        });
        jPanel1.add(text_box_lugar_partido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 159, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Categoria:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        boton_limpiar_calendario.setBackground(new java.awt.Color(0, 204, 0));
        boton_limpiar_calendario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_limpiar_calendario.setForeground(new java.awt.Color(0, 0, 0));
        boton_limpiar_calendario.setText("Limpiar");
        jPanel1.add(boton_limpiar_calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 88, -1));

        text_box_dorsal.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_box_dorsal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 161, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Partidos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Dorsal:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("*Seleccione un registro para efectuar alguna accion de edicion o borrado* ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Buscar por:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, -1));

        text_field_buscar.setBackground(new java.awt.Color(255, 255, 255));
        text_field_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_field_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(text_field_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 157, -1));

        boton_buscar_calendario.setBackground(new java.awt.Color(0, 204, 204));
        boton_buscar_calendario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_buscar_calendario.setForeground(new java.awt.Color(0, 0, 0));
        boton_buscar_calendario.setText("Buscar");
        jPanel1.add(boton_buscar_calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, -1, -1));

        boton_editar.setBackground(new java.awt.Color(255, 255, 0));
        boton_editar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_editar.setForeground(new java.awt.Color(0, 0, 0));
        boton_editar.setText("Editar");
        jPanel1.add(boton_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 90, -1));

        boton_borrar_calendario.setBackground(new java.awt.Color(204, 0, 0));
        boton_borrar_calendario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_borrar_calendario.setForeground(new java.awt.Color(0, 0, 0));
        boton_borrar_calendario.setText("Borrar");
        jPanel1.add(boton_borrar_calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 95, -1));

        combo_box_buscar.setBackground(new java.awt.Color(255, 255, 255));
        combo_box_buscar.setForeground(new java.awt.Color(0, 0, 0));
        combo_box_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(combo_box_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 145, -1));

        table_calendario.setBackground(new java.awt.Color(255, 255, 255));
        table_calendario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_calendario);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 757, 281));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo blanco.jpg"))); // NOI18N
        jLabel13.setText("jLabel5");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 300, 380));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo blanco.jpg"))); // NOI18N
        jLabel14.setText("jLabel5");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 770, 440));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancha 2.0.jpg"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 1140, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void text_field_id_calendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_field_id_calendarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_field_id_calendarioActionPerformed

    private void text_field_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_field_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_field_buscarActionPerformed

    private void text_box_lugar_partidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_box_lugar_partidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_box_lugar_partidoActionPerformed

    private void boton_guardar_calendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_guardar_calendarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_guardar_calendarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_borrar_calendario;
    private javax.swing.JButton boton_buscar_calendario;
    private javax.swing.JButton boton_editar;
    private javax.swing.JButton boton_guardar_calendario;
    private javax.swing.JButton boton_limpiar_calendario;
    private javax.swing.JComboBox<String> combo_box_buscar;
    private javax.swing.JComboBox<String> combo_box_categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_calendario;
    private javax.swing.JTextField text_box_dorsal;
    private javax.swing.JTextField text_box_fecha_partido;
    private javax.swing.JTextField text_box_hora_partido;
    private javax.swing.JTextField text_box_lugar_partido;
    private javax.swing.JTextField text_box_partidos;
    private javax.swing.JTextField text_field_buscar;
    private javax.swing.JTextField text_field_id_calendario;
    // End of variables declaration//GEN-END:variables
}

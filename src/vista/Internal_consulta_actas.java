package vista;

import controlador.Controlador_actas_partido;
import excepciones.ActasException;

import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Actas_partido;
import static vista.Ventana_arbitro.panel_principal_arbitro;

public class Internal_consulta_actas extends javax.swing.JInternalFrame {

    Controlador_actas_partido controladorActas = new Controlador_actas_partido();
    /**
     * Creates new form Internal_consulta_actas
     */
    public Internal_consulta_actas() {
        initComponents();

        this.setSize(new Dimension(864, 465));// Con esto le damos el tamaño que querramos.
        this.setTitle("ÁRBITRO: CONSULTA DE ACTAS DEL PARTIDO");// Titulo de la ventana
        // this.cargar_tabla_consulta_actas();//Me carga la tabla en el table
    }

    /**
     * Eliminar
     */
    private void eliminarActa() {
        if (Tabla_actas_partidos.getSelectedRow() >= 0) {

            int row = Tabla_actas_partidos.getSelectedRow();
            int id = Integer.parseInt(Tabla_actas_partidos.getModel().getValueAt(row, 0).toString());

            int confirmacion = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro?", "Advertencia",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                try {
                    String mensaje = controladorActas.eliminarActa(id);
                    JOptionPane.showMessageDialog(null, mensaje, "Dialog", JOptionPane.INFORMATION_MESSAGE);
                } catch (ActasException e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }
        }
    }

    /**
     * Listar
     */
    public void listarActas() {

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }

        };

        Internal_consulta_actas.Tabla_actas_partidos = new JTable(modelo);
        Internal_consulta_actas.jScrollPane1.setViewportView(Internal_consulta_actas.Tabla_actas_partidos);
        modelo.addColumn("id_acta_partido");
        modelo.addColumn("hora_inicio_partido");
        modelo.addColumn("hora_fin_partido");
        modelo.addColumn("equipo_rival");
        modelo.addColumn("equipo_local");
        modelo.addColumn("duracion_partido");
        modelo.addColumn("num_gole_equip_rival");
        modelo.addColumn("num_gole_equip_local");
        modelo.addColumn("equipo_ganador");
        modelo.addColumn("arbitro_id_arbitro1");

        List<Actas_partido> actas = controladorActas.listarActas();

        for (Actas_partido acta : actas) {
            modelo.addRow(new Object[] { acta.getId_acta_partido(),
                    acta.getHora_inicio_partido(),
                    acta.getHora_fin_partido(), acta.getNombre_equipo_rival(),
                    acta.getNombre_equipo_local(),
                    acta.getDuracion_partido(), acta.getGoles_equipo_rival(),
                    acta.getGoles_equipo_local(), acta.getEquipo_ganador(),
                    acta.getId_arbitro() });
        }

    }

    /**
     * Actualizar
     */
    public void actualizarActa() {

        Inter_ingreso_actas ingresarAc = new Inter_ingreso_actas();
        /*
         * Captura del valor seleccionado en la celda
         */
        if (Tabla_actas_partidos.getSelectedRow() > 0) {

            int row = Tabla_actas_partidos.getSelectedRow();

            ingresarAc.id_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 0).toString());
            ingresarAc.hora_inicio_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 1).toString());
            ingresarAc.hora_fin_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 2).toString());
            ingresarAc.equipo_rival_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 3).toString());
            ingresarAc.equipo_local_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 4).toString());
            ingresarAc.duracion_partido_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 5).toString());
            ingresarAc.goles_rival_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 6).toString());
            ingresarAc.goles_local_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 7).toString());
            ingresarAc.equipo_ganador_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 8).toString());
            ingresarAc.id_arbitro_text_box.setText(Tabla_actas_partidos.getModel().getValueAt(row, 9).toString());

            ingresarAc.jbtActualizar2.setEnabled(true);
            ingresarAc.Boton_ingresar_nueva_acta.setEnabled(false);

            panel_principal_arbitro.add(ingresarAc);
            ingresarAc.setVisible(true);
            ingresarAc.show();

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_actas_partidos = new javax.swing.JTable();
        boton_consulta = new javax.swing.JButton();
        jBtnActualizar = new javax.swing.JButton();
        jbtnActualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/busqueda_acta.png"))); // NOI18N
        jLabel1.setText("Consultar actas del partido");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        Tabla_actas_partidos.setBackground(new java.awt.Color(204, 204, 204));
        Tabla_actas_partidos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        Tabla_actas_partidos.setEnabled(false);
        jScrollPane1.setViewportView(Tabla_actas_partidos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE));

        boton_consulta.setBackground(new java.awt.Color(0, 153, 255));
        boton_consulta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        boton_consulta.setForeground(new java.awt.Color(255, 255, 255));
        boton_consulta.setText("Consultar");
        boton_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_consultaActionPerformed(evt);
            }
        });

        jBtnActualizar.setBackground(new java.awt.Color(51, 255, 51));
        jBtnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jBtnActualizar.setText("Actualizar");
        jBtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarActionPerformed(evt);
            }
        });

        jbtnActualizar.setBackground(new java.awt.Color(255, 51, 51));
        jbtnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnActualizar.setText("Eliminar");
        jbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(boton_consulta)
                                .addGap(76, 76, 76)
                                .addComponent(jBtnActualizar)
                                .addGap(63, 63, 63)
                                .addComponent(jbtnActualizar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(boton_consulta, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jBtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jbtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(13, 13, 13)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_consultaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_consultaActionPerformed
        listarActas();
    }// GEN-LAST:event_boton_consultaActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jBtnActualizarActionPerformed
        actualizarActa();
    }// GEN-LAST:event_jBtnActualizarActionPerformed

    private void jbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnActualizarActionPerformed
        eliminarActa();

    }// GEN-LAST:event_jbtnActualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable Tabla_actas_partidos;
    private javax.swing.JButton boton_consulta;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnActualizar;

}

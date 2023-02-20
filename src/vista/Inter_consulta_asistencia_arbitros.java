/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador_asistencia;
import controlador.Controlador_rol_arbitro;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelo.Arbitro;
import modelo.Asistencia;

/**
 *
 * @author kevin
 */
public class Inter_consulta_asistencia_arbitros extends javax.swing.JInternalFrame {

    DefaultTableModel modeloTablaArbitros;
    DefaultTableModel modeloTablaAsistencias;
    Integer id_arbitro = 0;

    /**
     * Creates new form Inter_consulta_asistencia_arbitros
     */
    public Inter_consulta_asistencia_arbitros() {
        initComponents();
        setearTabla();
        cargarArbitros();
    }

    private void setearTabla() {

        modeloTablaAsistencias = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }
        };

        modeloTablaArbitros = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }
        };

        modeloTablaArbitros.addColumn("#");
        modeloTablaArbitros.addColumn("id");
        modeloTablaArbitros.addColumn("Nombre");
        modeloTablaArbitros.addColumn("Apellido");
        modeloTablaArbitros.addColumn("Nacionalidad");
        modeloTablaArbitros.addColumn("Categoria");
        table_arbitros = new JTable(modeloTablaArbitros);
        jScrollPane1.setViewportView(table_arbitros);
        table_arbitros.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                table_arbitrosMousePressed(evt);
            }
        });

        modeloTablaAsistencias.addColumn("#");
        modeloTablaAsistencias.addColumn("id_asistencia");
        modeloTablaAsistencias.addColumn("Partido");
        modeloTablaAsistencias.addColumn("Lugar");
        modeloTablaAsistencias.addColumn("Fecha");
        modeloTablaAsistencias.addColumn("Asistencia");
        table_arbitro_asistencia = new JTable(modeloTablaAsistencias);
        jScrollPane2.setViewportView(table_arbitro_asistencia);
    }

    private void cargarArbitros() {
        int i = 0;
       /*  modeloTablaArbitros.getDataVector().removeAllElements();
        table_arbitros.updateUI(); */
        for (Arbitro e : Controlador_rol_arbitro.listarArbitros()) {
            i = i + 1;
            modeloTablaArbitros.addRow(new Object[] {
                    i,
                    e.getId_usuario(),
                    e.getNombre(),
                    e.getApellido(),
                    e.getNacionalidad(),
                    e.getCategoria()
            });
        }
    }

    private void cargarAsistencias() {

        if (id_arbitro == 0)
            return;

        int i = 0;
        modeloTablaAsistencias.getDataVector().removeAllElements();
        table_arbitro_asistencia.updateUI();
        for (Asistencia a : Controlador_asistencia.listarAsistencias(id_arbitro)) {
            i = i + 1;
            modeloTablaAsistencias.addRow(new Object[] {
                    i,
                    a.getId_asistencia(),
                    a.getPartido(),
                    a.getLugar(),
                    a.getFecha(),
                    a.getAsistio() ? "Asistio" : "No Asistio", });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_arbitros = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_arbitro_asistencia = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        text_field_buscar_arbitro = new javax.swing.JTextField();
        boton_buscar_arbitro = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Asistencia arbitros");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_arbitros.setBackground(new java.awt.Color(255, 255, 255));
        table_arbitros.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                }));
        table_arbitros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_arbitrosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_arbitros);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 750, 140));

        table_arbitro_asistencia.setModel(new javax.swing.table.DefaultTableModel(

        ));
        jScrollPane2.setViewportView(table_arbitro_asistencia);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 750, 160));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre del arbitro a buscar:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        text_field_buscar_arbitro.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_field_buscar_arbitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 220, -1));

        boton_buscar_arbitro.setBackground(new java.awt.Color(0, 204, 204));
        boton_buscar_arbitro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_buscar_arbitro.setForeground(new java.awt.Color(0, 0, 0));
        boton_buscar_arbitro.setText("Buscar");
        boton_buscar_arbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_arbitroActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar_arbitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 80, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancha 2.0.jpg"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 840, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 804,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_arbitrosMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_table_arbitrosMousePressed
        if (table_arbitros.getRowCount() > -1) {
            int fila = table_arbitros.getSelectedRow();
            id_arbitro = Integer.parseInt(table_arbitros.getModel().getValueAt(fila, 1).toString());
            System.out.print("Id arbitro: " + id_arbitro);
            cargarAsistencias();

        }
    }// GEN-LAST:event_table_arbitrosMousePressed

    private void boton_buscar_arbitroActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_buscar_arbitroActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_boton_buscar_arbitroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_buscar_arbitro;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_arbitro_asistencia;
    private javax.swing.JTable table_arbitros;
    private javax.swing.JTextField text_field_buscar_arbitro;
    // End of variables declaration//GEN-END:variables
}

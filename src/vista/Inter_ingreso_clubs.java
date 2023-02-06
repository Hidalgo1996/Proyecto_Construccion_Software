/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador_equipo;
import modelo.Equipo_futbol;

/**
 *
 * @author kevin
 */
public class Inter_ingreso_clubs extends javax.swing.JInternalFrame {

    Controlador_equipo controlador_equipo = new Controlador_equipo();
    DefaultTableModel modelo;
    Integer id = 0;

    /**
     * Creates new form Inter_ingreso_clubs
     */
    public Inter_ingreso_clubs() {
        initComponents();

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }

        };

        modelo.addColumn("#");
        modelo.addColumn("id");
        modelo.addColumn("Nombre Club");
        modelo.addColumn("Director");
        table_club = new JTable(modelo);
        // table_club.setModel(modelo);
        jScrollPane1.setViewportView(table_club);

        table_club.getColumnModel().getColumn(0).setMaxWidth(20);
        table_club.getColumnModel().getColumn(0).setPreferredWidth(20);
        table_club.removeColumn(table_club.getColumnModel().getColumn(1));
        cargarCombo();
        cargarListadoEquipos();

    }

    public void guardarClub() {

        String mensaje = "";
        if (text_field_nombre_club.getText().isEmpty() || text_field_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese llene los datos", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {

            Equipo_futbol nuevoEquipo = new Equipo_futbol();
            nuevoEquipo.setNombre_equipo(text_field_nombre_club.getText().trim());
            nuevoEquipo.setDirector(text_field_email.getText().trim());
            if (id == 0) {
                mensaje = controlador_equipo.guardarEquipo(nuevoEquipo);

            } else {
                nuevoEquipo.setId_equipo(id);
                mensaje = controlador_equipo.actualizarEquipo(nuevoEquipo);
                id = 0;
            }

            JOptionPane.showMessageDialog(null, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);

            cargarListadoEquipos();
            limpiarTexts();
        }

    }

    public void limpiarTexts() {
        text_field_nombre_club.setText("");
        text_field_email.setText("");
    }

    public void editarClub() {

        if (table_club.getSelectedRow() > -1) {
            int fila = table_club.getSelectedRow();
            id = (int) (table_club.getModel().getValueAt(fila, 1));
            System.out.println(id);
            text_field_nombre_club.setText(table_club.getModel().getValueAt(fila, 2).toString());
            text_field_email.setText(table_club.getModel().getValueAt(fila, 3).toString());

        }

    }

    public void eliminarClub() {

        if (table_club.getSelectedRow() >= 0) {

            int row = table_club.getSelectedRow();
            int id = Integer.parseInt(table_club.getModel().getValueAt(row, 1).toString());

            int confirmacion = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro?", "Advertencia",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                try {
                    String mensaje = controlador_equipo.eliminarEquipo(id);
                    JOptionPane.showMessageDialog(null, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
                    cargarListadoEquipos();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }
        }
    }

    public void cargarListadoEquipos() {
        int i = 0;
        modelo.getDataVector().removeAllElements();
        table_club.updateUI();
        for (Equipo_futbol e : controlador_equipo.listarEquipos()) {
            i = i + 1;
            modelo.addRow(new Object[] { i, e.getId_equipo(), e.getNombre_equipo(), e.getDirector() });
        }
    }

    public void cargarCombo() {
        combo_box_buscar.addItem("Nombre Club");
        combo_box_buscar.addItem("Director Club");
    }

    public void flitrarTabla(String buscarPor, String texto) {
        List<Equipo_futbol> listaFiltrada = new ArrayList<Equipo_futbol>();

        System.out.println("Filtro" + "\n");
        System.out.println(buscarPor + "\n");
        System.out.println(texto + "\n");

        if (buscarPor.equalsIgnoreCase("nombre club")) {
            for (Equipo_futbol e : controlador_equipo.listarEquipos()) {
                if (e.getNombre_equipo().toLowerCase().contains(texto.toLowerCase())) {
                    System.out.println(e.getNombre_equipo() + "\n");
                    listaFiltrada.add(e);
                }
            }
        } else {
            controlador_equipo.listarEquipos().forEach((e) -> {
                if (e.getDirector().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(e);
                }
            });
        }

        for (Equipo_futbol eq : listaFiltrada) {
            System.out.println(eq.getNombre_equipo() + "\n");
        }

        int i = 0;
        modelo.getDataVector().removeAllElements();
        table_club.updateUI();
        for (Equipo_futbol e : listaFiltrada) {
            i = i + 1;
            modelo.addRow(new Object[] { i, e.getId_equipo(), e.getNombre_equipo(), e.getDirector() });
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        boton_guardar_club = new javax.swing.JButton();
        boton_limpiar_club = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        text_field_id_club = new javax.swing.JTextField();
        text_field_nombre_club = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        text_field_email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        combo_box_buscar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_club = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        text_field_buscar_club = new javax.swing.JTextField();
        boton_buscar_club = new javax.swing.JButton();
        boton_editar_club = new javax.swing.JButton();
        boton_borrar_club = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Ingreso de clubs");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton_guardar_club.setBackground(new java.awt.Color(0, 204, 204));
        boton_guardar_club.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_guardar_club.setForeground(new java.awt.Color(0, 0, 0));
        boton_guardar_club.setText("Guardar");
        boton_guardar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_guardar_clubActionPerformed(evt);
            }
        });
        jPanel1.add(boton_guardar_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 89, -1));

        boton_limpiar_club.setBackground(new java.awt.Color(0, 204, 0));
        boton_limpiar_club.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_limpiar_club.setForeground(new java.awt.Color(0, 0, 0));
        boton_limpiar_club.setText("Limpiar");
        boton_limpiar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiar_clubActionPerformed(evt);
            }
        });
        jPanel1.add(boton_limpiar_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 94, -1));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre club:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Correo club:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        text_field_id_club.setBackground(new java.awt.Color(255, 255, 255));
        text_field_id_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_field_id_clubActionPerformed(evt);
            }
        });
        jPanel1.add(text_field_id_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 171, -1));

        text_field_nombre_club.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_field_nombre_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 171, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ingreso");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        text_field_email.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(text_field_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 171, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Id club:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        combo_box_buscar.setBackground(new java.awt.Color(255, 255, 255));
        combo_box_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jPanel1.add(combo_box_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 145, -1));

        table_club.setForeground(new java.awt.Color(255, 255, 255));
        table_club.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_club);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 714, 247));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("*Seleccione un registro para efectuar alguna accion de edicion o borrado* ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Buscar por:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        text_field_buscar_club.setBackground(new java.awt.Color(255, 255, 255));
        text_field_buscar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_field_buscar_clubActionPerformed(evt);
            }
        });
        jPanel1.add(text_field_buscar_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 157, -1));

        boton_buscar_club.setBackground(new java.awt.Color(0, 204, 204));
        boton_buscar_club.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_buscar_club.setForeground(new java.awt.Color(0, 0, 0));
        boton_buscar_club.setText("Buscar");
        boton_buscar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscar_clubActionPerformed(evt);
            }
        });
        jPanel1.add(boton_buscar_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, -1, -1));

        boton_editar_club.setBackground(new java.awt.Color(255, 255, 0));
        boton_editar_club.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_editar_club.setForeground(new java.awt.Color(0, 0, 0));
        boton_editar_club.setText("Editar");
        boton_editar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_editar_clubActionPerformed(evt);
            }
        });
        jPanel1.add(boton_editar_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 82, -1));

        boton_borrar_club.setBackground(new java.awt.Color(204, 0, 0));
        boton_borrar_club.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        boton_borrar_club.setForeground(new java.awt.Color(0, 0, 0));
        boton_borrar_club.setText("Borrar");
        boton_borrar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_borrar_clubActionPerformed(evt);
            }
        });
        jPanel1.add(boton_borrar_club, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 85, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo blanco.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 760, 410));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo blanco.jpg"))); // NOI18N
        jLabel6.setText("jLabel5");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 300, 260));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancha 2.0.jpg"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_borrar_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_borrar_clubActionPerformed
        eliminarClub();
    }// GEN-LAST:event_boton_borrar_clubActionPerformed

    private void boton_editar_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_editar_clubActionPerformed
        editarClub();
    }// GEN-LAST:event_boton_editar_clubActionPerformed

    private void boton_limpiar_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_limpiar_clubActionPerformed
        limpiarTexts();
    }// GEN-LAST:event_boton_limpiar_clubActionPerformed

    private void boton_buscar_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_buscar_clubActionPerformed
        if (text_field_buscar_club.getText().isEmpty()) {
            cargarListadoEquipos();
        } else {
            String filtro = combo_box_buscar.getSelectedItem().toString();
            String textoAbuscar = text_field_buscar_club.getText().trim();
            flitrarTabla(filtro, textoAbuscar);
        }
    }// GEN-LAST:event_boton_buscar_clubActionPerformed

    private void text_field_id_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_text_field_id_clubActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_text_field_id_clubActionPerformed

    private void text_field_buscar_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_text_field_buscar_clubActionPerformed

    }// GEN-LAST:event_text_field_buscar_clubActionPerformed

    private void boton_guardar_clubActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boton_guardar_clubActionPerformed
        guardarClub();
    }// GEN-LAST:event_boton_guardar_clubActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_borrar_club;
    private javax.swing.JButton boton_buscar_club;
    private javax.swing.JButton boton_editar_club;
    private javax.swing.JButton boton_guardar_club;
    private javax.swing.JButton boton_limpiar_club;
    private javax.swing.JComboBox<String> combo_box_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_club;
    private javax.swing.JTextField text_field_buscar_club;
    private javax.swing.JTextField text_field_email;
    private javax.swing.JTextField text_field_id_club;
    private javax.swing.JTextField text_field_nombre_club;
    // End of variables declaration//GEN-END:variables
}

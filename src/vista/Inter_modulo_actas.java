/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador_actas_partido;
import modelo.Actas_partido;
import modelo.Partido;

/**
 *
 * @author Hidalgo
 */
public class Inter_modulo_actas extends javax.swing.JInternalFrame {

    Controlador_actas_partido controladorActas = new Controlador_actas_partido();
    Actas_partido acta_partido = new Actas_partido();
    DefaultTableModel modelo;
    Integer id_acta = 0;

    LocalDateTime fechaEmision = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");

    /**
     * Creates new form Inter_modulo_actas
     */
    public Inter_modulo_actas() {
        initComponents();
        this.setTitle("ÁRBITRO: ACTAS DEL PARTIDO");

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return false;
            }

        };

        modelo.addColumn("#");
        modelo.addColumn("id");
        modelo.addColumn("Fecha Emision");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Fin");
        modelo.addColumn("Partido");
        modelo.addColumn("Duracion");
        modelo.addColumn("Goles Local");
        modelo.addColumn("Goles Rival");
        modelo.addColumn("Equipo Ganador");
        table_actas = new JTable(modelo);
        // table_actas.setModel(modelo);
        jScrollPane1.setViewportView(table_actas);

        table_actas.getColumnModel().getColumn(0).setMaxWidth(20);
        table_actas.getColumnModel().getColumn(0).setPreferredWidth(20);
        table_actas.removeColumn(table_actas.getColumnModel().getColumn(1));
        cargarListadoActas();

        fecha_emision_txt.setText(fechaEmision.format(formato));
        fecha_emision_txt.setEnabled(false);
        partido_combo.addItem("Seleccione");
        equipo_ganador_text_box.setEnabled(false);
        llenarCombo();

    }

    /**
     * Captura los datos dentro de los textbox y los guarda en 
     * la tabla acta partido de la base de datos.
     * 
     * @param void
     * @return void
     */
    public void guardarActa() {

        if (id_text_box.getText().isEmpty() || hora_inicio_text_box.getText().isEmpty()
                || hora_fin_text_box.getText().isEmpty()
                || partido_combo.getSelectedIndex() == 0 || duracion_partido_text_box.getText().isEmpty()
                || goles_rival_text_box.getText().isEmpty() || goles_local_text_box.getText().isEmpty()
                || fecha_emision_txt.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Llena todos los campos");

        } else {

            // Esto me permite capturar lo que hay en las cajas de texto y guardar en los
            // atributos de clase de actas_partido
            acta_partido.setId_acta_partido(Integer.parseInt(id_text_box.getText()));
            acta_partido.setFecha_emision(Date.valueOf(fechaEmision.toLocalDate()));
            acta_partido.setHora_inicio_partido(hora_inicio_text_box.getText());
            acta_partido.setHora_fin_partido(hora_fin_text_box.getText());
            /*
             * Controlador_actas_partido.cargarComboPartido().forEach((key, valor) -> {
             * if(valor.equals(partido_combo.getSelectedItem())){
             * acta_partido.setId_partido(key);
             * }
             * return;
             * });
             */
            acta_partido.setDuracion_partido(Time.valueOf(duracion_partido_text_box.getText()));
            acta_partido.setGoles_equipo_rival(Integer.parseInt(goles_rival_text_box.getText()));
            acta_partido.setGoles_equipo_local(Integer.parseInt(goles_local_text_box.getText()));
            acta_partido.setEquipo_ganador(equipo_ganador_text_box.getText().trim());

            if (id_acta == 0) {

                if (controladorActas.guardar(acta_partido)) {

                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                } else {

                    JOptionPane.showMessageDialog(null, "Error de guardado, intentelo otra vez");
                }
            } else {
                String mensaje = controladorActas.actualizarActa(acta_partido);
                JOptionPane.showMessageDialog(null, mensaje);

            }

        }

    }

    // testing ...
    /*
     * public void llenarCombo() {
     * if (Controlador_actas_partido.cargarComboPartido() != null) {
     * Controlador_actas_partido.cargarComboPartido().forEach((key, valor) -> {
     * partido_combo.addItem(valor);
     * });
     * }
     * else {
     * System.out.println("combo vacio");
     * }
     * }
     */
    public void llenarCombo() {
        if (Controlador_actas_partido.cargarComboPartido() != null) {
            for (Partido p : Controlador_actas_partido.cargarComboPartido()) {
                partido_combo
                        .addItem(p.getClub_local().getNombre_equipo() + " VS " + p.getClub_rival().getNombre_equipo());
            }
        } else {
            System.out.println("combo vacio");
        }
    }

    
    /**
     * Limpia las cajas de texto una vez ingresado una nueva acta de partido.
     * 
     * @param void
     * @return void
     */
    public void limpiarTexts() {
        hora_inicio_text_box.setText("");
        hora_fin_text_box.setText("");
        partido_combo.setSelectedIndex(0);
        duracion_partido_text_box.setText("");
        goles_local_text_box.setText("");
        goles_rival_text_box.setText("");
        equipo_ganador_text_box.setText("");
    }

    
    /**
     * Edicion de algun registro en especifico de
     * un acta de partidos que ya ha sido ingresada en base de datos.
     * 
     * @param void
     * @return void
     */
    public void editarActa() {

        if (table_actas.getSelectedRow() > -1) {
            int fila = table_actas.getSelectedRow();
            id_acta = (int) (table_actas.getModel().getValueAt(fila, 1));
            System.out.println(id_acta);
            hora_inicio_text_box.setText(table_actas.getModel().getValueAt(fila, 3).toString());
            hora_fin_text_box.setText(table_actas.getModel().getValueAt(fila, 4).toString());
            duracion_partido_text_box.setText(table_actas.getModel().getValueAt(fila, 6).toString());
            goles_local_text_box.setText(table_actas.getModel().getValueAt(fila, 7).toString());
            goles_rival_text_box.setText(table_actas.getModel().getValueAt(fila, 8).toString());
            equipo_ganador_text_box.setText(table_actas.getModel().getValueAt(fila, 9).toString());
        }

    }

    
    /**
     * Borrado logico de un acta ingresada en la base de datos
     * Lo pasa de activo a inactivo.
     * 
     * @param void
     * @return void
     */
    public void eliminarActa() {

        if (table_actas.getSelectedRow() >= 0) {

            int row = table_actas.getSelectedRow();
            int id = Integer.parseInt(table_actas.getModel().getValueAt(row, 1).toString());

            int confirmacion = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro?", "Advertencia",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == 0) {
                try {
                    String mensaje = controladorActas.eliminarActa(id);
                    JOptionPane.showMessageDialog(null, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
                    cargarListadoActas();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }
        }
    }

    
    /**
     * Carga todo el listado de las actas de la base de datos
     * y lo muestra en la ventana actual.
     * 
     * @param void
     * @return void
     */
    public void cargarListadoActas() {
        int i = 0;
        modelo.getDataVector().removeAllElements();
        table_actas.updateUI();
        for (Actas_partido e : controladorActas.listarActas()) {
            i = i + 1;
            modelo.addRow(new Object[] { i, e.getId_acta_partido(), e.getFecha_emision(), e.getHora_inicio_partido(),
                    e.getHora_fin_partido(),
                    e.getPartido().getClub_local().getNombre_equipo() + " VS "
                            + e.getPartido().getClub_rival().getNombre_equipo(),
                    e.getDuracion_partido(), e.getGoles_equipo_local(),
                    e.getGoles_equipo_rival(), e.getEquipo_ganador() });
        }
    }
    
    
    /**
     *Filtra datos especificos de la tabla acta de partidos
     * en base al tipo de busqueda que se hace.
     * 
     * @param buscarPor, texto
     * @return void
     */
    public void filtrarTabla(String buscarPor, String texto) {

        List<Actas_partido> listaFiltrada = new ArrayList<Actas_partido>();
        System.out.println("Filtro" + "\n");
        System.out.println(buscarPor + "\n");
        System.out.println(texto + "\n");

        if (buscarPor.equalsIgnoreCase("Duracion")) {
            for (Actas_partido e : controladorActas.listarActas()) {
                if (e.getDuracion_partido().toString().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(e);
                }
            }
        } else {
            controladorActas.listarActas().forEach((e) -> {
                if (e.getEquipo_ganador().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(e);
                }
            });
        }

        int i = 0;
        modelo.getDataVector().removeAllElements();
        table_actas.updateUI();
        for (Actas_partido e : listaFiltrada) {
            i = i + 1;
            modelo.addRow(new Object[] { i, e.getId_acta_partido(), e.getFecha_emision(), e.getHora_inicio_partido(),
                    e.getHora_fin_partido(),
                    e.getPartido().getClub_local().getNombre_equipo() + " VS "
                            + e.getPartido().getClub_rival().getNombre_equipo(),
                    e.getDuracion_partido(), e.getGoles_equipo_local(),
                    e.getGoles_equipo_rival(), e.getEquipo_ganador() });
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        hora_inicio_text_box = new javax.swing.JTextField();
        hora_fin_text_box = new javax.swing.JTextField();
        duracion_partido_text_box = new javax.swing.JTextField();
        goles_local_text_box = new javax.swing.JTextField();
        partido_combo = new javax.swing.JComboBox<>();
        goles_rival_text_box = new javax.swing.JTextField();
        equipo_ganador_text_box = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        combo_buscar = new javax.swing.JComboBox<>();
        txt_buscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_actas = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setForeground(new java.awt.Color(57, 120, 25));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Datos:");

        jLabel4.setText("Fecha de emisión:");

        jLabel5.setText("Hora inicio partido:");

        jLabel6.setText("Hora fin partido:");

        jLabel7.setText("Partido:");

        jLabel9.setText("Duración partido:");

        jLabel10.setText("Goles equipo local:");

        jLabel11.setText("Goles equipo rival:");

        jLabel12.setText("Equipo ganador:");

        goles_local_text_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goles_local_text_boxActionPerformed(evt);
            }
        });
        goles_local_text_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                goles_local_text_boxKeyTyped(evt);
            }
        });

        partido_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                partido_comboItemStateChanged(evt);
            }
        });
        partido_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partido_comboActionPerformed(evt);
            }
        });

        goles_rival_text_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goles_rival_text_boxActionPerformed(evt);
            }
        });
        goles_rival_text_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                goles_rival_text_boxKeyTyped(evt);
            }
        });

        equipo_ganador_text_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipo_ganador_text_boxActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12))
                                .addGap(23, 23, 23)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(partido_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hora_fin_text_box)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hora_inicio_text_box))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(equipo_ganador_text_box, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(goles_rival_text_box)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 96, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goles_local_text_box)
                            .addComponent(duracion_partido_text_box))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hora_inicio_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hora_fin_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(partido_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(duracion_partido_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(goles_local_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(goles_rival_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equipo_ganador_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 430));

        jPanel3.setPreferredSize(new java.awt.Dimension(0, 400));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 2, 400));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Seleccione un registro para realizar una acción.");

        jLabel14.setText("Buscar por:");

        combo_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre de equipo", "Equipo ganador", "Duración del partido" }));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search(23).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        table_actas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_actas);

        jButton4.setBackground(new java.awt.Color(255, 255, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Editar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 51, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(combo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addGap(0, 294, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combo_buscar)
                        .addComponent(jLabel14)
                        .addComponent(txt_buscar))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(100, 100, 100))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 770, 420));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancha 4.0.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void equipo_ganador_text_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipo_ganador_text_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipo_ganador_text_boxActionPerformed

    private void goles_rival_text_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_goles_rival_text_boxKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_goles_rival_text_boxKeyTyped

    private void goles_rival_text_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goles_rival_text_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goles_rival_text_boxActionPerformed

    private void partido_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partido_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_partido_comboActionPerformed

    private void partido_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_partido_comboItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_partido_comboItemStateChanged

    private void goles_local_text_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_goles_local_text_boxKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_goles_local_text_boxKeyTyped

    private void goles_local_text_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goles_local_text_boxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goles_local_text_boxActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        eliminarActa();
    }// GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        editarActa();
    }// GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        limpiarTexts();
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        guardarActa();
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        if (txt_buscar.getText().isEmpty()) {
            cargarListadoActas();
        } else {
            String filtro = combo_buscar.getSelectedItem().toString();
            String texto = txt_buscar.getText().trim();

            filtrarTabla(filtro, texto);
        }
    }// GEN-LAST:event_jButton3ActionPerformed

    private void partido_comboItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_partido_comboItemStateChanged

    }// GEN-LAST:event_partido_comboItemStateChanged

    private void partido_comboActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_partido_comboActionPerformed
        System.out.print(partido_combo.getSelectedItem());
    }// GEN-LAST:event_partido_comboActionPerformed

    private void equipo_ganador_text_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_equipo_ganador_text_boxActionPerformed

    }// GEN-LAST:event_equipo_ganador_text_boxActionPerformed

    private void goles_local_text_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goles_local_text_boxActionPerformed

    }// GEN-LAST:event_goles_local_text_boxActionPerformed

    private void goles_rival_text_boxKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_goles_rival_text_boxKeyTyped
        if (evt.getKeyChar() >= 48 && evt.getKeyChar() <= 57) {
            String[] ganador;
            if (!(goles_local_text_box.getText().isEmpty()) && !(goles_rival_text_box.getText().isEmpty())) {
                if (Integer.parseInt(goles_local_text_box.getText()) > Integer
                        .parseInt(goles_rival_text_box.getText())) {
                    ganador = partido_combo.getSelectedItem().toString().split("VS");
                    equipo_ganador_text_box.setText(ganador[0]);
                }
            }
        } else {
            evt.consume();
        }
    }// GEN-LAST:event_goles_rival_text_boxKeyTyped

    private void goles_local_text_boxKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_goles_local_text_boxKeyTyped

        if (evt.getKeyChar() >= 48 && evt.getKeyChar() <= 57) {

        } else {
            evt.consume();
        }
    }// GEN-LAST:event_goles_local_text_boxKeyTyped

    private void goles_rival_text_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goles_rival_text_boxActionPerformed

    }// GEN-LAST:event_goles_rival_text_boxActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_buscar;
    private javax.swing.JTextField duracion_partido_text_box;
    private javax.swing.JTextField equipo_ganador_text_box;
    private javax.swing.JTextField goles_local_text_box;
    private javax.swing.JTextField goles_rival_text_box;
    private javax.swing.JTextField hora_fin_text_box;
    private javax.swing.JTextField hora_inicio_text_box;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> partido_combo;
    private javax.swing.JTable table_actas;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}

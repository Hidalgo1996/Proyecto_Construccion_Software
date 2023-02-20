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
import controlador.Controlador_partido;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modelo.Actas_partido;
import modelo.Partido;
import modelo.TipoBusqueda;

/**
 *
 * @author Hidalgo
 */
public class Inter_modulo_actas extends javax.swing.JInternalFrame {

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    Controlador_actas_partido controladorActas = new Controlador_actas_partido();
    Actas_partido acta_partido = new Actas_partido();
    DefaultTableModel modelo;
    Integer id_acta = 0;

    // LocalDateTime fechaEmision = LocalDateTime.now();
    // DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
    /**
     * Creates new form Inter_modulo_actas
     */
    public Inter_modulo_actas() {
        initComponents();
        formatearText();
        this.setTitle("ÁRBITRO: ACTAS DEL PARTIDO");
        setearTabla();
        cargarListadoActas();
        equipo_ganador_text_box.setEnabled(false);
        llenarCombo();

    }

    private void setearTabla() {
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

        jScrollPane1.setViewportView(table_actas);

        table_actas.getColumnModel().getColumn(0).setMaxWidth(20);
        table_actas.getColumnModel().getColumn(0).setPreferredWidth(20);
        table_actas.removeColumn(table_actas.getColumnModel().getColumn(1));
        table_actas.removeColumn(table_actas.getColumnModel().getColumn(5));
    }

    private boolean camposVacios() {
        return (hora_inicio_text_box.getText().isEmpty()
                || hora_fin_text_box.getText().isEmpty()
                || (!partido_combo.isEnabled() && partido_combo.getSelectedIndex() == 0)
                || duracion_partido_text_box.getText().isEmpty()
                || goles_rival_text_box.getText().isEmpty() || goles_local_text_box.getText().isEmpty()
                || fecha_emision.getDate() == null);
    }

    private boolean filaSelecionada() {
        return (table_actas.getSelectedRow() > -1);
    }

    /**
     * Formatea textbox hora inicio y hora fin
     */
    public void formatearText() {
        try {
            MaskFormatter maskHora = new MaskFormatter("##:##");
            hora_fin_text_box.setFormatterFactory(new DefaultFormatterFactory(maskHora));
            hora_inicio_text_box.setFormatterFactory(new DefaultFormatterFactory(maskHora));
        } catch (ParseException e) {
            System.out.println("error en parseo data" + e.getMessage());
        }
    }

    /**
     * Captura los datos dentro de los textbox y los guarda en la tabla acta
     * partido de la base de datos.
     *
     * @param void
     * @return void
     */
    public void guardarActa() {

        if (camposVacios()) {
            JOptionPane.showMessageDialog(null, "Llena todos los campos");
        } else {

            // Esto me permite capturar lo que hay en las cajas de texto y guardar en los
            // atributos de clase de actas_partido
            acta_partido.setFecha_emision(new java.sql.Date(fecha_emision.getDate().getTime()));
            acta_partido.setHora_inicio_partido(hora_inicio_text_box.getText());
            acta_partido.setHora_fin_partido(hora_fin_text_box.getText());
            acta_partido.setNombre_equipo_local("");
            acta_partido.setNombre_equipo_rival("");
            acta_partido.setDuracion_partido((duracion_partido_text_box.getText()));
            acta_partido.setGoles_equipo_rival(Integer.parseInt(goles_rival_text_box.getText()));
            acta_partido.setGoles_equipo_local(Integer.parseInt(goles_local_text_box.getText()));
            acta_partido.setEquipo_ganador(equipo_ganador_text_box.getText().trim());

            if (id_acta == 0) {
                Controlador_partido.cargarComboPartido(TipoBusqueda.ACTA).forEach((p) -> {
                    String partido = p.getClub_local().getNombre_equipo() + " VS "
                            + p.getClub_rival().getNombre_equipo();

                    if (partido.equalsIgnoreCase(partido_combo.getSelectedItem().toString())) {
                        acta_partido.setPartido(new Partido(p.getId_partido()));
                    }
                    return;
                });

                if (controladorActas.guardar(acta_partido)) {
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    limpiarTexts();

                } else {

                    JOptionPane.showMessageDialog(null, "Error de guardado, intentelo otra vez");
                }
            } else {
                acta_partido.setId_acta_partido(id_acta);
                String mensaje = controladorActas.actualizarActa(acta_partido);
                id_acta = 0;
                JOptionPane.showMessageDialog(null, mensaje);
            }

        }

        cargarListadoActas();
    }

    public void llenarCombo() {
        if (Controlador_partido.cargarComboPartido(TipoBusqueda.ACTA) != null) {
            partido_combo.removeAllItems();
            partido_combo.updateUI();
            partido_combo.addItem("Seleccione");
            for (Partido p : Controlador_partido.cargarComboPartido(TipoBusqueda.ACTA)) {
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
        id_acta = 0;
        hora_inicio_text_box.setValue("");
        hora_fin_text_box.setValue("");
        partido_combo.setSelectedIndex(0);
        duracion_partido_text_box.setText("");
        goles_local_text_box.setText("");
        goles_rival_text_box.setText("");
        equipo_ganador_text_box.setText("");
        fecha_emision.setDate(null);
        partido_combo.setEnabled(true);
        llenarCombo();
    }

    /**
     * Edicion de algun registro en especifico de un acta de partidos que ya ha
     * sido ingresada en base de datos.
     *
     * @param void
     * @return void
     */
    public void editarActa() {

        if (filaSelecionada()) {
            int fila = table_actas.getSelectedRow();
            id_acta = (int) (table_actas.getModel().getValueAt(fila, 1));
            /* System.out.println(id_acta); */
            try {
                String _partido = table_actas.getModel().getValueAt(fila, 5).toString();
                fecha_emision.setDate(formatoFecha.parse(table_actas.getModel().getValueAt(fila, 2).toString()));
                hora_inicio_text_box.setValue(table_actas.getModel().getValueAt(fila, 3).toString());
                hora_fin_text_box.setValue(table_actas.getModel().getValueAt(fila, 4).toString());
                duracion_partido_text_box.setText(table_actas.getModel().getValueAt(fila, 6).toString());
                goles_local_text_box.setText(table_actas.getModel().getValueAt(fila, 7).toString());
                goles_rival_text_box.setText(table_actas.getModel().getValueAt(fila, 8).toString());
                equipo_ganador_text_box.setText(table_actas.getModel().getValueAt(fila, 9).toString());
                partido_combo.addItem(_partido);
                partido_combo.setSelectedItem(_partido);
                partido_combo.setEnabled(false);
            } catch (ParseException ex) {
                Logger.getLogger(Inter_modulo_actas.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error parseo formato fecha");
            }
        }

    }

    /**
     * Borrado logico de un acta ingresada en la base de datos Lo pasa de activo
     * a inactivo.
     *
     * @param void
     * @return void
     */
    public void eliminarActa() {

        if (filaSelecionada()) {

            int fila = table_actas.getSelectedRow();
            int id = Integer.parseInt(table_actas.getModel().getValueAt(fila, 1).toString());

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
     * Carga todo el listado de las actas de la base de datos y lo muestra en la
     * ventana actual.
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
     * Filtra datos especificos de la tabla acta de partidos en base al tipo de
     * busqueda que se hace.
     *
     * @param buscarPor, texto
     * @return void
     */
    public void filtrarTabla(String buscarPor, String texto) {

        List<Actas_partido> listaFiltrada = new ArrayList<Actas_partido>();
        /*
         * System.out.println("Filtro" + "\n");
         * System.out.println(buscarPor + "\n");
         * System.out.println(texto + "\n");
         */

        if (buscarPor.equalsIgnoreCase("Equipo ganador")) {
            for (Actas_partido e : controladorActas.listarActas()) {
                if (e.getEquipo_ganador().toString().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(e);
                }
            }
        } else if (buscarPor.equalsIgnoreCase("Partido")) {
            controladorActas.listarActas().forEach((e) -> {
                String partido = e.getPartido().getClub_local().getNombre_equipo().concat(" VS ")
                        .concat(e.getPartido().getClub_rival().getNombre_equipo());
                if (partido.toLowerCase().contains(texto.toLowerCase())) {
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
     * Setea equipo ganador en textbox segun mayor cantidad de goles
     */
    public void setearEquipoGanador() {
        // Separacion de Equipos del combo
        String equipo[] = partido_combo.getSelectedItem().toString().split("VS");

        if (!(goles_local_text_box.getText().isEmpty() || goles_rival_text_box.getText().isEmpty())
                && partido_combo.getSelectedIndex() > 0) {
            /*
             * System.out.println(equipo[0]);
             * System.out.println(equipo[1]);
             */
            if (Integer.parseInt(goles_local_text_box.getText().trim()) > 0
                    || Integer.parseInt(goles_rival_text_box.getText().trim()) > 0) {
                if (Integer.parseInt(goles_local_text_box.getText().trim()) > Integer
                        .parseInt(goles_rival_text_box.getText().trim())) {
                    equipo_ganador_text_box.setText(equipo[0].trim());
                } else if (Integer.parseInt(goles_local_text_box.getText().trim()) < Integer
                        .parseInt(goles_rival_text_box.getText().trim())) {
                    equipo_ganador_text_box.setText(equipo[1].trim());
                } else {
                    equipo_ganador_text_box.setText("Empate");
                }
            }
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
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
        duracion_partido_text_box = new javax.swing.JTextField();
        goles_local_text_box = new javax.swing.JTextField();
        partido_combo = new javax.swing.JComboBox<>();
        goles_rival_text_box = new javax.swing.JTextField();
        equipo_ganador_text_box = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        fecha_emision = new com.toedter.calendar.JDateChooser();
        hora_fin_text_box = new javax.swing.JFormattedTextField();
        hora_inicio_text_box = new javax.swing.JFormattedTextField();
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

        duracion_partido_text_box.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        duracion_partido_text_box.setEnabled(false);

        goles_local_text_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                goles_local_text_boxKeyReleased(evt);
            }

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

        goles_rival_text_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                goles_rival_text_boxKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                goles_rival_text_boxKeyTyped(evt);
            }
        });

        equipo_ganador_text_box.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

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

        hora_fin_text_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hora_fin_text_boxKeyReleased(evt);
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
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel11,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addGap(8, 8, 8))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel12))
                                                                .addGap(23, 23, 23)))
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(11, 11, 11)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(partido_combo, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(fecha_emision,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(11, 11, 11)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(equipo_ganador_text_box,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(goles_rival_text_box)))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(hora_fin_text_box,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                100, Short.MAX_VALUE)
                                                                        .addComponent(hora_inicio_text_box))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(53, 53, 53)
                                                                .addComponent(jButton1,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 94,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(jButton2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 92,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel4))
                                                .addGap(0, 96, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel10))
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(goles_local_text_box)
                                                        .addComponent(duracion_partido_text_box))))
                                .addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(fecha_emision, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(hora_inicio_text_box,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(partido_combo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(duracion_partido_text_box,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10)
                                                        .addComponent(goles_local_text_box,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(goles_rival_text_box,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(equipo_ganador_text_box,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel12))
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton2)
                                                        .addComponent(jButton1)))
                                        .addComponent(hora_fin_text_box, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(26, Short.MAX_VALUE)));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 430));

        jPanel3.setPreferredSize(new java.awt.Dimension(0, 400));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 2, Short.MAX_VALUE));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 2, 400));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Seleccione un registro para realizar una acción.");

        jLabel14.setText("Buscar por:");

        combo_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Partido", "Equipo ganador" }));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search(23).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        table_actas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
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
                                                .addGroup(jPanel4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel13))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(42, 42, 42)
                                                                .addComponent(jLabel14)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(combo_buscar,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txt_buscar,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 168,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButton3)))
                                                .addGap(0, 287, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1)))
                                .addContainerGap())
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(combo_buscar)
                                                .addComponent(jLabel14)
                                                .addComponent(txt_buscar))
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton4)
                                        .addComponent(jButton5))
                                .addGap(100, 100, 100)));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 770, 420));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancha 4.0.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goles_local_text_boxKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_goles_local_text_boxKeyReleased
        setearEquipoGanador();
    }// GEN-LAST:event_goles_local_text_boxKeyReleased

    private void goles_rival_text_boxKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_goles_rival_text_boxKeyReleased
        setearEquipoGanador();
    }// GEN-LAST:event_goles_rival_text_boxKeyReleased

    private void hora_fin_text_boxKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_hora_fin_text_boxKeyReleased
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        if (hora_fin_text_box.getValue() != null && hora_inicio_text_box.getValue() != null
                && hora_fin_text_box.getValue().toString().length() >= 4) {
            try {
                LocalTime localTime = (new Time(format.parse(hora_fin_text_box.getValue().toString()).getTime())
                        .toLocalTime());
                String[] hour = hora_inicio_text_box.getValue().toString().split(":");
                localTime = localTime.minusHours(Integer.parseInt(hour[0]));
                localTime = localTime.minusMinutes(Integer.parseInt(hour[1]));
                duracion_partido_text_box.setText(localTime.toString());
            } catch (ParseException ex) {
                Logger.getLogger(Inter_modulo_actas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }// GEN-LAST:event_hora_fin_text_boxKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        limpiarTexts();
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        guardarActa();
    }// GEN-LAST:event_jButton1ActionPerformed

    private void goles_rival_text_boxKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_goles_rival_text_boxKeyTyped
        if (evt.getKeyChar() >= 48 && evt.getKeyChar() <= 57) {

        } else {
            evt.consume();
        }
    }// GEN-LAST:event_goles_rival_text_boxKeyTyped

    private void partido_comboActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_partido_comboActionPerformed
    }// GEN-LAST:event_partido_comboActionPerformed

    private void partido_comboItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_partido_comboItemStateChanged
        if (partido_combo.getSelectedIndex() == 0 && id_acta == 0) {
            equipo_ganador_text_box.setText("");
            goles_local_text_box.setText("");
            goles_rival_text_box.setText("");

        }
    }// GEN-LAST:event_partido_comboItemStateChanged

    private void goles_local_text_boxKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_goles_local_text_boxKeyTyped
        if (evt.getKeyChar() >= 48 && evt.getKeyChar() <= 57) {

        } else {
            evt.consume();
        }
    }// GEN-LAST:event_goles_local_text_boxKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        eliminarActa();
    }// GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        editarActa();
    }// GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        if (txt_buscar.getText().isEmpty()) {
            cargarListadoActas();
        } else {
            String filtro = combo_buscar.getSelectedItem().toString();
            String texto = txt_buscar.getText().trim();

            filtrarTabla(filtro, texto);
        }
    }// GEN-LAST:event_jButton3ActionPerformed

    private void equipo_ganador_text_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_equipo_ganador_text_boxActionPerformed

    }// GEN-LAST:event_equipo_ganador_text_boxActionPerformed

    private void goles_local_text_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goles_local_text_boxActionPerformed

    }// GEN-LAST:event_goles_local_text_boxActionPerformed

    private void goles_rival_text_boxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goles_rival_text_boxActionPerformed

    }// GEN-LAST:event_goles_rival_text_boxActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_buscar;
    private javax.swing.JTextField duracion_partido_text_box;
    private javax.swing.JTextField equipo_ganador_text_box;
    private com.toedter.calendar.JDateChooser fecha_emision;
    private javax.swing.JTextField goles_local_text_box;
    private javax.swing.JTextField goles_rival_text_box;
    private javax.swing.JFormattedTextField hora_fin_text_box;
    private javax.swing.JFormattedTextField hora_inicio_text_box;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Modelo.Pelicula;
import Modelo.Proyeccion;
import Modelo.Sala;
import Persistencia.PeliculaData;
import Persistencia.ProyeccionData;
import Persistencia.SalaData;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabri
 */
public class VistaProyeccion extends javax.swing.JInternalFrame {
    private final ProyeccionData proyData = new ProyeccionData();
    private final PeliculaData peliData = new PeliculaData();
    private final SalaData salaData = new SalaData();
    private final DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form VistaProyeccion
     */
    public VistaProyeccion() {
        initComponents();
         armarTabla();
        limpiarCampos();
        //cargarTabla();
        
        jguardar.setEnabled(false);
    }
    private void armarTabla() {
        modelo.addColumn("ID Función");
        modelo.addColumn("Película");
        modelo.addColumn("Idioma");
        modelo.addColumn("3D");
        modelo.addColumn("Subtitulada");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Fin");
        modelo.addColumn("Lugares");
        modelo.addColumn("Sala");
        modelo.addColumn("Precio");
        jTable1.setModel(modelo);
    }

    private void limpiarCampos() {
        jidfuncion.setText("");
        jpelicula.setText("");
        jidioma.setText("");
        jes3d.setText("");
        jessubtitulada.setText("");
        jhorainicio.setText("");
        jhorafin.setText("");
        jlugaresdisponibles.setText("");
        jsala.setText("");
        jprecio.setText("");
    }

    private void limpiarTabla() {
        modelo.setRowCount(0);
    }

    private void cargarTabla() {
        limpiarTabla();
        for (Proyeccion p : proyData.listarProyecciones()) {
            modelo.addRow(new Object[]{
                p.getIdFuncion(),
                p.getPelicula().getTitulo(),
                p.getIdioma(),
                p.isEs3D(),
                p.isSubtitulada(),
                p.getHorInicio(),
                p.getHoraFin(),
                p.getCantidadLugaresDisponibles(),
                p.getSala().getNroSala(),
                p.getPrecio()
            });
        }
    }
private void buscarPelicula() {
    String titulo = jpelicula.getText().trim();

    if (titulo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el nombre de una película para buscar.");
        return;
    }

    Pelicula peli = peliData.buscarPeliculaPorTitulo(titulo);

    if (peli == null) {
        JOptionPane.showMessageDialog(this, "No se encontró una película con ese título.");
        limpiarTabla();
        return;
    }

    limpiarTabla();
    for (Proyeccion p : proyData.listarProyecciones()) {
        if (p.getPelicula().getIdPelicula() == peli.getIdPelicula()) {
            modelo.addRow(new Object[]{
                p.getIdFuncion(),
                p.getPelicula().getTitulo(),
                p.getIdioma(),
                p.isEs3D(),
                p.isSubtitulada(),
                p.getHorInicio(),
                p.getHoraFin(),
                p.getCantidadLugaresDisponibles(),
                p.getSala().getNroSala(),
                p.getPrecio()
            });
        }
    }
}
private void buscarPorSala() {
    String nroSalaTxt = jsala.getText().trim();

    if (nroSalaTxt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el número de sala para buscar.");
        return;
    }

    try {
        int nroSala = Integer.parseInt(nroSalaTxt);
        Sala sala = salaData.buscarSala(nroSala);

        if (sala == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una sala con ese número.");
            limpiarTabla();
            return;
        }

        limpiarTabla();
        for (Proyeccion p : proyData.listarProyecciones()) {
            if (p.getSala().getNroSala()== sala.getNroSala()) {
                modelo.addRow(new Object[]{
                    p.getIdFuncion(),
                    p.getPelicula().getTitulo(),
                    p.getIdioma(),
                    p.isEs3D(),
                    p.isSubtitulada(),
                    p.getHorInicio(),
                    p.getHoraFin(),
                    p.getCantidadLugaresDisponibles(),
                    p.getSala().getNroSala(),
                    p.getPrecio()
                });
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El número de sala debe ser un valor numérico.");
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jagregar = new javax.swing.JButton();
        jguardar = new javax.swing.JButton();
        jactualizar = new javax.swing.JButton();
        jborrar = new javax.swing.JButton();
        jbuscarporid = new javax.swing.JButton();
        jlistartodo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jidfuncion = new javax.swing.JTextField();
        jpelicula = new javax.swing.JTextField();
        jidioma = new javax.swing.JTextField();
        jes3d = new javax.swing.JTextField();
        jessubtitulada = new javax.swing.JTextField();
        jlugaresdisponibles = new javax.swing.JTextField();
        jhorafin = new javax.swing.JTextField();
        jhorainicio = new javax.swing.JTextField();
        jsala = new javax.swing.JTextField();
        jprecio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jbuscarporpelicula = new javax.swing.JButton();
        jbuscarporsala = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Funcion", "Pelicula", "Idioma", "Es 3D", "Subtitulada", "Inicio", "Fin", "Lugares Dispon", "Sala", "Precio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jagregar.setText("Agregar");
        jagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jagregarActionPerformed(evt);
            }
        });

        jguardar.setText("Guardar");
        jguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jguardarActionPerformed(evt);
            }
        });

        jactualizar.setText("Actualizar");
        jactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jactualizarActionPerformed(evt);
            }
        });

        jborrar.setText("Borrar");
        jborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jborrarActionPerformed(evt);
            }
        });

        jbuscarporid.setText("Buscar por ID");
        jbuscarporid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuscarporidActionPerformed(evt);
            }
        });

        jlistartodo.setText("Listar Todo");
        jlistartodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlistartodoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("id Funcion:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Pelicula:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Idioma:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Es 3D:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Es Subtitulada:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Hora de Inicio:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Hora Fin:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Lugares Disponibles:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Sala:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Precio:");

        jsala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsalaActionPerformed(evt);
            }
        });

        jprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprecioActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel11.setText("Gestion Proyeccion");

        jbuscarporpelicula.setText("Buscar por Pelicula");
        jbuscarporpelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuscarporpeliculaActionPerformed(evt);
            }
        });

        jbuscarporsala.setText("Buscar por Sala");
        jbuscarporsala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuscarporsalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jessubtitulada, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jes3d, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                    .addComponent(jidioma)
                                    .addComponent(jpelicula)
                                    .addComponent(jidfuncion))))
                        .addGap(223, 223, 223)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jhorafin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jhorainicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlugaresdisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jsala, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlistartodo))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jLabel11)))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jagregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jguardar)
                .addGap(36, 36, 36)
                .addComponent(jactualizar)
                .addGap(44, 44, 44)
                .addComponent(jborrar)
                .addGap(53, 53, 53)
                .addComponent(jbuscarporid)
                .addGap(72, 72, 72)
                .addComponent(jbuscarporsala)
                .addGap(62, 62, 62)
                .addComponent(jbuscarporpelicula)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jidfuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jpelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jhorafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jidioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlugaresdisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jes3d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jessubtitulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jhorainicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)
                        .addComponent(jsala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jlistartodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jagregar)
                    .addComponent(jguardar)
                    .addComponent(jactualizar)
                    .addComponent(jborrar)
                    .addComponent(jbuscarporid)
                    .addComponent(jbuscarporpelicula)
                    .addComponent(jbuscarporsala))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jsalaActionPerformed

    private void jprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jprecioActionPerformed

    private void jbuscarporsalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuscarporsalaActionPerformed
        // TODO add your handling code here:
        buscarPorSala();
    }//GEN-LAST:event_jbuscarporsalaActionPerformed

    private void jguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jguardarActionPerformed
        // TODO add your handling code here:
        System.out.println("Botón GUARDAR presionado");
        try {
            Proyeccion p = new Proyeccion();

            // Película y Sala se buscan desde BD
            String nombrePeli = jpelicula.getText();
            Pelicula peli = peliData.buscarPeliculaPorTitulo(nombrePeli);
            int idSala = Integer.parseInt(jsala.getText());
            Sala sala = salaData.buscarSala(idSala);

            p.setPelicula(peli);
            p.setSala(sala);
            p.setIdioma(jidioma.getText());
            p.setEs3D(Boolean.parseBoolean(jes3d.getText()));
            p.setSubtitulada(Boolean.parseBoolean(jessubtitulada.getText()));

            try {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
                p.setHorInicio(LocalTime.parse(jhorainicio.getText(), formato));
                p.setHoraFin(LocalTime.parse(jhorafin.getText(), formato));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de hora incorrecto. Usa HH:mm (por ej. 14:30)");
                return;
            }

            p.setCantidadLugaresDisponibles(Integer.parseInt(jlugaresdisponibles.getText()));
            p.setPrecio(Double.parseDouble(jprecio.getText()));

            proyData.ingresarProyeccion(p);
            JOptionPane.showMessageDialog(this, "Proyección guardada correctamente.");
            limpiarCampos();
            cargarTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en formato numérico: " + e.getMessage());
        }
        jactualizar.setEnabled(true);
        jborrar.setEnabled(true);
        jbuscarporid.setEnabled(true);
        jbuscarporsala.setEnabled(true);
        jbuscarporpelicula.setEnabled(true);
        jbuscarporpelicula.setEnabled(true);
         jidfuncion.setEnabled(true);
        jguardar.setEnabled(false);
    }//GEN-LAST:event_jguardarActionPerformed

    private void jactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jactualizarActionPerformed
        // TODO add your handling code here:
        try {
        // 1. Obtener ID de la proyección a actualizar
        int id = Integer.parseInt(jidfuncion.getText().trim());
        Proyeccion p = proyData.buscarProyeccion(id);

        if (p == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la proyección con ese ID");
            return;
        }

        // 2. Obtener los nuevos datos desde los campos
        String textoPeli = jpelicula.getText().trim();
    Pelicula peli = peliData.buscarPeliculaPorTitulo(textoPeli);

    if (peli == null) {
        JOptionPane.showMessageDialog(this, "No se encontró la película: " + textoPeli);
        return;
    }
//hola



if (peli == null) {
    JOptionPane.showMessageDialog(this, "No se encontró la película: " + textoPeli);
    return;
}

        if (peli == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la película: " + textoPeli);
            return;
        }

        int idSala = Integer.parseInt(jsala.getText().trim());
        Sala sala = salaData.buscarSala(idSala);
        if (sala == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la sala con ID " + idSala);
            return;
        }

        // 3. Asignar los nuevos valores al objeto existente
        p.setPelicula(peli);
        p.setSala(sala);
        p.setIdioma(jidioma.getText().trim());
        p.setEs3D(Boolean.parseBoolean(jes3d.getText().trim()));
        p.setSubtitulada(Boolean.parseBoolean(jessubtitulada.getText().trim()));
        p.setCantidadLugaresDisponibles(Integer.parseInt(jlugaresdisponibles.getText().trim()));
        p.setPrecio(Double.parseDouble(jprecio.getText().trim()));

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        p.setHorInicio(LocalTime.parse(jhorainicio.getText().trim(), formato));
        p.setHoraFin(LocalTime.parse(jhorafin.getText().trim(), formato));

        // 4. Actualizar en la base de datos
        proyData.modificarProyeccion(p);

        JOptionPane.showMessageDialog(this, "Proyección actualizada correctamente.");
        cargarTabla();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error de formato numérico: " + e.getMessage());
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de hora incorrecto. Usa HH:mm (por ej. 14:30)");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
    }
    }//GEN-LAST:event_jactualizarActionPerformed

    private void jborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jborrarActionPerformed
        // TODO add your handling code here:
            try {
        int id = Integer.parseInt(jidfuncion.getText());
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Seguro que querés borrar la proyección con ID " + id + "?", 
            "Confirmar borrado", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            proyData.eliminarProyeccion(id);
            JOptionPane.showMessageDialog(this, "Proyección eliminada correctamente.");
            limpiarCampos();
            cargarTabla();
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Debes ingresar un ID de función válido para borrar.");
    }
        
    }//GEN-LAST:event_jborrarActionPerformed

    private void jbuscarporidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuscarporidActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(jidfuncion.getText());
            Proyeccion p = proyData.buscarProyeccion(id);
            if (p != null) {
                limpiarTabla();
                modelo.addRow(new Object[]{
                    p.getIdFuncion(),
                    p.getPelicula().getTitulo(),
                    p.getIdioma(),
                    p.isEs3D(),
                    p.isSubtitulada(),
                    p.getHorInicio(),
                    p.getHoraFin(),
                    p.getCantidadLugaresDisponibles(),
                    p.getSala().getNroSala(),
                    p.getPrecio()
                });
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró proyección con ese ID");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jbuscarporidActionPerformed

    private void jlistartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlistartodoActionPerformed
        // TODO add your handling code here:
        cargarTabla();
    }//GEN-LAST:event_jlistartodoActionPerformed

    private void jbuscarporpeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuscarporpeliculaActionPerformed
        // TODO add your handling code here:
         buscarPelicula();
    }//GEN-LAST:event_jbuscarporpeliculaActionPerformed

    private void jagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jagregarActionPerformed
        // TODO add your handling code here:
        
        jactualizar.setEnabled(false);
        jborrar.setEnabled(false);
        jbuscarporid.setEnabled(false);
        jbuscarporsala.setEnabled(false);
        jbuscarporpelicula.setEnabled(false);
        jbuscarporpelicula.setEnabled(false);
        jidfuncion.setEnabled(false);
        jguardar.setEnabled(true);
        
          

    }//GEN-LAST:event_jagregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jactualizar;
    private javax.swing.JButton jagregar;
    private javax.swing.JButton jborrar;
    private javax.swing.JButton jbuscarporid;
    private javax.swing.JButton jbuscarporpelicula;
    private javax.swing.JButton jbuscarporsala;
    private javax.swing.JTextField jes3d;
    private javax.swing.JTextField jessubtitulada;
    private javax.swing.JButton jguardar;
    private javax.swing.JTextField jhorafin;
    private javax.swing.JTextField jhorainicio;
    private javax.swing.JTextField jidfuncion;
    private javax.swing.JTextField jidioma;
    private javax.swing.JButton jlistartodo;
    private javax.swing.JTextField jlugaresdisponibles;
    private javax.swing.JTextField jpelicula;
    private javax.swing.JTextField jprecio;
    private javax.swing.JTextField jsala;
    // End of variables declaration//GEN-END:variables
}

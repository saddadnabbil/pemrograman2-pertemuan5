/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tugaspertemuan5saddadnabbil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saddadnabbil
 */
public class LatihanSoal extends javax.swing.JFrame {
    private DefaultTableModel model;
    private int selectedRowIndex = -1;
    int xx, xy;
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel tabModel;
    Main main = new Main();
    Connection cn = main.getKoneksi();
    
    private int tipeId = 0;
    private int areaId = 0;

        
    /**
     * Creates new form LatihanSoal
     */
    public LatihanSoal() {
        initComponents();
        dataTable();
        showTablePenjualan("");
    }
    
    private void dataTable() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Pemesan");
        model.addColumn("ID Area");
        model.addColumn("Area");
        model.addColumn("ID Tipe Rumah");
        model.addColumn("Tipe Rumah");
        model.addColumn("Luas Tanah");
        model.addColumn("Harga");
        model.addColumn("Lama Cicilan");
        model.addColumn("Cicilan /bulan");
        dataTable.setModel(model);
    }
    
    public void showTablePenjualan (String where) {
        try {
            st = cn.createStatement();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            rs = st.executeQuery(""
                    + "SELECT p.`id`\n" +
                    "	,p.`nama_pemesan`\n" +
                    "	,p.`id_area_rumah`\n" +
                    "	,p.`id_tipe_rumah`\n" +
                    "	,p.`harga`\n" +
                    "	,p.`lama_cicilan`\n" +
                    "	,p.`cicilan_per_bulan`\n" +
                    "FROM penjualan_rumah AS p\n" +
                    "ORDER BY p.`id`");

            int jumlahRumahTerjual = 0;
            int totalRumahTerjual = 0;
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            
            while (rs.next()) {
                String areaRumah = "";
                String idAreaRumah = rs.getString("id_area_rumah");
                if ("1".equals(idAreaRumah)) {
                    areaRumah = "Bougenvile";
                } else if ("2".equals(idAreaRumah)) {
                    areaRumah = "Melati";
                } else if ("3".equals(idAreaRumah)) {
                    areaRumah = "Flamboyan";
                }

                String tipeRumah = "";
                String luasTanah = "";
                String idTipeRumah = rs.getString("id_tipe_rumah");
                if ("1".equals(idTipeRumah)) {
                    tipeRumah = "Tipe-36";
                    luasTanah = "90";
                } else if ("2".equals(idTipeRumah)) {
                    tipeRumah = "Tipe-45";
                    luasTanah = "120";
                } else if ("3".equals(idTipeRumah)) {
                    tipeRumah = "Tipe-54";
                    luasTanah = "140";
                }  
                
                Object[] data = {
                    rs.getString("id"),
                    rs.getString("nama_pemesan"),
                    rs.getString("id_area_rumah"),
                    areaRumah,
                    rs.getString("id_tipe_rumah"),
                    tipeRumah,
                    luasTanah,
                    rs.getString("harga"),
                    rs.getString("lama_cicilan"),
                    rs.getString("cicilan_per_bulan")
                };

                model.addRow(data);
                jumlahRumahTerjual++;
                totalRumahTerjual += Integer.parseInt(rs.getString("harga").replaceAll(",", ""));
            }
            
            jumlahRumahTerjualObj.setText(String.valueOf(jumlahRumahTerjual));
            totalPenjualanRumahObj.setText(decimalFormat.format(totalRumahTerjual));
        } catch(Exception e) {
          e.printStackTrace();
        }
    }
    
    private void clearData() {
        tipeId = 0;
        areaId = 0;
        namaPemesanObj.setText("");
        hargaTanahObj.setText("");
        hargaBangunanObj.setText("");
        luasTanahAsliObj.setText("");
        luasTanahTersediaObj.setText("");
        hargaObj.setText("");
        dpObj.setText("");
        lamaCicilanObj.setText("");
        ppnObj.setText("");
        cicilanObj.setText("");
        setujuCheckboxObj.setSelected(false);
        areaObj.setSelectedIndex(0);
    }
    
    private void setArea(int index) {
        if (index == 1) {
            hargaTanahObj.setText("500000");
            hargaBangunanObj.setText("90000000");
            areaId = 1;
        } else if (index == 2) {
            hargaTanahObj.setText("600000");
            hargaBangunanObj.setText("120000000");
            areaId = 2;
        } else if (index == 3) {
            hargaTanahObj.setText("700000");
            hargaBangunanObj.setText("150000000");
            areaId = 3;
        } else {
            hargaBangunanObj.setText("");
            hargaBangunanObj.setText("");
            areaId = 0;
        }
    }
    
    private void setTipeRumah(int luas) {
        if (luas == 36) {
            setLuasTanahAsli(90);
            tipeId = 1;
        } else if (luas == 45) {
            setLuasTanahAsli(120);
            tipeId = 2;
        } else if (luas == 54) {
            setLuasTanahAsli(140);
            tipeId = 3;
        }
    }
    
    private void setTipe(int index) {
        if (index == 1) {
            setTipe36();
        } else if (index == 2) {
            setTipe45();
        } else if (index == 3) {
            setTipe54();
        } else {
            tipeId = 0;
        }
    }
    
    private void setTipe36() {
        setLuasTanahAsli("90");
        tipeId = 1;
    }
    
    private void setTipe45() {
        setLuasTanahAsli("120");
        tipeId = 2;
    }
    
    private void setTipe54() {
        setLuasTanahAsli("140");
        tipeId = 3;
    }
    
    private void setLuasTanahAsli(String tipe) {
        luasTanahAsliObj.setText(tipe);
    }

    private void setLuasTanahAsli(int luas) {
        try {
            luasTanahAsliObj.setText(Integer.toString(luas));
        } catch (NumberFormatException e) {
            System.err.println("Invalid luas: " + luas);
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

        tipeRumah = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        namaPemesanObj = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        hargaTanahObj = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        areaObj = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        hargaBangunanObj = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tipe36 = new javax.swing.JRadioButton();
        tipe45 = new javax.swing.JRadioButton();
        tipe54 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        luasTanahAsliObj = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        luasTanahTersediaObj = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        hargaObj = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dpObj = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lamaCicilanObj = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ppnObj = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cicilanObj = new javax.swing.JTextField();
        prosesButton = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        totalPenjualanRumahObj = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jumlahRumahTerjualObj = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        setujuCheckboxObj = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Menu Penjualan Rumah");

        jLabel2.setText("Nama Pemesan");

        jLabel3.setText("Harga Tanah /m2");

        jLabel4.setText("Area");

        areaObj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=== Pilih Area ===", "Bougenvile", "Melati ", "Flamboyan" }));
        areaObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaObjActionPerformed(evt);
            }
        });

        jLabel5.setText("Harga Bangunan");

        jLabel6.setText("Tipe Rumah");

        tipeRumah.add(tipe36);
        tipe36.setText("Tipe-36");
        tipe36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipe36ActionPerformed(evt);
            }
        });

        tipeRumah.add(tipe45);
        tipe45.setText("Tipe-45");
        tipe45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipe45ActionPerformed(evt);
            }
        });

        tipeRumah.add(tipe54);
        tipe54.setText("Tipe-54");
        tipe54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipe54ActionPerformed(evt);
            }
        });

        jLabel7.setText("Luas Tanah Asli");

        jLabel8.setText("Luas Tanah Tersedia");

        jLabel9.setText("Harga");

        jLabel10.setText("Dp");

        jLabel11.setText("Lama Cicilan");

        jLabel12.setText("/bulan");

        jLabel13.setText("PPN (10%)");

        jLabel14.setText("Cicilan /bulan");

        prosesButton.setBackground(new java.awt.Color(255, 204, 51));
        prosesButton.setText("Proses");
        prosesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesButtonActionPerformed(evt);
            }
        });

        simpanButton.setBackground(new java.awt.Color(102, 255, 102));
        simpanButton.setText("Simpan");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        clearButton.setBackground(new java.awt.Color(255, 51, 51));
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dataTable);

        jLabel15.setText("Jumlah Rumah Terjual");

        totalPenjualanRumahObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalPenjualanRumahObjActionPerformed(evt);
            }
        });

        jLabel16.setText("Total Penjualan Rumah");

        jumlahRumahTerjualObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahRumahTerjualObjActionPerformed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tugaspertemuan5saddadnabbil/download.jpeg"))); // NOI18N

        setujuCheckboxObj.setText("Setuju");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(areaObj, javax.swing.GroupLayout.Alignment.LEADING, 0, 181, Short.MAX_VALUE)
                            .addComponent(namaPemesanObj, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hargaTanahObj)))
                    .addComponent(jLabel17))
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(prosesButton)
                        .addGap(18, 18, 18)
                        .addComponent(simpanButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(tipe36)
                            .addComponent(tipe45)
                            .addComponent(tipe54)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cicilanObj)
                            .addComponent(ppnObj)
                            .addComponent(lamaCicilanObj)
                            .addComponent(dpObj)
                            .addComponent(hargaBangunanObj)
                            .addComponent(luasTanahAsliObj)
                            .addComponent(luasTanahTersediaObj)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(0, 60, Short.MAX_VALUE))
                            .addComponent(hargaObj, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(setujuCheckboxObj)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jumlahRumahTerjualObj, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(totalPenjualanRumahObj, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(hargaBangunanObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipe36)
                            .addComponent(luasTanahAsliObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(namaPemesanObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(areaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(hargaTanahObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipe45)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipe54)
                    .addComponent(luasTanahTersediaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(dpObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(lamaCicilanObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(2, 2, 2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(hargaObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(ppnObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cicilanObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(setujuCheckboxObj))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prosesButton)
                    .addComponent(simpanButton)
                    .addComponent(clearButton))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(totalPenjualanRumahObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jumlahRumahTerjualObj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        // TODO add your handling code here:
        if (setujuCheckboxObj.isSelected()) {
            try {
            if (tipeId > 0) {
                if (areaId > 0) { 
                String queryInsert = "INSERT INTO penjualan_rumah (nama_pemesan, luas_tanah_tersedia, harga, dp, lama_cicilan, ppn, cicilan_per_bulan, id_area_rumah, id_tipe_rumah) VALUES('"
                    + namaPemesanObj.getText() + "', '"
                    + luasTanahTersediaObj.getText() + "', '"
                    + hargaObj.getText() + "', '"
                    + dpObj.getText() + "', '"
                    + lamaCicilanObj.getText() + "', '"
                    + ppnObj.getText() + "', '"
                    + cicilanObj.getText() + "', "
                    + areaId + ","
                    + tipeId + ");";
                st = cn.createStatement();
                st.executeUpdate(queryInsert);
                JOptionPane.showMessageDialog(null, "Simpan Berhasil");
                showTablePenjualan("");
                clearData();
                } else {
                    JOptionPane.showMessageDialog(this, "Area Tidak Boleh Kosong!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tipe Tidak Boleh Kosong!");
            }
            
          } catch (Exception e) {
            e.printStackTrace();
          }
        } else {
            JOptionPane.showMessageDialog(this, "Mohon Setujui Terlebih Dahulu!");
        }
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearButtonActionPerformed

    private void totalPenjualanRumahObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalPenjualanRumahObjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalPenjualanRumahObjActionPerformed

    private void jumlahRumahTerjualObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahRumahTerjualObjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahRumahTerjualObjActionPerformed

    private void tipe36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipe36ActionPerformed
        // TODO add your handling code here:
        setTipeRumah(36);
    }//GEN-LAST:event_tipe36ActionPerformed

    private void tipe45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipe45ActionPerformed
        // TODO add your handling code here:
        setTipeRumah(45);
    }//GEN-LAST:event_tipe45ActionPerformed

    private void tipe54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipe54ActionPerformed
        // TODO add your handling code here:
        setTipeRumah(54);
    }//GEN-LAST:event_tipe54ActionPerformed

    private void areaObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaObjActionPerformed
        // TODO add your handling code here:
        setArea(areaObj.getSelectedIndex());
    }//GEN-LAST:event_areaObjActionPerformed

    private void prosesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesButtonActionPerformed
        // TODO add your handling code here:
        DecimalFormat df = new DecimalFormat("#,###.##");

        String luasTanahAsliStr = luasTanahAsliObj.getText().trim().equals("") ? "0" : luasTanahAsliObj.getText();
        String luasTanahTersedia = luasTanahTersediaObj.getText().trim().equals("") ? "0" : luasTanahTersediaObj.getText();
        String hargaTanahStr = hargaTanahObj.getText().trim().equals("") ? "0" : hargaTanahObj.getText();
        String hargaBangunanStr = hargaBangunanObj.getText().trim().equals("") ? "0" : hargaBangunanObj.getText();
        String dpStr = dpObj.getText().trim().equals("") ? "0" : dpObj.getText();
        String lamaCicilanStr = lamaCicilanObj.getText().trim().equals("") ? "0" : lamaCicilanObj.getText();

        Double lta = Double.parseDouble(luasTanahAsliStr);
        Double ltt = Double.parseDouble(luasTanahTersedia);
        Double ht = Double.parseDouble(hargaTanahStr);
        Double hb = Double.parseDouble(hargaBangunanStr);
        Double dpDouble = Double.parseDouble(dpStr);
        Double lc = Double.parseDouble(lamaCicilanStr);

        if (dpDouble == 0 || lc == 0) {
            JOptionPane.showMessageDialog(null, "Mohon isi nilai DP dan Lama Cicilan");
        } else {
            Double hargaCount = (lta * ht) + hb;
            hargaObj.setText(df.format(hargaCount));

            Double ppnCount = 10 * hargaCount / 100;
            ppnObj.setText(df.format(ppnCount));

            Double cicilanCount = (hargaCount + ppnCount - dpDouble) / lc;
            cicilanObj.setText(df.format(cicilanCount));
        }
    }//GEN-LAST:event_prosesButtonActionPerformed

    private void dataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataTableMouseClicked
        // TODO add your handling code here:
        selectedRowIndex = dataTable.getSelectedRow();

        // Display data from selected row in text fields
        namaPemesanObj.setText(dataTable.getValueAt(selectedRowIndex, 1).toString());
        luasTanahTersediaObj.setText(dataTable.getValueAt(selectedRowIndex, 6).toString());
        hargaObj.setText(dataTable.getValueAt(selectedRowIndex, 7).toString());
        lamaCicilanObj.setText(dataTable.getValueAt(selectedRowIndex, 8).toString());
        cicilanObj.setText(dataTable.getValueAt(selectedRowIndex, 9).toString());

        // Debugging output using JOptionPane
        String debugMessage = "Nama Pemesan: " + namaPemesanObj.getText() + "\n"
            + "Luas Tanah Tersedia: " + luasTanahTersediaObj.getText() + "\n"
            + "Harga: " + hargaObj.getText() + "\n"
            + "Lama Cicilan: " + lamaCicilanObj.getText() + "\n"
            + "Cicilan: " + cicilanObj.getText();
        JOptionPane.showMessageDialog(null, debugMessage, "Data Information", JOptionPane.INFORMATION_MESSAGE);
        
        setTipe(Integer.parseInt(dataTable.getValueAt(selectedRowIndex, 4).toString()));
        setArea(Integer.parseInt(dataTable.getValueAt(selectedRowIndex, 2).toString()));
    }//GEN-LAST:event_dataTableMouseClicked

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
            java.util.logging.Logger.getLogger(LatihanSoal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LatihanSoal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LatihanSoal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LatihanSoal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LatihanSoal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> areaObj;
    private javax.swing.JTextField cicilanObj;
    private javax.swing.JButton clearButton;
    private javax.swing.JTable dataTable;
    private javax.swing.JTextField dpObj;
    private javax.swing.JTextField hargaBangunanObj;
    private javax.swing.JTextField hargaObj;
    private javax.swing.JTextField hargaTanahObj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahRumahTerjualObj;
    private javax.swing.JTextField lamaCicilanObj;
    private javax.swing.JTextField luasTanahAsliObj;
    private javax.swing.JTextField luasTanahTersediaObj;
    private javax.swing.JTextField namaPemesanObj;
    private javax.swing.JTextField ppnObj;
    private javax.swing.JButton prosesButton;
    private javax.swing.JCheckBox setujuCheckboxObj;
    private javax.swing.JButton simpanButton;
    private javax.swing.JRadioButton tipe36;
    private javax.swing.JRadioButton tipe45;
    private javax.swing.JRadioButton tipe54;
    private javax.swing.ButtonGroup tipeRumah;
    private javax.swing.JTextField totalPenjualanRumahObj;
    // End of variables declaration//GEN-END:variables
}

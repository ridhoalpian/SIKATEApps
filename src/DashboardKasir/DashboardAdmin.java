/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DashboardKasir;

import Swing.session;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.glasspanepopup.GlassPanePopup;



/**
 *
 * @author Lenovo
 */
public class DashboardAdmin extends javax.swing.JFrame {
    
    Color Defaultcolor,Clickedcolor;
    String kd = session.get_id();
    
    public DashboardAdmin() {
        initComponents();
        warnatabel();
        warnaframe();
        tampil_table();
        GlassPanePopup.install(this);
        setkd();
        tampil_tableuser();
    }
    private void setkd(){
        
        try {
            kd_prodd.setText(kd);
            Connection conn= (Connection)Koneksi.konek.configDBek();
            Statement stcost = (Statement) conn.createStatement();
            String sql = "select * from produk where id_produk = '"+kd_prodd.getText()+"'";
            ResultSet rscost = stcost.executeQuery(sql);
            while(rscost.next()){
                nma.setText(rscost.getString(2));
                heg.setText(rscost.getString(3));
                stk.setText(rscost.getString(4));
                tgl.setText(rscost.getString(5));
            }
        } catch (Exception e) {
        }
    }
        private void warnatabel(){
        DefaultTableCellRenderer headRender = new DefaultTableCellRenderer();
        headRender.setBackground(new Color(204, 204, 204));
        headRender.setForeground(new Color(0, 0, 0));
        tabeldatpro.getTableHeader().setDefaultRenderer(headRender);
        tabeluser.getTableHeader().setDefaultRenderer(headRender);
        tabeldatpro.setRowHeight(50);
        tabeluser.setRowHeight(50);
    }
        private void warnaframe(){
        Defaultcolor=new Color(58,154,84);
        Clickedcolor=new Color(62,181,84);
        
        dash1.setVisible(true);
        regis1.setVisible(false);
        dathut1.setVisible(false);
        datpen1.setVisible(false);
        datpro1.setVisible(false);
        keunt1.setVisible(false);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
        
        dash.setBackground(Clickedcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
        }
        
        public void tampil_table(){
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("Kode Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga Produk");
        model.addColumn("Stok Produk");
        model.addColumn("Tanggal Exp");
        
        try {
            int no=1;
            String sql = "SELECT * FROM produk";
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                        res.getString(2),res.getString(3),res.getString(4),
                        res.getString(5)
                        });
        }
            tabeldatpro.setModel(model);
        } catch (Exception e) {
        }
    }
        private void cari(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga Produk");
        model.addColumn("Stok Produk");
        model.addColumn("Tanggal Exp");
            
        try{
            Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.Statement stm=conn.createStatement();
            String sql = "SELECT * FROM produk WHERE id_produk LIKE '%"+search.getText()
                    +"%' OR nama_produk LIKE '%"+search.getText()+"%' OR harga_produk LIKE '%"
                    +search.getText()+"%' OR stok LIKE '%"+search.getText()+"%' OR tgl_exp LIKE '%"+search.getText()+"%'";

            ResultSet res=stm.executeQuery(sql);  
            while(res.next()){
                model.addRow(new Object[]{
                   res.getString(1),
                   res.getString(2),
                   res.getString(3),
                   res.getString(4),
                   res.getString(5),                   
               }); 
            }
           tabeldatpro.setModel(model);
        } catch (Exception e) {
        System.err.println(e.getMessage());
        }
    }    
        public void tampil_tableuser(){
             
            DefaultTableModel model=new DefaultTableModel();
            model.addColumn("NIK");
            model.addColumn("Nama");
            model.addColumn("Alamat");
            model.addColumn("Telepon");
            model.addColumn("Status");
            model.addColumn("Username");
        
        try {
            int no=1;
            String sql = "SELECT * FROM user u inner join akun a on u.nik = a.nik";
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),
                        res.getString(2),res.getString(3),res.getString(4),
                        res.getString(5), res.getString(6)
                        });
        }
            tabeluser.setModel(model);
        } catch (Exception e) {
        }
    }
        void refresh_tabeluser(){
            regis_txtalamat.setText("");
            regis_txtnama.setText("");
            regis_txtnik.setText("");
            regis_txtsearch.setText("");
            regis_txttelp.setText("");
        }
        private void cariuser(){
        DefaultTableModel model = new DefaultTableModel();
            model.addColumn("NIK");
            model.addColumn("Nama");
            model.addColumn("Alamat");
            model.addColumn("Telepon");
            model.addColumn("Status");
            model.addColumn("User");
            
        try{
            Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.Statement stm=conn.createStatement();
            String sql = "SELECT * FROM user u inner join akun a on u.nik = a.nik WHERE u.nik LIKE '%"+regis_txtsearch.getText()
                    +"%' OR u.nama LIKE '%"+regis_txtsearch.getText()+"%' OR u.alamat LIKE '%"
                    +regis_txtsearch.getText()+"%' OR u.telp LIKE '%"+regis_txtsearch.getText()+"%' OR u.status LIKE '%"
                    +regis_txtsearch.getText()+"%' OR a.username LIKE '%"+regis_txtsearch.getText()+"%'";

            ResultSet res=stm.executeQuery(sql);  
            while(res.next()){
                model.addRow(new Object[]{
                   res.getString(1),
                   res.getString(2),
                   res.getString(3),
                   res.getString(4),
                   res.getString(5),
                   res.getString(6),
               }); 
            }
           tabeluser.setModel(model);
        } catch (Exception e) {
        System.err.println(e.getMessage());
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

        dathut = new Swing.btnkotak();
        untg = new Swing.btnkotak();
        datpen = new Swing.btnkotak();
        kelaur = new Swing.btnkotak();
        pengeluaran = new Swing.btnkotak();
        regis = new Swing.btnkotak();
        dash = new Swing.btnkotak();
        datduk = new Swing.btnkotak();
        jLabel1 = new javax.swing.JLabel();
        dash1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dathut1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        datpen1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        datpro1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeldatpro = new javax.swing.JTable();
        btnroundkotak1 = new Swing.btnroundkotak();
        round1 = new Swing.round();
        search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        keunt1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        penglu1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        regis1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeluser = new javax.swing.JTable();
        regis_btnregis = new Swing.btnroundkotak();
        round2 = new Swing.round();
        jLabel5 = new javax.swing.JLabel();
        regis_txtsearch = new javax.swing.JTextField();
        tambahproduk = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        stok_produk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tgl_exp = new javax.swing.JTextField();
        hrg_produk = new javax.swing.JTextField();
        nm_produk = new javax.swing.JTextField();
        kd_produk = new javax.swing.JTextField();
        btnroundkotak2 = new Swing.btnroundkotak();
        btnroundkotak3 = new Swing.btnroundkotak();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        editproduk = new javax.swing.JPanel();
        stk = new javax.swing.JTextField();
        tgl = new javax.swing.JTextField();
        kd_prodd = new javax.swing.JTextField();
        nma = new javax.swing.JTextField();
        heg = new javax.swing.JTextField();
        btnroundkotak6 = new Swing.btnroundkotak();
        btnroundkotak5 = new Swing.btnroundkotak();
        btnroundkotak4 = new Swing.btnroundkotak();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tambahakun = new javax.swing.JPanel();
        txtalamat = new javax.swing.JTextField();
        txttelepon = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtnik = new javax.swing.JTextField();
        btncancel = new Swing.btnroundkotak();
        btnsimpan = new Swing.btnroundkotak();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        editakun = new javax.swing.JPanel();
        regis_txtnama = new javax.swing.JTextField();
        regis_txtalamat = new javax.swing.JTextField();
        regis_txttelp = new javax.swing.JTextField();
        regis_txtnik = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        btnroundkotak8 = new Swing.btnroundkotak();
        btnroundkotak7 = new Swing.btnroundkotak();
        btnroundkotak9 = new Swing.btnroundkotak();
        jLabel43 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dathut.setBackground(new java.awt.Color(58, 154, 84));
        dathut.setForeground(new java.awt.Color(255, 255, 255));
        dathut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/datahut.png"))); // NOI18N
        dathut.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        dathut.setIconTextGap(60);
        dathut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dathutMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dathutMousePressed(evt);
            }
        });
        dathut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dathutActionPerformed(evt);
            }
        });
        getContentPane().add(dathut, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 270, 340, 90));

        untg.setBackground(new java.awt.Color(58, 154, 84));
        untg.setForeground(new java.awt.Color(255, 255, 255));
        untg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/keunt.png"))); // NOI18N
        untg.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        untg.setIconTextGap(60);
        untg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                untgMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                untgMousePressed(evt);
            }
        });
        untg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                untgActionPerformed(evt);
            }
        });
        getContentPane().add(untg, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 670, 340, 90));

        datpen.setBackground(new java.awt.Color(58, 154, 84));
        datpen.setForeground(new java.awt.Color(255, 255, 255));
        datpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/datpen.png"))); // NOI18N
        datpen.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        datpen.setIconTextGap(30);
        datpen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datpenMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datpenMousePressed(evt);
            }
        });
        datpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datpenActionPerformed(evt);
            }
        });
        getContentPane().add(datpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 470, 340, 90));

        kelaur.setBackground(new java.awt.Color(58, 154, 84));
        kelaur.setForeground(new java.awt.Color(255, 255, 255));
        kelaur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/keluar.png"))); // NOI18N
        kelaur.setToolTipText("");
        kelaur.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        kelaur.setIconTextGap(90);
        kelaur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kelaurMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                kelaurMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                kelaurMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kelaurMousePressed(evt);
            }
        });
        kelaur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kelaurActionPerformed(evt);
            }
        });
        getContentPane().add(kelaur, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 950, 340, 100));

        pengeluaran.setBackground(new java.awt.Color(58, 154, 84));
        pengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        pengeluaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/pengeluaran.png"))); // NOI18N
        pengeluaran.setToolTipText("");
        pengeluaran.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        pengeluaran.setIconTextGap(90);
        pengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pengeluaranMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pengeluaranMousePressed(evt);
            }
        });
        pengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengeluaranActionPerformed(evt);
            }
        });
        getContentPane().add(pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 770, 340, 90));

        regis.setBackground(new java.awt.Color(58, 154, 84));
        regis.setForeground(new java.awt.Color(255, 255, 255));
        regis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/regis.png"))); // NOI18N
        regis.setToolTipText("");
        regis.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        regis.setIconTextGap(90);
        regis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regisMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                regisMousePressed(evt);
            }
        });
        regis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regisActionPerformed(evt);
            }
        });
        getContentPane().add(regis, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 370, 340, 90));

        dash.setBackground(new java.awt.Color(58, 154, 84));
        dash.setForeground(new java.awt.Color(255, 255, 255));
        dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/dash.png"))); // NOI18N
        dash.setBorderPainted(false);
        dash.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        dash.setIconTextGap(70);
        dash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dashMousePressed(evt);
            }
        });
        dash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashActionPerformed(evt);
            }
        });
        getContentPane().add(dash, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 170, 340, 90));

        datduk.setBackground(new java.awt.Color(58, 154, 84));
        datduk.setForeground(new java.awt.Color(255, 255, 255));
        datduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/datpro.png"))); // NOI18N
        datduk.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        datduk.setIconTextGap(60);
        datduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datdukMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datdukMousePressed(evt);
            }
        });
        datduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datdukActionPerformed(evt);
            }
        });
        getContentPane().add(datduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 570, 340, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/admin.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        dash1.setBackground(new java.awt.Color(255, 255, 255));
        dash1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Futura Md BT", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dashboard");
        jLabel2.setToolTipText("");
        dash1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 950));

        getContentPane().add(dash1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        dathut1.setBackground(new java.awt.Color(255, 255, 255));
        dathut1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Futura Md BT", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Data Hutang");
        jLabel7.setToolTipText("");
        dathut1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 960));

        getContentPane().add(dathut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        datpen1.setBackground(new java.awt.Color(255, 255, 255));
        datpen1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Futura Md BT", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Data Penjualan");
        jLabel8.setToolTipText("");
        datpen1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 960));

        getContentPane().add(datpen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        datpro1.setBackground(new java.awt.Color(255, 255, 255));
        datpro1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tabeldatpro.setAutoCreateRowSorter(true);
        tabeldatpro.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        tabeldatpro.setForeground(new java.awt.Color(51, 51, 51));
        tabeldatpro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Harga", "Ukuran", "Sisa Stok", "Tanggal", "Tindakan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabeldatpro.setGridColor(new java.awt.Color(204, 204, 204));
        tabeldatpro.setRowHeight(60);
        tabeldatpro.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tabeldatpro.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabeldatpro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldatproMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeldatpro);
        if (tabeldatpro.getColumnModel().getColumnCount() > 0) {
            tabeldatpro.getColumnModel().getColumn(0).setResizable(false);
            tabeldatpro.getColumnModel().getColumn(1).setResizable(false);
            tabeldatpro.getColumnModel().getColumn(2).setResizable(false);
            tabeldatpro.getColumnModel().getColumn(3).setResizable(false);
            tabeldatpro.getColumnModel().getColumn(4).setResizable(false);
            tabeldatpro.getColumnModel().getColumn(5).setResizable(false);
        }

        datpro1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 1450, 810));

        btnroundkotak1.setBackground(new java.awt.Color(58, 154, 84));
        btnroundkotak1.setForeground(new java.awt.Color(255, 255, 255));
        btnroundkotak1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/add (1).png"))); // NOI18N
        btnroundkotak1.setText("Tambah Produk");
        btnroundkotak1.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak1.setIconTextGap(10);
        btnroundkotak1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnroundkotak1MouseClicked(evt);
            }
        });
        btnroundkotak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak1ActionPerformed(evt);
            }
        });
        datpro1.add(btnroundkotak1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 270, 70));

        round1.setBackground(new java.awt.Color(204, 204, 204));

        search.setBackground(new java.awt.Color(204, 204, 204));
        search.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        search.setBorder(null);
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/search.png"))); // NOI18N

        javax.swing.GroupLayout round1Layout = new javax.swing.GroupLayout(round1);
        round1.setLayout(round1Layout);
        round1Layout.setHorizontalGroup(
            round1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(round1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        round1Layout.setVerticalGroup(
            round1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, round1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(round1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search))
                .addGap(8, 8, 8))
        );

        datpro1.add(round1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 410, 60));

        getContentPane().add(datpro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        keunt1.setBackground(new java.awt.Color(255, 255, 255));
        keunt1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Futura Md BT", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Keuntungan");
        jLabel10.setToolTipText("");
        keunt1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 960));

        getContentPane().add(keunt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        penglu1.setBackground(new java.awt.Color(255, 255, 255));
        penglu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Futura Md BT", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pengeluaran");
        jLabel3.setToolTipText("");
        penglu1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1530, 960));

        getContentPane().add(penglu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        regis1.setBackground(new java.awt.Color(255, 255, 255));
        regis1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tabeluser.setAutoCreateRowSorter(true);
        tabeluser.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        tabeluser.setForeground(new java.awt.Color(51, 51, 51));
        tabeluser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIK", "Nama", "Alamat", "Telepon", "Status", "Tindakan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabeluser.setGridColor(new java.awt.Color(204, 204, 204));
        tabeluser.setRowHeight(60);
        tabeluser.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tabeluser.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tabeluser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeluserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabeluser);

        regis1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 1450, 810));

        regis_btnregis.setBackground(new java.awt.Color(58, 154, 84));
        regis_btnregis.setForeground(new java.awt.Color(255, 255, 255));
        regis_btnregis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/add (1).png"))); // NOI18N
        regis_btnregis.setText("Data Akun");
        regis_btnregis.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        regis_btnregis.setIconTextGap(10);
        regis_btnregis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regis_btnregisActionPerformed(evt);
            }
        });
        regis1.add(regis_btnregis, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 220, 70));

        round2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/search.png"))); // NOI18N

        regis_txtsearch.setBackground(new java.awt.Color(204, 204, 204));
        regis_txtsearch.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        regis_txtsearch.setBorder(null);
        regis_txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regis_txtsearchActionPerformed(evt);
            }
        });
        regis_txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                regis_txtsearchKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout round2Layout = new javax.swing.GroupLayout(round2);
        round2.setLayout(round2Layout);
        round2Layout.setHorizontalGroup(
            round2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(round2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(regis_txtsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );
        round2Layout.setVerticalGroup(
            round2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, round2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(round2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(regis_txtsearch)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        regis1.add(round2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 410, 60));

        getContentPane().add(regis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        tambahproduk.setBackground(new java.awt.Color(255, 255, 255));
        tambahproduk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        tambahproduk.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 200, 110, 40));

        stok_produk.setBackground(new java.awt.Color(240, 240, 240));
        stok_produk.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        stok_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stok_produkActionPerformed(evt);
            }
        });
        tambahproduk.add(stok_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 710, 880, 50));

        jLabel6.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel6.setText("Stok");
        tambahproduk.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 680, 190, -1));

        tgl_exp.setBackground(new java.awt.Color(240, 240, 240));
        tgl_exp.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahproduk.add(tgl_exp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 880, 50));

        hrg_produk.setBackground(new java.awt.Color(240, 240, 240));
        hrg_produk.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahproduk.add(hrg_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, 880, 50));

        nm_produk.setBackground(new java.awt.Color(240, 240, 240));
        nm_produk.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahproduk.add(nm_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 880, 50));

        kd_produk.setBackground(new java.awt.Color(240, 240, 240));
        kd_produk.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahproduk.add(kd_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 880, 50));

        btnroundkotak2.setBackground(new java.awt.Color(204, 255, 255));
        btnroundkotak2.setForeground(new java.awt.Color(102, 102, 102));
        btnroundkotak2.setText("Cancel");
        btnroundkotak2.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak2ActionPerformed(evt);
            }
        });
        tambahproduk.add(btnroundkotak2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 790, 190, 60));

        btnroundkotak3.setBackground(new java.awt.Color(62, 181, 84));
        btnroundkotak3.setForeground(new java.awt.Color(255, 255, 255));
        btnroundkotak3.setText("Simpan");
        btnroundkotak3.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak3ActionPerformed(evt);
            }
        });
        tambahproduk.add(btnroundkotak3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 790, 200, 60));

        jLabel18.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel18.setText("Harga Produk");
        tambahproduk.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, 280, -1));

        jLabel19.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel19.setText("Input Data Produk Baru");
        tambahproduk.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        jLabel20.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel20.setText("Kode Produk");
        tambahproduk.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 270, -1));

        jLabel21.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel21.setText("Nama Produk");
        tambahproduk.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 280, -1));

        jLabel22.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel22.setText("Tanggal Kadaluwarsa");
        tambahproduk.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 350, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/edit.png"))); // NOI18N
        tambahproduk.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 1080));

        getContentPane().add(tambahproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        editproduk.setBackground(new java.awt.Color(255, 255, 255));
        editproduk.setPreferredSize(new java.awt.Dimension(1530, 950));
        editproduk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stk.setBackground(new java.awt.Color(240, 240, 240));
        stk.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        stk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stkActionPerformed(evt);
            }
        });
        editproduk.add(stk, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, 880, 50));

        tgl.setBackground(new java.awt.Color(240, 240, 240));
        tgl.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        editproduk.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 710, 880, 50));

        kd_prodd.setBackground(new java.awt.Color(240, 240, 240));
        kd_prodd.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        kd_prodd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kd_proddActionPerformed(evt);
            }
        });
        editproduk.add(kd_prodd, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 880, 50));

        nma.setBackground(new java.awt.Color(240, 240, 240));
        nma.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        editproduk.add(nma, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 880, 50));

        heg.setBackground(new java.awt.Color(240, 240, 240));
        heg.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        heg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hegActionPerformed(evt);
            }
        });
        editproduk.add(heg, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 880, 50));

        btnroundkotak6.setText("Kembali");
        btnroundkotak6.setFont(new java.awt.Font("Futura Md BT", 1, 14)); // NOI18N
        btnroundkotak6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak6ActionPerformed(evt);
            }
        });
        editproduk.add(btnroundkotak6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 190, 100, 40));

        btnroundkotak5.setBackground(new java.awt.Color(204, 255, 255));
        btnroundkotak5.setForeground(new java.awt.Color(102, 102, 102));
        btnroundkotak5.setText("Hapus");
        btnroundkotak5.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak5ActionPerformed(evt);
            }
        });
        editproduk.add(btnroundkotak5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 800, 200, 60));

        btnroundkotak4.setBackground(new java.awt.Color(62, 181, 84));
        btnroundkotak4.setForeground(new java.awt.Color(255, 255, 255));
        btnroundkotak4.setText("Edit");
        btnroundkotak4.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak4ActionPerformed(evt);
            }
        });
        editproduk.add(btnroundkotak4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 800, 200, 60));

        jLabel29.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel29.setText("Nama Produk");
        editproduk.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 280, -1));

        jLabel30.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel30.setText("Edit Data Produk Baru");
        editproduk.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        jLabel31.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel31.setText("Kode Produk");
        editproduk.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 270, -1));

        jLabel32.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel32.setText("Harga Produk");
        editproduk.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 350, -1));

        jLabel33.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel33.setText("Stok Produk");
        editproduk.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 580, 280, -1));

        jLabel34.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel34.setText("Tanggal Kadaluarsa");
        editproduk.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 680, 190, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/edit.png"))); // NOI18N
        jLabel17.setText("jLabel17");
        editproduk.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 1080));

        getContentPane().add(editproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        tambahakun.setBackground(new java.awt.Color(255, 255, 255));
        tambahakun.setPreferredSize(new java.awt.Dimension(1530, 950));
        tambahakun.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtalamat.setBackground(new java.awt.Color(240, 240, 240));
        txtalamat.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahakun.add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, 880, 50));

        txttelepon.setBackground(new java.awt.Color(240, 240, 240));
        txttelepon.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahakun.add(txttelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 540, 880, 50));

        txtnama.setBackground(new java.awt.Color(240, 240, 240));
        txtnama.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahakun.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 880, 50));

        txtnik.setBackground(new java.awt.Color(240, 240, 240));
        txtnik.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahakun.add(txtnik, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 880, 50));

        btncancel.setBackground(new java.awt.Color(204, 255, 255));
        btncancel.setForeground(new java.awt.Color(102, 102, 102));
        btncancel.setText("Cancel");
        btncancel.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        tambahakun.add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 830, 200, 60));

        btnsimpan.setBackground(new java.awt.Color(62, 181, 84));
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setText("Simpan");
        btnsimpan.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        tambahakun.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 830, 200, 60));

        jLabel9.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel9.setText("Telepon");
        tambahakun.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, -1, -1));

        jLabel23.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel23.setText("Silahkan Lengkapi Data Diri Anda");
        tambahakun.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, -1));

        jLabel24.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel24.setText("NIK");
        tambahakun.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        jLabel25.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel25.setText("Nama Lengkap");
        tambahakun.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, -1, -1));

        jLabel26.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel26.setText("Alamat");
        tambahakun.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, -1, -1));

        txtusername.setBackground(new java.awt.Color(240, 240, 240));
        txtusername.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahakun.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 650, 880, 50));

        jLabel27.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel27.setText("Username");
        tambahakun.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, -1, -1));

        jLabel28.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel28.setText("Password");
        tambahakun.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 710, -1, -1));

        txtpassword.setBackground(new java.awt.Color(240, 240, 240));
        txtpassword.setFont(new java.awt.Font("Futura Bk BT", 1, 24)); // NOI18N
        tambahakun.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 740, 880, 50));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/edit.png"))); // NOI18N
        tambahakun.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 1080));

        getContentPane().add(tambahakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        editakun.setBackground(new java.awt.Color(255, 255, 255));
        editakun.setPreferredSize(new java.awt.Dimension(1530, 950));
        editakun.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regis_txtnama.setBackground(new java.awt.Color(240, 240, 240));
        regis_txtnama.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        editakun.add(regis_txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 880, 50));

        regis_txtalamat.setBackground(new java.awt.Color(240, 240, 240));
        regis_txtalamat.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        editakun.add(regis_txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 880, 50));

        regis_txttelp.setBackground(new java.awt.Color(240, 240, 240));
        regis_txttelp.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        regis_txttelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regis_txttelpActionPerformed(evt);
            }
        });
        editakun.add(regis_txttelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, 880, 50));

        regis_txtnik.setBackground(new java.awt.Color(240, 240, 240));
        regis_txtnik.setFont(new java.awt.Font("Futura Md BT", 0, 24)); // NOI18N
        regis_txtnik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regis_txtnikActionPerformed(evt);
            }
        });
        editakun.add(regis_txtnik, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 880, 50));

        jLabel36.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel36.setText("Telepon");
        editakun.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 600, -1, -1));

        jLabel37.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel37.setText("Alamat");
        editakun.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, -1, -1));

        jLabel38.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel38.setText("Nama Lengkap");
        editakun.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, -1, -1));

        jLabel39.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel39.setText("NIK");
        editakun.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        jLabel42.setFont(new java.awt.Font("Futura Md BT", 1, 18)); // NOI18N
        jLabel42.setText("Silahkan Lengkapi Data Diri Anda");
        editakun.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        btnroundkotak8.setBackground(new java.awt.Color(204, 255, 255));
        btnroundkotak8.setForeground(new java.awt.Color(102, 102, 102));
        btnroundkotak8.setText("Delete");
        btnroundkotak8.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak8ActionPerformed(evt);
            }
        });
        editakun.add(btnroundkotak8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 780, 200, 60));

        btnroundkotak7.setBackground(new java.awt.Color(62, 181, 84));
        btnroundkotak7.setForeground(new java.awt.Color(255, 255, 255));
        btnroundkotak7.setText("Edit");
        btnroundkotak7.setFont(new java.awt.Font("Futura Md BT", 1, 24)); // NOI18N
        btnroundkotak7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak7ActionPerformed(evt);
            }
        });
        editakun.add(btnroundkotak7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 780, 200, 60));

        btnroundkotak9.setText("Kembali");
        btnroundkotak9.setFont(new java.awt.Font("Futura Md BT", 1, 14)); // NOI18N
        btnroundkotak9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroundkotak9ActionPerformed(evt);
            }
        });
        editakun.add(btnroundkotak9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 190, 100, 40));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Komponen/edit.png"))); // NOI18N
        editakun.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 1080));

        getContentPane().add(editakun, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1580, 1080));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashActionPerformed
        dash1.setVisible(true);
        regis1.setVisible(false);
        dathut1.setVisible(false);
        datpen1.setVisible(false);
        datpro1.setVisible(false);
        keunt1.setVisible(false);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_dashActionPerformed

    private void dashMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMousePressed
        dash.setBackground(Clickedcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_dashMousePressed

    private void regisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regisMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Clickedcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_regisMousePressed

    private void datdukMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datdukMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Clickedcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_datdukMousePressed

    private void untgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_untgMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Clickedcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_untgMousePressed

    private void datpenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datpenMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Clickedcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_datpenMousePressed

    private void datpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datpenActionPerformed
        dash1.setVisible(false);
        regis1.setVisible(false);
        dathut1.setVisible(false);
        datpen1.setVisible(true);
        datpro1.setVisible(false);
        keunt1.setVisible(false);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_datpenActionPerformed

    private void dathutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dathutMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Clickedcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_dathutMousePressed

    private void regisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regisActionPerformed
        dash1.setVisible(false);
        regis1.setVisible(true);
        dathut1.setVisible(false);
        datpen1.setVisible(false);
        datpro1.setVisible(false);
        keunt1.setVisible(false);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_regisActionPerformed

    private void dashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashMouseClicked
        
    }//GEN-LAST:event_dashMouseClicked

    private void dathutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dathutMouseClicked
        
    }//GEN-LAST:event_dathutMouseClicked

    private void datpenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datpenMouseClicked
        
    }//GEN-LAST:event_datpenMouseClicked

    private void datdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datdukMouseClicked
        
    }//GEN-LAST:event_datdukMouseClicked

    private void untgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_untgMouseClicked
        
    }//GEN-LAST:event_untgMouseClicked

    private void regisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regisMouseClicked
        
    }//GEN-LAST:event_regisMouseClicked

    private void datdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datdukActionPerformed
        dash1.setVisible(false);
        regis1.setVisible(false);
        dathut1.setVisible(false);
        datpen1.setVisible(false);
        datpro1.setVisible(true);
        keunt1.setVisible(false);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_datdukActionPerformed

    private void dathutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dathutActionPerformed
        dash1.setVisible(false);
        regis1.setVisible(false);
        dathut1.setVisible(true);
        datpen1.setVisible(false);
        datpro1.setVisible(false);
        keunt1.setVisible(false);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_dathutActionPerformed

    private void untgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_untgActionPerformed
        dash1.setVisible(false);
        regis1.setVisible(false);
        dathut1.setVisible(false);
        datpen1.setVisible(false);
        datpro1.setVisible(false);
        keunt1.setVisible(true);
        penglu1.setVisible(false);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_untgActionPerformed

    private void pengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengeluaranMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pengeluaranMouseClicked

    private void pengeluaranMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengeluaranMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Clickedcolor);
        kelaur.setBackground(Defaultcolor);
    }//GEN-LAST:event_pengeluaranMousePressed

    private void pengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengeluaranActionPerformed
        dash1.setVisible(false);
        regis1.setVisible(false);
        dathut1.setVisible(false);
        datpen1.setVisible(false);
        datpro1.setVisible(false);
        keunt1.setVisible(false);
        penglu1.setVisible(true);
        tambahakun.setVisible(false);
        tambahproduk.setVisible(false);
        editakun.setVisible(false);
        editproduk.setVisible(false);
    }//GEN-LAST:event_pengeluaranActionPerformed

    private void kelaurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kelaurMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_kelaurMouseClicked

    private void kelaurMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kelaurMousePressed
        dash.setBackground(Defaultcolor);
        regis.setBackground(Defaultcolor);
        datpen.setBackground(Defaultcolor);
        untg.setBackground(Defaultcolor);
        datduk.setBackground(Defaultcolor);
        dathut.setBackground(Defaultcolor);
        pengeluaran.setBackground(Defaultcolor);
        kelaur.setBackground(Clickedcolor);
    }//GEN-LAST:event_kelaurMousePressed

    private void kelaurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kelaurActionPerformed
        dispose();
    }//GEN-LAST:event_kelaurActionPerformed

    private void kelaurMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kelaurMouseEntered
        
    }//GEN-LAST:event_kelaurMouseEntered

    private void kelaurMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kelaurMouseExited
        
    }//GEN-LAST:event_kelaurMouseExited

    private void btnroundkotak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak1ActionPerformed
        tambahproduk.setVisible(true);
        datpro1.setVisible(false);
    }//GEN-LAST:event_btnroundkotak1ActionPerformed

    private void tabeldatproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldatproMouseClicked
//        session.set_id(kd);
        int row = tabeldatpro.rowAtPoint(evt.getPoint());
        String kd = tabeldatpro.getValueAt(row, 0).toString();
        kd_prodd.setText(kd);
        String nama =  tabeldatpro.getValueAt(row, 1).toString();
        nma.setText(nama);
        String harga = tabeldatpro.getValueAt(row, 2).toString();
        heg.setText(harga);
        String stok = tabeldatpro.getValueAt(row, 3).toString();
        stk.setText(stok);
        String tanggal = tabeldatpro.getValueAt(row, 4).toString();
        tgl.setText(tanggal);
        datpro1.setVisible(false);
        editproduk.setVisible(true);
    }//GEN-LAST:event_tabeldatproMouseClicked

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        
    }//GEN-LAST:event_searchActionPerformed

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        if (search.getText().equals(" Cari")) {
            search.setText("");
            search.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        if (search.getText().equals("")) {
            search.setText(" Cari");
            search.setForeground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_searchFocusLost

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        cari();
    }//GEN-LAST:event_searchKeyTyped

    private void tabeluserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeluserMouseClicked
        // TODO add your handling code here:
        editakun.setVisible(true);
        regis1.setVisible(false);
        regis_txtnik.setEditable(false);

        int baris = tabeluser.rowAtPoint(evt.getPoint());
        String id = tabeluser.getValueAt(baris, 0).toString();
        regis_txtnik.setText(id);
        String nama =  tabeluser.getValueAt(baris, 1).toString();
        regis_txtnama.setText(nama);
        String alamat = tabeluser.getValueAt(baris, 2).toString();
        regis_txtalamat.setText(alamat);
        String telepon = tabeluser.getValueAt(baris, 3).toString();
        regis_txttelp.setText(telepon);
    }//GEN-LAST:event_tabeluserMouseClicked

    private void regis_btnregisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regis_btnregisActionPerformed
        tambahakun.setVisible(true);
        regis1.setVisible(false);
    }//GEN-LAST:event_regis_btnregisActionPerformed

    private void regis_txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regis_txtsearchActionPerformed

    }//GEN-LAST:event_regis_txtsearchActionPerformed

    private void regis_txttelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regis_txttelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regis_txttelpActionPerformed

    private void regis_txtnikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regis_txtnikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regis_txtnikActionPerformed

    private void btnroundkotak1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnroundkotak1MouseClicked
        
    }//GEN-LAST:event_btnroundkotak1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tambahproduk.setVisible(false);
        datpro1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnroundkotak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak2ActionPerformed
        tambahproduk.setVisible(false);
        datpro1.setVisible(true);
    }//GEN-LAST:event_btnroundkotak2ActionPerformed

    private void btnroundkotak3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak3ActionPerformed
        try {
            String sql = "INSERT INTO produk (id_produk, nama_produk, harga_produk, stok, tgl_exp) VALUES ('"+kd_produk.getText()+"', '"+nm_produk.getText()+"', '"+hrg_produk.getText()+"','"+stok_produk.getText()+"', '"+tgl_exp.getText()+"')";
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            tampil_table();
            tambahproduk.setVisible(false);
            datpro1.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data gagal ditambahkan"+ e.getMessage());
        }

    }//GEN-LAST:event_btnroundkotak3ActionPerformed

    private void stkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stkActionPerformed

    private void kd_proddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kd_proddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_proddActionPerformed

    private void hegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hegActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        tambahakun.setVisible(false);
        regis1.setVisible(true);
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        try {
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO user (nik, nama, alamat, telp) VALUES ('"+txtnik.getText()+"', '"+txtnama.getText()+"', '"+txtalamat.getText()+"','"+txttelepon.getText()+"')";
            stmt.executeUpdate(sql);
            String sql2 = "INSERT INTO akun (nik, username, password) VALUES ('"+txtnik.getText()+"', '"+txtusername.getText()+"', '"+txtpassword.getText()+"')";
            stmt.executeUpdate(sql2);
            stmt.close();
            conn.close();
            tampil_tableuser();
            tambahakun.setVisible(false);
            regis1.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data gagal ditambahkan"+ e.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void stok_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stok_produkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stok_produkActionPerformed

    private void btnroundkotak4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak4ActionPerformed
        try {
            String sql = "UPDATE produk SET nama_produk = '"+nma.getText()+"', harga_produk = '"+heg.getText()+"', stok = '"+stk.getText()+"', tgl_exp = '"+tgl.getText()+"' WHERE id_produk = '"+kd_prodd.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            editproduk.setVisible(false);
            datpro1.setVisible(true); 
            tampil_table();
        } catch (Exception e) {
            System.out.println("gagal diubah "+e.getMessage());
        }
    }//GEN-LAST:event_btnroundkotak4ActionPerformed

    private void btnroundkotak5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak5ActionPerformed
        try {
            String sql ="DELETE FROM produk WHERE id_produk='"+kd_prodd.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            GlassPanePopup.closePopupLast();
            editproduk.setVisible(false);
            datpro1.setVisible(true);
            tampil_table();
        } catch (Exception e) {
            System.out.println("gagal dihapus "+e.getMessage());
        }
    }//GEN-LAST:event_btnroundkotak5ActionPerformed

    private void btnroundkotak6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak6ActionPerformed
        editproduk.setVisible(false);
        datpro1.setVisible(true);
    }//GEN-LAST:event_btnroundkotak6ActionPerformed

    private void regis_txtsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_regis_txtsearchKeyTyped
        cariuser();
    }//GEN-LAST:event_regis_txtsearchKeyTyped

    private void btnroundkotak7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak7ActionPerformed
        // TODO add your handling code here:
        try {
            int baris = tabeldatpro.getSelectedRow();
            int p = JOptionPane.showConfirmDialog(this, "Yakin ingin diubah ?", "Pesan", JOptionPane.YES_NO_OPTION);
            if(p==JOptionPane.YES_OPTION){
                String sql = "UPDATE user SET nama = '"+regis_txtnama.getText()+"', alamat = '"+regis_txtalamat.getText()+"', telp = '"+regis_txttelp.getText()+"' WHERE nik = '"+regis_txtnik.getText()+"'";
                java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Berhasil Diubah", "Pesan",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/tmp.png"));
                tampil_tableuser();
                editakun.setVisible(false);
                regis1.setVisible(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnroundkotak7ActionPerformed

    private void btnroundkotak8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak8ActionPerformed
        // TODO add your handling code here:
        try {
            String sql ="DELETE FROM user WHERE nik = '"+regis_txtnik.getText()+"'";
            java.sql.Connection conn=(Connection)Koneksi.konek.configDBek();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            tampil_tableuser();
            refresh_tabeluser();
            regis_btnregis.setEnabled(true);
            editakun.setVisible(false);
            regis1.setVisible(true);
        } catch (Exception e) {
            System.out.println("gagal terhapus");
        }
    }//GEN-LAST:event_btnroundkotak8ActionPerformed

    private void btnroundkotak9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroundkotak9ActionPerformed
        editakun.setVisible(false);
        regis1.setVisible(true);
    }//GEN-LAST:event_btnroundkotak9ActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.btnroundkotak btncancel;
    private Swing.btnroundkotak btnroundkotak1;
    private Swing.btnroundkotak btnroundkotak2;
    private Swing.btnroundkotak btnroundkotak3;
    private Swing.btnroundkotak btnroundkotak4;
    private Swing.btnroundkotak btnroundkotak5;
    private Swing.btnroundkotak btnroundkotak6;
    private Swing.btnroundkotak btnroundkotak7;
    private Swing.btnroundkotak btnroundkotak8;
    private Swing.btnroundkotak btnroundkotak9;
    private Swing.btnroundkotak btnsimpan;
    private Swing.btnkotak dash;
    private javax.swing.JPanel dash1;
    private Swing.btnkotak datduk;
    private Swing.btnkotak dathut;
    private javax.swing.JPanel dathut1;
    private Swing.btnkotak datpen;
    private javax.swing.JPanel datpen1;
    private javax.swing.JPanel datpro1;
    private javax.swing.JPanel editakun;
    private javax.swing.JPanel editproduk;
    private javax.swing.JTextField heg;
    private javax.swing.JTextField hrg_produk;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kd_prodd;
    private javax.swing.JTextField kd_produk;
    private Swing.btnkotak kelaur;
    private javax.swing.JPanel keunt1;
    private javax.swing.JTextField nm_produk;
    private javax.swing.JTextField nma;
    private Swing.btnkotak pengeluaran;
    private javax.swing.JPanel penglu1;
    private Swing.btnkotak regis;
    private javax.swing.JPanel regis1;
    private Swing.btnroundkotak regis_btnregis;
    private javax.swing.JTextField regis_txtalamat;
    private javax.swing.JTextField regis_txtnama;
    private javax.swing.JTextField regis_txtnik;
    private javax.swing.JTextField regis_txtsearch;
    private javax.swing.JTextField regis_txttelp;
    private Swing.round round1;
    private Swing.round round2;
    private javax.swing.JTextField search;
    private javax.swing.JTextField stk;
    private javax.swing.JTextField stok_produk;
    private javax.swing.JTable tabeldatpro;
    public javax.swing.JTable tabeluser;
    private javax.swing.JPanel tambahakun;
    private javax.swing.JPanel tambahproduk;
    private javax.swing.JTextField tgl;
    private javax.swing.JTextField tgl_exp;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnik;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txttelepon;
    private javax.swing.JTextField txtusername;
    private Swing.btnkotak untg;
    // End of variables declaration//GEN-END:variables
}

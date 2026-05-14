package Manage.GUI;

import Manage.BUS.KhachHangBUS;
import Manage.DTO.KhachHang;
import MyCustom.MyDialog;
import MyCustom.MyTable;
import MyCustom.TransparentPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import static Main.Main.changLNF;

public class PnQuanLyKhachHangGUI extends JPanel {

    public PnQuanLyKhachHangGUI() {
        changLNF("Windows");
        addControls();
        addEvents();
    }

    private KhachHangBUS khachHangBUS = new KhachHangBUS();

    final Color colorPanel = new Color(247, 247, 247);
    JButton btnReset;
    JTextField txtMa, txtHo, txtTen, txtTongChiTieu, txtTukhoa, txtMaxChiTieu, txtMinchiTieu;
    JComboBox<String> cmbGioiTinh;
    JButton btnThem, btnSua, btnXoa, btnTim;
    MyTable tblKhachHang;
    DefaultTableModel dtmKhachHang;

    private void addControls() {
        Font font = new Font("Times New Roman", Font.PLAIN, 14);
        Font headerFont = new Font("Times New Roman", Font.BOLD, 18);

        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(colorPanel);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ==================== PANEL CHINH ====================
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(colorPanel);

        // ==================== PANEL TIEU DE ====================
        JPanel pnTitle = new JPanel(new BorderLayout());
        pnTitle.setBackground(colorPanel);

        JLabel lblTitle = new JLabel("QUAN LY KHACH HANG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        btnReset = new JButton(new ImageIcon("image/Refresh-icon.png"));
        btnReset.setToolTipText("Lam moi");
        btnReset.setPreferredSize(new Dimension(40, 40));

        pnTitle.add(lblTitle, BorderLayout.WEST);
        pnTitle.add(btnReset, BorderLayout.EAST);

        mainPanel.add(pnTitle, BorderLayout.NORTH);

        // ==================== PANEL THONG TIN VA TIM KIEM ====================
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
        pnTop.setBackground(colorPanel);

        // ==== Panel Thong tin khach hang ====
        JPanel pnInfo = new JPanel(new GridBagLayout());
        pnInfo.setBackground(colorPanel);
        pnInfo.setBorder(BorderFactory.createTitledBorder("Thong tin khach hang"));
        pnInfo.setPreferredSize(new Dimension(600, 180));

        Font bigFont = new Font("Times New Roman", Font.PLAIN, 16);
      

        int row = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        gbc.weightx = 0.1;
        gbc.gridx = 0; gbc.gridy = row;
        JLabel lblMaKH = new JLabel("Ma KH");
        lblMaKH.setFont(bigFont);
        pnInfo.add(lblMaKH, gbc);
        gbc.gridx = 1; gbc.weightx = 0.35;
        txtMa = new JTextField();
        txtMa.setPreferredSize(new Dimension(220, 35));
        txtMa.setFont(bigFont);
        pnInfo.add(txtMa, gbc);
        txtMa.setEditable(false);

        gbc.weightx = 0.1;
        gbc.gridx = 2;
        JLabel lblHo = new JLabel("Ho dem");
        lblHo.setFont(bigFont);
        pnInfo.add(lblHo, gbc);
        gbc.gridx = 3; gbc.weightx = 0.2;
        txtHo = new JTextField();
        txtHo.setPreferredSize(new Dimension(120, 35));
        txtHo.setFont(bigFont);
        pnInfo.add(txtHo, gbc);

        row++;
        gbc.gridy = row; gbc.gridx = 0; gbc.weightx = 0.1;
        JLabel lblTen = new JLabel("Ten");
        lblTen.setFont(bigFont);
        pnInfo.add(lblTen, gbc);
        gbc.gridx = 1; gbc.weightx = 0.35;
        txtTen = new JTextField();
        txtTen.setPreferredSize(new Dimension(220, 35));
        txtTen.setFont(bigFont);
        pnInfo.add(txtTen, gbc);

        gbc.gridx = 2; gbc.weightx = 0.1;
        JLabel lblGioiTinh = new JLabel("Gioi tinh");
        lblGioiTinh.setFont(bigFont);
        pnInfo.add(lblGioiTinh, gbc);
        gbc.gridx = 3; gbc.weightx = 0.2;
        cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.addItem("Chon gioi tinh");
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nu");
        cmbGioiTinh.setPreferredSize(new Dimension(100, 35));
        cmbGioiTinh.setFont(bigFont);
        pnInfo.add(cmbGioiTinh, gbc);

        row++;
        gbc.gridy = row; gbc.gridx = 0; gbc.weightx = 0.1;
        JLabel lblTongChiTieu = new JLabel("Tong chi tieu");
        lblTongChiTieu.setFont(bigFont);
        pnInfo.add(lblTongChiTieu, gbc);
        gbc.gridx = 1; gbc.weightx = 0.35;
        txtTongChiTieu = new JTextField();
        txtTongChiTieu.setPreferredSize(new Dimension(220, 30));
        txtTongChiTieu.setFont(bigFont);
        pnInfo.add(txtTongChiTieu, gbc);
        txtTongChiTieu.setEditable(false);

        gbc.gridx = 2; gbc.gridwidth = 2;
        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        btnThem = new JButton("Them");
        btnSua = new JButton("Sua");
        btnXoa = new JButton("Xoa");
        btnThem.setPreferredSize(new Dimension(120, 30));
        btnSua.setPreferredSize(new Dimension(120, 30));
        btnXoa.setPreferredSize(new Dimension(120, 30));
        btnThem.setFont(bigFont);
        btnSua.setFont(bigFont);
        btnXoa.setFont(bigFont);
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.setBackground(colorPanel);
        pnInfo.add(pnButton, gbc);

        // ==== Panel Tim kiem ====
        // ==== Panel Tim kiem ====
        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new BoxLayout(pnSearch, BoxLayout.Y_AXIS));
        pnSearch.setBackground(colorPanel);
        pnSearch.setBorder(BorderFactory.createTitledBorder("Tim kiem"));
        pnSearch.setPreferredSize(new Dimension(700, 130));

        JPanel pnKeyword = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        pnKeyword.setBackground(colorPanel);
        JLabel lblTuKhoa = new JLabel("Tu khoa:");
        lblTuKhoa.setFont(bigFont);
        txtTukhoa = new JTextField();
        txtTukhoa.setPreferredSize(new Dimension(350, 34));
        txtTukhoa.setFont(bigFont);
        pnKeyword.add(lblTuKhoa);
        pnKeyword.add(txtTukhoa);

        JPanel pnExpense = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        pnExpense.setBackground(colorPanel);
        JLabel lblChiTieuTu = new JLabel("Chi tieu tu:");
        lblChiTieuTu.setFont(bigFont);
        txtMinchiTieu = new JTextField();
        txtMinchiTieu.setPreferredSize(new Dimension(150, 34));
        txtMinchiTieu.setFont(bigFont);
        JLabel lblDen = new JLabel("den:");
        lblDen.setFont(bigFont);
        txtMaxChiTieu = new JTextField();
        txtMaxChiTieu.setPreferredSize(new Dimension(150, 34));
        txtMaxChiTieu.setFont(bigFont);
        btnTim = createButton("Tim", "image/Search-icon.png");
        btnTim.setToolTipText("Tim kiem");
        btnTim.setPreferredSize(new Dimension(100, 34));
        btnTim.setFont(bigFont);
        pnExpense.add(lblChiTieuTu);
        pnExpense.add(txtMinchiTieu);
        pnExpense.add(lblDen);
        pnExpense.add(txtMaxChiTieu);
        pnExpense.add(btnTim);

        pnSearch.add(pnKeyword);
        pnSearch.add(pnExpense);

        // ==== Dat 2 panel theo chieu doc ====
        pnInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnSearch.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnTop.add(pnInfo);
        pnTop.add(Box.createRigidArea(new Dimension(0, 10)));
        pnTop.add(pnSearch);

        mainPanel.add(pnTop, BorderLayout.CENTER);

        // ==================== PANEL BANG DU LIEU ====================
        dtmKhachHang = new DefaultTableModel();
        dtmKhachHang.addColumn("Ma KH");
        dtmKhachHang.addColumn("Ho dem");
        dtmKhachHang.addColumn("Ten");
        dtmKhachHang.addColumn("Gioi tinh");
        dtmKhachHang.addColumn("Tong chi tieu");

        tblKhachHang = new MyTable(dtmKhachHang);
        tblKhachHang.setRowHeight(30);
        tblKhachHang.setFont(font);
        tblKhachHang.getTableHeader().setFont(headerFont);

        JScrollPane scrtblKhachHang = new JScrollPane(tblKhachHang);
        scrtblKhachHang.setBorder(BorderFactory.createTitledBorder("Danh sach khach hang"));
        
        mainPanel.add(scrtblKhachHang, BorderLayout.SOUTH);
        this.add(mainPanel);

        loadDataLenTableKhachHang();
    }

    private JPanel createFieldPanel(String label, Component field) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(colorPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2); // Padding nho
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(lbl, gbc);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if (field instanceof JTextField || field instanceof JComboBox) {
            field.setPreferredSize(new Dimension(200, 30));
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        panel.add(field, gbc);

        return panel;
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(120, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Cac phuong thuc con lai giu nguyen nhu cu
    private void addEvents() {
        // Giu nguyen toan bo phan addEvents() nhu cu
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenTableKhachHang();
                txtMa.setText("");
                txtHo.setText("");
                txtTen.setText("");
                txtTongChiTieu.setText("");
                txtTukhoa.setText("");
                txtMinchiTieu.setText("");
                txtMaxChiTieu.setText("");
                cmbGioiTinh.setSelectedIndex(0);
            }
        });

        tblKhachHang.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xuLyClicktblKhachHang();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        txtTukhoa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                xuLyLiveSearch();
            }
        });

        txtMinchiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaxChiTieu.requestFocus();
            }
        });

        txtMaxChiTieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTim.doClick();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyTimKiemTheoKhoang();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThemKhachHang();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLySuaKhachHang();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoaKhachHang();
            }
        });
    }

    private void loadDataLenTableKhachHang() {
        khachHangBUS.docDanhSach();
        ArrayList<KhachHang> dskh = khachHangBUS.getListKhachHang();
        loadDataLenTableKhachHang(dskh);
    }

    private void loadDataLenTableKhachHang(ArrayList<KhachHang> dskh) {
        dtmKhachHang.setRowCount(0);
        DecimalFormat dcf = new DecimalFormat("###,###");
        for (KhachHang kh : dskh) {
            Vector vec = new Vector();
            vec.add(kh.getMaKH());
            vec.add(kh.getHo());
            vec.add(kh.getTen());
            vec.add(kh.getGioiTinh());
            vec.add(dcf.format(kh.getTongChiTieu()));
            dtmKhachHang.addRow(vec);
        }
    }

    private void xuLyClicktblKhachHang() {
        int row = tblKhachHang.getSelectedRow();
        if (row > -1) {
            txtMa.setText(tblKhachHang.getValueAt(row, 0) + "");
            txtHo.setText(tblKhachHang.getValueAt(row, 1) + "");
            txtTen.setText(tblKhachHang.getValueAt(row, 2) + "");
            int index = tblKhachHang.getValueAt(row, 3).equals("Nam") ? 1 : 2;
            cmbGioiTinh.setSelectedIndex(index);
            txtTongChiTieu.setText(tblKhachHang.getValueAt(row, 4) + "");
        }
    }

    private void xuLyTimKiemTheoKhoang() {
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(txtMinchiTieu.getText(), txtMaxChiTieu.getText());
        if (dskh == null)
            return;
        loadDataLenTableKhachHang(dskh);
    }

    private void xuLyLiveSearch() {
        ArrayList<KhachHang> dskh = khachHangBUS.timKiemKhachHang(txtTukhoa.getText());
        loadDataLenTableKhachHang(dskh);
    }

    private void xuLyThemKhachHang() {
        if (khachHangBUS.themKhachHang(txtHo.getText(), txtTen.getText(), cmbGioiTinh.getSelectedItem() + ""))
            btnReset.doClick();
    }

    private void xuLySuaKhachHang() {
        if (khachHangBUS.suaKhachHang(txtMa.getText(), txtHo.getText(), txtTen.getText(), cmbGioiTinh.getSelectedItem() + ""))
            btnReset.doClick();
    }

    private void xuLyXoaKhachHang() {
        if(khachHangBUS.xoaKhachHang(txtMa.getText()))
            btnReset.doClick();
    }
}

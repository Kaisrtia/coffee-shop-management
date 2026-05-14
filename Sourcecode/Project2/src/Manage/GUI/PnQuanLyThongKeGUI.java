package Manage.GUI;

import Manage.BUS.ThongKeBUS;
import Manage.DTO.ThongKe;
import MyCustom.TransparentPanel;
import javax.swing.*;
import java.awt.*;
import static Main.Main.changLNF;
import java.text.DecimalFormat;
import java.util.Calendar;

public class PnQuanLyThongKeGUI extends JPanel {
    private ThongKeBUS thongKeBUS = new ThongKeBUS();
    private final Color colorPanel = new Color(0xF4F7FA);
    private final Color cardColor = Color.WHITE;
    private final Color primaryBlue = new Color(0x2F80B7);
    private final Color titleColor = new Color(0x243B53);
    private final Color softBorder = new Color(0xD9E2EC);
    private JLabel lblThongKeThucDon, lblThongKeKhachHang, lblThongKeNhanVien, lblThongKeDoanhThu;
    private JLabel lblDoanhThuQuy1, lblDoanhThuQuy2, lblDoanhThuQuy3, lblDoanhThuQuy4, lblTongDoanhThu;
    private JButton btnView, btnBack;
    private JComboBox<Integer> cmbNam;
    private CardLayout cardLayoutThongKe = new CardLayout();
    private JPanel pnMain;
    private JLabel lblMon1, lblMon2, lblMon3, lblMon4, lblMon5;
    private JLabel lblSoLuong1, lblSoLuong2, lblSoLuong3, lblSoLuong4, lblSoLuong5;
    private JPanel pnThongKeChiTiet;
    private DecimalFormat dcf = new DecimalFormat("###,###");

    public PnQuanLyThongKeGUI() {
        changLNF("Windows");
        addControls();
        addEvents();
    }

    private void addControls() {
        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        int w = 1030;

        pnMain = new TransparentPanel();
        pnMain.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        pnMain.setLayout(cardLayoutThongKe);

        JPanel pnThongKeTong = new JPanel(null);
        pnThongKeTong.setBackground(colorPanel);

        JLabel lblTileThongKeTong = new JLabel("TONG QUAN THONG KE", JLabel.CENTER);
        lblTileThongKeTong.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lblTileThongKeTong.setForeground(titleColor);
        lblTileThongKeTong.setBounds(0, 20, w, 45);
        pnThongKeTong.add(lblTileThongKeTong);

        btnView = createActionButton("Chi tiet");
        btnView.setForeground(Color.BLACK);
        btnView.setToolTipText("Xem chi tiet");
        btnView.setBounds(25, 22, 95, 34);
        pnThongKeTong.add(btnView);

        lblThongKeThucDon = createNumberLabel();
        lblThongKeKhachHang = createNumberLabel();
        lblThongKeNhanVien = createNumberLabel();
        lblThongKeDoanhThu = createNumberLabel();

        pnThongKeTong.add(createStatCard("San pham", lblThongKeThucDon, 98, 90, 369, 165));
        pnThongKeTong.add(createStatCard("Khach hang", lblThongKeKhachHang, 563, 90, 369, 165));
        pnThongKeTong.add(createStatCard("Nhan vien", lblThongKeNhanVien, 98, 305, 369, 165));
        pnThongKeTong.add(createStatCard("Doanh thu", lblThongKeDoanhThu, 563, 305, 369, 165));

        JLabel lblYear = new JLabel("Nam thong ke", JLabel.CENTER);
        lblYear.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblYear.setForeground(titleColor);
        lblYear.setBounds(w / 2 - 80, 505, 160, 25);
        pnThongKeTong.add(lblYear);

        cmbNam = new JComboBox<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= year - 1; i--) {
            cmbNam.addItem(i);
        }
        cmbNam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        cmbNam.setBounds(w / 2 - 60, 535, 120, 35);
        pnThongKeTong.add(cmbNam);

        JPanel pnRevenue = new JPanel(null);
        pnRevenue.setBackground(cardColor);
        pnRevenue.setBorder(BorderFactory.createLineBorder(softBorder));
        pnRevenue.setBounds(98, 600, 834, 200);

        JLabel lblRevenueTitle = new JLabel("Doanh thu theo quy", JLabel.CENTER);
        lblRevenueTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblRevenueTitle.setForeground(titleColor);
        lblRevenueTitle.setBounds(0, 12, 834, 28);
        pnRevenue.add(lblRevenueTitle);

        lblDoanhThuQuy1 = createRevenueLabel();
        lblDoanhThuQuy2 = createRevenueLabel();
        lblDoanhThuQuy3 = createRevenueLabel();
        lblDoanhThuQuy4 = createRevenueLabel();
        lblTongDoanhThu = createRevenueTotalLabel();

        lblDoanhThuQuy1.setBounds(80, 65, 150, 42);
        lblDoanhThuQuy2.setBounds(250, 65, 150, 42);
        lblDoanhThuQuy3.setBounds(420, 65, 150, 42);
        lblDoanhThuQuy4.setBounds(590, 65, 150, 42);
        lblTongDoanhThu.setBounds(80, 130, 674, 44);

        pnRevenue.add(createSmallLabel("Quy 1", 80, 42));
        pnRevenue.add(createSmallLabel("Quy 2", 250, 42));
        pnRevenue.add(createSmallLabel("Quy 3", 420, 42));
        pnRevenue.add(createSmallLabel("Quy 4", 590, 42));
        pnRevenue.add(lblDoanhThuQuy1);
        pnRevenue.add(lblDoanhThuQuy2);
        pnRevenue.add(lblDoanhThuQuy3);
        pnRevenue.add(lblDoanhThuQuy4);
        pnRevenue.add(lblTongDoanhThu);
        pnThongKeTong.add(pnRevenue);

        pnMain.add(pnThongKeTong, "1");

        pnThongKeChiTiet = new TransparentPanel(null);
        pnThongKeChiTiet.setBackground(colorPanel);

        btnBack = createActionButton("Quay lai");
        btnBack.setToolTipText("Quay lai");
        btnBack.setBounds(20, 18, 100, 34);
        pnThongKeChiTiet.add(btnBack);

        JPanel pnDetail = new JPanel(null);
        pnDetail.setBackground(cardColor);
        pnDetail.setBorder(BorderFactory.createLineBorder(softBorder));
        pnDetail.setBounds(172, 50, 686, 363);

        JLabel lblDetailTitle = new JLabel("Top san pham ban chay", JLabel.CENTER);
        lblDetailTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblDetailTitle.setForeground(titleColor);
        lblDetailTitle.setBounds(0, 18, 686, 32);
        pnDetail.add(lblDetailTitle);

        lblMon1 = createDetailLabel();
        lblMon2 = createDetailLabel();
        lblMon3 = createDetailLabel();
        lblMon4 = createDetailLabel();
        lblMon5 = createDetailLabel();
        lblSoLuong1 = createDetailNumberLabel();
        lblSoLuong2 = createDetailNumberLabel();
        lblSoLuong3 = createDetailNumberLabel();
        lblSoLuong4 = createDetailNumberLabel();
        lblSoLuong5 = createDetailNumberLabel();

        int y = 80;
        addDetailRow(pnDetail, lblMon1, lblSoLuong1, y);
        addDetailRow(pnDetail, lblMon2, lblSoLuong2, y += 50);
        addDetailRow(pnDetail, lblMon3, lblSoLuong3, y += 50);
        addDetailRow(pnDetail, lblMon4, lblSoLuong4, y += 50);
        addDetailRow(pnDetail, lblMon5, lblSoLuong5, y += 50);
        pnThongKeChiTiet.add(pnDetail);

        pnMain.add(pnThongKeChiTiet, "2");
        this.add(pnMain, BorderLayout.CENTER);
        hienThiThongKe();
    }

    private JLabel createNumberLabel() {
        JLabel label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 42));
        label.setForeground(primaryBlue);
        return label;
    }

    private JLabel createRevenueLabel() {
        JLabel label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setForeground(titleColor);
        return label;
    }

    private JLabel createRevenueTotalLabel() {
        JLabel label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 26));
        label.setForeground(primaryBlue);
        return label;
    }

    private JLabel createSmallLabel(String text, int x, int y) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label.setForeground(new Color(0x52616B));
        label.setBounds(x, y, 150, 25);
        return label;
    }

    private JPanel createStatCard(String title, JLabel numberLabel, int x, int y, int width, int height) {
        JPanel card = new JPanel(null);
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createLineBorder(softBorder));
        card.setBounds(x, y, width, height);

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleLabel.setForeground(titleColor);
        titleLabel.setBounds(0, 25, width, 35);
        numberLabel.setBounds(0, 70, width, 65);
        card.add(titleLabel);
        card.add(numberLabel);
        return card;
    }

    private JLabel createDetailLabel() {
        JLabel label = new JLabel("0");
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setForeground(titleColor);
        return label;
    }

    private JLabel createDetailNumberLabel() {
        JLabel label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setForeground(primaryBlue);
        return label;
    }

    private void addDetailRow(JPanel panel, JLabel productLabel, JLabel quantityLabel, int y) {
        productLabel.setBounds(55, y, 460, 36);
        quantityLabel.setBounds(535, y, 95, 36);
        panel.add(productLabel);
        panel.add(quantityLabel);
    }

    private JButton createActionButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(primaryBlue);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void addEvents() {
        btnView.addActionListener(e -> {
            hienThiThongKe();
            cardLayoutThongKe.show(pnMain, "2");
        });

        btnBack.addActionListener(e -> {
            hienThiThongKe();
            cardLayoutThongKe.show(pnMain, "1");
        });

        cmbNam.addActionListener(e -> hienThiThongKe());
    }

    private void hienThiThongKe() {
        ThongKe thongKe = thongKeBUS.thongKe(Integer.parseInt(cmbNam.getSelectedItem() + ""));
        lblThongKeThucDon.setText(dcf.format(thongKe.getSoLuongSP()));
        lblThongKeKhachHang.setText(dcf.format(thongKe.getSoLuongKH()));
        lblThongKeNhanVien.setText(dcf.format(thongKe.getSoLuongNV()));
        lblThongKeDoanhThu.setText(dcf.format(thongKe.getTongDoanhThu()));
        lblDoanhThuQuy1.setText(dcf.format(thongKe.getTongThuQuy(1)));
        lblDoanhThuQuy2.setText(dcf.format(thongKe.getTongThuQuy(2)));
        lblDoanhThuQuy3.setText(dcf.format(thongKe.getTongThuQuy(3)));
        lblDoanhThuQuy4.setText(dcf.format(thongKe.getTongThuQuy(4)));
        lblTongDoanhThu.setText("Tong doanh thu: " + dcf.format(thongKe.getTongDoanhThu()));
        lblMon1.setText(thongKe.getTopSanPhamBanChay().get(0).getTenSP());
        lblMon2.setText(thongKe.getTopSanPhamBanChay().get(1).getTenSP());
        lblMon3.setText(thongKe.getTopSanPhamBanChay().get(2).getTenSP());
        lblMon4.setText(thongKe.getTopSanPhamBanChay().get(3).getTenSP());
        lblMon5.setText(thongKe.getTopSanPhamBanChay().get(4).getTenSP());
        lblSoLuong1.setText("" + thongKe.getTopSanPhamBanChay().get(0).getSoLuong());
        lblSoLuong2.setText("" + thongKe.getTopSanPhamBanChay().get(1).getSoLuong());
        lblSoLuong3.setText("" + thongKe.getTopSanPhamBanChay().get(2).getSoLuong());
        lblSoLuong4.setText("" + thongKe.getTopSanPhamBanChay().get(3).getSoLuong());
        lblSoLuong5.setText("" + thongKe.getTopSanPhamBanChay().get(4).getSoLuong());
    }
}

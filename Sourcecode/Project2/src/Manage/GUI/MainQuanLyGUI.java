package Manage.GUI;

import Manage.BUS.DangNhapBUS;
import Manage.BUS.PhanQuyenBUS;
import Manage.DTO.PhanQuyen;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainQuanLyGUI extends JFrame {

    private final Color DARK_BLUE = new Color(0x2C3E50);
    private final Color MEDIUM_BLUE = new Color(0x2F80B7);
    private final Color GRAY = new Color(0xBDC3C7);
    private final Color SIDEBAR_TEXT = Color.WHITE;
    private final Color SIDEBAR_TEXT_HOVER = new Color(0x1F2D3A);
    private final Color HEADER_BUTTON_HOVER = new Color(0x6CAED6);
    private final Color CLOSE_BUTTON_HOVER = new Color(0xD75A4A);

    public MainQuanLyGUI() {
        this.setTitle("Phan mem quan ly ban hang");
        this.setSize(1280, 900);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/ManagerUI/icon-app.png");
        this.setIconImage(icon);
        addControls();
        addEvents();
    }

    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JLabel btnDoiMatKhau;
    JPanel pnTitle, pnMenuLeft, pnCard, pnBanHang, pnKhuyenMai, pnSanPham, pnNhanVien, pnKhachHang, pnThongKe;
    PnQuanLyBanHangGUI banHangPanel;
    PnQuanLyKhuyenMaiGUI khuyenMaiPanel;
    PnQuanLySanPhamGUI sanPhamPanel;
    PnQuanLyNhanVienGUI nhanVienPanel;
    PnQuanLyKhachHangGUI khachHangPanel;
    PnQuanLyThongKeGUI thongKePanel;

    JLabel btnClose, btnMinimize, lblBanHang, lblKhuyenMai, lblSanPham, lblNhanVien, lblKhachHang, lblThongKe;
    final Color clLeftItem = DARK_BLUE;
    final Color clLeftItemHover = GRAY;
    final Color clLeftItemSelected = MEDIUM_BLUE;
    ArrayList<JLabel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();
    int xMouse, yMouse;

    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        Container con = getContentPane();

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        pnTitle = new JPanel(null);
        pnTitle.setPreferredSize(new Dimension(width, 46));
        pnTitle.setBackground(MEDIUM_BLUE);

        btnDoiMatKhau = createHeaderButton("Doi mat khau");
        btnDoiMatKhau.setToolTipText("Doi mat khau");
        btnDoiMatKhau.setBounds(10, 5, 115, 36);
        btnDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDoiMatKhau);

        JLabel lblTitleText = new JLabel(new ImageIcon("image/ManagerUI/title-text.png"));
        lblTitleText.setBounds(width / 2 - 428 / 2, 3, 428, 38);
        pnTitle.add(lblTitleText);

        btnMinimize = createHeaderButton("-");
        btnMinimize.setBounds(width - 86, 5, 36, 36);
        btnMinimize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnMinimize);

        btnClose = createHeaderButton("X");
        btnClose.setBounds(width - 44, 5, 36, 36);
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnClose);

        pnMain.add(pnTitle, BorderLayout.NORTH);

        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - pnTitle.getHeight()));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));

        JLabel lblAvatar = new JLabel(new ImageIcon("image/ManagerUI/avatar.png"), JLabel.CENTER);
        lblAvatar.setPreferredSize(new Dimension(250, 190));
        lblAvatar.setMaximumSize(new Dimension(250, 190));
        lblAvatar.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnMenuLeft.add(lblAvatar);

        lblBanHang = createMenuLabel("Ban hang");
        lblKhuyenMai = createMenuLabel("Khuyen mai");
        lblSanPham = createMenuLabel("San pham");
        lblNhanVien = createMenuLabel("Nhan vien");
        lblKhachHang = createMenuLabel("Khach hang");
        lblThongKe = createMenuLabel("Thong ke");

        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(lblBanHang);
        listMenuLeft.add(lblKhuyenMai);
        listMenuLeft.add(lblSanPham);
        listMenuLeft.add(lblNhanVien);
        listMenuLeft.add(lblKhachHang);
        listMenuLeft.add(lblThongKe);

        lblBanHang.setToolTipText("Ban hang");
        lblKhuyenMai.setToolTipText("Quan ly khuyen mai");
        lblSanPham.setToolTipText("Quan ly san pham");
        lblNhanVien.setToolTipText("Quan ly nhan vien");
        lblKhachHang.setToolTipText("Quan ly khach hang");
        lblThongKe.setToolTipText("Thong ke");

        for (JLabel lbl : listMenuLeft) {
            lbl.setVisible(false);
            lbl.setPreferredSize(new Dimension(250, 62));
            lbl.setMaximumSize(new Dimension(250, 62));
            lbl.setMinimumSize(new Dimension(250, 62));
            lbl.setOpaque(true);
            lbl.setBackground(clLeftItem);
            lbl.setForeground(SIDEBAR_TEXT);
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(lbl);
        }

        lblBanHang.setBackground(clLeftItemSelected);
        lblBanHang.setVisible(true);
        pnMain.add(pnMenuLeft, BorderLayout.WEST);
        pnCard = new JPanel(cardMenuLeftGroup);

        pnBanHang = new JPanel();
        pnKhuyenMai = new JPanel();
        pnSanPham = new JPanel();
        pnNhanVien = new JPanel();
        pnKhachHang = new JPanel();
        pnThongKe = new JPanel();

        pnCard.add(pnBanHang, "1");
        pnCard.add(pnKhuyenMai, "2");
        pnCard.add(pnSanPham, "3");
        pnCard.add(pnNhanVien, "4");
        pnCard.add(pnKhachHang, "5");
        pnCard.add(pnThongKe, "6");

        banHangPanel = new PnQuanLyBanHangGUI();
        pnBanHang.setLayout(new BorderLayout());
        pnBanHang.add(banHangPanel, BorderLayout.CENTER);

        PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
        PhanQuyen quyen = phanQuyenBUS.getPhanQuyen(DangNhapBUS.quyenTK);

        if (quyen.getQlSanPham() == 1) {
            sanPhamPanel = new PnQuanLySanPhamGUI();
            pnSanPham.setLayout(new BorderLayout());
            pnSanPham.add(sanPhamPanel, BorderLayout.CENTER);
            lblSanPham.setVisible(true);

            khuyenMaiPanel = new PnQuanLyKhuyenMaiGUI();
            pnKhuyenMai.setLayout(new BorderLayout());
            pnKhuyenMai.add(khuyenMaiPanel, BorderLayout.CENTER);
            lblKhuyenMai.setVisible(true);
        }

        if (quyen.getQlNhanVien() == 1) {
            nhanVienPanel = new PnQuanLyNhanVienGUI();
            pnNhanVien.setLayout(new BorderLayout());
            pnNhanVien.add(nhanVienPanel, BorderLayout.CENTER);
            lblNhanVien.setVisible(true);
        }

        if (quyen.getQlKhachHang() == 1) {
            khachHangPanel = new PnQuanLyKhachHangGUI();
            pnKhachHang.setLayout(new BorderLayout());
            pnKhachHang.add(khachHangPanel, BorderLayout.CENTER);
            lblKhachHang.setVisible(true);
        }

        if (quyen.getThongKe() == 1) {
            thongKePanel = new PnQuanLyThongKeGUI();
            pnThongKe.setLayout(new BorderLayout());
            pnThongKe.add(thongKePanel, BorderLayout.CENTER);
            lblThongKe.setVisible(true);
        }

        pnMain.add(pnCard);
        con.add(pnMain);
    }

    private JLabel createMenuLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel createHeaderButton(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(MEDIUM_BLUE);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        return label;
    }

    private void addEvents() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnDoiMatKhau.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DlgDoiMatKhau().setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnDoiMatKhau.setOpaque(true);
                btnDoiMatKhau.setBackground(HEADER_BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDoiMatKhau.setBackground(MEDIUM_BLUE);
            }
        });

        btnMinimize.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thuNhoFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMinimize.setOpaque(true);
                btnMinimize.setBackground(HEADER_BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMinimize.setBackground(MEDIUM_BLUE);
            }
        });

        btnClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thoatChuongTrinh();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setOpaque(true);
                btnClose.setBackground(CLOSE_BUTTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setBackground(MEDIUM_BLUE);
            }
        });

        for (int i = 0; i < listMenuLeft.size(); i++) {
            final int index = i;
            listMenuLeft.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (JLabel lbl : listMenuLeft) {
                        lbl.setBackground(clLeftItem);
                        lbl.setForeground(SIDEBAR_TEXT);
                    }
                    listMenuLeft.get(index).setBackground(clLeftItemSelected);
                    listMenuLeft.get(index).setForeground(SIDEBAR_TEXT);
                    cardMenuLeftGroup.show(pnCard, String.valueOf(index + 1));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (listMenuLeft.get(index).getBackground() != clLeftItemSelected) {
                        listMenuLeft.get(index).setBackground(clLeftItemHover);
                        listMenuLeft.get(index).setForeground(SIDEBAR_TEXT_HOVER);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (listMenuLeft.get(index).getBackground() != clLeftItemSelected) {
                        listMenuLeft.get(index).setBackground(clLeftItem);
                        listMenuLeft.get(index).setForeground(SIDEBAR_TEXT);
                    }
                }
            });
        }
    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void thuNhoFrame() {
        this.setState(Frame.ICONIFIED);
    }

    private void thoatChuongTrinh() {
        System.exit(0);
    }
}

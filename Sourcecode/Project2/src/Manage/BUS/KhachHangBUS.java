package Manage.BUS;
import Manage.DAO.KhachHangDAO;
import Manage.DTO.KhachHang;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class KhachHangBUS {

    private ArrayList<KhachHang> listKhachHang = null;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();

    public void docDanhSach() {
        this.listKhachHang = khachHangDAO.getListKhachHang();
    }

    public ArrayList<KhachHang> getListKhachHang() {
        if (listKhachHang == null)
            docDanhSach();
        return listKhachHang;
    }

    public ArrayList<KhachHang> timKiemKhachHang(String txtMin, String txtMax) {
        if (txtMax.trim().equals("") && txtMin.trim().equals(""))
            return listKhachHang;
        try {
            ArrayList<KhachHang> dskh = new ArrayList<>();
            txtMin = txtMin.replace(",", "");
            txtMax = txtMax.replace(",", "");
            int min = Integer.parseInt(txtMin);
            int max = Integer.parseInt(txtMax);
            for (KhachHang kh : listKhachHang) {
                if (kh.getTongChiTieu() >= min && kh.getTongChiTieu() <= max) {
                    dskh.add(kh);
                }
            }
            return dskh;
        } catch (Exception e) {
            new MyDialog("Hay nhap gia tri phu hop!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<KhachHang> timKiemKhachHang(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<KhachHang> dskh = new ArrayList<>();
        for (KhachHang kh : listKhachHang) {
            String ho = kh.getHo().toLowerCase();
            String ten = kh.getTen().toLowerCase();
            String gioiTinh = kh.getGioiTinh().toLowerCase();
            if (ho.contains(tuKhoa) || ten.contains(tuKhoa) || gioiTinh.contains(tuKhoa)) {
                dskh.add(kh);
            }
        }
        return dskh;
    }

    public boolean themKhachHang(String ho, String ten, String gioiTinh) {
        if (ten.trim().equals("")) {
            new MyDialog("Khong duoc de trong ten!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (gioiTinh.equals("Chon gioi tinh")) {
            new MyDialog("Hay chon gioi tinh!", MyDialog.ERROR_DIALOG);
            return false;
        }
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        kh.setTongChiTieu(0);
        boolean flag = khachHangDAO.addKhachHang(kh);
        if (flag) {
            new MyDialog("Them thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Them that bai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaKhachHang(String ma, String ho, String ten, String gioiTinh) {
        if (ten.trim().equals("")) {
            new MyDialog("Khong duoc de trong ten!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (gioiTinh.equals("Chon gioi tinh")) {
            new MyDialog("Hay chon gioi tinh!", MyDialog.ERROR_DIALOG);
            return false;
        }
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        boolean flag = khachHangDAO.updateKhachHang(Integer.parseInt(ma), kh);
        if (flag) {
            new MyDialog("Sua thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sua that bai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean xoaKhachHang(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Ban co chac chan muon xoa?", MyDialog.WARNING_DIALOG);
            if(dlg.getAction() == MyDialog.CANCEL_OPTION)
                return false;
            flag = khachHangDAO.deleteKhachHang(maKH);
        } catch (Exception e) {
            new MyDialog("Chua chon khach hang!", MyDialog.ERROR_DIALOG);
        }
        if (flag) {
            new MyDialog("Xoa thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Xoa that bai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}


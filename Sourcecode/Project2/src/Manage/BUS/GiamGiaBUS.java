package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DTO.GiamGia;
import Manage.DAO.GiamGiaDAO;
import java.util.ArrayList;
import java.util.Date;

public class GiamGiaBUS {

    private ArrayList<GiamGia> listGiamGia = null;
    private GiamGiaDAO giamGiaDAO = new GiamGiaDAO();

    public GiamGiaBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listGiamGia = giamGiaDAO.getDanhSachMaGiam();
    }

    public ArrayList<GiamGia> getDanhSachGiamGia() {
        if (this.listGiamGia == null)
            docDanhSach();
        return this.listGiamGia;
    }

    public boolean themMaGiam(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ten.equals("")) {
            new MyDialog("Hay nhap ten chuong trinh khuyen mai!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngay ket thuc khong hop le!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.themMaGiam(gg);
        } catch (Exception e) {
            new MyDialog("Hay nhap so hop le!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Them moi thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Them moi that bai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaMaGiam(String ma, String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ma.equals("")) {
            new MyDialog("Chua chon ma de sua!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Hay nhap ten chuong trinh khuyen mai!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngay ket thuc khong hop le!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int maGiam = Integer.parseInt(ma);
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setMaGiam(maGiam);
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.suaMaGiam(gg);
        } catch (Exception e) {
            new MyDialog("Hay nhap so hop le!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Sua thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sua that bai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
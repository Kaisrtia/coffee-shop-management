package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DAO.NhanVienDAO;
import Manage.DTO.NhanVien;
import java.util.ArrayList;

public class NhanVienBUS {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVien> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nvDAO.getDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String chucVu) {
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ten.equals("")) {
            new MyDialog("Ten khong duoc de trong!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialog("Chuc vu khong duoc de trong!", MyDialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvDAO.themNhanVien(nv);
        if (!flag) {
            new MyDialog("Them that bai!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Them thanh cong!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ho, String ten, String gioiTinh, String chucVu) {
        int maNV = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ten.equals("")) {
            new MyDialog("Ten khong duoc de trong!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialog("Chuc vu khong duoc de trong!", MyDialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvDAO.updateNhanVien(nv);
        if (!flag) {
            new MyDialog("Cap nhap that bai!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cap nhap thanh cong!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<>();
        for (NhanVien nv : listNhanVien) {
            if (nv.getHo().toLowerCase().contains(tuKhoa) || nv.getTen().toLowerCase().contains(tuKhoa) ||
                    nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Ban co chac chan muon xoa?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = nvDAO.deleteNhanVien(maNV);
                if (flag) {
                    new MyDialog("Xoa thanh cong!", MyDialog.SUCCESS_DIALOG);
                } else {
                    new MyDialog("Khong the xoa nhan vien nay vi day la nhan vien cuoi cung cua loai chuc vu nay!", MyDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            new MyDialog("Chua chon nhan vien!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    
}

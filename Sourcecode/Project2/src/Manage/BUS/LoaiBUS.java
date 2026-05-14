package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DTO.LoaiSP;
import Manage.DAO.LoaiDAO;
import java.util.ArrayList;

public class LoaiBUS {

    private LoaiDAO loaiDAO = new LoaiDAO();
    private ArrayList<LoaiSP> listLoai = null;

    public LoaiBUS() {
        docDanhSachLoai();
    }

    public void docDanhSachLoai() {
        this.listLoai = loaiDAO.getDanhSachLoai();
    }

    public ArrayList<LoaiSP> getDanhSachLoai() {
        if (listLoai == null) {
            docDanhSachLoai();
        }
        return this.listLoai;
    }

    public String getTenLoai(int ma) {
        for (LoaiSP loai : listLoai) {
            if (loai.getMaLoai() == ma) {
                return loai.getMaLoai() + " - " + loai.getTenLoai();
            }
        }
        return "";
    }

    public boolean themLoai(int maLoai, String tenLoai) {
        if (tenLoai.trim().equals("")) {
            new MyDialog("Khong duoc de trong ten loai!", MyDialog.ERROR_DIALOG);
            return false;
        }
        maLoai += 1;
        LoaiSP loai = new LoaiSP(maLoai, tenLoai);
        if (loaiDAO.themLoai(loai)) {
            new MyDialog("Them thanh cong!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Them that bai!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean xoaLoai(String ma) {
        if (ma.trim().equals("")) {
            new MyDialog("Chua chon loai de xoa!", MyDialog.SUCCESS_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (loaiDAO.xoaLoai(maLoai)) {
            new MyDialog("Xoa thanh cong!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Xoa that bai! Loai co san pham con", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suaLoai(String ma, String ten) {
        if (ten.trim().equals("")) {
            new MyDialog("Khong duoc de trong ten loai!", MyDialog.ERROR_DIALOG);
            return false;
        }
        int maLoai = Integer.parseInt(ma);
        if (loaiDAO.suaLoai(maLoai, ten)) {
            new MyDialog("Sua thanh cong!", MyDialog.SUCCESS_DIALOG);
            return true;
        } else {
            new MyDialog("Sua that bai!", MyDialog.ERROR_DIALOG);
            return false;
        }
    }

}

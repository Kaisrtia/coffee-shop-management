package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DAO.SanPhamDAO;
import Manage.DTO.SanPham;
import java.util.ArrayList;

public class SanPhamBUS {
    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamBUS() {
        docListSanPham();
    }

    public void docListSanPham() {
        listSanPham = spDAO.getListSanPham();
    }

    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }

    public SanPham getSanPham(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoTen(String ten) {
        ArrayList<SanPham> dssp = new ArrayList<>();
        String tenLower = ten.toLowerCase();
        for (SanPham sp : listSanPham) {
            if (sp.getTenSP().toLowerCase().contains(tenLower)) {
                dssp.add(sp);
            }
        }
        return dssp;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maLoai = Integer.parseInt(ma);
                ArrayList<SanPham> dssp = new ArrayList<>();
                for (SanPham sp : listSanPham) {
                    if (sp.getMaLoai() == maLoai) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }
        return null;
    }

    public String getAnh(String ma) {
        try {
            int maSP = Integer.parseInt(ma);
            return spDAO.getAnh(maSP);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        spDAO.capNhatSoLuongSP(ma, soLuongMat);
    }

    public boolean themSanPham(String ten, String loai, String soLuong, String donViTinh, String anh, String donGia) {
        if (ten.trim().isEmpty()) {
            new MyDialog("Ten SP khong duoc de trong!", MyDialog.ERROR_DIALOG);
            return false;
        }

        if (donViTinh.trim().isEmpty()) {
            new MyDialog("Vui long dien don vi tinh!", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            if (maLoai == 0) {
                new MyDialog("Vui long chon Loai san pham!", MyDialog.ERROR_DIALOG);
                return false;
            }

            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia.replace(",", ""));

            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.themSanPham(sp)) {
                new MyDialog("Them thanh cong!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Them that bai!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (NumberFormatException e) {
            new MyDialog("Nhap so hop le cho Don gia va So luong!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean nhapSanPhamTuExcel(String ten, String loai, String soLuong, String donViTinh, String anh, String donGia) {
        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia.replace(",", ""));

            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            return spDAO.nhapSanPhamTuExcel(sp);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean xoaSanPham(String ma) {
        if (ma.trim().isEmpty()) {
            new MyDialog("Chua chon san pham de xoa!", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            int maSP = Integer.parseInt(ma);
            if (spDAO.xoaSanPham(maSP)) {
                new MyDialog("Xoa thanh cong!", MyDialog.SUCCESS_DIALOG);
                return true;
            }
            new MyDialog("Xoa that bai!", MyDialog.ERROR_DIALOG);
        } catch (NumberFormatException e) {
            new MyDialog("Ma san pham khong hop le!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean suaSanPham(String ma, String ten, String loai, String soLuong, String donViTinh, String anh, String donGia) {
        try {
            if (ma.trim().isEmpty()) {
                new MyDialog("Chua chon san pham de sua!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (ten.trim().isEmpty()) {
                new MyDialog("Ten SP khong duoc de trong!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (donViTinh.trim().isEmpty()) {
                new MyDialog("Vui long dien Don vi tinh!", MyDialog.ERROR_DIALOG);
                return false;
            }

            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            if (maLoai == 0) {
                new MyDialog("Vui long chon Loai san pham!", MyDialog.ERROR_DIALOG);
                return false;
            }

            int maSP = Integer.parseInt(ma);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia.replace(",", ""));

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.suaSanPham(sp)) {
                new MyDialog("Sua thanh cong!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Sua that bai!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (NumberFormatException e) {
            new MyDialog("Nhap so hop le cho Don gia va So luong!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public String getTenSP(int maSP) {
        for (SanPham sp : listSanPham) {
            if (sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return "";
    }
}
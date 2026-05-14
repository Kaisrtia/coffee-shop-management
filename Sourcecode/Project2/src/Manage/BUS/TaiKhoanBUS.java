package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DTO.TaiKhoan;
import Manage.DAO.TaiKhoanDAO;
public class TaiKhoanBUS {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public String getTenDangNhapTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getTenDangNhapTheoMa(maNV);
    }

    public String getQuyenTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getQuyenTheoMa(maNV);
    }

    public void datLaiMatKhau(String ma, String tenDangNhap) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiMatKhau(maNV, tenDangNhap);
        if (flag) {
            new MyDialog("Dat lai MK thanh cong! Mat khau moi la: " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Dat lai that bai!", MyDialog.ERROR_DIALOG);
        }
    }

    public void datLaiQuyen(String ma, String quyen) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiQuyen(maNV, quyen);
        if (flag) {
            new MyDialog("Dat lai thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Dat lai that bai!", MyDialog.ERROR_DIALOG);
        }
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }

    public boolean themTaiKhoan(String ma, String tenDangNhap, String quyen) {
        int maNV = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")) {
            new MyDialog("Khong de trong ten dang nhap!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            MyDialog dlg = new MyDialog("Ten dang nhap da trung! Co the tai khoan bi khoa, thuc hien mo khoa?", MyDialog.WARNING_DIALOG);
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                moKhoaTaiKhoan(ma);
                return true;
            }
            return false;
        }
        boolean flag = taiKhoanDAO.themTaiKhoan(maNV, tenDangNhap, quyen);
        if (flag) {
            new MyDialog("Cap tai khoan thanh cong! Mat khau la " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Cap tai khoan that bai! ", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public void khoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.khoaTaiKhoan(maNV);
        if (flag) {
            new MyDialog("Khoa tai khoan thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Khoa tai khoan that bai!", MyDialog.ERROR_DIALOG);
        }
    }

    public void moKhoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.moKhoaTaiKhoan(maNV);
        if (flag) {
            new MyDialog("Mo khoa tai khoan thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Mo khoa tai khoan that bai!", MyDialog.ERROR_DIALOG);
        }
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        if(!matKhauMoi.equals(nhapLaiMatKhau)) {
            new MyDialog("Mat khau moi khong khop!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = taiKhoanDAO.doiMatKhau(matKhauCu, matKhauMoi);
        if (flag) {
            new MyDialog("Doi thanh cong!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Mat khau cu sai!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public int getTrangThai(String maNV) {
        int ma = Integer.parseInt(maNV);
        return taiKhoanDAO.getTrangThai(ma);
    }

}

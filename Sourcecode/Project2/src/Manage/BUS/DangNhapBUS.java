package Manage.BUS;

import MyCustom.MyDialog;
import Manage.DAO.DangNhapDAO;
import Manage.DAO.TaiKhoanDAO;
import Manage.DTO.TaiKhoan;
import Manage.DTO.PhanQuyen;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class DangNhapBUS {

    private final static int EMPTY_ERROR = 1;
    private final static int WRONG_ERROR = 2;
    public static TaiKhoan taiKhoanLogin = null;
    public static String quyenTK = null;
    private DangNhapDAO dangNhapDAO = new DangNhapDAO();
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public DangNhapBUS() {
    }

    public TaiKhoan getTaiKhoanDangNhap(String user, String password) {
        if (kiemTraDangNhap(user, password) == EMPTY_ERROR) {
            new MyDialog("Khong duoc de trong thong tin!", MyDialog.ERROR_DIALOG);
            return null;
        }
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);
        taiKhoanLogin = account;

        if (account == null) {
            new MyDialog("Sai thong tin dang nhap hoac tai khoan da bi khoa!", MyDialog.ERROR_DIALOG);
        } else {
            PhanQuyenBUS phanQuyenBUS = new PhanQuyenBUS();
            quyenTK = account.getQuyen();
            PhanQuyen phanQuyen = phanQuyenBUS.getPhanQuyen(account.getQuyen());
            if (phanQuyen == null) {
                new MyDialog("Khong tim thay quyen cua tai khoan!", MyDialog.ERROR_DIALOG);
                return null;
            }
            new MyDialog("Dang nhap thanh cong!", MyDialog.SUCCESS_DIALOG);
        }
        return account;
    }

    private int kiemTraDangNhap(String user, String password) {
        user = user.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");
        int result = 0;

        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(user);
        tk.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        TaiKhoan account = dangNhapDAO.dangNhap(tk);

        if (user.length() <= 0 || password.length() <= 0) {
            result = EMPTY_ERROR;
        } else if (account == null) {
            result = WRONG_ERROR;
        }
        return result;
    }

}
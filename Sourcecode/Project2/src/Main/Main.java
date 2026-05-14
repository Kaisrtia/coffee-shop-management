package Main;

import MyCustom.MyConnect;
import Manage.GUI.DangNhapGUI;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class chinh cua ung dung
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Phuong thuc main cua ung dung
     * @param args Tham so dong lenh
     */
    public static void main(String[] args) {
        try {
            new MyConnect();
            changLNF("Windows");
            DangNhapGUI login = new DangNhapGUI();
            login.showWindow();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Loi khoi dong ung dung", e);
            JOptionPane.showMessageDialog(null, 
                "Khong the khoi dong ung dung. Vui long kiem tra lai ket noi database.",
                "Loi",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Thay doi giao dien cua ung dung
     * @param nameLNF Ten giao dien can thay doi
     */
    public static void changLNF(String nameLNF) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.WARNING, "Khong the thay doi giao dien", ex);
        }
    }
}
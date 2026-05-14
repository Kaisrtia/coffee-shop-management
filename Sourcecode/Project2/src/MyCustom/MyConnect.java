package MyCustom;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MyConnect {

    public static Connection conn = null;

    private String serverName = "localhost";
    private String dbName = "coffee_shop_management";
    private String userName = "root";
    private String password = "Nguyen17";
    private String port = "3306";

    public MyConnect() {
        loadConnectVariables();

        String strConnect = "jdbc:mysql://" + serverName + ":" + port + "/" + dbName
                + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&connectTimeout=5000";

        try {
            conn = DriverManager.getConnection(strConnect, userName, password);
            System.out.println("Ket noi thanh cong!");
        } catch (SQLException ex) {
            new MyDialog("Khong ket noi duoc toi CSDL!\n"
                    + "Kiem tra MySQL dang chay, database `" + dbName + "` da duoc tao, "
                    + "va thong tin trong ConnectVariable.txt.\n"
                    + ex.getMessage(), MyDialog.ERROR_DIALOG);
            System.exit(0);
        }
    }

    private void loadConnectVariables() {
        Path configPath = findConfigPath();
        if (configPath == null) {
            return;
        }

        try {
            List<String> values = Files.readAllLines(configPath, StandardCharsets.UTF_8);

            if (values.size() > 0 && !values.get(0).trim().isEmpty()) {
                serverName = values.get(0).trim();
            }
            if (values.size() > 1 && !values.get(1).trim().isEmpty()) {
                dbName = values.get(1).trim();
            }
            if (values.size() > 2 && !values.get(2).trim().isEmpty()) {
                userName = values.get(2).trim();
            }
            if (values.size() > 3) {
                password = values.get(3).trim();
            }
            if (values.size() > 4 && !values.get(4).trim().isEmpty()) {
                port = values.get(4).trim();
            }
        } catch (IOException ex) {
            System.out.println("Khong doc duoc ConnectVariable.txt, dung cau hinh mac dinh.");
        }
    }

    private Path findConfigPath() {
        Path[] paths = {
            Paths.get("ConnectVariable.txt"),
            Paths.get("Sourcecode", "Project2", "ConnectVariable.txt")
        };

        for (Path path : paths) {
            if (Files.exists(path)) {
                return path;
            }
        }
        return null;
    }
}

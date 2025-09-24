package vn.iotstar;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private final String serverName = "FANGSHICHAN"; 
    private final String dbName = "ShoppingServletService";
    private final String portNumber = "1433";
    private final String instance = ""; 
    private final String userID = "sa";
    private final String password = "Phuongthi123";

    public Connection getConnection() throws Exception {
        StringBuilder url = new StringBuilder("jdbc:sqlserver://")
                .append(serverName).append(":").append(portNumber);

        if (instance != null && !instance.trim().isEmpty()) {
            url.append("\\").append(instance);
        }

        url.append(";databaseName=").append(dbName)
           .append(";encrypt=true;trustServerCertificate=true;");

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(url.toString(), userID, password);
    }

    public static void main(String[] args) {
        try (Connection conn = new DBConnect().getConnection()) {
            if (conn != null) {
                System.out.println("✅ Kết nối DB thành công!");
            } else {
                System.out.println("❌ Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

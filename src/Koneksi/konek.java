package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class konek {
    private static Connection mysqlconfik;
    public static Connection configDBek()throws SQLException{
        try {
            String host = "jdbc:mysql://localhost:3306/db_sikate";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfik=DriverManager.getConnection(host, user, pass);
        } catch (Exception e) {
            System.err.println("Koneksi gagal "+e.getMessage());
        }
        return mysqlconfik;
    }
}

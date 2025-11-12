
import com.sun.jdi.connect.spi.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mural
 */
public class DBCconnection {
      private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null ) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/online_banking", "root", "your_password"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

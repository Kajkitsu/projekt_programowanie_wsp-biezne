package kajkitsu.projektPW.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JDBCTest {

    private static final String url = "jdbc:mysql://localhost";

    private static final String user = "root";

    private static final String password = "zaq1@WSX";

    public static void main(String args[]) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Success");

        } catch (SQLException wyjatek) {
            //e.printStackTrace();
            //System.out.println("Problem z logowaniem\nProsze sprawdzic:\n nazwę użytkownika, hasło, nazwę bazy danych lub adres IP serwera");
            System.out.println("SQLException: " + wyjatek.getMessage());
            System.out.println("SQLState: " + wyjatek.getSQLState());
            System.out.println("VendorError: " + wyjatek.getErrorCode());
        }
    }
}
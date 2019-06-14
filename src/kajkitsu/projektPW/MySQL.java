package kajkitsu.projektPW;

import kajkitsu.projektPW.gui.TankRecord;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQL {

    private static final String url = "jdbc:mysql://localhost/game_tank?useSSL=false";
    private static final String user = "root";
    private static final String password = "zaq1@WSX";

    public static void main(String[] args) {
        getRecords();
        //insertTankToMySQL(1, "test", 2, 3, 4);
    }

    public static List<TankRecord> getRecords() {
        List<TankRecord> list = new ArrayList<TankRecord>();
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM tanks_produced";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                TankRecord tankRecord = new TankRecord(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("serial_number"),
                        rs.getInt("level"),
                        rs.getString("date_of_production"),
                        rs.getInt("sum_of_required_resources"));

                System.out.println(tankRecord.toString());
                list.add(tankRecord);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }


        return list;
    }

    public static void insertTankToMySQL(int id, String name, int serial_number, int level, int sum_of_required_resources) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTime = sdf.format(dt);

            String query = " insert into tanks_produced (id, name, serial_number, level, date_of_production, sum_of_required_resources)"
                    + " values (?, ?, ?, ?, ?, ?)";


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, name);
            preparedStmt.setInt(3, serial_number);
            preparedStmt.setInt(4, level);
            preparedStmt.setString(5, endTime);
            preparedStmt.setInt(6, sum_of_required_resources);

            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }


}
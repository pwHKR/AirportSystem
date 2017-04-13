import java.io.FileInputStream;
import java.lang.*;
import java.lang.System;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBHandler implements DataStorage {
    private final String dbName;
    private final String user;
    private final String password;
    private final String connectionURL;

    public DBHandler() {
        Properties p = loadProperties();
        dbName = p.getProperty("dbName");
        user = p.getProperty("user");
        password = p.getProperty("password");
        connectionURL = "jdbc:mysql://46.101.88.25/" + dbName + "?user=" + user + "&password=" + password + "&useSSL=false";
    }

    private Properties loadProperties() {
        Properties appProp = new Properties();
        try (FileInputStream fis = new FileInputStream("app1.properties")) {
            appProp.load(fis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return appProp;
    }

    public void printAirplanes() {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Airplane");

            while (rs.next()) {
                java.lang.System.out.printf("Passenger capacity: [%d] Airplane top speed --> [%s] Max luggage weight" +
                                "--> [%s] RegNumber --> [%s]%n",
                        rs.getInt("passengerCapacity"), rs.getString("maxSpeed"),
                        rs.getString("maxLuggageWeight"), rs.getString("regnumber"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertAirplane(int passengerCapacity, String maxSpeed, String maxLuggageWeight, String regNumber) {
        String command = String.format("INSERT INTO Airplane values (%d, %s, %s, '%s')", passengerCapacity, maxSpeed,
                maxLuggageWeight, regNumber);

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement statement = conn.createStatement();
            int rowsAffected = statement.executeUpdate(command);

            System.out.println("Rows affected:" + rowsAffected);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
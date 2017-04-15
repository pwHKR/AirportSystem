import java.io.FileInputStream;
import java.lang.System;
import java.sql.*;
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


    public void insertCustomer(Customer customer) {






        /*String command2 = String.format("INSERT INTO User values (%s, %s,'%s')", customer.getUserName(),
                customer.getPassword(), customer.geteMail()); */

        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = "INSERT INTO Person (firstName, lastName,isMale , country, ssn, adress) "
                    + " VALUES (?,?,? ,?,?,?)";

           /* String query2 = " INSERT INTO User (userName, password, eMail) " +
                    " VALUES (?,?,?) WHERE userName = Person_systemId ";

            PreparedStatement ps = conn.prepareStatement(query2);

            ps.setString(1,customer.getUserName());
            ps.setString(2,customer.getPassword());
            ps.setString(3,customer.geteMail()); */


            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, customer.getFirstName());
            preparedStmt.setString(2, customer.getLastName());
            preparedStmt.setInt(3, 1);
            preparedStmt.setString(4, customer.getCountry());
            preparedStmt.setString(5, customer.getSsn());
            preparedStmt.setString(6, customer.getAdress());


            preparedStmt.execute();
            //ps.executeUpdate();


            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}
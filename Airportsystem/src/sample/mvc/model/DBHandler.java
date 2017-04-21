package sample.mvc.model;

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

    public void insertAirplane(Airplane airplane) {
        String command = String.format("INSERT INTO Airplane values (%d, %s, %d, '%s')", airplane.getPassengerCapacity(),
                airplane.getMaxSpeed(), airplane.getMaxLuggageWeight(), airplane.getRegNumber());

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement statement = conn.createStatement();
            int rowsAffected = statement.executeUpdate(command);

            System.out.println("Rows affected:" + rowsAffected);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String printUserType(String Username) {


        String userType = null;


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT typeOfUser FROM User WHERE userName=(?)");


            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, Username);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                userType = rs.getString("password");
            }


        } catch (SQLException e) {

        }
        return userType;

    }


    public void insertUser(User customer, String typeOfUser) {

        String si = null;


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            Statement stmt = conn.createStatement();

            String checkSSNQuery = "SELECT * FROM Person WHERE ssn= '" + customer.getSsn() + "'";

            ResultSet resultSet = stmt.executeQuery(checkSSNQuery);
            if (!resultSet.next()){
                String query = "INSERT INTO Person (firstName, lastName,isMale , country, ssn, adress) "
                        + " VALUES (?,?,? ,?,?,?)";

                String query2 = ("SELECT systemId FROM Person  WHERE ssn =(?)");

                String query3 = " INSERT INTO User (userName, password, eMail,Person_systemId,typeOfUser) " +
                        " VALUES (?,?,?,?,?) ";


                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, customer.getFirstName());
                preparedStmt.setString(2, customer.getLastName());
                preparedStmt.setBoolean(3, customer.isMale());
                preparedStmt.setString(4, customer.getCountry());
                preparedStmt.setString(5, customer.getSsn());
                preparedStmt.setString(6, customer.getAdress());

                preparedStmt.execute();

                PreparedStatement ps2 = conn.prepareStatement(query2);

                ps2.setString(1, customer.getSsn());

                ResultSet rs = ps2.executeQuery();

                while (rs.next()) {

                    si = rs.getString("systemId");
                }

                PreparedStatement ps = conn.prepareStatement(query3);

                ps.setString(1, customer.getUserName());
                ps.setString(2, customer.getPassword());
                ps.setString(3, customer.geteMail());
                ps.setString(4, si);
                ps.setString(5, typeOfUser);

                ps.execute();
                conn.close();

            }else{
                MyAlert.ssnExistsErr();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insertTrip(Trip trip) {

    }


    public String matchPassword(String UserName) {

        String pass = null;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT password FROM User WHERE userName=(?)");

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, UserName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                pass = rs.getString("password");
            }

        } catch (SQLException e) {

        }
        return pass;
    }
}
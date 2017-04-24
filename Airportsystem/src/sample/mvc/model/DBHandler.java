package sample.mvc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        String command = String.format("INSERT INTO Airplane values (%s,%d, %s, %d, '%s')", airplane.getModel(), airplane.getPassengerCapacity(),
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
            if (!resultSet.next()) {
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
            e.printStackTrace();
        }
        return pass;

    }


    public String getLocationId(Location location) {

        String iD = null;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = "SELECT locationId FROM Location WHERE city = ? AND country = ? AND airportName = ? ";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, location.getCity());
            ps.setString(2, location.getCountry());
            ps.setString(3, location.getAirport());


            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                iD = rs.getString("locationId");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(iD);
        return iD;


    }


    public ObservableList<String> getCountries() {

        ObservableList<String> countries = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT DISTINCT country  FROM AirportSystemdb.Location ORDER BY country ASC ");


            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery(query);


            while (rs.next()) {

                countries.add(rs.getString("country"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;


    }

    public ObservableList<String> getCities(String inputCountry) {


        ObservableList<String> cities = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = ("SELECT DISTINCT city FROM AirportSystemdb.Location WHERE country = '" + inputCountry + "'ORDER BY city ASC");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                cities.add(resultSet.getString("city"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;


    }


    public ObservableList<String> getAirports(String inputCity) {


        ObservableList<String> airports = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = ("SELECT DISTINCT airportName FROM AirportSystemdb.Location WHERE city = '" + inputCity + "'ORDER BY airportName ASC");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                airports.add(resultSet.getString("airportName"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;


    }

    public ObservableList<String> getWorkers() {


        ObservableList<String> workers = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            //"SELECT Person.firstName, lastName FROM AirportSystemdb.Person, AirportSystemdb.User WHERE Person.systemId = User.Person_systemId AND User.typeOfUser = 'Employee'"
            String query = ("select Person_systemId from AirportSystemdb.User where typeOfUser = 'Employee'");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                workers.add(resultSet.getString("Person_systemId"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workers;


    }

    public ObservableList<String> getWorkerinfo(String selected) {


        ObservableList<String> workers = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = ("SELECT * FROM AirportSystemdb.Person, AirportSystemdb.User WHERE Person.systemId = User.Person_systemId AND AirportSystemdb.User.typeOfUser = 'Employee' AND AirportSystemdb.User.Person_systemId = '" + Integer.parseInt(selected) + "'");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {


                workers.add("Full name: " + resultSet.getString("firstName") + " " + resultSet.getString("lastName") +
                        "\nSSN: " + resultSet.getString("ssn") + "\nAdress: " + resultSet.getString("adress") + "\nCountry: " + resultSet.getString("country") + "\nE-Mail: " + resultSet.getString("email"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workers;


    }


    public void setUserOnline(String userName) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = "UPDATE AirportSystemdb.User SET User.online = 1 WHERE User.userName = ?;";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, userName);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setUserOffline(String userName) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = "UPDATE AirportSystemdb.User SET User.online = 0 WHERE User.userName = ?;";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, userName);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeWorker(String sysId) {

        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            //"SELECT Person.firstName, lastName FROM AirportSystemdb.Person, AirportSystemdb.User WHERE Person.systemId = User.Person_systemId AND User.typeOfUser = 'Employee'"
            //String query = ("DELETE FROM AirportSystemdb.User, AirportSystemdb.Person WHERE AirportSystemdb.User.Person_systemId = " + sysId + "");

            System.out.println(sysId);

            String q1 = "DELETE FROM AirportSystemdb.User WHERE AirportSystemdb.User.Person_systemId = ?;";
            String q2 = "DELETE FROM AirportSystemdb.Person WHERE AirportSystemdb.Person.systemId = ?;";

            PreparedStatement st = conn.prepareStatement(q1);
            PreparedStatement pst = conn.prepareStatement(q2);

            st.setString(1, sysId);
            pst.setString(1, sysId);

            st.executeUpdate();
            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public int getUsersOnline() {
        int amount = 0;
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String q1 = "SELECT count(*) FROM AirportSystemdb.User WHERE AirportSystemdb.online = 1";

            PreparedStatement st = conn.prepareStatement(q1);


            amount = st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amount;
    }

    @Override
    public boolean isUserOnline(String userName) {
        return false;
    }


}
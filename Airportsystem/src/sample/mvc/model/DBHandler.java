package sample.mvc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBHandler implements DataStorage {

    private MyAlert myAlert = new MyAlert();

    private final String connectionURL;

    public DBHandler() {
        Properties p = loadProperties();
        String dbName = p.getProperty("dbName");
        String user = p.getProperty("user");
        String password = p.getProperty("password");
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

    public ObservableList<String> getAirplaneRegNumber(String selected) {

        ObservableList<String> LocationInfo = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = (" SELECT * FROM AirportSystemdb.Airplane where PSTR_idPSTR = '" + Integer.parseInt(selected) + "'");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {


                LocationInfo.add("\n\nAirplane\n" + "Model: " + resultSet.getString("model") +
                        "\nPassenger: " + resultSet.getString("passengerCapacity") + "\nMax Speed: " +
                        resultSet.getString("maxSpeed") + "\nLuggage: " +
                        resultSet.getString("maxLuggageWeight") + "\nRegistration: " +
                        resultSet.getString("regNumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return LocationInfo;


    }

    public void insertAirplane(Airplane airplane, String select) {

        int intSelect = Integer.parseInt(select);


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = "INSERT INTO Airplane (model, passengerCapacity,maxSpeed , maxLuggageWeight, regNumber, PSTR_idPSTR) "
                    + " VALUES (?,?,? ,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, airplane.getModel());
            ps.setInt(2, airplane.getPassengerCapacity());
            ps.setString(3, airplane.getMaxSpeed());
            ps.setInt(4, airplane.getMaxLuggageWeight());
            ps.setString(5, airplane.getRegNumber());
            ps.setInt(6, intSelect);

            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
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

                userType = rs.getString("typeOfUser");
            }


        } catch (SQLException e) {
            e.printStackTrace();
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

                String checkUserName = "SELECT * FROM AirportSystemdb.User WHERE userName= '" + customer.getUserName() + "'";
                ResultSet resultset1 = stmt.executeQuery(checkUserName);

                if (!resultset1.next()) {

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
                    ps.setString(3, customer.getEmail());
                    ps.setString(4, si);
                    ps.setString(5, typeOfUser);


                    ps.execute();
                    conn.close();
                } else {
                    myAlert.userNameExists();
                }
            } else {
                myAlert.ssnExistsErr();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String matchPassword(String UserName) {

        String pass = null;
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT password FROM User WHERE userName=(?)");

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, UserName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                pass = rs.getString("password");
            } else {
                return pass;
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
            String query = ("SELECT Person_systemId FROM AirportSystemdb.User WHERE typeOfUser = 'Employee'");


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


    public int getUsersOnlineCount() {
        int amount = 0;
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String q1 = "SELECT count(*) FROM AirportSystemdb.User WHERE AirportSystemdb.User.online = 1";

            Statement st = conn.createStatement();

            ResultSet resultSet = st.executeQuery(q1);

            while (resultSet.next()) {
                amount = resultSet.getInt("count(*)");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amount;
    }

    @Override
    public boolean isUserOnline(String userName) {


        boolean isOnline = false;
        int tinyInt = 0;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String q1 = "SELECT online FROM AirportSystemdb.User where userName = '" + userName + "'";


            Statement statement = conn.prepareStatement(q1);


            ResultSet rs = statement.executeQuery(q1);


            while (rs.next()) {
                tinyInt = rs.getInt("online");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tinyInt == 0) {
            isOnline = false;
        }

        if (tinyInt == 1) {
            isOnline = true;
        }


        return isOnline;
    }


    public void AddLocationPSTR(String idPSTR, String locationId) {

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String insertQuery = "INSERT INTO AirportSystemdb.PSTR (idPSTR) VALUES (?)";

            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setInt(1, Integer.valueOf(idPSTR));

            insertStatement.execute();


            String query = "UPDATE AirportSystemdb.Location SET Location.PSTR_idPSTR = ? WHERE Location.locationId = ?;";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, idPSTR);
            ps.setString(2, locationId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<String> getPSTR() {


        ObservableList<String> pstr = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            //"SELECT Person.firstName, lastName FROM AirportSystemdb.Person, AirportSystemdb.User WHERE Person.systemId = User.Person_systemId AND User.typeOfUser = 'Employee'"
            String query = ("SELECT PSTR_idPSTR  FROM AirportSystemdb.Location WHERE PSTR_idPSTR > 0");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                pstr.add(resultSet.getString("PSTR_idPSTR"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pstr;


    }


    public ObservableList<String> getPSTRLocationInfo(String selected) {


        ObservableList<String> LocationInfo = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = (" SELECT * FROM AirportSystemdb.Location where PSTR_idPSTR = '" + Integer.parseInt(selected) + "'");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {


                LocationInfo.add("City: " + resultSet.getString("city") +
                        "\nCountry: " + resultSet.getString("country") + "\nAirport: " + resultSet.getString("airportName"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return LocationInfo;


    }


    public ObservableList<String> getSystemVariables() {

        ObservableList<String> SystemVariables = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = ("SHOW GLOBAL STATUS WHERE VALUE > 0;");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                SystemVariables.add(resultSet.getString("Variable_name") + ": " +
                        resultSet.getString("value") + "\n");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return SystemVariables;
    }


    public ObservableList<String> getUsersOnline() {

        ObservableList<String> UsersOnline = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = ("SELECT userName FROM User WHERE online = 1");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                UsersOnline.add("" + resultSet.getString("userName") + "" + "\n");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UsersOnline;


    }


    public void updateTimeStampUser(String userName) {


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "UPDATE User SET time = current_timestamp where userName = '" + userName + "'";

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<String> searchForPassword(String userName) {
        ObservableList<String> userInformation = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String q1 = "SELECT password from AirportSystemdb.User WHERE userName = '" + userName + "';";

            Statement stmt = conn.createStatement();
            stmt.addBatch(q1);
            ResultSet resultSet = stmt.executeQuery(q1);

            while (resultSet.next()) {
                userInformation.add(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userInformation;
    }


    public ObservableList<String> getAllAirplaneRegNumbers() {
        ObservableList<String> airPlanes = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("SELECT regNumber FROM AirportSystemdb.Airplane;");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                airPlanes.add(resultSet.getString("regNumber"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airPlanes;
    }


    public ObservableList<String> getAirplaneInfo(String regNumber) {
        ObservableList<String> airPlanes = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("SELECT * FROM AirportSystemdb.Airplane WHERE regNumber = '" + regNumber + "';");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {


                airPlanes.add("Model: " + resultSet.getString("model") +
                        "\nPassenger Capacity:: " + resultSet.getString("passengerCapacity") + "" +
                        "\nMax Speed:: " + resultSet.getString("maxSpeed") +
                        "\nMax Luggage Weight: " + resultSet.getString("maxLuggageWeight") +
                        "\nReg Number: " + resultSet.getString("regNumber"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airPlanes;
    }


    public void insertFlight(Flight flight, boolean newFlightStatus) {

        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            if (newFlightStatus) {
                String query = "INSERT INTO Flight (flightStatus, gate, Airplane_regNumber) "
                        + " VALUES (?,?,?)";

                PreparedStatement ps = conn.prepareStatement(query);

                ps.setString(1, flight.getFlightStatus());
                ps.setString(2, flight.getGate());
                ps.setString(3, flight.getRegNumber());

                ps.execute();

            } else {
                String query = "INSERT INTO Flight (gate, Airplane_regNumber) "
                        + " VALUES (?,?)";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, flight.getGate());
                ps.setString(2, flight.getRegNumber());

                ps.execute();


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Integer> getAllFlightIds() {
        ObservableList<Integer> flightIds = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT flightId FROM AirportSystemdb.Flight ORDER BY flightId;");
            Statement stmt = conn.createStatement();

            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                flightIds.add(resultSet.getInt("flightId"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightIds;
    }


    public ObservableList<String> getCountriesWithPstr() {
        ObservableList<String> countries = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT DISTINCT country FROM AirportSystemdb.Location WHERE PSTR_idPSTR IS NOT NULL;");
            Statement stmt = conn.createStatement();

            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                countries.add(resultSet.getString("country"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }


    public ObservableList<String> matchCityWithCountry(String country) {
        ObservableList<String> cities = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT city FROM AirportSystemdb.Location WHERE country = '" + country + "' AND PSTR_idPSTR IS NOT NULL;");
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


    public void setBalance(double balance, int systemId) {


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = "UPDATE AirportSystemdb.Person\n" +
                    "SET\n" +
                    "balance = ?\n" +
                    "WHERE systemId = ?;";


            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDouble(1, balance);
            ps.setInt(2, systemId);

            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public int getIdFromUserName(String userName) {


        int systemId = 0;


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = "SELECT Person_systemId FROM AirportSystemdb.User WHERE userName ='" + userName + "'";


            PreparedStatement ps = conn.prepareStatement(query);


            ResultSet resultSet = ps.executeQuery(query);

            while (resultSet.next()) {
                systemId = resultSet.getInt("Person_systemId");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return systemId;


    }


    public String getBalanceFromId(int systemId) {

        Double balance = null;
        String stringBalance;
        String stringSystemId = Integer.toString(systemId);


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "SELECT balance FROM AirportSystemdb.Person WHERE systemId =" + stringSystemId + "";


            PreparedStatement ps = conn.prepareStatement(query);


            ResultSet resultSet = ps.executeQuery(query);

            while (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        stringBalance = Double.toString(balance);


        return stringBalance;

    }


    public void insertTrip(Trip trip, int locationIdFrom, int locationIdTo) {
        int tripId = 0;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query = "INSERT INTO AirportSystemdb.Trip (price, date, Flight_flightId,ticketAmount) "
                    + " VALUES (?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setDouble(1, trip.getTripPrice());
            ps.setString(2, trip.getDate());
            ps.setInt(3, trip.getFlightID());
            ps.setInt(4, trip.getTicketAmount());

            ps.execute();

            tripId = getMaxTripId();

            ps.close();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            String query2 = "INSERT INTO AirportSystemdb.Trip_has_Location" +
                    "(Trip_tripId," +
                    "Location_locationId," +
                    "isStart)" +
                    "VALUES" +
                    "(?,?,?)";
            java.lang.System.out.println("query2:" + query2);
            PreparedStatement ps2 = conn.prepareStatement(query2);

            ps2.setInt(1, tripId);

            ps2.setInt(2, locationIdFrom);
            ps2.setInt(3, 1);

            ps2.execute();


            String query4 = "INSERT INTO AirportSystemdb.Trip_has_Location" +
                    "(Trip_tripId," +
                    "Location_locationId," +
                    "isStart)" +
                    "VALUES" +
                    "(?,?,?)";

            java.lang.System.out.println("qurey4:" + query4);

            PreparedStatement ps3 = conn.prepareStatement(query4);

            ps3.setInt(1, tripId);
            ps3.setInt(2, locationIdTo);
            ps3.setInt(3, 0);

            ps3.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public Airplane getAirplaneObject(String regNumber) {

        Airplane airplane = null;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("SELECT * FROM AirportSystemdb.Airplane WHERE regNumber = '" + regNumber + "';");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {

                airplane = new Airplane(resultSet.getInt("passengerCapacity"),
                        resultSet.getInt("maxLuggageWeight"), resultSet.getString("maxSpeed"),
                        regNumber, resultSet.getString("model"));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airplane;
    }


    public int getLastFlightId() {

        int flightId = 0;


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT Max(flightId) AS lastId FROM AirportSystemdb.Flight;");

            java.lang.System.out.println(query);

            Statement stmt = conn.createStatement();


            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                flightId = resultSet.getInt("lastId");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightId;

    }

    public ObservableList<String> getTripList() {

        boolean once = false;
        int removeIndexNum = 0;

        ObservableList<String> trips = FXCollections.observableArrayList();


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip " +
                    "WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0;");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                for (int i = 0; i < resultSet.getInt("tripId") && !once; i++) {

                    trips.add(i, "");

                    removeIndexNum++;


                }


                once = true;


                trips.add(resultSet.getInt("tripId"), resultSet.getString("city") + " " +
                        resultSet.getString("country")
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        trips.remove(0, removeIndexNum);

        trips.add(0, String.valueOf(removeIndexNum));


        return trips;


    }


    public int getLocationIdFromPstrId(int pstrId) {


        int locationId = 0;
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "SELECT locationId FROM AirportSystemdb.Location where PSTR_idPSTR =  '" + pstrId + "'; ";


            PreparedStatement ps = conn.prepareStatement(query);


            ResultSet resultSet = ps.executeQuery(query);

            while (resultSet.next()) {
                locationId = resultSet.getInt("locationId");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        //stringBalance = Double.toString(balance);


        return locationId;
    }


    public int getMaxTripId() {

        int tripId = 0;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "SELECT Max(tripId) FROM AirportSystemdb.Trip";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);


            while (resultSet.next()) {
                tripId = resultSet.getInt("Max(tripId)");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripId;
    }


    public Location getLocationObject(int locationId) {


        Location location = null;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("SELECT city, country, airportName FROM AirportSystemdb.Location where locationId = '" + locationId + "';");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {

                location = new Location(resultSet.getString("city"),
                        resultSet.getString("country"), resultSet.getString("airportName"));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;


    }


    public ObservableList<String> getFilteredResults(String input, String choice) {


        ObservableList<String> trips = FXCollections.observableArrayList();
        String query = ("");

        switch (choice) {

            case "Price Ascending":
                if (input.isEmpty())
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip" +
                            " WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0 ORDER BY price ASC;");
                else
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip" +
                            " WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0 AND city '%" + input + "%' ORDER BY price ASC;");
                break;

            case "Price Descending":
                if (input.isEmpty())
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip" +
                            " WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0 ORDER BY price DESC ;");
                else
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip " +
                            " WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0 AND city '%" + input + "%' ORDER BY price DESC;");
                break;

            case "Name":
                if (input.isEmpty())
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip " +
                            " WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0 ORDER BY city ASC;");
                else
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip " +
                            "where Trip.tripId = Trip_has_Location.Trip_tripId and Location.locationId = Trip_has_Location.Location_locationId and Trip_has_Location.isStart = 0 AND city LIKE'%" + input + "%'ORDER by city ASC;");
                break;

            case "Date":
                query = ("");
                break;

            default:
                if (input.isEmpty())
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip " +
                            "WHERE Trip.tripId = Trip_has_Location.Trip_tripId AND Location.locationId = Trip_has_Location.Location_locationId AND Trip_has_Location.isStart = 0;");
                else
                    query = ("SELECT * FROM AirportSystemdb.Trip_has_Location, AirportSystemdb.Location, AirportSystemdb.Trip " +
                            "where Trip.tripId = Trip_has_Location.Trip_tripId and Location.locationId = Trip_has_Location.Location_locationId and Trip_has_Location.isStart = 0 AND city '%" + input + "%';");
                break;

            //LIKE'%" + input + "%'
        }

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {


                trips.add(resultSet.getString("city") + " "
                        + resultSet.getString("country"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return trips;
    }


    public Trip getTripObject(String tripId) {

        Trip trip = null;


        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT price, Trip.date, ticketAmount FROM AirportSystemdb.Trip where tripId = '" + tripId + "';");


            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                trip = new Trip(resultSet.getDouble("price"), resultSet.getString("date"),
                        getFlightId(tripId), resultSet.getInt("ticketAmount"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trip;


    }


    public int getFlightId(String tripId) {

        int flightId = 0;

        String query = "SELECT Flight_flightId FROM AirportSystemdb.Trip where tripId = '" + tripId + "';";


        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                flightId = resultSet.getInt("Flight_flightId");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return flightId;


    }


    public int getTripId(String flightId) {

        int tripId = 0;

        String query = "SELECT tripId FROM AirportSystemdb.Trip where Flight_flightId = '" + flightId + "';";


        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                tripId = resultSet.getInt("tripId");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tripId;


    }


    public Location getFromLocationObject(int tripID) {

        Location location = null;


        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement stmt = conn.createStatement();


            String query = "SELECT city, country, airportName FROM AirportSystemdb.Trip_has_Location," +
                    " AirportSystemdb.Location, AirportSystemdb.Trip where Trip.tripId = Trip_has_Location.Trip_tripId and " +
                    " Location.locationId = Trip_has_Location.Location_locationId and Trip_has_Location.isStart = 1" +
                    " and tripId = '" + tripID + "'";


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                location = new Location(resultSet.getString("airportName"),
                        resultSet.getString("city"), resultSet.getString("country"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;
    }


    public int getMaxPstrId() {

        int pstrId = 0;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "SELECT max(idPSTR) AS idPSTR FROM AirportSystemdb.PSTR;";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);


            while (resultSet.next()) {
                pstrId = resultSet.getInt("idPSTR");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pstrId;
    }

    public Flight getFlightObject(int flightId) {

        Flight flight = null;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("SELECT flightStatus, gate, Airplane_regNumber FROM AirportSystemdb.Flight WHERE flightId = '" + flightId + "';");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {

                flight = new Flight(resultSet.getString("flightStatus"),
                        resultSet.getString("gate"), resultSet.getString("Airplane_regNumber"));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flight;
    }


    @Override
    public ObservableList<String> getFlightinformation(int flightId) {
        ObservableList<String> flights = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = ("SELECT * FROM AirportSystemdb.Flight WHERE flightId = '" + flightId + "'");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                flights.add("Flight Status: " + resultSet.getString("flightStatus") + "\nGate: " + resultSet.getString("gate") +
                        "\nAirplanes reg number: " + resultSet.getString("Airplane_regNumber") + "\nflightID: " + resultSet.getString("flightId"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void changeFlightStatus(String newStatus, int flightId) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = "UPDATE AirportSystemdb.Flight SET FlightStatus = '" + newStatus + "' WHERE flightId = '" + flightId + "';";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTicketAmount(int tripId) {
        int ticketAmount = 0;

        String query = "SELECT ticketAmount FROM AirportSystemdb.Trip WHERE tripId = '" + tripId + "';";


        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            Statement stmt = conn.createStatement();


            stmt.addBatch(query);

            ResultSet resultSet = stmt.executeQuery(query);


            while (resultSet.next()) {

                ticketAmount = resultSet.getInt("ticketAmount");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketAmount;
    }

    @Override
    public void setTicketAmount(int ticketAmount, int tripId) {

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String q1 = "UPDATE AirportSystemdb.Trip SET ticketAmount = '" + ticketAmount + "' WHERE TripId ='" + tripId + "'";

            Statement statement = conn.prepareStatement(q1);

            statement.executeUpdate(q1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertBooking(Booking booking) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "INSERT INTO AirportSystemdb.Booking (date, totalPrice, passengerAmount, Trip_tripId)VALUES (curdate(), ?, ?, ?)";
            java.lang.System.out.println(query);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, booking.getPrice());
            ps.setInt(2, booking.getPassengers());
            ps.setInt(3, booking.getTripId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertPersonHasBooking(int systemId, int bookingId) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "INSERT INTO AirportSystemdb.Person_has_Booking (Person_systemId, Booking_bookingId)VALUES (?, ?)";
            java.lang.System.out.println(query);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, systemId);
            ps.setInt(2, bookingId);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getMaxBookingId() {
        int bookingId = 0;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {

            String query = "SELECT Max(bookingId) FROM AirportSystemdb.Booking";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);


            while (resultSet.next()) {
                bookingId = resultSet.getInt("Max(bookingId)");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingId;
    }

    public ObservableList<String> getUserBookings(String userName) {

        ObservableList<String> trips = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("select * from Person_has_Booking , User, Booking, Trip, Trip_has_Location, Location " +
                    "where Person_has_Booking.Person_systemId = User.Person_systemId and Person_has_Booking.Booking_bookingId = Booking.bookingId and Trip.tripId = Booking.Trip_tripId " +
                    "and Trip_has_Location.Trip_tripId = Trip.tripId and Trip_has_Location.Location_locationId = Location.locationId and userName = '" + userName + "' and isStart = 0;");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                trips.add(resultSet.getString("Location.city") + " " + resultSet.getString("Location.country") + " " +
                        " ID: " + resultSet.getInt("Booking.bookingId"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;


    }


    public String getBookingInfo(int bookingId) {

        String info = "";

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("select * from Flight, Person_has_Booking , User, Booking, Trip, Trip_has_Location, Location \n" +
                    "                    where Person_has_Booking.Person_systemId = User.Person_systemId and Person_has_Booking.Booking_bookingId = Booking.bookingId and Trip.tripId = Booking.Trip_tripId \n" +
                    "                    and Trip_has_Location.Trip_tripId = Trip.tripId and Trip_has_Location.Location_locationId = Location.locationId and bookingId =  '" + bookingId + "'  and isStart = 1;");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                info = ("Booking Date: " + resultSet.getString("date") + "\nTotal Price: " + resultSet.getDouble("totalPrice") + "SEK " +
                        "\nTickets Booked: " + resultSet.getInt("passengerAmount") + "\n\nFlight Date: " + resultSet.getString("Trip.date") + "\nFrom: " + resultSet.getString("city") + "   Airport: " +
                        resultSet.getString("airportName") + "\nGate: " + resultSet.getString("gate") + "\nFlight Status: " + resultSet.getString("flightStatus"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return info;

    }

    public Booking getBookingObject(int bookingId) {

        Booking booking = null;

        try (Connection conn = DriverManager.getConnection(connectionURL)) {
            String query = ("SELECT distinct date, totalPrice, passengerAmount, Trip_tripId FROM AirportSystemdb.Booking where bookingId = " + bookingId + ";");

            Statement stmt = conn.createStatement();
            stmt.addBatch(query);
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                booking = new Booking(bookingId, resultSet.getString("Date"), resultSet.getDouble("totalPrice"),
                        resultSet.getInt("passengerAmount"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;

    }

    public void cancelBookingId(int bookingId) {
        try (Connection conn = DriverManager.getConnection(connectionURL)) {


            //"SELECT Person.firstName, lastName FROM AirportSystemdb.Person, AirportSystemdb.User WHERE Person.systemId = User.Person_systemId AND User.typeOfUser = 'Employee'"
            //String query = ("DELETE FROM AirportSystemdb.User, AirportSystemdb.Person WHERE AirportSystemdb.User.Person_systemId = " + sysId + "");


            String q1 = "DELETE FROM Booking WHERE AirportSystemdb.Booking.bookingId = ?;";
            String q2 = "DELETE FROM AirportSystemdb.Person_has_Booking WHERE AirportSystemdb.Person_has_Booking.Booking_bookingId = ?;";


            PreparedStatement st = conn.prepareStatement(q1);
            PreparedStatement pst = conn.prepareStatement(q2);


            st.setInt(1, bookingId);
            pst.setInt(1, bookingId);


            pst.executeUpdate();
            st.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}





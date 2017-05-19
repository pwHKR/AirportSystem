package sample.mvc.model;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Åkesson on 2017-04-29.
 */
public class LocalFileStorage {

    private Path[] pathList = new Path[4];


    public LocalFileStorage() {

        Path path = Paths.get("logInLog.bin"); //path 0
        Path pathAirplane = Paths.get("obj.ser"); //path1
        Path pathFlight = Paths.get("obj2.ser");    //path 2
        Path pathBooking = Paths.get("obj3.ser");   //path3


        pathList[0] = path;
        pathList[1] = pathAirplane;
        pathList[2] = pathFlight;
        pathList[3] = pathBooking;


    }


    public void saveUser(String userName, String password) {

        ArrayList<String> saveList = new ArrayList<>();
        saveList.add(userName);
        saveList.add(password);

        try {
            Files.write(pathList[0], saveList, StandardOpenOption.CREATE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUsersUserName() {
        String userName = "";
        try {
            List<String> textLines = Files.readAllLines(pathList[0]);
            userName = textLines.get(0);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return userName;
    }

    public String getCurrentUsersPassword() {
        String password = "";
        try {
            List<String> textLines = Files.readAllLines(pathList[0]);
            password = textLines.get(1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return password;
    }

    public void saveAirplaneToFile(Airplane airplane) {

        ObjectOutputStream output = null;


        try {


            output = new ObjectOutputStream(Files.newOutputStream(pathList[1]));
        } catch (IOException e) {

        }

        try {
            output.writeObject(airplane);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output != null) {

            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public Airplane readAirplaneFromFile() {

        ObjectInputStream input = null;


        try {
            input = new ObjectInputStream(Files.newInputStream(pathList[1]));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Airplane record = null;
        try {

            while (true) { // loop until there is an end of EOFExeption

                record = (Airplane) input.readObject();


            }


        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (input != null) {
            try {
                input.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return record;


    }


    public void saveFlightToFile(Flight flight) {

        ObjectOutputStream output = null;


        try {


            output = new ObjectOutputStream(Files.newOutputStream(pathList[2]));
        } catch (IOException e) {

        }

        try {
            output.writeObject(flight);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output != null) {

            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public Flight readFlightFromFile() {

        ObjectInputStream input = null;


        try {
            input = new ObjectInputStream(Files.newInputStream(pathList[2]));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Flight record = null;
        try {

            while (true) { // loop until there is an end of EOFExeption

                record = (Flight) input.readObject();


            }


        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (input != null) {
            try {
                input.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return record;
    }

    public void saveBookingObjectToFile(Booking booking) {

        ObjectOutputStream output = null;


        try {


            output = new ObjectOutputStream(Files.newOutputStream(pathList[3]));
        } catch (IOException e) {

        }

        try {
            output.writeObject(booking);


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output != null) {

            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public Booking readBookingObjectFromFile() {

        ObjectInputStream input = null;


        try {
            input = new ObjectInputStream(Files.newInputStream(pathList[3]));


        } catch (IOException e) {
            e.printStackTrace();
        }

        Booking record = null;
        try {

            while (true) { // loop until there is an end of EOFExeption

                record = (Booking) input.readObject();


            }


        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (input != null) {
            try {
                input.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return record;
    }

}
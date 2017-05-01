package sample.mvc.model;

import java.io.IOException;
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
    private Path path = Paths.get("logInLog.bin");
    DataStorage dbh = new DBHandler();  //can be useful to have even if this class works with local-files

    public void saveUser(String userName, String password) {

        ArrayList<String> saveList = new ArrayList<>();
        saveList.add(userName);
        saveList.add(password);

        try {
            Files.write(path, saveList, StandardOpenOption.CREATE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUsersUserName() {
        String userName = "";
        try {
            List<String> textLines = Files.readAllLines(path);
            userName = textLines.get(0);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return userName;
    }

    public String getCurrentUsersPassword() {
        String password = "";
        try {
            List<String> textLines = Files.readAllLines(path);
            password = textLines.get(1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return password;
    }
}

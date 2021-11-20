import model.User;
import service.CSVService;

import java.util.List;

public class App {
    private static final String SAMPLE_CSV_FILE_PATH = "./users.csv";
    public static void main(String[] args) {
        CSVService csvParser = new CSVService();
        List<User> users = csvParser.importUsers(SAMPLE_CSV_FILE_PATH);
        System.out.println("Total user: " + users.size());
    }
}

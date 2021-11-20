import model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.CSVService;

import java.util.List;

public class CSVServiceTest {
    private static final String USER_CSV_FILE_PATH = "./testdata/users.csv";
    private static final String INVALID_HEADER_CSV_FILE_PATH = "./testdata/resource/invalid_header_users.csv";
    private static final String INVALID_ID_CSV_FILE_PATH = "./testdata/resource/invalid_id_users.csv";
    @Test
    public void successImportUsers(){
        CSVService service = new CSVService();
        System.out.println(INVALID_HEADER_CSV_FILE_PATH);
        List<User> user = service.importUsers(USER_CSV_FILE_PATH);
        Assertions.assertEquals(3, user.size());
    }

    @Test
    public void failedInvalidHeader() throws IllegalArgumentException{
        CSVService service = new CSVService();
        List<User> user = service.importUsers(INVALID_HEADER_CSV_FILE_PATH);
        Assertions.assertEquals(0, user.size());
    }

    @Test
    public void failedInvalidId() throws IllegalArgumentException{
        CSVService service = new CSVService();
        List<User> user = service.importUsers(INVALID_ID_CSV_FILE_PATH);
        Assertions.assertEquals(0, user.size());
    }
}

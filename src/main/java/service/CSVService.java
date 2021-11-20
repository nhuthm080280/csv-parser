package service;

import model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CSVService {
    private static final Character CSV_DELIMITER = ',';
    private static final String FILE_HEADER_MAPPING = "ID,FirstName,LastName,Email,Phone,Country";

    Logger logger = LoggerFactory.getLogger(this.getClass());
    public List<User> importUsers(String filePath) {
        logger.info("Start importing users");
        List<User> users = new ArrayList<>();
        try (
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csvParser = new CSVParser(
                reader,
                CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setDelimiter(CSV_DELIMITER)
                    .setHeader(FILE_HEADER_MAPPING).build()
            )

        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                User user = new User();
                user.setId(UUID.fromString(csvRecord.get(0)));
                user.setFirstName(csvRecord.get(1));
                user.setLastName(csvRecord.get(2));
                user.setEmail(csvRecord.get(3));
                user.setPhoneNumber(csvRecord.get(4));
                users.add(user);
            }
        } catch (IllegalArgumentException ex) {
            logger.info("Please double check header, delimiter");
        } catch (IOException ex) {
            logger.info("file not found");
        }
        logger.info("Total imported user: {}", users.size());
        return users;
    }
}

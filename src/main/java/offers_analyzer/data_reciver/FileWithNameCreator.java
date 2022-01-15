package offers_analyzer.data_reciver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileWithNameCreator {

    public static final DateTimeFormatter CREATION_DATE_FILE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yy");

    public static final String FILE_EXTENSION = ".csv";

    public File create(String fileName) {
        return create(fileName, LocalDateTime.now());
    }

    public File create(String fileName, LocalDateTime localDateTime) {
        return new File(fileName
                + localDateTime.format(CREATION_DATE_FILE_FORMATTER)
                + FILE_EXTENSION);
    }
}

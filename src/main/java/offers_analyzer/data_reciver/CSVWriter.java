package offers_analyzer.data_reciver;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVWriter<T> {

    private static final Logger logger = LogManager.getLogger(PageWalker.class);

    private final ObjectWriter writer;

    public CSVWriter(ObjectWriter writer) {
        this.writer = writer;
    }

    public void write(List<T> objectsToWrite, File csvOutputFile) {
        try {
            objectsToWrite.forEach(System.out::println);
            writer.writeValues(csvOutputFile).writeAll(objectsToWrite);
        } catch (IOException exception) {
            logger.log(Level.ERROR, "Error while writing file", exception);
        }
    }
}

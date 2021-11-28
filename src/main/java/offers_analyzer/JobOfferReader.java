package offers_analyzer;

import com.fasterxml.jackson.databind.ObjectReader;
import offers_analyzer.data_reciver.JobOffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class JobOfferReader {

    private static final Logger logger = LogManager.getLogger(JobOfferReader.class);

    public List<JobOffer> read(File file, ObjectReader objectReader) {
        try (Reader reader = new FileReader(file)) {
            return objectReader.readValues(reader).readAll().stream()
                    .map(JobOffer.class::cast)
                    .toList();
        } catch (Exception exception) {
            logger.error("Errow while parsing file", exception);
        }
        return Collections.emptyList();
    }
}

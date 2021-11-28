package offers_analyzer.data_reciver;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class StudentPlJobOffersParser {

    private final static String FILE_NAME = "Data_";

    private static final Logger logger = LogManager.getLogger(StudentPlJobOffersParser.class);

    public void parse(ObjectWriter objectWriter) {
        logger.info("Start parsing");
        List<JobOffer> jobOffers = getJobOffers();

        logger.info("Start file writing");
        CSVWriter<JobOffer> writer = new CSVWriter<>(objectWriter);
        writer.write(jobOffers, createFile());

        logger.info("File written");
    }

    private List<JobOffer> getJobOffers() {
        PageWalker pageWalker = new PageWalker();
        return pageWalker.walk("https://students.pl/oferty");
    }

    private File createFile() {
        FileWithNameCreator fileWithNameCreator = new FileWithNameCreator();
        return fileWithNameCreator.create(FILE_NAME);
    }
}

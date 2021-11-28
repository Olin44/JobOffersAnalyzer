package offers_analyzer;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import offers_analyzer.data_reciver.CSVWriter;
import offers_analyzer.data_reciver.FileWithNameCreator;
import offers_analyzer.data_reciver.JobOffer;

import java.util.List;

public class ResultAnalyzer {

    private final static String DATA_SOURCE_FILE_NAME = "Data_";

    private static final String RESULT_FILE_NAME = "Results_";

    private final JobOfferReader jobOfferReader;
    private final StatisticsProvider statisticsProvider;
    private final FileWithNameCreator fileWithNameCreator;

    public ResultAnalyzer() {
        jobOfferReader = new JobOfferReader();
        statisticsProvider = new StatisticsProvider();
        fileWithNameCreator = new FileWithNameCreator();
    }

    public void analyze(ObjectReader objectReader, ObjectWriter objectWriter) {
        List<JobOffer> jobOffers = jobOfferReader.read(fileWithNameCreator.create(DATA_SOURCE_FILE_NAME), objectReader);
        new CSVWriter<AnalyzeResults>(objectWriter)
                .write(statisticsProvider.analyze(jobOffers), fileWithNameCreator.create(RESULT_FILE_NAME));
    }
}

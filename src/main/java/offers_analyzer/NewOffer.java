package offers_analyzer;

import com.fasterxml.jackson.databind.ObjectReader;
import offers_analyzer.data_reciver.FileWithNameCreator;
import offers_analyzer.data_reciver.JobOffer;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewOffer {

    private static final String RESULT_FILE_NAME = "Data_";

    public List<JobOffer> find(LocalDateTime day1, LocalDateTime day2, ObjectReader objectReader) {
        FileWithNameCreator fileWithNameCreator = new FileWithNameCreator();
        File firstFile = fileWithNameCreator.create(RESULT_FILE_NAME, day1);
        File secondFile = fileWithNameCreator.create(RESULT_FILE_NAME, day2);

        JobOfferReader jobOfferReader = new JobOfferReader();

        List<JobOffer> jobOffersFirstDay = jobOfferReader.read(firstFile, objectReader);
        List<JobOffer> jobOffersSecondDay = jobOfferReader.read(secondFile, objectReader);
        List<JobOffer> newOffers = new ArrayList<>(jobOffersSecondDay);
        newOffers.removeAll(jobOffersFirstDay);
        return newOffers;
//        return Stream.of(jobOffersFirstDay, jobOffersSecondDay)
//                .flatMap(Collection::stream)
//                .collect(Collectors.groupingBy(JobOffer::getUrl, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(p -> p.getValue() == 1)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
    }
}

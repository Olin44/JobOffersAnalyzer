package offers_analyzer;

import offers_analyzer.data_reciver.JobOffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticsProvider {

    private static final Logger logger = LogManager.getLogger(StatisticsProvider.class);

    public static final int MAX_SIZE = 5;

    public List<AnalyzeResults> analyze(List<JobOffer> jobOfferList) {
        logger.info("Analyze data started");
        List<String> mostPopularSalary = getData(jobOfferList, JobOffer::getSalary);
        List<String> mostPopularCompanyName = getData(jobOfferList, JobOffer::getCompanyName);
        List<String> mostPopularJobCategory = getData(jobOfferList, JobOffer::getJobCategory);
        List<String> mostPopularContractType = getData(jobOfferList, JobOffer::getContractType);
        List<String> mostPopularJobTitle = getData(jobOfferList, JobOffer::getJobTitle);
        List<String> mostPopularLocation = getData(jobOfferList, JobOffer::getLocation);

        List<AnalyzeResults> analyzeResults = new ArrayList<>();
        for(int i = 0; i < MAX_SIZE; i++) {
            analyzeResults.add(new AnalyzeResults(
                    mostPopularSalary.get(i),
                    mostPopularCompanyName.get(i),
                    mostPopularJobCategory.get(i),
                    mostPopularContractType.get(i),
                    mostPopularJobTitle.get(i),
                    mostPopularLocation.get(i)));
        }
        logger.info("Analyze data end");
        return analyzeResults;
    }

    public List<String> getData(List<JobOffer> jobOfferList, Function<JobOffer, String> valueGetter) {
        return jobOfferList
                .stream()
                .map(valueGetter)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
                .limit(MAX_SIZE)
                .map(getResultsWithCount())
                .toList();
    }

    private Function<Map.Entry<String, Long>, String> getResultsWithCount() {
        return result -> result.getKey() + " Count: " + result.getValue();
    }
}

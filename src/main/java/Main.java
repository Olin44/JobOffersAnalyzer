import offers_analyzer.NewOffer;
import offers_analyzer.ResultAnalyzer;
import offers_analyzer.data_reciver.StudentPlJobOffersParser;
import offers_analyzer.utils.AnalyzerResultsAccessConfig;
import offers_analyzer.utils.JobOffersAccessConfig;

import java.time.LocalDateTime;

public class Main {
    private final static String FILE_NAME = "/data/Data_";

    private static final String RESULT_FILE_NAME = "/results/Results_";

    public static void main(String[] args) {
        JobOffersAccessConfig jobOffersAccessConfig = new JobOffersAccessConfig();
        AnalyzerResultsAccessConfig analyzerResultsAccessConfig = new AnalyzerResultsAccessConfig();

        StudentPlJobOffersParser studentPlJobOffersParser = new StudentPlJobOffersParser();
        studentPlJobOffersParser.parse(jobOffersAccessConfig.getWriter());

        ResultAnalyzer resultAnalyzer = new ResultAnalyzer();
        resultAnalyzer.analyze(jobOffersAccessConfig.getReader(), analyzerResultsAccessConfig.getWriter());
//
//        NewOffer newOffer = new NewOffer();
//        newOffer.find( LocalDateTime.of(2021, 12,9, 12, 12),
//                        LocalDateTime.now(),
//                        jobOffersAccessConfig.getReader())
//                .forEach(System.out::println);
    }
}
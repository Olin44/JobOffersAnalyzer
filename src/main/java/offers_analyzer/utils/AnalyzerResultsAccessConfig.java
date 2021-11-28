package offers_analyzer.utils;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import offers_analyzer.AnalyzeResults;

public class AnalyzerResultsAccessConfig extends CsvAccessConfig<AnalyzeResults> {

    @Override
    protected CsvSchema configureSchema() {
        return CsvSchema.builder().setUseHeader(true)
                .addColumn("salary")
                .addColumn("companyName")
                .addColumn("jobCategory")
                .addColumn("contractType")
                .addColumn("jobTitle")
                .addColumn("location")
                .build();
    }

    @Override
    protected Class<AnalyzeResults> getClassType() {
        return AnalyzeResults.class;
    }
}

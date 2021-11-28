package offers_analyzer.utils;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import offers_analyzer.data_reciver.JobOffer;

public class JobOffersAccessConfig extends CsvAccessConfig<JobOffer> {


    @Override
    protected CsvSchema configureSchema() {
        return CsvSchema.builder().setUseHeader(true)
                .addColumn("url")
                .addColumn("companyName")
                .addColumn("location")
                .addColumn("salary")
                .addColumn("contractType")
                .addColumn("jobTitle")
                .addColumn("jobDescription")
                .addColumn("jobCategory")
                .build();
    }

    @Override
    protected Class<JobOffer> getClassType() {
        return JobOffer.class;
    }
}

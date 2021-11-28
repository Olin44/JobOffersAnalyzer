package offers_analyzer;

public class AnalyzeResults {

   private String salary;
   private String companyName;
   private String jobCategory;
   private String contractType;
   private String jobTitle;
   private String location;

    public AnalyzeResults(String salary, String companyName, String jobCategory, String contractType, String jobTitle, String location) {
        this.salary = salary;
        this.companyName = companyName;
        this.jobCategory = jobCategory;
        this.contractType = contractType;
        this.jobTitle = jobTitle;
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public String getContractType() {
        return contractType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }
}

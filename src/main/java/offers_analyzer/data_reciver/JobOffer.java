package offers_analyzer.data_reciver;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Objects;

public class JobOffer {

    private String location;
    private String url;
    private String companyName;
    private String salary;
    private String contractType;
    private String jobTitle;
    private String jobDescription;
    private String jobCategory;

    public JobOffer(String url, Document doc) {
        this.url = url;
        this.companyName = getElementText(doc, "n-Listing_briefCompany");
        this.location = getElementText(doc, "n-Listing_briefLocation");
        this.salary = getElementText(doc, "n-Listing_briefSalary");
        this.contractType = getElementText(doc, "n-Listing_briefContract");
        this.jobTitle = getElementText(doc, "n-Listing_headerTitle");
        this.jobDescription = getElementText(doc, "n-Listing_body");
        this.jobCategory = getElementText(doc, "n-Tag-category");
    }

    public JobOffer() {
    }

    private String getElementText(Document doc, String className) {
        return doc.getElementsByClass(className).stream()
                .findFirst()
                .map(Element::text)
                .orElse("");
    }

    public String getLocation() {
        return location;
    }

    public String getUrl() {
        return url;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSalary() {
        return salary;
    }

    public String getContractType() {
        return contractType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    @Override
    public String toString() {
        return "JobOffer{" +
                "location='" + location + '\'' +
                ", url='" + url + '\'' +
                ", companyName='" + companyName + '\'' +
                ", salary='" + salary + '\'' +
                ", contractType='" + contractType + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobCategory='" + jobCategory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobOffer jobOffer = (JobOffer) o;
        return Objects.equals(location, jobOffer.location) && Objects.equals(url, jobOffer.url) && Objects.equals(companyName, jobOffer.companyName) && Objects.equals(salary, jobOffer.salary) && Objects.equals(contractType, jobOffer.contractType) && Objects.equals(jobTitle, jobOffer.jobTitle) && Objects.equals(jobDescription, jobOffer.jobDescription) && Objects.equals(jobCategory, jobOffer.jobCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, url, companyName, salary, contractType, jobTitle, jobDescription, jobCategory);
    }
}

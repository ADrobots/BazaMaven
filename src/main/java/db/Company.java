package db;

public class Company {

    int companyId;
    String companyName;
    Long companyInn;
    String companyMailingAdress;
    String companyEmail;
    String companyNamber;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyInn() {
        return companyInn;
    }

    public void setCompanyInn(Long companyInn) {
        this.companyInn = companyInn;
    }

    public String getCompanyMailingAdress() {
        return companyMailingAdress;
    }

    public void setCompanyMailingAdress(String companyMailingAdress) {
        this.companyMailingAdress = companyMailingAdress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyNamber() {
        return companyNamber;
    }

    public void setCompanyNamber(String companyNamber) {
        this.companyNamber = companyNamber;
    }
}

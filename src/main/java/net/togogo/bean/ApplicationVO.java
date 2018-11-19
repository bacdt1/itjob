package net.togogo.bean;

public class ApplicationVO {
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    private Application application;
    private String jobname;
    private String companyname;
    private String status;

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApplicationVO{" +
                "jobname='" + jobname + '\'' +
                ", companyname='" + companyname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

package net.togogo.bean;

public class CollectVO {

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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    private Collect collect;

    public Collect getCollect() {
        return collect;
    }

    public void setCollect(Collect collect) {
        this.collect = collect;
    }

    private String jobname;
    private String companyname;
    private String salary;

    @Override
    public String toString() {
        return "CollectVO{" +
                "jobname='" + jobname + '\'' +
                ", companyname='" + companyname + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}

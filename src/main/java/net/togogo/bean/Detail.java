package net.togogo.bean;

public class Detail {
    private Integer id;

    private String jobname;

    private String salary;

    private Integer num;

    private Integer job_Id;

    private String sex;

    private String age;

    private String degree;

    private String description;

    private Integer company_Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getJobId() {
        return job_Id;
    }

    public void setJobId(Integer jobId) {
        this.job_Id = jobId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCompanyId() {
        return company_Id;
    }

    public void setCompanyId(Integer companyId) {
        this.company_Id = companyId;
    }
}
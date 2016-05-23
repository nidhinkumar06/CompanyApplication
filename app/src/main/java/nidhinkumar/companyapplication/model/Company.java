package nidhinkumar.companyapplication.model;

import java.util.ArrayList;

/**
 * Created by user on 19-05-2016.
 */
public class Company {
    private String companyname, companyowner;
    private String startdate;
    private int companyid;
    private String description;
    private String departments;

    public Company() {
    }

    public Company(int companyid,String companyname, String companyowner, String startdate, String description,
                 String departments) {
        this.companyid=companyid;
        this.companyname = companyname;
        this.companyowner = companyowner;
        this.startdate = startdate;
        this.description = description;
        this.departments = departments;
    }
    public int getCompanyid() {
        return companyid;
    }
    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanyowner() {
        return companyowner;
    }

    public void setCompanyowner(String companyowner) {
        this.companyowner = companyowner;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

}

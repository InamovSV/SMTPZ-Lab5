package com.restapi.lab.Models.Requests;

public class CreateEmployee {
    private String fullname;
    private String position;
    private int company_id;

    public CreateEmployee(){

    }

    public CreateEmployee(String fullname, String position, int company_id){
        this.company_id = company_id;
        this.fullname = fullname;
        this.position = position;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}

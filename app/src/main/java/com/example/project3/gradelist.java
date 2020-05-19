package com.example.project3;

public class gradelist {
    String gradename;
    String gradenumber;
    String gradecontent;
    String gradesum;

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getGradenumber() {
        return gradenumber;
    }

    public void setGradenumber(String gradenumber) {
        this.gradenumber = gradenumber;
    }

    public String getGradecontent() {
        return gradecontent;
    }

    public void setGradecontent(String gradecontent) {
        this.gradecontent = gradecontent;
    }

    public String getGradesum() {
        return gradesum;
    }

    public void setGradesum(String gradesum) {
        this.gradesum = gradesum;
    }

    public gradelist(String gradename, String gradenumber, String gradecontent, String gradesum) {
        this.gradename = gradename;
        this.gradenumber = gradenumber;
        this.gradecontent = gradecontent;
        this.gradesum = gradesum;
    }
}

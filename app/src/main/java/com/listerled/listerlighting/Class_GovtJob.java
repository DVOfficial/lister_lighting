package com.listerled.listerlighting;

import androidx.annotation.Keep;

@Keep
public class Class_GovtJob {

    public String UserName;
    public String Date;
    public String SrNo;
    public String JobId;


    public String NoOfPostCategory;
    public String EducationCategory;
    public String StateCentralCategory;
    public String DepartmentCategory;

    public String Job_Company_Name;
    public String Job_Description;
    public String Post_Name_Short;
    public String Post_Name_Long;
    public String No_of_Post;
    public String Job_Title_Combined;

    public String Age_Limit;
    public String Fees;
    public String Education_Short;
    public String Education_Long;
    public String Experience_Required;
    public String Selection_Process;
    public String Salary;
    public String Who_Can_Apply;


    public String FFO_Posted_Date;
    public String Registration_Start_Date;
    public String Registration_Last_Date;
    public String Registration_Last_Date_Custom;
    public String Admit_Card_Date;
    public String Exam_Date;

    public String Official_Website;
    public String Notification_Link;
    public String Registration_Link;
    public String Registration_Link2;
    public String Copy_Website_Link;
    public String Status;
    public String CompanyImage_Link;


    private boolean expanded;


    public String queryDate;
    public String queryUserNo;
    public String queryName;
    public String queryMobile;
    public String queryEmail;
    public String queryPassword;
    public String queryUserid;
    public String queryToken;

    public Class_GovtJob(String queryDate, String queryUserNo, String queryName, String queryMobile, String queryEmail, String queryPassword, String queryUserid, String queryToken) {
        this.queryDate = queryDate;
        this.queryUserNo = queryUserNo;
        this.queryName = queryName;
        this.queryMobile = queryMobile;
        this.queryEmail = queryEmail;
        this.queryPassword = queryPassword;
        this.queryUserid = queryUserid;
        this.queryToken = queryToken;
    }

    public Class_GovtJob() {
    }


    public Class_GovtJob(String userName, String date, String srNo, String jobId, String noOfPostCategory, String educationCategory, String stateCentralCategory, String departmentCategory, String job_Company_Name, String job_Description, String post_Name_Short, String post_Name_Long, String no_of_Post, String job_Title_Combined, String age_Limit, String fees, String education_Short, String education_Long, String experience_Required, String selection_Process, String salary, String who_Can_Apply, String FFO_Posted_Date, String registration_Start_Date, String registration_Last_Date, String registration_Last_Date_Custom, String admit_Card_Date, String exam_Date, String official_Website, String notification_Link, String registration_Link, String registration_Link2, String copy_Website_Link, String status, String companyImage_Link, boolean expanded, String queryDate, String queryUserNo, String queryName, String queryMobile, String queryEmail, String queryPassword, String queryUserid, String queryToken) {
        UserName = userName;
        Date = date;
        SrNo = srNo;
        JobId = jobId;
        NoOfPostCategory = noOfPostCategory;
        EducationCategory = educationCategory;
        StateCentralCategory = stateCentralCategory;
        DepartmentCategory = departmentCategory;
        Job_Company_Name = job_Company_Name;
        Job_Description = job_Description;
        Post_Name_Short = post_Name_Short;
        Post_Name_Long = post_Name_Long;
        No_of_Post = no_of_Post;
        Job_Title_Combined = job_Title_Combined;
        Age_Limit = age_Limit;
        Fees = fees;
        Education_Short = education_Short;
        Education_Long = education_Long;
        Experience_Required = experience_Required;
        Selection_Process = selection_Process;
        Salary = salary;
        Who_Can_Apply = who_Can_Apply;
        this.FFO_Posted_Date = FFO_Posted_Date;
        Registration_Start_Date = registration_Start_Date;
        Registration_Last_Date = registration_Last_Date;
        Registration_Last_Date_Custom = registration_Last_Date_Custom;
        Admit_Card_Date = admit_Card_Date;
        Exam_Date = exam_Date;
        Official_Website = official_Website;
        Notification_Link = notification_Link;
        Registration_Link = registration_Link;
        Registration_Link2 = registration_Link2;
        Copy_Website_Link = copy_Website_Link;
        Status = status;
        CompanyImage_Link = companyImage_Link;
        this.expanded = expanded;
        this.queryDate = queryDate;
        this.queryUserNo = queryUserNo;
        this.queryName = queryName;
        this.queryMobile = queryMobile;
        this.queryEmail = queryEmail;
        this.queryPassword = queryPassword;
        this.queryUserid = queryUserid;
        this.queryToken = queryToken;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }


    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public String getQueryUserNo() {
        return queryUserNo;
    }

    public void setQueryUserNo(String queryUserNo) {
        this.queryUserNo = queryUserNo;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryMobile() {
        return queryMobile;
    }

    public void setQueryMobile(String queryMobile) {
        this.queryMobile = queryMobile;
    }

    public String getQueryEmail() {
        return queryEmail;
    }

    public void setQueryEmail(String queryEmail) {
        this.queryEmail = queryEmail;
    }

    public String getQueryPassword() {
        return queryPassword;
    }

    public void setQueryPassword(String queryPassword) {
        this.queryPassword = queryPassword;
    }

    public String getQueryUserid() {
        return queryUserid;
    }

    public void setQueryUserid(String queryUserid) {
        this.queryUserid = queryUserid;
    }

    public String getQueryToken() {
        return queryToken;
    }

    public void setQueryToken(String queryToken) {
        this.queryToken = queryToken;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNoOfPostCategory() {
        return NoOfPostCategory;
    }

    public void setNoOfPostCategory(String noOfPostCategory) {
        NoOfPostCategory = noOfPostCategory;
    }

    public String getEducationCategory() {
        return EducationCategory;
    }

    public void setEducationCategory(String educationCategory) {
        EducationCategory = educationCategory;
    }

    public String getStateCentralCategory() {
        return StateCentralCategory;
    }

    public void setStateCentralCategory(String stateCentralCategory) {
        StateCentralCategory = stateCentralCategory;
    }

    public String getDepartmentCategory() {
        return DepartmentCategory;
    }

    public void setDepartmentCategory(String departmentCategory) {
        DepartmentCategory = departmentCategory;
    }

    public String getJob_Company_Name() {
        return Job_Company_Name;
    }

    public void setJob_Company_Name(String job_Company_Name) {
        Job_Company_Name = job_Company_Name;
    }

    public String getPost_Name_Short() {
        return Post_Name_Short;
    }

    public void setPost_Name_Short(String post_Name_Short) {
        Post_Name_Short = post_Name_Short;
    }

    public String getPost_Name_Long() {
        return Post_Name_Long;
    }

    public void setPost_Name_Long(String post_Name_Long) {
        Post_Name_Long = post_Name_Long;
    }

    public String getNo_of_Post() {
        return No_of_Post;
    }

    public void setNo_of_Post(String no_of_Post) {
        No_of_Post = no_of_Post;
    }

    public String getJob_Title_Combined() {
        return Job_Title_Combined;
    }

    public void setJob_Title_Combined(String job_Title_Combined) {
        Job_Title_Combined = job_Title_Combined;
    }

    public String getAge_Limit() {
        return Age_Limit;
    }

    public void setAge_Limit(String age_Limit) {
        Age_Limit = age_Limit;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    public String getEducation_Short() {
        return Education_Short;
    }

    public void setEducation_Short(String education_Short) {
        Education_Short = education_Short;
    }

    public String getEducation_Long() {
        return Education_Long;
    }

    public void setEducation_Long(String education_Long) {
        Education_Long = education_Long;
    }

    public String getExperience_Required() {
        return Experience_Required;
    }

    public void setExperience_Required(String experience_Required) {
        Experience_Required = experience_Required;
    }

    public String getSelection_Process() {
        return Selection_Process;
    }

    public void setSelection_Process(String selection_Process) {
        Selection_Process = selection_Process;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getFFO_Posted_Date() {
        return FFO_Posted_Date;
    }

    public void setFFO_Posted_Date(String FFO_Posted_Date) {
        this.FFO_Posted_Date = FFO_Posted_Date;
    }

    public String getRegistration_Start_Date() {
        return Registration_Start_Date;
    }

    public void setRegistration_Start_Date(String registration_Start_Date) {
        Registration_Start_Date = registration_Start_Date;
    }

    public String getRegistration_Last_Date() {
        return Registration_Last_Date;
    }

    public void setRegistration_Last_Date(String registration_Last_Date) {
        Registration_Last_Date = registration_Last_Date;
    }

    public String getRegistration_Last_Date_Custom() {
        return Registration_Last_Date_Custom;
    }

    public void setRegistration_Last_Date_Custom(String registration_Last_Date_Custom) {
        Registration_Last_Date_Custom = registration_Last_Date_Custom;
    }

    public String getAdmit_Card_Date() {
        return Admit_Card_Date;
    }

    public void setAdmit_Card_Date(String admit_Card_Date) {
        Admit_Card_Date = admit_Card_Date;
    }

    public String getExam_Date() {
        return Exam_Date;
    }

    public void setExam_Date(String exam_Date) {
        Exam_Date = exam_Date;
    }

    public String getOfficial_Website() {
        return Official_Website;
    }

    public void setOfficial_Website(String official_Website) {
        Official_Website = official_Website;
    }

    public String getNotification_Link() {
        return Notification_Link;
    }

    public void setNotification_Link(String notification_Link) {
        Notification_Link = notification_Link;
    }

    public String getRegistration_Link() {
        return Registration_Link;
    }

    public void setRegistration_Link(String registration_Link) {
        Registration_Link = registration_Link;
    }

    public String getCopy_Website_Link() {
        return Copy_Website_Link;
    }

    public void setCopy_Website_Link(String copy_Website_Link) {
        Copy_Website_Link = copy_Website_Link;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }

    public String getJob_Description() {
        return Job_Description;
    }

    public void setJob_Description(String job_Description) {
        Job_Description = job_Description;
    }

    public String getWho_Can_Apply() {
        return Who_Can_Apply;
    }

    public void setWho_Can_Apply(String who_Can_Apply) {
        Who_Can_Apply = who_Can_Apply;
    }

    public String getRegistration_Link2() {
        return Registration_Link2;
    }

    public void setRegistration_Link2(String registration_Link2) {
        Registration_Link2 = registration_Link2;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCompanyImage_Link() {
        return CompanyImage_Link;
    }

    public void setCompanyImage_Link(String companyImage_Link) {
        CompanyImage_Link = companyImage_Link;
    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }
}

package com.vora.darsh.managio;

public class Leave_raw {
    public String title;

    public String message;

    public String leaveType;

    public String submissionDate;

    public String startDate;

    public String endDate;

    public String link;

    public String status;

    public String leaveID;

    public String getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(String leaveID) {
        this.leaveID = leaveID;
    }

    public Leave_raw(String title, String message, String leaveType, String submissionDate, String startDate, String endDate, String link, String status, String leaveID) {
        this.title = title;
        this.message = message;
        this.leaveType = leaveType;
        this.submissionDate = submissionDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.link = link;
        this.status = status;
        this.leaveID = leaveID;
    }

    public Leave_raw(String title, String message, String leaveType, String submissionDate, String startDate, String endDate, String link, String status) {
        this.title = title;
        this.message = message;
        this.leaveType = leaveType;
        this.submissionDate = submissionDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.link = link;
        this.status = status;
    }

    public Leave_raw() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.vora.darsh.managio;

public class Task_raw {
    public String title;

    public String details;

    public String startDate;

    public String endDate;

    public String link;

    public String status;

    public String taskId;

    public Task_raw(String title, String details, String startDate, String endDate, String link, String status, String taskId) {
        this.title = title;
        this.details = details;
        this.startDate = startDate;
        this.endDate = endDate;
        this.link = link;
        this.status = status;
        this.taskId = taskId;
    }

    public Task_raw(String title, String details, String startDate, String endDate, String link, String status) {
        this.title = title;
        this.details = details;
        this.startDate = startDate;
        this.endDate = endDate;
        this.link = link;
        this.status = status;
    }

    public Task_raw() {
    }

    public Task_raw(String title, String details, String startDate, String endDate) {
        this.title = title;
        this.details = details;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}

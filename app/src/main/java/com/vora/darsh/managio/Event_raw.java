package com.vora.darsh.managio;

public class Event_raw {

    public String title;

    public String details;

    public String eventDate;

    public String location;

    public String createdBy;

    public Event_raw() {
    }

    public Event_raw(String title, String details, String eventDate, String location, String createdBy) {
        this.title = title;
        this.details = details;
        this.eventDate = eventDate;
        this.location = location;
        this.createdBy = createdBy;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

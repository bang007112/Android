package com.example.myapplication.models;

import java.util.Date;


public class AppointmentModel {
    String ID, uerID, hour, appointmentContent, date;
    UserModel userModel;

    public String getID() {return ID;}
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return ID;
    }
    public void setUerID(String ID) {this.ID = ID;}
    public UserModel userModel(){ return userModel;}

    public String getAppointmentContent() {
        return appointmentContent;
    }
    public void setAppointmentContent(String appointmentContent) {this.appointmentContent = appointmentContent;}

    public String getHour() {return hour;}
    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {return date;}
    public void setDate(String date) {
        this.date = date;
    }
}

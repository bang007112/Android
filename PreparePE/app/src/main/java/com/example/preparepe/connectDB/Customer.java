package com.example.preparepe.connectDB;

public class Customer {
    String ID, FULLNAME, DATE_OF_BIRTH, ADDRESS;
    int AGE;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFULLNAME() {
        return FULLNAME;
    }
    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public int getAGE() {
        return AGE;
    }
    public void setAGE(int AGE) {this.AGE = AGE;}

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH;
    }
    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {this.DATE_OF_BIRTH = DATE_OF_BIRTH;}

    public String getADDRESS() {
        return ADDRESS;
    }
    public void setADDRESS(String ADDRESS) {this.ADDRESS = ADDRESS;}
}

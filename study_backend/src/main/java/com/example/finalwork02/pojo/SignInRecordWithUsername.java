package com.example.finalwork02.pojo;


public class SignInRecordWithUsername {

    private SignInRecord signInRecord;
    private String username;

    public SignInRecordWithUsername(SignInRecord signInRecord, String username) {
        this.signInRecord = signInRecord;
        this.username = username;
    }

    // Getters and setters
    public SignInRecord getSignInRecord() {
        return signInRecord;
    }

    public void setSignInRecord(SignInRecord signInRecord) {
        this.signInRecord = signInRecord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
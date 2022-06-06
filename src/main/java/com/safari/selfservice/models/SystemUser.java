package com.safari.selfservice.models;

import javax.persistence.*;


@Entity
@Table
public class SystemUser {
    @Id
    @SequenceGenerator(
            name = "user_id",
            sequenceName = "user_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id"
    )
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Integer numOfMessagesLeft;
    private String password;
    private String userState; // possible values (new, active, deactivated, unregistered)

    public SystemUser() {
    }

    public SystemUser(Integer id, String fullName, String phoneNumber, String email, Integer numOfMessagesLeft, String password, String userState) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numOfMessagesLeft = numOfMessagesLeft;
        this.password = password;
        this.userState = userState;
    }

    public SystemUser(String fullName, String phoneNumber, String email, Integer numOfMessagesLeft, String password, String userState) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numOfMessagesLeft = numOfMessagesLeft;
        this.password = password;
        this.userState = userState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumOfMessagesLeft() {
        return numOfMessagesLeft;
    }

    public void setNumOfMessagesLeft(Integer numOfMessagesLeft) {
        this.numOfMessagesLeft = numOfMessagesLeft;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", numOfMessagesLeft=" + numOfMessagesLeft +
                ", password='" + password + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }
}

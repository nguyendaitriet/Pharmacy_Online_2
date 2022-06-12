package com.triet.pharmacyonline.model;

import com.triet.pharmacyonline.utils.ValidationUtils;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class User {
    private long id;
    private String fullName;
    private int gender;
    private String phoneNumber;
    private String email;
    private String address;
    private Date dateOfBirth;
    private String creationDate;
    private int role;
    private boolean blocked;
    private String username;
    private String password;

    public User() {
    }

    public User(long id, String fullName, int gender, String phoneNumber, String email, String address, Date dateOfBirth, String creationDate, int role, boolean blocked, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.role = role;
        this.blocked = blocked;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Pattern(regexp = ValidationUtils.FULL_NAME_REGEX,
            message = "Full Name must only contain letters, capitalize first letter of each word and no redundant whitespace.")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Pattern(regexp = ValidationUtils.PHONE_REGEX,
            message = "Phone Number: First digit must be '0', second digit is form '1' to '9' and length is from 10 to 11 digits.")
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Pattern(regexp = ValidationUtils.USERNAME_REGEX,
            message = "Invalid username!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Pattern(regexp = ValidationUtils.PASSWORD_REGEX,
            message = "Invalid password!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

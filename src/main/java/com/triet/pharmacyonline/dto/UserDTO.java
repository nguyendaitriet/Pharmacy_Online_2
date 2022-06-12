package com.triet.pharmacyonline.dto;

import java.util.Date;

public class UserDTO {
    private long id;
    private String fullName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private Date dateOfBirth;
    private String creationDate;
    private String role;
    private String username;
    private boolean blocked;

    public UserDTO() {
    }

    public UserDTO(long id, String fullName, String gender, String phoneNumber, String email, String address, Date dateOfBirth, String creationDate, String role, String username, boolean blocked) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.role = role;
        this.username = username;
        this.blocked = blocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public LocalDateTime getCreationDate() throws ParseException {
//       return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(creationDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


}

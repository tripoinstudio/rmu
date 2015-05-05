package com.tripoin.rmu.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Achmad Fauzi on 4/15/2015 : 11:47 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class EmployeeDTO {
    @JsonProperty("employeeId")
    private Integer employeeId;

    @JsonProperty("fname")
    private String firstName;

    @JsonProperty("lname")
    private String lastName;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
